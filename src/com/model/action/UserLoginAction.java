package com.model.action;

import java.util.List;
import java.util.Map;

import com.model.dbconnectionpool.DBConnectionPoolAdapter;
import com.model.service.ServiceSupportFactory;
import com.model.service.UserService;
import com.model.template.User;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

/**
 * 
 * @author Alex
 *
 */
public class UserLoginAction  extends ActionSupport{


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public final static String ROLE_ATT = "role";
	
	private String tips	;
	
	private String userName;
	
	private String pwd;
	

	private List<User> userList;
	
	
	
	public List<User> getUserList() {
		return userList;
	}

	public void setUserList(List<User> userList) {
		this.userList = userList;
	}

	public String getTips() {
		return tips;
	}

	public void setTips(String tips) {
		this.tips = tips;
	}

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

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String execute() {
		
		String result = "";
		if(userName == null || pwd == null || userName.equals("") || pwd.equals("")){
			return "error";
		}
		
		
		User u = ServiceSupportFactory.getUserService().find(userName);
		if(u!= null && u.getPwd().equals(pwd)){
			Map<String, Object> session = ActionContext.getContext().getSession();
			
			session.put(ROLE_ATT, u);
			System.out.println("角色登录成功");
			result = SUCCESS;
			
		}else{
			result = ERROR;
			setTips("亲，你的账号或密码错了~");
		}
        return result;  
	}

	public String getUsers(){
		
		List<User> us = ServiceSupportFactory.getUserService().getAllUser();
		if(!us.isEmpty()){
			userList = us;
		}
		
		return SUCCESS;
	}
	
}
