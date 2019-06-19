package com.gh.phone;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;

import com.gh.action.system.CompetenceManager;
import com.gh.base.Action;
import com.gh.common.UUIDCreater;
import com.gh.dao.IntegrateddataDAO;
import com.gh.entity.BaseDatadictionary;
import com.gh.entity.BaseMenu;
import com.gh.entity.BaseMenuRole;
import com.gh.entity.BaseUser;
import com.gh.entity.BaseUserRole;
import com.gh.entity.BuAffairs;
import com.gh.entity.BuDining;
import com.gh.entity.BuEntertainmentshopping;
import com.gh.entity.BuLoglog;
import com.gh.entity.BuNews;
import com.gh.entity.BuPersonnel;
import com.gh.entity.BuScanning;
import com.gh.entity.BuStay;
import com.gh.entity.IntegrateddataWithBLOBs;
import com.gh.service.BaseDatadictionaryService;
import com.gh.service.BaseMenuRoleService;
import com.gh.service.BaseMenuService;
import com.gh.service.BaseUserRoleService;
import com.gh.service.BaseUserService;
import com.gh.service.BuAffairsService;
import com.gh.service.BuDiningService;
import com.gh.service.BuEntertainmentshoppingService;
import com.gh.service.BuLoglogService;
import com.gh.service.BuNewsService;
import com.gh.service.BuPersonnelService;
import com.gh.service.BuScanningService;
import com.gh.service.BuStayService;
import com.gh.system.lucene.GPSRouteFactory;
import com.gh.system.lucene.GPSRouteModel;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * 客户端业务逻辑入口
 * @author wjc
 *
 */
public class PhoneAction extends Action {
	
	//dao
	BaseMenuService menu_service;
	BaseMenuRoleService menu_role_service;
	BaseUserRoleService user_role_service;
	BuAffairsService affairs_service;
	BuNewsService news_service;
	BaseDatadictionaryService dictionary_service;
	BuStayService stay_service;
	BuDiningService dining_service;
	BuEntertainmentshoppingService shopping_service;
	BuLoglogService loglog_service;
	BuScanningService scanning_service;
	BuPersonnelService personnel_service;
	IntegrateddataDAO integrateddataDAO;
	
	//req 
	String ident;
	String id;
	String qid;
	String account;
	String password;
	String category;
	String description;
	String name;
	String before_images;
	String after_images;
	String after_description;
	String json_str;
	String store_id;
	
	//rep
	ReturnInfo ri = new ReturnInfo();
	
	public String forIdent(){
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.registerTypeAdapter(Object.class, new NaturalDeserializer());
		Gson gson = gsonBuilder.create();
		
		try {
			System.out.println("【IDENT】"+ident);
			if("LOGIN".equals(ident)){	// 用户登录
				List<BuPersonnel> users = personnel_service.execSql("select * from BU_PERSONNEL where USERNAME = '"+account+"' AND USERPASSWORD = '"+password+"' AND DELETEMARK = '0'");
				if(users.size() > 0){
					BuPersonnel user = users.get(0);
					//用户基本信息
					ri.getOs().put("user", gson.toJson(user));
					/*
					//用户菜单权限
					List<BaseMenu> parent_menu = menu_service.execSql("select * from BASE_MENU where MENU_NAME = '客户端菜单'");
					List<BaseUserRole> user_role = user_role_service.execSql("select * from BASE_USER_ROLE where USERID = '"+user.getFuid()+"'");
					if(parent_menu.size() > 0){
						List<BaseMenu> menus = menu_service.execSql("select * from BASE_MENU where MENU_PARENTID = '"+parent_menu.get(0).getFuid()+"'");
						String all_menu_ids = "";
						for(BaseMenu bm : menus) { all_menu_ids += ",'"+bm.getFuid()+"'"; }
						if(all_menu_ids.length() > 0){
							all_menu_ids = all_menu_ids.substring(1);
							List<BaseMenuRole> menu_roles = menu_role_service.execSql("select *,count(distinct menu_id) menu_id from BASE_MENU_ROLE where MENU_ID IN ("+all_menu_ids+") AND ROLE_ID = '"+user_role.get(0).getRoleid()+"' group by menu_id");
							String menu_ids = "";
							for(BaseMenuRole bmr : menu_roles){
								menu_ids += "," + bmr.getMenuId();
							}
							if(menu_ids.length() > 0) {
								menu_ids = menu_ids.substring(1);
								ri.getOs().put("menu", menu_ids);
							}
						}
					}
					*/
					
					//读取数据字典
					List<BaseDatadictionary> dictionary_list = dictionary_service.execSql("select * from BASE_DATADICTIONARY");
					Map<String, String> dictionary =  new HashMap<String, String>();
					for(BaseDatadictionary bd : dictionary_list){
						dictionary.put(bd.getCode(), bd.getFullname());
					}
					ri.getOs().put("dictionary", dictionary);
					
					//插入登录日志
					String today = TimeUtils.SIMPLE_DATE_FORMAT_A.format(new Date());
					List<BuLoglog> logs = loglog_service.execSql("select * from bu_loglog where perid = '"+user.getFuid()+"' and date_format(createdate,'%Y-%m-%d') = '"+today+"' ");
					if(logs.size() == 1){
						loglog_service.execUpdateSql("update bu_loglog set LOGINGTIME = '"+TimeUtils.SIMPLE_DATE_FORMAT.format(new Date())+"' where date_format(CREATEDATE,'%Y-%m-%d')='"+today+"'; ");
					}else{
						BuLoglog new_log = new BuLoglog();
						new_log.setFuid(UUIDCreater.getUUID());
						new_log.setRealname(user.getRealname());
						new_log.setMobile(user.getMobile());
						new_log.setPerid(user.getFuid());
						new_log.setLogingtime(new Date());
						new_log.setOuttime(null);
						new_log.setCreatedate(new Date());
						
						loglog_service.save(new_log);
					}
					ri.setCode(1);
				}else{
					ri.setCode(0);
					ri.setHint("用户名或密码错误, 请重试");
				}
			}else if("LOGIN_OUT".equals(ident)){	//登出
				BuPersonnel user = personnel_service.findById(id);
				if(user != null){
					String today = TimeUtils.SIMPLE_DATE_FORMAT_A.format(new Date());
					List<BuLoglog> logs = loglog_service.execSql("select * from bu_loglog where perid = '"+user.getFuid()+"' and date_format(createdate,'%Y-%m-%d')='"+today+"' ");
					if(logs.size() == 1){
						loglog_service.execUpdateSql("update bu_loglog set OUTTIME = '"+TimeUtils.SIMPLE_DATE_FORMAT.format(new Date())+"' where date_format(CREATEDATE,'%Y-%m-%d')='"+today+"';");
					}else{
						BuLoglog new_log = new BuLoglog();
						new_log.setFuid(UUIDCreater.getUUID());
						new_log.setRealname(user.getRealname());
						new_log.setMobile(user.getMobile());
						new_log.setPerid(user.getFuid());
						new_log.setLogingtime(TimeUtils.getDateDay(new Date()));
						new_log.setOuttime(new Date());
						new_log.setCreatedate(new Date());
						
						loglog_service.save(new_log);
					}
				}
			}else if("POSITION_UPLOAD".equals(ident)){	//位置上传
				System.out.println(json_str);
				if(!StringUtils.isEmpty(json_str)){
					Position po = gson.fromJson(json_str, Position.class);
					GPSRouteModel gpsrm = new GPSRouteModel();
					gpsrm.setCreateTime(po.getCreateTime());
					gpsrm.setDirection(po.getDirection());
					gpsrm.setPhone(po.getPhone());
					gpsrm.setPosX(po.getPosX());
					gpsrm.setPosY(po.getPosY());
					gpsrm.setRequestTime(po.getRequestTime());
					gpsrm.setSpeed(po.getSpeed());
					gpsrm.setUserId(po.getUserId());
					GPSRouteFactory.getSingle().Insert(gpsrm);
					ri.setCode(1);
				}
			}else if("AFFAIR_UPLOAD".equals(ident)){	//事务上传
				String today = TimeUtils.SIMPLE_DATE_FORMAT_A.format(new Date());
				int count = affairs_service.execSql("select * from BU_AFFAIRS where date_format(CREATEDATE,'%Y-%m-%d') = '"+today+"'").size();
				String num = ""+(count+1);
				if(count <= 8){ num = "00"+(count+1); }
				else if(count <= 98){ num = "0"+(count+1); }
				BuAffairs affairs = new BuAffairs();
				affairs.setFuid(UUIDCreater.getUUID());
				affairs.setCode(TimeUtils.SHORT_DATE_FORMAT.format(new Date()).substring(2)+num);
				affairs.setType(category);
				affairs.setDescription(description);
				affairs.setReported(name);
				affairs.setReportedid(id);
				
				affairs.setBeforeimage(getImageName(before_images));
				affairs.setAfterimage(getImageName(after_images));
				if(!StringUtils.isEmpty(affairs.getAfterimage())) {	//如果处理后照片也存在的话 直接设置完结
					affairs.setStatus("2");
				}else{
					affairs.setStatus("1");
				}
				affairs.setAfterexplain(after_description);
				affairs.setDeletemark(0);
				affairs.setCreatedate(new Date());
				affairs.setModifydate(new Date());
				affairs_service.save(affairs);
				
				ri.setCode(1);
				ri.setHint("事务提交成功!");
			}else if("AFFAIR_LIST_READ".equals(ident)){	//事务列表读取
				List<BuAffairs> affairs_list = affairs_service.selectByPage(indexPage, 10, " REPORTEDID = '"+id+"' ", "CREATEDATE desc");
				ri.setCode(1);
				ri.setO(gson.toJson(affairs_list));
			}else if("AFFAIR_DETAIL_READ".equals(ident)){	//事务详细读取
				
			}else if("QR_READ".equals(ident)){	//二维码扫描
				BuPersonnel user = personnel_service.findById(id);
				if(user != null){
					List<IntegrateddataWithBLOBs> store = integrateddataDAO.SimpleIntegratedQueryByPage(1, 1, " QID = '"+qid+"' ", null);
					if(store != null && store.size() > 0){
						String category = store.get(0).getType();
						String store_id = store.get(0).getFuid();
						if(category.length() > 6){ category = category.substring(0, 6); }
						ri.getOs().put("category", category);
						if("002015".equals(category) || "002016".equals(category) || "002017".equals(category) || "002018".equals(category)){
							if("002017".equals(category)){
								BuStay stay = stay_service.findById(store_id);
								if(stay != null){
									saveScanningLog(user, stay.getType(), stay.getFuid());
									ri.setCode(1);
									ri.setO(gson.toJson(stay));
								}else{
									ri.setHint("查询失败, 该商铺不存在或已注销");
								}
							}else if("002018".equals(category)){
								BuDining dining = dining_service.findById(store_id);
								if(dining != null){
									saveScanningLog(user, dining.getType(), dining.getFuid());
									ri.setCode(1);
									ri.setO(gson.toJson(dining));
								}else{
									ri.setHint("查询失败, 该商铺不存在或已注销");
								}
							}else if("002015".equals(category) || "002016".equals(category)){
								BuEntertainmentshopping shopping = shopping_service.findById(store_id);
								if(shopping != null){
									saveScanningLog(user, shopping.getType(), shopping.getFuid());
									ri.setCode(1);
									ri.setO(gson.toJson(shopping));
								}else{
									ri.setHint("查询失败, 该商铺不存在或已注销");
								}
							}
						}else{
							ri.setHint("不能维护的商铺类型!");
						}
					}else{
						ri.setHint("该商铺不存在!");
					}
				}else{
					ri.setHint("无权限!");
				}
			}else if("NEWS_LIST_READ".equals(ident)){	//新闻列表读取
				List<BuNews> affairs_list = news_service.selectByPage(indexPage, 10, " TYPE = '004006' ", "CREATEDATE desc");
				ri.setCode(1);
				ri.setO(JSONPR.list2json(affairs_list, "fuid;fullname;createdate;"));
			}else if("STORE_UPDATE".equals(ident)){	//商铺修改 category表示修改类别 1住宿 2餐饮 3购物娱乐
				try {
					System.out.println("category="+category);
					System.out.println(json_str);
					switch(StringUtil.toInt(category)){
					case 1:
						BuStay bs = gson.fromJson(json_str, BuStay.class);
						bs.setDimages(getImageName(bs.getDimages()));
						bs.setModifydate(new Date());
						stay_service.updateSelective(bs);
						break;
					case 2:
						BuDining dinning = gson.fromJson(json_str, BuDining.class);
						dinning.setDimages(getImageName(dinning.getDimages()));
						dinning.setModifydate(new Date());
						dining_service.updateSelective(dinning);
						break;
					case 3:
						BuEntertainmentshopping shopping = gson.fromJson(json_str, BuEntertainmentshopping.class);
						shopping.setDimages(getImageName(shopping.getDimages()));
						shopping.setModifydate(new Date());
						shopping_service.updateSelective(shopping);
						break;
					}
					ri.setCode(1);
					ri.setHint("信息已更新!");
				} catch (Exception e) {
					e.printStackTrace();
					ri.setCode(0);
					ri.setHint("参数错误!");
				}
			}
			
			ReturnInfo.printJson(request, response, ri, false);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}
	
	/**
	 * 保存扫码记录
	 * @param user
	 * @param category
	 * @param store_id
	 */
	public void saveScanningLog(BuPersonnel user, String category, String store_id){
		BuScanning new_scanning = new BuScanning();
		new_scanning.setFuid(UUIDCreater.getUUID());
		new_scanning.setRealname(user.getRealname());
		new_scanning.setMobile(user.getMobile());
		new_scanning.setCreatedate(new Date());
		new_scanning.setPerid(user.getFuid());
		new_scanning.setScanningtime(new Date());
		new_scanning.setShopsid(store_id);
		new_scanning.setType(category);
		
		scanning_service.save(new_scanning);
	}
	
	/**
	 * 读取上传的图片 返回文件名
	 * @param pictures
	 * @return
	 */
	public static String getImageName(String pictures){
		String pics_name = "";
		//保存图片
		if(pictures != null){
			pictures = pictures.replaceAll("data:image/jpeg;base64,", "");
			if(pictures.indexOf(",") >= 0){
				String[] pic_rr = pictures.split(",");
				for (String pic : pic_rr) {
					pics_name += "," + getImageName(pic);
				}
				pics_name = pics_name.substring(1);
			}else{
				if(pictures.length() > 300){
					pics_name = TimeUtils.LONG_SIMPLE_DATE_FORMAT.format(new Date()) + UUIDCreater.getUUID().substring(0,3);
					if(pictures.indexOf("data:image/jpeg;base64,") >= 0){
						pictures = pictures.substring(23);
					}
					
					String o_name = "B_"+pics_name + ".jpg";
					ImageBASE64ED.getPicFormatBASE64(pictures, CompetenceManager.IMAGE_PATH + o_name);	//保存原图
					
					//压缩略缩图
					ImageCompress mypic = new ImageCompress();
					mypic.compressPic(CompetenceManager.IMAGE_PATH, CompetenceManager.IMAGE_PATH, o_name, "L_"+pics_name + ".jpg", 300, 300, true);
					
					pics_name += ".jpg";
				}else{
					pics_name = FileUtils.getFileName(pictures);
				}
			}
		}
		return pics_name;
	}


	public String getIdent() {
		return ident;
	}


	public void setIdent(String ident) {
		this.ident = ident;
	}


	public ReturnInfo getRi() {
		return ri;
	}


	public void setRi(ReturnInfo ri) {
		this.ri = ri;
	}

	public BaseMenuService getMenu_service() {
		return menu_service;
	}


	public void setMenu_service(BaseMenuService menu_service) {
		this.menu_service = menu_service;
	}


	public BaseMenuRoleService getMenu_role_service() {
		return menu_role_service;
	}


	public void setMenu_role_service(BaseMenuRoleService menu_role_service) {
		this.menu_role_service = menu_role_service;
	}


	public BaseUserRoleService getUser_role_service() {
		return user_role_service;
	}


	public void setUser_role_service(BaseUserRoleService user_role_service) {
		this.user_role_service = user_role_service;
	}


	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public String getAccount() {
		return account;
	}


	public void setAccount(String account) {
		this.account = account;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}

	public BuAffairsService getAffairs_service() {
		return affairs_service;
	}

	public void setAffairs_service(BuAffairsService affairs_service) {
		this.affairs_service = affairs_service;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBefore_images() {
		return before_images;
	}

	public void setBefore_images(String before_images) {
		this.before_images = before_images;
	}

	public String getAfter_images() {
		return after_images;
	}

	public void setAfter_images(String after_images) {
		this.after_images = after_images;
	}

	public String getAfter_description() {
		return after_description;
	}

	public void setAfter_description(String after_description) {
		this.after_description = after_description;
	}

	public BuNewsService getNews_service() {
		return news_service;
	}

	public void setNews_service(BuNewsService news_service) {
		this.news_service = news_service;
	}

	public BaseDatadictionaryService getDictionary_service() {
		return dictionary_service;
	}

	public void setDictionary_service(BaseDatadictionaryService dictionary_service) {
		this.dictionary_service = dictionary_service;
	}

	public BuStayService getStay_service() {
		return stay_service;
	}

	public void setStay_service(BuStayService stay_service) {
		this.stay_service = stay_service;
	}

	public String getJson_str() {
		return json_str;
	}

	public void setJson_str(String json_str) {
		this.json_str = json_str;
	}

	public BuDiningService getDining_service() {
		return dining_service;
	}

	public void setDining_service(BuDiningService dining_service) {
		this.dining_service = dining_service;
	}

	public BuEntertainmentshoppingService getShopping_service() {
		return shopping_service;
	}

	public void setShopping_service(BuEntertainmentshoppingService shopping_service) {
		this.shopping_service = shopping_service;
	}

	public BuLoglogService getLoglog_service() {
		return loglog_service;
	}

	public void setLoglog_service(BuLoglogService loglog_service) {
		this.loglog_service = loglog_service;
	}

	public BuScanningService getScanning_service() {
		return scanning_service;
	}

	public void setScanning_service(BuScanningService scanning_service) {
		this.scanning_service = scanning_service;
	}

	public String getStore_id() {
		return store_id;
	}

	public void setStore_id(String store_id) {
		this.store_id = store_id;
	}
	public BuPersonnelService getPersonnel_service() {
		return personnel_service;
	}
	public void setPersonnel_service(BuPersonnelService personnel_service) {
		this.personnel_service = personnel_service;
	}

	public IntegrateddataDAO getIntegrateddataDAO() {
		return integrateddataDAO;
	}

	public void setIntegrateddataDAO(IntegrateddataDAO integrateddataDAO) {
		this.integrateddataDAO = integrateddataDAO;
	}

	public String getQid() {
		return qid;
	}

	public void setQid(String qid) {
		this.qid = qid;
	}
}
