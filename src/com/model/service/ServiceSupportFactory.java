package com.model.service;

import com.model.service.impl.SearchServiceImpl;
import com.model.service.impl.UserServiceImpl;

public class ServiceSupportFactory {

	
	private static UserServiceImpl userServiceImp = new UserServiceImpl();
	
	private  static SearchService searchServiceImp = new SearchServiceImpl();
	
	
	public static UserServiceImpl getUserService(){
		if(userServiceImp == null){
			userServiceImp = new UserServiceImpl();
		}
		return userServiceImp;
	}
	
	public static SearchService getSearchServiceImp(){
		if(searchServiceImp == null){
			searchServiceImp = new SearchServiceImpl();
		}
		return searchServiceImp;
	}
}
