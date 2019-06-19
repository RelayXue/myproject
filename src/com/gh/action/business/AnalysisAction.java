package com.gh.action.business;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.gh.action.system.CompetenceManager;
import com.gh.base.Action;
import com.gh.service.BuAffairsService;
import com.gh.service.BuNewsService;

/** 
 * 住宿BU_STAY
 * @author xiaofeng
 * @version 1.0
 * @since 1.0
 */


public class AnalysisAction  extends Action{
	
	private BuNewsService buNewsService;
	private BuAffairsService buAffairsService;
	/**
	 * useRoundEdges 是否显示光滑边缘 formatNumberScale
	 * 是否格式化数字,默认为1(True),自动的给你的数字加上K（千）或M（百万）；若取0,则不加K或M baseFontSize 图表字体大小
	 * decimalPrecision 指定小数位的位数，[0-10] 例如：='0' 取整 showValues
	 * 是否在图表显示对应的数据值，默认为1(True) showBorder 是否显示图表边框 bgColor 背景颜色 渐变
	 */
	final String publicParameter = "useRoundEdges='1' formatNumberScale='1' baseFontSize='13' decimalPrecision='1' showValues='0' showBorder='0' bgColor='FFFFFF,FFFFFF'";
	
	/**
	 * 显示事务统计
	 * @return
	 */
	public String showAffairsAnalysis(){ 
		String roleid=(String)request.getSession().getAttribute("roleid");
		com=CompetenceManager.getCom(roleid, "Analysis!showAffairsAnalysis");
		if(!com.getHisSelect()){
			return "error";
		}
		return "AffairsAnalysis";
	}
	
	/**
	 * 显示新闻统计
	 * @return
	 */
	public String showNewsAnalysis(){ 
		String roleid=(String)request.getSession().getAttribute("roleid");
		com=CompetenceManager.getCom(roleid, "Analysis!showAffairsAnalysis");
		if(!com.getHisSelect()){
			return "error";
		}
		return "NewsAnalysis";
	}
	
	/**
	 * @author xiao
	 * @see  事务趋势分析
	 * @return
	 * @throws IOException
	 */
	public String AffairsAnalysis() throws IOException {
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		DateFormat df = new java.text.SimpleDateFormat("yyyy");
		String where = " and year(CREATEDATE)="+df.format(new Date());
		String xml = "";
		double January = buAffairsService.getRecordCount(" MONTH(CREATEDATE)=1 " + where);
		double February = buAffairsService.getRecordCount(" MONTH(CREATEDATE)=2 " + where);
		double March = buAffairsService.getRecordCount(" MONTH(CREATEDATE)=3 " + where);
		double April = buAffairsService.getRecordCount(" MONTH(CREATEDATE)=4 " + where);
		double May = buAffairsService.getRecordCount(" MONTH(CREATEDATE)=5 " + where);
		double June = buAffairsService.getRecordCount(" MONTH(CREATEDATE)=6 " + where);
		double July = buAffairsService.getRecordCount(" MONTH(CREATEDATE)=7 " + where);
		double August = buAffairsService.getRecordCount(" MONTH(CREATEDATE)=8 " + where);
		double September = buAffairsService.getRecordCount(" MONTH(CREATEDATE)=9 " + where);
		double October = buAffairsService.getRecordCount(" MONTH(CREATEDATE)=10 " + where);
		double Eleven = buAffairsService.getRecordCount(" MONTH(CREATEDATE)=11 " + where);
		double Twelve = buAffairsService.getRecordCount(" MONTH(CREATEDATE)=12 " + where);
		
		xml += "<chart "+publicParameter+"  palette='1' yAxisValuesPadding='10'>";
		xml += " <set label='一月' value='" + January + "' /> ";
		xml += " <set label='二月' value='" + February + "' /> ";
		xml += " <set label='三月' value='" + March + "' /> ";
		xml += " <set label='四月' value='" + April + "' /> ";
		xml += " <set label='五月' value='" + May + "' /> ";
		xml += " <set label='六月' value='" + June + "' /> ";
		xml += " <set label='七月' value='" + July + "' /> ";
		xml += " <set label='八月' value='" + August + "' /> ";
		xml += " <set label='九月' value='" + September + "' /> ";
		xml += " <set label='十月' value='" + October + "' /> ";
		xml += " <set label='十一月' value='" + Eleven + "' /> ";
		xml += " <set label='十二月' value='" + Twelve + "' /> ";
		xml += "<trendLines>";
		xml += "<line startValue='700000' color='009933' displayvalue='Target' />";
		xml += "</trendLines>";
		xml += "</chart>";
		out.print(xml);
		return null;
		
	}
	/**
	 * @author xiao
	 * @see  新闻统计
	 * @return
	 * @throws IOException
	 */
	public String newsAnalysis() throws IOException {
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		DateFormat df = new java.text.SimpleDateFormat("yyyy");
		String where = " and year(CREATEDATE)="+df.format(new Date());
		String xml = "";
		double January = buNewsService.getRecordCount(" MONTH(CREATEDATE)=1 " + where);
		double February = buNewsService.getRecordCount(" MONTH(CREATEDATE)=2 " + where);
		double March = buNewsService.getRecordCount(" MONTH(CREATEDATE)=3 " + where);
		double April = buNewsService.getRecordCount(" MONTH(CREATEDATE)=4 " + where);
		double May = buNewsService.getRecordCount(" MONTH(CREATEDATE)=5 " + where);
		double June = buNewsService.getRecordCount(" MONTH(CREATEDATE)=6 " + where);
		double July = buNewsService.getRecordCount(" MONTH(CREATEDATE)=7 " + where);
		double August = buNewsService.getRecordCount(" MONTH(CREATEDATE)=8 " + where);
		double September = buNewsService.getRecordCount(" MONTH(CREATEDATE)=9 " + where);
		double October = buNewsService.getRecordCount(" MONTH(CREATEDATE)=10 " + where);
		double Eleven = buNewsService.getRecordCount(" MONTH(CREATEDATE)=11 " + where);
		double Twelve = buNewsService.getRecordCount(" MONTH(CREATEDATE)=12 " + where);

		xml += "<chart "+publicParameter+"  palette='1' yAxisValuesPadding='10'>";
		xml += " <set label='一月' value='" + January + "' /> ";
		xml += " <set label='二月' value='" + February + "' /> ";
		xml += " <set label='三月' value='" + March + "' /> ";
		xml += " <set label='四月' value='" + April + "' /> ";
		xml += " <set label='五月' value='" + May + "' /> ";
		xml += " <set label='六月' value='" + June + "' /> ";
		xml += " <set label='七月' value='" + July + "' /> ";
		xml += " <set label='八月' value='" + August + "' /> ";
		xml += " <set label='九月' value='" + September + "' /> ";
		xml += " <set label='十月' value='" + October + "' /> ";
		xml += " <set label='十一月' value='" + Eleven + "' /> ";
		xml += " <set label='十二月' value='" + Twelve + "' /> ";
		xml += "<trendLines>";
		xml += "<line startValue='700000' color='009933' displayvalue='Target' />";
		xml += "</trendLines>";
		xml += "</chart>";
		out.print(xml);
		return null;
		
	}

	public BuAffairsService getBuAffairsService() {
		return buAffairsService;
	}

	public void setBuAffairsService(BuAffairsService buAffairsService) {
		this.buAffairsService = buAffairsService;
	}

	public String getPublicParameter() {
		return publicParameter;
	}

	public BuNewsService getBuNewsService() {
		return buNewsService;
	}

	public void setBuNewsService(BuNewsService buNewsService) {
		this.buNewsService = buNewsService;
	}
	
	
}
