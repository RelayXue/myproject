package com.gh.phone;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 
 * json小工具
 * 
 * @author linzhihao
 */
public class JSONPR {
	
	public static final String NULL_JSON_OBJECT = "{}";
	
	public static final String NULL_JSON_ARRAY = "[{}]";
	
	private static final String EMPTY_STRING = "";
	
	private String json;
	
	private Map<String, String> k;

	private JSONPR(){
	super();
	}
		
	/**
	 * 参数p简要说明<br />
	 * -------------------<br />
	 * p="key0;key1;key2;"	表示对象中的那些属性需要被转换为json<br />
	 * p="x0:y0;x1:y1;"		表示对象中属性x在转换成json的时候 使用y作为key<br />
	 * p="@x0:y0.ykey;"		表示 在完成转换后 在json字串中增加key 为 x0 值为 y0.ykey [相当于调用 getY0().getYkey()方法]<br />
	 * p="x:#y;"			表示把属性x当成一个对象去解析(结果是一个json) 并且用y作为key;<br />
	 * p="RQ"				表示把双引号 以 \" 的形式表示<br />
	 * p="RN"				表示删除回车换行符<br />
	 * p="ALL"				表示对象的所有属性都参与json解析<br />
	 * p="LOOP"				表示如果对象中引用了另外一个对象则 把另外一个对象也当作json解析<br />
	 * <br />
	 * 参数可以叠加使用 用分号 ; 隔开<br />
	 * 
	 * 简单例子<br />
	 * -------------<br />
	 * <pre>{@code}
	 * class ELem{
	 * 		int aaa;
	 * 		int bbb;
	 * 		Elem2 elem2;
	 * }
	 * 
	 * class Elem2{
	 * 		String aaa;
	 * 		String bbb;
	 * }
	 * 
	 * </pre>
	 * 省略 getter setter;<br />
	 * <br />
	 * ----------------------------------------------------------------<br />
	 * 创建对象 Elem elem 和 Elem2 elem2; elem.elem2=elem2;<br />
	 * object2json(elem) 或者 object2json(elem,"LOOP") 产生的json为 {"aaa":"val","bbb":"val","elem2":{"aaa":"val","bbb":"val"}}<br />
	 * object2json(elem, "aaa;bbb:b") 产生 {"aaa":"val","b":"val"};<br />
	 * object2json(elem, "aaa:a;bbb:b;@e2a:elem2.aaa;") {"a":"val","b":"val","e2a":"elem2.aaa 的val 相当与调用 getElem2().getAaa()"}<br />
	 * object2json(elem, "aaa:a;bbb:b;e2:#elem2;") 把elem2属性当作一个对象去遍历 并且放入key为e2的json字串中<br />
	 * 
	 */
	public static String object2json(Object obj, String p){
		if(obj==null){
			return NULL_JSON_OBJECT;//FIXME
		}
	if(obj instanceof List<?>){
		return list2json((List<?>) obj,"LOOP");
	}
	if(obj instanceof Map<?,?>){
		return map2json((Map<?, ?>) obj,"LOOP");
	}
	JSONPR j = new JSONPR();
	j.configure(p);
		try {
			return j.builder(obj);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * 把列表转换成JOSN
	 * 
	 * @param list
	 * @return
	 */
	public static String list2json(List<?> list) {
	return list2json(list, "LOOP");
	}
	
	public static String object2json(Object obj){
	return object2json(obj,"LOOP");
	}
	
	public static String map2json(Map<?,?> map){
	return map2json(map,"LOOP");
	}
	
	/**
	 * 
	 * @param list
	 *            需要转换的列表<br />
	 *            
	 * @param p 使用方式同 object2json(List<?>,String);
	 */
	public static String list2json(List<?> list, String p) {
	JSONPR js = new JSONPR();
	StringBuffer sb = new StringBuffer();
	js.configure(p);
	sb.append("[");
	for (Object o : list) {
		try {
			sb.append(js.builder(o)).append(",");
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	if(sb.length() == 1){
		sb.append("t");
	}
		sb.setCharAt(sb.length()-1, ']');
		return sb.toString();
	}
	
	/**
	 * 将JSON数据还原成对象
	 * @param <T>
	 * @param T
	 * @param json
	 * @return
	 */
	public static <T> T json2object(Class<?> T, String json){
	JSONPR j = new JSONPR();
	j.configure("LOOP");
		return j.rebuilder(T, json);
	}
	
	/**
	 * 参数p 解析工作未完成
	 * @param map
	 * @param p
	 * @return
	 */
	@Deprecated
	public static String map2json(Map<?,?> map, String p){
		if(map==null){
			throw new IllegalArgumentException("Map 为空");
		}
		JSONPR json = new JSONPR();
		json.configure(p);
		StringBuffer result = new StringBuffer();
		result.append("{");
		Set<?> set = map.keySet();
	for(Object o : set){
		if(!isBasicTypeOrString(o.getClass())){
			throw new IllegalArgumentException("Key 必须为基本类型或字符串");
		}
		result.append("\"").append(o.toString()).append("\":");
		if(isBasicTypeOrString(map.get(o).getClass())){
			result.append("\"").append(map.get(o).toString()).append("\",");
		}else{
			try {
				result.append(json.builder(map.get(o))).append(",");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
		if(result.charAt(result.length()-1) == ',')
			result.setCharAt(result.length()-1, '}');
		else
			result.append("}");
			return result.toString();
	}

	/*-------------- 在一些场合简化操作 ------------- */

	public JSONPR(Object o){
		json = object2json(o);
	}

	public JSONPR(Object o, String p){
		json = object2json(o, p);
	}
	
	public JSONPR(List<?> list,String p){
		json=list2json(list, p);
	}
	
	public JSONPR(List<?> list){
		json = list2json(list);
	}
	
	public String toString(){
		if(json==null){
			return super.toString();
		}else{
			return json;
		}
	}
	
	public String getJSON(){
		return this.toString();
	}
	
	/**
	 * 把单个对象转换成JSON
	 * 
	 * @param o
	 * @return
	 * @throws Exception
	 */
	private String builder(Object o) throws IllegalArgumentException,
			IllegalAccessException, InvocationTargetException,
			SecurityException, NoSuchMethodException{
		/* 
		 * Field[] fields= o.getClass().getDeclaredFields();
		 */
	if(o==null){
		return NULL_JSON_OBJECT; //FIXME
	}
	if(o instanceof Map<?, ?>){
		return map2json((Map<?, ?>) o);
	}
	if(o instanceof List<?>){
		return list2json((List<?>) o,"LOOP");
	}
	Method[] mets = o.getClass().getMethods();
	StringBuffer sb = new StringBuffer();
	sb.append("{");
	for (Method m : mets) {
		String metName = m.getName();
		if (metName.indexOf("get") == 0 && !metName.equals("getClass") && metName.length()>3) {
		String field = metName.substring(3, 4).toLowerCase()
				+ metName.substring(4, metName.length());
	if ((k != null && k.get(field) != null)
			|| k.get(":ALLTOJSON") != null ) {
		if("!".equals(k.get(field)))
			continue;
		
	String key = k.get(field);
	boolean isobj = false;
	if (key!=null && key.indexOf("#") == 0) {
		key = key.substring(1);
		isobj=true;
	}
		Object result = m.invoke(o);
		
		String val = result == null ? EMPTY_STRING : result.toString();
		if(result instanceof Map<?,?>){
			if(k.get(":LOOP")!=null || k.get(field)!=null){
				isobj = true;
				val = map2json((Map<?, ?>) result);
			}else{
				continue;
			}
		}if(result instanceof List<?>){
			if(k.get(":LOOP")!=null || k.get(field)!=null){
				isobj = true;
				val = list2json((List<?>) result);
			}else{
				continue;
			}
		}else if( val.matches("^\\w+(?:\\.\\w+)*@\\w+$") )
	
			if(k.get(":LOOP")!=null || k.get(field)!=null){
				isobj = true;
				val = object2json(result, "LOOP");
			}else{
				continue;
			}
		if(!isobj) {
			val = format(val);
		}
		if (EMPTY_STRING.equals(key) || key == null && !isobj) {
			sb.append("\"").append(field);
//			if(isobj){
//				sb.append("\":").append(val).append(",");
//			}else{
//			}
			sb.append("\":\"").append(val).append("\",");
		} else if((EMPTY_STRING.equals(key) || key == null )&& isobj && result!=null){
			sb.append("\"").append(field).append("\":").append( object2json(result, "LOOP") ).append(",");
		} else if((!EMPTY_STRING.equals(key) && key != null )&& isobj && result!=null){
			sb.append("\"").append(key).append("\":").append( object2json(result, "LOOP") ).append(",");
		}else {
			sb.append("\"").append(key);
			if(isobj)
				sb.append("\":").append(val).append(",");
			else
				sb.append("\":\"").append(val).append("\",");
		}
	}
		}
	}
		Set<String> kset = k.keySet();
		Iterator<String> iterator = kset.iterator();
		String s = null;
	while (iterator.hasNext()) {
		s = iterator.next();
		if (s.indexOf("@") == 0) {
			String key = s.substring(1);
			String sval = k.get(s);
			if (sval.indexOf(".") != -1) {
				String[] sl = sval.split("\\.");
				Method m = o.getClass().getMethod(
					"get" + sl[0].substring(0, 1).toUpperCase()
					+ sl[0].substring(1, sl[0].length()));
				Object result = m.invoke(o);
	for (int i = 1; i < sl.length; i++) {
		Method mc = result.getClass().getMethod("get"
			+ sl[i].substring(0, 1).toUpperCase()
			+ sl[i].substring(1, sl[i].length()));
		result = mc.invoke(result);
	}
			String val = result == null ? EMPTY_STRING : result.toString();
			if( val.matches("^\\w+(?:\\.\\w+)*@\\w+$") )
			val = object2json(result);
			val = format(val);
			sb.append("\"").append(key).append("\":\"").append( val ).append("\",");
			} else {
				
			}
		}
	}
	if(sb.charAt(sb.length()-1) == ',')
		sb.setCharAt(sb.length()-1, '}');
	else
		sb.append("}");
		return sb.toString();
	}
	
	/**
	 * 分析参数
	 * 
	 * @param p
	 */
	private void configure(String p) {
	
	if (p == null || EMPTY_STRING.equals(p))
		throw new RuntimeException("param not found");
	k = new HashMap<String, String>();
	String[] ks = p.split(";");
		for (String t : ks) {
			String[] v = t.split(":");
			v[0] = v[0].replace(" ", EMPTY_STRING);
	if ("ALL".equals(v[0])){
		k.put(":ALLTOJSON", EMPTY_STRING);
		continue;
	}
	if ("RN".equals(v[0])){
		k.put(":REPLACE_RN", EMPTY_STRING);
		continue;
	}
	if ("RQ".equals(v[0])){
		k.put(":REPLACE_QUOTATION", EMPTY_STRING);
		continue;
	}
	if ("LOOP".equals(v[0])){
		k.put(":LOOP", EMPTY_STRING);
		k.put(":ALLTOJSON", EMPTY_STRING);
	}
			if (v.length == 2) {
				v[1] = v[1].replace(" ", EMPTY_STRING);
				k.put(v[0], v[1]);
			} else if (v.length == 1 && v[0].indexOf("@") == -1) {
				k.put(v[0], EMPTY_STRING);
			}
		}
	}
	
	
	/**
	 * 处理字符串
	 * 
	 * @param val
	 * @return
	 */
	private String format(String val) {
	if (k.get(":REPLACE_RN") != null)
		val = val.replace("\r\n", EMPTY_STRING);
	if (k.get(":REPLACE_QUOTATION") != null)
		val = val.replace("\"", "\\\"");
	return val;
	}
	
	
	
	/**
	 * JSON字串还原成对象
	 * @param <T>
	 * @param T
	 * @param json
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private <T> T rebuilder(Class<?> T, String json){
	Object result = null;
	try {
		result = T.newInstance();
	} catch (Exception e) {
		e.printStackTrace();
	}
	Method[] methods = T.getMethods();
	for(Method method : methods){
		String methodName = method.getName();
		if(methodName.indexOf("set")==0 && methodName.length()>3){
			Class<?>[] paramType = method.getParameterTypes();
		if(paramType.length != 1){
			continue;
		}
			String field = String.valueOf(methodName.charAt(3)).toLowerCase() + methodName.substring(4);
			if(	k.get(":LOOP")!=null || k.get(field)!=null){ // 需要转换
		//String config = k.get(field);
		if( isBasicTypeOrString(paramType[0]) ){
			String regex = "(?:\"|')?"+field+"(?:\"|')?(?:\\s)*:(?:\\s)*(?:\"|')?([^'\"\\},]*)(?:\"|')?";
			Pattern p = Pattern.compile(regex);
			Matcher m = p.matcher(json);
		if(m.find() && m.groupCount()==1){
			try {
				method.invoke(result, dataConvernt(paramType[0], m.group(1)));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else{
			display("没有找到数据 Field:[%s] Type:[%s]", field, paramType[0]);
		}
		}else{
			String regex = "(?:\"|')?"+field+"(?:\"|')?(?:\\s)*:(?:\\s)*\\{[^}]*\\}";
			Pattern p = Pattern.compile(regex);
			Matcher m = p.matcher(json);
		if(m.find()){
			try {
				method.invoke(result, this.rebuilder(paramType[0], m.group()));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else{
			display("没有找到数据 Field:[%s] Type:[%s]", field, paramType[0]);
		}
		}
			}
		}
	}
		return (T) result;
	}
	
	/**
	 * 字符串转换为指定类型
	 * @param <t>
	 * @param t
	 * @param data
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private static <t> t dataConvernt(Class<?> t, String data){
		if(t == int.class || t==Integer.class){
			int result =  Integer.valueOf(data);
				return (t) new Integer(result);
		}
		if(t == long.class || t == Long.class){
			long result =  Long.valueOf(data);
				return (t) new Long(result);
		}
		if(t == byte.class || t == Byte.class){
			byte result =  Byte.valueOf(data);
				return (t) new Byte(result);
		}
		if(t == short.class || t == Short.class){
			short result =  Short.valueOf(data);
				return (t) new Short(result);
		}
		if(t == boolean.class || t== Boolean.class){
			boolean result =  Boolean.valueOf(data);
				return (t) new Boolean(result);
		}
		if(t == float.class || t == Float.class){
			float result =  Float.valueOf(data);
				return (t) new Float(result);
		}
		if(t == double.class || t == Double.class){
			double result =  Double.valueOf(data);
				return (t) new Double(result);
		}
		if(t == char.class){
			return (t) char.class.cast(data);
		}
		if(t == String.class)
			return (t) data;
		return null;
	}
	
	/**
	 * 是否为基本类型或String
	 * @param Class<?> t
	 * @return
	 */
	private static boolean isBasicTypeOrString(Class<?> t){
		if( t == byte.class 	||	t == Byte.class		||	
			t == short.class	||	t == Short.class 	||
			t == int.class		||	t == Integer.class	||
			t == long.class 	||	t == Long.class		||
			t == float.class	||	t == Float.class 	||
			t == double.class 	||	t == Double.class	||
			t == boolean.class	||	t == Boolean.class	||
			t == char.class 	||	
									t == String.class)
	return true;
	return false;
	}
	
	private static void display(String str, Object...s){
		if(s!=null && s.length>0)
			System.out.println(String.format(str, s));
		else
			System.out.println(str);
	}
	
}