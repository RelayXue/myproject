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
public interface  BuDiningSpecialtyService{ 

	public BuDiningSpecialty findById(String id);
	
	public void save(BuDiningSpecialty item);
	
	public void update(BuDiningSpecialty item);
	
	public void updateSelective(BuDiningSpecialty item);
	
	public void delete(String id);
	
    public void deleteByWhere(String swhere);
	
	List<BuDiningSpecialty> execSql(String sql);
	
	public List<BuDiningSpecialty> selectByPage(int pageIndex, int pageSize, String strWhere, String strOrderBy);
	
	public int getRecordCount(String strWhere);
	
}
