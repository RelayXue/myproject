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


public interface BuStayDAO {
	 
    void insert(BuStay record);
 
    int updateByPrimaryKey(BuStay record);
    
    int updateByPrimaryKeySelective(BuStay record);
 
     BuStay selectByPrimaryKey(String fuid);
 
    int deleteByPrimaryKey(String fuid);
    
    public List<BuStay> execSql(String sql);
    
    public int deleteByWhere(String swhere);
    
    public Object selectObject(String sql);

    public int count(String strWhere);
    
    public List<DataEntity> execData(String sql);
    
    public Object updateBySql(String sql);

}
