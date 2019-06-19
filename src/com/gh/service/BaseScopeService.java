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
public interface  BaseScopeService{ 

	public BaseScope findById(String id);
	
	public void save(BaseScope item);
	
	public void update(BaseScope item);
	
	public void updateSelective(BaseScope item);
	
	public void delete(String id);
	
	List<BaseScope> execSql(String sql);
	
	public List<BaseScope> selectByPage(int pageIndex, int pageSize, String strWhere, String strOrderBy);
	
	public int getRecordCount(String strWhere);
	
}
