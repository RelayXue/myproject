package com.gh.action.weixin.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Calendar;
import java.util.Random;

import javax.servlet.ServletContext;

import org.apache.commons.io.FileUtils;

import com.gh.common.StringUtil;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

/**
 * @ClassName Upload 
 * @Description 文件上传
 * @author oriental_pearl
 * @date 2014-5-22
 * @version V1.0
 */
public class Upload {
	/**
	 * @param file 文件流
	 * @param fileName 文件名称
	 * @param path 保存地址
	 * @return
	 * @throws IOException
	 */
	public static String upload(File file,String fileName, String path)
	throws IOException {
		String Extension="";                //后缀名
		if(fileName!=null&&fileName.length()>0){
			String[] fname=fileName.split("\\.");
			Extension=fname[1];
		}
		if(Extension.length()==0){
			return "";
		}
		if(StringUtil.isTrimBlank(path)){
			return "";
		}
		String name="IMG"+getUUIDFileName()+"."+Extension;
		File target = new File(path, name);
		FileUtils.copyFile(file, target);

		PicCompress picCompress = new PicCompress(target,name,path);
		picCompress.compressPic();
		return name;// 保存文件的存放路径
	}
	 /**
	  * 文件上传
	  * @param file 上传文件
	  * @param fileName 上传文件名
	  * @param address 文件保存路径
	  * @param context
	  * @throws IOException
	  */
	public static String upload(File file,String fileName, String address,ServletContext context)
	throws IOException {
		String Extension="";                //后缀名
		if(fileName!=null&&fileName.length()>0){
			String[] fname=fileName.split("\\.");
			Extension=fname[1];
		}
		String targetDirectory = context.getRealPath(address);
		if(Extension.length()==0){
			return null;
		}
		String name=getUUIDFileName()+"."+Extension;
		File target = new File(targetDirectory, name);
		FileUtils.copyFile(file, target);

		PicCompress picCompress = new PicCompress(target,name,targetDirectory);
		picCompress.compressPic();
		return address+"/"+name;// 保存文件的存放路径
	}
	
	//base64字符串转化成图片  
    public static boolean GenerateImage(String imgStr,String name,String path)  
    {   //对字节数组字符串进行Base64解码并生成图片  
        if (StringUtil.isTrimBlank(imgStr)) //图像数据为空  
            return false;  
        BASE64Decoder decoder = new BASE64Decoder();  
        try   
        {  
            //Base64解码  
            byte[] b = decoder.decodeBuffer(imgStr);  
            for(int i=0;i<b.length;++i)  
            {  
                if(b[i]<0)  	
                {//调整异常数据  
                    b[i]+=256;  
                }  
            }  
            //生成jpeg图片  
            OutputStream out = new FileOutputStream(path+name);      
            out.write(b);  
            out.flush();  
            out.close();  
            return true;  
        }   
        catch (Exception e)   
        {  
            return false;  
        }  
    }
    
    public static String GetImageStr(String path)
    {//将图片文件转化为字节数组字符串，并对其进行Base64编码处理
        InputStream in = null;
        byte[] data = null;
        //读取图片字节数组
        try 
        {
            in = new FileInputStream(path);        
            data = new byte[in.available()];
            in.read(data);
            in.close();
        } 
        catch (IOException e) 
        {
            //e.printStackTrace();
            return "";
        }
        //对字节数组Base64编码
        BASE64Encoder encoder = new BASE64Encoder();
        return encoder.encode(data);//返回Base64编码过的字节数组字符串
    }
	
	public static String getUUIDFileName() {
		Calendar now = Calendar.getInstance();
		String now_YEAR = "" + now.get(Calendar.YEAR);
		String now_MONTH = "" + (now.get(Calendar.MONTH) + 1);
		String now_DATE = "" + now.get(Calendar.DATE);
		String now_HOUR = "" + now.get(Calendar.HOUR_OF_DAY);
		String now_MINUTE = "" + now.get(Calendar.MINUTE);
		String now_SECOND = "" + now.get(Calendar.SECOND);

		String tmp = now_YEAR + now_MONTH + now_DATE + now_HOUR + now_MINUTE
		+ now_SECOND + new Random().nextInt(10);

		return tmp;
	}


}
