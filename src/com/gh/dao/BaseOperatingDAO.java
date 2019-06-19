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

public interface BaseOperatingDAO {
	 
    void insert(BaseOperating record);
 
    int updateByPrimaryKey(BaseOperating record);
    
    int updateByPrimaryKeySelective(BaseOperating record);
 
     BaseOperating selectByPrimaryKey(String fuid);
 
    int deleteByPrimaryKey(String fuid);
    
    public List<BaseOperating> execSql(String sql);

    public Object selectObject(String sql);

    public int count(String strWhere);

}
