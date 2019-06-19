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
public interface  BuWeixinvoteService{ 

	public BuWeixinvote findById(String id);
	
	public void save(BuWeixinvote item);
	
	public void update(BuWeixinvote item);
	
	public void updateSelective(BuWeixinvote item);
	
	public void delete(String id);
	
    public void deleteByWhere(String swhere);
	
	List<BuWeixinvote> execSql(String sql);
	
	public List<BuWeixinvote> selectByPage(int pageIndex, int pageSize, String strWhere, String strOrderBy);
	
	public int getRecordCount(String strWhere);
	
}
