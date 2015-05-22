package com.model.service.impl;

import java.util.List;

import com.model.dao.DaoSupportFactory;
import com.model.exception.ServiceException;
import com.model.service.UserService;
import com.model.template.User;

public class UserServiceImpl  implements UserService{

	@Override
	public User find(String userName) throws ServiceException {
		User user = DaoSupportFactory.getUserDaoInterface().findUserByName(userName);
		return user;
	}

	@Override
	public boolean add(User user) throws ServiceException {
		boolean addUser = DaoSupportFactory.getUserDaoInterface().addUser(user);
		return addUser;
	}

	@Override
	public boolean update(User user) throws ServiceException {
		return true;
	}

	
	
	
	@Override
	public boolean delete(String userName) throws ServiceException {
		return false;
	}

	@Override
	public boolean resetUserPassword(String userName, String newPwd) {

		boolean reset = DaoSupportFactory.getUserDaoInterface().resetUserPwd(userName, newPwd);
		
		return reset;
	}

	@Override
	public List<User> getAllUser() {
		
		List<User> b = DaoSupportFactory.getUserDaoInterface().getAllUser();
		
		return b;
	}

}
