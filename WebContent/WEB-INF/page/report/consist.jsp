<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="atguigu" tagdir="/WEB-INF/tags" %>
<%@ include file="/commons/common.jsp" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<title>客户构成分析</title>
</head>
<body>
	
	<div class="page_title">
		客户构成分析
	</div>
	<div class="button_bar">
		<button class="common_button" onclick="document.forms[0].submit();">
			查询
		</button>
	</div>
	${param.type }
	<form action="${ctp }/report/consist">
		<div id="listView" style="display:block;">
			<table class="query_form_table" border="0" cellPadding="3"
				cellSpacing="0">
				<tr>
					<th>
						查询方式
					</th>
					<td>
						<%
							Map<String,Object> types=new HashMap<String,Object>();
							types.put("search_level","按等级");
							types.put("search_credit","按信用");
							types.put("search_satify","按满意");		
							request.setAttribute("types", types);
							%>
						<select name="type">
							
							<c:forEach items="${types }" var="type">
								<c:if test="${param.type==type.key }">
									<option  value="${type.key }" selected="selected">${type.value }</option>
								</c:if>
								<c:if test="${param.type!=type.key }">
									<option  value="${type.key }" >${type.value }</option>
								</c:if>
							</c:forEach>
							<!-- <option value="search_level">
								按等级
							</option>
							<option value="search_credit">
								按信用度
							</option>
							<option value="search_satify">
								按满意度
							</option> -->
						</select>
					</td>
					<th>
						&nbsp;
					</th>
					<td>
						&nbsp;
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
						序号
					</th>
					<th>
						${param.type}
					</th>
					<th>
						客户数量
					</th>
				</tr>
				
				<c:forEach var="objects" items="${page.content }" varStatus="status">
					<tr>
						<td class="list_data_number">${status.index + 1}</td>
						<td class="list_data_ltext">${objects.type}</td>
						<td class="list_data_number">${objects.count}</td>
						
					</tr>
				</c:forEach>
			</table>
			<atguigu:page page="${page }" queryString="${queryString }"></atguigu:page>
			</c:if>
			<c:if test="${page == null || page.totalElments == 0 }">
				没有任何数据
			</c:if>
		</div>
	</form>
</body>
</html>