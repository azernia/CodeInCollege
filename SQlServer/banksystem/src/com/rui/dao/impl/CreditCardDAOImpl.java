package com.rui.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.rui.dao.infa.CreditCardDAOInfa;
import com.rui.util.DBUtil;
import com.rui.vo.CreditCardTransactionDetailsVO;

public class CreditCardDAOImpl implements CreditCardDAOInfa {

	@Override
	public double searchBalance(String creditCardNum) {
		String sql = "select balance from creditcard where creditcardnum = ?";
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = DBUtil.dbConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, creditCardNum);
			rs = ps.executeQuery();
			if(rs.next()) {
				return rs.getDouble("balance");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil.dbClose(conn, ps, rs);
		}
		return -1;
	}

	@Override
	public List<CreditCardTransactionDetailsVO> searchTransactionDetails(String creditCardNum) {
		String sql = "select ctd.* from ctrelationship ctr,creditcardtransactiondetails ctd where ctr.creditcardnum = ? and ctr.creditcardtransactionserialnum = ctd.creditcardtransactionserialnum";
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<CreditCardTransactionDetailsVO> list = new ArrayList<CreditCardTransactionDetailsVO>();
		try {
			conn = DBUtil.dbConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, creditCardNum);
			rs = ps.executeQuery();
			while(rs.next()) {
				CreditCardTransactionDetailsVO ccd = new CreditCardTransactionDetailsVO();
				ccd.setCreditCardTransactionSerialNum(rs.getLong("creditcardtransactionserialnum"));
				ccd.setRepaymentAmount(rs.getDouble("repaymentamount"));
				ccd.setRepaymentTime(rs.getString("repaymenttime"));
				ccd.setTransactionAmount(rs.getDouble("transactionAmount"));
				ccd.setTransactionChannel(rs.getString("transactionchannel"));
				ccd.setTransactionType(rs.getString("transactiontype"));
				ccd.setMerchant(rs.getString("merchants"));
				list.add(ccd);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return list;
	}

	@Override
	public double shouldRepaymentAmount(String creditCardNum) {
		String sql = "select ctd.repaymentamount from ctrelationship ctr,creditcardtransactiondetails ctd where ctr.creditcardnum = ? and ctr.creditcardtransactionserialnum = ctd.creditcardtransactionserialnum";
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		double count = 0;
		try {
			conn = DBUtil.dbConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, creditCardNum);
			rs = ps.executeQuery();
			while(rs.next()) {
				count += rs.getDouble("repaymentamount");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return count;
	}

}
