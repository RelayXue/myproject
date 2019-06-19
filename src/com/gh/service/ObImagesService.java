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
public interface  ObImagesService{ 

	public ObImages findById(String id);
	
	public void save(ObImages item);
	
	public void update(ObImages item);
	
	public void updateSelective(ObImages item);
	
	public void delete(String id);
	
    public void deleteByWhere(String swhere);
	
	List<ObImages> execSql(String sql);
	
	public List<ObImages> selectByPage(int pageIndex, int pageSize, String strWhere, String strOrderBy);
	
	public int getRecordCount(String strWhere);
	
}
