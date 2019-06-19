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
public interface  GmPathLineService{ 

	public GmPathLine findById(String id);
	
	public void save(GmPathLine item);
	
	public void update(GmPathLine item);
	
	public void updateSelective(GmPathLine item);
	
	public void delete(String id);
	
    public void deleteByWhere(String swhere);
	
	List<GmPathLine> execSql(String sql);
	
	public List<GmPathLine> selectByPage(int pageIndex, int pageSize, String strWhere, String strOrderBy);
	
	public int getRecordCount(String strWhere);
	
}
