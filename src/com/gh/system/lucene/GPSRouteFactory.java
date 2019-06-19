package com.gh.system.lucene;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.lucene.analysis.core.WhitespaceAnalyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.Field.Index;
import org.apache.lucene.document.Field.Store;
import org.apache.lucene.facet.DrillDownQuery;
import org.apache.lucene.facet.DrillSideways;
import org.apache.lucene.facet.DrillSideways.DrillSidewaysResult;
import org.apache.lucene.facet.FacetsConfig;
import org.apache.lucene.facet.taxonomy.TaxonomyReader;
import org.apache.lucene.facet.taxonomy.directory.DirectoryTaxonomyReader;
import org.apache.lucene.facet.taxonomy.directory.DirectoryTaxonomyWriter;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.queryparser.classic.MultiFieldQueryParser;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.Sort;
import org.apache.lucene.search.SortField;
import org.apache.lucene.search.SortField.Type;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;

import com.gh.common.PropertiesUtil;
import com.gh.system.TimeUtils;

/**
 * @author wjc
 * @date 2014-09-30
 */
public class GPSRouteFactory {
	final Version version = Version.LUCENE_47;
	
	private static String ROOT_PATH = "D://WuZhenLucene/";
	
//	private static String ROOT_PATH = PropertiesUtil.getValue("lucenePath");
	
	/**
	 * 索引路径
	 */
//	private static String lucene_index_path = ROOT_PATH + "DongHuLucene/luceneIndex";
	private final static String lucene_index_path = ROOT_PATH + "luceneIndex";
	/**
	 * 数据路径
	 */
	private final static String lucene_data_path = ROOT_PATH + "data";
//	private static String lucene_data_path = "G://tempSave/www/data";
	private static GPSRouteFactory singleton;
	private static Directory indexDir;
	private static Directory taxoDir;
	private FacetsConfig config = new FacetsConfig();
	
	static public GPSRouteFactory getSingle() throws Exception{
		if(singleton == null) {
			singleton = new GPSRouteFactory(FSDirectory.open(new File(lucene_index_path)), FSDirectory.open(new File(lucene_data_path)));
		}
		return singleton;
	}
	
	private GPSRouteFactory(Directory indexDir, Directory taxoDir) throws Exception{
		this.indexDir = indexDir;
		this.taxoDir = taxoDir;
	} 
	
	/**
	 * 插入单个位置记录
	 * @param pos
	 * @throws Exception
	 */
	public void Insert(GPSRouteModel pos) throws Exception{
		List<GPSRouteModel> users = new ArrayList<GPSRouteModel>();
		users.add(pos);
		InsertForBatch(users);
	}
	
	/**
	 * 批量插入位置记录
	 * @param users
	 * @throws Exception
	 */
	void InsertForBatch(List<GPSRouteModel> users) throws Exception{
		IndexWriter indexWriter = new IndexWriter(indexDir, new IndexWriterConfig(version,  new WhitespaceAnalyzer(version)));

		// Writes facet ords to a separate directory from the main index
		DirectoryTaxonomyWriter taxoWriter = new DirectoryTaxonomyWriter(taxoDir);
		
		for(GPSRouteModel pos : users){
			Document doc = new Document();
			doc.add(new Field("user_id", pos.getUserId(), Store.YES, Index.NOT_ANALYZED));
			doc.add(new Field("phone", pos.getPhone(), Store.YES, Index.NOT_ANALYZED));
			doc.add(new Field("pos_x", pos.getPosX(), Store.YES, Index.NO));
			doc.add(new Field("pos_y", pos.getPosY(), Store.YES, Index.NO));
			doc.add(new Field("speed", pos.getSpeed(), Store.YES, Index.NO));
			doc.add(new Field("direction", pos.getDirection(), Store.YES, Index.NO));
			doc.add(new Field("create_time", pos.getCreateTime().getTime()+"", Store.YES, Index.NOT_ANALYZED));
			doc.add(new Field("request_time", pos.getRequestTime().getTime()+"", Store.YES, Index.NOT_ANALYZED));
			indexWriter.addDocument(config.build(taxoWriter, doc));
		}
		
		indexWriter.close();
		taxoWriter.close();
	}
	
	/**
	 * 根据用户ID查询全部位置数据
	 * @param user_id
	 * @return
	 * @throws Exception
	 */
	List<GPSRouteModel> SelectListByUser(String user_id) throws Exception{
        //需要查询的字段
        String[] fields = { "user_id" };
		return SelectListByQP(fields, user_id);
	}
	
	/**
	 * 使用自定义的Query Parser来查询列表
	 * @param fields 需要查询的字段
	 * @param query_parser_str	查询格式
	 * @return
	 * @throws Exception
	 */
	List<GPSRouteModel> SelectListByQP(String[] fields, String query_parser_str) throws Exception{
        QueryParser queryParser = new MultiFieldQueryParser(version, fields, new StandardAnalyzer(version));
        Query query = queryParser.parse(query_parser_str);
        
        // 2，进行查询，从索引库中查找
        IndexSearcher indexSearcher = new IndexSearcher(IndexReader.open(indexDir));
        Sort sort = new Sort();
        sort.setSort(new SortField("create_time", Type.SCORE));
        
        TopDocs topDocs = indexSearcher.search(query, 999999, sort);
        List<GPSRouteModel> documents = new ArrayList<GPSRouteModel>();
        // 3，打印结果
        for (ScoreDoc scoreDoc : topDocs.scoreDocs) {
            // 文档内部编号
            int index = scoreDoc.doc; 
            // 根据编号取出相应的文档
            Document doc = indexSearcher.doc(index);
            if(doc != null) documents.add(DocumentToPosition(doc));
        }
		return documents;
	}
	
	/**
	 * 查询所有位置数据
	 * @return
	 * @throws IOException
	 */
	List<GPSRouteModel> SelectListAll() throws IOException{
		DirectoryReader indexReader = DirectoryReader.open(indexDir);
		IndexSearcher searcher = new IndexSearcher(indexReader);
		TaxonomyReader taxoReader = new DirectoryTaxonomyReader(taxoDir);

		// Passing no baseQuery means we drill down on all
		// documents ("browse only"):
		DrillDownQuery q = new DrillDownQuery(config);

		// Now user drills down on Publish Date/2010:
//		q.add("Publish Date", "2012");

		DrillSideways ds = new DrillSideways(searcher, config, taxoReader);
		DrillSidewaysResult result = ds.search(q, 9999999);
		
		ScoreDoc[] pos_docs = result.hits.scoreDocs;
		
		indexReader.close();
		taxoReader.close();

		// Retrieve results
		List<GPSRouteModel> pos_list = new ArrayList<GPSRouteModel>();
		if(pos_docs.length > 0){
			IndexSearcher indexSearcher = new IndexSearcher(IndexReader.open(indexDir));
	        for (ScoreDoc scoreDoc : pos_docs) {
	            // 文档内部编号
	            int index = scoreDoc.doc; 
	            // 根据编号取出相应的文档
	            Document doc = indexSearcher.doc(index);
	            pos_list.add(DocumentToPosition(doc));
	        }


		}
		
		return pos_list;
	}
	
	/**
	 * 查询用户某个时间段的数据
	 * @param user_id
	 * @param begin_date 包括开始日期
	 * @param end_date 不包括结束日期
	 * @return
	 * @throws Exception
	 */
	public List<GPSRouteModel> SelectListByUserWithCycle(String user_id, Date begin_date, Date end_date) throws Exception{
		List<GPSRouteModel> documents = new ArrayList<GPSRouteModel>();
		
		String[] fields = { "user_id" };
		String begin_str = begin_date.getTime()+"";
		String end_str = end_date.getTime()+"";
		documents = SelectListByQP(fields, "(user_id:"+user_id+") AND (create_time:["+begin_str+" TO "+end_str+"])");
		
		return documents;
	}
	
	public static void main(String[] args) throws Exception {
		/*
		Position p = new Position();
		p.setUserId("dsgfkljdsg454611");
		p.setCreateTime(TimeUtils.CN_SHORT_DATE_FORMAT_3.parse("2014-05-06 10:15"));
		p.setRequestTime(TimeUtils.CN_SHORT_DATE_FORMAT_3.parse("2014-05-06 10:15"));
		
		Test5.getSingle().Insert(p);
		*/ 
//		List<Position> users = Test5.getSingle().SelectListByUser("dfg498et1");
		//List<Position> users = PositionDL.getSingle().SelectListAll();
		//List<GPSRouteModel> users = GPSRouteFactory.getSingle().SelectListByUserWithCycle("13372315301", TimeUtils.SIMPLE_DATE_FORMAT_A.parse("2014-03-01"), TimeUtils.SIMPLE_DATE_FORMAT_A.parse("2014-12-10"));
		
		/*
		GPSRouteModel model = new GPSRouteModel();
		
		model.setUserId("c92f1ec9ad46492fba6249d00635985c");
		model.setPosX("120.488484470000");
		model.setPosY("30.734589540000");
		model.setDirection("小明是一名学生！");
		model.setPhone("15180650402");
		model.setCreateTime(new Date());
		model.setRequestTime(new Date());
		model.setSpeed("0.0");
		
		GPSRouteFactory.getSingle().Insert(model);
		*/
		
		List<GPSRouteModel> users = GPSRouteFactory.getSingle().SelectListByUser("15180650402");
//		List<GPSRouteModel> users = GPSRouteFactory.getSingle().SelectListByUserWithCycle("15180650402", TimeUtils.SIMPLE_DATE_FORMAT_A.parse("2015-01-12"), TimeUtils.SIMPLE_DATE_FORMAT_A.parse("2015-01-13"));
		System.out.println(users.size() + "条记录");
        for (GPSRouteModel pos : users) {
            System.out.println("------------------------------");
            System.out.println("user_id = " + pos.getUserId());
            System.out.println("phone = " + pos.getPhone());
            System.out.println("create_time = " + TimeUtils.SIMPLE_DATE_FORMAT.format(pos.getCreateTime()));
            System.out.println("request_time = " + TimeUtils.SIMPLE_DATE_FORMAT.format(pos.getRequestTime()));
            System.out.println("request_time = " + pos.getPosX());
        } 
        
		
		//批量插入测试 
      /*  List<String> datas = new ArrayList<String>();
        datas.add("120.492773,30.739253");
        datas.add("120.491631,30.739567");
        datas.add("120.490308,30.739976");
        datas.add("120.489318,30.740310");
        datas.add("120.488909,30.740443");
        datas.add("120.488414,30.740671");
        datas.add("120.487319,30.741023");
        datas.add("120.486872,30.741176");
        datas.add("120.486263,30.741366");
        datas.add("120.485644,30.741566");
        datas.add("120.485473,30.741633");
        datas.add("120.484683,30.741899");
        datas.add("120.484340,30.742032");
        datas.add("120.484035,30.742194");
        datas.add("120.483541,30.742499");
        datas.add("120.483531,30.742508");
        datas.add("120.482903,30.743460");
        datas.add("120.482798,30.743822");
        datas.add("120.482770,30.744155");
        datas.add("120.482789,30.744488");
        datas.add("120.482855,30.744802");
        datas.add("120.482950,30.745030");
        datas.add("120.483398,30.746011");
        datas.add("120.483940,30.747181");
        datas.add("120.484369,30.748086");
        datas.add("120.484616,30.748657");
		List<GPSRouteModel> pos_list = new ArrayList<GPSRouteModel>();
		for(int i = 0;i<datas.size();i++){
			GPSRouteModel pos = new GPSRouteModel();
			pos.setUserId("15180650402");
			pos.setPosX(datas.get(i).split(",")[0]);
			pos.setPosY(datas.get(i).split(",")[1]);
			pos.setDirection("15");
			pos.setPhone("15180650402");
			pos.setCreateTime(new Date());
			pos.setRequestTime(new Date());
			pos.setSpeed("0.0");
			
			pos_list.add(pos);
			System.out.println(i);
			Thread.sleep(8 * 1000);
		}
		
		GPSRouteFactory.getSingle().InsertForBatch(pos_list);*/
		
		
		/*
		long a = new Date().getTime();
		List<Position> users = PositionDL.getSingle().SelectListAll();
		long b = new Date().getTime();
		System.out.println(b-a);
		System.out.println(users.size());
		*/ 
        /*
 	List<Position> users2 = PositionDL.getSingle().SelectListByUserWithCycle("13372315301", TimeUtils.SIMPLE_DATE_FORMAT_A.parse("2014-03-01"), TimeUtils.SIMPLE_DATE_FORMAT_A.parse("2014-12-10"));
     
		System.out.println("------------------------------"+users2.size());
        System.out.println("user_id = " + users2.get(0).getUserId());
       // System.out.println("create_time = " + TimeUtils.SIMPLE_DATE_FORMAT.format(users.get(0).getCreateTime()));
        //System.out.println("request_time = " + TimeUtils.SIMPLE_DATE_FORMAT.format(users.get(0).getRequestTime()));
        //System.out.println("request_time = " + users.get(0).getPosX());
        for(int u=0;u<users2.size();u++){
        	  System.out.println("user_id = " + users2.get(u).getCreateTime());
        }
		 */

	}
	
	/**
	 * 文档类转换成位置类
	 * @param doc
	 * @return
	 */
	public static GPSRouteModel DocumentToPosition(Document doc){
		if(doc != null){
			GPSRouteModel pos = new GPSRouteModel();
			pos.setUserId(doc.get("user_id"));
			pos.setPhone(doc.get("phone"));
			pos.setPosX(doc.get("pos_x"));
			pos.setPosY(doc.get("pos_y"));
			pos.setSpeed(doc.get("speed"));
			pos.setDirection(doc.get("direction"));
			pos.setCreateTime(new Date(Long.parseLong(doc.get("create_time"))));
			pos.setRequestTime(new Date(Long.parseLong(doc.get("request_time"))));
			return pos;
		}
		return null;
	}
}
