
<%@ page import="java.util.*" contentType="text/html;charset=UTF-8"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%@ taglib uri="/struts-tags" prefix="s"%>

<!doctype html>
<html>
	<head>
		<meta http-equiv="Content-type" content="text/html; charset=utf-8">
		<meta name="viewport"
			content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
		<link href="<%=basePath%>module/weixin/css/ssh_4.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="<%=basePath%>js/jquery.js"></script>
		<script type="text/javascript" src="<%=basePath%>js/jqueryscrollpagination/scripts/scrollpagination.js"></script>
		<title></title>
		
		<script type="text/javascript">
	$(function() {
		var indexPage='2';
		$('#content').scrollPagination({
			'contentPage' : '<%=basePath%>fnews!poetry', 
			'contentData' : 'pageType=page&&indexPage='+indexPage,
			'scrollTarget' : $(window),  
			'heightOffset' : 10,  
			'beforeLoad' : function() {  
				$('#loading').fadeIn();
			},
			'afterLoad' : function(elementsLoaded) { 
				$('#loading').fadeOut();
				indexPage++;
				this.contentData='pageType=page&&indexPage='+indexPage;
				var i = 0;
				$(elementsLoaded).fadeInWithDelay();
				if ($('#content').children().size() > 100) { 
					$('#nomoreresults').fadeIn();
					$('#content').stopScrollPagination();
				}
			}
		});

		$.fn.fadeInWithDelay = function() {
			var delay = 0;
			return this.each(function() {
				$(this).delay(delay).animate({
					opacity : 1
				}, 200);
				delay += 100;
			});
		};

	});
	
	
	function  showDe(id){
		window.location.href="<%=basePath%>fnews!poetryDetails?id="+id;
	}
</script> 
		
		
	</head>
	<body>
		<div>
			<div class="utop">
				<img src="<%=basePath%>module/weixin/images/top1.png" />
			</div>
			<div class="ucontainer">
				<table width="100%">
				<s:iterator value="buWeixinactivity_list" var="list">
					<tr onclick="showDe('<s:property value="#list.fuid"/>')">
						<td style="padding: 6px 0px 6px 0px">
							<div class="uitem">
								<div class="l">
								</div>
								<div class="c">
									<div class="t">
									</div>
									<div class="u">
										<div class="ucontent">
											<div class="f1">
												<s:property value="@com.gh.common.SplitChinese@splitStr(title,8)" escape="false"/>      
											</div>
											<div class="f2">
												<s:property value="#list.author"/>
											</div>
											<div class="f3">
												<s:date name="#list.createtime"  format="yyyy-MM-dd" /> 
											</div>
											<div class="f4">
												<s:property value="#list.content.replaceAll('<[^>]+>','').substring(0,12)" escape="false" />   
											</div>
										</div>
									</div>
									<div class="b">
									</div>
								</div>
								<div class="r">
								</div>
							</div>
						</td>
					</tr>
					</s:iterator>
					<tr id="content">
				 
					</tr>
				</table>
			</div>
			<div class="ubottom">
			</div>
		</div>
	</body>
</html>
