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
public interface  BuGeographyService{ 

	public BuGeography findById(String id);
	
	public void save(BuGeography item);
	
	public void update(BuGeography item);
	
	public void updateSelective(BuGeography item);
	
	public void delete(String id);
	
    public void deleteByWhere(String swhere);
	
	List<BuGeography> execSql(String sql);
	
	public List<BuGeography> selectByPage(int pageIndex, int pageSize, String strWhere, String strOrderBy);
	
	public int getRecordCount(String strWhere);
	
}
