package com.model.action;

import com.model.service.ServiceSupportFactory;
import com.model.template.User;
import com.opensymphony.xwork2.ActionSupport;

public class CreateAccountAction extends ActionSupport{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	private String userName;
	
	private String pwd;
	
	private String tips;
	
	private byte type;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getTips() {
		return tips;
	}

	public void setTips(String tips) {
		this.tips = tips;
	}

	public byte getType() {
		return type;
	}

	public void setType(byte type) {
		this.type = type;
	}

	@Override
	public String execute() throws Exception {

		if(userName == null || userName.equals("") || pwd == null || pwd.equals("")){
			tips = "请输入用户名和密码！";
			return ERROR;
		}
		
		User user = ServiceSupportFactory.getUserService().find(userName);
		if(user != null){
			tips = "这个用户名已经被占用了，亲你还是换一个吧~";
			return ERROR;
		}
		
		
		User newUser = new User();
		newUser.setUserName(userName);
		newUser.setPwd(pwd);
		newUser.setUserType(type);
		newUser.setOtherScript("无");
		
		boolean add = ServiceSupportFactory.getUserService().add(newUser);
		if(!add){
			tips = "增加用户不成功，请稍后再试！";
			return ERROR;
		}
		tips = "用户增加成功！";
		return SUCCESS;
	}
	
}
