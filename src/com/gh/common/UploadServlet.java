package com.gh.common;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItemIterator;
import org.apache.commons.fileupload.FileItemStream;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.fileupload.util.Streams;

/**
 * Servlet implementation class for Servlet: UploadServlet
 * 
 */
public class UploadServlet extends javax.servlet.http.HttpServlet implements
		javax.servlet.Servlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	File tmpDir = null;// 初始化上传文件的临时存放目录
	File saveDir = null;// 初始化上传文件后的保存目录

	public UploadServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		try {
			String pp = null;
			String upto = null;
			if (ServletFileUpload.isMultipartContent(request)) {
				DiskFileItemFactory dff = new DiskFileItemFactory();// 创建该对象
				dff.setRepository(tmpDir);// 指定上传文件的临时目录
				dff.setSizeThreshold(10240000);// 指定在内存中缓存数据大小,单位为byte
				ServletFileUpload sfu = new ServletFileUpload(dff);// 创建该对象
				sfu.setFileSizeMax(50000000);// 指定单个上传文件的最大尺寸
				sfu.setSizeMax(10000000);// 指定一次上传多个文件的总尺寸
				FileItemIterator fii = sfu.getItemIterator(request);// 解析request
																	// 请求,并返回FileItemIterator集合
				while (fii.hasNext()) {
					FileItemStream fis = fii.next();// 从集合中获得一个文件流
					if (!fis.isFormField() && fis.getName().length() > 0) {// 过滤掉表单中非文件域
						String fileName = fis.getName().substring(
								fis.getName().lastIndexOf("\\"));// 获得上传文件的文件名
						System.out.println("request.getRealPath()=="
								+ request.getRealPath("/"));
						String uploadPath = request.getRealPath("/")
								+ "dataimages\\";// 选定上传的目录此处为当前目录

						if (!new File(uploadPath).isDirectory())// 选定上传的目录此处为当前目录，没有则创建
							new File(uploadPath).mkdirs();
						System.out.println("uploadPath=" + uploadPath);
						fileName = fileName
								.substring(fileName.lastIndexOf("."));// 获取从.开始到最后的字符
						// 将时间转化为字符串用于给文件或者文件夹改名，防止传上来的图片名称相同
						Date time = new Date();
						String dirTime = String.valueOf(time.getTime());
						//
						BufferedInputStream in = new BufferedInputStream(
								fis.openStream());// 获得文件输入流

						// BufferedOutputStream out = new
						// BufferedOutputStream(new FileOutputStream(new
						// File(saveDir+"\\"+dirTime+fileName)));//获得文件输出流
						BufferedOutputStream out = new BufferedOutputStream(
								new FileOutputStream(new File(uploadPath + "\\"
										+ dirTime + fileName)));// 获得文件输出流
						// pp为已经上传的文件
						pp = uploadPath + dirTime + fileName;
						System.out.println("pp=" + pp);
						// upto为上传的路径
						upto = uploadPath;

						Streams.copy(in, out, true);// 开始把文件写到你指定的上传文件夹
					}

				}
				/*
				 * //定义解压字符串，用于解压上传的rar文件，注意此处需要一个winrar.exe文件 String
				 * jieya=request.getRealPath("/")+
				 * "WinRAR.exe x -t -o+ -p- \""+pp+"\" \""+upto+"\""; //String
				 * jieya=
				 * "D:\\Tomcat 5.5\\webapps\\fileupload\\WinRAR.exe x -t -o+ -p- \""
				 * +pp+"\" \""+upto+"\"";
				 * 
				 * Process p=Runtime.getRuntime().exec(jieya);//将传输的rar文件解压
				 */
				response.getWriter().println("File upload successfully!!!");// 终于成功了,还不到你的上传文件中看看,你要的东西都到齐了吗
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void init() throws ServletException {// 初始化，没什么意义
		super.init();
		String tmpPath = "c:\\tmpdir";
		String savePath = "c:\\updir";
		tmpDir = new File(tmpPath);
		saveDir = new File(savePath);
		if (!tmpDir.isDirectory())
			tmpDir.mkdir();
		if (!saveDir.isDirectory())
			saveDir.mkdir();

	}

}