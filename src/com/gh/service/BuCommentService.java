package com.gh.service;

import java.util.List;
import org.springframework.stereotype.Service;



import java.util.*;
  
 

import com.gh.entity.*;
import com.gh.dao.*;
import com.gh.service.*;  

/**
 * @author xiaofeng
 * @version 1.0
 * @since 1.0
 */

@Service
public interface  BuCommentService{ 

	public BuComment findById(String id);
	
	public void save(BuComment item);
	
	public void update(BuComment item);
	
	public void updateSelective(BuComment item);
	
	public void delete(String id);
	
    public void deleteByWhere(String swhere);
	
	List<BuComment> execSql(String sql);
	
	public List<BuComment> selectByPage(int pageIndex, int pageSize, String strWhere, String strOrderBy);
	
	public int getRecordCount(String strWhere);
	
}
