package com.gh.service;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import com.gh.common.common;
import com.gh.dao.BuWeatherDAO;
import com.gh.entity.BuWeather;

/**
 * @author xiaofeng
 * @version 1.0
 * @since 1.0
 */

public class BuWeatherServiceImpl implements BuWeatherService {
	private int tt = 0;
	private BuWeatherDAO buWeatherDAO;

	public void setBuWeatherDAO(BuWeatherDAO buWeatherDAO) {
		this.buWeatherDAO = buWeatherDAO;
	}

	public BuWeatherDAO getBuWeatherDAO() {
		return this.buWeatherDAO;
	}

	@Override
	public BuWeather findById(String id) {
		// TODO Auto-generated method stub
		return buWeatherDAO.selectByPrimaryKey(id);
	}

	@Override
	public void save(BuWeather item) {
		// TODO Auto-generated method stub
		buWeatherDAO.insert(item);
	}

	@Override
	public void update(BuWeather item) {
		// TODO Auto-generated method stub
		buWeatherDAO.updateByPrimaryKey(item);
	}

	@Override
	public void updateSelective(BuWeather item) {
		buWeatherDAO.updateByPrimaryKeySelective(item);

	}

	@Override
	public void delete(String id) {
		// TODO Auto-generated method stub
		buWeatherDAO.deleteByPrimaryKey(id);
	}

	@Override
	public void deleteByWhere(String swhere) {
		// TODO Auto-generated method stub
		buWeatherDAO.deleteByWhere(swhere);
	}

	@Override
	public List<BuWeather> execSql(String sql) {
		// TODO Auto-generated method stub
		return buWeatherDAO.execSql(sql);
	}

	@Override
	public List<BuWeather> selectByPage(int pageIndex, int pageSize, String strWhere, String strOrderBy) {
		String mainSql = "SELECT * FROM bu_weather";
		if (strWhere != null && strWhere.trim().length() > 0) {
			mainSql = mainSql + " WHERE " + strWhere;
		}
		if (strOrderBy != null && strOrderBy.trim().length() > 0) {
			mainSql = mainSql + " ORDER BY " + strOrderBy;
		}
		int size = (int) this.getRecordCount(strWhere);
		int totalsize = (size % pageSize == 0) ? (size / pageSize) : (size / pageSize + 1);
		int max = pageIndex > totalsize ? totalsize * pageSize : pageIndex * pageSize;
		int min = pageIndex == 1 ? 0 : pageIndex * pageSize;
		if (max <= min) {
			min = min - pageSize;
			pageIndex -= 1;
		}
		min = min > 0 ? min : 0;
		mainSql += "  LIMIT " + min + "," + pageSize;
		List<BuWeather> xList = this.execSql(mainSql);
		return xList;
	}

	/*
	 * @Override public List<BuWeather> selectByPage(int pageIndex, int
	 * pageSize,String strWhere, String strOrderBy) { String mainSql =
	 * "SELECT * FROM bu_weather"; if (strWhere != null &&
	 * strWhere.trim().length() > 0) { mainSql = mainSql + " WHERE " + strWhere;
	 * } if (strOrderBy != null && strOrderBy.trim().length() > 0) { mainSql =
	 * mainSql + " ORDER BY " + strOrderBy; } int size
	 * =(int)this.getRecordCount(strWhere); int totalsize = (size % pageSize ==
	 * 0) ? (size / pageSize) : (size / pageSize + 1); int max = pageIndex >
	 * totalsize ? totalsize * pageSize : pageIndex * pageSize; int min =
	 * pageIndex == 1 ? 1 : (pageIndex - 1) * pageSize + 1; if (max <= min) {
	 * min = min - pageSize; pageIndex -= 1; } String sql =
	 * "SELECT * FROM (SELECT t01.*, rownum as newRowNum FROM (" + mainSql +
	 * ") t01 WHERE rownum <='" + max + "' ) WHERE newRowNum >='" + min + "'";
	 * List<BuWeather> xList = this.execSql(sql); return xList; }
	 * 
	 * @Override public List<BuWeather> selectByPage(int pageIndex, int
	 * pageSize,String strWhere, String strOrderBy) { int min = pageIndex == 1 ?
	 * 0 : (pageIndex - 1) * pageSize; String _oB = ""; if (strOrderBy != null
	 * && strOrderBy.trim().length() > 0) { _oB = " ORDER BY " + strOrderBy; }
	 * String where="",where2=""; if (strWhere != null &&
	 * strWhere.trim().length() > 0) { where = " WHERE " + strWhere + " ";
	 * where2 = " AND " + strWhere; } String mainSql = "select top " + pageSize
	 * + " * from BuWeather  where fuid  not in (select top " + min +
	 * " fuid from BuWeather "+ where + _oB + ") " + where2 + _oB;
	 * List<BuWeather> xList = this.execSql(mainSql); return xList; }
	 */

	@Override
	public int getRecordCount(String strWhere) {

		String sql = "SELECT COUNT(*) FROM bu_weather";
		if (strWhere != null && strWhere.trim().length() > 0) {
			sql = sql + " WHERE " + strWhere;
		}
		Object obj = buWeatherDAO.selectObject(sql);
		if (obj != null) {
			return Integer.valueOf(buWeatherDAO.selectObject(sql).toString());
		}
		return 0;

	}

	/**
	 * @see 中国气象局天气预报服务
	 */
	public int getWeather() {
		String httpsUrl = "http://api.map.baidu.com/telematics/v3/weather?location=%E6%A1%90%E4%B9%A1&output=json&ak=mAiri7rBdiVGxmp95tDudHfP";
		//String httpsUrl = "http://m.weather.com.cn/atad/101210301.html";
		String data = "";
		for (int u = 0; u < 10; u++) {
			try {
				URL my_url = new URL(httpsUrl);
				BufferedReader br = new BufferedReader(new InputStreamReader(my_url.openStream(), "utf-8"));
				String strTemp = "";
				while (null != (strTemp = br.readLine())) {
					data += strTemp;
				}
				JSONObject node = JSONObject.fromObject(data);
				JSONArray jsons = JSONArray.fromObject(node.get("weatherinfo"));
				for (Object o : jsons) {
					JSONObject jsonNode = JSONObject.fromObject(o);
					BuWeather buWeather1 = buWeatherDAO.selectByPrimaryKey("1");
					buWeather1.setAllergy(jsonNode.getString("index_ag"));
					buWeather1.setCalendar(jsonNode.getString("date"));
					buWeather1.setCarwash(jsonNode.getString("index_xc"));
					buWeather1.setChanges(jsonNode.getString("weather1"));
					buWeather1.setClothes(jsonNode.getString("index_ls"));
					buWeather1.setComfort(jsonNode.getString("index_co"));
					buWeather1.setDescription(jsonNode.getString("img_title1"));
					buWeather1.setDressing(jsonNode.getString("index_d"));
					buWeather1.setImage(common.WeatherImg(jsonNode.getString("img_title1")));
					buWeather1.setTemperature(jsonNode.getString("temp1"));
					buWeather1.setTime(jsonNode.getString("date_y"));
					buWeather1.setTraveling(jsonNode.getString("index_tr"));
					buWeather1.setType(1);
					buWeather1.setUltravioletrays(jsonNode.getString("index_uv"));
					buWeather1.setWeek(jsonNode.getString("week"));
					buWeather1.setWind(jsonNode.getString("wind1"));
					buWeather1.setWindpower(jsonNode.getString("fl1"));
					buWeather1.setModifydate(new Date());
					buWeatherDAO.updateByPrimaryKey(buWeather1);
					BuWeather buWeather2 = buWeatherDAO.selectByPrimaryKey("2");
					buWeather2.setWind(jsonNode.getString("wind2"));
					buWeather2.setWindpower(jsonNode.getString("fl2"));
					buWeather2.setTemperature(jsonNode.getString("temp2"));
					buWeather2.setImage(common.WeatherImg(jsonNode.getString("img_title2")));
					buWeather2.setTemperature(jsonNode.getString("temp2"));
					buWeather2.setChanges(jsonNode.getString("weather2"));
					buWeather2.setDescription(jsonNode.getString("img_title2"));
					buWeather2.setType(2);
					buWeather2.setModifydate(new Date());
					buWeatherDAO.updateByPrimaryKey(buWeather2);
					BuWeather buWeather3 = buWeatherDAO.selectByPrimaryKey("3");
					buWeather3.setWind(jsonNode.getString("wind3"));
					buWeather3.setWindpower(jsonNode.getString("fl3"));
					buWeather3.setTemperature(jsonNode.getString("temp3"));
					buWeather3.setImage(common.WeatherImg(jsonNode.getString("img_title3")));
					buWeather3.setTemperature(jsonNode.getString("temp3"));
					buWeather3.setChanges(jsonNode.getString("weather3"));
					buWeather3.setDescription(jsonNode.getString("img_title3"));
					buWeather3.setType(3);
					buWeather3.setModifydate(new Date());
					buWeatherDAO.updateByPrimaryKey(buWeather3);
					BuWeather buWeather4 = buWeatherDAO.selectByPrimaryKey("4");
					buWeather4.setWind(jsonNode.getString("wind4"));
					buWeather4.setWindpower(jsonNode.getString("fl4"));
					buWeather4.setTemperature(jsonNode.getString("temp4"));
					buWeather4.setImage(common.WeatherImg(jsonNode.getString("img_title4")));
					buWeather4.setTemperature(jsonNode.getString("temp4"));
					buWeather4.setChanges(jsonNode.getString("weather4"));
					buWeather4.setDescription(jsonNode.getString("img_title4"));
					buWeather4.setType(4);
					buWeather4.setModifydate(new Date());
					buWeatherDAO.updateByPrimaryKey(buWeather4);
					BuWeather buWeather5 = buWeatherDAO.selectByPrimaryKey("5");
					buWeather5.setWind(jsonNode.getString("wind5"));
					buWeather5.setWindpower(jsonNode.getString("fl5"));
					buWeather5.setTemperature(jsonNode.getString("temp5"));
					buWeather5.setImage(common.WeatherImg(jsonNode.getString("img_title5")));
					buWeather5.setTemperature(jsonNode.getString("temp5"));
					buWeather5.setChanges(jsonNode.getString("weather5"));
					buWeather5.setDescription(jsonNode.getString("img_title5"));
					buWeather5.setType(5);
					buWeather5.setModifydate(new Date());
					buWeatherDAO.updateByPrimaryKey(buWeather5);
					BuWeather buWeather6 = buWeatherDAO.selectByPrimaryKey("6");
					buWeather6.setWind(jsonNode.getString("wind6"));
					buWeather6.setWindpower(jsonNode.getString("fl6"));
					buWeather6.setTemperature(jsonNode.getString("temp6"));
					buWeather6.setImage(common.WeatherImg(jsonNode.getString("img_title6")));
					buWeather6.setTemperature(jsonNode.getString("temp6"));
					buWeather6.setChanges(jsonNode.getString("weather6"));
					buWeather6.setDescription(jsonNode.getString("img_title6"));
					buWeather6.setType(6);
					buWeather6.setModifydate(new Date());
					buWeatherDAO.updateByPrimaryKey(buWeather6);
				}
				br.close();
				break;
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		return 1;
	}
	/**
	 * @throws UnsupportedEncodingException 
	 * @see 百度天气预报服务
	 */
	public void getBaiduWeather() throws UnsupportedEncodingException { 
		GetWeater("嘉兴");
	}
	// 获取天气信息
	public  String GetWeater(String city) {
		String buffstr = null;
		try {
			String xml = this.GetXmlCode(URLEncoder.encode(city, "utf-8")); // 设置输入城市的编码，以满足百度天气api需要
			buffstr = this.readStringXml(xml, city);// 调用xml解析函数
		} catch (Exception e) {
			e.printStackTrace();
		}
		return buffstr;
	}

	public String GetXmlCode(String city) throws UnsupportedEncodingException {
		String requestUrl = "http://api.map.baidu.com/telematics/v3/weather?location=" + city + "&output=xml&ak=mAiri7rBdiVGxmp95tDudHfP";
		StringBuffer buffer = null;
		try {
			// 建立连接
			URL url = new URL(requestUrl);
			HttpURLConnection httpUrlConn = (HttpURLConnection) url.openConnection();
			httpUrlConn.setDoInput(true);
			httpUrlConn.setRequestMethod("GET");
			// 获取输入流
			InputStream inputStream = httpUrlConn.getInputStream();
			InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "utf-8");
			BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
			// 读取返回结果
			buffer = new StringBuffer();
			String str = null;
			while ((str = bufferedReader.readLine()) != null) {
				buffer.append(str);
			}
			// 释放资源
			bufferedReader.close();
			inputStreamReader.close();
			inputStream.close();
			httpUrlConn.disconnect();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return buffer.toString(); // 返回获取的xml字符串
	}

	public String readStringXml(String xml, String ifcity) {
		StringBuffer buff = new StringBuffer(); // 用来拼接天气信息的
		Document doc = null;
		List listdate = null; // 用来存放日期
		List listday = null; // 用来存放白天图片路径信息
		List listweather = null;
		List listwind = null;
		List listtem = null;
		List listresults = null;
		List listdes = null;
		List listzs = null;
		List listtitle = null;
		List listtipt = null;
		try {
			// 读取并解析XML文档
			// 下面的是通过解析xml字符串的
			doc = DocumentHelper.parseText(xml); 		// 将字符串转为XML
			Element rootElt = doc.getRootElement(); 	// 获取根节点
			Iterator iter = rootElt.elementIterator("results"); // 获取根节点下的子节点results
			String status = rootElt.elementText("status"); 		// 获取状态，如果等于success,表示有数据
			if (!status.equals("success"))
				return "暂无数据"; // 如果不存在数据，直接返回
			String date = rootElt.elementText("date"); // 获取根节点下的，当天日期
			// 遍历results节点
			while (iter.hasNext()) {
				Element recordEle = (Element) iter.next();
				Iterator index = recordEle.elementIterator("index"); //
				// 遍历results节点下的index节点
				while (index.hasNext()) {
					Element itemEle = (Element) index.next();
					listtitle = itemEle.elements("title");
					listzs = itemEle.elements("zs");
					listdes = itemEle.elements("des");
					listtipt = itemEle.elements("tipt");
				}
				BuWeather buWeather=new BuWeather();
				//各类状态
				for (int i = 0; i < listtitle.size(); i++) {
					Element eletitle = (Element) listtitle.get(i);
					Element elezs = (Element) listzs.get(i);// ..
					if(eletitle.getText().equals("穿衣")){
						buWeather.setDressing(elezs.getText());
					}
					if(eletitle.getText().equals("洗车")){
						buWeather.setCarwash(elezs.getText());
					}
					if(eletitle.getText().equals("旅游")){
						buWeather.setTraveling(elezs.getText());
					}
					if(eletitle.getText().equals("感冒")){
						buWeather.setCold(elezs.getText());
					}
					if(eletitle.getText().equals("运动")){
						buWeather.setMovement(elezs.getText());
					}
					if(eletitle.getText().equals("紫外线强度")){
						buWeather.setUltravioletrays(elezs.getText());
					}
					buff.append("======" + eletitle.getText() + "==" + elezs.getText() + "\n"); 
				}
				//各类指数
				for (int i = 0; i < listtipt.size(); i++) {
					Element eletipt = (Element) listtipt.get(i);
					Element eledes = (Element) listdes.get(i);
					if(eletipt.getText().equals("穿衣指数")){
						buWeather.setDressingst(eledes.getText());
					}
					if(eletipt.getText().equals("洗车指数")){
						buWeather.setCarwashindex(eledes.getText());
					}
					if(eletipt.getText().equals("旅游指数")){
						buWeather.setTravelingst(eledes.getText());
					}
					if(eletipt.getText().equals("感冒指数")){
						buWeather.setColdst(eledes.getText());
					}
					if(eletipt.getText().equals("运动指数")){
						buWeather.setMovementst(eledes.getText());
					}
					if(eletipt.getText().equals("紫外线强度指数")){
						buWeather.setUltravioletraysst(eledes.getText());
					}
					buff.append("======" + eletipt.getText() + "==" + eledes.getText() + "\n");  
				}

				Iterator iters = recordEle.elementIterator("weather_data"); //
				// 遍历results节点下的weather_data节点
				while (iters.hasNext()) {
					Element itemEle = (Element) iters.next();
					listdate = itemEle.elements("date");
					listday = itemEle.elements("dayPictureUrl");
					listweather = itemEle.elements("weather");
					listresults = itemEle.elements("results");
					listwind = itemEle.elements("wind");
					listtem = itemEle.elements("temperature");
				}
				//具体天气状况
				for (int i = 0; i < listdate.size(); i++) {  
					Element eledate = (Element) listdate.get(i);  
					Element eleday = (Element) listday.get(i); 
					Element eleweather = (Element) listweather.get(i);
					Element elewind = (Element) listwind.get(i);
					Element eletem = (Element) listtem.get(i);
					if(i!=0){
						buWeather=new BuWeather();
					}
					buWeather.setFuid((i+1)+"");
					buWeather.setTemperature(eletem.getText());
					buWeather.setWind(elewind.getText());
					buWeather.setChanges(eleweather.getText());
					buWeather.setImage(common.WeatherImg(eleweather.getText()));
					String nowdate[]=eledate.getText().split(" ");
					if(nowdate!=null&&nowdate.length>2){
							buWeather.setWeek(nowdate[0]);
							buWeather.setTime(nowdate[1]);
							//提前数字温度
							if(nowdate[2]!=null&&nowdate[2].length()>0){
								String regEx="[^0-9]";   
								Pattern p = Pattern.compile(regEx);   
								Matcher m = p.matcher(nowdate[2]);   
								buWeather.setTemperaturenow(m.replaceAll("").trim());
							}
					}
					if(i!=0){
						buWeather.setWeek(eledate.getText());
						 Date d=new Date();   
						 SimpleDateFormat df=new SimpleDateFormat("MM.dd");   
						 buWeather.setTime(df.format(new Date(d.getTime() + (long)i * 24 * 60 * 60 * 1000)));
					}
					buWeather.setModifydate(new Date());
					buWeatherDAO.updateByPrimaryKey(buWeather);
					buff.append(eledate.getText() + "===" + eleweather.getText() + "===" + elewind.getText() + "===" + eletem.getText() + "\n"); // 拼接信息
				}
			}
		} catch (DocumentException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return buff.toString();
	}
	public static void main(String[] args) {
		// 测试
		BuWeatherServiceImpl tt=new BuWeatherServiceImpl();
		System.out.println(tt.GetWeater("嘉兴").toString());
	}
}
