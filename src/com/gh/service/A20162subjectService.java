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
public interface  A20162subjectService{ 

	public A20162subject findById(String id);
	
	public void save(A20162subject item);
	
	public void update(A20162subject item);
	
	public void updateSelective(A20162subject item);
	
	public void delete(String id);
	
    public void deleteByWhere(String swhere);
	
	List<A20162subject> execSql(String sql);
	
	public List<A20162subject> selectByPage(int pageIndex, int pageSize, String strWhere, String strOrderBy);
	
	public int getRecordCount(String strWhere);
	
}
