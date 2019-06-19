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
public interface  A20161activityService{ 

	public A20161activity findById(String id);
	
	public void save(A20161activity item);
	
	public void update(A20161activity item);
	
	public void updateSelective(A20161activity item);
	
	public void delete(String id);
	
    public void deleteByWhere(String swhere);
	
	List<A20161activity> execSql(String sql);
	
	public List<A20161activity> selectByPage(int pageIndex, int pageSize, String strWhere, String strOrderBy);
	
	public int getRecordCount(String strWhere);
	
}
