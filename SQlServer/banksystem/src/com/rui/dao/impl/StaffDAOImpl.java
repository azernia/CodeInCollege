package com.rui.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.rui.dao.infa.StaffDAOInfa;
import com.rui.util.DBUtil;
import com.rui.vo.StaffVO;

public class StaffDAOImpl implements StaffDAOInfa {

	@Override
	public int checkLogin(StaffVO staff) {
		String sql = "select rank from staff where staffid = ? and staffpwd = ?";
		int rank = 0;
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = DBUtil.dbConnection();
			ps = conn.prepareStatement(sql);
			ps.setInt(1, staff.getStaffID());
			ps.setString(2, staff.getStaffPwd());
			rs = ps.executeQuery();
			if(rs.next()) {
				rank = rs.getInt("rank");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rank;
	}

}
