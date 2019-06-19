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
public interface  BuAccommodationService{ 

	public BuAccommodation findById(String id);
	
	public void save(BuAccommodation item);
	
	public void update(BuAccommodation item);
	
	public void updateSelective(BuAccommodation item);
	
	public void delete(String id);
	
    public void deleteByWhere(String swhere);
	
	List<BuAccommodation> execSql(String sql);
	
	public List<BuAccommodation> selectByPage(int pageIndex, int pageSize, String strWhere, String strOrderBy);
	
	public int getRecordCount(String strWhere);
	
}
