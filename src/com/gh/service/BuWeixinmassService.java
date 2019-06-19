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
public interface  BuWeixinmassService{ 

	public BuWeixinmass findById(String id);
	
	public void save(BuWeixinmass item);
	
	public void update(BuWeixinmass item);
	
	public void updateSelective(BuWeixinmass item);
	
	public void delete(String id);
	
    public void deleteByWhere(String swhere);
	
	List<BuWeixinmass> execSql(String sql);
	
	public List<BuWeixinmass> selectByPage(int pageIndex, int pageSize, String strWhere, String strOrderBy);
	
	public int getRecordCount(String strWhere);
	
}
