package com.gh.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gh.entity.BaseUser;



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
public interface  BaseOperationLogService{ 

	public BaseOperationLog findById(String id);
	
	public void save(BaseOperationLog item);
	
	public void update(BaseOperationLog item);
	
	public void updateSelective(BaseOperationLog item);
	
	public void delete(String id);
	
	List<BaseOperationLog> execSql(String sql);
	
	public List<BaseOperationLog> selectByPage(int pageIndex, int pageSize, String strWhere, String strOrderBy);
	
	public int getRecordCount(String strWhere);
	
	public void saveLog(String data);
	
}
