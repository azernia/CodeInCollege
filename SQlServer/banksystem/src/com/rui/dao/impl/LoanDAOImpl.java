package com.rui.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.rui.dao.infa.LoanDAOInfa;
import com.rui.util.DBUtil;
import com.rui.vo.LoanVO;

public class LoanDAOImpl implements LoanDAOInfa {

	@Override
	public List<LoanVO> searchLoanMsg(String bankCardNum) {
		String sql = "select l.* from bankcard bc,loan l where bc.bankcardnum = ? and bc.loanid = l.loanid";
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<LoanVO> list = new ArrayList<LoanVO>();
		try {
			conn = DBUtil.dbConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, bankCardNum);
			rs = ps.executeQuery();
			if(rs.next()) {
				LoanVO lvo = new LoanVO();
				lvo.setBankCardNum(rs.getString("bankcardnum"));
				lvo.setLoanID(rs.getString("loanid"));
				lvo.setLoanAmount(rs.getDouble("loanamount"));
				lvo.setLoanDate(rs.getString("loandate"));
				lvo.setRepaymentDate(rs.getString("repaymentdate"));
				list.add(lvo);
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
	public boolean insertLoanMsg(LoanVO loan) {
		// TODO Auto-generated method stub
		return false;
	}

}
