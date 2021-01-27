package cn.com.dao;

import cn.com.VO.ManagerVO;

public interface ManagerInfa {
	boolean checkLogin(ManagerVO mvo);
	void updateLoginDate(String date,String userName);
}
