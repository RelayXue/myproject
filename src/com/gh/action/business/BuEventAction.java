package com.gh.action.business;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;

import org.apache.struts2.ServletActionContext;

import com.gh.action.system.CompetenceManager;
import com.gh.base.Action;
import com.gh.common.DateUtil;
import com.gh.common.UUIDCreater;
import com.gh.entity.BuEvent;
import com.gh.service.BuEventService;

/**
 * @author xiaofeng
 * @version 1.0
 * @since 1.0
 */


public class BuEventAction  extends Action{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private BuEventService buEventService;
	private String time1;
	private String time2;
	private String id;
	private String skey;
	private List<BuEvent> buEvent_list;
	private BuEvent buEvent;
	/**
	 * useRoundEdges 是否显示光滑边缘 formatNumberScale
	 * 是否格式化数字,默认为1(True),自动的给你的数字加上K（千）或M（百万）；若取0,则不加K或M baseFontSize 图表字体大小
	 * decimalPrecision 指定小数位的位数，[0-10] 例如：='0' 取整 showValues
	 * 是否在图表显示对应的数据值，默认为1(True) showBorder 是否显示图表边框 bgColor 背景颜色 渐变
	 */
	final String publicParameter = "useRoundEdges='1' formatNumberScale='1' baseFontSize='13' decimalPrecision='1' showValues='0' showBorder='0' bgColor='FFFFFF,FFFFFF'";

	/**
	 * @see 显示列表页
	 * @author xiao
	 */
	public String show(){
		String roleid=(String)request.getSession().getAttribute("roleid");
		com=CompetenceManager.getCom(roleid, "buEvent");
		//--------------------操作权限控制
		if(!com.getHisSelect()){
			return "error";
		}
		//--------------------
		String SqlWhere="";
		if(skey!=null&&skey.length()>0){
			SqlWhere+="  title like '%"+skey+"%'";
		} 
		pageSize=10;
		indexPage=indexPage==0?1:indexPage;
		totalPage=buEventService.getRecordCount(SqlWhere);
		buEvent_list=buEventService.selectByPage(indexPage,pageSize, SqlWhere, "MODIFYDATE desc");
		return "buEvent";
	}
	/**
	 * @see 事件统计
	 * @author xiao
	 */
	public String showCount(){
		String roleid=(String)request.getSession().getAttribute("roleid");
		com=CompetenceManager.getCom(roleid, "buEvent!showCount");
		//--------------------操作权限控制
		if(!com.getHisSelect()){
			return "error";
		}
		//--------------------
		  
		buEvent_list=buEventService.execSql("select * from bu_event group by type");
		if(buEvent_list!=null&&buEvent_list.size()>0){
			for(int a=0;a<buEvent_list.size();a++){
				String type=buEvent_list.get(a).getType();
				int count=buEventService.getRecordCount(" type='"+type+"'");
				int complete=buEventService.getRecordCount(" type='"+type+"' and STATUS=1");
				buEvent_list.get(a).setDnumber(count+"");
				buEvent_list.get(a).setDescription(complete+"");
				buEvent_list.get(a).setAddress((count-complete)+"");
				
			}
		}
		 return "buEventCount";
	}
	/**
	 * @see 统计分析图表
	 * @author xiao
	 */
	public String showAnalysis(){
		return "CountAnalysis";
	}
	/**
	 * @see 统计分析图表
	 * @author xiao
	 */
	public String CountAnalysis() throws IOException {
		response.setContentType("text/json;charset=utf-8");
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
		response.setContentType("charset=gbk");
		PrintWriter out = ServletActionContext.getResponse().getWriter();
		String where="";
		if (time1 != null && time1.length() > 0 && time2 != null && time2.length() > 0) {
			Date dt = DateUtil.parseDate(time2,"yyyy-MM-dd") ;
			String time3 = time2;
			if(dt!=null){
				  dt = DateUtil.addDate(dt,1); 
				  time3 = DateUtil.format(dt,"yyyy-MM-dd");
			} 
			where = " and CREATEDATE>='" + time1 + "' and CREATEDATE<='" + time3 + "'";
		}
		int b1=buEventService.getRecordCount("type ='003001'"+ where);
		int b2=buEventService.getRecordCount("type ='003002'"+ where);
		int b3=buEventService.getRecordCount("type ='003003'"+ where);
		int b4=buEventService.getRecordCount("type ='003004'"+ where);
		int b5=buEventService.getRecordCount("type ='003005'"+ where);
		int b6=buEventService.getRecordCount("type ='003006'"+ where);
		String xml = "";
		xml += "<chart " + publicParameter + " useRoundEdges='1' exportEnabled='1' exportShowMenuItem='0' >";
		xml += "<set label='保洁' value='" + b1 + "' />";
		xml += "<set label='分类垃圾' value='" + b2 + "' />";
		xml += "<set label='绿化' value='" + b3 + "' />";
		xml += "<set label='日常事务' value='" + b4 + "' />";
		xml += "<set label='停车管理' value='" + b5 + "' />";
		xml += "<set label='商铺事务' value='" + b6 + "' />";
		xml += "</chart>";
		out.print(xml);
		return null;
	}
	/**
	 * @see 统计趋势分析图表
	 * @author xiao
	 */
	public String showYearAnalysis(){
		return "YearAnalysis";
	}
	/**
	 * @see 统计分析图表趋势分析
	 * @author xiao
	 */
	public String YearAnalysis() throws IOException {
		response.setContentType("text/json;charset=utf-8");
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
		response.setContentType("charset=gbk");
		PrintWriter out = ServletActionContext.getResponse().getWriter();
		String where = "";
		String xml = "";
		if (time1 != null && time1.length() > 0) {
			where = " and YEAR(CREATEDATE)=" + time1;
		} else {
			where = " and YEAR(CREATEDATE)=2014";
		}
		double January = buEventService.getRecordCount(" MONTH(CREATEDATE)=1 " + where);
		double February = buEventService.getRecordCount(" MONTH(CREATEDATE)=2 " + where);
		double March = buEventService.getRecordCount(" MONTH(CREATEDATE)=3 " + where);
		double April = buEventService.getRecordCount(" MONTH(CREATEDATE)=4 " + where);
		double May = buEventService.getRecordCount(" MONTH(CREATEDATE)=5 " + where);
		double June = buEventService.getRecordCount(" MONTH(CREATEDATE)=6 " + where);
		double July = buEventService.getRecordCount(" MONTH(CREATEDATE)=7 " + where);
		double August = buEventService.getRecordCount(" MONTH(CREATEDATE)=8 " + where);
		double September = buEventService.getRecordCount(" MONTH(CREATEDATE)=9 " + where);
		double October = buEventService.getRecordCount(" MONTH(CREATEDATE)=10 " + where);
		double Eleven = buEventService.getRecordCount(" MONTH(CREATEDATE)=11 " + where);
		double Twelve = buEventService.getRecordCount(" MONTH(CREATEDATE)=12 " + where);

		double January_n = buEventService.getRecordCount(" MONTH(CREATEDATE)=1  and Status='1'" + where);
		double February_n = buEventService.getRecordCount(" MONTH(CREATEDATE)=2  and Status='1'" + where);
		double March_n = buEventService.getRecordCount(" MONTH(CREATEDATE)=3  and Status='1'" + where);
		double April_n = buEventService.getRecordCount(" MONTH(CREATEDATE)=4  and Status='1'" + where);
		double May_n = buEventService.getRecordCount(" MONTH(CREATEDATE)=5  and Status='1'" + where);
		double June_n = buEventService.getRecordCount(" MONTH(CREATEDATE)=6  and Status='1'" + where);
		double July_n = buEventService.getRecordCount(" MONTH(CREATEDATE)=7  and Status='1'" + where);
		double August_n = buEventService.getRecordCount(" MONTH(CREATEDATE)=8  and Status='1'" + where);
		double September_n = buEventService.getRecordCount(" MONTH(CREATEDATE)=9  and Status='1'" + where);
		double October_n = buEventService.getRecordCount(" MONTH(CREATEDATE)=10  and Status='1'" + where);
		double Eleven_n = buEventService.getRecordCount(" MONTH(CREATEDATE)=11  and Status='1'" + where);
		double Twelve_n = buEventService.getRecordCount(" MONTH(CREATEDATE)=12  and Status='1'" + where);
		xml += "<chart " + publicParameter + " palette='1' yAxisValuesPadding='10'>";
		xml += "<categories>";
		xml += "<category label='一月' />";
		xml += "<category label='二月' />";
		xml += "<category label='三月' />";
		xml += "<category label='四月' />";
		xml += "<category label='五月' />";
		xml += "<category label='六月' />";
		xml += "<category label='七月' />";
		xml += "<category label='八月' />";
		xml += "<category label='九月' />";
		xml += "<category label='十月' />";
		xml += "<category label='十一月' />";
		xml += "<category label='十二月' />";
		xml += "</categories>";
		xml += "<dataset seriesName='应解决数'>";
		xml += " <set value='" + January + "' /> ";
		xml += " <set value='" + February + "' /> ";
		xml += " <set value='" + March + "' /> ";
		xml += " <set value='" + April + "' /> ";
		xml += " <set value='" + May + "' /> ";
		xml += " <set value='" + June + "' /> ";
		xml += " <set value='" + July + "' /> ";
		xml += " <set value='" + August + "' /> ";
		xml += " <set value='" + September + "' /> ";
		xml += " <set value='" + October + "' /> ";
		xml += " <set value='" + Eleven + "' /> ";
		xml += " <set value='" + Twelve + "' /> ";
		xml += "</dataset>";
		xml += "<dataset seriesname='解决数'>";
		xml += " <set value='" + January_n + "' /> ";
		xml += " <set value='" + February_n + "' /> ";
		xml += " <set value='" + March_n + "' /> ";
		xml += " <set value='" + April_n + "' /> ";
		xml += " <set value='" + May_n + "' /> ";
		xml += " <set value='" + June_n + "' /> ";
		xml += " <set value='" + July_n + "' /> ";
		xml += " <set value='" + August_n + "' /> ";
		xml += " <set value='" + September_n + "' /> ";
		xml += " <set value='" + October_n + "' /> ";
		xml += " <set value='" + Eleven_n + "' /> ";
		xml += " <set value='" + Twelve_n + "' /> ";
		xml += "</dataset>";
		xml += "</chart>";
		out.print(xml);
		return null;
	}
	/**
	 * @see 修改及新增
	 * @author xiao
	 */
	public String edit(){
		String username=(String)request.getSession().getAttribute("username");
		String userid=(String)request.getSession().getAttribute("userid");
		String id=buEvent.getFuid();
		String roleid=(String)request.getSession().getAttribute("roleid");
		if(id!=null&&id.length()>0){
			com=CompetenceManager.getCom(roleid, "buEvent");
			if(!com.getHisUpdate()){
				return "error";
			}
			buEvent.setModifydate(new Date());
			buEvent.setModifyuserrealname(username);
			buEventService.updateSelective(buEvent);
		}else{
			com=CompetenceManager.getCom(roleid, "buEvent");
			if(!com.getHisAdd()){
				return "error";
			}
			buEvent.setFuid(UUIDCreater.getUUID());
			buEvent.setCreatedate(new Date());
			buEvent.setCreateuserrealname(username);
			buEvent.setModifydate(new Date());
			buEvent.setModifyuserrealname(username);
			buEventService.save(buEvent);
		}
		return "show";
	}
	/**
	 * @see 显示详细页或修改页
	 * @author xiao
	 */
	public String update_show(){
		buEvent=buEventService.findById(id);
		return "buEventEditor";
	}
	/**
	 * @see 删除单条数据或多条
	 * @author xiao
	 */
	public String delete(){
		String roleid=(String)request.getSession().getAttribute("roleid");
		com=CompetenceManager.getCom(roleid, "buEvent");
		if(!com.getHisDelete()){
			return "error";
		}
		if(id!=null&&id.length()>0){
			String ids[]=id.split(",");
			if(ids!=null&&ids.length>0){
				for(int a=0;a<ids.length;a++){
					buEventService.delete(ids[a]);
				}
			}else{
				buEventService.delete(id);
			}
		}
		return "show";
	}

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getSkey() {
		return skey;
	}
	public void setSkey(String skey) {
		this.skey = skey;
	}
	public BuEventService getBuEventService() {
		return buEventService;
	}
	public void setBuEventService(BuEventService buEventService) {
		this.buEventService = buEventService;
	}
	public List<BuEvent> getBuEvent_list() {
		return buEvent_list;
	}
	public void setBuEvent_list(List<BuEvent> buEvent_list) {
		this.buEvent_list = buEvent_list;
	}
	public BuEvent getBuEvent() {
		return buEvent;
	}
	public void setBuEvent(BuEvent buEvent) {
		this.buEvent = buEvent;
	}
	public String getTime1() {
		return time1;
	}
	public void setTime1(String time1) {
		this.time1 = time1;
	}
	public String getTime2() {
		return time2;
	}
	public void setTime2(String time2) {
		this.time2 = time2;
	}
}
