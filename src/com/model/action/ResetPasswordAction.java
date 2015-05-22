package com.model.action;

import java.util.Map;

import com.model.service.ServiceSupportFactory;
import com.model.template.User;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class ResetPasswordAction extends ActionSupport{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	
	private String oldPwd;
	
	private String newPwd;
	
	private String cnfPwd;
	
//	private String returnMsg;
//	
//	
//	public String getReturnMsg() {
//		return returnMsg;
//	}
//
//	public void setReturnMsg(String returnMsg) {
//		this.returnMsg = returnMsg;
//	}

	public String getOldPwd() {
		return oldPwd;
	}

	public void setOldPwd(String oldPwd) {
		this.oldPwd = oldPwd;
	}

	public String getNewPwd() {
		return newPwd;
	}

	public void setNewPwd(String newPwd) {
		this.newPwd = newPwd;
	}

	public String getCnfPwd() {
		return cnfPwd;
	}

	public void setCnfPwd(String cnfPwd) {
		this.cnfPwd = cnfPwd;
	}

	@Override
	public String execute() throws Exception {
		
		if(!newPwd.equals(cnfPwd)){
//			returnMsg = 0;
//			returnMsg = "两次输入的新密码不一致！";
			return ERROR;
		}
		
		Map<String, Object> session = ActionContext.getContext().getSession();
		User u = (User) session.get(UserLoginAction.ROLE_ATT);
		
		if(!u.getPwd().equals(oldPwd)){
//			returnMsg = 1;
//			returnMsg = "旧密码不正确！";
			return ERROR;
		}
		
		
		
		boolean suc = ServiceSupportFactory.getUserService().resetUserPassword(u.getUserName(), newPwd);
		if(suc){
			u.setPwd(newPwd);
//			System.out.println("----------------------------------------------------success!");
//			returnMsg = "修改成功";
//			returnMsg = 2;
		}
		
		return SUCCESS;
	}
	
	
	
	
	

}
