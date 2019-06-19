package com.gh.action.weixin.file;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

/**
 * 图片压缩
 * @ClassName PicCompress 
 * @author oriental_pearl
 * @date 2013-7-15
 */
public class PicCompress {
	private File file = null; // 文件对象 
	private String inputDir; // 输入图路径
	private String outputDir; // 输出图路径
	private String inputFileName; // 输入图文件名
	private String outputFileName; // 输出图文件名
	private int outputWidth = 800; // 默认输出图片宽
	private int outputHeight = 600; // 默认输出图片高
	private boolean proportion = false; // 是否等比缩放标记(默认为等比缩放) 

	public PicCompress() { // 初始化变量
		inputDir = ""; 
		outputDir = ""; 
		inputFileName = ""; 
		outputFileName = ""; 
	} 
	
	public PicCompress(File file,String outputName,String outputDir){
		this.file = file;
		this.outputFileName = outputName;
		this.outputDir = outputDir;
	}
	
	public PicCompress(File file,String inputDir,String outputDir,String outputName){
		this.file = file;
		this.inputDir = inputDir; 
		this.outputDir = outputDir; 
		this.outputFileName = outputName;
	}
	
	public PicCompress(File file,String inputDir,String outputDir,String inputName,String outputName){
		this(file,inputDir,outputDir,outputName);
		this.inputFileName = inputName;
	}

	public String compressPic(){
		try { 
			if (!file.exists()) { 
				return ""; 
			} 
			Image img = ImageIO.read(file); 
			// 判断图片格式是否正确 
			if (img.getWidth(null) == -1) {
				System.out.println(" can't read,retry!" + "<BR>"); 
				return "no"; 
			} else { 
				int newWidth; int newHeight; 
				// 判断是否是等比缩放 
				if (this.proportion) { 
					// 为等比缩放计算输出的图片宽度及高度 
					double rate1 = ((double) img.getWidth(null)) / (double) outputWidth + 0.1; 
					double rate2 = ((double) img.getHeight(null)) / (double) outputHeight + 0.1; 
					// 根据缩放比率大的进行缩放控制 
					double rate = rate1 > rate2 ? rate1 : rate2; 
					newWidth = (int) (((double) img.getWidth(null)) / rate); 
					newHeight = (int) (((double) img.getHeight(null)) / rate); 
				} else { 
					newWidth = outputWidth; // 输出的图片宽度 
					newHeight = outputHeight; // 输出的图片高度 
				} 
				BufferedImage tag = new BufferedImage((int) newWidth, (int) newHeight, BufferedImage.TYPE_INT_RGB); 

				/*
				 * Image.SCALE_SMOOTH 的缩略算法 生成缩略图片的平滑度的
				 * 优先级比速度高 生成的图片质量比较好 但速度慢
				 */ 
				tag.getGraphics().drawImage(img.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH), 0, 0, null);
				FileOutputStream out = new FileOutputStream(outputDir +"\\"+ outputFileName);
				// JPEGImageEncoder可适用于其他图片类型的转换 
				JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out); 
				encoder.encode(tag); 
				out.close(); 
			} 
		} catch (IOException ex) { 
			ex.printStackTrace(); 
		} 
		return "ok";
	}
	public String getInputDir() {
		return inputDir;
	}
	public void setInputDir(String inputDir) {
		this.inputDir = inputDir;
	}
	public String getOutputDir() {
		return outputDir;
	}
	public void setOutputDir(String outputDir) {
		this.outputDir = outputDir;
	}
	public String getInputFileName() {
		return inputFileName;
	}
	public void setInputFileName(String inputFileName) {
		this.inputFileName = inputFileName;
	}
	public String getOutputFileName() {
		return outputFileName;
	}
	public void setOutputFileName(String outputFileName) {
		this.outputFileName = outputFileName;
	}
	public int getOutputWidth() {
		return outputWidth;
	}
	public void setOutputWidth(int outputWidth) {
		this.outputWidth = outputWidth;
	}
	public int getOutputHeight() {
		return outputHeight;
	}
	public void setOutputHeight(int outputHeight) {
		this.outputHeight = outputHeight;
	}
	public boolean isProportion() {
		return proportion;
	}
	public void setProportion(boolean proportion) {
		this.proportion = proportion;
	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}
	
	public static void main(String[] args) {
		File file = new File("D:\\Program Files\\apache-tomcat-6.0.20\\webapps\\Home\\upload\\20137152349110.jpg");
		PicCompress picCompress = new PicCompress(file,"20137152349110.jpg","D:\\Program Files\\apache-tomcat-6.0.20\\webapps\\Home\\upload\\");
		System.out.println(picCompress.compressPic());
	}

}
