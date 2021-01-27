package com.rui.vo;
/**
 * 信用卡实体类
 * @author Rui
 * @date 2019年12月18日下午11:15:01
 * @version 1.0
 */
public class CreditCardVO {
	private String creditCardNum;
	private String bUserID;
	private int creditCardPwd;
	private double totalDegree;
	/** 余额 */
	private double balance;
	/** 信用积分 */
	private int creditScore;
	
	public CreditCardVO() {
	}
	public CreditCardVO(String creditCardNum, int creditCardPwd) {
		this.creditCardNum = creditCardNum;
		this.creditCardPwd = creditCardPwd;
	}
	public CreditCardVO(String creditCardNum, String bUserID, int creditCardPwd, double totalDegree, double balance,
			int creditScore) {
		super();
		this.creditCardNum = creditCardNum;
		this.bUserID = bUserID;
		this.creditCardPwd = creditCardPwd;
		this.totalDegree = totalDegree;
		this.balance = balance;
		this.creditScore = creditScore;
	}
	public String getCreditCardNum() {
		return creditCardNum;
	}
	public void setCreditCardNum(String creditCardNum) {
		this.creditCardNum = creditCardNum;
	}
	public String getbUserID() {
		return bUserID;
	}
	public void setbUserID(String bUserID) {
		this.bUserID = bUserID;
	}
	public int getCreditCardPwd() {
		return creditCardPwd;
	}
	public void setCreditCardPwd(int creditCardPwd) {
		this.creditCardPwd = creditCardPwd;
	}
	public double getTotalDegree() {
		return totalDegree;
	}
	public void setTotalDegree(double totalDegree) {
		this.totalDegree = totalDegree;
	}
	public double getBalance() {
		return balance;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}
	public int getCreditScore() {
		return creditScore;
	}
	public void setCreditScore(int creditScore) {
		this.creditScore = creditScore;
	}
}
