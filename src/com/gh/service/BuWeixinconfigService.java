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
public interface  BuWeixinconfigService{ 

	public BuWeixinconfig findById(String id);
	
	public void save(BuWeixinconfig item);
	
	public void update(BuWeixinconfig item);
	
	public void updateSelective(BuWeixinconfig item);
	
	public void delete(String id);
	
    public void deleteByWhere(String swhere);
	
	List<BuWeixinconfig> execSql(String sql);
	
	public List<BuWeixinconfig> selectByPage(int pageIndex, int pageSize, String strWhere, String strOrderBy);
	
	public int getRecordCount(String strWhere);
	
}
