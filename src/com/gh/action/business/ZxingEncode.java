package com.gh.action.business;

import java.io.File;
import java.io.IOException;
import java.util.Hashtable;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.gh.common.UUIDCreater;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
 

public class ZxingEncode {
	/**
	 * @see 生成二维码图片
	 * @param fuid 主键
	 * @param type 二维码类别
	 * @param path 二维码路径
	 * @return  二维码内容
	 */
	
	public static String gocode(String fuid,String type,String path){
		HttpServletRequest request=ServletActionContext.getRequest();
		//String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
        String contents = type+";"+fuid;  
        Hashtable<EncodeHintType, String> hints = new Hashtable<EncodeHintType, String>();  
        hints.put(EncodeHintType.CHARACTER_SET, "utf-8");  
        BitMatrix matrix = null;  
        try {  
  
            matrix = new MultiFormatWriter().encode(contents,  
                    BarcodeFormat.QR_CODE, 380, 380, hints);  
  
        } catch (WriterException e) {  
  
            e.printStackTrace();  
  
        }  
        File file = new File(path);  
        try {  
            MatrixToImageWriter.writeToFile(matrix, "png", file);  
            return contents;
        } catch (IOException e) {
        	 e.printStackTrace();  
        	 return null;
        }  
    
		
	}
}
