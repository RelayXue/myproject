package com.gh.dao;

import java.util.List;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.gh.entity.BaseMenuRole;
import com.gh.entity.DataEntity;

public class WebSearchDaoImpl extends SqlMapClientDaoSupport implements WebSearchDao{
	
	@SuppressWarnings("unchecked")
	public List<BaseMenuRole> execSql(String sql){
    	return (List<BaseMenuRole>) getSqlMapClientTemplate().queryForList(
				"tj_WebSearch.execSql", sql);
    }

    public DataEntity selectObject(String sql){
	     if(sql!=null && sql.trim().length()>0){
	         return (DataEntity) getSqlMapClientTemplate().queryForObject("tj_WebSearch.execSqlOne",sql);
	     }
	     return null;
    }

}
