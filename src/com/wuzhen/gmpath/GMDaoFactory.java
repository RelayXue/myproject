package com.wuzhen.gmpath;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.gh.service.GmPathCrossPointService;
import com.gh.service.GmPathLineService;

public class GMDaoFactory {
	static String[] name = new String[] { "applicationContext.xml" };
	private static ApplicationContext resources = new ClassPathXmlApplicationContext(name);

	private static GmPathCrossPointService getGmPathCrossPoint() {
		return (GmPathCrossPointService) resources
				.getBean("gmpathcrosspointService");
	}

	private static GmPathLineService getGmPathLine() {
		return (GmPathLineService) resources.getBean("gmpathlineService");
	}

	/* 各Service静态工厂 */
	public static GmPathCrossPointService GmPathCrossPointInstance = getGmPathCrossPoint();
	public static GmPathLineService GmPathLineInstance = getGmPathLine();
}
