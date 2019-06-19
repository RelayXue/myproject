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
public interface  BuScanningService{ 

	public BuScanning findById(String id);
	
	public void save(BuScanning item);
	
	public void update(BuScanning item);
	
	public void updateSelective(BuScanning item);
	
	public void delete(String id);
	
    public void deleteByWhere(String swhere);
	
	List<BuScanning> execSql(String sql);
	
	public List<BuScanning> selectByPage(int pageIndex, int pageSize, String strWhere, String strOrderBy);
	
	public int getRecordCount(String strWhere);
	
}
