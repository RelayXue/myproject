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
public interface  BuBarcodeService{ 

	public BuBarcode findById(String id);
	
	public void save(BuBarcode item);
	
	public void update(BuBarcode item);
	
	public void updateSelective(BuBarcode item);
	
	public void delete(String id);
	
    public void deleteByWhere(String swhere);
	
	List<BuBarcode> execSql(String sql);
	
	public List<BuBarcode> selectByPage(int pageIndex, int pageSize, String strWhere, String strOrderBy);
	
	public int getRecordCount(String strWhere);
	
}
