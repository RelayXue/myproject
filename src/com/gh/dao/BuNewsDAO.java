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


public interface BuNewsDAO {
	 
    void insert(BuNews record);
 
    int updateByPrimaryKey(BuNews record);
    
    int updateByPrimaryKeySelective(BuNews record);
 
     BuNews selectByPrimaryKey(String fuid);
 
    int deleteByPrimaryKey(String fuid);
    
    public List<BuNews> execSql(String sql);
    
    public int deleteByWhere(String swhere);
    
    public Object selectObject(String sql);
    public Object selectObject1(String sql);

    public int count(String strWhere);
    
    public int SumField(String method, String field, String where);

}
