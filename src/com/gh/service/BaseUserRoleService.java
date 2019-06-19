package com.gh.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gh.entity.BaseUser;



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
public interface  BaseUserRoleService{ 

	public BaseUserRole findById(String id);
	
	public void save(BaseUserRole item);
	
	public void update(BaseUserRole item);
	
	public void updateSelective(BaseUserRole item);
	
	public void delete(String id);
	
	List<BaseUserRole> execSql(String sql);
	
	public List<BaseUserRole> selectByPage(int pageIndex, int pageSize, String strWhere, String strOrderBy);
	
	public int getRecordCount(String strWhere);
	
	public List<BaseUserRole> selectView(String sql); 
	
}
