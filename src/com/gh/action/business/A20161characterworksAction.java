package com.gh.action.business;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.struts2.ServletActionContext;

import com.gh.action.system.CompetenceManager;
import com.gh.base.Action;
import com.gh.common.FileUpload;
import com.gh.common.ImageCompression;
import com.gh.common.Imgedit;
import com.gh.entity.A20161activity;
import com.gh.entity.A20161characterworks;
import com.gh.service.A20161activityService;
import com.gh.service.A20161characterworksService;

/**
 * @ClassName A20161characterworksAction
 * @Description 用于对A20161characterworks对象逻辑跳转
 * @author tujing
 * @date 2015-04-01
 */
public class A20161characterworksAction extends Action{
	
	private static final long serialVersionUID = 1L;
	private A20161characterworksService a20161characterworksService;
    private A20161characterworks a20161characterworks;
    private List<A20161characterworks> a20161characterworks_list;
    private A20161activity a20161activity;
    private List<A20161activity> a20161activitie_list;
    private A20161activityService a20161activityService;
    private String skey;
    private String ord;
    private String id;
    private int  isVote;
    private int score;
    private String type;
    private String choose;
	//----图片上传----
	private String imgNum;
	private String reNum;
    public String show(){ 
    	String roleid=(String)request.getSession().getAttribute("roleid");
		com=CompetenceManager.getCom(roleid, "characterworks");
		//--------------------操作权限控制
		if(!com.getHisSelect()){
			return "error";
		}
    	 String SqlWhere="";
    	 if(type!=null&&type.length()>0){
    		 if(type.equals("undefined")){
        		 type="0";
        	 }
  		    SqlWhere+=" type="+type;
  		    
  		 } else{
  			SqlWhere+=" type=0";
  		 }
 		if(skey!=null&&skey.length()>0){
 		    SqlWhere+=" and  title like '%"+skey+"%' or  peoplename like '%"+skey+"%'  ";
 		 } 
 		//--------------------
 	    pageSize=10;
 	    ord="votetype desc,createtime ";
 		indexPage=indexPage==0?1:indexPage;
 		totalPage=a20161characterworksService.getRecordCount(SqlWhere);
 		a20161characterworks_list=a20161characterworksService.selectByPage(indexPage,pageSize, SqlWhere, "fuid");
 		String sql="select * from a2016_1_activity";
 		a20161activitie_list=a20161activityService.execSql(sql);
 		if(a20161activitie_list!=null&&a20161activitie_list.size()>0){
			a20161activity=a20161activitie_list.get(0);
		}
 		return "characterwork";
    }
    public String findByType(){
    	String roleid=(String)request.getSession().getAttribute("roleid");
		com=CompetenceManager.getCom(roleid, "characterworks");
		//--------------------操作权限控制
		if(!com.getHisSelect()){
			return "error";
		}
    	 String SqlWhere="";
 		if(type!=null&&type.length()>0){
 		    SqlWhere+=" type="+type;
 		 } 
 		//--------------------
 	    pageSize=10;
 		indexPage=indexPage==0?1:indexPage;
 		totalPage=a20161characterworksService.getRecordCount(SqlWhere);
 		a20161characterworks_list=a20161characterworksService.selectByPage(indexPage,pageSize, SqlWhere,"fuid");
 		String sql="select * from a2016_1_activity";
 		a20161activitie_list=a20161activityService.execSql(sql);
 		if(a20161activitie_list!=null&&a20161activitie_list.size()>0){
			a20161activity=a20161activitie_list.get(0);
		}
 		return "characterwork";
    }
    public String show1(){
    	String roleid=(String)request.getSession().getAttribute("roleid");
		com=CompetenceManager.getCom(roleid, "characterworks");
		//--------------------操作权限控制
		if(!com.getHisSelect()){
			return "error";
		}
    	 String SqlWhere=" type = 1 ";
 		if(skey!=null&&skey.length()>0){
 		    SqlWhere+="  title like '%"+skey+"%' or  peoplename like '%"+skey+"%'  ";
 		 } 
 		//--------------------
 	    pageSize=10;
 		indexPage=indexPage==0?1:indexPage;
 		totalPage=a20161characterworksService.getRecordCount(SqlWhere);
 		a20161characterworks_list=a20161characterworksService.selectByPage(indexPage,pageSize, SqlWhere, "fuid");
 		String sql="select * from a2016_1_activity";
 		a20161activitie_list=a20161activityService.execSql(sql);
 		if(a20161activitie_list!=null&&a20161activitie_list.size()>0){
			a20161activity=a20161activitie_list.get(0);
		}
 		return "manager";
    }
	/**
	 * @see 设置投票
	 * @return
	 */
	public String setVote(){
		A20161characterworks a20161characterwor=a20161characterworksService.findById(id);
		if(a20161characterwor!=null){
			a20161characterwor.setVotetype(isVote);
			a20161characterworksService.updateSelective(a20161characterwor);
		}
		return "show";
	}
	/**
	 * @see 设置分数
	 * @return
	 */
	public String setScore(){
		A20161characterworks a20161characterwor=a20161characterworksService.findById(id);
		if(a20161characterwor!=null){
			a20161characterwor.setExpertmark(score);
		/*	int num=0;
			Double total=0.0;
			if(a20161characterwor.getNum()<=10){
				num=5;
			}else if(a20161characterwor.getNum()>10&&a20161characterwor.getNum()<=20){
				num=10;
			}else if(a20161characterwor.getNum()>20&&a20161characterwor.getNum()<=30){
				num=15;
			}else if(a20161characterwor.getNum()>30&&a20161characterwor.getNum()<=40){
				num=20;
			}else if(a20161characterwor.getNum()>40&&a20161characterwor.getNum()<=60){
				num=25;
			}else if(a20161characterwor.getNum()>60&&a20161characterwor.getNum()<=80){
				num=30;
			}else if(a20161characterwor.getNum()>80&&a20161characterwor.getNum()<=100){
				num=35;
			}else if(a20161characterwor.getNum()>100){
				num=40;
			}
			total=(a20161characterwor.getExpertmark()*0.6)+num*0.4;
			a20161characterwor.setTotal(total);*/
			a20161characterworksService.updateSelective(a20161characterwor);
		}
		return "show";
	}
	/**
	 * @see 查看详情
	 * @return
	 */
	public String showDetail(){
		a20161characterworks=a20161characterworksService.findById(id);
		String sql="select * from a2016_1_activity";
 		a20161activitie_list=a20161activityService.execSql(sql);
 		if(a20161activitie_list!=null&&a20161activitie_list.size()>0){
			a20161activity=a20161activitie_list.get(0);
		}
		return "detail";
	}
	public String delete(){
		a20161characterworksService.delete(id);
		return "show1";
	}
	public String edit(){
		a20161characterworks=a20161characterworksService.findById(id);
		return "edit";
	}
	public String save() throws IOException{
		/*if(a20161characterworks.getFuid()!=null){
			a20161characterworksService.updateSelective(a20161characterworks);
		}else{
			a20161characterworks.setCreatetime(new Date());
			a20161characterworks.setFuid(UUIDCreater.getUUID());
			a20161characterworksService.save(a20161characterworks);
		}
		*/
		
		String roleid = (String) request.getSession().getAttribute("roleid");
		String id = a20161characterworks.getFuid();
		if (id != null && id.length() > 0) {
			com=CompetenceManager.getCom(roleid, "characterworks");
			if (!com.getHisUpdate()) {
				return "error";
			}
			//------------图片修改
			A20161characterworks a20161characterwork = a20161characterworksService.findById(id);
			String dimg = a20161characterwork.getImgurl();
			//System.out.println(imgNum);
			boolean flag=false;  ///flag :false:表示图片进行压缩,     flag： true 表示图片不进行压缩
			String imgRe = Imgedit.modify(reNum,imgNum, dimg, img, imgFileName,flag);
			if (imgRe.length() > 0) {
				a20161characterworks.setImgurl(imgRe);
			} else {
				a20161characterworks.setImgurl("default.jpg");
			}
			//----------------
			
			a20161characterworksService.updateSelective(a20161characterworks);
		} else {
			com=CompetenceManager.getCom(roleid, "characterworks");
			if (!com.getHisUpdate()) {
				return "error";
			}
			//新建时多图上传
			FileUpload fileupload=new FileUpload();
			ArrayList<String> re=fileupload.inFile(img, "/upload", imgFileName);
			if(re!=null&&re.size()>0){
				String imges="";
				for(int a=0;a<re.size();a++){
					imges+=re.get(a)+",";
				}
				imges=imges.length()>0?imges.substring(0,imges.length()-1):"";
				System.out.println("--------"+imges);
				a20161characterworks.setImgurl(imges);
				for(int i=0;i<re.size();i++){
					//压缩图片
					ImageCompression mypic = new ImageCompression();
					String targetDirectory = ServletActionContext.getServletContext().getRealPath("upload");
					mypic.compressPic(targetDirectory + "/", targetDirectory + "/", re.get(i), "B_"+re.get(i), 900, 700, true);
					mypic.compressPic(targetDirectory + "/", targetDirectory + "/", re.get(i), "L_"+re.get(i), 300, 280, true);
				}
				
			}else{
				a20161characterworks.setImgurl("default.jpg");
			}
			a20161characterworks.setCreatetime(new Date());
			a20161characterworks.setFuid(this.fuid());
			a20161characterworks.setType(1);
			a20161characterworks.setNum(0);
			a20161characterworks.setExpertmark(0);
			a20161characterworksService.save(a20161characterworks);

		}
		return "show1";
	}
	
	/**
	 * @see  新闻 删除单条数据或多条数据
	 * @author xiao
	 */
	public String delete_s(){
		String roleid=(String)request.getSession().getAttribute("roleid");
		com=CompetenceManager.getCom(roleid, "characterworks");
		if(!com.getHisDelete()){
			return "error";
		}
		if (id != null && id.length() > 0) {
			String ids[] = id.split(",");
			if (ids != null && ids.length > 0) {
				for (int a = 0; a < ids.length; a++) {
					a20161characterworksService.delete(ids[a]);
				}
			}
		} else {
			if (id != null && id.length() > 0) {
				a20161characterworksService.delete(id);
			}
		}
		return "show1";
	}
	
	public String fuid(){
		String ret="";
		String sql="select * from a2016_1_characterworks order by fuid desc";
		a20161characterworks_list=a20161characterworksService.execSql(sql);
		if(a20161characterworks_list.size()==0){
			ret="0001";
		}else{
			int num=Integer.parseInt(a20161characterworks_list.get(0).getFuid());
			num++;
			ret=num+"";
			if(ret.length()==1){
				ret="000"+ret;
			}else if(ret.length()==2){
				ret="00"+ret;
			}else if(ret.length()==3){
				ret="0"+ret;
			}
		}
		System.out.println(ret);
		return ret;
	}
	
	
	
	
	 /** 
     * 根据地址获得数据的字节流 
     * @param strUrl 网络连接地址 
     * @return 
     */  
    public static byte[] getImageFromNetByUrl(String strUrl){  
        try {  
            URL url = new URL(strUrl);  
            HttpURLConnection conn = (HttpURLConnection)url.openConnection();  
            conn.setRequestMethod("GET");  
            conn.setConnectTimeout(5 * 1000);  
            InputStream inStream = conn.getInputStream();//通过输入流获取图片数据  
            byte[] btImg = readInputStream(inStream);//得到图片的二进制数据  
            return btImg;  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
        return null;  
    }  
    /** 
     * 从输入流中获取数据 
     * @param inStream 输入流 
     * @return 
     * @throws Exception 
     */  
    public static byte[] readInputStream(InputStream inStream) throws Exception{  
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();  
        byte[] buffer = new byte[1024];  
        int len = 0;  
        while( (len=inStream.read(buffer)) != -1 ){  
            outStream.write(buffer, 0, len);  
        }  
        inStream.close();  
        return outStream.toByteArray();  
    }  
    /** 
     * 将图片写入到磁盘 
     * @param img 图片数据流 
     * @param fileName 文件保存时的名称 
     */  
    public static void writeImageToDisk(byte[] img, String fileName,String type,int num){  
        try {
        	String url="D:\\我为乌镇拍封面";
        	File file = new File(url);
        	if  (!file .exists()  && !file .isDirectory()){       
                file .mkdir();
            } 
        	if("0".equals(type)){
        		url+="\\手机组";
        	}else{
        		url+="\\相机组";
        	}
        	file = new File(url);
        	if  (!file .exists()  && !file .isDirectory()){       
                file .mkdir();
            } 
        	url+="\\"+num;
        	file = new File(url);
        	if  (!file .exists()  && !file .isDirectory()){       
                file .mkdir();
            } 
            FileOutputStream fops = new FileOutputStream(file+"\\"+fileName);  
            fops.write(img);  
            fops.flush();  
            fops.close();  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
    }  
	//图片导出
	public String downLoad() throws IOException{
		PrintWriter out=ServletActionContext.getResponse().getWriter();
		String sql="SELECT * FROM a2016_1_characterworks WHERE type="+type+" ORDER BY fuid";
		//String sql="SELECT * FROM a2016_1_characterworks WHERE fuid='0001' or fuid='0002' ORDER BY fuid";
		a20161characterworks_list=a20161characterworksService.execSql(sql);
		String imgName="";
		int count=0;
		String url="";
		for(int i=0;i<a20161characterworks_list.size();i++){
			imgName=a20161characterworks_list.get(i).getImgurl();
			count=selectCount(',', imgName);
			for(int j=0;j<=count;j++){
				if(j==count){
					url="http://60.12.184.19/upload/"+imgName;
					byte[] btImg = getImageFromNetByUrl(url);
					if(null != btImg && btImg.length > 0){  
			            String fileName = (j+1)+".jpg";  
			            writeImageToDisk(btImg, fileName, type, (i+1));
			        }
				}else{
					url="http://60.12.184.19/upload/"+imgName.substring(0, imgName.indexOf(','));
					byte[] btImg = getImageFromNetByUrl(url);
					if(null != btImg && btImg.length > 0){  
			            String fileName = (j+1)+".jpg";  
			            writeImageToDisk(btImg, fileName,type, (i+1));
			        }
				}
				imgName=imgName.substring(imgName.indexOf(',')+1, imgName.length());
			}
		}
		out.print(true);
		return null;
	}
	
	
	
	
	
	
	//信息Excel导出
	public String exportExcel(){
		String sql="select * from a2016_1_characterworks where type="+type+" ORDER BY fuid";
		a20161characterworks_list=a20161characterworksService.execSql(sql);
		exportExcelWork(a20161characterworks_list);
		return null;
	}
	//Excel导出方法
	public String exportExcelWork(List<A20161characterworks> list){
		String ret="OK";
		try {
			// 第一步，创建一个webbook，对应一个Excel文件  
			HSSFWorkbook wb = new HSSFWorkbook();  
	        // 第三步，在webbook中添加一个sheet,对应Excel文件中的sheet
	        String fileName="";
	        if(list.get(0).getType()==0){
	        	fileName="手机组信息";
	        }else{
	        	fileName="相机组信息";
	        }
	        HSSFSheet sheet = wb.createSheet(fileName);
	        //创建标题栏
	         
	        HSSFRow row = sheet.createRow(0);//创建一行
	        row.createCell((short) 0).setCellValue("序号");
	        row.createCell((short) 1).setCellValue("作者");
	        row.createCell((short) 2).setCellValue("手机号");
	        row.createCell((short) 3).setCellValue("标题");
	        //内容部分
	        if(list.size()>0){
		        for(int i=0;i<list.size();i++){
		        	row = sheet.createRow(i+1);//创建一行
		        	row.createCell((short) 0).setCellValue(i+1);
		        	row.createCell((short) 1).setCellValue(list.get(i).getPeoplename());
		        	row.createCell((short) 2).setCellValue(list.get(i).getPhone());
		        	row.createCell((short) 3).setCellValue(list.get(i).getTitle());
		        }
	        }
	        response.setContentType("application/vnd.ms-excel");
	        String name=fileName+".xls";
	        response.setHeader("Content-disposition", "attachment;filename="+ new String(name.getBytes("GB2312"),"ISO8859-1"));    
	        OutputStream ouputStream = response.getOutputStream();
			wb.write(ouputStream);    
	        ouputStream.flush();    
	        ouputStream.close();
		} catch (IOException e) {
			ret="系统提示：Excel文件导出失败，原因："+ e.toString();
			System.out.println(ret);   
			e.printStackTrace();
		}
		return ret;
	}
	
	
	
	
	
	public static int selectCount(char a,String b){
		int count=0;
		for(int i=0;i<b.length();i++){
			if(a==b.charAt(i)){
				count++;
			}
		}
		return count;
	}
	/**
	 * getter、setter方法
	 */
    public A20161characterworks getA20161characterworks() {
		return a20161characterworks;
	}
	public void setA20161characterworks(A20161characterworks a20161characterworks) {
		this.a20161characterworks = a20161characterworks;
	}
	public void setA20161characterworksService(A20161characterworksService a20161characterworksService) {
		this.a20161characterworksService = a20161characterworksService;
	}
	public A20161characterworksService getA20161characterworksService() {
		return a20161characterworksService;
	}

	public List<A20161characterworks> getA20161characterworks_list() {
		return a20161characterworks_list;
	}

	public void setA20161characterworks_list(
			List<A20161characterworks> a20161characterworks_list) {
		this.a20161characterworks_list = a20161characterworks_list;
	}

	public String getSkey() {
		return skey;
	}

	public void setSkey(String skey) {
		this.skey = skey;
	}

	public String getOrd() {
		return ord;
	}

	public void setOrd(String ord) {
		this.ord = ord;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public int getIsVote() {
		return isVote;
	}
	public void setIsVote(int isVote) {
		this.isVote = isVote;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	public String getImgNum() {
		return imgNum;
	}
	public void setImgNum(String imgNum) {
		this.imgNum = imgNum;
	}
	public String getReNum() {
		return reNum;
	}
	public void setReNum(String reNum) {
		this.reNum = reNum;
	}
	public A20161activity getA20161activity() {
		return a20161activity;
	}
	public void setA20161activity(A20161activity a20161activity) {
		this.a20161activity = a20161activity;
	}
	public List<A20161activity> getA20161activitie_list() {
		return a20161activitie_list;
	}
	public void setA20161activitie_list(List<A20161activity> a20161activitie_list) {
		this.a20161activitie_list = a20161activitie_list;
	}
	public A20161activityService getA20161activityService() {
		return a20161activityService;
	}
	public void setA20161activityService(A20161activityService a20161activityService) {
		this.a20161activityService = a20161activityService;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getChoose() {
		return choose;
	}
	public void setChoose(String choose) {
		this.choose = choose;
	}
}
