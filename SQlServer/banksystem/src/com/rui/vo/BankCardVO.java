package com.rui.vo;

/**
 * 银行卡实体类
 * 
 * @author Rui
 * @date 2019年12月18日下午11:10:57
 * @version 1.0
 */
public class BankCardVO {
	private String bankCardNum;
	private String bUserID;
	private int bankCardPwd;
	private double asset;
	private String businessID;
	private String loanID;

	public BankCardVO() {
	}

	public BankCardVO(String bankCardNum, int bankCardPwd) {
		this.bankCardNum = bankCardNum;
		this.bankCardPwd = bankCardPwd;
	}

	public BankCardVO(String bankCardNum, String bUserID, int bankCardPwd, double asset, String businessID,
			String loanID) {
		super();
		this.bankCardNum = bankCardNum;
		this.bUserID = bUserID;
		this.bankCardPwd = bankCardPwd;
		this.asset = asset;
		this.businessID = businessID;
		this.loanID = loanID;
	}

	public String getBankCardNum() {
		return bankCardNum;
	}

	public void setBankCardNum(String bankCardNum) {
		this.bankCardNum = bankCardNum;
	}

	public String getbUserID() {
		return bUserID;
	}

	public void setbUserID(String bUserID) {
		this.bUserID = bUserID;
	}

	public int getBankCardPwd() {
		return bankCardPwd;
	}

	public void setBankCardPwd(int bankCardPwd) {
		this.bankCardPwd = bankCardPwd;
	}

	public double getAsset() {
		return asset;
	}

	public void setAsset(double asset) {
		this.asset = asset;
	}

	public String getBusinessID() {
		return businessID;
	}

	public void setBusinessID(String businessID) {
		this.businessID = businessID;
	}

	public String getLoanID() {
		return loanID;
	}

	public void setLoanID(String loanID) {
		this.loanID = loanID;
	}
}
