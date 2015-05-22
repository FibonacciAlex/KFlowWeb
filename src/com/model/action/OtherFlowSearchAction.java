package com.model.action;

import java.util.List;

import javax.servlet.ServletResponse;

import sun.print.resources.serviceui;

import com.model.service.ServiceSupportFactory;
import com.model.template.ExpFlowTemplate;
import com.model.template.OtherFlowTemplate;
import com.model.util.UtilTools;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class OtherFlowSearchAction extends ActionSupport{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	private  int serverID;
	private int pageNo = 1;
	private int pageSize = 30;
	private int recordCount;
	private String url;
	private int status;
	
	private long roleID;
	
	private int flowType;
	private String serverName;
	private String flowName;
	private String startTime;
	private String endTime;
	
	private long totalCount;
	
	private List<OtherFlowTemplate> otherFlowList;

	
	public long getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(long totalCount) {
		this.totalCount = totalCount;
	}

	public String getServerName() {
		return serverName;
	}

	public void setServerName(String serverName) {
		this.serverName = serverName;
	}

	public String getFlowName() {
		return flowName;
	}

	public void setFlowName(String flowName) {
		this.flowName = flowName;
	}

	public List<OtherFlowTemplate> getOtherFlowList() {
		return otherFlowList;
	}

	public void setOtherFlowList(List<OtherFlowTemplate> otherFlowList) {
		this.otherFlowList = otherFlowList;
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


	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
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

	public long getRoleID() {
		return roleID;
	}

	public void setRoleID(long roleID) {
		this.roleID = roleID;
	}

	public int getFlowType() {
		return flowType;
	}

	public void setFlowType(int flowType) {
		this.flowType = flowType;
	}


	@Override
	public String execute() throws Exception {
		
		String result = "";
		
		OtherFlowTemplate temp = new OtherFlowTemplate();
		temp.setFlowType(getFlowType());
		temp.setOwnerID(getRoleID());
		temp.setStartTime(startTime);
		temp.setEndTime(endTime);
		
		List<OtherFlowTemplate> list = ServiceSupportFactory.getSearchServiceImp().getOtherFlow(serverID, temp);
		serverName = UtilTools.serverMap.get(serverID);
		flowName = UtilTools.flowMap.get(flowType);
		temp.setFlowName(flowName);
		if(list != null){
			pageSize = list.size() < pageSize ? list.size() : pageSize;
			
			//统计一下充值总数
			if(flowType == 7){
				totalCount = caculateValue(list);
				ActionContext.getContext().getSession().put(ActionConst.VIP_TOTAL_CHARGE, totalCount);
			}
			
			result = SUCCESS;
			otherFlowList = list.subList(0, pageSize);
			recordCount = list.size();
			ActionContext.getContext().getSession().put(ActionConst.REQ_TEMP, temp);
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
		OtherFlowTemplate condition = (OtherFlowTemplate)ActionContext.getContext().getSession().get(ActionConst.REQ_TEMP);
		if(condition == null){
			result = ERROR;
			setStatus(2);
			return result;
		}
		serverName = UtilTools.serverMap.get(serverID);
		roleID = condition.getOwnerID();
		startTime = condition.getStartTime();
		endTime = condition.getEndTime();
		List<OtherFlowTemplate> list = ServiceSupportFactory.getSearchServiceImp().getOtherFlowByPage(serverID, condition,minCount, maxCount - minCount);
		if(list  != null){
			flowName = condition.getFlowName();
			result = SUCCESS;
			otherFlowList = list;
			Object charge = ActionContext.getContext().getSession().get(ActionConst.VIP_TOTAL_CHARGE);
			if(charge != null){
				totalCount = (Long) charge;
			}
			
		}else{
			result = ERROR;
			setStatus(3);
		}
		
		return result;
	}	

	private long caculateValue(List<OtherFlowTemplate> list){
		long value = 0;
		
		for (OtherFlowTemplate template : list) {
			String tips = template.getTips();
			if(!tips.contains("充值:")){
				continue;
			}
			String[] split = tips.split(";");
			for (String subStr : split) {
				if(!subStr.contains("充值")){
					continue;
				}
				String[] split2 = subStr.split(":");
				long target = Long.parseLong(split2[1]);
				value += target;
			}
		}
		
		return value;
	}

}
