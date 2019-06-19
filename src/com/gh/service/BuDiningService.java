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
public interface  BuDiningService{ 

	public BuDining findById(String id);
	
	public void save(BuDining item);
	
	public void update(BuDining item);
	
	public void updateSelective(BuDining item);
	
	public void delete(String id);
	
    public void deleteByWhere(String swhere);
	
	List<BuDining> execSql(String sql);
	
	public List<BuDining> selectByPage(int pageIndex, int pageSize, String strWhere, String strOrderBy);
	
	public int getRecordCount(String strWhere);
	
}
