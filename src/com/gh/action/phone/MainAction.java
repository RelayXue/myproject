package com.gh.action.phone;



import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.gh.base.Action;
import com.gh.common.UUIDCreater;
import com.gh.dao.IntegrateddataDAO;
import com.gh.entity.BuAttractions;
import com.gh.entity.BuComment;
import com.gh.entity.BuDining;
import com.gh.entity.BuEntertainmentshopping;
import com.gh.entity.BuGeography;
import com.gh.entity.BuNews;
import com.gh.entity.BuRoad;
import com.gh.entity.BuStay;
import com.gh.entity.BuWeather;
import com.gh.entity.Integrateddata;
import com.gh.entity.IntegrateddataWithBLOBs;
import com.gh.entity.Load;
import com.gh.phone.JSONPR;
import com.gh.phone.ReturnInfo;
import com.gh.phone.StringUtil;
import com.gh.service.BuAttractionsService;
import com.gh.service.BuCommentService;
import com.gh.service.BuDiningService;
import com.gh.service.BuEntertainmentshoppingService;
import com.gh.service.BuGeographyService;
import com.gh.service.BuNewsService;
import com.gh.service.BuRoadService;
import com.gh.service.BuStayService;
import com.gh.service.BuWeatherService;
import com.google.zxing.client.result.GeoParsedResult;
import com.wuzhen.gmpath.GMOptimalPath;
import com.wuzhen.gmpath.GMPoint;
import com.wuzhen.gmpath.GMSearchFactory;

/**
 * 手机web界面业务入口
 * @author wjc
 *
 */
public class MainAction extends Action {

	//dao
	BuDiningService buDiningService;
	BuEntertainmentshoppingService esService;
	BuStayService buStayService;
	BuNewsService buNewsService;
	BuAttractionsService buAttractionsService;
	IntegrateddataDAO integrateddataDAO;
	BuWeatherService buWeatherService;
	BuRoadService buRoadService;
	BuGeographyService buGeographyService;
	BuCommentService buCommentService;
	
	//req
	String category;	//查询类别
	int area;	//查询范围(默认0米为查询所有)
	String orderBy;	//排序方式 (0默认排序 1距离由近到远 2距离由高到低 3价格由低到高 4人气由高到低)
	String filterCategory;	//筛选类别?
	String id;	//主键
	String key;
	
	String start_point_id;
	String end_point_id;
	String start_point;
	String end_point;
	
	String r;
	String qid;
	
	double star;
	
	//rep
	ReturnInfo ri = new ReturnInfo();
	List<BuNews> news_list;
	BuNews news;
	String mes;
	BuWeather buWeater;
	
	//当前位置
	String pos_x = "0.0";
	String pos_y = "0.0";
	
	public String index(){
		buWeater = buWeatherService.findById("1");
		news_list = buNewsService.execSql("select * from bu_news where type = '004032'");
		return "index";
	}
	
	/**
	 * 业务相关列表查询
	 * @return
	 */
	public String list(){
		//temp
//		pos_x = "120.48805711";
//		pos_y = "30.75085066";
		
		try {
			String where = "";
			com.gh.phone.StringUtil su = new com.gh.phone.StringUtil();
			if((pos_x.equals("0") || pos_y.equals("0")) && ("1".equals(orderBy) || area > 0)){
				ri.setCode(0);
				ri.setHint("无法得到您的当前位置");
			}else{
				if(area > 0){
					where = addAndWhereSql(where, "  distance <= "+area);	//范围判断公式
				}
				
				//排序
				String order_by = "";
				switch(su.toInt(orderBy)){
					case 1://距离由近到远
						order_by = " dimages_status desc, distance asc ";
						break;
					case 2://价格由高到低
						order_by = " dimages_status desc, consumption desc ";
						break;
					case 3://价格由低到高
						order_by = " dimages_status desc, consumption asc ";
						break;
					case 4://人气由高到底
						order_by = " dimages_status desc, praise desc ";
						break;
					default:
						order_by = " dimages_status desc, praise desc ";
						break;
				}
				
				//类别
				if(!"0".equals(category)){
					where = addAndWhereSql(where, " type like '"+category+"%' ");
				}
				
				//关键字
				if(!StringUtils.isEmpty(key) && !key.equals("null") && key != null){
					where = addAndWhereSql(where, " fullname like '%"+key+"%' ");
				}
				
				//xy 坐标为空的店铺不查
				where = addAndWhereSql(where, " (fx is not null and fx != '' and fy is not null and fy != '') ");
				List<IntegrateddataWithBLOBs> list = integrateddataDAO.IntegratedQueryByPage(indexPage, pageSize, where, order_by, pos_x, pos_y);
				int total_size = integrateddataDAO.getCount(where);
				//得到页数
				int total_page = (total_size / pageSize) + ((total_size % pageSize > 0)?1:0); 
				
				if(list.size() > 0){
					ri.setCode(1);
					ri.setO(list);
					ri.getOs().put("total_page", total_page);
				}else{
					ri.setCode(0);
					if(indexPage == 1){
						ri.setHint("没有相关数据~");
					}else{
						ri.setHint("没有更多数据了~");
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		ReturnInfo.printJson(request, response, ri, false);
		return null;
	}
	
	
	
	
	/**
	 * 显示详情
	 * @return
	 */
	public String detail(){
		if(category.equals("002018")){//显示餐饮店铺详情
			BuDining o = buDiningService.findById(id);
			if(o != null){
				ri.setO(o);
				List<BuDining> din_list = buDiningService.execSql("select * from bu_dining where fuid != '"+id+"' order by CREATEDATE desc LIMIT 0,3");
				ri.getOs().put("more", StringUtil.replaceBlank(JSONPR.list2json(din_list, "fuid;fullname;address;phone;supervisor;supervisorphone;fx;fy;introduction")));
				ri.setCode(1);
			}else{
				ri.setCode(0);
				ri.setHint("没有找到相关数据");
			}
		}else if(category.equals("002016")){//显示娱乐详情
			BuEntertainmentshopping o = esService.findById(id);
			if(o != null){
				ri.setO(o);
				List<BuEntertainmentshopping> e_list = esService.execSql("select * from bu_entertainmentshopping where  type = '002016' and fuid != '"+id+"'  order by CREATEDATE desc LIMIT 0,3");
				ri.getOs().put("more", StringUtil.replaceBlank(JSONPR.list2json(e_list, "fuid;fullname;address;phone;supervisor;supervisorphone;introduction")));
				ri.setCode(1);
			}else{
				ri.setCode(0);
				ri.setHint("没有找到相关数据");
			}
		}else if(category.equals("002015")){//显示购物详情
			BuEntertainmentshopping o = esService.findById(id);
			if(o != null){
				ri.setO(o);
				List<BuEntertainmentshopping> e_list = esService.execSql("select * from bu_entertainmentshopping where  type = '002015' and fuid != '"+id+"'  order by CREATEDATE desc LIMIT 0,3");
				ri.getOs().put("more", StringUtil.replaceBlank(JSONPR.list2json(e_list, "fuid;fullname;address;phone;supervisor;supervisorphone;introduction")));
				ri.setCode(1);
			}else{
				ri.setCode(0);
				ri.setHint("没有找到相关数据");
			}
		}else if(category.equals("002017")){//显示住宿详情
			try {
				BuStay o = buStayService.findById(id);
				if(o != null){
					ri.setO(o);
					List<BuStay> e_list = buStayService.execSql("select * from bu_stay where fuid != '"+id+"' order by CREATEDATE desc LIMIT 0,3");
					ri.getOs().put("more", StringUtil.replaceBlank(JSONPR.list2json(e_list, "fuid;fullname;address;phone;supervisor;supervisorphone;introduction")));
					ri.setCode(1);
				}else{
					ri.setCode(0);
					ri.setHint("没有找到相关数据");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}/*else if(category.equals("002019")){//显示景点详情
			try {
				BuAttractions o = buAttractionsService.findById(id);
				if(o != null){
					ri.setO(JSONPR.object2json(o, "fuid;fullname;phone;address;dimages;introduction;type;praise;fx;fy;"));
					List<BuAttractions> e_list = buAttractionsService.execSql("select * from bu_attractions where fuid != '"+id+"' order by CREATEDATE desc LIMIT 0,3");
					ri.getOs().put("more", JSONPR.list2json(e_list, "fuid;fullname;phone;address;dimages;type;praise;"));
					ri.setCode(1);
				}else{
					ri.setCode(0);
					ri.setHint("没有找到相关数据");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}*/else{	//所有的其他类型的详情
			try {
				List<IntegrateddataWithBLOBs> os = integrateddataDAO.SimpleIntegratedQueryByPage(1, 1, " fuid = '"+id+"'", null);
				if(os != null && os.size() > 0){
					ri.setO(os.get(0));
					List<IntegrateddataWithBLOBs> e_list = integrateddataDAO.SimpleIntegratedQueryByPage(1, 3, " fuid != '"+id+"' AND type = '"+os.get(0).getType()+"'", null);
					ri.getOs().put("more", StringUtil.replaceBlank(JSONPR.list2json(e_list, "fuid;fullname;phone;address;dimages;type;praise;introduction")));
					ri.setCode(1);
				}else{
					ri.setCode(0);
					ri.setHint("没有找到相关数据");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		ReturnInfo.printJson(request, response, ri, false);
		return null;
	}
	
	/**
	 * 地图搜索 （周边5000米）
	 * @return
	 */
	public String mapSearch(){
		try {
			String where = " fullname like '%"+key+"%' AND (fx is not null and fy is not null and fx != '' and fy != '') ";
			//if(su.toInt(pos_x) != 0 && su.toInt(pos_y) != 0) where += " and  SQRT(POW(fx-"+pos_x+", 2) + POW(fy-"+pos_y+", 2)) * 94690.206 < 99999 ";
			List<IntegrateddataWithBLOBs> stay_list = integrateddataDAO.SimpleIntegratedQueryByPage(1, 15, where, " length(fullname) asc");
			if(stay_list.size() > 0){
				ri.setO(JSONPR.list2json(stay_list, "fuid;fullname;address;type;fx;fy;dimages;supervisor;supervisorphone;phone;"));
				ri.setCode(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		ReturnInfo.printJson(request, response, ri, false);
		return null;
	}
	
	/**
	 * 点赞操作
	 * @return
	 */
	public String clickLike(){
		if(!StringUtils.isEmpty(category) && !category.equals("0")){
			if("1".equals(category)){	//新闻
				BuNews nn = buNewsService.findById(id);
				if(nn != null){
					nn.setPraise(StringUtil.toInt(nn.getPraise())+1);
					buNewsService.update(nn);
					ri.setCode(1);
					ri.getOs().put("count", nn.getPraise());
				}
			}else if("002018".equals(category.substring(0,6))){	//餐饮
				BuDining nn = buDiningService.findById(id);
				if(nn != null){
					nn.setPraise(StringUtil.toInt(nn.getPraise())+1);
					buDiningService.update(nn);
					ri.setCode(1);
					ri.getOs().put("count", nn.getPraise());
				}
			}else if("002015".equals(category.substring(0,6)) || "002016".equals(category.substring(0,6))){	//娱乐 - 购物
				BuEntertainmentshopping nn = esService.findById(id);
				if(nn != null){
					nn.setPraise(StringUtil.toInt(nn.getPraise())+1);
					esService.update(nn);
					ri.setCode(1);
					ri.getOs().put("count", nn.getPraise());
				}
			}else if("002017".equals(category.substring(0,6))){	//住宿
				BuStay nn = buStayService.findById(id);
				if(nn != null){
					nn.setPraise(StringUtil.toInt(nn.getPraise())+1);
					buStayService.update(nn);
					ri.setCode(1);
					ri.getOs().put("count", nn.getPraise());
				}
			}/*else if("002019".equals(category)){	//景点
				BuAttractions nn = buAttractionsService.findById(id);
				if(nn != null){
					nn.setPraise(StringUtil.toInt(nn.getPraise())+1);
					buAttractionsService.update(nn);
					ri.setCode(1);
					ri.getOs().put("count", nn.getPraise());
				}
			}*/else{
				BuGeography nn = buGeographyService.findById(id);
				if(nn != null){
					nn.setPraise(StringUtil.toInt(nn.getPraise())+1);
					buGeographyService.update(nn);
					ri.setCode(1);
					ri.getOs().put("count", nn.getPraise());
				}
			}
		}else{
			ri.setCode(-1);
			ri.setHint("没有找到类别代码");
		}

		ReturnInfo.printJson(request, response, ri, false);
		return null;
	}
	
	/**
	 * 查询路线信息
	 * @return
	 */
	public String queryRouteMes(){
		try {
			String start_name = "当前位置";
			if(pos_x.equals("0") || pos_y.equals("0")){
				if(StringUtils.isEmpty(start_point)){
					ri.setCode(-1); ri.setHint("请输入起点地址"); 
				}else{
					List<IntegrateddataWithBLOBs> start_points = integrateddataDAO.SimpleIntegratedQueryByPage(1, 1, "FULLNAME LIKE '"+start_point+"'", null);
					if(start_points!=null && start_points.size() > 0){ 
						pos_x = start_points.get(0).getFx(); 
						pos_y = start_points.get(0).getFy(); 
						start_name = start_points.get(0).getFullname();
					}else{
						ri.setCode(-1); ri.setHint("无法获取到该地址位置, 请选择其他起点"); 
					}
				}
			}
			
			if(!StringUtils.isEmpty(pos_x) && !StringUtils.isEmpty(pos_y)){ 
				List<IntegrateddataWithBLOBs> end_points = integrateddataDAO.SimpleIntegratedQueryByPage(1, 1, " FULLNAME LIKE '"+end_point+"' ", null);
				if(end_points != null && end_points.size() >0 && !StringUtils.isEmpty(end_points.get(0).getFx()) && !StringUtils.isEmpty(end_points.get(0).getFy())){
					//DataEntity start_point = start_points.get(0);
					//DataEntity end_point = end_points.get(0);
					GMPoint beginPoint = GMPoint.FromStringByComma(pos_x+","+pos_y);
			    	GMPoint endPoint = GMPoint.FromStringByComma(end_points.get(0).getFx()+","+ end_points.get(0).getFy());
			    	GMOptimalPath path = GMSearchFactory.GetOptimalPathStringByXY2(beginPoint.getX(), beginPoint.getY(), endPoint.getX(), endPoint.getY());
			    	Load load = new Load();
			    	load.setStart(pos_x+","+pos_y);
			    	load.setEnd(end_points.get(0).getFx()+","+end_points.get(0).getFy());
			    	load.setStart_name(start_name);
			    	load.setEnd_name(end_points.get(0).getFullname());
			    	load.setXy(path.GetPathString());
			    	load.setRoadXy(path.GetPathDesLineArr());
			    	load.setRoadText(Arrays.asList(path.GetPathDesString().split("##")));
			    	DecimalFormat df=new DecimalFormat("#0.00");
			    	load.setLength(df.format(path.Length/1000));
			    	ri.setO(load);
			    	ri.setCode(1);
				}else{
					ri.setCode(-1); ri.setHint("未找到目的地地址, 请重新选择");
				}
			}else{
				ri.setCode(-1); ri.setHint("未找到起点地址, 请重新选择"); 
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		ReturnInfo.printJson(request, response, ri, false);
		return null;
	}
	
	/**
	 * 美图分享
	 * @return
	 */
	public String pictureShare(){
		List<BuNews> news = buNewsService.execSql("select * from bu_news where type='004005' and (fx is not null and fx != '' and fy is not null and fy != '') order by praise desc ");
		if(news != null && news.size() > 0){
			ri.setCode(1);
			ri.setO(JSONPR.list2json(news, "fuid;img1;fx;fy;"));
		}else{
			ri.setCode(0);
		}
		
		ReturnInfo.printJson(request, response, ri, false);
		return null;
	}
	
	/**
	 * 路况查询
	 * @return
	 */
	public String routeStatus(){
		List<BuRoad> roads = buRoadService.execSql("select * from bu_road");
		if(roads != null && roads.size() > 0){
			ri.setCode(1);
			ri.setO(JSONPR.list2json(roads, "xy;state;"));
		}else{
			ri.setCode(0);
		}
		
		ReturnInfo.printJson(request, response, ri, false);
		return null;
	}
	
	/**
	 * 显示路线详细信息
	 * @return
	 */
	public String showRouteDetail(){
		return "route_detail";
	}
	
	/**
	 * 评星级
	 * @return
	 */
	public String rating(){
		try {
			BuComment buComment = new BuComment();
			buComment.setFuid(UUIDCreater.getUUID());
			buComment.setCreatedate(new Date());
			buComment.setModifydate(new Date());
			buComment.setObjectid(id);
			buComment.setScore(star);
			buComment.setCapita(0.0);
			if(category.length() > 6){ category = category.substring(0,6); }
			buComment.setSystype(category);
			buComment.setDescription("手机评级");
			buCommentService.save(buComment);
			
			ri.setCode(1);
			ri.setHint("评级成功!");
		} catch (Exception e) {
			e.printStackTrace();
			ri.setHint("评级失败! 请检查网络");
		}
		
		ReturnInfo.printJson(request, response, ri, false);
		return null;
	}
	
	public String selectEqCode(){
		if(!StringUtil.isBlank(qid)){
			String where = " QID = '"+qid+"' ";
			List<IntegrateddataWithBLOBs> stay_list = integrateddataDAO.SimpleIntegratedQueryByPage(1, 1, where, null);
			if(stay_list != null && stay_list.size() > 0){
				id = stay_list.get(0).getFuid();
				category = stay_list.get(0).getType();
				return "select_eqcode";
			}
		}
		ri.setCode(-1);
		ri.setHint("二维码不正确!");
		ReturnInfo.printJson(request, response, ri, false);
		return null;
	}
	
	public String addAndWhereSql(String sql, String add_sql){
		if(StringUtils.isEmpty(sql)){
			return "  "+add_sql+"  ";
		}else{
			return sql + "   AND  "+add_sql+"  ";
		}
	}
	
	
	
	public String getbt(){
		if(r!=null&&r.equals("1")){
			return "Midnight";
		}
		if(qid!=null&&qid.length()>0){
			List<Integrateddata> list=integrateddataDAO.execSql("select * from integrateddata where qid='"+qid+"'");
			if(list!=null&&list.size()>0){
				id=list.get(0).getFuid();
				category=list.get(0).getType();
				return "detail";
			}
		}else{
			return "show";
		}
		return "show";
	}
	
	
	public BuDiningService getBuDiningService() {
		return buDiningService;
	}

	public void setBuDiningService(BuDiningService buDiningService) {
		this.buDiningService = buDiningService;
	}

	public BuEntertainmentshoppingService getEsService() {
		return esService;
	}

	public void setEsService(BuEntertainmentshoppingService esService) {
		this.esService = esService;
	}

	public BuStayService getBuStayService() {
		return buStayService;
	}

	public void setBuStayService(BuStayService buStayService) {
		this.buStayService = buStayService;
	}

	public int getArea() {
		return area;
	}

	public void setArea(int area) {
		this.area = area;
	}

	public String getOrderBy() {
		return orderBy;
	}

	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}

	public ReturnInfo getRi() {
		return ri;
	}

	public void setRi(ReturnInfo ri) {
		this.ri = ri;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public BuNewsService getBuNewsService() {
		return buNewsService;
	}

	public void setBuNewsService(BuNewsService buNewsService) {
		this.buNewsService = buNewsService;
	}

	public List<BuNews> getNews_list() {
		return news_list;
	}

	public void setNews_list(List<BuNews> news_list) {
		this.news_list = news_list;
	}

	public BuNews getNews() {
		return news;
	}

	public void setNews(BuNews news) {
		this.news = news;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getFilterCategory() {
		return filterCategory;
	}

	public void setFilterCategory(String filterCategory) {
		this.filterCategory = filterCategory;
	}

	public String getPos_x() {
		return pos_x;
	}

	public void setPos_x(String pos_x) {
		this.pos_x = pos_x;
	}

	public String getPos_y() {
		return pos_y;
	}

	public void setPos_y(String pos_y) {
		this.pos_y = pos_y;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public BuAttractionsService getBuAttractionsService() {
		return buAttractionsService;
	}

	public void setBuAttractionsService(BuAttractionsService buAttractionsService) {
		this.buAttractionsService = buAttractionsService;
	}

	public String getCategory() {
		return category;
	}

	public String getStart_point_id() {
		return start_point_id;
	}

	public void setStart_point_id(String start_point_id) {
		this.start_point_id = start_point_id;
	}

	public String getEnd_point_id() {
		return end_point_id;
	}

	public void setEnd_point_id(String end_point_id) {
		this.end_point_id = end_point_id;
	}

	public String getMes() {
		return mes;
	}

	public void setMes(String mes) {
		this.mes = mes;
	}

	public IntegrateddataDAO getIntegrateddataDAO() {
		return integrateddataDAO;
	}

	public void setIntegrateddataDAO(IntegrateddataDAO integrateddataDAO) {
		this.integrateddataDAO = integrateddataDAO;
	}

	public BuWeatherService getBuWeatherService() {
		return buWeatherService;
	}

	public void setBuWeatherService(BuWeatherService buWeatherService) {
		this.buWeatherService = buWeatherService;
	}

	public BuWeather getBuWeater() {
		return buWeater;
	}

	public void setBuWeater(BuWeather buWeater) {
		this.buWeater = buWeater;
	}

	public BuRoadService getBuRoadService() {
		return buRoadService;
	}

	public void setBuRoadService(BuRoadService buRoadService) {
		this.buRoadService = buRoadService;
	}

	public BuGeographyService getBuGeographyService() {
		return buGeographyService;
	}

	public void setBuGeographyService(BuGeographyService buGeographyService) {
		this.buGeographyService = buGeographyService;
	}

	public String getStart_point() {
		return start_point;
	}

	public void setStart_point(String start_point) {
		this.start_point = start_point;
	}

	public String getEnd_point() {
		return end_point;
	}

	public void setEnd_point(String end_point) {
		this.end_point = end_point;
	}

	public BuCommentService getBuCommentService() {
		return buCommentService;
	}

	public void setBuCommentService(BuCommentService buCommentService) {
		this.buCommentService = buCommentService;
	}

	public double getStar() {
		return star;
	}

	public void setStar(double star) {
		this.star = star;
	}

	public String getQid() {
		return qid;
	}

	public void setQid(String qid) {
		this.qid = qid;
	}

	public String getR() {
		return r;
	}

	public void setR(String r) {
		this.r = r;
	}
}
