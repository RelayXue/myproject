package com.gh.action.business;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.gh.action.system.CompetenceManager;
import com.gh.base.Action;
import com.gh.common.UUIDCreater;
import com.gh.entity.BaseDatadictionary;
import com.gh.entity.BuBarcode;
import com.gh.entity.BuScenic;
import com.gh.service.BaseDatadictionaryService;
import com.gh.service.BuBarcodeService;

/**
 * @author xiaofeng
 * @version 1.0
 * @since 1.0
 */


public class BuBarcodeAction  extends Action{
	
	
	private BuBarcodeService buBarcodeService;
	private String ids;
	private String names;
	private String id;
	private String skey;
	private List<BuBarcode> buBarcode_list;
	private BuBarcode buBarcode;
	private BaseDatadictionaryService baseDatadictionaryService;
	private String type;
	private String key;
	public String show(){
		String roleid=(String)request.getSession().getAttribute("roleid");
		com=CompetenceManager.getCom(roleid, "buBarcode?type="+type);
		if(!com.getHisSelect()){
			return "error";
		}
		 String SqlWhere=" type like '"+type+"%'";
		if(skey!=null&&skey.length()>0){
		    SqlWhere+=" and code like '%"+skey+"%'";
		 } 
	    pageSize=10;
		indexPage=indexPage==0?1:indexPage;
		totalPage=buBarcodeService.getRecordCount(SqlWhere);
		buBarcode_list=buBarcodeService.selectByPage(indexPage,pageSize, SqlWhere, "MODIFYDATE desc");
		 return "BuBarcode";
	}
	/**
	 * @see 显示二维码审核页
	 * @author xiao
	 */
	public String examineShow(){
		buBarcode=buBarcodeService.findById(id);
		return "buBarcodeExamine";
	}
	/**
	 * @see 二维码审核
	 * @author xiao
	 */
	public String examine(){
		buBarcodeService.updateSelective(buBarcode);
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
					ZxingEncode.gocode(id[a], type, img1);
					buBarcode=new BuBarcode();
					buBarcode.setFuid(id[a]);
					buBarcode.setPrstatus("1");
					buBarcode.setCodepath("/Barcode/"+name[a] + ".png");
					buBarcodeService.updateSelective(buBarcode);
				}
			}
		}
		return "show";
	}
	public String edit(){
		String username=(String)request.getSession().getAttribute("username");
		String userid=(String)request.getSession().getAttribute("userid");
		String id=buBarcode.getFuid();
		String roleid=(String)request.getSession().getAttribute("roleid");
		if(id!=null&&id.length()>0){
			com=CompetenceManager.getCom(roleid, "buBarcode?type="+type);
			if(!com.getHisSelect()){
				return "error";
			}
			buBarcode.setModifydate(new Date());
			buBarcode.setModifyuserrealname(username);
			buBarcodeService.updateSelective(buBarcode);
		}else{
			com=CompetenceManager.getCom(roleid, "buBarcode?type="+type);
			if(!com.getHisSelect()){
				return "error";
			}
			buBarcode.setFuid(UUIDCreater.getUUID());
			buBarcode.setCreatedate(new Date());
			buBarcode.setCreateuserrealname(username);
			buBarcode.setModifydate(new Date());
			buBarcode.setModifyuserrealname(username);
			buBarcodeService.save(buBarcode);
		}
		return "show";
	}
	public String update_show(){
		buBarcode=buBarcodeService.findById(id);
		return "buBarcodeEditor";
	}
	public String delete(){
		String roleid=(String)request.getSession().getAttribute("roleid");
		com=CompetenceManager.getCom(roleid, "buBarcode?type="+type);
		if(!com.getHisSelect()){
			return "error";
		}
		buBarcodeService.delete(id);
		return this.show();
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
	public BuBarcodeService getBuBarcodeService() {
		return buBarcodeService;
	}
	public void setBuBarcodeService(BuBarcodeService buBarcodeService) {
		this.buBarcodeService = buBarcodeService;
	}
	public List<BuBarcode> getBuBarcode_list() {
		return buBarcode_list;
	}
	public void setBuBarcode_list(List<BuBarcode> buBarcode_list) {
		this.buBarcode_list = buBarcode_list;
	}
	public BuBarcode getBuBarcode() {
		return buBarcode;
	}
	public void setBuBarcode(BuBarcode buBarcode) {
		this.buBarcode = buBarcode;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public BaseDatadictionaryService getBaseDatadictionaryService() {
		return baseDatadictionaryService;
	}
	public void setBaseDatadictionaryService(
			BaseDatadictionaryService baseDatadictionaryService) {
		this.baseDatadictionaryService = baseDatadictionaryService;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
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
