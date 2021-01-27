package cn.com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import cn.com.VO.AreaVO;
import cn.com.util.DBUtil;

public class AreaDAOImpl implements AreaDAOInfa {
	public boolean inserArea(AreaVO avo) {
		boolean b = false;
		String sql = "inser into area values(?,?)";
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = DBUtil.dbConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, avo.getAreaID());
			ps.setString(2, avo.getAreaMsg());
			int i = ps.executeUpdate();
			if(i != 0) {
				b = true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBUtil.dbClose(conn, ps, null);
		}
		return b;
	}
}
