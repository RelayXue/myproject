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
public interface  BuQuicklinksService{ 

	public BuQuicklinks findById(String id);
	
	public void save(BuQuicklinks item);
	
	public void update(BuQuicklinks item);
	
	public void updateSelective(BuQuicklinks item);
	
	public void delete(String id);
	
    public void deleteByWhere(String swhere);
	
	List<BuQuicklinks> execSql(String sql);
	
	public List<BuQuicklinks> selectByPage(int pageIndex, int pageSize, String strWhere, String strOrderBy);
	
	public int getRecordCount(String strWhere);
	
}
