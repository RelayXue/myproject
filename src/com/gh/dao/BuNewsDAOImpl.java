package com.gh.dao;

import java.util.*;

import com.gh.entity.*;
import com.gh.dao.*;
import com.gh.service.*;

/**
 * @author xiaofeng
 * @version 1.0
 * @since 1.0
 */

import java.util.List;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

public class BuNewsDAOImpl extends SqlMapClientDaoSupport implements BuNewsDAO {

	public BuNewsDAOImpl() {
		super();
	}

	public void insert(BuNews record) {
		getSqlMapClientTemplate().insert("tj_BuNews.insert", record);
	}

	public int updateByPrimaryKey(BuNews record) {
		int rows = getSqlMapClientTemplate().update("tj_BuNews.updateByPrimaryKey", record);
		return rows;
	}

	public int updateByPrimaryKeySelective(BuNews record) {
		// TODO Auto-generated method stub
		int rows = getSqlMapClientTemplate().update("tj_BuNews.updateByPrimaryKeySelective", record);
		return rows;
	}

	public BuNews selectByPrimaryKey(String fuid) {
		BuNews key = new BuNews();
		key.setFuid(fuid);
		BuNews record = (BuNews) getSqlMapClientTemplate().queryForObject("tj_BuNews.selectByPrimaryKey", key);
		return record;
	}

	public int deleteByPrimaryKey(String fuid) {
		BuNews key = new BuNews();
		key.setFuid(fuid);
		int rows = getSqlMapClientTemplate().delete("tj_BuNews.deleteByPrimaryKey", key);
		return rows;
	}

	public int deleteByWhere(String swhere) {
		int rows = getSqlMapClientTemplate().delete("tj_BuNews.deleteByWhere", swhere);
		return rows;
	}

	public List<BuNews> execSql(String sql) {
		return (List<BuNews>) getSqlMapClientTemplate().queryForList("tj_BuNews.execSql", sql);
	}

	public Object selectObject(String sql) {
		if (sql != null && sql.trim().length() > 0) {
			return getSqlMapClientTemplate().queryForObject("tj_BuNews.execSqlObject", sql);
		}
		return null;
	}
	
	public Object selectObject1(String sql) {
		if (sql != null && sql.trim().length() > 0) {
			return getSqlMapClientTemplate().queryForObject("tj_BuNews.execSqlObject", sql);
		}
		return null;
	}
	
	/**
	 * 统计字段
	 * @param method
	 * @param field
	 * @param where
	 * @return
	 */
	public int SumField(String method, String field, String where) {
		String sql = "SELECT sum(" + field + ") as a FROM bu_news";
		if (where != null && where.trim().length() > 0) {
			sql = sql + " WHERE " + where;
		}
		Object obj = this.selectObject(sql);
		if (obj != null) {
			return Integer.valueOf(this.selectObject(sql).toString());
		}
		return 0;
	}

	public int count(String strWhere) {
		String sql = "SELECT COUNT(*) as a FROM bu_news";
		if (strWhere != null && strWhere.trim().length() > 0) {
			sql = sql + " WHERE " + strWhere;
		}
		Object obj = this.selectObject(sql);
		if (obj != null) {
			return Integer.valueOf(this.selectObject(sql).toString());
		}
		return 0;
	}
}
