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
public interface  GmPathCrossPointService{ 

	public GmPathCrossPoint findById(String id);
	
	public void save(GmPathCrossPoint item);
	
	public void update(GmPathCrossPoint item);
	
	public void updateSelective(GmPathCrossPoint item);
	
	public void delete(String id);
	
    public void deleteByWhere(String swhere);
	
	List<GmPathCrossPoint> execSql(String sql);
	
	public List<GmPathCrossPoint> selectByPage(int pageIndex, int pageSize, String strWhere, String strOrderBy);
	
	public int getRecordCount(String strWhere);
	
}
