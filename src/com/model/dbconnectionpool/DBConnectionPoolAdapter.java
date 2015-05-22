package com.model.dbconnectionpool;

import org.apache.log4j.Logger;

import com.model.dbconnectionpool.mysql.DBConnectionFactory;
import com.model.dbconnectionpool.mysql.DefineDataSourceManagerIF;



/**
 * 数据库连接池管理器，可以通过方法{@link #getDBConnectionPool()}获取mysql连接池，通过
 * {@link #getHsConnectionPoolManager()} 获取handlerSocket连接池
 * 
 * @author Administrator
 */
public class DBConnectionPoolAdapter {

	private static final Logger logger = Logger.getLogger(DBConnectionPoolAdapter.class);

	private static final String dBConPoolUrl = "proxool_pool_mysql.properties";
	private static DefineDataSourceManagerIF dBConPool;
	private static boolean isInitDbPool = false;

	public static void init() throws Exception {
		if(isInitDbPool){
			return;
		}
		
		logger.info("！！！数据库配置加载开始！！！");
		String path = DBConnectionPoolAdapter.class.getClassLoader().getResource("").toURI().getPath(); 
		dBConPool = DBConnectionFactory.getInstance().newProxoolDataSourceInstance(path + dBConPoolUrl);
		isInitDbPool = true;
		logger.info("！！！数据库配置加载完成！！！");
	}


	public static DefineDataSourceManagerIF getDBConnectionPool() {
		return dBConPool;
	}

}
