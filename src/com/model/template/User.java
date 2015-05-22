package com.model.template;

/**
 * 
 * @author Alex
 *
 */
public class User {

	/**超级管理员*/
	public final static  byte ADMIN_USER = 1;
	/**普通管理员*/
	public final static byte COMMON_USER = 2;
	
	private boolean admin = false;
	
	private String userName;
	
	private String pwd;
	
	/**用户类型  根据类型确定权限*/
	private byte userType;

	private String otherScript;
	
	
	

	public User() {
	}
	
	

	public String getOtherScript() {
		return otherScript;
	}



	public void setOtherScript(String otherScript) {
		this.otherScript = otherScript;
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

	public byte getUserType() {
		return userType;
	}

	public void setUserType(byte userType) {
		this.userType = userType;
	}

	
	public boolean isAdmin(){
		if(userType == ADMIN_USER){
			admin = true;
		}else{
			admin = false;
		}
		return admin;
	}
	
	public String getAdmin(){
		return "true";
	}
	
}
