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
public interface  BuNaattrService{ 

	public BuNaattr findById(String id);
	
	public void save(BuNaattr item);
	
	public void update(BuNaattr item);
	
	public void updateSelective(BuNaattr item);
	
	public void delete(String id);
	
    public void deleteByWhere(String swhere);
	
	List<BuNaattr> execSql(String sql);
	
	public List<BuNaattr> selectByPage(int pageIndex, int pageSize, String strWhere, String strOrderBy);
	
	public int getRecordCount(String strWhere);
	
}
