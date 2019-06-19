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
public interface  BaseUserService{ 

	public BaseUser findById(String id);
	
	public void save(BaseUser item);
	
	public void update(BaseUser item);
	
	public void updateSelective(BaseUser item);
	
	public void delete(String id);
	
	List<BaseUser> execSql(String sql);
	
	public List<BaseUser> selectByPage(int pageIndex, int pageSize, String strWhere, String strOrderBy);
	
	public int getRecordCount(String strWhere);
	
}
