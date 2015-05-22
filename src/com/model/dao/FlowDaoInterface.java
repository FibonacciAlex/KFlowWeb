package com.model.dao;

import java.util.List;

import com.model.template.CurrencyFlowTemplate;
import com.model.template.ExpFlowTemplate;
import com.model.template.OtherFlowTemplate;
import com.model.template.TreasureFlowTemplate;
import com.model.template.TreasureModifyTemplate;

public interface FlowDaoInterface {
	
	public List<CurrencyFlowTemplate> getCurrencyFlowByTemplate(int serverID, CurrencyFlowTemplate temp);

	
	public List<CurrencyFlowTemplate> getCurrencyFlowByPage(int serverID, CurrencyFlowTemplate temp, int minCount, int maxCount);
	
	/**
	 * 获取流水记录总数
	 * @param tableName 表名
	 * @return
	 */
	public int getFlowCount(String tableName);
	
	/**
	 * 查找经验流水
	 * @param serverID
	 * @param eft
	 * @return
	 */
	public List<ExpFlowTemplate> getExpFlowByTemplate(int serverID,ExpFlowTemplate eft);
	
	/**
	 * 查找其他流水
	 * @param serverID
	 * @param oft
	 * @return
	 */
	public List<OtherFlowTemplate> getOtherFlowByTemplate(int serverID, OtherFlowTemplate oft);
	
	
	/**
	 * 查找资产进出流水
	 * @param serverID
	 * @param tft
	 * @return
	 */
	public List<TreasureFlowTemplate> getTreasureFlowByTemplate(int serverID, TreasureFlowTemplate tft);
	
	/**
	 * 查找资产修改流水
	 * @param serverID
	 * @param tmt
	 * @return
	 */
	public List<TreasureModifyTemplate> getTreasureModifyByTemplate(int serverID, TreasureModifyTemplate tmt);


	/**
	 * 分页查找资产流水数据
	 * @param serverID
	 * @param condition
	 * @param minCount
	 * @param pageSize
	 * @return
	 */
	public List<TreasureFlowTemplate> getTreasureFlowByPage(int serverID, TreasureFlowTemplate condition, int minCount, int pageSize);


	/**
	 * 分页查找资产修改数据
	 * @param serverID
	 * @param condition
	 * @param minCount
	 * @param pageSize
	 * @return
	 */
	public List<TreasureModifyTemplate> getTreasureModifyByPage(int serverID, TreasureModifyTemplate condition, int minCount, int pageSize);


	/**
	 * 分页查看其他流水数据
	 * @param serverID
	 * @param condition
	 * @param minCount
	 * @param pageSize
	 * @return
	 */
	public List<OtherFlowTemplate> getOtherFlowByPage(int serverID, OtherFlowTemplate condition, int minCount, int pageSize);


	/**
	 * 分页查看经验流水数据
	 * @param serverID
	 * @param condition
	 * @param minCount
	 * @param pageSize
	 * @return
	 */
	public List<ExpFlowTemplate> getExpFlowByTemplate(int serverID, ExpFlowTemplate condition, int minCount, int pageSize);
}
