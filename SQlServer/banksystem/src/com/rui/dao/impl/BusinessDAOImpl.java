package com.rui.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.rui.dao.infa.BusinessDAOInfa;
import com.rui.util.DBUtil;
import com.rui.vo.BusinessVO;

public class BusinessDAOImpl implements BusinessDAOInfa {

	@Override
	public List<BusinessVO> searchAllBusiness() {
		String sql = "select * from business";
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<BusinessVO> list = new ArrayList<BusinessVO>();
		try {
			conn = DBUtil.dbConnection();
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()) {
				BusinessVO bvo = new BusinessVO();
				bvo.setBusinessName(rs.getString("businessname"));
				bvo.setDescription(rs.getString("description"));
				list.add(bvo);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil.dbClose(conn, ps, rs);
		}
		return list;
	}

	@Override
	public Map<String, String> searchBusinessByBankCard(String bankCardNum) {
		String sql = "select bc.bankcardnum,bs.businessname,bs.description from bankcard bc,business bs where bc.bankcardnum = ? and bc.businessid = bs.businessid";
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Map<String , String> map = new HashMap<>();
		try {
			conn = DBUtil.dbConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, bankCardNum);
			rs = ps.executeQuery();
			if(rs.next()) {
				map.put("办理账户", rs.getString("bankcardnum"));
				map.put("业务名", rs.getString("businessname"));
				map.put("业务描述", rs.getString("description"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil.dbClose(conn, ps, rs);
		}
		return map;
	}

}
