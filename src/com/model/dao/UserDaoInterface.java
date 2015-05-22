package com.model.dao;

import java.util.List;

import com.model.template.User;

/**
 * 用户数据访问对象接口
 * @author Alex
 *
 */
public interface UserDaoInterface {

	
	/**
	 * 根据角色名查找角色
	 * @param userName
	 * @return
	 */
	public User findUserByName(String userName);

	/**
	 * 增加用户
	 * @param user
	 * @return
	 */
	public boolean addUser(User user);
	
	
	/**
	 * 用户重置密码
	 * @param userName
	 * @param newPwd
	 * @return
	 */
	public boolean resetUserPwd(String userName, String newPwd);
	
	
	/**
	 * 查找用户列表
	 * @return
	 */
	public List<User> getAllUser();
	
}
