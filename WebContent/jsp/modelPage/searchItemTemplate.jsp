<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>模板查询</title>
<style type="text/css">
th.dataContainer1{
	border-bottom:1px solid #a1a1a1;
	padding: 5px;
	width: 20%;
	border-bottom-style: dashed;
}
th.dataContainer2{
	border-bottom:1px solid #a1a1a1;
	padding: 5px;
	width: 20%;
	border-bottom-style: dashed;
}
th.dataContainer3{
	border-bottom:1px solid #a1a1a1;
	padding: 5px;
	width: 60%;
	border-bottom-style: dashed;
}
td.dataContainer1{
	border-bottom:1px solid #a1a1a1;
	padding: 5px;
	width: 20%;
	border-bottom-style: dashed;
	text-align: center;
}
td.dataContainer2{
	border-bottom:1px solid #a1a1a1;
	padding: 5px;
	width: 20%;
	border-bottom-style: dashed;
	text-align: center;
}
td.dataContainer3{
	border-bottom:1px solid #a1a1a1;
	padding: 5px;
	width: 60%;
	border-bottom-style: dashed;
	text-align: center;
}
</style>
</head>
<body>
	<h3>模板查询</h3>
	<div style="width: 100%; height: 50px">
		<form action="searchItemTemplate" method="post">
			<table style="width: 30%; height: 100%; margin: 10px auto;  border: 1px solid #a1a1a1;  border-radius: 5px;">
				<tr>
					<td>
						<label>道具名:</label><input id="text" name="name" type="text"/>
					</td>
					<td><input type="submit" id="subbtn" value="Search"/></td>
				</tr>
			</table>
		</form>
	</div>
	
	
	<table  style="width:1280px;   margin: 10px auto; border: 1px solid #a1a1a1; border-radius:5px;">
		<tr class="underLine">
			<th class="dataContainer1">模板ID</th>
			<th class="dataContainer2">模板名</th>
			<th class="dataContainer3">描述</th>
		</tr>
		<s:if test="msg!=null">

			<script>
				alert("${msg}");
			</script>

		</s:if>
		
		<s:iterator value="templateList">
			<tr class="underLine">
				<td class="dataContainer1"><s:property value="id" /></td>
				<td class="dataContainer2"><s:property value="itemName"/></td>
				<td class="dataContainer3"><s:property value="desc" /></td>
			</tr>
		</s:iterator>
	</table>
</body>
</html>