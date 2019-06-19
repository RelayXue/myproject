package com.gh.common;

import java.io.UnsupportedEncodingException;
import java.net.InetAddress;
import java.net.NetworkInterface;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;

public class common {
	/**
	 * 该方法是用于获取物理MAC地址
	 * 
	 * @param InetAddress
	 *            :IP地址
	 * @return MAC地址
	 * 
	 */
	private static String getMACAddress(InetAddress ia) throws Exception {
		// 获得网络接口对象（即网卡），并得到mac地址，mac地址存在于一个byte数组中。
		byte[] mac = NetworkInterface.getByInetAddress(ia).getHardwareAddress();

		// 下面代码是把mac地址拼装成String
		StringBuffer sb = new StringBuffer();

		for (int i = 0; i < mac.length; i++) {
			if (i != 0) {
				sb.append("-");
			}
			// mac[i] & 0xFF 是为了把byte转化为正整数
			String s = Integer.toHexString(mac[i] & 0xFF);
			sb.append(s.length() == 1 ? 0 + s : s);
		}

		// 把字符串所有小写字母改为大写成为正规的mac地址并返回
		return sb.toString().toUpperCase();
	}
	
	/**
	 * 获取访问者IP
	 * @param request
	 * @return
	 */
    public static String getIpAddr(HttpServletRequest request) {
        String ip = request.getHeader("X-Real-IP");
        if (!StringUtils.isBlank(ip) && !"unknown".equalsIgnoreCase(ip)) {
            return ip;
        }
        ip = request.getHeader("X-Forwarded-For");
        if (!StringUtils.isBlank(ip) && !"unknown".equalsIgnoreCase(ip)) {
            // 多次反向代理后会有多个IP值，第一个为真实IP。
            int index = ip.indexOf(',');
            if (index != -1) {
                return ip.substring(0, index);
            } else {
                return ip;
            }
        } else {
            return request.getRemoteAddr();
        }
    }

	/**
	 * 该方法是用于截短中英混合字符串
	 * 
	 * @param text
	 *            :需要截短的字符串
	 * @param length
	 *            :要保留的字符串长度
	 * @return 完成截短的字符串.如被截短则以...结尾
	 * 
	 */
	public static String splitStr(String text, int length) {
		int textLength = text.length();
		int byteLength = 0;
		StringBuffer returnStr = new StringBuffer();
		for (int i = 0; i < textLength && byteLength < length * 2; i++) {
			String str_i = text.substring(i, i + 1);
			if (str_i.getBytes().length == 1) {// 英文
				byteLength++;
			} else {// 中文
				byteLength += 2;
			}
			returnStr.append(str_i);
		}
		try {
			if (byteLength < text.getBytes("gbk").length) {
				returnStr.append("...");
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return returnStr.toString();
	}

	/**
	 * 该方法是用于截短中英混合字符串
	 * 
	 * @param text
	 *            :需要截短的字符串
	 * @param length
	 *            :要保留的字符串长度
	 * @param endWith
	 *            :截短后要追加的字符串
	 * @return 完成截短的字符串.
	 * 
	 */
	public static String splitStr(String text, int length, String endWith) {
		int textLength = text.length();
		int byteLength = 0;
		StringBuffer returnStr = new StringBuffer();
		for (int i = 0; i < textLength && byteLength < length * 2; i++) {
			String str_i = text.substring(i, i + 1);
			if (str_i.getBytes().length == 1) {// 英文
				byteLength++;
			} else {// 中文
				byteLength += 2;
			}
			returnStr.append(str_i);
		}
		try {
			if (byteLength < text.getBytes("gbk").length) {
				returnStr.append(endWith);
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return returnStr.toString();
	}

	/**
	 * 该方法是用于获取select Code
	 */
	public static String getCode(String code) {
		String re = "";
		if (code != null && code.length() > 0) {
			String tt[] = code.split(",");
			if (tt != null && tt.length > 0) {
				for (int a = 0; a < tt.length; a++) {
					if (!tt[a].trim().equals("-1")) {
						re = tt[a].trim();
					}
				}
			}
		}
		return re;
	}

	/**
	 * 将数组中的数字大小从大到小重新排序
	 */
	public static String[] orderList(String[] code) {
		int max = 0;
		int tmp = 0;
		for (int i = 0; i < code.length; i++) {
			max = i;//
			/** 查找第 i大的数，直到记下第 i大数的位置 ***/
			for (int j = i + 1; j < code.length; j++) {
				if (Integer.parseInt(code[max]) < Integer.parseInt(code[j]))
					max = j;// 记下较大数位置，再次比较，直到最大
			}
			/*** 如果第 i大数的位置不在 i,则交换 ****/
			if (i != max) {
				tmp = Integer.parseInt(code[i]);
				code[i] = code[max];
				code[max] = tmp + "";
			}
		}
		return code;
	}
	/**
	 * 将天气描述转换为图片
	 */
	public static String WeatherImg(String cont) {
		String re="weather-4.png";
		if(cont.contains("雨")){
			re="weather-3.png";
		}
		if(cont.contains("雪")){
			re="weather-7.png";
		}
		if(cont.equals("多云转晴")||cont.equals("晴转多云")){
			re="weather-1.png";
		}
		if(cont.equals("多云")||cont.equals("阴")||cont.equals("多云转阴")||cont.equals("阵雨转阴")){
			re="weather-2.png";
		}
		if(cont.contains("小雨")||cont.equals("中雨")||cont.equals("阵雨")||cont.equals("阴转阵雨")){
			re="weather-3.png";
		}
		if(cont.equals("晴")){
			re="weather-4.png";
		}
		if(cont.equals("雷阵雨")){
			re="weather-5.png";
		}
		if(cont.equals("大雨")||cont.equals("暴雨")||cont.equals("大暴雨")){  
			re="weather-6.png";
		}
		if(cont.equals("小雪")){  
			re="weather-7.png";
		}
		if(cont.equals("大雪")){  
			re="weather-8.png";
		}
		if(cont.equals("中雪")){  
			re="weather-9.png";
		}
		return re;
	}
}
