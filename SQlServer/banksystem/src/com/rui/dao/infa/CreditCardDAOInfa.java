package com.rui.dao.infa;

import java.util.List;

import com.rui.vo.CreditCardTransactionDetailsVO;

public interface CreditCardDAOInfa {
	double searchBalance(String creditCardNum);
	List<CreditCardTransactionDetailsVO> searchTransactionDetails(String creditCardNum);
	double shouldRepaymentAmount(String creditCardNum);
}
