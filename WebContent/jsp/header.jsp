<%@page import="java.text.DateFormat"%>
<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<div style="width: 100%;height:100%; margin: 0 auto; background: #D7D8D7 top center ; border: 1px solid;" align="center">

	<div style="float: left; width: 30%;">
		<img alt="logo" style="float: left; margin-top: 50px; margin-left: 240px; padding: 0;" src="./image/logo.png">
		<h3 style="margin-top: 100px; margin-left: 50px;color: #4B504D; font-family: -webkit-pictograph;">广州考拉信息技术有限公司<br />
			<small>GUANGZHOU KOLA INFORMATION TECHNOLOGY CO.LTD.</small>
		</h3>
	</div>

	<div style="float: right;top: 10%;">
		<%
			DateFormat d = DateFormat.getDateInstance(DateFormat.FULL);
			String date = d.format(new Date());
			String uname = request.getParameter("userName");
			if (uname == null || uname.equals("")) {
				uname = "还没有注册的酱油小王子";
				response.sendRedirect("/login.jsp");
			}
		%>

		
		
		<p>您好管理员：<%=uname%>          <input type="button" value="修改密码" onclick="showBg()" style="color: red;"></p>
		<p>现在是<%=date.toString()%></p>
		<p style="color: red">PS:流水数据在凌晨才更新</p>
	</div>

</div>


