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
public interface  BuPersonnelService{ 

	public BuPersonnel findById(String id);
	
	public void save(BuPersonnel item);
	
	public void update(BuPersonnel item);
	
	public void updateSelective(BuPersonnel item);
	
	public void delete(String id);
	
    public void deleteByWhere(String swhere);
	
	List<BuPersonnel> execSql(String sql);
	
	public List<BuPersonnel> selectByPage(int pageIndex, int pageSize, String strWhere, String strOrderBy);
	
	public int getRecordCount(String strWhere);
	
}
