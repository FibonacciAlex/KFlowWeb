package com.model.action;

import java.util.List;

import com.model.service.ServiceSupportFactory;
import com.model.template.CurrencyFlowTemplate;
import com.model.template.ExpFlowTemplate;
import com.model.util.UtilTools;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

/**
 * 
 * @author Alex
 *
 */
public class ExpFlowSearchAction extends ActionSupport{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private  int serverID;
	private String serverName;
	private int pageNo = 1;
	private int pageSize = 30;
	private int recordCount;
	private String url;
	private int status;
	
	private long roleID;
	
	private String startTime;
	
	private String endTime;

	
	

	private List<ExpFlowTemplate> expFlowList;
	

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

	public String getServerName() {
		return serverName;
	}

	public void setServerName(String serverName) {
		this.serverName = serverName;
	}

	public List<ExpFlowTemplate> getExpFlowList() {
		return expFlowList;
	}

	public void setExpFlowList(List<ExpFlowTemplate> expFlowList) {
		this.expFlowList = expFlowList;
	}

	public int getServerID() {
		return serverID;
	}

	public void setServerID(int serverID) {
		this.serverID = serverID;
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

	public long getRoleID() {
		return roleID;
	}

	public void setRoleID(long roleID) {
		this.roleID = roleID;
	}


	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	@Override
	public String execute() throws Exception {
		String result="";
		ExpFlowTemplate temp = new ExpFlowTemplate();
		temp.setOwnerID(getRoleID());
		temp.setStartTime(startTime);
		temp.setEndTime(endTime);
		
		List<ExpFlowTemplate> list = ServiceSupportFactory.getSearchServiceImp().getExpFlow(serverID, temp);
		serverName = UtilTools.serverMap.get(serverID);
		if(list != null){
			pageSize = list.size() < pageSize ? list.size() : pageSize;
			result = SUCCESS;
			expFlowList = list.subList(0, pageSize);
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
		ExpFlowTemplate condition = (ExpFlowTemplate)ActionContext.getContext().getSession().get(ActionConst.REQ_TEMP);
		serverName = UtilTools.serverMap.get(serverID);
		roleID = condition.getOwnerID();
		startTime = condition.getStartTime();
		endTime = condition.getEndTime();
		
		List<ExpFlowTemplate> list = ServiceSupportFactory.getSearchServiceImp().getExpFlowByPage(serverID, condition, minCount, maxCount - minCount);
		if(list  != null){
			result = SUCCESS;
			expFlowList = list;
		}else{
			result = ERROR;
		}
		
		return result;
	}
	
	
	
}
