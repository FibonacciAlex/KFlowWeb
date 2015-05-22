<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>流水查询中心</title>

<script src="./js/jquery-1.7.2.js" type="text/javascript"></script>
<script src="./js/KGWeb.js" type="text/javascript"></script>


<style type="text/css">
body {
	font-family: Arial, Helvetica, sans-serif;
	font-size: 12px;
	margin: 0;
}

#fullbg {
	background-color: gray;
	left: 0;
	opacity: 0.5;
	position: absolute;
	top: 0;
	z-index: 3;
	filter: alpha(opacity = 50);
	-moz-opacity: 0.5;
	-khtml-opacity: 0.5;
}

#dialog {
	background-color: #fff;
	border: 5px solid rgba(0, 0, 0, 0.4);
	height: 200px;
	left: 50%;
	margin: -200px 0 0 -200px;
	padding: 1px;
	position: fixed !important; /* 浮动对话框 */
	position: absolute;
	top: 50%;
	width: 400px;
	z-index: 5;
	border-radius: 5px;
	display: none;/*首先隐藏*/
}

#dialog p {
	margin: 0 0 12px;
	height: 24px;
	line-height: 24px;
	background: #CCCCCC;
}

#dialog p.close {
	padding-right: 10px;
	text-align: right;
}


#dialog p.close a {
	text-align:right;
	color: #fff;
	text-decoration: none;
}
</style>


</head>
<body style="height:970px;  center;margin: 0;padding: 0;border: 0;">
	<table  style="overflow: hidden; margin: 0px; padding: 0px; width: 100%; height: 100%; background: #D7D8D7" >
		<tr style="height: 200px; top: 10px">
			<td>
 				<%@include file="header.jsp" %>
			</td>
		</tr>
		<tr>
			<td>
				<div id="menu" style="background-color:#d5e5f0;  width: 20%; height:100% ;  border: 1px solid; top: 0; float: left; text-align: center;">
					<%@include file="menu.jsp"%>
				</div>
				<iframe name="mainFrame" style="width: 79%; height:100%;  background-color: #d5e5f0; border: 1px solid;top: 0; float: right" scrolling="auto" src="./jsp/modelPage/treasureFlow.jsp"></iframe>
			</td>
		</tr>
		<tr>
			<td>
				<div style="width: 100%; height: 100%; border: 1px solid; top: 0; text-align: center;">Copyright @2014 广州考拉信息技术有限公司</div>
			</td>
		</tr>
	</table>



	<div id="fullbg"></div>
	<div id="dialog">
		<p class="close"><a href="#" onclick="closeBg();">关闭</a></p>
		 	<!--  	<s:if test="returnMsg!=null">

					<script>
						window.alert("+ ${returnMsg} +");
					</script>

				</s:if>   -->  
		<div>
		
		<!-- 	<form id="ff">    -->
			<table align="center">
					<tr>
						<td><label>原密码:</label></td><td> <input id="oldPwd" type="text" name="oldPwd" /></td>
					</tr>
					<tr>
						<td><label>新密码:</label></td><td> <input id="newPwd" type="text" name="newPwd" /></td>
					</tr>
					<tr>
						<td><label>确认密码:</label></td><td> <input id="cnfPwd" type="text" name="cnfPwd" /></td>
					</tr>
					<tr>
						<td colspan="6"><input type="button" value="确定" style=" margin: 10px 50px;"  onclick="resetPwd()"/> 
						<s:reset theme="simple" value="重填" style=" margin: 10px 50px;" /></td>
					</tr>
			
			</table>

		<!-- 	</form>   -->
		</div>
	</div>


</body>
</html>