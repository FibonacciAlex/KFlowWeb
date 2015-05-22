package com.model.template;

public class ItemTemplate {

	
	private long id;
	
	private String itemName;
	
	private String desc;

	
	
	public ItemTemplate() {
	}
	
	

	public ItemTemplate(long id, String itemName, String desc) {
		this.id = id;
		this.itemName = itemName;
		this.desc = desc;
	}



	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}
	
	
}
