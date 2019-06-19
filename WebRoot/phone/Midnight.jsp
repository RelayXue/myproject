<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	String qid=request.getParameter("qid");		
%>


<!doctype html>
<html>
<head>
    <meta http-equiv="Content-type" content="text/html; charset=utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
    <link href="css/fqjmain.css" rel="stylesheet" type="text/css" />
    <title>子夜路风情街</title>
</head>
<body>
    <div class="cContainer">
        <div class="item1">
            <div class="topJ">
                <div class="title">
                    子夜路风情街</div>
                <div class="content">　　子夜，名自茅盾小说，也如小说所叙“一切都会发生”，那墙、那瓦、那砖，以及那薄薄几缕青苔深深浅浅中都言说着千年以来的故事...</div>
            </div>
            <div class="top1">
               <img src="images/top1.png" style="width: 100%;height:100%"/>
            </div>
            <div class="middle1">
            	<img src="images/middle1.png" style="width: 100%;height:100%"/>
            </div>
            <div class="bottom1">
					<a href="<%=basePath %>ziye!show?type=002017">
						<div>
							住宿
							<img src="images/go1.png" style="vertical-align: middle;with:100%;height:100%;" />
						</div> </a>
						<a href="<%=basePath %>ziye!show?type=002018" >
						<div>
							餐饮
							<img src="images/go1.png" style="vertical-align: middle;with:100%;height:100%;" />
						</div> </a>
						<a href="<%=basePath %>ziye!show?type=002016" >
					<div>
						娱乐
						<img src="images/go1.png" style="vertical-align: middle;with:100%;height:100%;" />
					</div>
					</a>
					<a href="<%=basePath %>ziye!show?type=002014" >
					<div>
						停车
						<img src="images/go1.png" style="vertical-align: middle;with:100%;height:100%;" />
					</div>
					</a>
				</div>
        </div>
        <%if(qid!=null&&qid.length()>0){ %>
        <div class="item2">
				<a
					href="<%=basePath %>phone/getbt!getbt?qid=<%=qid %>">进入当前店铺<img
						src="images/go2.png" />
				</a>
			</div>
			<%} %>
        <div class="item3">
            <img src="images/tip2.png" />微信、支付宝、刷卡支付任您选
        </div>
    </div>
</body>
</html>
