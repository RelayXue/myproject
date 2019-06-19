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
public interface  BuWeixinVotingRecordService{ 

	public BuWeixinVotingRecord findById(String id);
	
	public void save(BuWeixinVotingRecord item);
	
	public void update(BuWeixinVotingRecord item);
	
	public void updateSelective(BuWeixinVotingRecord item);
	
	public void delete(String id);
	
    public void deleteByWhere(String swhere);
	
	List<BuWeixinVotingRecord> execSql(String sql);
	
	public List<BuWeixinVotingRecord> selectByPage(int pageIndex, int pageSize, String strWhere, String strOrderBy);
	
	public int getRecordCount(String strWhere);
	
}
