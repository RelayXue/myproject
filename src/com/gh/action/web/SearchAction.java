package com.gh.action.web;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLDecoder;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.apache.lucene.queryparser.xml.builders.LikeThisQueryBuilder;
import org.apache.lucene.util.MathUtil;

import sun.awt.image.ImageWatched.Link;

import com.gh.action.system.CompetenceManager;
import com.gh.base.Action;
import com.gh.common.StringUtil;
import com.gh.common.SystemTypeConstant;
import com.gh.common.UUIDCreater;
import com.gh.entity.BaseDatadictionary;
import com.gh.entity.BuAttractions;
import com.gh.entity.BuComment;
import com.gh.entity.BuDining;
import com.gh.entity.BuEntertainmentshopping;
import com.gh.entity.BuGeography;
import com.gh.entity.BuLink;
import com.gh.entity.BuNews;
import com.gh.entity.BuRoad;
import com.gh.entity.BuStay;
import com.gh.entity.BuWeather;
import com.gh.entity.DataContainer;
import com.gh.entity.DataEntity;
import com.gh.entity.Load;
import com.gh.entity.ScenicContainer;
import com.gh.service.BaseDatadictionaryService;
import com.gh.service.BuCommentService;
import com.gh.service.BuLinkService;
import com.gh.service.BuNewsService;
import com.gh.service.BuRoadService;
import com.gh.service.BuStayService;
import com.gh.service.BuWeatherService;
import com.gh.service.WebSearchService;
import com.wuzhen.gmpath.GMOptimalPath;
import com.wuzhen.gmpath.GMPoint;
import com.wuzhen.gmpath.GMSearchFactory;

/**
 * @ClassName SearchAction 
 * @Description  TODO
 * @author ieastar
 * @date 2014-11-11
 * @version V1.0
 */
public class SearchAction extends Action{

	private static final long serialVersionUID = -7201462478831835676L;
	
	private String id;
	private String json;
	private int totalPage;
	private int page = 1;
	private int currentPage;
	private String _pn;
	private String skey;
	private String type;
	private BuStayService buStayService;
	private List<DataEntity> list = new ArrayList<DataEntity>();
	private DataContainer<?> dataContainer;
	private WebSearchService webSearchService;
	private BuWeatherService buWeatherService;
	private BuNewsService buNewsService;
	private BaseDatadictionaryService baseDatadictionaryService;
	private BuRoadService buRoadService;
	private String q;
	private int limit;
	private String xy;
	private String flag;
	private ScenicContainer sc;
	private BuAttractions ba;
	private BuWeather bw;
	private String xy1;
	private String name1;
	private BuNews news;
	private List<BaseDatadictionary> typeDatas;
	private List<BuRoad> roads;
	private BuCommentService buCommentService;
	private List<BuComment> comments;
	private List<BuNews> newsList = new ArrayList<BuNews>();
	private Link link;
	private BuLinkService buLinkService;
	private List<BuLink> buLink_list;
	//360全景显示条数
	private final static int NUM_360 = 4;
	private String pic_status="1";
	
	public String init(){
		return SUCCESS;
	}
	
	public String list() throws UnsupportedEncodingException{
		currentPage = this.getCurrentPage();
    	String where=" 1=1 ";
		if(skey!=null&&skey.length()>0){
			skey= URLDecoder.decode(skey, "utf-8");
			where+=" and a.fullname like '%"+skey+"%'";
		}
		where += " and fx is not null and fx!='' ";
		totalPage=(int)Math.ceil(webSearchService.getDataCount(where)/Double.valueOf(8));
		list = webSearchService.selectDataByPage(currentPage,8,where,"type asc");
		return SUCCESS;
	}
	
	/**
	 * 地图标注详情
	 * @return
	 */
	public String viewPointDetail(){
		if(StringUtil.isNotTrimBlank(type)){
			if(type.equals(SystemTypeConstant.DATA_TYPE_STAY)){
				dataContainer = webSearchService.findBuStayById(id);
			}else if(type.equals(SystemTypeConstant.DATA_TYPE_SHOPPING)){
				dataContainer = webSearchService.findBuEntertainmentshoppingById(id);
			}else if(type.equals(SystemTypeConstant.DATA_TYPE_FUNNY)){
				dataContainer = webSearchService.findBuEntertainmentshoppingById(id);
			}else if(type.equals(SystemTypeConstant.DATA_TYPE_GEOGRAPHY)){
				dataContainer = webSearchService.findBuGeographyById(id);
			}else if(type.equals(SystemTypeConstant.DATA_TYPE_DINING)){
				dataContainer = webSearchService.findBuDiningById(id);
			}
			skey = getNameString(type);
		}
		return "point_detail";
	}
	
	public String typeInit(){
		return "typeInit";
	}
	
	public String typeList() throws UnsupportedEncodingException{
		currentPage = this.getCurrentPage();
    	String where=" 1=1 ";
		if(skey!=null&&skey.length()>0){
			skey= URLDecoder.decode(skey, "utf-8");
			where+=" and fullname like '%"+skey+"%'";
		}
		where += " and fx is not null and fx!='' and type like'"+type+"%'";
		totalPage=(int)Math.ceil(webSearchService.getTypeDataCount(type,where)/Double.valueOf(8));
		list = webSearchService.findByType(type,currentPage,8,where," praise desc");
		return "typeInit";
	}
	
	public String typeSearch() throws UnsupportedEncodingException{
		String t = "",t1 = type;
		if(StringUtil.isNotTrimBlank(type)){
			if(type.length()>6){
				t = " code like '"+type.substring(0,6)+"%' and Notshow IS NULL";
				t1 = type.substring(0,6);
			}else{
				t = " code like '"+type+"%' and Notshow IS NULL";
			}
		}
		typeDatas = baseDatadictionaryService.selectByPage(1,100,t,"code asc");
		if(!(typeDatas==null||typeDatas.isEmpty())){
			typeDatas.get(0).setFullname("全部");
		}
    	String where=" 1=1 ";
    	where += " and fx is not null and fx!='' and type like'"+type+"%'";
    	limit = webSearchService.getTypeDataCount(t1,where+" and DIMAGES_STATUS=0 ");
		totalPage=(int)Math.ceil(limit/Double.valueOf(8));
		//先查询有图片
		if("1".equals(pic_status)){
			list = webSearchService.findByType(t1,page,4,where+" and DIMAGES_STATUS=1 "," praise desc");
			if(list==null||list.isEmpty()){
				pic_status = "2";
				page = 1;
				list = webSearchService.findByType(t1,page,8,where+" and DIMAGES_STATUS=0 "," praise desc");
			}
		}else{
			list = webSearchService.findByType(t1,page,8,where+" and DIMAGES_STATUS=0 "," praise desc");
		}
		
		skey = getNameString(t1);
		if(StringUtil.isNotTrimBlank(flag)){
			return "typeDiv";
		}
		return "typeList";
	}
	
	public String showDetail(){
		if(StringUtil.isNotTrimBlank(type)){
			if(type.equals(SystemTypeConstant.DATA_TYPE_STAY)){
				dataContainer = webSearchService.findBuStayById(id);
			}else if(type.equals(SystemTypeConstant.DATA_TYPE_SHOPPING)){
				dataContainer = webSearchService.findBuEntertainmentshoppingById(id);
			}else if(type.equals(SystemTypeConstant.DATA_TYPE_FUNNY)){
				dataContainer = webSearchService.findBuEntertainmentshoppingById(id);
			}else if(type.equals(SystemTypeConstant.DATA_TYPE_GEOGRAPHY)){
				dataContainer = webSearchService.findBuGeographyById(id);
			}else if(type.equals(SystemTypeConstant.DATA_TYPE_DINING)){
				dataContainer = webSearchService.findBuDiningById(id);
			}
			skey = getNameString(type);
			
		}
		return "show_detail";
	}
	
	
	public String searchAll() throws UnsupportedEncodingException{
		String where=" 1=1 ";
		if(skey!=null&&skey.length()>0){
			skey= URLDecoder.decode(skey, "utf-8"); 
			where+=" and a.fullname like '%"+skey+"%'";
		}
		where += " and fx is not null and fx!='' ";
		limit = webSearchService.getDataCount(where+" and DIMAGES_STATUS=0 ");
		totalPage=(int)Math.ceil(limit/Double.valueOf(8));
		//先查询有图片
		if("1".equals(pic_status)){
			list = webSearchService.selectDataByPage(page,4,where+" and DIMAGES_STATUS=1 "," praise desc");
			if(list==null||list.isEmpty()){
				pic_status = "2";
				page = 1;
				list = webSearchService.selectDataByPage(page,8,where+" and DIMAGES_STATUS=0 "," praise desc");
			}
		}else{
			list = webSearchService.selectDataByPage(page,8,where+" and DIMAGES_STATUS=0 "," praise desc");
		}
		if(StringUtil.isNotTrimBlank(flag)){
			return "typeDiv";
		}
		return "searchAll";
	}
	
	/**
	 * 景点列表
	 * @return
	 */
	public String scenicList(){
    	String where=" 1=1 ";
    	String newsType = "";
    	if(StringUtil.isTrimBlank(type)){
    		//东栅
    		type = "002019001";
    		newsType = "004028";
    	}else if(type.equals("002019001")){
    		newsType = "004028";
    	}else if(type.equals("002019002")){
    		//西栅
    		newsType = "004029";
    	}else if(type.equals("002019003")){
    		//雅达
    		newsType = "004030";
    	}
    	where+=" and type = "+type;
    	//查找景区简介
    	List<BuNews> newsList=buNewsService.selectByPage(1,1," type = "+newsType, "MODIFYDATE desc");
    	if(!(newsList==null||newsList.isEmpty())){
    		news = newsList.get(0);
    	}
    	limit = webSearchService.getBuattractionsCount(where);
		totalPage=(int)Math.ceil(limit/Double.valueOf(8));
		if(StringUtil.isNotTrimBlank(flag)){
			json = toJson(webSearchService.findAttractionsList(page, 12, where,"type asc"));
			return JSON;
		}else{
			sc = webSearchService.findBuattractions(page,12,where,"type asc");
		}
		return "scenicList";
	}
	
	public String scenicDetail(){
		if(StringUtil.isNotTrimBlank(id)){
			ba = webSearchService.findAttractionsById(id);
			if(StringUtil.isNotTrimBlank(ba.getDimages())){
				String str [] = ba.getDimages().split(",");
				ba.setImages(Arrays.asList(str));
			}
			if(ba.getBrowse()!=null){
				ba.setBrowse(ba.getBrowse()+1);
			}else{
				ba.setBrowse(1);
			}
			webSearchService.updateBySql("update bu_attractions set browse = "+ba.getBrowse()+" where fuid='"+id+"'");
		}
		return "scenicDetail";
	}
	
	public String test(){
		return "test";
	}
	
	public String autoCompleteData() throws UnsupportedEncodingException{
		q = URLDecoder.decode(q, "utf-8"); 
		json = webSearchService.findAutoCompleteData(q,limit);
		return JSON;
	}
	
	/**
	 * 驾驶路线
	 */
	public String carLineInit(){
//		if(StringUtil.isTrimBlank(name1)||StringUtil.isTrimBlank(xy1)){
//			name1 = "乌镇管委会";
//			xy1 = "120.483903387231,30.743029614206";
//		}
		return "carLine";
	}
	
	public String carLineData() throws UnsupportedEncodingException{
		String str [] = {"0,0","0,0"};
		if(StringUtil.isNotTrimBlank(q)){
			q = URLDecoder.decode(q, "utf-8");
			String strr [] = q.split("\\|");
			if(strr.length==2){
				if(StringUtil.isNotTrimBlank(strr[0])&&StringUtil.isNotTrimBlank(strr[1])){
					str [0] = webSearchService.findAutoCompleteData(strr[0]);
					str [1] = webSearchService.findAutoCompleteData(strr[1]);
				}
			}
		}
		Load load = new Load();
		try {
			GMPoint beginPoint = GMPoint.FromStringByComma(str[0]);
	    	GMPoint endPoint = GMPoint.FromStringByComma(str[1]);
	    	GMOptimalPath path = GMSearchFactory.GetOptimalPathStringByXY2(beginPoint.getX(), beginPoint.getY(), endPoint.getX(), endPoint.getY());
	    	load.setXy(path.GetPathString());
	    	load.setStart(str[0]);
	    	load.setEnd(str[1]);
	    	load.setRoadXy(path.GetPathDesLineArr());
	    	load.setRoadText(Arrays.asList(path.GetPathDesString().split("##")));
	    	DecimalFormat df=new DecimalFormat("#0.00");
	    	load.setLength(df.format(path.Length/1000));
	    	//时间  30km/h 1分钟0.5公里
	    	BigDecimal t = new BigDecimal(Double.parseDouble(load.getLength())/0.5).setScale(0, BigDecimal.ROUND_HALF_UP);
	    	if(Integer.parseInt(t.toString())==0){
	    		load.setTime("1");
	    	}else{
	    		load.setTime(t.toString());
	    	}
		} catch (Exception e) {
		}
		
    	json = toJson(load);
		return JSON;
	}
	
	/**
	 * 步行路线
	 * @return
	 */
	public String walkLineInit(){
//		if(StringUtil.isTrimBlank(name1)||StringUtil.isTrimBlank(xy1)){
//			name1 = "乌镇管委会";
//			xy1 = "120.483903387231,30.743029614206";
//		}
		return "walkLine";
	}
	
	public String walkLineData() throws UnsupportedEncodingException{
		String str [] = {"0,0","0,0"};
		if(StringUtil.isNotTrimBlank(q)){
			q = URLDecoder.decode(q, "utf-8");
			String strr [] = q.split("\\|");
			if(strr.length==2){
				if(StringUtil.isNotTrimBlank(strr[0])&&StringUtil.isNotTrimBlank(strr[1])){
					str [0] = webSearchService.findAutoCompleteData(strr[0]);
					str [1] = webSearchService.findAutoCompleteData(strr[1]);
				}
			}
		}
		Load load = new Load();
		try {
			GMPoint beginPoint = GMPoint.FromStringByComma(str[0]);
	    	GMPoint endPoint = GMPoint.FromStringByComma(str[1]);
	    	GMOptimalPath path = GMSearchFactory.GetOptimalPathStringByXY2(beginPoint.getX(), beginPoint.getY(), endPoint.getX(), endPoint.getY());
	    	load.setXy(path.GetPathString());
	    	load.setStart(str[0]);
	    	load.setEnd(str[1]);
	    	load.setRoadXy(path.GetPathDesLineArr());
	    	load.setRoadText(Arrays.asList(path.GetPathDesString().split("##")));
	    	DecimalFormat df=new DecimalFormat("#0.00");
	    	load.setLength(df.format(path.Length/1000));
	    	//时间  30km/h 1分钟0.5公里
	    	BigDecimal t = new BigDecimal(Double.parseDouble(load.getLength())/0.5).setScale(0, BigDecimal.ROUND_HALF_UP);
	    	if(Integer.parseInt(t.toString())==0){
	    		load.setTime("1");
	    	}else{
	    		load.setTime(t.toString());
	    	}
		} catch (Exception e) {
		}
		
    	json = toJson(load);
		return JSON;
	}
	
	private String getNameString(String type){
		String name = "";
		if(StringUtil.isTrimBlank(type)){
			type = "002018";
		}
		if(type.equals("002018")){
			name = "餐饮";
		}else if(type.equals("002017")){
			name = "住宿";
		}else if(type.equals("3")){
			name = "其他";
		}else if(type.equals("4")){
			name = "其他";
		}else if(type.equals("5")){
			name = "其他";
		}else if(type.equals("6")){
			name = "其他";
		}else if(type.equals("002015")){
			name = "购物";
		}else if(type.equals("002016")){
			name = "娱乐";
		}
		return name;
	}
	
	public String trafficPlayList(){
		String where=" 1=1 ";
		if(StringUtil.isTrimBlank(type)){
    		type = "004014";
    	}
    	where+=" and type = "+type;
    	limit = webSearchService.findNewsCount(where);
		totalPage=(int)Math.ceil(limit/Double.valueOf(10));
		dataContainer = webSearchService.findNews(page,10,where,"");
		if(StringUtil.isNotTrimBlank(flag)){
			return "trafficPlayDiv";
		}
		return "trafficPlayList";
	}
	
	public String newsList(){
		String where=" 1=1 ";
		if(StringUtil.isTrimBlank(type)){
    		type = "004002";
    	}
    	where+=" and type = "+type;
    	limit = webSearchService.findNewsCount(where);
		totalPage=(int)Math.ceil(limit/Double.valueOf(10));
		dataContainer = webSearchService.findNews(page,10,where," createdate desc");
		if(StringUtil.isNotTrimBlank(flag)){
			return "newsDiv";
		}
		return "newsList";
	}
	
	public String newsDetail(){
		if(StringUtil.isNotTrimBlank(id)){
			news = buNewsService.findById(id);
		}
		return "newsDetail";
	}
	
	public String likeToGo(){
		String where=" 1=1 ";
    	where += " and fx is not null and fx!='' ";
    	if(StringUtil.isNotTrimBlank(id)){
    		where += " and fuid != '"+id+"' ";
    	}
    	limit = webSearchService.getTypeDataCount(type,where);
		totalPage=(int)Math.ceil(limit/Double.valueOf(6));
		list = webSearchService.findByType(type,page,6,where,"praise desc");
		return "likeToGo";
	}
	
	public String showTrafficLine(){
		roads = buRoadService.execSql("select * from bu_road");
		json = toJson(roads);
		return JSON;
	}
	
	public String showMap(){
		return "showMap";
	}
	
	public String praiseClick(){
		if(StringUtil.isNotTrimBlank(id)&&StringUtil.isNotTrimBlank(q)&&StringUtil.isNotTrimBlank(type)){
			String sql = "bu_stay";
			if(StringUtil.isNotTrimBlank(type)){
				if(type.equals(SystemTypeConstant.DATA_TYPE_STAY)){
					sql = "bu_stay";
				}else if(type.equals(SystemTypeConstant.DATA_TYPE_SHOPPING)){
					sql = "bu_entertainmentshopping";
				}else if(type.equals(SystemTypeConstant.DATA_TYPE_FUNNY)){
					sql = "bu_entertainmentshopping";
				}else if(type.equals(SystemTypeConstant.DATA_TYPE_GEOGRAPHY)){
					sql = "bu_geography";
				}else if(type.equals(SystemTypeConstant.DATA_TYPE_DINING)){
					sql = "bu_dining";
				}else if(type.equals("news")){
					sql = "bu_news";
				}else if(type.equals("comment")){
					sql = "bu_comment";
				}else if(type.equals("scenic")){
					sql = "bu_attractions";
				}
				webSearchService.updateBySql("update "+sql+" set praise = "+q+" where fuid='"+id+"'");
			}
		}
		return JSON;
	}
	
	public String commentList(){
		if(StringUtil.isNotTrimBlank(id)){
			comments= buCommentService.execSql("select * from bu_comment where OBJECTID = '"+id+"' and examine=1 order by createdate desc");
		    if(!(comments==null||comments.isEmpty())){
		    	for(int i=0;i<comments.size();i++){
		    		if(comments.get(i).getPraise()==null){
		    			comments.get(i).setPraise(0);
		    		}
		    	}
		    }
		}
		return "commentList";
	}
	
	public String saveComment(){
		BuComment buComment = new BuComment();
		buComment.setFuid(UUIDCreater.getUUID());
		buComment.setCreatedate(new Date());
		buComment.setModifydate(new Date());
		buComment.setObjectid(id);
		buComment.setScore(Double.parseDouble(skey));
		buComment.setCapita(Double.parseDouble(flag));
		buComment.setSystype(type);
		buComment.setDescription(q);
		buCommentService.save(buComment);
//		double a_v = 0;double star = 0; int cn = 0;
//		if(StringUtil.isNotTrimBlank(type)){
//			String sql="bu_stay";
//			if(type.equals(SystemTypeConstant.DATA_TYPE_STAY)){
//				sql = "bu_stay";
//				DataContainer<BuStay> d = webSearchService.findBuStayById(id);
//				if(StringUtil.isNotTrimBlank(d.getObj().getConsumption())){
//					//人均消费
//					a_v = Double.parseDouble(d.getObj().getConsumption());
//				}
//				if(d.getObj().getStar()!=null){
//					//星
//					star = d.getObj().getStar();
//				}
//				if(d.getObj().getComment()!=null){
//					//评论数
//					cn = d.getObj().getComment();
//				}
//			}else if(type.equals(SystemTypeConstant.DATA_TYPE_SHOPPING)||type.equals(SystemTypeConstant.DATA_TYPE_FUNNY)){
//				sql = "bu_entertainmentshopping";
//				DataContainer<BuEntertainmentshopping> d = webSearchService.findBuEntertainmentshoppingById(id);
//				if(StringUtil.isNotTrimBlank(d.getObj().getConsumption())){
//					//人均消费
//					a_v = Double.parseDouble(d.getObj().getConsumption());
//				}
//				if(d.getObj().getStar()!=null){
//					//星
//					star = d.getObj().getStar();
//				}
//				if(d.getObj().getComment()!=null){
//					//评论数
//					cn = d.getObj().getComment();
//				}
//			}else if(type.equals(SystemTypeConstant.DATA_TYPE_GEOGRAPHY)){
//				sql = "bu_geography";
//				DataContainer<BuGeography> d = webSearchService.findBuGeographyById(id);
//				if(d.getObj().getConsumption()!=null){
//					//人均消费
//					a_v = d.getObj().getConsumption();
//				}
//				if(d.getObj().getStar()!=null){
//					//星
//					star = d.getObj().getStar();
//				}
//				if(d.getObj().getComment()!=null){
//					//评论数
//					cn = d.getObj().getComment();
//				}
//			}else if(type.equals(SystemTypeConstant.DATA_TYPE_DINING)){
//				sql = "bu_dining";
//				DataContainer<BuDining> d = webSearchService.findBuDiningById(id);
//				if(StringUtil.isNotTrimBlank(d.getObj().getConsumption())){
//					//人均消费
//					a_v = Double.parseDouble(d.getObj().getConsumption());
//				}
//				if(d.getObj().getStar()!=null){
//					//星
//					star = d.getObj().getStar();
//				}
//				if(d.getObj().getComment()!=null){
//					//评论数
//					cn = d.getObj().getComment();
//				}
//			}
//			a_v = (a_v*cn+Double.parseDouble(flag))/(cn+1)*1.0;
//			star = (star*cn+Double.parseDouble(skey))/(cn+1)*1.0;
//			webSearchService.updateBySql("update "+sql+" set comment = "+(cn+1)+",star="+star+",consumption="+a_v+" where fuid='"+id+"'");
//		}
//		if(StringUtil.isNotTrimBlank(id)){
//			comments= buCommentService.execSql("select * from bu_comment where OBJECTID = '"+id+"' order by createdate desc");
//		}
		return JSON;
	}
	
	public String wuzhen360(){
		newsList = buNewsService.execSql("select * from bu_news where type like '004025%' order by createdate desc limit 0,"+NUM_360);
		return "wuzhen360";
	}
	
	
	/**
	 * 联系我们
	 */
	public String contact(){
		newsList = buNewsService.execSql("select * from bu_news where type like '004034%'");
		return "contact";
	}
	
	/**
	 * 乌镇国际旅游区
	 */
	public String wuzhen(){
		newsList = buNewsService.execSql("select * from bu_news where type like '004035%'");
		return "wuzhen";
	}
	
	/**
	 * 友情链接
	 */
	public String link(){
		buLink_list =buLinkService.execSql("select * from bu_link");
		return "link";
	}
	
	
	
	public String getId(){
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getJson() {
		return json;
	}

	public void setJson(String json) {
		this.json = json;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public String get_pn() {
		return _pn;
	}

	public void set_pn(String _pn) {
		this._pn = _pn;
	}

	public String getSkey() {
		return skey;
	}

	public void setSkey(String skey) {
		this.skey = skey;
	}
	
	public int getCurrentPage() {
		int currentpage=1;
		String _pn1[];
		if(_pn!=null&&_pn.trim().length()>0){
		  _pn1=_pn.split(",");
		  currentpage=Integer.parseInt(_pn1[1]);
		}
		return currentpage;
	}

	public List<DataEntity> getList() {
		return list;
	}

	public void setList(List<DataEntity> list) {
		this.list = list;
	}

	public void setBuStayService(BuStayService buStayService) {
		this.buStayService = buStayService;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public DataContainer<?> getDataContainer() {
		return dataContainer;
	}

	public void setDataContainer(DataContainer<?> dataContainer) {
		this.dataContainer = dataContainer;
	}

	public WebSearchService getWebSearchService() {
		return webSearchService;
	}

	public void setWebSearchService(WebSearchService webSearchService) {
		this.webSearchService = webSearchService;
	}

	public BuStayService getBuStayService() {
		return buStayService;
	}

	public String getQ() {
		return q;
	}

	public void setQ(String q) {
		this.q = q;
	}

	public int getLimit() {
		return limit;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}

	public String getXy() {
		return xy;
	}

	public void setXy(String xy) {
		this.xy = xy;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public ScenicContainer getSc() {
		return sc;
	}

	public void setSc(ScenicContainer sc) {
		this.sc = sc;
	}

	public BuAttractions getBa() {
		return ba;
	}

	public void setBa(BuAttractions ba) {
		this.ba = ba;
	}

	public BuWeatherService getBuWeatherService() {
		return buWeatherService;
	}

	public void setBuWeatherService(BuWeatherService buWeatherService) {
		this.buWeatherService = buWeatherService;
	}

	public BuWeather getBw() {
		bw = buWeatherService.findById("1");
		return bw;
	}

	public void setBw(BuWeather bw) {
		this.bw = bw;
	}

	public String getXy1() {
		return xy1;
	}

	public void setXy1(String xy1) {
		this.xy1 = xy1;
	}

	public String getName1() {
		return name1;
	}

	public void setName1(String name1) {
		this.name1 = name1;
	}

	public BuNewsService getBuNewsService() {
		return buNewsService;
	}

	public void setBuNewsService(BuNewsService buNewsService) {
		this.buNewsService = buNewsService;
	}

	public BuNews getNews() {
		return news;
	}

	public void setNews(BuNews news) {
		this.news = news;
	}

	public List<BaseDatadictionary> getTypeDatas() {
		return typeDatas;
	}

	public void setTypeDatas(List<BaseDatadictionary> typeDatas) {
		this.typeDatas = typeDatas;
	}

	public void setBaseDatadictionaryService(
			BaseDatadictionaryService baseDatadictionaryService) {
		this.baseDatadictionaryService = baseDatadictionaryService;
	}

	public BuRoadService getBuRoadService() {
		return buRoadService;
	}

	public void setBuRoadService(BuRoadService buRoadService) {
		this.buRoadService = buRoadService;
	}

	public BaseDatadictionaryService getBaseDatadictionaryService() {
		return baseDatadictionaryService;
	}

	public List<BuRoad> getRoads() {
		return roads;
	}

	public void setRoads(List<BuRoad> roads) {
		this.roads = roads;
	}

	public BuCommentService getBuCommentService() {
		return buCommentService;
	}

	public void setBuCommentService(BuCommentService buCommentService) {
		this.buCommentService = buCommentService;
	}

	public List<BuComment> getComments() {
		return comments;
	}

	public void setComments(List<BuComment> comments) {
		this.comments = comments;
	}

	public List<BuNews> getNewsList() {
		return newsList;
	}

	public void setNewsList(List<BuNews> newsList) {
		this.newsList = newsList;
	}

	public String getPic_status() {
		return pic_status;
	}

	public void setPic_status(String pic_status) {
		this.pic_status = pic_status;
	}

	public Link getLink() {
		return link;
	}

	public void setLink(Link link) {
		this.link = link;
	}

	public List<BuLink> getBuLink_list() {
		return buLink_list;
	}

	public void setBuLink_list(List<BuLink> buLink_list) {
		this.buLink_list = buLink_list;
	}

	public BuLinkService getBuLinkService() {
		return buLinkService;
	}

	public void setBuLinkService(BuLinkService buLinkService) {
		this.buLinkService = buLinkService;
	}

	
}
