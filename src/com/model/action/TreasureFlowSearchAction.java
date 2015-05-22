package com.model.action;

import java.util.List;

import com.model.service.ServiceSupportFactory;
import com.model.template.TreasureFlowTemplate;
import com.model.util.UtilTools;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class TreasureFlowSearchAction extends ActionSupport{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int pageNo = 1;
	private int pageSize = 30;
	private int recordCount;
	private String url;
	private int status;
	
	private  int serverID;
	private String serverName;
	private long roleID;
	
	private String templateID;
	
	private String startTime;
	
	private String  endTime;
	
	private int add;

	
	private List<TreasureFlowTemplate> recordList;
	
	public String getServerName() {
		return serverName;
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

	public void setServerName(String serverName) {
		this.serverName = serverName;
	}

	public List<TreasureFlowTemplate> getRecordList() {
		return recordList;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}


	public void setRecordList(List<TreasureFlowTemplate> recordList) {
		this.recordList = recordList;
	}

	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getRecordCount() {
		return recordCount;
	}

	public void setRecordCount(int recordCount) {
		this.recordCount = recordCount;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public int getServerID() {
		return serverID;
	}

	public void setServerID(int serverID) {
		this.serverID = serverID;
	}

	public long getRoleID() {
		return roleID;
	}

	public void setRoleID(long roleID) {
		this.roleID = roleID;
	}

	public String getTemplateID() {
		return templateID;
	}

	public void setTemplateID(String templateID) {
		this.templateID = templateID;
	}


	public int getAdd() {
		return add;
	}

	public void setAdd(int add) {
		this.add = add;
	}
	
	@Override
	public String execute() throws Exception {
		String result="";
		
		TreasureFlowTemplate temp = new TreasureFlowTemplate();
		temp.setOwnerID(getRoleID());
		temp.setIsAdd(getAdd());
		temp.setStartTime(startTime);
		temp.setTemplateID(getTemplateID());
		temp.setEndTime(endTime);
		
		List<TreasureFlowTemplate> list = ServiceSupportFactory.getSearchServiceImp().getTreasureFlow(serverID, temp);
		serverName = UtilTools.serverMap.get(serverID);
		
		if(list != null){
			pageSize = list.size() < pageSize ? list.size() : pageSize;
			result = SUCCESS;
			recordList = list.subList(0, pageSize);
			ActionContext.getContext().getSession().put(ActionConst.REQ_TEMP, temp);
			recordCount = list.size();
		}else{
			result = ERROR;
			setStatus(1);
		}
		
		return result;
		
	}
	
	public String cutPage(){
		String result = "";
		int minCount = (pageNo - 1) * pageSize;
		int maxCount = pageNo * pageSize > recordCount  ? recordCount : pageNo * pageSize;
		TreasureFlowTemplate condition = (TreasureFlowTemplate)ActionContext.getContext().getSession().get(ActionConst.REQ_TEMP);
		//还原条件
		if(condition.getOwnerID() > 0){
			setRoleID(condition.getOwnerID());
		}
		setStartTime(condition.getStartTime());
		setEndTime(condition.getEndTime());
		setTemplateID(condition.getTemplateID());
		serverName = UtilTools.serverMap.get(serverID);
		
		List<TreasureFlowTemplate> list = ServiceSupportFactory.getSearchServiceImp().getTreasureFlowByPage(serverID,condition,minCount,maxCount-minCount);
		
		if(list  != null){
			result = SUCCESS;
			recordList = list;
		}else{
			result = ERROR;
		}
		
		return result;
		
	}
	
}
