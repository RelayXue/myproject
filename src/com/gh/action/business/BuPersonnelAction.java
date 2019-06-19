package com.gh.action.business;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.gh.action.system.CompetenceManager;
import com.gh.base.Action;
import com.gh.common.TransformJSON;
import com.gh.common.UUIDCreater;
import com.gh.entity.BuPersonnel;
import com.gh.service.BuPersonnelService;
import com.gh.system.TimeUtils;
import com.gh.system.lucene.GPSRouteFactory;
import com.gh.system.lucene.GPSRouteModel;

/**
 * @author xiaofeng
 * @version 1.0
 * @since 1.0
 */


public class BuPersonnelAction  extends Action{
	
	
	private BuPersonnelService buPersonnelService;
	private String uid;
	private String id;
	private String skey;
	private List<BuPersonnel> buPersonnel_list;
	private BuPersonnel buPersonnel;
	//------------------人员位置轨迹---------------------------
	private String SecurityData;
	private String trajectoryData;//人员轨迹
	private String phone;//人员手机号码
	private String startTime;//开始时间
	private String endTime;//结束时间
	private String time;
	
	/**
	 * @see 显示列表页
	 * @author xiao
	 */
	public String show(){
		String roleid=(String)request.getSession().getAttribute("roleid");
		com=CompetenceManager.getCom(roleid, "buPersonnel");
		//--------------------操作权限控制
		if(!com.getHisSelect()){
			return "error";
		}
		 String SqlWhere="";
		if(skey!=null&&skey.length()>0){
		    SqlWhere+="  realname like '%"+skey+"%'";
		 } 
		//--------------------
	    pageSize=10;
		indexPage=indexPage==0?1:indexPage;
		totalPage=buPersonnelService.getRecordCount(SqlWhere);
		buPersonnel_list=buPersonnelService.selectByPage(indexPage,pageSize, SqlWhere, "MODIFYDATE desc");
		 return "buPersonnel";
	}
	/**
	 * @see 修改及新增
	 * @author xiao
	 */
	public String edit(){
		String username=(String)request.getSession().getAttribute("username");
		String userid=(String)request.getSession().getAttribute("userid");
		String id=buPersonnel.getFuid();
		String roleid=(String)request.getSession().getAttribute("roleid");
		if(id!=null&&id.length()>0){
			com=CompetenceManager.getCom(roleid, "buPersonnel");
			if(!com.getHisUpdate()){
				return "error";
			}
			buPersonnel.setModifydate(new Date());
			buPersonnelService.updateSelective(buPersonnel);
		}else{
			com=CompetenceManager.getCom(roleid, "buPersonnel");
			if(!com.getHisUpdate()){
				return "error";
			}
			buPersonnel.setFuid(UUIDCreater.getUUID());
			buPersonnel.setDeletemark("0");
			buPersonnel.setCreatedate(new Date());
			buPersonnel.setModifydate(new Date());
			buPersonnelService.save(buPersonnel);
		}
		return "show";
	}
	/**
	 * @see 显示详细页或修改页
	 * @author xiao
	 */
	public String update_show(){
		buPersonnel=buPersonnelService.findById(id);
		return "buPersonnelEditor";
	}
	/**
	 * @see 删除单条数据或多条
	 * @author xiao
	 */
	public String delete(){
		String roleid=(String)request.getSession().getAttribute("roleid");
		com=CompetenceManager.getCom(roleid, "buPersonnel");
		if(!com.getHisDelete()){
			return "error";
		}
		if(id!=null&&id.length()>0){
			String ids[]=id.split(",");
			if(ids!=null&&ids.length>0){
				for(int a=0;a<ids.length;a++){
					buPersonnelService.delete(ids[a]);
				}
			}else{
				buPersonnelService.delete(id);
			}
		}
		return "show";
	}
	
	//------------------人员位置轨迹---------------------
	/**
	 * 人员选择
	 * @return
	 */
	public String selectPerson(){
		String roleid=(String)request.getSession().getAttribute("roleid");
		com=CompetenceManager.getCom(roleid, "buPersonnel");
		//--------------------操作权限控制
		if(!com.getHisSelect()){
			return "error";
		}
		 String SqlWhere="";
		if(skey!=null&&skey.length()>0){
		    SqlWhere+="  realname like '%"+skey+"%'";
		 } 
		//--------------------
	    pageSize=10;
		indexPage=indexPage==0?1:indexPage;
		totalPage=buPersonnelService.getRecordCount(SqlWhere);
		buPersonnel_list=buPersonnelService.selectByPage(indexPage,pageSize, SqlWhere, " modifydate desc ");
		 return "selectperson";
	}
	
	/**
	 * 显示人员地图
	 * @return
	 * @throws IOException 
	 */
	public String showmap() throws IOException{
		//System.out.println("************"+time);
		//buPersonnel_list=buPersonnelService.execSql("select * from bu_personnel where modifydate>="+"'"+time+"'");
		buPersonnel_list=buPersonnelService.execSql("select * from bu_personnel");
		SecurityData=TransformJSON.toJsonArr(buPersonnel_list);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("utf-8");//响应页面编码
		PrintWriter out = response.getWriter();
		//System.out.println(SecurityData);
		out.print(SecurityData);
		return null;
	}
	
	/**
	 * 人员轨迹数据
	 * @return
	 * @throws ParseException
	 * @throws Exception
	 */
	public String personTrajectory()throws ParseException, Exception{
		List<GPSRouteModel> users = GPSRouteFactory.getSingle().SelectListByUserWithCycle(phone, TimeUtils.SIMPLE_DATE_FORMAT_A.parse(startTime), TimeUtils.SIMPLE_DATE_FORMAT_A.parse(endTime));
		trajectoryData = TransformJSON.toJsonArr(users);
		HttpServletResponse response = ServletActionContext.getResponse();
		PrintWriter out = response.getWriter();
		
		//System.out.println(trajectoryData);
		out.print(trajectoryData);
		return null;
	}
	

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public String getSkey() {
		return skey;
	}
	public void setSkey(String skey) {
		this.skey = skey;
	}
	public BuPersonnelService getBuPersonnelService() {
		return buPersonnelService;
	}
	public void setBuPersonnelService(BuPersonnelService buPersonnelService) {
		this.buPersonnelService = buPersonnelService;
	}
	public List<BuPersonnel> getBuPersonnel_list() {
		return buPersonnel_list;
	}
	public void setBuPersonnel_list(List<BuPersonnel> buPersonnel_list) {
		this.buPersonnel_list = buPersonnel_list;
	}
	public BuPersonnel getBuPersonnel() {
		return buPersonnel;
	}
	public void setBuPersonnel(BuPersonnel buPersonnel) {
		this.buPersonnel = buPersonnel;
	}
	public String getTrajectoryData() {
		return trajectoryData;
	}
	public void setTrajectoryData(String trajectoryData) {
		this.trajectoryData = trajectoryData;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getSecurityData() {
		return SecurityData;
	}
	public void setSecurityData(String securityData) {
		SecurityData = securityData;
	}
	
	
}
