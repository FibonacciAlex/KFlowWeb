package com.model.template;

import com.model.util.UtilTools;

public class OtherFlowTemplate {
	
	private long ownerID;
	
	private int flowType;
	
	private String flowName;
	
	private String recordTime;
	
	private String tips;

	private String startTime;
	
	private String endTime;
	
	
	public OtherFlowTemplate() {
	}

	
	public String getFlowName() {
		return flowName;
	}


	public void setFlowName(String flowName) {
		this.flowName = flowName;
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

	public long getOwnerID() {
		return ownerID;
	}

	public void setOwnerID(long owerID) {
		this.ownerID = owerID;
	}

	public int getFlowType() {
		return flowType;
	}

	public void setFlowType(int flowType) {
		this.flowType = flowType;
		setFlowName(UtilTools.flowMap.get(flowType));
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
	
	

}
