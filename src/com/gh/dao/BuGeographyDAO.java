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


public interface BuGeographyDAO {
	 
    void insert(BuGeography record);
 
    int updateByPrimaryKey(BuGeography record);
    
    int updateByPrimaryKeySelective(BuGeography record);
 
     BuGeography selectByPrimaryKey(String fuid);
 
    int deleteByPrimaryKey(String fuid);
    
    public List<BuGeography> execSql(String sql);
    
    public List<AutoCompleteEntity> execAutoData(String sql);
    
    public int deleteByWhere(String swhere);
    
    public Object selectObject(String sql);

    public int count(String strWhere);

}
