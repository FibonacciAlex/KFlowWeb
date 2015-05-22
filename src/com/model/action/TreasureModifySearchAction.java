package com.model.action;

import java.util.ArrayList;
import java.util.List;

import com.model.service.ServiceSupportFactory;
import com.model.template.TreasureModifyTemplate;
import com.model.util.UtilTools;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class TreasureModifySearchAction extends ActionSupport{

	
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

	private String UUID;
	
	private String startTime;
	
	private String endTime;
	
	private List<TreasureModifyTemplate> recordList;

	public String getServerName() {
		return serverName;
	}

	public void setServerName(String serverName) {
		this.serverName = serverName;
	}

	public long getRoleID() {
		return roleID;
	}

	public void setRoleID(long roleID) {
		this.roleID = roleID;
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

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getServerID() {
		return serverID;
	}

	public void setServerID(int serverID) {
		this.serverID = serverID;
	}

	public String getUUID() {
		return UUID;
	}

	public void setUUID(String uUID) {
		UUID = uUID;
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

	public List<TreasureModifyTemplate> getRecordList() {
		return recordList;
	}

	public void setRecordList(List<TreasureModifyTemplate> recordList) {
		this.recordList = recordList;
	}

	@Override
	public String execute() throws Exception {
		String result="";
		
		TreasureModifyTemplate temp = new TreasureModifyTemplate();
		temp.setUUID(getUUID());
		temp.setStartTime(startTime);
		temp.setEndTime(endTime);
		temp.setRoleID(getRoleID());
		
		List<TreasureModifyTemplate> list = ServiceSupportFactory.getSearchServiceImp().getTreasureModify(serverID, temp);
		serverName = UtilTools.serverMap.get(serverID);
		
		if(list != null &&!list.isEmpty()){
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
		TreasureModifyTemplate condition = (TreasureModifyTemplate)ActionContext.getContext().getSession().get(ActionConst.REQ_TEMP);
		
		serverName = UtilTools.serverMap.get(serverID);
		if(condition.getUUID() != null){
			UUID = condition.getUUID();
		}
		if(condition.getStartTime() != null){
			startTime = condition.getStartTime();
		}
		if(condition.getEndTime() != null){
			endTime = condition.getEndTime();
		}
		
		
		if(condition.getRoleID() > 0){
			roleID = condition.getRoleID();
		}

		List<TreasureModifyTemplate> list = ServiceSupportFactory.getSearchServiceImp().getTreasureModifyByPage(serverID, condition, minCount, maxCount - minCount);
		if(list != null){
			result = SUCCESS;
			recordList = list;
		}
		return result;
		
	}
	
	
	
	
	
	
	
}
