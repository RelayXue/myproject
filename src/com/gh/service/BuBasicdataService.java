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
public interface  BuBasicdataService{ 

	public BuBasicdata findById(String id);
	
	public void save(BuBasicdata item);
	
	public void update(BuBasicdata item);
	
	public void updateSelective(BuBasicdata item);
	
	public void delete(String id);
	
    public void deleteByWhere(String swhere);
	
	List<BuBasicdata> execSql(String sql);
	
	public List<BuBasicdata> selectByPage(int pageIndex, int pageSize, String strWhere, String strOrderBy);
	
	public int getRecordCount(String strWhere);
	
}
