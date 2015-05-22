package com.model.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.model.template.User;
import com.model.template.ItemTemplate;
import com.model.util.UtilTools;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class ServerListAction extends ActionSupport{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	private HashMap<Integer, String> serverMap;


	private HashMap<Integer, String>currencyList;
	
	private HashMap<Integer, String>flowList;
	
	private boolean isAdmin;


	public HashMap<Integer, String> getFlowList() {
		return flowList;
	}


	public void setFlowList(HashMap<Integer, String> flowList) {
		this.flowList = flowList;
	}


	public HashMap<Integer, String> getServerMap() {
		return serverMap;
	}


	public void setServerMap(HashMap<Integer, String> serverMap) {
		this.serverMap = serverMap;
	}
	
	public HashMap<Integer, String> getCurrencyList() {
		return currencyList;
	}


	public void setCurrencyList(HashMap<Integer, String> currencyList) {
		this.currencyList = currencyList;
	}


	public boolean isAdmin() {
		return isAdmin;
	}


	public void setAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}


	public String loadServer(){
		serverMap = UtilTools.serverMap;
		return SUCCESS;
	}
	
	public String loadCurrency(){
		currencyList = UtilTools.currencyMap;
		return SUCCESS;
	}
	
	public String loadFlow(){
		flowList = UtilTools.flowMap;
		return SUCCESS;
	}
	
	public String loadMenu(){
		User user = (User) ActionContext.getContext().getSession().get(UserLoginAction.ROLE_ATT);
		if(user == null){
			return ERROR;
		}
		setAdmin(user.isAdmin());
		if(!isAdmin){
			return ERROR;
		}
		return SUCCESS;
		
	}

}
