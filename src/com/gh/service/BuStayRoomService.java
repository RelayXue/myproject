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
public interface  BuStayRoomService{ 

	public BuStayRoom findById(String id);
	
	public void save(BuStayRoom item);
	
	public void update(BuStayRoom item);
	
	public void updateSelective(BuStayRoom item);
	
	public void delete(String id);
	
    public void deleteByWhere(String swhere);
	
	List<BuStayRoom> execSql(String sql);
	
	public List<BuStayRoom> selectByPage(int pageIndex, int pageSize, String strWhere, String strOrderBy);
	
	public int getRecordCount(String strWhere);
	
}
