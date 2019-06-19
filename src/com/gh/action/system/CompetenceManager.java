package com.gh.action.system;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServlet;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.gh.common.TransformJSON;
import com.gh.entity.BaseDatadictionary;
import com.gh.entity.BaseMenuRole;
import com.gh.entity.BaseOperating;
import com.gh.entity.BaseOrganize;
import com.gh.entity.BaseScope;
import com.gh.entity.ComData;
import com.gh.phone.FileUtils;
import com.gh.service.BaseDatadictionaryService;
import com.gh.service.BaseMenuRoleService;
import com.gh.service.BaseMenuService;
import com.gh.service.BaseOrganizeService;
import com.gh.service.BaseScopeService;
import com.gh.service.BaseUserRoleService;


public class CompetenceManager extends  HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static List<BaseOperating> Operating_list;
	static String[] name = new String[] { "applicationContext.xml" };
	private static ApplicationContext resources = new ClassPathXmlApplicationContext(name);
	public static List<BaseMenuRole> baseMenuRole_list;
	public static List<BaseScope> baseScope_list;
	public static List<BaseOrganize>  baseOrganize_list;
	public  BaseMenuRoleService baseMenuRoleService;
	public  BaseUserRoleService baseUserRoleService;
	public  BaseOrganizeService baseOrganizeService;
	public  BaseMenuService baseMenuService;
	public  BaseScopeService baseScopeService;
	public  BaseDatadictionaryService baseDatadictionaryService;
	public ServletContext context;
	public List<String> or=new ArrayList<String>();
	
	/**
	 * 图片存储路径
	 */
	static public String IMAGE_PATH;
	/**
	 * 项目根路径
	 */
	static public String ROOT_PATH;

	public CompetenceManager() { 
		super(); 
	} 
	public void init(){ 
		System.out.println("开始缓存时间："+new Date());
		this.DataRoleMenu(null);
		this.DataScope(null);
		this.Datadictionary(null);
		this.DataOrganize(null);
		
		System.out.println("开始缓存时间："+new Date());
		
		ROOT_PATH = getServletContext().getRealPath("/");
		//创建路径
		IMAGE_PATH = getServletContext().getRealPath("/") + "/upload/";
		FileUtils.createFilePath(IMAGE_PATH);
	} 
	
	public void DataScope(ServletContext context){ 
		if(context==null){
			context =this.getServletContext();   
		}
		baseScopeService=(BaseScopeService)resources.getBean("BaseScopeService");
		baseScope_list=baseScopeService.execSql("select m.menu_url as menu_id ,s.* from base_scope  s left join base_menu m on s.menu_id=m.fuid");
	} 
	
	public void DataOrganize(ServletContext context){ 
		if(context==null){
			context =this.getServletContext();   
		}
		baseOrganizeService=(BaseOrganizeService)resources.getBean("BaseOrganizeService");
		baseOrganize_list=baseOrganizeService.execSql("select * from base_organize");
	} 
	
	public void DataRoleMenu(ServletContext context){ 
		if(context==null){
			context =this.getServletContext();   
		}
		baseMenuRoleService=(BaseMenuRoleService)resources.getBean("BaseMenuRoleService");
		baseMenuRole_list=baseMenuRoleService.execSql("  select op.code as operating_id, m.menu_url as menu_id,mr.* from base_menu_role mr left join   base_menu m on mr.menu_id=m.fuid  left join  base_operating op  on op.fuid=mr.operating_id");
	} 
	
	public void Datadictionary(ServletContext context){
		baseDatadictionaryService=(BaseDatadictionaryService)resources.getBean("BaseDatadictionaryService");
		if(context==null){
			context =this.getServletContext();   
		}
		List<BaseDatadictionary> baseDatadictionary_list= baseDatadictionaryService.execSql("select * from  base_datadictionary");
		Map<String, String> DatadictionaryMap = new HashMap<String, String>();
		for(BaseDatadictionary t : baseDatadictionary_list){
			DatadictionaryMap.put(t.getCode().toString(), t.getFullname());
		}
		
		context.setAttribute("DatadictionaryMap", DatadictionaryMap);
		context.setAttribute("baseDatadictionary_list", TransformJSON.toJSON(baseDatadictionary_list));
	}
	
	public static ComData  getCom(String roleid,String menuid){
		String ro[]=null;
		if(roleid!=null&&roleid.length()>0){
			ro=roleid.split(",");
		}
		ComData com=new ComData();
		if(baseMenuRole_list!=null){
			for(int a=0;a<baseMenuRole_list.size();a++){
				if(ro!=null){
					for(int b=0;b<ro.length;b++){
						if(baseMenuRole_list.get(a).getMenuId()!=null&&baseMenuRole_list.get(a).getMenuId().equals(menuid)
								&&baseMenuRole_list.get(a).getRoleId()!=null&&baseMenuRole_list.get(a).getRoleId().equals(ro[b])){
							if(baseMenuRole_list.get(a).getOperatingId().equals("base_show")){
								com.setHisSelect(true);
							}
							if(baseMenuRole_list.get(a).getOperatingId().equals("base_add")){
								com.setHisAdd(true);
							}
							if(baseMenuRole_list.get(a).getOperatingId().equals("base_del")){
								com.setHisDelete(true);
							}
							if(baseMenuRole_list.get(a).getOperatingId().equals("base_update")){
								com.setHisUpdate(true);
							}
							if(baseMenuRole_list.get(a).getOperatingId().startsWith("business")){
								com.getHisOther().put(baseMenuRole_list.get(a).getOperatingId(), true);
							}
							
						}
					}
				}
			}
		}
		System.out.println(menuid+"-----add:"+com.getHisAdd());
		System.out.println(menuid+"-----del:"+com.getHisDelete());
		System.out.println(menuid+"-----sel:"+com.getHisSelect());
		System.out.println(menuid+"-----update:"+com.getHisUpdate());
		return com;
	}
	public static String  getScope(String MenuUrl,String RoleId){
		String OrganizeId="";
		if(baseScope_list!=null&&baseScope_list.size()>0){
			for(int a=0;a<baseScope_list.size();a++){
				String menu_url=baseScope_list.get(a).getMenuId();
				String role_id=baseScope_list.get(a).getRoleId();
				if(menu_url!=null&&menu_url.equals(MenuUrl)&&role_id!=null&&role_id.equals(RoleId)){
					OrganizeId+="'"+baseScope_list.get(a).getOrganizeId()+"',";
				}
			}
			
		}
		OrganizeId=OrganizeId.length()>0?OrganizeId.substring(0, OrganizeId.length()-1):OrganizeId;
		return OrganizeId;
	}
	public  String  getOrganizeChild(String OrganizeId){
		if(baseOrganize_list!=null&&baseOrganize_list.size()>0){
			for(int a=0;a<baseOrganize_list.size();a++){
				if(baseOrganize_list.get(a).getParentid().equals(OrganizeId)){
					or.add(baseOrganize_list.get(a).getFuid());
					getOrganizeChild(baseOrganize_list.get(a).getFuid());
				}
			}
		}
		String Organize_Id="";
		if(or!=null&&or.size()>0){
			for(int a=0;a<or.size();a++){
				Organize_Id+="'"+or.get(a)+"',";
			}
		}
		Organize_Id=Organize_Id.length()>0?Organize_Id.substring(0, Organize_Id.length()-1):"'"+OrganizeId+"'";
		Organize_Id+=",'"+OrganizeId+"'";
		return Organize_Id;
	}
	
	public  void refreshRoleMenu(ServletContext context){
		this.DataRoleMenu(context);
	}
	public  void RefreshDatadictionary(ServletContext context){
		this.Datadictionary(context);
	}
	public  void RefreshDataScope(ServletContext context){
		this.DataScope(context);
	}
	public  void RefreshDataOrganize(ServletContext context){
		 this.DataOrganize(context);
	}
	public void clear(){
		baseMenuRole_list=null;
	}
	public List<String> getOr() {
		return or;
	}
	public void setOr(List<String> or) {
		this.or = or;
	}
}
