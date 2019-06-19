package com.gh.service;

import java.util.List;
import org.springframework.stereotype.Service;



import java.util.*;
  
 

import com.gh.entity.*;
import com.gh.dao.*;
import com.gh.service.*;  

/**
 * @author xiaofeng
 * @version 1.0
 * @since 1.0
 */

@Service
public interface  BuWeatherService{ 

	public BuWeather findById(String id);
	
	public void save(BuWeather item);
	
	public void update(BuWeather item);
	
	public void updateSelective(BuWeather item);
	
	public void delete(String id);
	
    public void deleteByWhere(String swhere);
	
	List<BuWeather> execSql(String sql);
	
	public List<BuWeather> selectByPage(int pageIndex, int pageSize, String strWhere, String strOrderBy);
	
	public int getRecordCount(String strWhere);
	
}
