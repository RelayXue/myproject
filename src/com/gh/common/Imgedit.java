package com.gh.common;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.struts2.ServletActionContext;

/**
 * 图片上传修改
 * 
 * @author Administrator
 * 
 */
public class Imgedit {

	/**
	 * 
	 * @param reNum 删除图片标识
	 * @param imgNum 修改图片标识
 	 * @param dimg 原图
	 * @param img 修改后图
	 * @param imgFileName 
	 * @return String 组装好的图片名称
	 * @throws IOException
	 */
	public static String modify(String reNum, String imgNum, String dimg,
			File[] img, String[] imgFileName, boolean flag) throws IOException {
		ArrayList<String> al = new ArrayList<String>();
		if (dimg != null) {
			String oldList[] = dimg.split(",");
			for (int c = 0; c < oldList.length; c++) {
				al.add(oldList[c]);
			}

		}
		if (reNum != null && reNum.length() > 0) {
			String num[] =common.orderList(reNum.split(","));//排序
			int tt = al.size();
			if (tt == num.length) {
				al.clear();//清空

			} else {
				if (num != null && num.length > 0) {
					for (int c = 0; c < num.length; c++) {
						if (Integer.parseInt(num[c]) < tt) {
							int index=Integer.parseInt(num[c]);
							al.remove(index);
						}
					}
				}
			}
		}
		
		if (imgNum != null && imgNum.length() > 0) {
			String num[] = imgNum.split(",");
			// --------------取出上传图片----
			FileUpload fileupload = new FileUpload();
			ArrayList<String> re = fileupload.inFile(img, "/upload",
					imgFileName);
			if (re != null && re.size() > 0) {
				for (int a = 0; a < re.size(); a++) {
					if (al != null && al.size() > 0) {
						// 判断是否有图片新增
						if (Integer.parseInt(num[a]) > al.size() - 1) {
							al.add(re.get(a));
						} else {
							// 无图片新增执行修改
							for (int c = 0; c < al.size(); c++) {
								al.set(Integer.parseInt(num[a]), re.get(a));
							}
						}
					}else{
						al.add(re.get(a));//当图片全部删除，并重新新增的时候进行添加
					}
					
				}
				if(!flag){
					//压缩图片
					for(int i=0;i<re.size();i++){
						ImageCompression mypic = new ImageCompression();
						String targetDirectory = ServletActionContext.getServletContext().getRealPath("upload");
						mypic.compressPic(targetDirectory + "/", targetDirectory + "/", re.get(i), "B_"+re.get(i), 900, 700, true);
						mypic.compressPic(targetDirectory + "/", targetDirectory + "/", re.get(i), "L_"+re.get(i), 300, 280, true);
					
					}
				}
			}
		}
		String imgRe = "";
		for (int c = 0; c < al.size(); c++) {
			imgRe += al.get(c) + ",";
		}
		imgRe=imgRe.length()>0?imgRe.substring(0,imgRe.length()-1):"";
		return imgRe;
	}
	/**
	 * 
	 * @param reNum1 删除图片标识
	 * @param imgNum1 修改图片标识
 	 * @param dimg1 原图
	 * @param img1 修改后图
	 * @param imgFileName1 
	 * @return String 组装好的图片名称
	 * @throws IOException
	 */
	public static String modify_(String reNum_, String imgNum_, String dimg_,
			File[] img_, String[] img_FileName) throws IOException {
		ArrayList<String> al = new ArrayList<String>();
		if (dimg_ != null) {
			String oldList[] = dimg_.split(",");
			for (int c = 0; c < oldList.length; c++) {
				al.add(oldList[c]);
			}
			System.out.println("-----这里的al值是：-----"+al.size());
		}
		if (reNum_ != null && reNum_.length() > 0) {
			String num[] =common.orderList(reNum_.split(","));//排序
			int tt = al.size();
			if (tt == num.length) {
				al.clear();//清空

			} else {
				if (num != null && num.length > 0) {
					for (int c = 0; c < num.length; c++) {
						if (Integer.parseInt(num[c]) < tt) {
							int index=Integer.parseInt(num[c]);
							al.remove(index);
						}
					}
				}
			}
		}
		if (imgNum_ != null && imgNum_.length() > 0) {
			String num[] = imgNum_.split(",");
			// --------------取出上传图片----
			FileUpload fileupload = new FileUpload();
			ArrayList<String> re = fileupload.inFile_(img_, "/upload",
					img_FileName);
			if (re != null && re.size() > 0) {
				for (int a = 0; a < re.size(); a++) {
					if (al != null && al.size() > 0) {
						// 判断是否有图片新增
						if (Integer.parseInt(num[a]) > al.size() - 1) {
							al.add(re.get(a));
						} else {
							// 无图片新增执行修改
							for (int c = 0; c < al.size(); c++) {
								al.set(Integer.parseInt(num[a]), re.get(a));
							}
						}
					}
				}
				//压缩图片
				for(int i=0;i<re.size();i++){
					ImageCompression mypic = new ImageCompression();
					String targetDirectory = ServletActionContext.getServletContext().getRealPath("upload");
					mypic.compressPic(targetDirectory + "/", targetDirectory + "/", re.get(i), "B_"+re.get(i), 900, 700, true);
					mypic.compressPic(targetDirectory + "/", targetDirectory + "/", re.get(i), "L_"+re.get(i), 300, 280, true);
					}

			}
		}
		String imgRe_ = "";
		for (int c = 0; c < al.size(); c++) {
			imgRe_ += al.get(c) + ",";
		}
		imgRe_=imgRe_.length()>0?imgRe_.substring(0,imgRe_.length()-1):"";
		return imgRe_;
	}
}
