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
public interface  BuExamineService{ 

	public BuExamine findById(String id);
	
	public void save(BuExamine item);
	
	public void update(BuExamine item);
	
	public void updateSelective(BuExamine item);
	
	public void delete(String id);
	
    public void deleteByWhere(String swhere);
	
	List<BuExamine> execSql(String sql);
	
	public List<BuExamine> selectByPage(int pageIndex, int pageSize, String strWhere, String strOrderBy);
	
	public int getRecordCount(String strWhere);
	
}
