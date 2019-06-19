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


public interface GmPathLineDAO {
	 
    void insert(GmPathLine record);
 
    int updateByPrimaryKey(GmPathLine record);
    
    int updateByPrimaryKeySelective(GmPathLine record);
 
     GmPathLine selectByPrimaryKey(String fuid);
 
    int deleteByPrimaryKey(String fuid);
    
    public List<GmPathLine> execSql(String sql);
    
    public int deleteByWhere(String swhere);
    
    public Object selectObject(String sql);

    public int count(String strWhere);

}
