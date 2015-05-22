package com.model.action;

import java.util.List;
import java.util.Map;

import com.model.service.ServiceSupportFactory;
import com.model.template.CurrencyFlowTemplate;
import com.model.util.UtilTools;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

/**
 * 
 * @author Alex
 *
 */
public class CurrencyFlowSearchAction  extends  ActionSupport{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	private  int serverID;
	
	private String serverName;
	
	private long roleID;
	
	private int currencyType;
	
	private String currencyName;

	private String startTime;
	
	private String endTime;
	
	private int add;
	
	private int status;
	
	private List<CurrencyFlowTemplate>  currencyFlowList ;
	
	
	private int pageNo = 1;
	private int pageSize = 30;
	private int recordCount;
	private String url;
	
	private long totalValue;
	

	public long getTotalValue() {
		return totalValue;
	}



	public void setTotalValue(long totalValue) {
		this.totalValue = totalValue;
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



	public int getPageNo() {
		return pageNo;
	}



	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}



	public String getServerName() {
		return serverName;
	}



	public void setServerName(String serverName) {
		this.serverName = serverName;
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



	public int getCurrencyType() {
		return currencyType;
	}



	public void setCurrencyType(int currencyType) {
		this.currencyType = currencyType;
	}



	public int getAdd() {
		return add;
	}



	public void setAdd(int add) {
		this.add = add;
	}





	public int getStatus() {
		return status;
	}



	public void setStatus(int status) {
		this.status = status;
	}



	public List<CurrencyFlowTemplate> getCurrencyFlowList() {
		return currencyFlowList;
	}



	public void setCurrencyFlowList(List<CurrencyFlowTemplate> currencyFlowList) {
		this.currencyFlowList = currencyFlowList;
	}



	public String getCurrencyName() {
		return currencyName;
	}



	public void setCurrencyName(String currencyName) {
		this.currencyName = currencyName;
	}



	@Override
	public String execute() {
		String result = "";
		CurrencyFlowTemplate temp = new CurrencyFlowTemplate();
		temp.setIsAdd(getAdd());
		temp.setOwnerID(getRoleID());
		temp.setCurrencyType(getCurrencyType());
		temp.setStartTime(startTime);
		temp.setEndTime(endTime);
		
		List<CurrencyFlowTemplate> currencyFlow = ServiceSupportFactory.getSearchServiceImp().getCurrencyFlow(serverID,temp);

		serverName = UtilTools.serverMap.get(serverID);
		currencyName = UtilTools.currencyMap.get(currencyType);
		
		if(currencyFlow != null){
			totalValue = caculateTotalValue(currencyFlow);
			pageSize = currencyFlow.size() < pageSize ? currencyFlow.size() : pageSize;
			result = SUCCESS;
			currencyFlowList = currencyFlow.subList((pageNo - 1) * pageSize, pageSize);
			recordCount = currencyFlow.size();
			ActionContext context = ActionContext.getContext();
			Map<String, Object> session = context.getSession();
			session.put(ActionConst.REQ_TEMP, temp);
			session.put(ActionConst.CUR_TOTAL, totalValue);
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
		Map<String, Object> session = ActionContext.getContext().getSession();
		
		CurrencyFlowTemplate condition = (CurrencyFlowTemplate)session.get(ActionConst.REQ_TEMP);
		
		totalValue = (Long) session.get(ActionConst.CUR_TOTAL);
		
		serverName = UtilTools.serverMap.get(serverID);
		roleID = condition.getOwnerID();
		startTime = condition.getStartTime();
		endTime = condition.getEndTime();
		List<CurrencyFlowTemplate> list = ServiceSupportFactory.getSearchServiceImp().getCurrencyFlowByPage(serverID, condition,minCount, maxCount - minCount);
		if(list  != null){
			currencyName = UtilTools.currencyMap.get(condition.getCurrencyType());
			result = SUCCESS;
			currencyFlowList = list;
		}else{
			result = ERROR;
		}
		
		return result;
	}
	
	private long caculateTotalValue(List<CurrencyFlowTemplate> currencyFlow){
		long total = 0;
		if(currencyFlow == null){
			return total;
		}
		for (CurrencyFlowTemplate tem : currencyFlow) {
			total += tem.getValue();
		}
		return total;
	}
}
