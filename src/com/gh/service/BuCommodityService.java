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
public interface  BuCommodityService{ 

	public BuCommodity findById(String id);
	
	public void save(BuCommodity item);
	
	public void update(BuCommodity item);
	
	public void updateSelective(BuCommodity item);
	
	public void delete(String id);
	
    public void deleteByWhere(String swhere);
	
	List<BuCommodity> execSql(String sql);
	
	public List<BuCommodity> selectByPage(int pageIndex, int pageSize, String strWhere, String strOrderBy);
	
	public int getRecordCount(String strWhere);
	
}
