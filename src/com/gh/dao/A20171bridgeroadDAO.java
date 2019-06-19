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


public interface A20171bridgeroadDAO {
	 
    void insert(A20171bridgeroad record);
 
    int updateByPrimaryKey(A20171bridgeroad record);
    
    int updateByPrimaryKeySelective(A20171bridgeroad record);
 
     A20171bridgeroad selectByPrimaryKey(String fuid);
 
    int deleteByPrimaryKey(String fuid);
    
    public List<A20171bridgeroad> execSql(String sql);
    
    public int deleteByWhere(String swhere);
    
    public Object selectObject(String sql);

    public int count(String strWhere);

}
