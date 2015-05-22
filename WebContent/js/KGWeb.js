
function checkTime() {
	var startTime = document.getElementById("d11");
	var endTime = document.getElementById("d12");
	if(startTime.value=="" && endTime.value!=""){
		alert("请选择开始时间！");
		return false;
	}
	
	if(startTime.value!="" && endTime.value==""){
		alert("请选择结束时间！");
		return false;
	}
	if(startTime.value==""&& endTime.value==""){
		return true;
	}
	if(!dateCompare(startTime.value, endTime.value)){
		alert("亲， 结束时间不可以早过开始时间！");
		return false;
	}
	return true;
	
}

function checkText(){
	var text = document.getElementById("text");
	if(text != null){
		alert(text.value);
	}
	return true;
}

function dateCompare(startDate,endDate){
	
	var arr = startDate.split("-");
	var startTime = new Date(arr[0],arr[1],arr[2]);
	var ar = endDate.split("-");
	var endTime = new Date(ar[0],ar[1],ar[2]);
	if(startTime.getTime() > endTime.getTime()){
		return false;
	}
	return true;
}

function checkRoleIDOrRecordTime(form) {
	if(form.roleID.value=="" && form.recordTime.value==""){
		alert("亲，麻烦你告诉我角色ID或日期~");
		return false;
	}
	return true;
}


function initSelection() {
	$.ajax({
		url:'loadServerList.action',
		type:'post',
		data:{},
		dataType:'json',
		success:function(data){
			var selector = document.getElementById("selector");
			$.each(data.serverMap, function(key,value) {
				if(!checkOptionExit(selector,key)){
					selector.options.add(new Option(value,key));
				}
			});
		}
	});
	initCurrency();
	initFlowType();
}

function initCurrency() {
	var selector = document.getElementById("currency");
	if(selector != null){
	$.ajax({
		url:'loadCurrencyList.action',
		type:'post',
		data:{},
		dataType:'json',
		success:function(data){
				$.each(data.currencyList, function(key,value) {
					if(!checkOptionExit(selector,key)){
						selector.options.add(new Option(value,key));
					}
				});
			}
		});
	}
}



function initFlowType() {
	var selector = document.getElementById("flowList");
	if(selector != null){
		$.ajax({
			url:'loadFlowList.action',
			type:'post',
			data:{},
			dataType:'json',
			success:function(data){
					$.each(data.flowList, function(key,value) {
						if(!checkOptionExit(selector, key)){
							selector.options.add(new Option(value,key));
						}
					});
				}
			});
	}
}

function checkOptionExit(selector,key){
	var isExit = false;
	for(var i = 0; i< selector.options.length; i++){
		if(selector.options[i].value == key){
			isExit = true;
			break;
		}
	}
	return isExit;
}


function initMenu() {
	var target = document.getElementById("lastMenu");
	if (target != null) {

		$.ajax({
					url : 'loadMenuList.action',
					type : 'post',
					success : function(data) {
						// 改变dom
						var div=document.createElement("div");
						div.class="menu";
						div.setAttribute("id","1111");
						div.setAttribute("class","menu");
						div.innerHTML="<a href='./jsp/modelPage/createNewAccount.jsp' target='mainFrame'>创建新用户</a></div>";
						target.parentNode.appendChild(div);
						div=document.createElement("div");
						div.class="menu";
						div.setAttribute("id","2222");
						div.setAttribute("class","menu");
						div.innerHTML="<a href='getAllUser.action' target='mainFrame'>用户列表</a></div>";
						target.parentNode.appendChild(div);
					}
				});
	}
}






function resetPwd(){

		var oldP = document.getElementById("oldPwd").value;
		if (oldP == "") {
			alert("请正常填写旧密码！");
			return false;
		}
		
		var newP = document.getElementById("newPwd").value;
		if (newP == "") {
			alert("请填写新密码！");
			return false;
		}

		if(oldP == newP){
			alert("新密码不能与原密码相同！");
			return false;
		}
		
		var cnfP = document.getElementById("cnfPwd").value;
		if (newP != cnfP) {
			alert("两次填写的新密码不一致！");
			return false;
		}


		         	$.ajax({
		         		type: "post",
		         		url: "resetPassword.action",     
		         		data: {oldPwd:oldP,newPwd:newP,cnfPwd:cnfP},    
		         		success: function(data) {
		         			alert("修改成功！");
//		         			var dataObj = eval('(' + data + ')');
//		         			var dataObj = eval('(' + data + ')');  
//		         			alert(dataObj.returnMsg);
		         		},
		         		error: function(data) {
		         			alert("修改失败,请确认原密码是否正确！");
//		         			var dataObj = eval('(' + data + ')');
//		         			alert(dataObj.returnMsg);
		         		}
		         	});

	
}



//显示灰色 jQuery 遮罩层 
function showBg() {
	var bh = $("body").height();
	var bw = $("body").width();
	$("#fullbg").css({
		height : bh,
		width : bw,
		display : "block"
	});
	$("#dialog").show();
}
//关闭灰色 jQuery 遮罩 
function closeBg() {
	$("#fullbg,#dialog").hide();
}


