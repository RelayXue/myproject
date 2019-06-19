package com.gh.common;

import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.Random;

import javax.servlet.ServletContext;

import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;

public class Upload {
	//file 上传文件、filename 上传文件名、address 文件保存路径
	public static String upload(File file,String fileName,  String address,ServletContext context)
			throws IOException {
		String Extension="";                //后缀名
		String Original="";                //后缀名
		if(fileName!=null&&fileName.length()>0){
			String[] fname=fileName.split("\\.");
			Extension=fname[1];
			Original=fname[0];
		}
		//String targetDirectory = ServletActionContext.getRequest().getRealPath("/upload");
		String targetDirectory = context.getRealPath(address);
		if(Extension.length()==0){
			return null;
		}
		String name=Original+"~"+getUUIDFileName2()+"."+Extension;
		File target = new File(targetDirectory, name);
		FileUtils.copyFile(file, target);
		return address+"/"+name;// 保存文件的存放路径
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
	public static String getUUIDFileName2() {
		Calendar now = Calendar.getInstance();
		String now_YEAR = "" + now.get(Calendar.YEAR);
		now_YEAR=now_YEAR.substring(2,4);
		String now_MONTH = "" + (now.get(Calendar.MONTH) + 1);
		String now_DATE = "" + now.get(Calendar.DATE);
		String now_HOUR = "" + now.get(Calendar.HOUR_OF_DAY);
		String now_MINUTE = "" + now.get(Calendar.MINUTE);
		String now_SECOND = "" + now.get(Calendar.SECOND);

		String tmp = now_YEAR + now_MONTH + now_DATE + now_HOUR + now_MINUTE
				+ now_SECOND;

		return tmp;
	}


}
