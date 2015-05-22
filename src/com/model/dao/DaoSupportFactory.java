package com.model.dao;

import com.model.dao.impl.FlowDaoImpl;
import com.model.dao.impl.UserDaoImpl;


/**
 * 数据访问对象工厂
 * @author Alex
 *
 */
public class DaoSupportFactory {

	
	private static UserDaoInterface userDaoImpl = new UserDaoImpl();
	
	private static FlowDaoInterface flowDaoImpl = new FlowDaoImpl();
	
	public static UserDaoInterface getUserDaoInterface(){
		if(userDaoImpl == null){
			userDaoImpl = new UserDaoImpl();
		}
		return userDaoImpl;
	}
	
	public static FlowDaoInterface getFlowDaoInterface(){
		if(flowDaoImpl == null){
			flowDaoImpl = new FlowDaoImpl();
		}
		return flowDaoImpl;
	}
	
}
