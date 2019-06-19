package com.gh.common;

import java.io.UnsupportedEncodingException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SplitChinese {
	/**
	 * 该方法是用于截短中英混合字符串
	 * 
	 * @param text:需要截短的字符串
	 * @param length:要保留的字符串长度
	 * @return 完成截短的字符串.如被截短则以...结尾
	 * 
	 */
	public static String splitStr(String text, int length) {
		int textLength = text.length();
		text = text.replace("&ldquo;","“");
		text = text.replace("&rdquo;","”");
		text=text.replace(" ", "");
		text=text.replace("&nbsp;", "");
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
		String str = returnStr.toString();
		str = str.replace("“","&ldquo;");
		str = str.replace("”","&rdquo;");
		return str;
	}
	/**
	 * 该方法是用于截短中英混合字符串
	 * 
	 * @param text:需要截短的字符串
	 * @param length:要保留的字符串长度
	 * @param endWith:截短后要追加的字符串
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
	
	//隐藏手机中间号码   13757179587 
	public static String splitPhone(String phone) {
		if(phone!=null&&phone.length()>0){
			if(phone.trim().length()==11){
				phone=phone.substring(0,3)+"****"+phone.substring(7,11);
				return phone;
			}else{
				return "";
			}
		}else{
			return "";
		}
	}
	//数字转英文
	public static String toABC(int i) {
		return (" "+(char)i);
	}
    
    /**
     * 过滤html标签
     * @param inputString
     * @return
     */
	public static String Html2Text(String inputString) {      
        String htmlStr = inputString; // 含html标签的字符串      
        String textStr = "";      
        java.util.regex.Pattern p_script;      
        java.util.regex.Matcher m_script;      
        java.util.regex.Pattern p_style;      
        java.util.regex.Matcher m_style;      
        java.util.regex.Pattern p_html;      
        java.util.regex.Matcher m_html;      
    
        java.util.regex.Pattern p_html1;      
        java.util.regex.Matcher m_html1;      
    
       try {      
            String regEx_script = "<[//s]*?script[^>]*?>[//s//S]*?<[//s]*?///[//s]*?script[//s]*?>"; // 定义script的正则表达式{或<script[^>]*?>[//s//S]*?<///script>      
            String regEx_style = "<[//s]*?style[^>]*?>[//s//S]*?<[//s]*?///[//s]*?style[//s]*?>"; // 定义style的正则表达式{或<style[^>]*?>[//s//S]*?<///style>      
            String regEx_html = "<[^>]+>"; // 定义HTML标签的正则表达式      
            String regEx_html1 = "<[^>]+";      
            String regEx_html2 = "&[a-z]{0,};";      
            p_script = Pattern.compile(regEx_script, Pattern.CASE_INSENSITIVE);      
            m_script = p_script.matcher(htmlStr);      
            htmlStr = m_script.replaceAll(""); // 过滤script标签      
    
            p_style = Pattern.compile(regEx_style, Pattern.CASE_INSENSITIVE);      
            m_style = p_style.matcher(htmlStr);      
            htmlStr = m_style.replaceAll(""); // 过滤style标签      
    
            p_html = Pattern.compile(regEx_html, Pattern.CASE_INSENSITIVE);      
            m_html = p_html.matcher(htmlStr);      
            htmlStr = m_html.replaceAll(""); // 过滤html标签      
    
            p_html1 = Pattern.compile(regEx_html1, Pattern.CASE_INSENSITIVE);      
            m_html1 = p_html1.matcher(htmlStr);      
            htmlStr = m_html1.replaceAll(""); // 过滤html标签      
            
            p_html1 = Pattern.compile(regEx_html2, Pattern.CASE_INSENSITIVE);      
            m_html1 = p_html1.matcher(htmlStr);      
            htmlStr = m_html1.replaceAll(""); // 过滤html标签      
            
            textStr = htmlStr.replaceAll(" ", "");      
    
        } catch (Exception e) {      
            System.err.println("Html2Text: " + e.getMessage());      
        }      
    
       return textStr;// 返回文本字符串      
    }
}
