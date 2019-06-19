<%@ page import="java.util.*" contentType="text/html;charset=UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
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