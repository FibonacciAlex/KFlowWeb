package com.model.service.impl;

import java.util.List;

import com.model.dao.DaoSupportFactory;
import com.model.service.SearchService;
import com.model.template.CurrencyFlowTemplate;
import com.model.template.ExpFlowTemplate;
import com.model.template.OtherFlowTemplate;
import com.model.template.TreasureFlowTemplate;
import com.model.template.TreasureModifyTemplate;

public class SearchServiceImpl implements SearchService{

	@Override
	public List<CurrencyFlowTemplate> getCurrencyFlow(int serverID, CurrencyFlowTemplate cft) {
		List<CurrencyFlowTemplate> recordList = DaoSupportFactory.getFlowDaoInterface().getCurrencyFlowByTemplate(serverID, cft);
		
		return recordList;
	}

	@Override
	public List<CurrencyFlowTemplate> getCurrencyFlowByPage(int serverId, CurrencyFlowTemplate cft, int minCount, int maxCount) {
		List<CurrencyFlowTemplate> currencyFlowByPage = DaoSupportFactory.getFlowDaoInterface().getCurrencyFlowByPage(serverId, cft, minCount, maxCount);
		return currencyFlowByPage;
	}

	@Override
	public int getCurrencyFlowCount(int serverID) {
		String tableName = "currency_record_1_" + serverID;
		int flowCount = DaoSupportFactory.getFlowDaoInterface().getFlowCount(tableName);
		return flowCount;
	}

	@Override
	public List<ExpFlowTemplate> getExpFlow(int serverID, ExpFlowTemplate eft) {
		List<ExpFlowTemplate> list = DaoSupportFactory.getFlowDaoInterface().getExpFlowByTemplate(serverID, eft);
		return list;
	}

	@Override
	public List<OtherFlowTemplate> getOtherFlow(int serverID, OtherFlowTemplate oft) {

		List<OtherFlowTemplate> list = DaoSupportFactory.getFlowDaoInterface().getOtherFlowByTemplate(serverID, oft);
		
		return list;
	}

	@Override
	public List<TreasureFlowTemplate> getTreasureFlow(int serverID, TreasureFlowTemplate tft) {
		List<TreasureFlowTemplate> list = DaoSupportFactory.getFlowDaoInterface().getTreasureFlowByTemplate(serverID, tft);
		return list;
	}

	@Override
	public List<TreasureModifyTemplate> getTreasureModify(int serverID, TreasureModifyTemplate tmt) {
		List<TreasureModifyTemplate> list = DaoSupportFactory.getFlowDaoInterface().getTreasureModifyByTemplate(serverID, tmt);
		return list;
	}

	@Override
	public List<TreasureFlowTemplate> getTreasureFlowByPage(int serverID, TreasureFlowTemplate condition, int minCount, int pageSize) {
		List<TreasureFlowTemplate> list = DaoSupportFactory.getFlowDaoInterface().getTreasureFlowByPage(serverID,condition,minCount,pageSize);
		return list;
	}

	@Override
	public List<TreasureModifyTemplate> getTreasureModifyByPage(int serverID, TreasureModifyTemplate condition, int minCount, int pageSize) {
		List<TreasureModifyTemplate> list = DaoSupportFactory.getFlowDaoInterface().getTreasureModifyByPage(serverID, condition, minCount, pageSize);
		return list;
	}

	@Override
	public List<OtherFlowTemplate> getOtherFlowByPage(int serverID, OtherFlowTemplate condition, int minCount, int pageSize) {
		List<OtherFlowTemplate> list = DaoSupportFactory.getFlowDaoInterface().getOtherFlowByPage(serverID, condition,minCount, pageSize);
		return list;
	}

	@Override
	public List<ExpFlowTemplate> getExpFlowByPage(int serverID, ExpFlowTemplate condition, int minCount, int pageSize) {
		List<ExpFlowTemplate>list = DaoSupportFactory.getFlowDaoInterface().getExpFlowByTemplate(serverID, condition, minCount, pageSize);
		return list;
	}

}
