package com.gh.action.touch;



import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

import org.apache.commons.lang.StringUtils;

import com.gh.base.Action;
import com.gh.common.SplitChinese;
import com.gh.common.common;
import com.gh.dao.IntegrateddataDAO;
import com.gh.entity.BuDining;
import com.gh.entity.BuEntertainmentshopping;
import com.gh.entity.BuNews;
import com.gh.entity.BuStay;
import com.gh.entity.Integrateddata;
import com.gh.entity.IntegrateddataWithBLOBs;
import com.gh.entity.Load;
import com.gh.phone.JSONPR;
import com.gh.phone.ReturnInfo;
import com.gh.phone.StringUtil;
import com.gh.service.BuDiningService;
import com.gh.service.BuEntertainmentshoppingService;
import com.gh.service.BuNewsService;
import com.gh.service.BuStayService;
import com.wuzhen.gmpath.GMOptimalPath;
import com.wuzhen.gmpath.GMPoint;
import com.wuzhen.gmpath.GMSearchFactory;

/**
 * 触摸屏4大类相关业务
 * @author wjc
 *
 */
public class MainAction extends Action {

	//dao
	BuDiningService buDiningService;
	BuEntertainmentshoppingService esService;
	BuStayService buStayService;
	BuNewsService buNewsService;
	IntegrateddataDAO integrateddataDAO;
	
	//req
	String category;	//数据类型
	int area;	//查询范围(默认0米为查询所有)
	String orderBy;	//排序方式?
	String filter;	//筛选方式?
	String id;	//主键
	String key;
	
	//rep
	ReturnInfo ri = new ReturnInfo();
	List<BuNews> news_list;
	BuNews news;
	
	class TempPoint{
		String x;
		String y;
	}
	
	/**
	 * 根据IP获取经纬度
	 * @return
	 */
	public TempPoint getPositionForIP(){
		TempPoint p = new TempPoint();
		String ip = common.getIpAddr(request);
		if("10.30.0.26".equals(ip)){
			//火车站
			p.x = "120.489801";
			p.y = "30.734172";
		}else if("10.30.0.25".equals(ip)){
			//镇政府
			p.x = "120.482919";
			p.y = "30.744825";
		}else{
			//默认 乌镇位置
			p.x = "120.48698206";
			p.y = "30.75249743";
		}
		
		return p;
	}
	
	public String index(){
		return "index";
	}
	
	/**
	 * 业务相关列表查询
	 * @return
	 */
	public String list(){
		try {
			String where = "";
			if(area > 0){
				where = addAndWhereSql(where, "  distance <= "+area);	//范围判断公式
			}
			
			//排序
			String order_by = "";
			switch(StringUtil.toInt(orderBy)){
				case 1://距离由近到远
					order_by = " dimages_status desc, distance asc ";
					break;
				case 2://价格由高到低
					order_by = " dimages_status desc,consumption desc ";
					break;
				case 3://价格由低到高
					order_by = " dimages_status desc,consumption asc ";
					break;
				case 4://人气由高到底
					order_by = " dimages_status desc,praise desc ";
					break;
				default:
					order_by = " dimages_status desc, praise desc ";
					break;
			}
			
			//类别
			where = addAndWhereSql(where, " type like '"+category+"%' AND (fx is not null and fx != '' and fy is not null and fy != '')");
			TempPoint p = getPositionForIP();
			List<IntegrateddataWithBLOBs> list = integrateddataDAO.IntegratedQueryByPage(indexPage, pageSize, where, order_by, p.x, p.y);
			if(list.size() > 0){
				ri.setCode(1);
				ri.setO(JSONPR.list2json(list, "fuid;fullname;fx;fy;type;phone;address;dimages;supervisor;supervisorphone;praise;consumption;distance;dimagesStatus;"));
			}else{
				ri.setCode(0);
				if(indexPage == 1){
					ri.setHint("没有相关数据~");
				}else{
					ri.setHint("没有更多数据了~");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		ReturnInfo.printJson(request, response, ri, false);
		return null;
	}
	
	/**
	 * 显示美食店铺详情
	 * @return
	 */
	public String showMeiShiDetail(){
		BuDining o = buDiningService.findById(id);
		if(o != null){
			ri.setO(o);
			List<BuDining> din_list = buDiningService.execSql("select * from bu_dining where fuid != '"+id+"' AND (fx is not null and fx != '' and fy is not null and fy != '') order by CREATEDATE desc LIMIT 0,3");
			ri.getOs().put("more", JSONPR.list2json(din_list, "fuid;fullname;address;phone;supervisorphone;introduction;"));
			ri.setCode(1);
		}else{
			ri.setCode(0);
			ri.setHint("没有找到该地点");
		}
		ReturnInfo.printJson(request, response, ri, false);
		return null;
	}
	
	/**
	 * 显示娱乐详情
	 * @return
	 */
	public String showYuLeDetail(){
		BuEntertainmentshopping o = esService.findById(id);
		if(o != null){
			ri.setO(o);
			List<BuEntertainmentshopping> e_list = esService.execSql("select * from bu_entertainmentshopping where  type like '002016%' and fuid != '"+id+"' AND (fx is not null and fx != '' and fy is not null and fy != '')  order by CREATEDATE desc LIMIT 0,3");
			ri.getOs().put("more", JSONPR.list2json(e_list, "fuid;fullname;address;phone;supervisorphone;introduction;"));
			ri.setCode(1);
		}else{
			ri.setCode(0);
			ri.setHint("没有找到该地点");
		}
		ReturnInfo.printJson(request, response, ri, false);
		return null;
	}
	
	/**
	 * 显示购物详情
	 * @return
	 */
	public String showGouWuDetail(){
		BuEntertainmentshopping o = esService.findById(id);
		if(o != null){
			ri.setO(o);
			List<BuEntertainmentshopping> e_list = esService.execSql("select * from bu_entertainmentshopping where  type like '002015%' and fuid != '"+id+"' AND (fx is not null and fx != '' and fy is not null and fy != '')  order by CREATEDATE desc LIMIT 0,3");
			ri.getOs().put("more", JSONPR.list2json(e_list, "fuid;fullname;address;phone;supervisorphone;introduction;"));
			ri.setCode(1);
		}else{
			ri.setCode(0);
			ri.setHint("没有找到该地点");
		}
		ReturnInfo.printJson(request, response, ri, false);
		return null;
	}
	
	/**
	 * 显示住宿详情
	 * @return
	 */
	public String showZhuSuDetail(){
		try {
			BuStay o = buStayService.findById(id);
			if(o != null){
				ri.setO(o);
				List<BuStay> e_list = buStayService.execSql("select * from bu_stay where fuid != '"+id+"' AND (fx is not null and fx != '' and fy is not null and fy != '') order by CREATEDATE desc LIMIT 0,3");
				ri.getOs().put("more", JSONPR.list2json(e_list, "fuid;fullname;address;phone;supervisorphone;introduction;"));
				ri.setCode(1);
			}else{
				ri.setCode(0);
				ri.setHint("没有找到该地点");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		ReturnInfo.printJson(request, response, ri, false);
		return null;
	}
	
	/**
	 * 显示新闻列表
	 * @return
	 */
	public String showNewsList(){
		if(indexPage == 0) indexPage = 1;
		if(pageSize == 0) pageSize = 999;
		switch(StringUtil.toInt(category)){
		case 1:	//乌镇地理
			news_list = buNewsService.selectByPage(indexPage, pageSize, " type = '004008' and deletemark=0 ", "MODIFYDATE desc");
			break;
		case 2:	//乌镇历史
			news_list = buNewsService.selectByPage(indexPage, pageSize, " type = '004009' and deletemark=0 ", "MODIFYDATE desc");
			break;
		case 3:	//乌镇名俗
			news_list = buNewsService.selectByPage(indexPage, pageSize, " type = '004010' and deletemark=0 ", "MODIFYDATE desc");
			break;
		case 4:	//乌镇故事
			news_list = buNewsService.selectByPage(indexPage, pageSize, " type = '004011' and deletemark=0 ", "MODIFYDATE desc");
			break;
		case 5:	//乌镇名人
			news_list = buNewsService.selectByPage(indexPage, pageSize, " type = '004012' and deletemark=0 ", "MODIFYDATE desc");
			for(int i = 0;i<news_list.size();i++){
				String content = SplitChinese.Html2Text(news_list.get(i).getContent());
				news_list.get(i).setContent((content.length() > 22)?content.substring(0, 22)+"..":content);
			}
			break;
		case 6:	//乌镇保护 
			news_list = buNewsService.selectByPage(indexPage, pageSize, " type = '004013' and deletemark=0 ", "MODIFYDATE desc");
			break;
		}
		return "news_list";
	}

	
	/**
	 * 显示新闻-名人详细信息
	 * @return
	 */
	public String showNewsMingRenDetail(){
		news = buNewsService.findById(id);
		return "mingren_detail";
	}
	
	/**
	 * 地图搜索 （周边1000米）
	 * @return
	 */
	public String mapSearch(){
		try {
			TempPoint p = getPositionForIP();
			List<IntegrateddataWithBLOBs> stay_list = integrateddataDAO.IntegratedQueryByPage(1, 10, " fullname like '%"+key+"%' and distance < 5000 and (fx is not null and fx != '' and fy is not null and fy != '') ", " length(fullname) asc", p.x, p.y);
			if(stay_list.size() > 0){
				ri.setO(StringUtil.replaceBlank(JSONPR.list2json(stay_list, "fuid;fullname;address;type;fx;fy;dimages;supervisor;supervisorphone;phone;")));
				ri.setCode(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
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
			TempPoint p = getPositionForIP();
			List<IntegrateddataWithBLOBs> end_points = integrateddataDAO.SimpleIntegratedQueryByPage(1, 1, "fuid = '"+id+"'", null);
			if(end_points != null && end_points.size() == 1 && !StringUtils.isEmpty(end_points.get(0).getFx()) && !StringUtils.isEmpty(end_points.get(0).getFy())){
				//DataEntity start_point = start_points.get(0);
				//DataEntity end_point = end_points.get(0);
				GMPoint beginPoint = GMPoint.FromStringByComma(p.x+","+p.y);
		    	GMPoint endPoint = GMPoint.FromStringByComma(end_points.get(0).getFx()+","+ end_points.get(0).getFy());
		    	GMOptimalPath path = GMSearchFactory.GetOptimalPathStringByXY2(beginPoint.getX(), beginPoint.getY(), endPoint.getX(), endPoint.getY());
		    	Load load = new Load();
		    	load.setStart(p.x+","+p.y);
		    	load.setEnd(end_points.get(0).getFx()+","+end_points.get(0).getFy());
		    	load.setStart_name("当前位置");
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
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		ReturnInfo.printJson(request, response, ri, false);
		return null;
	}
	
	/**
	 * 热点推荐
	 * @return
	 */
	public String hotRecommended(){
		try {
			List<BuNews> keys = buNewsService.execSql("select * from bu_news where type like '004031%' AND deletemark = '0'");
			if(keys.size() > 0){
				ri.setO(JSONPR.list2json(keys, "fullname"));
				ri.setCode(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

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

	public String getFilter() {
		return filter;
	}

	public void setFilter(String filter) {
		this.filter = filter;
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

	public IntegrateddataDAO getIntegrateddataDAO() {
		return integrateddataDAO;
	}

	public void setIntegrateddataDAO(IntegrateddataDAO integrateddataDAO) {
		this.integrateddataDAO = integrateddataDAO;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}
}
