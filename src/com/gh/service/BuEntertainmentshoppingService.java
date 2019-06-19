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
public interface  BuEntertainmentshoppingService{ 

	public BuEntertainmentshopping findById(String id);
	
	public void save(BuEntertainmentshopping item);
	
	public void update(BuEntertainmentshopping item);
	
	public void updateSelective(BuEntertainmentshopping item);
	
	public void delete(String id);
	
    public void deleteByWhere(String swhere);
	
	List<BuEntertainmentshopping> execSql(String sql);
	
	public List<BuEntertainmentshopping> selectByPage(int pageIndex, int pageSize, String strWhere, String strOrderBy);
	
	public int getRecordCount(String strWhere);
	
}
