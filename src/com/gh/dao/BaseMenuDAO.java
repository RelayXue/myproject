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

import com.gh.entity.BaseUser;

public interface BaseMenuDAO {
	 
    void insert(BaseMenu record);
 
    int updateByPrimaryKey(BaseMenu record);
    
    int updateByPrimaryKeySelective(BaseMenu record);
 
     BaseMenu selectByPrimaryKey(String fuid);
 
    int deleteByPrimaryKey(String fuid);
    
    public List<BaseMenu> execSql(String sql);

    public Object selectObject(String sql);

    public int count(String strWhere);

}
