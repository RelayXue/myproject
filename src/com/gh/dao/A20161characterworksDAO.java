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


public interface A20161characterworksDAO {
	 
    void insert(A20161characterworks record);
 
    int updateByPrimaryKey(A20161characterworks record);
    
    int updateByPrimaryKeySelective(A20161characterworks record);
 
     A20161characterworks selectByPrimaryKey(String fuid);
 
    int deleteByPrimaryKey(String fuid);
    
    public List<A20161characterworks> execSql(String sql);
    
    public int deleteByWhere(String swhere);
    
    public Object selectObject(String sql);

    public int count(String strWhere);

}
