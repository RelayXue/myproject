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


public interface BuCommodityDAO {
	 
    void insert(BuCommodity record);
 
    int updateByPrimaryKey(BuCommodity record);
    
    int updateByPrimaryKeySelective(BuCommodity record);
 
     BuCommodity selectByPrimaryKey(String fuid);
 
    int deleteByPrimaryKey(String fuid);
    
    public List<BuCommodity> execSql(String sql);
    
    public int deleteByWhere(String swhere);
    
    public Object selectObject(String sql);

    public int count(String strWhere);

}
