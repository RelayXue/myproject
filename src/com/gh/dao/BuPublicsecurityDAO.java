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


public interface BuPublicsecurityDAO {
	 
    void insert(BuPublicsecurity record);
 
    int updateByPrimaryKey(BuPublicsecurity record);
    
    int updateByPrimaryKeySelective(BuPublicsecurity record);
 
     BuPublicsecurity selectByPrimaryKey(String fuid);
 
    int deleteByPrimaryKey(String fuid);
    
    public List<BuPublicsecurity> execSql(String sql);
    
    public int deleteByWhere(String swhere);
    
    public Object selectObject(String sql);

    public int count(String strWhere);

}
