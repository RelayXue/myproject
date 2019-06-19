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
public interface  BuWeixinluckdrawService{ 

	public BuWeixinluckdraw findById(String id);
	
	public void save(BuWeixinluckdraw item);
	
	public void update(BuWeixinluckdraw item);
	
	public void updateSelective(BuWeixinluckdraw item);
	
	public void delete(String id);
	
    public void deleteByWhere(String swhere);
	
	List<BuWeixinluckdraw> execSql(String sql);
	
	public List<BuWeixinluckdraw> selectByPage(int pageIndex, int pageSize, String strWhere, String strOrderBy);
	
	public int getRecordCount(String strWhere);
	
}
