package com.gh.action.phone;

import java.util.List;

import com.gh.base.Action;
import com.gh.common.HtmlUtil;
import com.gh.dao.IntegrateddataDAO;
import com.gh.entity.Integrateddata;

public class ziye extends Action {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String type;	
	private IntegrateddataDAO integrateddataDAO;
	private List<Integrateddata> integrateddata_list;
	
	
	public String show(){
		String where=" and type like '"+type+"%'";
//		if(type!=null&&type.equals("002016")){
//			where=" and (type like '002016%' or type like '002015%')";
//		}
		integrateddata_list=integrateddataDAO.execSql("select * from integrateddata where iszy=1 "+where+" order by sort  desc ");
		if(integrateddata_list!=null&&integrateddata_list.size()>0){
			for(int a=0;a<integrateddata_list.size();a++){
				String image=integrateddata_list.get(a).getDimages();
				String intt=integrateddata_list.get(a).getIntroduction();
				image=image!=null&&image.length()>0?image:"default.jpg";
				integrateddata_list.get(a).setDimages(image);
				integrateddata_list.get(a).setIntroduction(HtmlUtil.getTextFromHtml(intt));
			}
		}
		return "ziye";
	}
	
	public String qwe(){
		return "234";
	}


	public String getType() {
		return type;
	}


	public void setType(String type) {
		this.type = type;
	}


	public IntegrateddataDAO getIntegrateddataDAO() {
		return integrateddataDAO;
	}


	public void setIntegrateddataDAO(IntegrateddataDAO integrateddataDAO) {
		this.integrateddataDAO = integrateddataDAO;
	}


	public List<Integrateddata> getIntegrateddata_list() {
		return integrateddata_list;
	}


	public void setIntegrateddata_list(List<Integrateddata> integrateddata_list) {
		this.integrateddata_list = integrateddata_list;
	}
	

}
