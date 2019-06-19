package com.gh.common;

import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class DateUtil {

	/**
	 * 把一个字符串变成 一个java.util.Date类型的对象
	 * 
	 * @param dateStr
	 *            日期字符串
	 * 
	 * @param format
	 *            格式字符串
	 * 
	 *            说明 这俩个参数必须相对应;
	 * @return 返回日期
	 */
	public static final String yyyy_MM_dd_HH_mm_ss = "yyyy-MM-dd HH:mm:ss";
	public static java.util.Date parseDate(String dateStr, String format) {
		java.util.Date date = null;
		Locale locale = new Locale("en", "US"); // 美国人的习惯 要不然那种 zzz EEE 无法转过去
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format, locale);
		// 必须捕获异常
		try {
			date = simpleDateFormat.parse(dateStr);
		} catch (ParseException px) {
			px.printStackTrace();
		}
		return date;
	}

	/**
	 * 
	 * 一个java.util.Date类型的对象 变成一个字符串
	 * 
	 * @param date
	 *            日期
	 * @param format
	 *            格式
	 * @return String 返回字符型日期
	 */
	public static String format(java.util.Date date, String format) {
		String result = "";
		try {
			if (date != null) {
				java.text.DateFormat df = new java.text.SimpleDateFormat(format);
				result = df.format(date);
			}
		} catch (Exception e) {
		}
		return result;
	}

	/**
	 * 返回年份
	 * 
	 * @param date
	 *            日期
	 * @return 返回年份
	 */
	public static int getYear(java.util.Date date) {
		java.util.Calendar c = java.util.Calendar.getInstance();
		c.setTime(date);
		return c.get(java.util.Calendar.YEAR);
	}

	/**
	 * 返回月份
	 * 
	 * @param date
	 *            日期
	 * @return 返回月份
	 */
	public static int getMonth(java.util.Date date) {
		java.util.Calendar c = java.util.Calendar.getInstance();
		c.setTime(date);
		return c.get(java.util.Calendar.MONTH) + 1;
	}

	/**
	 * 返回日份
	 * 
	 * @param date
	 *            日期
	 * @return 返回日份
	 */
	public static int getDay(java.util.Date date) {
		java.util.Calendar c = java.util.Calendar.getInstance();
		c.setTime(date);
		return c.get(java.util.Calendar.DAY_OF_MONTH);
	}

	/**
	 * 返回小时
	 * 
	 * @param date
	 *            日期
	 * @return 返回小时
	 */
	public static int getHour(java.util.Date date) {
		java.util.Calendar c = java.util.Calendar.getInstance();
		c.setTime(date);
		return c.get(java.util.Calendar.HOUR_OF_DAY);
	}

	/**
	 * 返回分钟
	 * 
	 * @param date
	 *            日期
	 * @return 返回分钟
	 */
	public static int getMinute(java.util.Date date) {
		java.util.Calendar c = java.util.Calendar.getInstance();
		c.setTime(date);

		return c.get(java.util.Calendar.MINUTE);
	}

	/**
	 * 返回秒钟
	 * 
	 * @param date
	 *            日期
	 * @return 返回秒钟
	 */
	public static int getSecond(java.util.Date date) {
		java.util.Calendar c = java.util.Calendar.getInstance();
		c.setTime(date);
		return c.get(java.util.Calendar.SECOND);
	}

	/**
	 * 返回毫秒
	 * 
	 * @param date
	 *            日期
	 * @return 返回毫秒
	 */
	public static long getMillis(java.util.Date date) {
		java.util.Calendar c = java.util.Calendar.getInstance();
		c.setTime(date);
		return c.getTimeInMillis();
	}

	/**
	 * 返回格式化的字符串日期yyyy/MM/dd
	 * 
	 * @param date
	 *            日期
	 * @return 返回字符串日期
	 */
	public static String getStringDate(java.util.Date date) {
		return format(date, "yyyy/MM/dd");
	}
	
	public static String getStringDate1(java.util.Date date) {
		return format(date, "yyyy-MM-dd");
	}

	/**
	 * 返回格式化的字符串时间HH:mm:ss
	 * 
	 * @param date
	 *            日期
	 * @return 返回字符串时间
	 */
	public static String getStringTime(java.util.Date date) {
		return format(date, "HH:mm:ss");
	}

	/**
	 * 返回格式化的字符串时间yyyy/MM/dd HH:mm:ss
	 * 
	 * @param date
	 *            日期
	 * @return 返回字符型日期时间
	 */
	public String getStringDateTime(java.util.Date date) {
		return format(date, "yyyy/MM/dd HH:mm:ss");
	}

	/**
	 * 日期加天数
	 * 
	 * @param date
	 *            日期
	 * @param day
	 *            天数
	 * @return 返回相加后的日期
	 */
	public static java.util.Date addDate(java.util.Date date, int day) {
		java.util.Calendar c = java.util.Calendar.getInstance();
		c.setTimeInMillis(getMillis(date) + ((long) day) * 24 * 3600 * 1000);
		return c.getTime();
	}

	public String addDate(String dateStr, int day) throws ParseException {
		try{
		SimpleDateFormat si = new SimpleDateFormat("yyyy-MM-dd");
		long s=si.parse(dateStr).getTime();
		Date dt = DateUtil.addDate(new Date(s),1); 
		return getStringDateTime(dt); 
		}catch(Exception ex){
			return "";
		}
	}

	/**
	 * 日期减天数 求相差几天的问题
	 * 
	 * @param date
	 *            日期
	 * @param date1
	 *            日期
	 * @return 返回相减后的日期
	 */
	public static int diffDate(java.util.Date date, java.util.Date date1) {
		return (int) ((getMillis(date) - getMillis(date1)) / (24 * 3600 * 1000));
	}

	public static java.util.Date parseDate(String dateStr) {
		return parseDate(dateStr, "yyyy/MM/dd");
	}

	/**
	 * 取得指定月份的第一天
	 * 
	 * @param strdate
	 *            String
	 * @return String
	 */

	public static String getMonthBegin(String strdate) {

		java.util.Date date = parseDate(strdate);// 注意这时候的格式 要按"yyyy/MM/dd"
		return format(date, "yyyy/MM") + "/01";// 最后得到的 本来是可以任意格式的;
												// 但是考虑到下一方法调用到这一方法的结果;
	} // 在parseDate 转的时候就 不是"yyyy/MM/dd"这种格式就转不了

	/**
	 * 取得指定月份的最后一天
	 * 
	 * @param strdate
	 *            String
	 * @return String
	 */
	public static String getMonthEnd(String strdate) {
		java.util.Date date = parseDate(getMonthBegin(strdate));
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.MONTH, 1);// 加一个月
		calendar.add(Calendar.DAY_OF_YEAR, -1);// 减一天
		return getStringDate(calendar.getTime());
	}
}