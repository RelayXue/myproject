package com.gh.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import com.gh.common.StringUtil;
import com.gh.dao.BuAttractionsDAO;
import com.gh.dao.BuDiningDAO;
import com.gh.dao.BuEntertainmentshoppingDAO;
import com.gh.dao.BuGeographyDAO;
import com.gh.dao.BuNewsDAO;
import com.gh.dao.BuStayDAO;
import com.gh.entity.AutoCompleteEntity;
import com.gh.entity.BuAttractions;
import com.gh.entity.BuDining;
import com.gh.entity.BuEntertainmentshopping;
import com.gh.entity.BuGeography;
import com.gh.entity.BuNews;
import com.gh.entity.BuStay;
import com.gh.entity.DataContainer;
import com.gh.entity.DataEntity;
import com.gh.entity.ScenicContainer;
import com.google.gson.Gson;

public class WebSearchServiceImpl implements WebSearchService{

	private BuDiningDAO buDiningDAO;

	private BuEntertainmentshoppingDAO buEntertainmentshoppingDAO;

	private BuStayDAO buStayDAO;

	private BuGeographyDAO buGeographyDAO;
	
	private BuAttractionsDAO buAttractionsDAO;
	
	private BuNewsDAO buNewsDAO;

	@Override
	public List<DataEntity> findByType(String type, int pageIndex,
			int pageSize, String strWhere, String strOrderBy) {
		List<DataEntity> xList = new ArrayList<DataEntity>();
		if(StringUtil.isNotTrimBlank(type)){
			int size =(int)this.getTypeDataCount(type,strWhere);
			int totalsize = (size % pageSize == 0) ? (size / pageSize) : (size / pageSize + 1);
			int max = pageIndex > totalsize ? totalsize * pageSize : pageIndex * pageSize;
			int min = pageIndex == 1 ? 0 : pageIndex  * pageSize;
			if (max <= min) {
				min = min - pageSize;
				pageIndex -= 1;
			}
			min=min>0?min:0;

			String mainSql = "";
			if(type.equals("002018")){
				//美食
				mainSql = "SELECT FUID,FULLNAME,FX,FY,'002018' AS TYPE,PHONE,ADDRESS,DIMAGES,SUPERVISOR,SUPERVISORPHONE,praise,examine,star,consumption,comment FROM bu_dining ";
			}else if(type.equals("002017")){
				//住宿
				mainSql = "SELECT FUID,FULLNAME,FX,FY,'002017' AS TYPE,PHONE,ADDRESS,DIMAGES,SUPERVISOR,SUPERVISORPHONE,praise,examine,star,consumption,comment FROM bu_stay ";
			}else if(type.equals("002015")){
				//购物
				mainSql = "SELECT FUID,FULLNAME,FX,FY,'002015' AS TYPE,PHONE,ADDRESS,DIMAGES,SUPERVISOR,SUPERVISORPHONE,praise,examine,star,consumption,comment FROM bu_entertainmentshopping ";
				//strWhere += "and type like '002015%'";
			}else if(type.equals("002016")){
				//娱乐
				mainSql = "SELECT FUID,FULLNAME,FX,FY,'002016' AS TYPE,PHONE,ADDRESS,DIMAGES,SUPERVISOR,SUPERVISORPHONE,praise,examine,star,consumption,comment FROM bu_entertainmentshopping ";
				//strWhere += "and type like '002016%'";
			}
			if(StringUtil.isNotTrimBlank(mainSql)){
				if (strWhere != null && strWhere.trim().length() > 0) {
					mainSql = mainSql + " WHERE " + strWhere;
				}
				if (strOrderBy != null && strOrderBy.trim().length() > 0) {
					mainSql = mainSql + " ORDER BY " + strOrderBy;
				}
				mainSql+="  LIMIT "+min+","+pageSize;
				xList = buStayDAO.execData(mainSql);
			}
		}
		for(int i=0;i<xList.size();i++){
			DataEntity dataEntity = xList.get(i);
			String name = dataEntity.getFullname();
			name = getNameFormat(name);
			xList.get(i).setFullname(name);
			String addr = xList.get(i).getAddress();
			if(StringUtil.isNotTrimBlank(addr)&&addr.length()>10){
				xList.get(i).setAddress(addr.substring(0,10)+"...");
			}
			String phone = xList.get(i).getPhone();
			if(StringUtil.isNotTrimBlank(phone)&&phone.length()>11){
				xList.get(i).setPhone(phone.substring(0,11)+"...");
			}
			if(StringUtil.isTrimBlank(xList.get(i).getPraise())){
				xList.get(i).setPraise("0");
			}
			if(StringUtil.isTrimBlank(xList.get(i).getExamine())){
				xList.get(i).setExamine("0");
			}
			String imgs = xList.get(i).getDimages();
			if(StringUtil.isNotTrimBlank(imgs)){
				//xList.get(i).setDimages(imgs.split(",")[0]);
				xList.get(i).setImages(Arrays.asList(imgs.split(",")));
			}
			if(StringUtil.isTrimBlank(xList.get(i).getConsumption())){
				xList.get(i).setConsumption("--");
			}
			if(StringUtil.isTrimBlank(xList.get(i).getStar())){
				xList.get(i).setStar("3.0");
			}
			if(StringUtil.isTrimBlank(xList.get(i).getComment())){
				xList.get(i).setComment("0");
			}
		}
		return xList;
	}
	
	private String getNameFormat(String name){
		if(name.length()>2){
			if(name.substring(0,2).equals("乌镇")){
				name = name.substring(2);
			}
		}
		if(name.length()>5){
			if(name.substring(0,5).equals("桐乡市乌镇")){
				name = name.substring(5);
			}
		}
		if(name.length()>10){
			name = name.substring(0,10)+"...";
		}
		return name;
	}
	
	private String getNameFormat2(String name){
		if(name.length()>2){
			if(name.substring(0,2).equals("乌镇")){
				name = name.substring(2);
			}
		}
		if(name.length()>5){
			if(name.substring(0,5).equals("桐乡市乌镇")){
				name = name.substring(5);
			}
		}
		if(name.length()>6){
			name = name.substring(0,6)+"...";
		}
		return name;
	}
	
	private String getAddressFormat(String name){
		if(name.length()>5){
			if(name.substring(0,5).equals("桐乡市乌镇")){
				name = name.substring(5);
			}
		}
		if(name.length()>25){
			name = name.substring(0,25)+"...";
		}
		return name;
	}

	@Override
	public int getTypeDataCount(String type,String strWhere) {

		String mainSql = "";
		if(type.equals("002018")){
			//美食
			mainSql = "SELECT count(*) FROM bu_dining ";
		}else if(type.equals("002017")){
			//住宿
			mainSql = "SELECT count(*) FROM bu_stay ";
		}else if(type.equals("002015")){
			//娱乐
			mainSql = "SELECT count(*) FROM bu_entertainmentshopping ";
			strWhere += "and type like '002015%'";
		}else if(type.equals("002016")){
			//娱乐
			mainSql = "SELECT count(*) FROM bu_entertainmentshopping ";
			strWhere += "and type like '002016%'";
		}else if(type.equals("3")){
			//交通
		}else if(type.equals("4")){
			//诚信商家

		}
		if(StringUtil.isNotTrimBlank(mainSql)){
			if(strWhere!=null && strWhere.trim().length()>0){
				mainSql = mainSql + " WHERE " + strWhere;
			}
			Object obj =  buStayDAO.selectObject(mainSql);
			if(obj!=null){
				return Integer.valueOf( obj.toString());
			}
		}
		return 0; 
	}

	public List<DataEntity> selectDataByPage(int pageIndex, int pageSize,String strWhere, String strOrderBy) {
		String mainSql = "SELECT a.* FROM (SELECT FUID,FULLNAME,FX,FY,'002018' AS TYPE,PHONE,ADDRESS,DIMAGES_STATUS,DIMAGES,SUPERVISOR,SUPERVISORPHONE,praise,examine,star,consumption,comment FROM bu_dining union SELECT FUID,FULLNAME,FX,FY,'002017' AS TYPE,PHONE,ADDRESS,DIMAGES_STATUS,DIMAGES,SUPERVISOR,SUPERVISORPHONE,praise,examine,star,consumption,comment FROM bu_stay union SELECT FUID,FULLNAME,FX,FY,TYPE,PHONE,ADDRESS,DIMAGES_STATUS,DIMAGES,SUPERVISOR,SUPERVISORPHONE,praise,examine,star,consumption,comment FROM bu_entertainmentshopping union SELECT FUID,FULLNAME,FX,FY,'4' AS TYPE,PHONE,ADDRESS,DIMAGES_STATUS,DIMAGES,SUPERVISOR,SUPERVISORPHONE,praise,examine,star,consumption,comment FROM bu_geography) a";
		if (strWhere != null && strWhere.trim().length() > 0) {
			mainSql = mainSql + " WHERE " + strWhere;
		}
		if (strOrderBy != null && strOrderBy.trim().length() > 0) {
			mainSql = mainSql + " ORDER BY " + strOrderBy;
		}
		int size =(int)this.getDataCount(strWhere);
		int totalsize = (size % pageSize == 0) ? (size / pageSize) : (size / pageSize + 1);
		int max = pageIndex > totalsize ? totalsize * pageSize : pageIndex * pageSize;
		int min = pageIndex == 1 ? 0 : pageIndex  * pageSize;
		if (max <= min) {
			min = min - pageSize;
			pageIndex -= 1;
		}
		min=min>0?min:0;
		mainSql+="  LIMIT "+min+","+pageSize; 
		List<DataEntity> xList = buStayDAO.execData(mainSql);
		for(int i=0;i<xList.size();i++){
			DataEntity dataEntity = xList.get(i);
			String name = dataEntity.getFullname();
			name = getNameFormat(name);
			xList.get(i).setFullname(name);
			String addr = xList.get(i).getAddress();
			if(StringUtil.isNotTrimBlank(addr)&&addr.length()>10){
				xList.get(i).setAddress(addr.substring(0,10)+"...");
			}
			String phone = xList.get(i).getPhone();
			if(StringUtil.isNotTrimBlank(phone)&&phone.length()>11){
				xList.get(i).setPhone(phone.substring(0,11)+"...");
			}
			if(StringUtil.isTrimBlank(xList.get(i).getPraise())){
				xList.get(i).setPraise("0");
			}
			if(StringUtil.isTrimBlank(xList.get(i).getExamine())){
				xList.get(i).setExamine("0");
			}
			if(StringUtil.isNotTrimBlank(xList.get(i).getType())){
				if(xList.get(i).getType().length()>6){
					xList.get(i).setType(xList.get(i).getType().substring(0,6));
				}
			}
			String imgs = xList.get(i).getDimages();
			if(StringUtil.isNotTrimBlank(imgs)){
				//xList.get(i).setDimages(imgs.split(",")[0]);
				xList.get(i).setImages(Arrays.asList(imgs.split(",")));
			}
			if(StringUtil.isTrimBlank(xList.get(i).getConsumption())){
				xList.get(i).setConsumption("--");
			}
			if(StringUtil.isTrimBlank(xList.get(i).getStar())){
				xList.get(i).setStar("3.0");
			}
			if(StringUtil.isTrimBlank(xList.get(i).getComment())){
				xList.get(i).setComment("0");
			}
		}
		return xList;
	}

	public int getDataCount(String strWhere) {
		String sql = "SELECT COUNT(*) FROM (SELECT FUID,FULLNAME,FX,FY,'002018' AS TYPE,PHONE,ADDRESS,DIMAGES_STATUS,DIMAGES,SUPERVISOR,SUPERVISORPHONE,praise,examine,star,consumption,comment FROM bu_dining union SELECT FUID,FULLNAME,FX,FY,'002017' AS TYPE,PHONE,ADDRESS,DIMAGES_STATUS,DIMAGES,SUPERVISOR,SUPERVISORPHONE,praise,examine,star,consumption,comment FROM bu_stay union SELECT FUID,FULLNAME,FX,FY,TYPE,PHONE,ADDRESS,DIMAGES_STATUS,DIMAGES,SUPERVISOR,SUPERVISORPHONE,praise,examine,star,consumption,comment FROM bu_entertainmentshopping union SELECT FUID,FULLNAME,FX,FY,'4' AS TYPE,PHONE,ADDRESS,DIMAGES_STATUS,DIMAGES,SUPERVISOR,SUPERVISORPHONE,praise,examine,star,consumption,comment FROM bu_geography) a";
		if(strWhere!=null && strWhere.trim().length()>0){
			sql = sql + " WHERE " + strWhere;
		}
		Object obj =  buStayDAO.selectObject(sql);
		if(obj!=null){
			return Integer.valueOf( obj.toString());
		}
		return 0; 
	}

	@Override
	public DataContainer<BuDining> findBuDiningById(String id) {
		DataContainer<BuDining> dataContainer = new DataContainer<BuDining>();
		if(StringUtil.isNotTrimBlank(id)){
			BuDining buDining = buDiningDAO.selectByPrimaryKey(id);
			if(StringUtil.isNotTrimBlank(buDining.getFullname())){
				buDining.setName(getNameFormat2(buDining.getFullname()));
			}
			if(StringUtil.isNotTrimBlank(buDining.getDimages())){
				String str [] = buDining.getDimages().split(",");
				buDining.setImages(Arrays.asList(str));
			}
			if(buDining.getPraise()==null){
				buDining.setPraise(0);
			}
			//update
			if(buDining.getExamine()!=null){
				buDining.setExamine(buDining.getExamine()+1);
			}else{
				buDining.setExamine(1);
			}
			buDining.setAddress(getAddressFormat(buDining.getAddress()));
			if(StringUtil.isTrimBlank(buDining.getConsumption())){
				buDining.setConsumption("--");
			}
			if(buDining.getStar()==null){
				buDining.setStar(3.0);
			}
			buStayDAO.updateBySql("update bu_dining set Examine = "+buDining.getExamine()+" where fuid='"+id+"'");
			dataContainer.setObj(buDining);
		}
		return dataContainer;
	}
	@Override
	public DataContainer<BuStay> findBuStayById(String id) {
		DataContainer<BuStay> dataContainer = new DataContainer<BuStay>();
		if(StringUtil.isNotTrimBlank(id)){
			BuStay buStay = buStayDAO.selectByPrimaryKey(id);
			if(StringUtil.isNotTrimBlank(buStay.getFullname())){
				buStay.setName(getNameFormat2(buStay.getFullname()));
			}
			if(StringUtil.isNotTrimBlank(buStay.getDimages())){
				String str [] = buStay.getDimages().split(",");
				buStay.setImages(Arrays.asList(str));
			}
			if(buStay.getPraise()==null){
				buStay.setPraise(0);
			}
			//update
			if(buStay.getExamine()!=null){
				buStay.setExamine(buStay.getExamine()+1);
			}else{
				buStay.setExamine(1);
			}
			buStay.setAddress(getAddressFormat(buStay.getAddress()));
			if(StringUtil.isTrimBlank(buStay.getConsumption())){
				buStay.setConsumption("--");
			}
			if(buStay.getStar()==null){
				buStay.setStar(3.0);
			}
			buStayDAO.updateBySql("update bu_stay set Examine = "+buStay.getExamine()+" where fuid='"+id+"'");
			dataContainer.setObj(buStay);
		}
		return dataContainer;
	}
	@Override
	public DataContainer<BuEntertainmentshopping> findBuEntertainmentshoppingById(
			String id) {
		DataContainer<BuEntertainmentshopping> dataContainer = new DataContainer<BuEntertainmentshopping>();
		if(StringUtil.isNotTrimBlank(id)){
			BuEntertainmentshopping b = buEntertainmentshoppingDAO.selectByPrimaryKey(id);
			if(b==null){
				return dataContainer;
			}
			if(StringUtil.isNotTrimBlank(b.getFullname())){
				b.setName(getNameFormat2(b.getFullname()));
			}
			if(StringUtil.isNotTrimBlank(b.getDimages())){
				String str [] = b.getDimages().split(",");
				b.setImages(Arrays.asList(str));
			}
			if(b.getPraise()==null){
				b.setPraise(0);
			}
			//update
			if(b.getExamine()!=null){
				b.setExamine(b.getExamine()+1);
			}else{
				b.setExamine(1);
			}
			b.setAddress(getAddressFormat(b.getAddress()));
			if(StringUtil.isTrimBlank(b.getConsumption())){
				b.setConsumption("--");
			}
			if(b.getStar()==null){
				b.setStar(3.0);
			}
			buStayDAO.updateBySql("update bu_entertainmentshopping set Examine = "+b.getExamine()+" where fuid='"+id+"'");
			dataContainer.setObj(b);
		}
		return dataContainer;
	}
	@Override
	public DataContainer<BuGeography> findBuGeographyById(String id) {
		DataContainer<BuGeography> dataContainer = new DataContainer<BuGeography>();
		if(StringUtil.isNotTrimBlank(id)){
			BuGeography b = buGeographyDAO.selectByPrimaryKey(id);
			if(StringUtil.isNotTrimBlank(b.getFullname())){
				b.setName(getNameFormat2(b.getFullname()));
			}
			if(StringUtil.isNotTrimBlank(b.getDimages())){
				String str [] = b.getDimages().split(",");
				b.setImages(Arrays.asList(str));
			}
			b.setAddress(getAddressFormat(b.getAddress()));
			if(b.getConsumption()==null){
				b.setConsumption(0.0);
			}
			if(b.getStar()==null){
				b.setStar(3.0);
			}
			dataContainer.setObj(b);
		}
		return dataContainer;
	}
	
	@Override
	public String findAutoCompleteData(String q, int len) {
		String sql = "select * from (SELECT FULLNAME,FX,FY from bu_geography union SELECT FULLNAME,FX,FY from bu_dining union SELECT FULLNAME,FX,FY from bu_entertainmentshopping union SELECT FULLNAME,FX,FY from bu_stay) a  where a.fx is not null and a.fx!='' and a.fullname like '%"+q+"%' limit 0,"+len;
		List<AutoCompleteEntity> list = buGeographyDAO.execAutoData(sql);
		Gson gson = new Gson();
		StringBuffer sb = new StringBuffer();
		if(!(list==null||list.isEmpty())){
			for(int i=0;i<list.size();i++){
				sb.append(gson.toJson(list.get(i))+"\n");
			}
		}
		return sb.toString();
	}
	
	public String findAutoCompleteData(String q) {
		String sql = "select * from (SELECT FULLNAME,FX,FY from bu_geography union SELECT FULLNAME,FX,FY from bu_dining union SELECT FULLNAME,FX,FY from bu_entertainmentshopping union SELECT FULLNAME,FX,FY from bu_stay) a  where a.fx is not null and a.fx!='' and a.fullname ='"+q+"'";
		List<AutoCompleteEntity> list = buGeographyDAO.execAutoData(sql);
		if(!(list==null||list.isEmpty())){
			return list.get(0).getX()+","+list.get(0).getY();
		}
		return "";
	}
	
	public List<BuAttractions> findAttractionsList(int pageIndex, int pageSize,String strWhere, String strOrderBy){
		String mainSql = "SELECT * FROM bu_attractions";
		if (strWhere != null && strWhere.trim().length() > 0) {
			mainSql = mainSql + " WHERE " + strWhere;
		}
		if (strOrderBy != null && strOrderBy.trim().length() > 0) {
			mainSql = mainSql + " ORDER BY " + strOrderBy;
		}
		int size =(int)this.getBuattractionsCount(strWhere);
		int totalsize = (size % pageSize == 0) ? (size / pageSize) : (size / pageSize + 1);
		int max = pageIndex > totalsize ? totalsize * pageSize : pageIndex * pageSize;
		int min = pageIndex == 1 ? 0 : pageIndex  * pageSize;
		if (max <= min) {
			min = min - pageSize;
			pageIndex -= 1;
		}
		min=min>0?min:0;
		mainSql+="  LIMIT "+min+","+pageSize;
		List<BuAttractions> xList = buAttractionsDAO.execSql(mainSql);
		if(!(xList==null||xList.isEmpty())){
			for(int i=0;i<xList.size();i++){
				String imgs = xList.get(i).getDimages();
				if(StringUtil.isNotTrimBlank(imgs)){
					xList.get(i).setDimages(imgs.split(",")[0]);
				}
				String introduction = xList.get(i).getIntroduction();
				if(StringUtil.isNotTrimBlank(introduction)&&introduction.trim().length()>12){
					xList.get(i).setIntroduction(introduction.trim().substring(0,12)+"...");
				}
				String name = xList.get(i).getFullname();
				if(StringUtil.isNotTrimBlank(name)&&name.trim().length()>10){
					xList.get(i).setFullname(name.trim().substring(0,10)+"...");
				}
			}
		}
		return xList;
	}
	
	@Override
	public ScenicContainer findBuattractions(int pageIndex, int pageSize,String strWhere, String strOrderBy) {
		String mainSql = "SELECT * FROM bu_attractions";
		if (strWhere != null && strWhere.trim().length() > 0) {
			mainSql = mainSql + " WHERE " + strWhere;
		}
		if (strOrderBy != null && strOrderBy.trim().length() > 0) {
			mainSql = mainSql + " ORDER BY " + strOrderBy;
		}
		int size =(int)this.getBuattractionsCount(strWhere);
		int totalsize = (size % pageSize == 0) ? (size / pageSize) : (size / pageSize + 1);
		int max = pageIndex > totalsize ? totalsize * pageSize : pageIndex * pageSize;
		int min = pageIndex == 1 ? 0 : pageIndex  * pageSize;
		if (max <= min) {
			min = min - pageSize;
			pageIndex -= 1;
		}
		min=min>0?min:0;
		mainSql+="  LIMIT "+min+","+pageSize;
		List<BuAttractions> xList = buAttractionsDAO.execSql(mainSql);
		ScenicContainer scenicContainer = new ScenicContainer();
		if(!(xList==null||xList.isEmpty())){
			for(int i=0;i<xList.size();i++){
				String imgs = xList.get(i).getDimages();
				if(StringUtil.isNotTrimBlank(imgs)){
					xList.get(i).setDimages(imgs.split(",")[0]);
				}
				String introduction = xList.get(i).getIntroduction();
				if(StringUtil.isNotTrimBlank(introduction)&&introduction.trim().length()>12){
					xList.get(i).setIntroduction(introduction.trim().substring(0,12)+"...");
				}
				String name = xList.get(i).getFullname();
				if(StringUtil.isNotTrimBlank(name)&&name.trim().length()>10){
					xList.get(i).setFullname(name.trim().substring(0,10)+"...");
				}
				
				if(i==0||i==4||i==8){
					scenicContainer.getList1().add(xList.get(i));
				}else if(i==1||i==5||i==9){
					scenicContainer.getList2().add(xList.get(i));
				}else if(i==2||i==6||i==10){
					scenicContainer.getList3().add(xList.get(i));
				}else if(i==3||i==7||i==11){
					scenicContainer.getList4().add(xList.get(i));
				}
			}
		}
		return scenicContainer;
	}
	
	public BuAttractions findAttractionsById(String id){
		return buAttractionsDAO.selectByPrimaryKey(id);
	}
	
	@Override
	public int getBuattractionsCount(String strWhere) {
	     String sql = "SELECT COUNT(*) FROM bu_attractions";
	     if(strWhere!=null && strWhere.trim().length()>0){
	         sql = sql + " WHERE " + strWhere;
	     }
	     Object obj =  buAttractionsDAO.selectObject(sql);
	     if(obj!=null){
	         return Integer.valueOf( buAttractionsDAO.selectObject(sql).toString());
	     }
	     return 0; 
	}
	
	@Override
	public DataContainer<List<BuNews>> findNews(int pageIndex, int pageSize, String strWhere,
			String strOrderBy) {
		String mainSql = "SELECT * FROM bu_news";
		if (strWhere != null && strWhere.trim().length() > 0) {
			mainSql = mainSql + " WHERE " + strWhere;
		}
		if (strOrderBy != null && strOrderBy.trim().length() > 0) {
			mainSql = mainSql + " ORDER BY " + strOrderBy;
		}
		int size =(int)this.findNewsCount(strWhere);
		int totalsize = (size % pageSize == 0) ? (size / pageSize) : (size / pageSize + 1);
		int max = pageIndex > totalsize ? totalsize * pageSize : pageIndex * pageSize;
		int min = pageIndex == 1 ? 0 : pageIndex  * pageSize;
		if (max <= min) {
			min = min - pageSize;
			pageIndex -= 1;
		}
		min=min>0?min:0;
		mainSql+="  LIMIT "+min+","+pageSize;
		List<BuNews> xList = buNewsDAO.execSql(mainSql);
		if(!(xList==null||xList.isEmpty())){
			for(int i=0;i<xList.size();i++){
				if(StringUtil.isNotTrimBlank(xList.get(i).getImg1())){
					String str [] = xList.get(i).getImg1().split(",");
					xList.get(i).setImages(Arrays.asList(str));
				}
//				int num = 1;
//				//更新查看数
//				if(xList.get(i).getReadnum()!=null){
//				   num = num+xList.get(i).getReadnum();	
//				}
//				xList.get(i).setReadnum(num);
//				buNewsDAO.updateByPrimaryKey(xList.get(i));
			}
		}
		DataContainer<List<BuNews>> dataContainer = new DataContainer<List<BuNews>>();
		dataContainer.setObj(xList);
		return dataContainer;
	}

	@Override
	public int findNewsCount(String strWhere) {
		String sql = "SELECT COUNT(*) as a FROM bu_news";
	     if(strWhere!=null && strWhere.trim().length()>0){
	         sql = sql + " WHERE " + strWhere;
	     }
	     Object obj =  buNewsDAO.selectObject(sql);
	     if(obj!=null){
	         return Integer.valueOf( buNewsDAO.selectObject(sql).toString());
	     }
	     return 0; 
	}
	
	@Override
	public int updateBySql(String sql) {
		return (Integer) buStayDAO.updateBySql(sql);
	}


	public BuDiningDAO getBuDiningDAO() {
		return buDiningDAO;
	}
	public void setBuDiningDAO(BuDiningDAO buDiningDAO) {
		this.buDiningDAO = buDiningDAO;
	}
	public BuEntertainmentshoppingDAO getBuEntertainmentshoppingDAO() {
		return buEntertainmentshoppingDAO;
	}
	public void setBuEntertainmentshoppingDAO(
			BuEntertainmentshoppingDAO buEntertainmentshoppingDAO) {
		this.buEntertainmentshoppingDAO = buEntertainmentshoppingDAO;
	}
	public BuStayDAO getBuStayDAO() {
		return buStayDAO;
	}
	public void setBuStayDAO(BuStayDAO buStayDAO) {
		this.buStayDAO = buStayDAO;
	}
	public BuGeographyDAO getBuGeographyDAO() {
		return buGeographyDAO;
	}
	public void setBuGeographyDAO(BuGeographyDAO buGeographyDAO) {
		this.buGeographyDAO = buGeographyDAO;
	}

	public void setBuAttractionsDAO(BuAttractionsDAO buAttractionsDAO) {
		this.buAttractionsDAO = buAttractionsDAO;
	}

	public void setBuNewsDAO(BuNewsDAO buNewsDAO) {
		this.buNewsDAO = buNewsDAO;
	}
}
