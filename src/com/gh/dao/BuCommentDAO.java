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


public interface BuCommentDAO {
	 
    void insert(BuComment record);
 
    int updateByPrimaryKey(BuComment record);
    
    int updateByPrimaryKeySelective(BuComment record);
 
     BuComment selectByPrimaryKey(String fuid);
 
    int deleteByPrimaryKey(String fuid);
    
    public List<BuComment> execSql(String sql);
    
    public int deleteByWhere(String swhere);
    
    public Object selectObject(String sql);

    public int count(String strWhere);

}
