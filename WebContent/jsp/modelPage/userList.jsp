<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户列表</title>
 <script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery-1.7.2.js"></script>
 <script type="text/javascript" src="<%=request.getContextPath() %>/js/KGWeb.js"></script>
 
<style type="text/css">

th.dataContainer1{
	border-bottom:1px solid #a1a1a1;
	padding: 5px;
	width: 25%;
	border-bottom-style: dashed;
}
th.dataContainer2{
	border-bottom:1px solid #a1a1a1;
	padding: 5px;
	width: 25%;
	border-bottom-style: dashed;
}
th.dataContainer3{
	border-bottom:1px solid #a1a1a1;
	padding: 5px;
	width: 25%;
	border-bottom-style: dashed;
}
th.dataContainer4{
	border-bottom:1px solid #a1a1a1;
	padding: 5px;
	width: 25%;
	border-bottom-style: dashed;
}

td.dataContainer1{
	border-bottom:1px solid #a1a1a1;
	padding: 5px;
	width: 25%;
	border-bottom-style: dashed;
	text-align: center;
}
td.dataContainer2{
	border-bottom:1px solid #a1a1a1;
	padding: 5px;
	width: 25%;
	border-bottom-style: dashed;
	text-align: center;
}
td.dataContainer3{
	border-bottom:1px solid #a1a1a1;
	padding: 5px;
	width: 25%;
	border-bottom-style: dashed;
	text-align: center;
}
td.dataContainer4{
	border-bottom:1px solid #a1a1a1;
	padding: 5px;
	width: 25%;
	border-bottom-style: dashed;
	text-align: center;
}
</style>

</head>
<body>

		<h3>用户列表</h3>
		<table align="center" style="width: 880px; border: 1px solid #a1a1a1; border-radius: 5px; margin: 10% auto;">
			<tr class="underLine">
				<th class="dataContainer1">用户名</th>
				<th class="dataContainer2">密码</th>
				<th class="dataContainer3">类型</th>
				<th class="dataContainer4">备注</th>
			</tr>

			<s:iterator id="returnData" value="userList">
			<tr class="underLine">
				<td class="dataContainer1"><s:property value="userName" /></td>
				<td class="dataContainer2"><s:property value="pwd" /></td>
				<td class="dataContainer3">
				<s:if test="userType==1">超级管理员</s:if>
				<s:else>普通管理员</s:else>
				</td>
				<td class="dataContainer4"><s:property value="otherScript" /></td>
			</tr>	
			</s:iterator>
		</table>

</body>
</html>