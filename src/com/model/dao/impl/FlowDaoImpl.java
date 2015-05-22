package com.model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.model.dao.BaseDao;
import com.model.dao.FlowDaoInterface;
import com.model.template.CurrencyFlowTemplate;
import com.model.template.ExpFlowTemplate;
import com.model.template.OtherFlowTemplate;
import com.model.template.TreasureFlowTemplate;
import com.model.template.TreasureModifyTemplate;

public class FlowDaoImpl implements FlowDaoInterface{

	@Override
	public List<CurrencyFlowTemplate> getCurrencyFlowByTemplate(int serverID, CurrencyFlowTemplate temp) {

		if(temp == null){
			return null;
		}
		
		String sql = "select role_id, currency_type, currency_count, flow_type, record_time, remark from currency_record_1_" + serverID + " where 1=1";
		
		if(temp.getOwnerID() > 0){
			sql += " and role_id=" + temp.getOwnerID();
		}
		if(temp.getCurrencyType() > 0){
			sql += " and currency_type=" + temp.getCurrencyType();
		}
		if(temp.getIsAdd() >= 0){
			sql += " and flow_type=" +temp.getIsAdd();
		}
		if(temp.getStartTime()!= null && !temp.getEndTime().equals("")){
			sql += " and record_time between' "+ temp.getStartTime()+" 00:00:00' and '" +temp.getEndTime()+" 23:59:59'";
		}
		//增加按时间排序
		sql += " order by record_time";

		List<CurrencyFlowTemplate> recordList = null;
		CurrencyFlowTemplate record = null;
		BaseDao dao = new BaseDao();
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			
			conn = dao.getConnection();
			ps = conn.prepareStatement(sql);
			try {
				ResultSet rs = ps.executeQuery();
				while(rs.next()){
					record = new CurrencyFlowTemplate();
					record.setOwnerID(rs.getLong("role_id"));
					record.setCurrencyType(rs.getInt("currency_type"));
					record.setValue(rs.getLong("currency_count"));
					record.setIsAdd(rs.getInt("flow_type"));
					record.setRecordTime(rs.getString("record_time"));
					record.setTips(rs.getString("remark"));
					if(recordList == null){
						recordList = new ArrayList<CurrencyFlowTemplate>();
					}
					recordList.add(record);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}finally{
				ps.clearParameters();
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			dao.closedConnetion(conn, ps);
		}
		return recordList;
	}

	@Override
	public List<CurrencyFlowTemplate> getCurrencyFlowByPage(int serverID, CurrencyFlowTemplate temp, int minCount, int maxCount) {
		
		if(temp == null){
			return null;
		}
		
		String sql = "select role_id, currency_type, currency_count, flow_type, record_time, remark from currency_record_1_" + serverID + " where 1=1";
		
		if(temp.getOwnerID() > 0){
			sql += " and role_id=" + temp.getOwnerID();
		}
		if(temp.getCurrencyType() > 0){
			sql += " and currency_type=" + temp.getCurrencyType();
		}
		if(temp.getIsAdd() >= 0){
			sql += " and flow_type=" +temp.getIsAdd();
		}
		if(temp.getStartTime() != null && !temp.getStartTime().equals("")){
			sql += " and record_time between' "+ temp.getStartTime()+" 00:00:00' and '" +temp.getEndTime()+" 23:59:59'";
		}
		
		//增加按时间排序
		sql += " order by record_time limit "+minCount+","+maxCount;
		
		List<CurrencyFlowTemplate> recordList = null;
		CurrencyFlowTemplate record = null;
		BaseDao dao = new BaseDao();
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			
			conn = dao.getConnection();
			ps = conn.prepareStatement(sql);
			try {
				ResultSet rs = ps.executeQuery();
				while(rs.next()){
					record = new CurrencyFlowTemplate();
					record.setOwnerID(rs.getLong("role_id"));
					record.setCurrencyType(rs.getInt("currency_type"));
					record.setValue(rs.getLong("currency_count"));
					record.setIsAdd(rs.getInt("flow_type"));
					record.setRecordTime(rs.getString("record_time"));
					record.setTips(rs.getString("remark"));
					if(recordList == null){
						recordList = new ArrayList<CurrencyFlowTemplate>();
					}
					recordList.add(record);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}finally{
				ps.clearParameters();
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			dao.closedConnetion(conn, ps);
		}
		return recordList;
	}

	@Override
	public int getFlowCount(String tableName) {

		int count = 0;
		
		String sql = "SELECT COUNT(id) from "+ tableName;
		
		BaseDao dao = new BaseDao();
		Connection conn = null;
		PreparedStatement ps = null;
		
		try {
			conn = dao.getConnection();
			ps = conn.prepareStatement(sql);
			
			try {
				ResultSet rs = ps.executeQuery();
				if(rs.next()){
					count = rs.getInt("COUNT(id)");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}finally{
				ps.clearParameters();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			dao.closedConnetion(conn, ps);
		}
		return count;
	}

	@Override
	public List<ExpFlowTemplate> getExpFlowByTemplate(int serverID, ExpFlowTemplate eft) {

		if(eft == null){
			return null;
		}
		
		String sql = "select * from experience_record_1_" + serverID+" where 1=1 ";
		if(eft.getOwnerID() > 0){
			sql += "and role_id = "+eft.getOwnerID();
		}
		if(eft.getStartTime() != null && !eft.getStartTime().equals("")){
			sql += " and record_time between' "+ eft.getStartTime()+" 00:00:00' and '" +eft.getEndTime()+" 23:59:59'";
		}
		
		//增加按时间排序
		sql += " order by record_time";
		
		BaseDao dao = new BaseDao();
		Connection conn = null;
		PreparedStatement ps = null;
		List<ExpFlowTemplate> recordList = null;
		ExpFlowTemplate record;
		try {
			conn = dao.getConnection();
			ps = conn.prepareStatement(sql);
			try {
				ResultSet rs = ps.executeQuery();
				while(rs.next()){
					record = new ExpFlowTemplate();
					record.setOwnerID(rs.getLong("role_id"));
					record.setRecordTime(rs.getString("record_time"));
					record.setTips(rs.getString("remark"));
					record.setValue(rs.getInt("exp_count"));
					if(recordList == null){
						recordList = new ArrayList<ExpFlowTemplate>();
					}
					recordList.add(record);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}finally{
				ps.clearParameters();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			dao.closedConnetion(conn, ps);
		}
		
		return recordList;
	}

	@Override
	public List<OtherFlowTemplate> getOtherFlowByTemplate(int serverID, OtherFlowTemplate oft) {

		if(oft == null){
			return null;
		}
		
		String sql = "select * from other_log_record_1_" +serverID+ " where 1=1";
		if(oft.getOwnerID() > 0){
			sql += " and role_id=" + oft.getOwnerID();
		}
		if(oft.getFlowType() > 0){
			sql+= " and flow_type="+ oft.getFlowType();
		}
		if(oft.getStartTime() != null && !oft.getStartTime().equals("")){
			sql += " and record_time between' "+ oft.getStartTime()+" 00:00:00' and '" +oft.getEndTime()+" 23:59:59'";
		}
		
		//增加按时间排序
		sql += " order by record_time";
		
		BaseDao dao = new BaseDao();
		Connection conn = null;
		PreparedStatement ps = null;
		List<OtherFlowTemplate> recordList = null;
		OtherFlowTemplate record;
		try {
			conn = dao.getConnection();
			ps = conn.prepareStatement(sql);
			try {
				ResultSet rs = ps.executeQuery();
				while(rs.next()){
					record = new OtherFlowTemplate();
					record.setOwnerID(rs.getLong("role_id"));
					record.setFlowType(rs.getInt("flow_type"));
					record.setRecordTime(rs.getString("record_time"));
					record.setTips(rs.getString("remark"));
					if(recordList == null){
						recordList = new ArrayList<OtherFlowTemplate>();
					}
					recordList.add(record);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}finally{
				ps.clearParameters();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			dao.closedConnetion(conn, ps);
		}
		
		return recordList;
	}

	@Override
	public List<TreasureFlowTemplate> getTreasureFlowByTemplate(int serverID,	TreasureFlowTemplate tft) {
		if(tft == null){
			return null;
		}
		String sql = "select * from property_in_out_record_1_" + serverID + " where 1=1";
		if(tft.getOwnerID() > 0){
			sql += " and role_id=" + tft.getOwnerID();
		}
		if(tft.getIsAdd() >= 0){
			sql += " and flow_type=" + tft.getIsAdd();
		}
		if(tft.getStartTime()!= null && !tft.getStartTime().equals("")){
			sql += " and record_time between' "+ tft.getStartTime()+" 00:00:00' and '" +tft.getEndTime()+" 23:59:59'";
		}
		if(tft.getTemplateID() != null && !tft.getTemplateID().equals("")){
			sql += " and property_template_id='"+tft.getTemplateID()+"'";
		}
		
		
		//增加按时间排序
		sql += " order by record_time";
		
		BaseDao dao = new BaseDao();
		Connection conn = null;
		PreparedStatement ps = null;
		List<TreasureFlowTemplate> recordList = null;
		TreasureFlowTemplate record;
		try {
			conn = dao.getConnection();
			ps = conn.prepareStatement(sql);
			try {
				ResultSet rs = ps.executeQuery();
				while(rs.next()){
					record = new TreasureFlowTemplate();
					record.setOwnerID(rs.getLong("role_id"));
					record.setUUID(rs.getString("UUID"));
					record.setPropertyType(rs.getInt("property_type"));
					record.setTemplateID(rs.getString("property_template_id"));
					record.setIsAdd(rs.getInt("flow_type"));
					record.setRecordTime(rs.getString("record_time"));
					record.setTips(rs.getString("remark"));
					if(recordList == null){
						recordList = new ArrayList<TreasureFlowTemplate>();
					}
					recordList.add(record);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}finally{
				ps.clearParameters();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			dao.closedConnetion(conn, ps);
		}
		
		return recordList;
	}

	@Override
	public List<TreasureModifyTemplate> getTreasureModifyByTemplate( int serverID, TreasureModifyTemplate tmt) {
		if(tmt == null){
			return null;
		}
		
		String sql = "select * from property_modify_record_1_" +serverID+ " where 1=1";
		if(tmt.getStartTime() != null && !tmt.getStartTime().equals("")){
			sql += " and record_time between' "+ tmt.getStartTime()+" 00:00:00' and '" +tmt.getEndTime()+" 23:59:59'";
		}
		if(tmt.getUUID() != null  && !tmt.getUUID().equals("")){
			sql += " and UUID='" +tmt.getUUID()+"'";
		}
		if(tmt.getRoleID() > 0){
			sql += " and role_id='"+tmt.getRoleID()+"'";
		}
		
		//增加按时间排序
		sql += " order by record_time";
		
		BaseDao dao = new BaseDao();
		Connection conn = null;
		PreparedStatement ps = null;
		List<TreasureModifyTemplate> recordList = null;
		TreasureModifyTemplate record;
		try {
			conn = dao.getConnection();
			ps = conn.prepareStatement(sql);
			try {
				ResultSet rs = ps.executeQuery();
				while(rs.next()){
					record = new TreasureModifyTemplate();
					record.setRoleID(rs.getLong("role_id"));
					record.setUUID(rs.getString("UUID"));
					record.setRecordTime(rs.getString("record_time"));
					record.setTips(rs.getString("remark"));
					if(recordList == null){
						recordList = new ArrayList<TreasureModifyTemplate>();
					}
					recordList.add(record);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}finally{
				ps.clearParameters();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			dao.closedConnetion(conn, ps);
		}
		
		return recordList;
		
	}

	@Override
	public List<TreasureFlowTemplate> getTreasureFlowByPage(int serverID, TreasureFlowTemplate condition, int minCount, int pageSize) {
		if(condition == null){
			return null;
		}
		String sql = "select * from property_in_out_record_1_" + serverID + " where 1=1";
		if(condition.getOwnerID() > 0){
			sql += " and role_id=" + condition.getOwnerID();
		}
		if(condition.getIsAdd() >= 0){
			sql += " and flow_type=" + condition.getIsAdd();
		}
		if(condition.getStartTime()!= null && !condition.getStartTime().equals("")){
			sql += " and record_time between' "+ condition.getStartTime()+" 00:00:00' and '" +condition.getEndTime()+" 23:59:59'";
		}
		if(condition.getTemplateID() != null && !condition.getTemplateID().equals("")){
			sql += " and property_template_id='"+condition.getTemplateID()+"'";
		}
		
		
		//增加按时间排序
		sql += " order by record_time limit " + minCount + "," + pageSize;
		
		BaseDao dao = new BaseDao();
		Connection conn = null;
		PreparedStatement ps = null;
		List<TreasureFlowTemplate> recordList = null;
		TreasureFlowTemplate record;
		try {
			conn = dao.getConnection();
			ps = conn.prepareStatement(sql);
			try {
				ResultSet rs = ps.executeQuery();
				while(rs.next()){
					record = new TreasureFlowTemplate();
					record.setOwnerID(rs.getLong("role_id"));
					record.setUUID(rs.getString("UUID"));
					record.setPropertyType(rs.getInt("property_type"));
					record.setTemplateID(rs.getString("property_template_id"));
					record.setIsAdd(rs.getInt("flow_type"));
					record.setRecordTime(rs.getString("record_time"));
					record.setTips(rs.getString("remark"));
					if(recordList == null){
						recordList = new ArrayList<TreasureFlowTemplate>();
					}
					recordList.add(record);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}finally{
				ps.clearParameters();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			dao.closedConnetion(conn, ps);
		}
		
		return recordList;
	}

	@Override
	public List<TreasureModifyTemplate> getTreasureModifyByPage(int serverID, TreasureModifyTemplate tmt, int minCount, int pageSize) {

		
		if(tmt == null){
			return null;
		}
		
		String sql = "select * from property_modify_record_1_" +serverID+ " where 1=1";
		if(tmt.getStartTime() != null && !tmt.getStartTime().equals("")){
			sql += " and record_time between' "+ tmt.getStartTime()+" 00:00:00' and '" +tmt.getEndTime()+" 23:59:59'";
		}
		if(tmt.getUUID() != null  && !tmt.getUUID().equals("")){
			sql += " and UUID='" +tmt.getUUID()+"'";
		}
		if(tmt.getRoleID() > 0){
			sql += " and role_id='"+tmt.getRoleID()+"'";
		}
		
		//增加按时间排序
		sql += " order by record_time limit " + minCount + "," + pageSize;
		
		BaseDao dao = new BaseDao();
		Connection conn = null;
		PreparedStatement ps = null;
		List<TreasureModifyTemplate> recordList = null;
		TreasureModifyTemplate record;
		try {
			conn = dao.getConnection();
			ps = conn.prepareStatement(sql);
			try {
				ResultSet rs = ps.executeQuery();
				while(rs.next()){
					record = new TreasureModifyTemplate();
					record.setRoleID(rs.getLong("role_id"));
					record.setUUID(rs.getString("UUID"));
					record.setRecordTime(rs.getString("record_time"));
					record.setTips(rs.getString("remark"));
					if(recordList == null){
						recordList = new ArrayList<TreasureModifyTemplate>();
					}
					recordList.add(record);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}finally{
				ps.clearParameters();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			dao.closedConnetion(conn, ps);
		}
		
		return recordList;
	}

	@Override
	public List<OtherFlowTemplate> getOtherFlowByPage(int serverID, OtherFlowTemplate oft, int minCount, int pageSize) {

		if(oft == null){
			return null;
		}
		
		String sql = "select * from other_log_record_1_" +serverID+ " where 1=1";
		if(oft.getOwnerID() > 0){
			sql += " and role_id=" + oft.getOwnerID();
		}
		if(oft.getFlowType() > 0){
			sql+= " and flow_type="+ oft.getFlowType();
		}
		if(oft.getStartTime() != null && !oft.getStartTime().equals("")){
			sql += " and record_time between' "+ oft.getStartTime()+" 00:00:00' and '" +oft.getEndTime()+" 23:59:59'";
		}
		
		//增加按时间排序
		sql += " order by record_time limit " + minCount + "," + pageSize;
		
		BaseDao dao = new BaseDao();
		Connection conn = null;
		PreparedStatement ps = null;
		List<OtherFlowTemplate> recordList = null;
		OtherFlowTemplate record;
		try {
			conn = dao.getConnection();
			ps = conn.prepareStatement(sql);
			try {
				ResultSet rs = ps.executeQuery();
				while(rs.next()){
					record = new OtherFlowTemplate();
					record.setOwnerID(rs.getLong("role_id"));
					record.setFlowType(rs.getInt("flow_type"));
					record.setRecordTime(rs.getString("record_time"));
					record.setTips(rs.getString("remark"));
					if(recordList == null){
						recordList = new ArrayList<OtherFlowTemplate>();
					}
					recordList.add(record);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}finally{
				ps.clearParameters();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			dao.closedConnetion(conn, ps);
		}
		
		return recordList;
	}

	@Override
	public List<ExpFlowTemplate> getExpFlowByTemplate(int serverID, ExpFlowTemplate eft, int minCount, int pageSize) {

		if(eft == null){
			return null;
		}
		
		String sql = "select * from experience_record_1_" + serverID+" where 1=1 ";
		if(eft.getOwnerID() > 0){
			sql += "and role_id = "+eft.getOwnerID();
		}
		if(eft.getStartTime() != null && !eft.getStartTime().equals("")){
			sql += " and record_time between' "+ eft.getStartTime()+" 00:00:00' and '" +eft.getEndTime()+" 23:59:59'";
		}
		
		//增加按时间排序
		sql += " order by record_time limit " + minCount + "," + pageSize;
		
		BaseDao dao = new BaseDao();
		Connection conn = null;
		PreparedStatement ps = null;
		List<ExpFlowTemplate> recordList = null;
		ExpFlowTemplate record;
		try {
			conn = dao.getConnection();
			ps = conn.prepareStatement(sql);
			try {
				ResultSet rs = ps.executeQuery();
				while(rs.next()){
					record = new ExpFlowTemplate();
					record.setOwnerID(rs.getLong("role_id"));
					record.setRecordTime(rs.getString("record_time"));
					record.setTips(rs.getString("remark"));
					record.setValue(rs.getInt("exp_count"));
					if(recordList == null){
						recordList = new ArrayList<ExpFlowTemplate>();
					}
					recordList.add(record);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}finally{
				ps.clearParameters();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			dao.closedConnetion(conn, ps);
		}
		
		return recordList;
	}
		

}
