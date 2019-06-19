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
public interface  BuStreetlightsService{ 

	public BuStreetlights findById(String id);
	
	public void save(BuStreetlights item);
	
	public void update(BuStreetlights item);
	
	public void updateSelective(BuStreetlights item);
	
	public void delete(String id);
	
    public void deleteByWhere(String swhere);
	
	List<BuStreetlights> execSql(String sql);
	
	public List<BuStreetlights> selectByPage(int pageIndex, int pageSize, String strWhere, String strOrderBy);
	
	public int getRecordCount(String strWhere);
	
}
