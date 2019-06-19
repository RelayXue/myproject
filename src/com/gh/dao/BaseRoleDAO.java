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

public interface BaseRoleDAO {
	 
    void insert(BaseRole record);
 
    int updateByPrimaryKey(BaseRole record);
    
    int updateByPrimaryKeySelective(BaseRole record);
 
     BaseRole selectByPrimaryKey(String fuid);
 
    int deleteByPrimaryKey(String fuid);
    
    public List<BaseRole> execSql(String sql);

    public Object selectObject(String sql);

    public int count(String strWhere);

}
