package com.model.template;

import com.model.util.UtilTools;



public class CurrencyFlowTemplate{

	
	
	
	/**货币所有者id*/
	private long ownerID;
	
	/**货币类型*/
	private int currencyType;
	
	/**货币名*/
	private String currencyName;
	
	/**货币金额*/
	private long value;
	
	/**流水类型，标志进或出*/
	private int isAdd;
	
	/**流水产生时间*/
	private String recordTime;
	
	/**备注*/
	private String tips;


	private String startTime;
	
	private String endTime;

	public CurrencyFlowTemplate() {
	}

	public long getOwnerID() {
		return ownerID;
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

	public void setOwnerID(long ownerID) {
		this.ownerID = ownerID;
	}

	public int getCurrencyType() {
		return currencyType;
	}

	public void setCurrencyType(int currencyType) {
		this.currencyType = currencyType;
	}

	public long getValue() {
		return value;
	}

	public void setValue(long value) {
		this.value = value;
	}

	public int getIsAdd() {
		return isAdd;
	}

	public void setIsAdd(int isAdd) {
		this.isAdd = isAdd;
	}

	public String getRecordTime() {
		return recordTime;
	}

	public void setRecordTime(String recordTime) {
		this.recordTime = recordTime;
	}

	public String getTips() {
		return tips;
	}

	public void setTips(String tips) {
		this.tips = tips;
	}

	public String getCurrencyName() {
		currencyName = UtilTools.currencyMap.get(currencyType);
		return currencyName;
	}

}
