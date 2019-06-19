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
public interface  ObVideoService{ 

	public ObVideo findById(String id);
	
	public void save(ObVideo item);
	
	public void update(ObVideo item);
	
	public void updateSelective(ObVideo item);
	
	public void delete(String id);
	
	List<ObVideo> execSql(String sql);
	
	public List<ObVideo> selectByPage(int pageIndex, int pageSize, String strWhere, String strOrderBy);
	
	public int getRecordCount(String strWhere);
	
}
