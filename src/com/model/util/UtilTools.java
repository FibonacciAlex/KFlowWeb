package com.model.util;

import java.io.File;
import java.rmi.ServerException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.TimeUnit;

import org.jdom.Document;
import org.jdom.Element;

import com.model.template.ItemTemplate;
import com.model.util.KGameExcelTable.KGameExcelRow;
import com.model.util.timer.KGameTimer;
import com.model.util.timer.KGameTimeSignal;
import com.model.util.timer.KGameTimerTask;

public class UtilTools {

	/**服务器列表key=serverID,value=serverName*/
	public static HashMap<Integer, String> serverMap = new HashMap<Integer, String>(); 
	
	/**货币列表key=currencyType, value=currencyName*/
	public static HashMap<Integer, String> currencyMap = new HashMap<Integer, String>();
	
	/**其他流水列表*/
	public static HashMap<Integer, String>flowMap = new HashMap<Integer, String>();
	
	public static HashMap<Long, ItemTemplate> itemMap = new HashMap<Long, ItemTemplate>();
	
	private static String SYSTEM_CONFIG_FILE = "config.xml";
	
	private static String ITEM_TEMPLATE_FILE = "ItemTemplate.xls";
	
	private static String CONFIG_PATH;
	
	private static String ITEM_TEMPLATE_PATH;
	
	/**配置文件上次加载时间*/
	private static long FILE_LAST_MODIFY_TIME = 0;
	/**道具模板上次加载时间*/
	private static long ITEM_TEMPLATE_LAST_MODIFY_TIME = 0;
	
	public static KGameTimer timer = null;
	
	public static void initServerMap(){
		try {
			System.out.println("--------------------------------------------------start  init server map!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
			String uri = UtilTools.class.getClassLoader().getResource("").toURI().getPath();
			Document doc = XmlUtil.openXml(uri+ SYSTEM_CONFIG_FILE);
			Element rootElement = doc.getRootElement();
			List<Element> children = rootElement.getChild("serverList").getChildren();
			for (Element elem : children) {
				String id = elem.getAttributeValue("id");
				String serverName = elem.getTextTrim();
				serverMap.put(Integer.parseInt(id), serverName);
			}
			
			children = rootElement.getChild("currencyList").getChildren();
			for (Element elem : children) {
				String type = elem.getAttributeValue("type");
				String currencyName = elem.getTextTrim();
				currencyMap.put(Integer.parseInt(type), currencyName);
			}
			
			children = rootElement.getChild("otherFlowType").getChildren();
			for (Element elem : children) {
				String type = elem.getAttributeValue("type");
				String flowName = elem.getTextTrim();
				flowMap.put(Integer.parseInt(type), flowName);
			}

			
			// 启动文件扫描时效
			if (timer == null) {
				timer = new KGameTimer(rootElement.getChild("Timer"));
				CONFIG_PATH = uri + SYSTEM_CONFIG_FILE;
				ITEM_TEMPLATE_PATH = uri + ITEM_TEMPLATE_FILE;
			}
			File file = new File(CONFIG_PATH);
			FILE_LAST_MODIFY_TIME = file.lastModified();

			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void initItemTemplate(){
		
		try {
			System.out.println("--------------------------------------------------start init itemTemplate!!!!!!!!!!!!");
			String uri = UtilTools.class.getClassLoader().getResource("").toURI().getPath();
			KGameExcelFile excelFile = new KGameExcelFile(uri + ITEM_TEMPLATE_FILE);
			String[] sheetNames = excelFile.getAllSheetNames();
			ItemTemplate template;
			for (String sn : sheetNames) {
				KGameExcelTable table = excelFile.getTable(sn, 1);
				//遍历表内所有行
				KGameExcelRow[] allDataRows = table.getAllDataRows();
				for (KGameExcelRow row : allDataRows) {
					long id = row.getLong("id");
					String name = row.getData("name");
					String desc = row.getData("desc");
					template = new ItemTemplate(id, name, desc);
					UtilTools.itemMap.put(id, template);
				}
			}
			
			File file = new File(ITEM_TEMPLATE_PATH);
			ITEM_TEMPLATE_LAST_MODIFY_TIME = file.lastModified();
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	
	/**
	 * 转换日期
	 * @param data
	 * @param format
	 * @return
	 */
	public static long paresDateStringToLong(String data,String format) {
		
		DateFormat df = new SimpleDateFormat(format);
		long time = 0;
		try {
			time = df.parse(data).getTime();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return time;
	}
	
	
	static class CheckConfigModifyTask implements KGameTimerTask{
		
		public CheckConfigModifyTask(){
		}

		@Override
		public String getName() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Object onTimeSignal(KGameTimeSignal timeSignal)
				throws ServerException {
			//检查一下文件是否有改变  如果改则重新加载
			File file = new File(CONFIG_PATH);
			if(FILE_LAST_MODIFY_TIME  != file.lastModified()){
				System.out.println("--------------------------------------------------------发现config配置文件有改变，重新加载！");
				//先清除一下缓存
				clearCach();
				initServerMap();
			}
			timer.newTimeSignal(this, 30, TimeUnit.SECONDS);
			
			return null;
		}

		private void clearCach() {
			serverMap.clear();
			currencyMap.clear();
			flowMap.clear();
			
		}

		@Override
		public void done(KGameTimeSignal timeSignal) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void rejected(RejectedExecutionException e) {
			// TODO Auto-generated method stub
			
		}
		
	}
	
	
	static class ItemTemplateExcelModifyTask implements KGameTimerTask{

		@Override
		public String getName() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Object onTimeSignal(KGameTimeSignal timeSignal)
				throws ServerException {
			
			File file = new File(ITEM_TEMPLATE_PATH);
			if(ITEM_TEMPLATE_LAST_MODIFY_TIME != file.lastModified()){
				System.out.println("-------------------------------------------------发现道具模板有改变，重新加载！");
				itemMap.clear();
				initItemTemplate();
			}else{
				//启动excel文件加载
				timer.newTimeSignal(new ItemTemplateExcelModifyTask(), 30, TimeUnit.SECONDS);
			}
			
			return null;
		}

		@Override
		public void done(KGameTimeSignal timeSignal) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void rejected(RejectedExecutionException e) {
			// TODO Auto-generated method stub
			
		}
		
	}
}
