<%@ page language="java" contentType="text/html; charset=UTF-8"
	    pageEncoding="UTF-8"%>
	<%@taglib prefix="s" uri="/struts-tags"%>
	<%@taglib prefix="tag" uri="/page_tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
 <script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery-1.7.2.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/js/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/js/KGWeb.js"></script>
<style type="text/css">

th.dataContainer1{
	border-bottom:1px solid #a1a1a1;
	padding: 5px;
	width: 15%;
	border-bottom-style: dashed;
}
th.dataContainer2{
	border-bottom:1px solid #a1a1a1;
	padding: 5px;
	width: 10%;
	border-bottom-style: dashed;
}
th.dataContainer3{
	border-bottom:1px solid #a1a1a1;
	padding: 5px;
	width: 15%;
	border-bottom-style: dashed;
}
th.dataContainer4{
	border-bottom:1px solid #a1a1a1;
	padding: 5px;
	width: 60%;
	border-bottom-style: dashed;
}

td.dataContainer1{
	border-bottom:1px solid #a1a1a1;
	padding: 5px;
	width: 15%;
	border-bottom-style: dashed;
	text-align: center;
}
td.dataContainer2{
	border-bottom:1px solid #a1a1a1;
	padding: 5px;
	width: 10%;
	border-bottom-style: dashed;
	text-align: center;
}
td.dataContainer3{
	border-bottom:1px solid #a1a1a1;
	padding: 5px;
	width: 15%;
	border-bottom-style: dashed;
	text-align: center;
}
td.dataContainer4{
	border-bottom:1px solid #a1a1a1;
	padding: 5px;
	width: 60%;
	border-bottom-style: dashed;
	text-align: center;
}
</style>
</head>
<body onload="initSelection()">

<h3>其他流水查询</h3>
	<div style="width: 100%; height: 50px">
		<form action="otherFlowSearch" method="post">
			<table style="width: 85%; height: 100%; margin: 10px auto;  border: 1px solid #a1a1a1;  border-radius: 5px;">
				<tr>
					<td><label>服务器ID</label><select id="selector" name="serverID">
					<s:if test="serverID!=null">
						<option value="${serverID}">${serverName}</option>
					</s:if>
					</select></td>
					<td><label>角色ID:</label>
					<s:if test="roleID!=0">
						<input name="roleID" type="text" value="${roleID}"/>
					</s:if>
					<s:else>
						<input name="roleID" type="text"/>
					</s:else>
					</td>
					<td><label>流水类型:</label><select id="flowList"  name="flowType">
					<s:if test="flowType==null">
						<option value="-1"></option>
					</s:if>
					<s:else>
						<option value="${flowType}">${flowName}</option>
					</s:else>
					</select></td>
					<td><label>开始日期:</label><input id="d11" name="startTime" class="Wdate" onfocus="WdatePicker({isShowWeek:true})" value="${startTime}"/></td>
					<td><label>结束日期:</label><input id="d12" name="endTime" class="Wdate" onfocus="WdatePicker({isShowWeek:true})" value="${endTime}"/></td> 
					<!--<td><label>选择日期:</label><input/> </td>-->
					<td><input type="submit" id="subbtn" value="Search" onclick="return checkTime()"/></td>
				</tr>
			</table>
		</form>
	</div>

	<table  style="width:1280px;   margin: 10px auto; border: 1px solid #a1a1a1; border-radius:5px;">
		<tr class="underLine">
			<th class="dataContainer1">角色ID</th>
			<th class="dataContainer2">流水类型</th>
			<th class="dataContainer3">产生时间</th>
			<th class="dataContainer4">备注</th>
		</tr>
		<s:if test="status==1">

			<script>
				alert("亲，找不到数据啊~！你还是换一些条件吧~");
			</script>

		</s:if>
		<s:if test="status==2">
			<script>
				alert("骚年，你已经太久没操作过了，还是重新登录一下吧~");
				parent.location.href='<%=request.getContextPath() %>/login.jsp';
			</script>
		</s:if>
		<s:if test="status==3">
		<script>
				alert("骚年，今天服务器闹别扭，还是改天再试吧~");
			</script>
		</s:if>


		<s:iterator id="returnData" value="otherFlowList">
			<tr class="underLine">
				<td class="dataContainer1"><s:property value="ownerID" /></td>
				<td class="dataContainer2"><s:property value="flowName"/></td>
				<td class="dataContainer3"><s:property value="recordTime" /></td>
				<td class="dataContainer4"><s:property value="tips" /></td>
			</tr>
		</s:iterator>
		<s:if test="totalCount!=null && totalCount!=0">
			<tr class="underLine">
				<td class="dataContainer1">查询总额：</td>
				<td class="dataContainer2"></td>
				<td class="dataContainer3"></td>
				<td class="dataContainer4">${totalCount}</td>
			</tr>
		</s:if>
	</table>
	<!-- 引入分页标签 -->  
	<s:if test="otherFlowList!=null">
    <div class="page_div"  style="margin: 10px auto;text-align: center;">  
        <tag:pages pageSize="${pageSize}" pageNo="${pageNo}" url="cutOtherFlowPage.action" recordCount="${recordCount}"></tag:pages>  
    </div>  
	</s:if>
<!-- 分页标签结束 --> 


</body>
</html>