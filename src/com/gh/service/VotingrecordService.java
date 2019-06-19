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
public interface  VotingrecordService{ 

	public Votingrecord findById(String id);
	
	public void save(Votingrecord item);
	
	public void update(Votingrecord item);
	
	public void updateSelective(Votingrecord item);
	
	public void delete(String id);
	
    public void deleteByWhere(String swhere);
	
	List<Votingrecord> execSql(String sql);
	
	public List<Votingrecord> selectByPage(int pageIndex, int pageSize, String strWhere, String strOrderBy);
	
	public int getRecordCount(String strWhere);
	
}
