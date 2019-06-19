package com.gh.action.weixin.backstage;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.struts2.ServletActionContext;

import com.gh.action.system.CompetenceManager;
import com.gh.base.Action;
import com.gh.common.FileUpload;
import com.gh.common.ImageCompression;
import com.gh.common.Imgedit;
import com.gh.common.UUIDCreater;
import com.gh.entity.BuWeixinconfig;
import com.gh.entity.BuWeixinmass;
import com.gh.service.BuWeixinconfigService;
import com.gh.service.BuWeixinmassService;

/**
 * @author xiaofeng
 * @version 1.0
 * @since 1.0
 */


public class BuWeixinmassAction  extends Action{
	
	
	private BuWeixinmassService buWeixinmassService;
	private BuWeixinconfigService buWeixinconfigService;
	private String uid;
	private String weixinname;
	private String id;
	private String skey;
	private List<BuWeixinmass> buWeixinmass_list;
	private BuWeixinmass buWeixinmass;
	//----图片上传----
	private String imgNum;
	private String reNum;
	
	/**
	 * @see 显示列表页
	 * @author xiao
	 */
	public String show(){
		String roleid=(String)request.getSession().getAttribute("roleid");
		com=CompetenceManager.getCom(roleid, "buWeixinmass");
		//--------------------操作权限控制
		if(!com.getHisSelect()){
			return "error";
		}
		 String SqlWhere="";
		if(skey!=null&&skey.length()>0){
		    SqlWhere+="  fullname like '%"+skey+"%'";
		 } 
		//--------------------
	    pageSize=10;
		indexPage=indexPage==0?1:indexPage;
		totalPage=buWeixinmassService.getRecordCount(SqlWhere);
		buWeixinmass_list=buWeixinmassService.selectByPage(indexPage,pageSize, SqlWhere, "  state ,orderby ");
		List<BuWeixinconfig> list=buWeixinconfigService.execSql("select * from bu_Weixinconfig where type='005002'");
		if(list!=null&&list.size()>0){
			weixinname=list.get(0).getContent();
		}
		 return "buWeixinmass";
	}
	/**
	 * @see 修改及新增
	 * @author xiao
	 * @throws IOException 
	 */
	public String edit() throws IOException{
		String roleid=(String)request.getSession().getAttribute("roleid");
		com=CompetenceManager.getCom(roleid, "buWeixinmass");
		id=buWeixinmass.getFuid();
		if (id != null && id.length() > 0) {
			if (!com.getHisUpdate()) {
				return "error";
			}
			//------------图片修改
			BuWeixinmass  buWeixinmass1 = buWeixinmassService.findById(id);
			String dimg = buWeixinmass1.getPicurl();
			boolean flag=false;
			String imgRe = Imgedit.modify(reNum,imgNum, dimg, img, imgFileName,flag);
			//----------------
			buWeixinmass.setPicurl(imgRe);
			String content=buWeixinmass.getContent();
			content=content!=null?content.replace("\"", "'"):content;
			buWeixinmass.setContent(content);
			buWeixinmassService.updateSelective(buWeixinmass);
		} else {
			if (!com.getHisAdd()) {
				return "error";
			}
			//新建时多图上传
			FileUpload fileupload=new FileUpload();
			ArrayList<String> re=fileupload.inFile(img, "/upload", imgFileName);
			if(re!=null&&re.size()>0){
				String imges="";
				for(int a=0;a<re.size();a++){
					imges+=re.get(a)+",";
				}
				imges=imges.length()>0?imges.substring(0,imges.length()-1):"";
				buWeixinmass.setPicurl(imges);
				for(int i=0;i<re.size();i++){
					//压缩图片
					ImageCompression mypic = new ImageCompression();
					String targetDirectory = ServletActionContext.getServletContext().getRealPath("upload");
					mypic.compressPic(targetDirectory + "/", targetDirectory + "/", re.get(i), "B_"+re.get(i), 700, 500, true);
					mypic.compressPic(targetDirectory + "/", targetDirectory + "/", re.get(i), "L_"+re.get(i), 300, 200, true);
					}
				
			}
			buWeixinmass.setFuid(UUIDCreater.getUUID());
			buWeixinmass.setState("0");
			String content=buWeixinmass.getContent();
			content=content!=null?content.replace("\"", "'"):content;
			buWeixinmass.setContent(content);
			buWeixinmass.setCreatedate(new Date());
			buWeixinmassService.save(buWeixinmass);
		}
		
		return "show";
	}
	/**
	 * @see 微信群发
	 * @author xiao
	 * @throws Exception 
	 */
	public String send() throws Exception{
		String fuid[]=id.split(",");
		buWeixinmass_list=new ArrayList<BuWeixinmass>();
		if(fuid!=null&&fuid.length>0){
			for(int a=0;a<fuid.length;a++){
				buWeixinmass=buWeixinmassService.findById(fuid[a]);
				buWeixinmass_list.add(buWeixinmass); 
			}
			if(buWeixinmass_list!=null&&buWeixinmass_list.size()>0){
				WeixinAction weixin=new WeixinAction();
				String re=weixin.sendAllMember(buWeixinmass_list);
				if(re!=null&&re.equals("0")){
					for(int a=0;a<buWeixinmass_list.size();a++){
						BuWeixinmass  buWeixinmass =buWeixinmass_list.get(a);
						buWeixinmass.setState("1");
						buWeixinmassService.updateSelective(buWeixinmass);
					}
				}
			}
		}
		return null;
	}
	/**
	 * @see 微信与发送
	 * @author xiao
	 * @throws Exception 
	 */
	public String ysend() throws Exception{
		response.setCharacterEncoding("utf-8");
		PrintWriter out=response.getWriter();
		String fuid[]=id.split(",");
		buWeixinmass_list=new ArrayList<BuWeixinmass>();
		if(weixinname==null||weixinname.length()==0){
			out.print("未设置预发送接收人，发送失败");
			return null;
		}
		if(fuid!=null&&fuid.length>0){
			for(int a=0;a<fuid.length;a++){
				buWeixinmass=buWeixinmassService.findById(fuid[a]);
				buWeixinmass_list.add(buWeixinmass);
			}
			if(buWeixinmass_list!=null&&buWeixinmass_list.size()>0){
				WeixinAction weixin=new WeixinAction();
				String re=weixin.ysendMember(buWeixinmass_list,weixinname);
				if(re!=null&&re.equals("0")){
					for(int a=0;a<buWeixinmass_list.size();a++){
						BuWeixinmass  buWeixinmass =buWeixinmass_list.get(a);
						buWeixinmass.setState("0");
						buWeixinmassService.updateSelective(buWeixinmass);
					}
				}
			}
		}
		out.print("预发送成功！请耐心等待！");
		return null;
	}
	/**
	 * @see 显示详细页或修改页
	 * @author xiao
	 */
	public String update_show(){
		buWeixinmass=buWeixinmassService.findById(id);
		return "buWeixinmassEditor";
	}
	/**
	 * @see 删除单条数据或多条
	 * @author xiao
	 */
	public String delete(){
		String roleid=(String)request.getSession().getAttribute("roleid");
		com=CompetenceManager.getCom(roleid, "buWeixinmass");
		if(!com.getHisDelete()){
			return "error";
		}
		if(id!=null&&id.length()>0){
			String ids[]=id.split(",");
			if(ids!=null&&ids.length>0){
				for(int a=0;a<ids.length;a++){
					buWeixinmassService.delete(ids[a]);
				}
			}else{
				buWeixinmassService.delete(id);
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
	public BuWeixinmassService getBuWeixinmassService() {
		return buWeixinmassService;
	}
	public void setBuWeixinmassService(BuWeixinmassService buWeixinmassService) {
		this.buWeixinmassService = buWeixinmassService;
	}
	public List<BuWeixinmass> getBuWeixinmass_list() {
		return buWeixinmass_list;
	}
	public void setBuWeixinmass_list(List<BuWeixinmass> buWeixinmass_list) {
		this.buWeixinmass_list = buWeixinmass_list;
	}
	public BuWeixinmass getBuWeixinmass() {
		return buWeixinmass;
	}
	public void setBuWeixinmass(BuWeixinmass buWeixinmass) {
		this.buWeixinmass = buWeixinmass;
	}
	public String getImgNum() {
		return imgNum;
	}
	public void setImgNum(String imgNum) {
		this.imgNum = imgNum;
	}
	public String getReNum() {
		return reNum;
	}
	public void setReNum(String reNum) {
		this.reNum = reNum;
	}
	public BuWeixinconfigService getBuWeixinconfigService() {
		return buWeixinconfigService;
	}
	public void setBuWeixinconfigService(BuWeixinconfigService buWeixinconfigService) {
		this.buWeixinconfigService = buWeixinconfigService;
	}
	public String getWeixinname() {
		return weixinname;
	}
	public void setWeixinname(String weixinname) {
		this.weixinname = weixinname;
	}
}
