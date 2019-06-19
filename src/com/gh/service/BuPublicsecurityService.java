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
public interface  BuPublicsecurityService{ 

	public BuPublicsecurity findById(String id);
	
	public void save(BuPublicsecurity item);
	
	public void update(BuPublicsecurity item);
	
	public void updateSelective(BuPublicsecurity item);
	
	public void delete(String id);
	
    public void deleteByWhere(String swhere);
	
	List<BuPublicsecurity> execSql(String sql);
	
	public List<BuPublicsecurity> selectByPage(int pageIndex, int pageSize, String strWhere, String strOrderBy);
	
	public int getRecordCount(String strWhere);
	
}
