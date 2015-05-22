package com.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import org.apache.log4j.Logger;

import com.model.dbconnectionpool.DBConnectionPoolAdapter;
import com.model.dbconnectionpool.mysql.DefineDataSourceManagerIF;


/**
 * 基础DAO 封装了数据访问的实现
 * @author Alex
 */
public class BaseDao {

	
	private final static Logger LOG = Logger.getLogger(BaseDao.class);
	
	private DefineDataSourceManagerIF dbPool;

	public BaseDao() {
		try {

			dbPool = DBConnectionPoolAdapter.getDBConnectionPool();
			
		} catch (Exception e) {
			e.printStackTrace();
			LOG.error("获取数据源时出现异常！！" + e.getMessage());
		}
	}
	
	/**
	 * 返回一个连接对象
	 * @return
	 * @throws Exception
	 */
	public Connection getConnection() throws Exception{
		try {
			
			return dbPool.getConnection();
			
		} catch (Exception e) {
			LOG.error("获取连接对象时出现异常！！！"  + e.getMessage());
		}
		return null;
	}
	
	public PreparedStatement getPreState(Connection con, String sql) throws Exception{
		try {
			return dbPool.writeStatement(con, sql);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}
	
	public void closedConnetion(Connection con, PreparedStatement ps){
		if(con == null || ps == null){
			return ;
		}
		try {
			dbPool.closePreparedStatement(ps);
			dbPool.closeConnection(con);
		} catch (Exception e) {
			e.printStackTrace();
			LOG.error("关闭数据库连接对象时出现异常！" + e.getMessage());
		}
		
	}
	
}
