<%@ page language="java" import="java.util.*,com.gh.entity.*,com.gh.common.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String type=request.getParameter("type");
List<Integrateddata> integrateddata_list=(List<Integrateddata>)request.getAttribute("integrateddata_list");
%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head> 
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width,height=device-height,inital-scale=1.0,maximum-scale=1.0,user-scalable=no;">

<meta name="format-detection" content="telephone=no">
<title>子夜路设计</title>
 <LINK REL="stylesheet" HREF="<%=basePath %>phone/css/ziye.css" TYPE="text/css">
 <script type="text/javascript" src="<%=basePath %>js/jquery-1.8.0.min.js" ></script>
 
</head>

<body>
 <div>
     <img src="<%=basePath %>phone/images/top.png" class="top"/>
 </div>
 
<img src="<%=basePath %>phone/images/bg.png" class="bottom" />

<div class="whole">
	  <div class="left"> 
            <div class="dd1" type="002017"> 住宿</div> 
            <div class="dd1" type="002018"> 餐饮</div>
            <div class="dd1" type="002016"> 休闲</div>
            <div class="dd1" type="002015"> 购物</div>
            <div class="dd1"  type="002019"> 景点</div>
            <div class="dd1" type="002014"> 停车</div>
            <div class="dd1"> 其他</div>
      </div>
    
    
    <div class="right">
    <%
    	if(integrateddata_list!=null&&integrateddata_list.size()>0){
    		for(int a=0;a<integrateddata_list.size();a++){
    			String img=integrateddata_list.get(a).getDimages();
    			String ii[]=img.split(",");
    			String t="L_default.jpg";
    			if(ii!=null){
    				t=ii[0];
    			}
    %>			
    	 <div style="float:right">
             		<img src="<%=basePath %>upload/L_<%=t%>" class="image"/>
               </div>
              <div style="border:2px solid #9d9a9a;float:right;margin:24px 0px 0px 0px;height:125px">
                       <div class="title"><%=SplitChinese.splitStr(integrateddata_list.get(a).getFullname(),7) %> </div>
                       <div class="content">  <%=SplitChinese.splitStr(integrateddata_list.get(a).getIntroduction(),34) %>
                             <div><input type="button" value="详细信息" onclick="window.location.href='<%=basePath %>module/phone/search_detail.jsp?id=<%=integrateddata_list.get(a).getFuid()%>&category=<%=integrateddata_list.get(a).getType()%>'" class="detail" /></div>
                       </div>
             </div> 
                    
             <div class="bg"></div>
    <% 	
    		}
    	}
    %>
       
     </div>   
     
</div>



<script>
	var cdiv=$(".left").find("div");
	var tt='<%=type%>';
	cdiv.each(function (i){
		var type=$(this).attr("type");
		if(type==tt){
			$(this).attr("class","tt");
		}
	})
	cdiv.click(function (){
		cdiv.each(function (i){
			$(this).attr("class","dd1");
		})
		var type=$(this).attr("type");
		$(this).attr("class","tt");
		window.location.href="<%=basePath %>ziye!show?type="+type;
	})
	
	
	
</script>

</body>
</html>
