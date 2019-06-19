package com.gh.phone;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class TimeUtils {
	static public long ONE_DAY = 1000 * 60 * 60 * 24;
	/**
	 * EEE MMM dd HH:mm:ss zzz yyyy
	 */
	public static SimpleDateFormat ENGLISH_DATE_FORMAT = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy", java.util.Locale.ENGLISH);
	/**
	 * yyyy-MM-dd HH:mm:ss
	 */
	public static SimpleDateFormat SIMPLE_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	/**
	 * HH:mm:ss
	 */
	public static SimpleDateFormat SIMPLE_MOMENT_FORMAT = new SimpleDateFormat("HH:mm:ss");
	/**
	 * yyyy-MM-dd
	 */
	public static SimpleDateFormat SIMPLE_DATE_FORMAT_A = new SimpleDateFormat("yyyy-MM-dd");
	/**
	 * yyyy年MM月dd日
	 */
	public static SimpleDateFormat CN_SHORT_DATE_FORMAT = new SimpleDateFormat("yyyy年MM月dd日");
	/**
	 * yyyy年MM月dd日 HH点mm分
	 */
	public static SimpleDateFormat CN_SHORT_DATE_FORMAT_2 = new SimpleDateFormat("yyyy年MM月dd日 HH点mm分");
	/**
	 * yyyy-MM-dd HH:mm
	 */
	public static SimpleDateFormat CN_SHORT_DATE_FORMAT_3 = new SimpleDateFormat("yyyy-MM-dd HH:mm");
	/**
	 * yyyyMMdd
	 */
	public static SimpleDateFormat SHORT_DATE_FORMAT = new SimpleDateFormat("yyyyMMdd");
	/**
	 * yyyyMMddHHmmss
	 */
	public static SimpleDateFormat LONG_SIMPLE_DATE_FORMAT = new SimpleDateFormat("yyyyMMddHHmmss");
	/**
	 * HHmmss
	 */
	public static SimpleDateFormat SIMPLE_HHMMSS = new SimpleDateFormat("HHmmss");
	/**
	 * yyyy/MM/dd
	 */
	public static SimpleDateFormat SHORT_DATE_FORMAT_2 = new SimpleDateFormat("yyyy/MM/dd");

	/**
	 * 得到时间的00:00:00
	 * 
	 * @param date
	 * @return
	 */
	public static Date getDateDay(Date date) {
		if (date == null)
			return date;
		try {
			return TimeUtils.SIMPLE_DATE_FORMAT_A.parse(TimeUtils.SIMPLE_DATE_FORMAT_A.format(date));
		} catch (ParseException e) {
			e.printStackTrace();
			return date;
		}
	}

	/**
	 * 取得两个时间段的时间间隔 return t2 与t1的间隔天数 throws ParseException
	 * 如果输入的日期格式不是0000-00-00 格式抛出异常
	 */
	public static int getBetweenDays(String t1, String t2) throws ParseException {
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		int betweenDays = 0;
		Date d1 = format.parse(t1);
		Date d2 = format.parse(t2);
		Calendar c1 = Calendar.getInstance();
		Calendar c2 = Calendar.getInstance();
		c1.setTime(d1);
		c2.setTime(d2);
		// 保证第二个时间一定大于第一个时间
		if (c1.after(c2)) {
			c1 = c2;
			c2.setTime(d1);
		}
		int betweenYears = c2.get(Calendar.YEAR) - c1.get(Calendar.YEAR);
		betweenDays = c2.get(Calendar.DAY_OF_YEAR) - c1.get(Calendar.DAY_OF_YEAR);
		for (int i = 0; i < betweenYears; i++) {
			betweenDays += countDays(c1.get(Calendar.YEAR));
			c1.set(Calendar.YEAR, (c1.get(Calendar.YEAR) + 1));
		}
		return betweenDays;
	}

	/**
	 * 获得当前周- 周一的日期
	 * @return
	 */
	public static String getCurrentMonday() {
		int mondayPlus = getMondayPlus();
		GregorianCalendar currentDate = new GregorianCalendar();
		currentDate.add(GregorianCalendar.DATE, mondayPlus);
		Date monday = currentDate.getTime();
		DateFormat df = DateFormat.getDateInstance();
		String preMonday = df.format(monday);
		return preMonday;
	}

	/**
	 * 本周1往后推N天日期
	 * @return
	 */
	public static String getPreviousSunday(int day) {
		int mondayPlus = getMondayPlus();
		GregorianCalendar currentDate = new GregorianCalendar();
		currentDate.add(GregorianCalendar.DATE, mondayPlus + day);
		Date monday = currentDate.getTime();
		DateFormat df = DateFormat.getDateInstance();
		String preMonday = df.format(monday);
		return preMonday;
	}

	/**
	 * 获得当前日期与本周一相差的天数
	 * @return
	 */
	public static int getMondayPlus() {
		Calendar cd = Calendar.getInstance();
		// 获得今天是一周的第几天，星期日是第一天，星期二是第二天......
		int dayOfWeek = cd.get(Calendar.DAY_OF_WEEK);
		if (dayOfWeek == 1) {
			return -6;
		} else {
			return 2 - dayOfWeek;
		}
	}

	public static int countDays(int year) {
		int n = 0;
		for (int i = 1; i <= 12; i++) {
			n += countDays(i, year);
		}
		return n;
	}

	public static int countDays(int month, int year) {
		int count = -1;
		switch (month) {
		case 1:
		case 3:
		case 5:
		case 7:
		case 8:
		case 10:
		case 12:
			count = 31;
			break;
		case 4:
		case 6:
		case 9:
		case 11:
			count = 30;
			break;
		case 2:
			if (year % 4 == 0)
				count = 29;
			else
				count = 28;
			if ((year % 100 == 0) & (year % 400 != 0))
				count = 28;
		}
		return count;
	}
	
	public static Date parse(SimpleDateFormat sdf, String date_str){
		Date date = null;
		try {
			date = sdf.parse(date_str);
		} catch (Exception e) {}
		return date;
	} 
	
	public static String format(SimpleDateFormat sdf, Date date){
		String str = "";
		try {
			str = sdf.format(date);
		} catch (Exception e) {	}
		return str;
	}
	
	/**
	 * 对月份进行增减
	 * @param d
	 * @param vc
	 * @return
	 */
	static public Date MonthPT(Date d, int vc){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");// 格式化对象
		Calendar calendar = Calendar.getInstance();// 日历对象
		calendar.setTime(d);// 设置当前日期
		calendar.add(Calendar.MONTH, vc);// 月份减一
		//System.out.println(sdf.format(calendar.getTime()));// 输出格式化的日期
		return calendar.getTime();
	}
}