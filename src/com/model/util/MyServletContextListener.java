package com.model.util;

import java.util.concurrent.TimeUnit;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.log4j.Logger;

import com.model.dbconnectionpool.DBConnectionPoolAdapter;
import com.model.util.UtilTools.CheckConfigModifyTask;
import com.model.util.UtilTools.ItemTemplateExcelModifyTask;

public class MyServletContextListener implements ServletContextListener{

	private final static Logger log = Logger.getLogger(MyServletContextListener.class);
	
	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		
	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {

		try {
			//初始化一下数据库连接
			DBConnectionPoolAdapter.init();
			
			//初始化服务器列表
			UtilTools.initServerMap();
			
			//初始化道具模板
			UtilTools.initItemTemplate();
			
			
			UtilTools.timer.newTimeSignal(new CheckConfigModifyTask(), 30, TimeUnit.SECONDS);

			//启动excel文件加载
			UtilTools.timer.newTimeSignal(new ItemTemplateExcelModifyTask(), 30, TimeUnit.SECONDS);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			log.error("初始化数据连接时出现异常！");
			System.exit(0);
		}
	}

	
	
}
