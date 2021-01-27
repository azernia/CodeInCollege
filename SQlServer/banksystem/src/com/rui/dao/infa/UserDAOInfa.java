package com.rui.dao.infa;

import com.rui.vo.BankCardVO;
import com.rui.vo.CreditCardVO;

public interface UserDAOInfa {
	boolean checkLoginByBankCard(BankCardVO bankCard);
	boolean checkLoginByCreditCard(CreditCardVO creditCard);
}
