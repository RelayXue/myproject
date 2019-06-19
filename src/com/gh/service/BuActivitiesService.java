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
public interface  BuActivitiesService{ 

	public BuActivities findById(String id);
	
	public void save(BuActivities item);
	
	public void update(BuActivities item);
	
	public void updateSelective(BuActivities item);
	
	public void delete(String id);
	
    public void deleteByWhere(String swhere);
	
	List<BuActivities> execSql(String sql);
	
	public List<BuActivities> selectByPage(int pageIndex, int pageSize, String strWhere, String strOrderBy);
	
	public int getRecordCount(String strWhere);
	
}
