package com.model.service;

import java.util.List;

import com.model.exception.ServiceException;
import com.model.template.User;


/**
 * 用户接口  处理和用户相关的业务
 * @author Alex 2014-09-09
 *
 */
public interface UserService {

	/**
	 * 查找用户
	 * @param userName
	 * @return
	 * @throws ServiceException
	 */
	User find(String userName) throws ServiceException;
	
	/**
	 * 增加一个用户
	 * @param user
	 * @return
	 * @throws ServiceException
	 */
	boolean add(User user) throws ServiceException;
	
	/**
	 * 更新一个用户信息
	 * @param user
	 * @throws ServiceException
	 */
	boolean update(User user) throws ServiceException;
	
	/**
	 * 删除一个用户
	 * @param userName
	 * @return
	 * @throws ServiceException
	 */
	boolean delete(String userName) throws ServiceException;
	
	/**
	 * 用户重置密码
	 * @param userName
	 * @param newPwd
	 * @return
	 */
	boolean resetUserPassword(String userName, String newPwd);
	
	/**
	 * 查找所有用户信息
	 * @return
	 */
	List<User> getAllUser();
}
