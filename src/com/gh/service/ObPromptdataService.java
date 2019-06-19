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
public interface  ObPromptdataService{ 

	public ObPromptdata findById(String id);
	
	public void save(ObPromptdata item);
	
	public void update(ObPromptdata item);
	
	public void updateSelective(ObPromptdata item);
	
	public void delete(String id);
	
	List<ObPromptdata> execSql(String sql);
	
	public List<ObPromptdata> selectByPage(int pageIndex, int pageSize, String strWhere, String strOrderBy);
	
	public int getRecordCount(String strWhere);
	
}
