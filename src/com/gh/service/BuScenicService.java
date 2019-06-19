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
public interface  BuScenicService{ 

	public BuScenic findById(String id);
	
	public void save(BuScenic item);
	
	public void update(BuScenic item);
	
	public void updateSelective(BuScenic item);
	
	public void delete(String id);
	
    public void deleteByWhere(String swhere);
	
	List<BuScenic> execSql(String sql);
	
	public List<BuScenic> selectByPage(int pageIndex, int pageSize, String strWhere, String strOrderBy);
	
	public int getRecordCount(String strWhere);
	
}
