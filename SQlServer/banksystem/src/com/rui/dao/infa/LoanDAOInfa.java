package com.rui.dao.infa;

import java.util.List;

import com.rui.vo.LoanVO;

public interface LoanDAOInfa {
	List<LoanVO> searchLoanMsg(String bankCardNum);
	boolean insertLoanMsg(LoanVO loan);
}
