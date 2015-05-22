package com.model.template;

public class TreasureFlowTemplate {
	
	private long ownerID;
	
	private int propertyType;
	
	private String templateID;
	
	private String UUID;
	
	private  int isAdd;
	
	private String startTime;
	
	private String endTime;
	
	private String tips;

	private String recordTime;
	
	public String getRecordTime() {
		return recordTime;
	}



	public void setRecordTime(String recordTime) {
		this.recordTime = recordTime;
	}



	public TreasureFlowTemplate() {
	}

	
	
	public int getPropertyType() {
		return propertyType;
	}


	public void setPropertyType(int propertyType) {
		this.propertyType = propertyType;
	}



	public long getOwnerID() {
		return ownerID;
	}

	public void setOwnerID(long ownerID) {
		this.ownerID = ownerID;
	}

	public String getTemplateID() {
		return templateID;
	}

	public void setTemplateID(String templateID) {
		this.templateID = templateID;
	}

	public String getUUID() {
		return UUID;
	}

	public void setUUID(String uUID) {
		UUID = uUID;
	}

	public int getIsAdd() {
		return isAdd;
	}

	public void setIsAdd(int isAdd) {
		this.isAdd = isAdd;
	}


	public String getStartTime() {
		return startTime;
	}



	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}



	public String getEndTime() {
		return endTime;
	}



	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}



	public String getTips() {
		return tips;
	}

	public void setTips(String tips) {
		this.tips = tips;
	}
	
	
	

}
