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
public interface  A20161clickService{ 

	public A20161click findById(String id);
	
	public void save(A20161click item);
	
	public void update(A20161click item);
	
	public void updateSelective(A20161click item);
	
	public void delete(String id);
	
    public void deleteByWhere(String swhere);
	
	List<A20161click> execSql(String sql);
	
	public List<A20161click> selectByPage(int pageIndex, int pageSize, String strWhere, String strOrderBy);
	
	public int getRecordCount(String strWhere);
	
}
