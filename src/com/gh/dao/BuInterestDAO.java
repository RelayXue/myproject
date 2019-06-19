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


public interface BuInterestDAO {
	 
    void insert(BuInterest record);
 
    int updateByPrimaryKey(BuInterest record);
    
    int updateByPrimaryKeySelective(BuInterest record);
 
     BuInterest selectByPrimaryKey(String fuid);
 
    int deleteByPrimaryKey(String fuid);
    
    public List<BuInterest> execSql(String sql);
    
    public List<BuInterest> execSqlCount(String sql);
    
    public int deleteByWhere(String swhere);
    
    public Object selectObject(String sql);

    public int count(String strWhere);

}
