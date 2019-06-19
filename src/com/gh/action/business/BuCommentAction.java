package com.gh.action.business;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.gh.action.system.CompetenceManager;
import com.gh.base.Action;
import com.gh.common.StringUtil;
import com.gh.common.SystemTypeConstant;
import com.gh.common.UUIDCreater;
import com.gh.entity.BuComment;
import com.gh.entity.BuDining;
import com.gh.entity.BuEntertainmentshopping;
import com.gh.entity.BuGeography;
import com.gh.entity.BuStay;
import com.gh.entity.DataContainer;
import com.gh.service.BuCommentService;
import com.gh.service.WebSearchService;

/**
 * @author xiaofeng
 * @version 1.0
 * @since 1.0
 */


public class BuCommentAction  extends Action{


	private BuCommentService buCommentService;
	private String uid;
	private String id;
	private String examine;
	private String type;
	private String skey;
	private List<BuComment> buComment_list;
	private BuComment buComment;
	private WebSearchService webSearchService;

	/**
	 * @see 显示列表页
	 * @author xiao
	 */
	public String show(){
		String roleid=(String)request.getSession().getAttribute("roleid");
		com=CompetenceManager.getCom(roleid, "buComment?type="+type);
		//--------------------操作权限控制
		if(!com.getHisSelect()){
			return "error";
		}
		String SqlWhere=" systype like '"+type+"%'";
		if(skey!=null&&skey.length()>0){
			SqlWhere+=" and description like '%"+skey+"%'";
		} 
		//--------------------
		pageSize=10;
		indexPage=indexPage==0?1:indexPage;
		totalPage=buCommentService.getRecordCount(SqlWhere);
		buComment_list=buCommentService.selectByPage(indexPage,pageSize, SqlWhere, "MODIFYDATE desc");
		return "buComment";
	}
	/**
	 * @see 审核
	 * @author xiao
	 */
	public String examine(){
		buComment=new BuComment();
		buComment.setFuid(id);
		buComment.setExamine(examine);
		if(StringUtil.isNotTrimBlank(examine)&&StringUtil.isNotTrimBlank(id)){
			BuComment bc = buCommentService.findById(id);
			if(examine.equals("1")){
				//通过
				double a_v = 0;double star = 0; int cn = 0;
				if(StringUtil.isNotTrimBlank(type)){
					String sql="bu_stay";
					if(type.equals(SystemTypeConstant.DATA_TYPE_STAY)){
						sql = "bu_stay";
						DataContainer<BuStay> d = webSearchService.findBuStayById(bc.getObjectid());
						if(d.getObj()!=null&&StringUtil.isNotTrimBlank(d.getObj().getConsumption())&&!d.getObj().getConsumption().equals("--")){
							//人均消费
							a_v = Double.parseDouble(d.getObj().getConsumption());
						}
						if(d.getObj()!=null&&d.getObj().getStar()!=null){
							//星
							star = d.getObj().getStar();
						}
						if(d.getObj()!=null&&d.getObj().getComment()!=null){
							//评论数
							cn = d.getObj().getComment();
						}
					}else if(type.equals(SystemTypeConstant.DATA_TYPE_SHOPPING)||type.equals(SystemTypeConstant.DATA_TYPE_FUNNY)){
						sql = "bu_entertainmentshopping";
						DataContainer<BuEntertainmentshopping> d = webSearchService.findBuEntertainmentshoppingById(bc.getObjectid());
						if(d.getObj()!=null&&StringUtil.isNotTrimBlank(d.getObj().getConsumption())&&!d.getObj().getConsumption().equals("--")){
							//人均消费
							a_v = Double.parseDouble(d.getObj().getConsumption());
						}
						if(d.getObj()!=null&&d.getObj().getStar()!=null){
							//星
							star = d.getObj().getStar();
						}
						if(d.getObj()!=null&&d.getObj().getComment()!=null){
							//评论数
							cn = d.getObj().getComment();
						}
					}else if(type.equals(SystemTypeConstant.DATA_TYPE_GEOGRAPHY)){
						sql = "bu_geography";
						DataContainer<BuGeography> d = webSearchService.findBuGeographyById(bc.getObjectid());
						if(d.getObj()!=null&&d.getObj().getConsumption()!=null){
							//人均消费
							a_v = d.getObj().getConsumption();
						}
						if(d.getObj()!=null&&d.getObj().getStar()!=null){
							//星
							star = d.getObj().getStar();
						}
						if(d.getObj()!=null&&d.getObj().getComment()!=null){
							//评论数
							cn = d.getObj().getComment();
						}
					}else if(type.equals(SystemTypeConstant.DATA_TYPE_DINING)){
						sql = "bu_dining";
						DataContainer<BuDining> d = webSearchService.findBuDiningById(bc.getObjectid());
						if(d.getObj()!=null&&StringUtil.isNotTrimBlank(d.getObj().getConsumption())&&!d.getObj().getConsumption().equals("--")){
							//人均消费
							a_v = Double.parseDouble(d.getObj().getConsumption());
						}
						if(d.getObj()!=null&&d.getObj().getStar()!=null){
							//星
							star = d.getObj().getStar();
						}
						if(d.getObj()!=null&&d.getObj().getComment()!=null){
							//评论数
							cn = d.getObj().getComment();
						}
					}
					//人均
					a_v = (a_v*cn+bc.getCapita())/(cn+1)*1.0;
					//星
					star = (star*cn+bc.getScore())/(cn+1)*1.0;
					
					BigDecimal a_v1 = new BigDecimal(a_v).setScale(0, BigDecimal.ROUND_HALF_UP);
					BigDecimal star1 = new BigDecimal(star).setScale(1, BigDecimal.ROUND_HALF_UP);
					webSearchService.updateBySql("update "+sql+" set comment = "+(cn+1)+",star="+star1.toString()+",consumption="+a_v1.toString()+" where fuid='"+bc.getObjectid()+"'");
				}
			}
		}
		buCommentService.updateSelective(buComment);
		return "show";
	}
	/**
	 * @see 显示详细页或修改页
	 * @author xiao
	 */
	public String update_show(){
		buComment=buCommentService.findById(id);
		return "buCommentEditor";
	}
	/**
	 * @see 删除单条数据或多条
	 * @author xiao
	 */
	public String delete(){
		String roleid=(String)request.getSession().getAttribute("roleid");
		com=CompetenceManager.getCom(roleid, "buComment");
		if(!com.getHisDelete()){
			return "error";
		}
		if(id!=null&&id.length()>0){
			String ids[]=id.split(",");
			if(ids!=null&&ids.length>0){
				for(int a=0;a<ids.length;a++){
					buCommentService.delete(ids[a]);
				}
			}else{
				buCommentService.delete(id);
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
	public BuCommentService getBuCommentService() {
		return buCommentService;
	}
	public void setBuCommentService(BuCommentService buCommentService) {
		this.buCommentService = buCommentService;
	}
	public List<BuComment> getBuComment_list() {
		return buComment_list;
	}
	public void setBuComment_list(List<BuComment> buComment_list) {
		this.buComment_list = buComment_list;
	}
	public BuComment getBuComment() {
		return buComment;
	}
	public void setBuComment(BuComment buComment) {
		this.buComment = buComment;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getExamine() {
		return examine;
	}
	public void setExamine(String examine) {
		this.examine = examine;
	}
	public WebSearchService getWebSearchService() {
		return webSearchService;
	}
	public void setWebSearchService(WebSearchService webSearchService) {
		this.webSearchService = webSearchService;
	}
}
