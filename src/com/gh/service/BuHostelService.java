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
public interface  BuHostelService{ 

	public BuHostel findById(String id);
	
	public void save(BuHostel item);
	
	public void update(BuHostel item);
	
	public void updateSelective(BuHostel item);
	
	public void delete(String id);
	
    public void deleteByWhere(String swhere);
	
	List<BuHostel> execSql(String sql);
	
	public List<BuHostel> selectByPage(int pageIndex, int pageSize, String strWhere, String strOrderBy);
	
	public int getRecordCount(String strWhere);
	
}
