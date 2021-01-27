package cn.com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import cn.com.VO.ManagerVO;
import cn.com.util.DBUtil;

public class ManagerDAOImpl implements ManagerInfa {
	public boolean checkLogin(ManagerVO mvo) {
		boolean b = false;
		String sql = "select *from manager where managerName=? and mpassword=?";
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = DBUtil.dbConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, mvo.getManagerName());
			ps.setString(2, mvo.getManagerPassWord());
			rs = ps.executeQuery();
			b = rs.next();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBUtil.dbClose(conn, ps, null);
		}
		return b;
	}

	@Override
	public void updateLoginDate(String date,String userName) {
		// TODO Auto-generated method stub
		String sql = "update manager set loginTime=to_date(?,'yyyy-mm-dd hh:mi:ss') where managerName=?";
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = DBUtil.dbConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, date);
			ps.setString(2, userName);
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
