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
public interface  BuWeixinmessageService{ 

	public BuWeixinmessage findById(String id);
	
	public void save(BuWeixinmessage item);
	
	public void update(BuWeixinmessage item);
	
	public void updateSelective(BuWeixinmessage item);
	
	public void delete(String id);
	
    public void deleteByWhere(String swhere);
	
	List<BuWeixinmessage> execSql(String sql);
	
	public List<BuWeixinmessage> selectByPage(int pageIndex, int pageSize, String strWhere, String strOrderBy);
	
	public int getRecordCount(String strWhere);
	
}
