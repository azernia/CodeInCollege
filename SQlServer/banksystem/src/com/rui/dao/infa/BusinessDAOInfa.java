package com.rui.dao.infa;

import java.util.List;
import java.util.Map;

import com.rui.vo.BusinessVO;

public interface BusinessDAOInfa {
	List<BusinessVO> searchAllBusiness();
	Map<String, String> searchBusinessByBankCard(String bankCardNum);
}
