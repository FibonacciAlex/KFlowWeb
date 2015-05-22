package com.model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;




import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.model.dao.BaseDao;
import com.model.dao.UserDaoInterface;
import com.model.template.User;

public class UserDaoImpl implements UserDaoInterface {

	private static final Logger logger = Logger.getLogger(UserDaoImpl.class);
	

	@Override
	public User findUserByName(String userName) {

		String sql = "select user_Name, pwd, user_Type, other_Script from manager_record where user_Name=?";
		
		User u = null;
		BaseDao dao = new BaseDao();
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			
			conn = dao.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, userName);
			try{
			ResultSet rst = ps.executeQuery();
				if(rst.next()){
					u = new User();
					u.setUserName(rst.getString("user_Name"));
					u.setPwd(rst.getString("pwd"));
					u.setUserType(rst.getByte("user_type"));
					u.setOtherScript(rst.getString("other_Script"));
				}
			}catch(Exception e){
				logger.error("查询登录角色数据时出现异常！");
				
			}finally{
				ps.clearParameters();
			}
		} catch (Exception e) {
			logger.error("查询登录角色数据时出现异常！");
		}finally{
			dao.closedConnetion(conn, ps);
		}
		return u;
	}


	@Override
	public boolean addUser(User user) {

		boolean result = false;
		
		if(user == null){
			return result;
		}
		
		String sql = "insert into manager_record (user_Name,pwd,user_type,other_Script) values (?,?,?,?)";
		BaseDao dao = new BaseDao();
		Connection conn = null;
		PreparedStatement ps = null;
		
		try {
			conn = dao.getConnection();
			ps = dao.getPreState(conn, sql);
			
			ps.setString(1, user.getUserName());
			ps.setString(2, user.getPwd());
			ps.setByte(3, user.getUserType());
			ps.setString(4, user.getOtherScript());
			ps.execute();
			result = true;
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("增加用户时出现异常!");
			e.printStackTrace();
		}finally{
			dao.closedConnetion(conn, ps);
		}
		return result;
	}


	@Override
	public boolean resetUserPwd(String userName, String oldPwd) {

		String sql = "update manager_record set pwd ='" +oldPwd+ "' where user_Name = '" +userName +"'";

		BaseDao dao = new BaseDao();
		
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			
			conn = dao.getConnection();
			ps = dao.getPreState(conn, sql);
			ps.execute();
			
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("修改用户密码时出现异常！");
			e.printStackTrace();
		}finally{
			dao.closedConnetion(conn, ps);
		}
		
		return true;
	}


	@Override
	public List<User> getAllUser() {
		
		String sql = "select * from manager_record";
		
		BaseDao dao = new BaseDao();
		
		Connection conn = null;
		PreparedStatement ps = null;
		List<User> list = null;
		User u = null;
		
		try {
			list = new ArrayList<User>();
			conn = dao.getConnection();
			ps = dao.getPreState(conn, sql);
			ResultSet rst = ps.executeQuery();
			while(rst.next()){
				u = new User();
				u.setUserName(rst.getString("user_Name"));
				u.setPwd(rst.getString("pwd"));
				u.setUserType(rst.getByte("user_type"));
				u.setOtherScript(rst.getString("other_Script"));
				list.add(u);
			}
		} catch (Exception e) {

			logger.error("查询用户列表时出现异常！");
			e.printStackTrace();
		}finally{
			dao.closedConnetion(conn, ps);
		}
		
		return list;
	}
}
