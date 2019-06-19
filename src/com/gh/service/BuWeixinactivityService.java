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
public interface  BuWeixinactivityService{ 

	public BuWeixinactivity findById(String id);
	
	public void save(BuWeixinactivity item);
	
	public void update(BuWeixinactivity item);
	
	public void updateSelective(BuWeixinactivity item);
	
	public void delete(String id);
	
    public void deleteByWhere(String swhere);
	
	List<BuWeixinactivity> execSql(String sql);
	
	public List<BuWeixinactivity> selectByPage(int pageIndex, int pageSize, String strWhere, String strOrderBy);
	
	public int getRecordCount(String strWhere);
	
}
