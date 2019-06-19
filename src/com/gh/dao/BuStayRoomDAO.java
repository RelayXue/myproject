package com.gh.dao;

import java.util.*;
  
 

import com.gh.entity.*;
import com.gh.dao.*;
import com.gh.service.*;  

/**
 * @author xiaofeng
 * @version 1.0
 * @since 1.0
 */


import java.util.List; 


public interface BuStayRoomDAO {
	 
    void insert(BuStayRoom record);
 
    int updateByPrimaryKey(BuStayRoom record);
    
    int updateByPrimaryKeySelective(BuStayRoom record);
 
     BuStayRoom selectByPrimaryKey(String fuid);
 
    int deleteByPrimaryKey(String fuid);
    
    public List<BuStayRoom> execSql(String sql);
    
    public int deleteByWhere(String swhere);
    
    public Object selectObject(String sql);

    public int count(String strWhere);

}
