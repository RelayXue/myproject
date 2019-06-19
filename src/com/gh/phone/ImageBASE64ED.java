package com.gh.phone;

import java.io.FileInputStream;
import java.io.RandomAccessFile;

public class ImageBASE64ED {
	
	/**
	 * 图片BASE64 编码
	 * 
	 * @author zhujie
	 */
	public static String getPicBASE64(String picPath) {
		String content = null;
		try {
			FileInputStream fileForInput = new FileInputStream(picPath);
			byte[] bytes = new byte[fileForInput.available()];
			fileForInput.read(bytes);
			
			content = new sun.misc.BASE64Encoder().encode(bytes); // 具体的编码方法
			fileForInput.close();
			fileForInput = null;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return content;
	}
	
	/**
	 * 对图片BASE64 解码
	 * 
	 * @author zhujie
	 */
	public static void getPicFormatBASE64(String str, String picPath) {
		try {
			
			byte[] result = new sun.misc.BASE64Decoder().decodeBuffer(str.trim());
			RandomAccessFile inOut = new RandomAccessFile(picPath, "rw"); // r,rw,rws,rwd
			inOut.write(result);
			inOut.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

