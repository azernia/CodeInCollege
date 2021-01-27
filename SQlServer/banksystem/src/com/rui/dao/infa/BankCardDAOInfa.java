package com.rui.dao.infa;

import java.util.List;

import com.rui.vo.BankCardTransactionDetailsVO;
import com.rui.vo.BankCardVO;

public interface BankCardDAOInfa {
	double searchAssets(BankCardVO bankCard);
	List<BankCardTransactionDetailsVO> searchTransactionDetails(BankCardVO bankCard);
	boolean updateAssetsBySaving(BankCardVO bankCard);
	boolean updateAssetsByWithDraw(BankCardVO bankCard);
	boolean insertTransactionDetail(BankCardTransactionDetailsVO details);
}
