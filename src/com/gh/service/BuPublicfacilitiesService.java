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
public interface  BuPublicfacilitiesService{ 

	public BuPublicfacilities findById(String id);
	
	public void save(BuPublicfacilities item);
	
	public void update(BuPublicfacilities item);
	
	public void updateSelective(BuPublicfacilities item);
	
	public void delete(String id);
	
    public void deleteByWhere(String swhere);
	
	List<BuPublicfacilities> execSql(String sql);
	
	public List<BuPublicfacilities> selectByPage(int pageIndex, int pageSize, String strWhere, String strOrderBy);
	
	public int getRecordCount(String strWhere);
	
}
