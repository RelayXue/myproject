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
public interface  BuStayService{ 

	public BuStay findById(String id);
	
	public void save(BuStay item);
	
	public void update(BuStay item);
	
	public void updateSelective(BuStay item);
	
	public void delete(String id);
	
    public void deleteByWhere(String swhere);
	
	List<BuStay> execSql(String sql);
	
	public List<BuStay> selectByPage(int pageIndex, int pageSize, String strWhere, String strOrderBy);
	
	public int getRecordCount(String strWhere);
	
	public int getDataCount(String strWhere);
}
