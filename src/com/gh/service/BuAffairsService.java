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
public interface  BuAffairsService{ 

	public BuAffairs findById(String id);
	
	public void save(BuAffairs item);
	
	public void update(BuAffairs item);
	
	public void updateSelective(BuAffairs item);
	
	public void delete(String id);
	
    public void deleteByWhere(String swhere);
	
	List<BuAffairs> execSql(String sql);
	
	public List<BuAffairs> selectByPage(int pageIndex, int pageSize, String strWhere, String strOrderBy);
	
	public int getRecordCount(String strWhere);
	
}
