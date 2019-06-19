package com.gh.service;

import java.util.List;

import com.gh.entity.BuAttractions;
import com.gh.entity.BuDining;
import com.gh.entity.BuEntertainmentshopping;
import com.gh.entity.BuGeography;
import com.gh.entity.BuNews;
import com.gh.entity.BuStay;
import com.gh.entity.DataContainer;
import com.gh.entity.DataEntity;
import com.gh.entity.ScenicContainer;

public interface WebSearchService {
	
	public List<DataEntity> selectDataByPage(int pageIndex, int pageSize,String strWhere, String strOrderBy);
	
	public int getDataCount(String strWhere);
	
	public DataContainer<BuDining> findBuDiningById(String id);
	
	public DataContainer<BuStay> findBuStayById(String id);
	
	public DataContainer<BuEntertainmentshopping> findBuEntertainmentshoppingById(String id);
	
	public DataContainer<BuGeography> findBuGeographyById(String id);
	
	public List<DataEntity> findByType(String type,int pageIndex,int pageSize,String strWhere, String strOrderBy);

	public int getTypeDataCount(String type,String strWhere);
	
	public String findAutoCompleteData(String q,int len);
	
	public ScenicContainer findBuattractions(int pageIndex, int pageSize,String strWhere, String strOrderBy);
    
	public List<BuAttractions> findAttractionsList(int pageIndex, int pageSize,String strWhere, String strOrderBy);
	
	public int getBuattractionsCount(String strWhere);
	
	public BuAttractions findAttractionsById(String id);
	
	public DataContainer<List<BuNews>> findNews(int pageIndex, int pageSize,String strWhere, String strOrderBy);
	
	public int findNewsCount(String strWhere);
	
	public int updateBySql(String sql);
	
	public String findAutoCompleteData(String q);
}
