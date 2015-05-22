<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
 <script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery-1.7.2.js"></script>
 <script type="text/javascript" src="<%=request.getContextPath() %>/js/KGWeb.js"></script>
 
<style type="text/css">
div.menu {
	margin: 60px auto 10px auto;
	text-align: center;
	border: 2px solid #a1a1a1;
	padding: 5px 20px;
	background: #eaf2f8;
	width: 80px;
	border-radius: 5px;
	-moz-border-radius: 5px; /* 老的 Firefox */
	transition: width 0s, background 1s;
	/*transition-delay: 1s;*/
	/* Firefox 4 */
	-moz-transition: width 0s, background 1s, -moz-transform 1s;
	/*-moz-transition-delay:1s;*/
	/* Safari and Chrome */
	-webkit-transition: width 0s, background 1s, -webkit-transform 1s;
	/*-webkit-transition-delay:1s;*/
	/* Opera */
	-o-transition: width 0s, background 1s, -o-transform 1s; 
	/*-o-transition-delay:1s;*/
	
}

div.menu:hover {
	width: 85px;
	background: #92B901;
	/*transform: rotate(360deg);*/
	/*-moz-transform: rotate(360deg); /* Firefox 4 */
	/*-webkit-transform: rotate(360deg); /* Safari and Chrome */
	/*-o-transform: rotate(360deg); /* Opera */
}

a {
	text-decoration: none;
}

a:hover {
	text-decoration: none;
}

a:visited {
	color: black;
	text-decoration: none;
}
</style>
</head>
<body onload="initMenu()">

	<div class="menu"><a href="./jsp/modelPage/treasureFlow.jsp" target="mainFrame">资产出入</a></div>
	<div class="menu"><a href="./jsp/modelPage/treasureModify.jsp" target="mainFrame">资产修改</a></div>
	<div class="menu"><a href="./jsp/modelPage/currencyFlow.jsp" target="mainFrame">货币流水</a></div>
	<div class="menu"><a href="./jsp/modelPage/expFlow.jsp" target="mainFrame">经验流水</a></div>
	<div class="menu"><a href="./jsp/modelPage/otherFlow.jsp" target="mainFrame">其他流水</a></div>
	<div id="lastMenu" class="menu"><a href="./jsp/modelPage/searchItemTemplate.jsp" target="mainFrame">查找模板</a></div>

</body>
</html>