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
public interface  BuNewsService{ 

	public BuNews findById(String id);
	
	public void save(BuNews item);
	
	public void update(BuNews item);
	
	public void updateSelective(BuNews item);
	
	public void delete(String id);
	
    public void deleteByWhere(String swhere);
	
	List<BuNews> execSql(String sql);
	
	public List<BuNews> selectByPage(int pageIndex, int pageSize, String strWhere, String strOrderBy);
	
	public int getRecordCount(String strWhere);
	
	public int SumFieldForPraise(String where);
	
	public int SumFieldForReadNum(String where);
}
