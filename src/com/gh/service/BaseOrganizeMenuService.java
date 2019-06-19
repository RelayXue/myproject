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
public interface  BaseOrganizeMenuService{ 

	public BaseOrganizeMenu findById(String id);
	
	public void save(BaseOrganizeMenu item);
	
	public void update(BaseOrganizeMenu item);
	
	public void updateSelective(BaseOrganizeMenu item);
	
	public void delete(String id);
	
	List<BaseOrganizeMenu> execSql(String sql);
	
	public List<BaseOrganizeMenu> selectByPage(int pageIndex, int pageSize, String strWhere, String strOrderBy);
	
	public int getRecordCount(String strWhere);
	
}
