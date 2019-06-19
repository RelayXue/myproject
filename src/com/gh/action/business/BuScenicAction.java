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
import com.gh.entity.BuBarcode;
import com.gh.entity.BuScenic;
import com.gh.service.BuScenicService;

/**
 * @author xiaofeng
 * @version 1.0
 * @since 1.0
 */


public class BuScenicAction  extends Action{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private BuScenicService buScenicService;
	private String uid;
	private String id;
	private String names;
	private String ids;
	private String skey;
	private List<BuScenic> buScenic_list;
	private BuScenic buScenic;
	private ServletContext context;
	private File uploadpic;
	private String uploadpicFileName;
	/**
	 * @see 显示列表页
	 * @author xiao
	 */
	public String show(){
		String roleid=(String)request.getSession().getAttribute("roleid");
		com=CompetenceManager.getCom(roleid, "buScenic");
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
		totalPage=buScenicService.getRecordCount(SqlWhere);
		buScenic_list=buScenicService.selectByPage(indexPage,pageSize, SqlWhere, "fullname desc");
		 return "buScenic";
	}
	/**
	 * @see 景区码显示列表页
	 * @author xiao
	 */
	public String BcodeShow(){
		String roleid=(String)request.getSession().getAttribute("roleid");
		com=CompetenceManager.getCom(roleid, "buScenic!BcodeShow");
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
		totalPage=buScenicService.getRecordCount(SqlWhere);
		buScenic_list=buScenicService.selectByPage(indexPage,pageSize, SqlWhere, "fullname desc");
		return "BuBarcode";
	}
	
	/**
	 * @see html5端显示列表页
	 * @author abc0571
	 */
	public String getPhoneScenicList(){
		 this.show();
		 return "phoneScenicList";
	}
	/**
	 * @see html5端显示列表页
	 * @author abc0571
	 */
	public String getPhoneScenicDetail(){
		 this.update_show();
		 return "phoneScenicDetail";
	}
	 
	
	/**
	 * @see 修改及新增
	 * @author xiao
	 * @throws IOException 
	 */
	public String edit() throws IOException{
		String username=(String)request.getSession().getAttribute("username");
		String userid=(String)request.getSession().getAttribute("userid");
		String id=buScenic.getFuid();
		String roleid=(String)request.getSession().getAttribute("roleid");
		// file 上传文件、filename 上传文件名、address 文件保存路径
		String address = Upload.upload(upload, uploadFileName, "/upload",
				context);
		buScenic.setVoice(address);
		if(id!=null&&id.length()>0){
			com=CompetenceManager.getCom(roleid, "buScenic");
			if(!com.getHisUpdate()){
				return "error";
			}
			buScenic.setModifydate(new Date());
			buScenic.setModifyuserrealname(username);
			buScenicService.updateSelective(buScenic);
		}else{
			com=CompetenceManager.getCom(roleid, "buScenic");
			if(!com.getHisUpdate()){
				return "error";
			}
			//uid 图片关联
			if(uid!=null&&uid.length()>0){
				buScenic.setFuid(uid);
			}else{
				buScenic.setFuid(UUIDCreater.getUUID());
			}
			buScenic.setCreatedate(new Date());
			buScenic.setCreateuserrealname(username);
			buScenic.setModifydate(new Date());
			buScenic.setModifyuserrealname(username);
			buScenicService.save(buScenic);
		}
		return "show";
	}
	/**
	 * @see 显示详细页或修改页
	 * @author xiao
	 */
	public String update_show(){
		buScenic=buScenicService.findById(id);
		return "buScenicEditor";
	}
	/**
	 * @see 显示二维码审核页
	 * @author xiao
	 */
	public String examineShow(){
		buScenic=buScenicService.findById(id);
		return "buScenicExamine";
	}
	/**
	 * @see 二维码审核
	 * @author xiao
	 */
	public String examine(){
		buScenicService.updateSelective(buScenic);
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
					ZxingEncode.gocode(id[a], "100001", img1);
					buScenic=new BuScenic();
					buScenic.setFuid(id[a]);
					buScenic.setPrstatus("1");
					buScenic.setCodepath("/Barcode/"+name[a] + ".png");
					buScenicService.updateSelective(buScenic);
				}
			}
		}
		return "showExamine";
	}
	/**
	 * @see 删除单条数据或多条
	 * @author xiao
	 */
	public String delete(){
		String roleid=(String)request.getSession().getAttribute("roleid");
		com=CompetenceManager.getCom(roleid, "buScenic");
		if(!com.getHisDelete()){
			return "error";
		}
		if(id!=null&&id.length()>0){
			String ids[]=id.split(",");
			if(ids!=null&&ids.length>0){
				for(int a=0;a<ids.length;a++){
					buScenicService.delete(ids[a]);
				}
			}else{
				buScenicService.delete(id);
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
	public BuScenicService getBuScenicService() {
		return buScenicService;
	}
	public void setBuScenicService(BuScenicService buScenicService) {
		this.buScenicService = buScenicService;
	}
	public List<BuScenic> getBuScenic_list() {
		return buScenic_list;
	}
	public void setBuScenic_list(List<BuScenic> buScenic_list) {
		this.buScenic_list = buScenic_list;
	}
	public BuScenic getBuScenic() {
		return buScenic;
	}
	public void setBuScenic(BuScenic buScenic) {
		this.buScenic = buScenic;
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
}
