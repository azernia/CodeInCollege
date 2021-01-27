package com.rui.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.rui.dao.infa.UserDAOInfa;
import com.rui.vo.BankCardVO;
import com.rui.vo.CreditCardVO;
import com.rui.util.DBUtil;

public class UserDAOImpl implements UserDAOInfa {

	@Override
	public boolean checkLoginByBankCard(BankCardVO bankCard) {
		boolean b = false;
		String sql = "select buserid from bankcard where bankcardnum = ? and bankcardpwd = ?";
		Connection conn =null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = DBUtil.dbConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, bankCard.getBankCardNum());
			ps.setInt(2, bankCard.getBankCardPwd());
			rs = ps.executeQuery();
			b = rs.next();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil.dbClose(conn, ps, rs);
		}
		return b;
	}

	@Override
	public boolean checkLoginByCreditCard(CreditCardVO creditCard) {
		boolean b = false;
		String sql = "select buserid from creditcard where creditcardnum = ? and creditcardpwd = ?";
		Connection conn =null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = DBUtil.dbConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, creditCard.getCreditCardNum());
			ps.setInt(2, creditCard.getCreditCardPwd());
			rs = ps.executeQuery();
			b = rs.next();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil.dbClose(conn, ps, rs);
		}
		return b;
	}

}
