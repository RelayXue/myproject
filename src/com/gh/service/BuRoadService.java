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
public interface  BuRoadService{ 

	public BuRoad findById(String id);
	
	public void save(BuRoad item);
	
	public void update(BuRoad item);
	
	public void updateSelective(BuRoad item);
	
	public void delete(String id);
	
    public void deleteByWhere(String swhere);
	
	List<BuRoad> execSql(String sql);
	
	public List<BuRoad> selectByPage(int pageIndex, int pageSize, String strWhere, String strOrderBy);
	
	public int getRecordCount(String strWhere);
	
}
