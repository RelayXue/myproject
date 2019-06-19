package com.gh.action.business;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletContext;

import com.gh.action.system.CompetenceManager;
import com.gh.base.Action;
import com.gh.common.ImageCompression;
import com.gh.common.UUIDCreater;
import com.gh.common.Upload;
import com.gh.entity.BuCommodity;
import com.gh.entity.BuScenic;
import com.gh.service.BuCommodityService;

/**
 * @author xiaofeng
 * @version 1.0
 * @since 1.0
 */


public class BuCommodityAction  extends Action{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private BuCommodityService buCommodityService;
	private String uid;
	private String ids;
	private String names;
	private String id;
	private String skey;
	private List<BuCommodity> buCommodity_list;
	private BuCommodity buCommodity;
	private ServletContext context;
	private File uploadpic;
	private String uploadpicFileName;
	
	/**
	 * @see 显示列表页
	 * @author xiao
	 */
	public String show(){
		String roleid=(String)request.getSession().getAttribute("roleid");
		com=CompetenceManager.getCom(roleid, "buCommodity");
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
		totalPage=buCommodityService.getRecordCount(SqlWhere);
		buCommodity_list=buCommodityService.selectByPage(indexPage,pageSize, SqlWhere, "MODIFYDATE desc");
		 return "buCommodity";
	}
	/**
	 * @see 景区码显示列表页
	 * @author xiao
	 */
	public String BcodeShow(){
		String roleid=(String)request.getSession().getAttribute("roleid");
		com=CompetenceManager.getCom(roleid, "buCommodity!BcodeShow");
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
		totalPage=buCommodityService.getRecordCount(SqlWhere);
		buCommodity_list=buCommodityService.selectByPage(indexPage,pageSize, SqlWhere, "MODIFYDATE desc");
		return "BuBarcode";
	}
	/**
	 * @see 修改及新增
	 * @author xiao
	 * @throws IOException 
	 */
	public String edit() throws IOException{
		String username=(String)request.getSession().getAttribute("username");
		String userid=(String)request.getSession().getAttribute("userid");
		String id=buCommodity.getFuid();
		String roleid=(String)request.getSession().getAttribute("roleid");
		if(id!=null&&id.length()>0){
			com=CompetenceManager.getCom(roleid, "buCommodity");
			if(!com.getHisUpdate()){
				return "error";
			}
			buCommodity.setModifydate(new Date());
			buCommodity.setModifyuserrealname(username);
			buCommodityService.updateSelective(buCommodity);
		}else{
			com=CompetenceManager.getCom(roleid, "buCommodity");
			if(!com.getHisUpdate()){
				return "error";
			}
			//uid 图片关联
			if(uid!=null&&uid.length()>0){
				buCommodity.setFuid(uid);
			}else{
				buCommodity.setFuid(UUIDCreater.getUUID());
			}
			buCommodity.setCreatedate(new Date());
			buCommodity.setCreateuserrealname(username);
			buCommodity.setModifydate(new Date());
			buCommodity.setModifyuserrealname(username);
			buCommodityService.save(buCommodity);
		}
		return "show";
	}
	/**
	 * @see 显示二维码审核页
	 * @author xiao
	 */
	public String examineShow(){
		buCommodity=buCommodityService.findById(id);
		return "buCommodityExamine";
	}
	/**
	 * @see 二维码审核
	 * @author xiao
	 */
	public String examine(){
		buCommodityService.updateSelective(buCommodity);
		return null;
	}
	/**
	 * @see 生成二维码
	 * @author xiao
	 */
	public String generate() {
		String path = request.getSession().getServletContext().getRealPath("/") + "Barcode\\";
		if(ids!=null){
			String id[]=ids.split(",");
			String name[]=names.split(",");
			if(id!=null&&id.length>0){
				for(int a=0;a<id.length;a++){
					String img1 = path + "//" + name[a] + ".png";
					ZxingEncode.gocode(id[a], "100004", img1);
					buCommodity=new BuCommodity();
					buCommodity.setFuid(id[a]);
					buCommodity.setPrstatus("1");
					buCommodity.setCodepath("/Barcode/"+name[a] + ".png");
					buCommodityService.updateSelective(buCommodity);
				}
			}
		}
		return "showExamine";
	}
	/**
	 * @see 显示详细页或修改页
	 * @author xiao
	 */
	public String update_show(){
		buCommodity=buCommodityService.findById(id);
		return "buCommodityEditor";
	}
	/**
	 * @see 删除单条数据或多条
	 * @author xiao
	 */
	public String delete(){
		String roleid=(String)request.getSession().getAttribute("roleid");
		com=CompetenceManager.getCom(roleid, "buCommodity");
		if(!com.getHisDelete()){
			return "error";
		}
		if(id!=null&&id.length()>0){
			String ids[]=id.split(",");
			if(ids!=null&&ids.length>0){
				for(int a=0;a<ids.length;a++){
					buCommodityService.delete(ids[a]);
				}
			}else{
				buCommodityService.delete(id);
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
	public BuCommodityService getBuCommodityService() {
		return buCommodityService;
	}
	public void setBuCommodityService(BuCommodityService buCommodityService) {
		this.buCommodityService = buCommodityService;
	}
	public List<BuCommodity> getBuCommodity_list() {
		return buCommodity_list;
	}
	public void setBuCommodity_list(List<BuCommodity> buCommodity_list) {
		this.buCommodity_list = buCommodity_list;
	}
	public BuCommodity getBuCommodity() {
		return buCommodity;
	}
	public void setBuCommodity(BuCommodity buCommodity) {
		this.buCommodity = buCommodity;
	}
	public String getIds() {
		return ids;
	}
	public void setIds(String ids) {
		this.ids = ids;
	}
	public String getNames() {
		return names;
	}
	public void setNames(String names) {
		this.names = names;
	}
	public ServletContext getContext() {
		return context;
	}
	public void setContext(ServletContext context) {
		this.context = context;
	}
	public File getUploadpic() {
		return uploadpic;
	}
	public void setUploadpic(File uploadpic) {
		this.uploadpic = uploadpic;
	}
	public String getUploadpicFileName() {
		return uploadpicFileName;
	}
	public void setUploadpicFileName(String uploadpicFileName) {
		this.uploadpicFileName = uploadpicFileName;
	}
}
