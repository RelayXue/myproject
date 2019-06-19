
<%@ page import="java.util.*,com.gh.entity.*" contentType="text/html;charset=UTF-8"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	List<BuNews> buNews_list=(List<BuNews> )request.getAttribute("buNews_list");		
%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dli">

<html>
	<head>
		<title>美图欣赏</title>
		<link href="<%=basePath%>module/weixin/css/base.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="<%=basePath%>module/weixin/js-photo/jquery-1.7.1.min.js"></script>
		<script src="<%=basePath%>module/weixin/js-photo/custom.js"></script>
		<script src="<%=basePath%>module/weixin/js-photo/jquery.columnizer.min.js"></script>
		<script src="<%=basePath%>module/weixin/js-photo/jquery.isotope.min.js"></script>
		<script src="<%=basePath%>module/weixin/js-photo/lof-slider.js"></script>
		<script src="<%=basePath%>module/weixin/js-photo/superfish-1.4.8/js/superfish.js"></script>
		<script src="<%=basePath%>module/weixin/js-photo/jquery.jcarousel.min.js" type="text/javascript"></script>
		<script src="<%=basePath%>module/weixin/js-photo/jquery.flexslider.js"></script>
		<link rel="stylesheet" media="all" href="<%=basePath%>module/weixin/css/photo.css" />


		<script>
	jQuery(document).ready(function($) {

	});
</script>
		<style>
</style>
	</head>
	<body>
		<div>

			<div id="filter-container">
				<%if(buNews_list!=null&&buNews_list.size()>0){
					for(int a=0;a<buNews_list.size();a++){
				 %>
				 <figure class="photo">
				<div class="photo_img">							 
					<a href="<%=basePath%>fnews!shareDetails?id=<%=buNews_list.get(a).getFuid() %>" class="thumb"><img src="<%=basePath%>upload/580-<%=buNews_list.get(a).getImg1() %>" />
					</a>
					<h3>
						<a href="<%=basePath%>fnews!shareDetails?id=<%=buNews_list.get(a).getFuid() %>"><%=buNews_list.get(a).getFullname() %></a>
					</h3>
				</div>
				</figure>
				 
				 <%}} %>



			 </div>

		</div>

	</body>

</html>