package com.gh.action.business;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletContext;

import org.apache.struts2.ServletActionContext;

import com.gh.action.system.CompetenceManager;
import com.gh.base.Action;
import com.gh.common.FileUpload;
import com.gh.common.ImageCompression;
import com.gh.common.Imgedit;
import com.gh.common.UUIDCreater;
import com.gh.dao.IntegrateddataDAO;
import com.gh.entity.BuStay;
import com.gh.entity.IntegrateddataWithBLOBs;
import com.gh.service.BuStayService;

/** 
 * 住宿BU_STAY
 * @author xiaofeng
 * @version 1.0
 * @since 1.0
 */


public class BuStayAction  extends Action{
	
	
	private BuStayService buStayService;
	private String uid;
	private String id;
	private String skey;
	private List<BuStay> buStay_list;
	private BuStay buStay;
	private ServletContext context;
	private File uploadpic;
	private String uploadpicFileName;
	private String ids;
	private String names;
	private String typeName;//搜索类型
	
	//------图片上传----
	private File upload;
	private String uploadFileName;
	private String imgNum;
	private String reNum;
	
	/**
	 * @see 显示列表页
	 * @author xiao
	 */
	public String show(){
		String roleid=(String)request.getSession().getAttribute("roleid");
		com=CompetenceManager.getCom(roleid, "buStay");
		//--------------------操作权限控制
		if(!com.getHisSelect()){
			return "error";
		}
		String SqlWhere=" DELETEMARK=0";
		//String SqlWhere=" DELETEMARK=0 and praise!='' ";  ///------用来测试图片大小的
		
		if(typeName!=null&&typeName.length()>6){
			//System.out.println("typeName的类型长度是："+typeName.length());
			SqlWhere+=" and type="+typeName;
		}
		if(skey!=null&&skey.length()>0){
			SqlWhere+=" and fullname like '%"+skey+"%'";
		} 
		//--------------------
		pageSize=10;
		indexPage=indexPage==0?1:indexPage;
		totalPage=buStayService.getRecordCount(SqlWhere);
		buStay_list=buStayService.selectByPage(indexPage,pageSize, SqlWhere, "DIMAGES_STATUS DESC, PRAISE DESC");
		return "buStay";
	}
	/**
	 * @see 显示列表页
	 * @author xiao
	 */
	public String showExamine(){
		String roleid=(String)request.getSession().getAttribute("roleid");
		com=CompetenceManager.getCom(roleid, "buStay!showExamine");
		//--------------------操作权限控制
		if(!com.getHisSelect()){
			return "error";
		}
		 String SqlWhere=" DELETEMARK=0";
		if(skey!=null&&skey.length()>0){
		    SqlWhere+=" and fullname like '%"+skey+"%'";
		 } 
		//--------------------
	    pageSize=10;
		indexPage=indexPage==0?1:indexPage;
		totalPage=buStayService.getRecordCount(SqlWhere);
		buStay_list=buStayService.selectByPage(indexPage,pageSize, SqlWhere, "MODIFYDATE desc");
		 return "buStayCode";
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
					ZxingEncode.gocode(id[a], "002017", img1);
					buStay=new BuStay();
					buStay.setFuid(id[a]);
					buStay.setPrstatus("1");
					buStay.setCodepath("/Barcode/"+name[a] + ".png");
					buStayService.updateSelective(buStay);
				}
			}
		}
		return "showExamine";
	}
	/**
	 * @see 生成二维码
	 * @author xiao
	 */
	public String generateAll() {
		String path = request.getSession().getServletContext().getRealPath("/") + "Barcode\\";
		 List<BuStay> buStay_list=buStayService.execSql("select * from bu_Stay where PRSTATUS!='1'");
		if(buStay_list!=null&&buStay_list.size()>0){
			for(int a=0;a<buStay_list.size();a++){
				String img1 = path + "//" + buStay_list.get(a).getFullname() + ".png";
				ZxingEncode.gocode(buStay_list.get(a).getFuid(),buStay_list.get(a).getType(), img1);
				buStay=buStay_list.get(a);
				buStay.setPrstatus("1");
				buStay.setCodepath("/Barcode/"+buStay_list.get(a).getFullname() + ".png");
				buStayService.updateSelective(buStay);
			}
		}
		return "showExamine";
	}
	/**
	 * @see 修改及新增
	 * @author xiao
	 * @throws IOException 
	 */
	public String edit() throws IOException{
		String username=(String)request.getSession().getAttribute("username");
		String userid=(String)request.getSession().getAttribute("userid");
		String id=buStay.getFuid();
		String roleid=(String)request.getSession().getAttribute("roleid");
		if(id!=null&&id.length()>0){
			com=CompetenceManager.getCom(roleid, "buStay");
			if(!com.getHisUpdate()){
				return "error";
			}
			
			//------------图片修改
			BuStay buStay1 = buStayService.findById(id);
			String dimg = buStay1.getDimages();
			//System.out.println(imgNum);
			boolean flag=false;
			String imgRe = Imgedit.modify(reNum,imgNum, dimg, img, imgFileName,flag);
			if(imgRe.length()>0){
				buStay.setDimages(imgRe);
			}else{
				buStay.setDimages("");
			}
			buStay.setModifydate(new Date());
			buStayService.updateSelective(buStay);
			

		}else{
			com=CompetenceManager.getCom(roleid, "buStay");
			if(!com.getHisAdd()){
				return "error";
			}
			////新建时多图上传
			FileUpload fileupload=new FileUpload();
			ArrayList<String> re=fileupload.inFile(img, "/upload", imgFileName);
			if(re!=null&&re.size()>0){
				String imges="";
				for(int a=0;a<re.size();a++){
					imges+=re.get(a)+",";
				}
				imges=imges.length()>0?imges.substring(0,imges.length()-1):"";
				System.out.println("--------"+imges);
				buStay.setDimages(imges);
				buStay.setDimagesStatus(1);
				for(int i=0;i<re.size();i++){
					//压缩图片
					ImageCompression mypic = new ImageCompression();
					String targetDirectory = ServletActionContext.getServletContext().getRealPath("upload");
					mypic.compressPic(targetDirectory + "/", targetDirectory + "/", re.get(i), "B_"+re.get(i), 900, 700, true);
					mypic.compressPic(targetDirectory + "/", targetDirectory + "/", re.get(i), "L_"+re.get(i), 300, 280, true);
				}
			}else{
				buStay.setDimagesStatus(0);
			}
			buStay.setFuid(UUIDCreater.getUUID());
			buStay.setDeletemark(0);
			buStay.setCreatedate(new Date());
			buStay.setModifydate(new Date());
			buStayService.save(buStay);
			
		}
		return "show";
	}
	
	/**
	 * @see 显示详细页或修改页
	 * @author xiao
	 */
	public String update_show(){
		buStay=buStayService.findById(id);
		return "buStayEditor";
	}
	/**
	 * @see 删除(逻辑删除)
	 * @author xiao
	 */
	public String delete(){
		String roleid=(String)request.getSession().getAttribute("roleid");
		com=CompetenceManager.getCom(roleid, "buStay");
		if(!com.getHisDelete()){
			return "error";
		}
	
//		if (uid != null && uid.length() > 0) {
//			String ids[] = uid.split(",");
//			if (ids != null && ids.length > 0) {
//				for (int a = 0; a < ids.length; a++) {
//					buStayService.delete(ids[a]);
//				}
//			}
//		} else {
//			if (id != null && id.length() > 0) {
//				buStayService.delete(id);
//			}
//		}
		BuStay buStay = buStayService.findById(id);
		buStay.setDeletemark(1);
		buStayService.updateSelective(buStay);
		
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
	public BuStayService getBuStayService() {
		return buStayService;
	}
	public void setBuStayService(BuStayService buStayService) {
		this.buStayService = buStayService;
	}
	public List<BuStay> getBuStay_list() {
		return buStay_list;
	}
	public void setBuStay_list(List<BuStay> buStay_list) {
		this.buStay_list = buStay_list;
	}
	public BuStay getBuStay() {
		return buStay;
	}
	public void setBuStay(BuStay buStay) {
		this.buStay = buStay;
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
	public File getUpload() {
		return upload;
	}
	public void setUpload(File upload) {
		this.upload = upload;
	}
	public String getUploadFileName() {
		return uploadFileName;
	}
	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
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
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	
	
	
}
