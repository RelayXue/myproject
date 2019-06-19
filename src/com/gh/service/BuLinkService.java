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
public interface  BuLinkService{ 

	public BuLink findById(String id);
	
	public void save(BuLink item);
	
	public void update(BuLink item);
	
	public void updateSelective(BuLink item);
	
	public void delete(String id);
	
    public void deleteByWhere(String swhere);
	
	List<BuLink> execSql(String sql);
	
	public List<BuLink> selectByPage(int pageIndex, int pageSize, String strWhere, String strOrderBy);
	
	public int getRecordCount(String strWhere);
	
}
