<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>登录系统</title>

<script type="text/javascript">

	function showAlertMsg() {
		window.alert("+ ${tips} +");
	}
	
</script>

</head>
<body style="width:100%; height:100%; margin:0; background: #d5e5f0 url(./image/bg.jpg);-moz-background-size:100% 100%;background-size:100% 100%; background-repeat: no-repeat;">

	<table align="center" style="width: 480px; border: 1px solid #a1a1a1; border-radius: 5px; margin: 405px auto;">
		<tr>
			<td><p style="text-align: center;">请输入用户名和密码来登录</p>
				 <s:if test="tips!=null">

					<script>
						alert("亲，你输入的用户名或密码不正确啊！");
					</script>

				</s:if>
				<div align="center">
					<s:form action="login">
						<s:textfield name="userName" label="用户名" />
						<s:password name="pwd" label="密  码" />
						<tr>
							<td colspan="6"><s:submit id="submit" value="登录"
									theme="simple" style=" margin: 10px 50px;" /> <s:reset
									theme="simple" value="重填" style=" margin: 10px 50px;" /></td>
						</tr>
					</s:form>
				</div></td>
		</tr>
	</table>

</body>
</html>