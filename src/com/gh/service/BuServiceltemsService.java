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
public interface  BuServiceltemsService{ 

	public BuServiceltems findById(String id);
	
	public void save(BuServiceltems item);
	
	public void update(BuServiceltems item);
	
	public void updateSelective(BuServiceltems item);
	
	public void delete(String id);
	
    public void deleteByWhere(String swhere);
	
	List<BuServiceltems> execSql(String sql);
	
	public List<BuServiceltems> selectByPage(int pageIndex, int pageSize, String strWhere, String strOrderBy);
	
	public int getRecordCount(String strWhere);
	
}
