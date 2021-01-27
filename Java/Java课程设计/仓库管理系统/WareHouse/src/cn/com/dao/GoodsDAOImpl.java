package cn.com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import cn.com.VO.GoodsVO;
import cn.com.util.DBUtil;

public class GoodsDAOImpl implements GoodsInfa {
	public boolean goodsInsert(GoodsVO gvo) {
		boolean b = false;
		String sql1 = "insert into goods values(?,?,?,?,to_date(?,'yyyy-mm-dd hh:mi:ss'),to_date(?,'yyyy-mm-dd hh:mi:ss'),?,?)";
		String sql2 = "insert into goodsClass values(?)";
		Connection conn = null;
		PreparedStatement ps = null;
		PreparedStatement ps2 = null;
		try {
			conn = DBUtil.dbConnection();
			ps2 = conn.prepareStatement(sql2);
			ps2.setString(1, gvo.getClassName());
			ps2.executeUpdate();
			ps = conn.prepareStatement(sql1);
			ps.setString(1, gvo.getGoodsID());
			ps.setString(2, gvo.getGoodsName());
			ps.setString(3, gvo.getClassName());
			ps.setFloat(4, gvo.getStockPrice());
			ps.setString(5, gvo.getStockTime());
			ps.setString(6, "1-1-1 01:01:01");
			ps.setString(7, gvo.getAreaID());
			ps.setInt(8, gvo.getAmount());
			int i = ps.executeUpdate();
			if(i!=0) {
				b = true;
				return b;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBUtil.dbClose(conn, ps, null);
			if(ps2!=null) {
				try {
					ps2.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return b;
	}

	@Override
	public boolean updateShipment(String date,GoodsVO gvo) {
		// TODO Auto-generated method stub
		boolean b = false;
		String sql = "update goods set shipmentTime=to_date(?,'yyyy-mm-dd hh:mi:ss'),amount=? where goodsID=? or goodsName=?";
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = DBUtil.dbConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, date);
			ps.setInt(2, gvo.getAmount());
			ps.setString(3, gvo.getGoodsID());
			ps.setString(4, gvo.getGoodsName());
			int flag = ps.executeUpdate();
			if(flag > 0) {
				b = true;
				return b;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return b;
	}


	@Override
	public List<GoodsVO> selectShipmentByGoodsName(GoodsVO gvo) {
		List<GoodsVO> list = null;
		String sql = "select goodsID,amount from goods where goodsName=?";
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			list = new ArrayList<GoodsVO>();
			conn = DBUtil.dbConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, gvo.getGoodsName());
			rs = ps.executeQuery();
			if(rs.next()) {
				gvo.setGoodsID(rs.getString("goodsID"));
				gvo.setAmount(rs.getInt("amount"));
				list.add(gvo);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBUtil.dbClose(conn, ps, rs);
		}
		return list;
	}

	@Override
	public List<GoodsVO> selectShipmentByGoodsID(GoodsVO gvo) {
		List<GoodsVO> list = null;
		String sql = "select goodsName,amount from goods where goodsID=?";
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			list = new ArrayList<GoodsVO>();
			conn = DBUtil.dbConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, gvo.getGoodsID());
			rs = ps.executeQuery();
			if(rs.next()) {
				gvo.setGoodsName(rs.getString("goodsName"));
				gvo.setAmount(rs.getInt("amount"));
				list.add(gvo);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBUtil.dbClose(conn, ps, rs);
		}
		return list;
	}

	@Override
	public List<GoodsVO> selectAll() {
		List<GoodsVO> list = new ArrayList<GoodsVO>();
		String sql = "select * from goods";
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
			conn = DBUtil.dbConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			while(rs.next()) {
				GoodsVO gvo = new GoodsVO();
				gvo.setGoodsID(rs.getString("goodsID"));
				gvo.setGoodsName(rs.getString("goodsName"));
				gvo.setClassName(rs.getString("className"));
				gvo.setStockPrice(rs.getFloat("stockPrice"));
				gvo.setStockTime(rs.getString("stockTime"));
				gvo.setShipmentTime(rs.getString("shipmentTime"));
				gvo.setAreaID(rs.getString("areaID"));
				gvo.setAmount(rs.getInt("amount"));
				list.add(gvo);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBUtil.dbClose(conn, stmt, rs);
		}
		return list;
	}

	@Override
	public List<GoodsVO> selectByGoodsID(String goodsID) {
		List<GoodsVO> list = new ArrayList<GoodsVO>();
		String sql = "select * from goods where goodsID=?";
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		GoodsVO gvo = new GoodsVO();
		try {
			conn = DBUtil.dbConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, goodsID);
			rs = ps.executeQuery();
			while(rs.next()) {
				gvo.setGoodsID(rs.getString("goodsID"));
				gvo.setGoodsName(rs.getString("goodsName"));
				gvo.setClassName(rs.getString("className"));
				gvo.setStockPrice(rs.getFloat("stockPrice"));
				gvo.setStockTime(rs.getString("stockTime"));
				gvo.setShipmentTime(rs.getString("shipmentTime"));
				gvo.setAreaID(rs.getString("areaID"));
				gvo.setAmount(rs.getInt("amount"));
				list.add(gvo);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBUtil.dbClose(conn, ps, rs);
		}
		return list;
	}

	@Override
	public List<GoodsVO> selectByGoodsName(String goodsName) {
		List<GoodsVO> list = new ArrayList<GoodsVO>();
		String sql = "select * from goods where goodsName like ?";
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		GoodsVO gvo = new GoodsVO();
		try {
			conn = DBUtil.dbConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, "%" +goodsName+ "%");
			rs = ps.executeQuery();
			while(rs.next()) {
				gvo.setGoodsID(rs.getString("goodsID"));
				gvo.setGoodsName(rs.getString("goodsName"));
				gvo.setClassName(rs.getString("className"));
				gvo.setStockPrice(rs.getFloat("stockPrice"));
				gvo.setStockTime(rs.getString("stockTime"));
				gvo.setShipmentTime(rs.getString("shipmentTime"));
				gvo.setAreaID(rs.getString("areaID"));
				gvo.setAmount(rs.getInt("amount"));
				list.add(gvo);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBUtil.dbClose(conn, ps, rs);
		}
		return list;
	}

	@Override
	public List<GoodsVO> selectByGoodsClass(String goodsClass) {
		List<GoodsVO> list = new ArrayList<GoodsVO>();
		String sql = "select * from goods where goodsClass=?";
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		GoodsVO gvo = new GoodsVO();
		try {
			conn = DBUtil.dbConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, goodsClass);
			rs = ps.executeQuery();
			while(rs.next()) {
				gvo.setGoodsID(rs.getString("goodsID"));
				gvo.setGoodsName(rs.getString("goodsName"));
				gvo.setClassName(rs.getString("className"));
				gvo.setStockPrice(rs.getFloat("stockPrice"));
				gvo.setStockTime(rs.getString("stockTime"));
				gvo.setShipmentTime(rs.getString("shipmentTime"));
				gvo.setAreaID(rs.getString("areaID"));
				gvo.setAmount(rs.getInt("amount"));
				list.add(gvo);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBUtil.dbClose(conn, ps, rs);
		}
		return list;
	}

	@Override
	public List<GoodsVO> selectByAmount(int amount) {
		List<GoodsVO> list = new ArrayList<GoodsVO>();
		String sql = "select * from goods where amount=?";
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		GoodsVO gvo = new GoodsVO();
		try {
			conn = DBUtil.dbConnection();
			ps = conn.prepareStatement(sql);
			ps.setInt(1, amount);
			rs = ps.executeQuery();
			while(rs.next()) {
				gvo.setGoodsID(rs.getString("goodsID"));
				gvo.setGoodsName(rs.getString("goodsName"));
				gvo.setClassName(rs.getString("className"));
				gvo.setStockPrice(rs.getFloat("stockPrice"));
				gvo.setStockTime(rs.getString("stockTime"));
				gvo.setShipmentTime(rs.getString("shipmentTime"));
				gvo.setAreaID(rs.getString("areaID"));
				gvo.setAmount(rs.getInt("amount"));
				list.add(gvo);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBUtil.dbClose(conn, ps, rs);
		}
		return list;
	}

	@Override
	public List<GoodsVO> selectByAreaID(String areaID) {
		List<GoodsVO> list = new ArrayList<GoodsVO>();
		String sql = "select * from goods where areaID=?";
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		GoodsVO gvo = new GoodsVO();
		try {
			conn = DBUtil.dbConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, areaID);
			rs = ps.executeQuery();
			while(rs.next()) {
				gvo.setGoodsID(rs.getString("goodsID"));
				gvo.setGoodsName(rs.getString("goodsName"));
				gvo.setClassName(rs.getString("className"));
				gvo.setStockPrice(rs.getFloat("stockPrice"));
				gvo.setStockTime(rs.getString("stockTime"));
				gvo.setShipmentTime(rs.getString("shipmentTime"));
				gvo.setAreaID(rs.getString("areaID"));
				gvo.setAmount(rs.getInt("amount"));
				list.add(gvo);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBUtil.dbClose(conn, ps, rs);
		}
		return list;
	}

	@Override
	public List<GoodsVO> selectByStockPrice(float stockPrice) {
		List<GoodsVO> list = new ArrayList<GoodsVO>();
		String sql = "select * from goods where stockPrice=?";
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		GoodsVO gvo = new GoodsVO();
		try {
			conn = DBUtil.dbConnection();
			ps = conn.prepareStatement(sql);
			ps.setFloat(1, stockPrice);
			rs = ps.executeQuery();
			while(rs.next()) {
				gvo.setGoodsID(rs.getString("goodsID"));
				gvo.setGoodsName(rs.getString("goodsName"));
				gvo.setClassName(rs.getString("className"));
				gvo.setStockPrice(rs.getFloat("stockPrice"));
				gvo.setStockTime(rs.getString("stockTime"));
				gvo.setShipmentTime(rs.getString("shipmentTime"));
				gvo.setAreaID(rs.getString("areaID"));
				gvo.setAmount(rs.getInt("amount"));
				list.add(gvo);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBUtil.dbClose(conn, ps, rs);
		}
		return list;
	}

	@Override
	public List<GoodsVO> selectByStockTime(String stockTime) {
		List<GoodsVO> list = new ArrayList<GoodsVO>();
		String sql = "select * from goods where stockTime like ?";
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		GoodsVO gvo = new GoodsVO();
		try {
			conn = DBUtil.dbConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, "%"+stockTime+"%");
			rs = ps.executeQuery();
			while(rs.next()) {
				gvo.setGoodsID(rs.getString("goodsID"));
				gvo.setGoodsName(rs.getString("goodsName"));
				gvo.setClassName(rs.getString("className"));
				gvo.setStockPrice(rs.getFloat("stockPrice"));
				gvo.setStockTime(rs.getString("stockTime"));
				gvo.setShipmentTime(rs.getString("shipmentTime"));
				gvo.setAreaID(rs.getString("areaID"));
				gvo.setAmount(rs.getInt("amount"));
				list.add(gvo);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBUtil.dbClose(conn, ps, rs);
		}
		return list;
	}

	@Override
	public List<GoodsVO> selectByShipmentTime(String shipmentTime) {
		List<GoodsVO> list = new ArrayList<GoodsVO>();
		String sql = "select * from goods where shipmentTime like ?";
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		GoodsVO gvo = new GoodsVO();
		try {
			conn = DBUtil.dbConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, "%"+shipmentTime+"%");
			rs = ps.executeQuery();
			while(rs.next()) {
				gvo.setGoodsID(rs.getString("goodsID"));
				gvo.setGoodsName(rs.getString("goodsName"));
				gvo.setClassName(rs.getString("className"));
				gvo.setStockPrice(rs.getFloat("stockPrice"));
				gvo.setStockTime(rs.getString("stockTime"));
				gvo.setShipmentTime(rs.getString("shipmentTime"));
				gvo.setAreaID(rs.getString("areaID"));
				gvo.setAmount(rs.getInt("amount"));
				list.add(gvo);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBUtil.dbClose(conn, ps, rs);
		}
		return list;
	}
}
