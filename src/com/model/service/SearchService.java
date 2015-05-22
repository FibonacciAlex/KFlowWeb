package com.model.service;

import java.util.List;

import com.model.template.CurrencyFlowTemplate;
import com.model.template.ExpFlowTemplate;
import com.model.template.OtherFlowTemplate;
import com.model.template.TreasureFlowTemplate;
import com.model.template.TreasureModifyTemplate;

public interface SearchService {

	/**
	 * 根据货币流水模板查找流水数据
	 * @param cft 货币流水模板
	 * @return
	 */
	public List<CurrencyFlowTemplate> getCurrencyFlow(int serverId, CurrencyFlowTemplate cft);
	
	
	public List<CurrencyFlowTemplate> getCurrencyFlowByPage(int serverId, CurrencyFlowTemplate cft, int minCount, int maxCount);
	
	
	public int getCurrencyFlowCount(int serverID);
	
	/**
	 * 根据经验流水模板查找流水数据
	 * @param serverID
	 * @param eft 经验流水模板
	 * @return
	 */
	public List<ExpFlowTemplate> getExpFlow(int serverID, ExpFlowTemplate eft);
	
	/**
	 * 根据其他流水模板查找流水数据
	 * @param serverID
	 * @param oft
	 * @return
	 */
	public List<OtherFlowTemplate> getOtherFlow(int serverID, OtherFlowTemplate oft);
	
	/**
	 * 根据资产模板查找流水数据
	 * @param serverID
	 * @param tft
	 * @return
	 */
	public List<TreasureFlowTemplate> getTreasureFlow(int serverID, TreasureFlowTemplate tft);
	
	/**
	 * 根据资产修改模板查找流水数据
	 * @param serverID
	 * @param tmt
	 * @return
	 */
	public List<TreasureModifyTemplate> getTreasureModify(int serverID, TreasureModifyTemplate tmt);


	/**
	 * 分页查找资产流水数据
	 * @param serverID
	 * @param condition
	 * @param minCount
	 * @param pageSize
	 * @return
	 */
	public List<TreasureFlowTemplate> getTreasureFlowByPage(int serverID,TreasureFlowTemplate condition, int minCount, int pageSize);


	/**
	 * 分页查看资产修改数据
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
	 * @param i
	 * @return
	 */
	public List<ExpFlowTemplate> getExpFlowByPage(int serverID, ExpFlowTemplate condition, int minCount, int i);


	
}
