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
public interface  OpinionService{ 

	public Opinion findById(String id);
	
	public void save(Opinion item);
	
	public void update(Opinion item);
	
	public void updateSelective(Opinion item);
	
	public void delete(String id);
	
    public void deleteByWhere(String swhere);
	
	List<Opinion> execSql(String sql);
	
	public List<Opinion> selectByPage(int pageIndex, int pageSize, String strWhere, String strOrderBy);
	
	public int getRecordCount(String strWhere);
	
}
