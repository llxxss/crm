<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/commons/common.jsp" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<title>角色管理 - 分配权限</title>
	<script type="text/javascript">
		$(function(){
			//点父控子
			$(":checkbox[name!='authorities']").click(function(){
				var id=$(this).attr("id");
				var flag=$(this).prop("checked");
				$("."+id).prop("checked",flag);
			});
			//点子控父
			$(":checkbox[name='authorities']").click(function(){
				var pid=$(this).attr("class");
				var flag=$("."+pid).length==$("."+pid+":checked").length;
				$("#"+pid).prop("checked",flag);
			});
			$(":checkbox").each(function(){
				var pid=$(this).attr("class");
				var flag=$("."+pid).length==$("."+pid+":checked").length;
				$("#"+pid).prop("checked",flag);
			})
		})
	</script>
</head>

<body class="main">
 	
 	
 	<form:form action="${ctp }/role/${role.id}" method="PUT" modelAttribute="role">
 	
		<input type="hidden" name="id" value="${role.id}" />
		
		<div class="page_title">
			角色管理 &gt; 分配权限
		</div>
		
		<div class="button_bar">
			<button class="common_button" onclick="javascript:history.back(-1);">
				返回
			</button>
			<button class="common_button" onclick="document.forms[0].submit();">
				保存
			</button>
		</div>

		<table class="query_form_table" border="0" cellPadding="3"
			cellSpacing="0">
			<tr>
				<th class="input_title" width="10%">
					角色名
				</th>
				<td class="input_content" width="20%">
					${role.name}
				</td>
				<th class="input_title" width="10%">
					角色描述
				</th>
				<td class="input_content" width="20%">
					${role.description}
				</td>
				<th class="input_title" width="10%">
					状态
				</th>
				<td class="input_content" width="20%">
					${role.enabled? "有效" : "无效"}
				</td>
			</tr>
			<tr>
				<th class="input_title">
					权限
				</th>
				<td class="input_content" colspan="5" valign="top">
					<c:forEach var="pa" items="${parentAuthorities }" varStatus="status">
						<input id="${pa.id }" type="checkbox" class="parent"/>
						${pa.displayName }
						<br>&nbsp;&nbsp;&nbsp;&nbsp;
						<form:checkboxes cssClass="${pa.id }" items="${pa.subAuthorities }" path="authorityIds"
						itemLabel="displayName" itemValue="id" delimiter="<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"/>
						<br><br>
					</c:forEach>
				</td>
			</tr>
		</table>
	</form:form>
	
</body>
</html>
