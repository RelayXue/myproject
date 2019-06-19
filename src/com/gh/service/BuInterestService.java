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
public interface  BuInterestService{ 

	public BuInterest findById(String id);
	
	public void save(BuInterest item);
	
	public void update(BuInterest item);
	
	public void updateSelective(BuInterest item);
	
	public void delete(String id);
	
    public void deleteByWhere(String swhere);
	
	List<BuInterest> execSql(String sql);
	
	public List<BuInterest> selectByPage(int pageIndex, int pageSize, String strWhere, String strOrderBy);
	
	public int getRecordCount(String strWhere);
	
	public List<BuInterest> execSqlCount(String sql);
}
