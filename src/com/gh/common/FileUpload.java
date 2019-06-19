package com.gh.common;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Random;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;

public class FileUpload {
	public ArrayList<String> inFile(File[] img, String address, String[] imgFileName) throws IOException {
		String realpath = ServletActionContext.getServletContext().getRealPath(address);
		ArrayList<String> al = new ArrayList<String>();
		if (img != null) {
			File savedir = new File(realpath);
			if (!savedir.getParentFile().exists())
				savedir.getParentFile().mkdirs();
			for (int i = 0; i < img.length; i++) {
				// 文件重命名
				String Extension = "";
				String Original = "";
				if (imgFileName[i] != null && imgFileName[i].length() > 0) {
					String[] fname = imgFileName[i].split("\\.");
					//Original = fname[0];
					Random rd=new Random();
					int x=rd.nextInt(1000);
					Original = "img"+x;
					Extension = fname[1];
				}
				if (Extension.length() == 0) {
					return null;
				}
				String name = Original + "~" + getUUIDFileName() + "." + Extension;
				//String name = "~" + getUUIDFileName() + "." + Extension;
				// ---------------
				al.add(name);
				File savefile = new File(savedir, name);
				FileUtils.copyFile(img[i], savefile);
			}
		}
		return al;
	}
	
	//-------图片处理后调用方法----------------
	public ArrayList<String> inFile_(File[] img_, String address, String[] img_FileName) throws IOException {
		String realpath = ServletActionContext.getServletContext().getRealPath(address);
		ArrayList<String> al = new ArrayList<String>();
		if (img_ != null) {
			File savedir = new File(realpath);
			if (!savedir.getParentFile().exists())
				savedir.getParentFile().mkdirs();
			for (int i = 0; i < img_.length; i++) {
				// 文件重命名
				String Extension = "";
				String Original = "";
				if (img_FileName[i] != null && img_FileName[i].length() > 0) {
					String[] fname = img_FileName[i].split("\\.");
					//Original = fname[0];
					Original = "img_"+i;
					Extension = fname[1];
				}
				if (Extension.length() == 0) {
					return null;
				}
				String name = Original + "~" + getUUIDFileName() + "." + Extension;
			
				//String name = "~" + getUUIDFileName() + "." + Extension;
				// ---------------
				al.add(name);
				File savefile = new File(savedir, name);
				FileUtils.copyFile(img_[i], savefile);
			}
		}
		return al;
	}

	
	
	public static String getUUIDFileName() {
		Calendar now = Calendar.getInstance();
		String now_YEAR = "" + now.get(Calendar.YEAR);
		now_YEAR = now_YEAR.substring(2, 4);
		String now_MONTH = "" + (now.get(Calendar.MONTH) + 1);
		String now_DATE = "" + now.get(Calendar.DATE);
		String now_HOUR = "" + now.get(Calendar.HOUR_OF_DAY);
		String now_MINUTE = "" + now.get(Calendar.MINUTE);
		String now_SECOND = "" + now.get(Calendar.SECOND);

		String tmp = now_YEAR + now_MONTH + now_DATE + now_HOUR + now_MINUTE + now_SECOND;

		return tmp;
	}
}
