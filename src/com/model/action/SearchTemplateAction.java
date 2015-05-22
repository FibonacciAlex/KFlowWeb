package com.model.action;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.model.template.ItemTemplate;
import com.model.util.UtilTools;
import com.opensymphony.xwork2.ActionSupport;

public class SearchTemplateAction extends ActionSupport{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	private String name;

	private String msg;

	private List<ItemTemplate> templateList;
	
	public String getMsg() {
		return msg;
	}


	public void setMsg(String msg) {
		this.msg = msg;
	}


	public List<ItemTemplate> getTemplateList() {
		return templateList;
	}


	public void setTemplateList(List<ItemTemplate> templateList) {
		this.templateList = templateList;
	}



	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	@Override
	public String execute() throws Exception {
		
		if(getName().equals("")){
			msg = "请输入目标道具名！";
			return ERROR;
		}
		ItemTemplate template;
		List<ItemTemplate> tempList = new ArrayList<ItemTemplate>();
		Iterator<Entry<Long, ItemTemplate>> iterator = UtilTools.itemMap.entrySet().iterator();
		while (iterator.hasNext()) {
			Map.Entry<Long,ItemTemplate> entry = (Map.Entry<Long,ItemTemplate>) iterator.next();
			template = entry.getValue();
			if(template.getItemName().equals(name)){
				tempList.add(template);
			}
		}
		
		if(tempList.isEmpty()){
			msg = "骚年，找不到你的道具，麻烦确定这个道具是否存在啦~";
			return ERROR;
		}
		setTemplateList(tempList);
		return SUCCESS;
	}
}
