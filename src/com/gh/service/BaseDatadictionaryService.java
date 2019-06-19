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
public interface  BaseDatadictionaryService{ 

	public BaseDatadictionary findById(String id);
	
	public void save(BaseDatadictionary item);
	
	public void update(BaseDatadictionary item);
	
	public void updateSelective(BaseDatadictionary item);
	
	public void delete(String id);
	
	List<BaseDatadictionary> execSql(String sql);
	
	public List<BaseDatadictionary> selectByPage(int pageIndex, int pageSize, String strWhere, String strOrderBy);
	
	public int getRecordCount(String strWhere);
	
}
