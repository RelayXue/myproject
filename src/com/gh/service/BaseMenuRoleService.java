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
public interface  BaseMenuRoleService{ 

	public BaseMenuRole findById(String id);
	
	public void save(BaseMenuRole item);
	
	public void update(BaseMenuRole item);
	
	public void updateSelective(BaseMenuRole item);
	
	public void delete(String id);
	
	List<BaseMenuRole> execSql(String sql);
	
	public List<BaseMenuRole> selectByPage(int pageIndex, int pageSize, String strWhere, String strOrderBy);
	
	public int getRecordCount(String strWhere);
	
}
