<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@ include file="/commons/common.jsp" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="atguigu" tagdir="/WEB-INF/tags" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<title>客户贡献分析</title>
</head>
<body>

	<div class="page_title">
		客户贡献分析
	</div>
	<div class="button_bar">
		<button class="common_button" onclick="document.forms[0].submit();">
			查询
		</button>
	</div>
	
	<form action="${ctp }/report/pay">
		<div id="listView" style="display:block;">
			<table class="query_form_table" border="0" cellPadding="3"
				cellSpacing="0">
				<tr>
					<th>
						客户名称
					</th>
					<td>
						<input type="text" name="search_LIKE_custName" />
					</td>
					<th>
						日期
					</th>
					<td>
						<input type="text" name="search_D_date1" size="10" />
						-
						<input type="text" name="search_D_date2" size="10" />
					</td>
				</tr>
			</table>
			<!-- 列表数据 -->
			<br />
			
			<c:if test="${page != null && page.totalElments > 0 }">
				<table class="data_list_table" border="0" cellPadding="3"
					cellSpacing="0">
					<tr>
						
						<th>
							客户名称
						</th>
						<th>
							订单金额（元）
						</th>
					</tr>
					<c:forEach var="item" items="${page.content }">
						<tr>
							
							<td align="center">
								${item.custName}
							</td>
							<td align="center">
								${item.menoy }
							</td>
		
						</tr>
					</c:forEach>
				</table>
				<atguigu:page page="${page}" queryString="${queryString }"></atguigu:page>
			</c:if>
			<c:if test="${page == null || page.totalElments == 0 }">
				没有任何数据
			</c:if>
		</div>
	</form>
</body>
</html>