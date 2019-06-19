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
public interface  BuGpsdeviceService{ 

	public BuGpsdevice findById(String id);
	
	public void save(BuGpsdevice item);
	
	public void update(BuGpsdevice item);
	
	public void updateSelective(BuGpsdevice item);
	
	public void delete(String id);
	
    public void deleteByWhere(String swhere);
	
	List<BuGpsdevice> execSql(String sql);
	
	public List<BuGpsdevice> selectByPage(int pageIndex, int pageSize, String strWhere, String strOrderBy);
	
	public int getRecordCount(String strWhere);
	
}
