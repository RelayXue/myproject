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
public interface  BuNavigationService{ 

	public BuNavigation findById(String id);
	
	public void save(BuNavigation item);
	
	public void update(BuNavigation item);
	
	public void updateSelective(BuNavigation item);
	
	public void delete(String id);
	
    public void deleteByWhere(String swhere);
	
	List<BuNavigation> execSql(String sql);
	
	public List<BuNavigation> selectByPage(int pageIndex, int pageSize, String strWhere, String strOrderBy);
	
	public int getRecordCount(String strWhere);
	
}
