package com.rui.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.rui.dao.infa.BankCardDAOInfa;
import com.rui.util.DBUtil;
import com.rui.vo.BankCardTransactionDetailsVO;
import com.rui.vo.BankCardVO;

public class BankCardDAOImpl implements BankCardDAOInfa {

	@Override
	public double searchAssets(BankCardVO bankCard) {
		String sql = "select asset from bankcard where bankcardnum = ?";
		Connection conn = null;
		double asset = Double.MIN_VALUE;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = DBUtil.dbConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, bankCard.getBankCardNum());
			rs = ps.executeQuery();
			if(rs.next()) {
				asset = rs.getDouble("asset");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil.dbClose(conn, ps, rs);
		}
		return asset;
	}

	@Override
	public List<BankCardTransactionDetailsVO> searchTransactionDetails(BankCardVO bankCard) {
		String sql = "select * from bankcardtransactiondetails bct,btrelationship btr where btr.bankcardtransactionserialnum = bct.bankcardtransactionserialnum and btr.bankcardnum = ?";
		//String sql = "select * from bankcardtransactiondetails bct,btrelationship btr where btr.bankcardtransactionserialnum = bct.bankcardtransactionserialnum and btr.bankcardnum = '436742920141062657'";
		List<BankCardTransactionDetailsVO> list = new ArrayList<>();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = DBUtil.dbConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, bankCard.getBankCardNum());
			rs = ps.executeQuery();
			while(rs.next()) {
				BankCardTransactionDetailsVO bct = new BankCardTransactionDetailsVO();
				bct.setBankCardTransactionSerialNum(rs.getLong("bankCardTransactionSerialNum"));
				bct.setCounterpartyAccount(rs.getString("counterpartyAccount"));
				bct.setTransactionType(rs.getString("transactionType"));
				bct.setTradingChannel(rs.getString("tradingChannel"));
				bct.setTransactionAmount(rs.getDouble("transactionAmount"));
				bct.setTransactionAccount(rs.getString("transactionAccount"));
				bct.setTransactionDate(rs.getString("transactionDate"));
				list.add(bct);
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
	public boolean updateAssetsBySaving(BankCardVO bankCard) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateAssetsByWithDraw(BankCardVO bankCard) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean insertTransactionDetail(BankCardTransactionDetailsVO details) {
		String sql = "";
		return false;
	}

}
