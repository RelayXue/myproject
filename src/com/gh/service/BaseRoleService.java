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
public interface  BaseRoleService{ 

	public BaseRole findById(String id);
	
	public void save(BaseRole item);
	
	public void update(BaseRole item);
	
	public void updateSelective(BaseRole item);
	
	public void delete(String id);
	
	List<BaseRole> execSql(String sql);
	
	public List<BaseRole> selectByPage(int pageIndex, int pageSize, String strWhere, String strOrderBy);
	
	public int getRecordCount(String strWhere);
	
}
