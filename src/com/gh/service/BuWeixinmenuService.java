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
public interface  BuWeixinmenuService{ 

	public BuWeixinmenu findById(String id);
	
	public void save(BuWeixinmenu item);
	
	public void update(BuWeixinmenu item);
	
	public void updateSelective(BuWeixinmenu item);
	
	public void delete(String id);
	
    public void deleteByWhere(String swhere);
	
	List<BuWeixinmenu> execSql(String sql);
	
	public List<BuWeixinmenu> selectByPage(int pageIndex, int pageSize, String strWhere, String strOrderBy);
	
	public int getRecordCount(String strWhere);
	
}
