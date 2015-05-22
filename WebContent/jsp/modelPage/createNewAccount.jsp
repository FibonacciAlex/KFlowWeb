<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>创建新用户</title>
 <script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery-1.7.2.js"></script>
 <script type="text/javascript" src="<%=request.getContextPath() %>/js/KGWeb.js"></script>
</head>
<body>

<div align="center" style="height: 50px; top: 30%">
	<table align="center" style="width: 480px; border: 1px solid #a1a1a1; border-radius: 5px; margin: 10% auto;">
		<tr>
			<td><p style="text-align: center;">创建新用户</p>
				 <s:if test="tips!=null">

					<script>
						alert("${tips}");
					</script>

				</s:if>
				<div align="center">
					<s:form action="createNewAccount">
						<s:textfield name="userName" label="用户名" />
						<s:password name="pwd" label="密  码" />
						<s:radio list="#{'1':'超级管理员','2':'普通用户'}" name="type" value="2"></s:radio>
						<tr>
							<td colspan="6"><s:submit id="submit" value="提交"
									theme="simple" style=" margin: 10px 50px;" onclick="return checkRoleExist(form)"/> <s:reset
									theme="simple" value="重填" style=" margin: 10px 50px;" /></td>
						</tr>
					</s:form>
				</div></td>
		</tr>
	</table>
</div>
</body>
</html>