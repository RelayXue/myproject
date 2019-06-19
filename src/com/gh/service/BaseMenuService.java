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
public interface  BaseMenuService{ 

	public BaseMenu findById(String id);
	
	public void save(BaseMenu item);
	
	public void update(BaseMenu item);
	
	public void updateSelective(BaseMenu item);
	
	public void delete(String id);
	
	List<BaseMenu> execSql(String sql);
	
	public List<BaseMenu> selectByPage(int pageIndex, int pageSize, String strWhere, String strOrderBy);
	
	public int getRecordCount(String strWhere);
	
}
