package com.rui.vo;

public class CreditCardTransactionDetailsVO {
	private long creditCardTransactionSerialNum;
	private String transactionType;
	private double transactionAmount;
	private String repaymentTime;
	private double repaymentAmount;
	private String transactionChannel;
	private String merchant;
	
	public String getMerchant() {
		return merchant;
	}
	public void setMerchant(String merchant) {
		this.merchant = merchant;
	}
	public CreditCardTransactionDetailsVO() {
	}
	public CreditCardTransactionDetailsVO(long creditCardTransactionSerialNum, String transactionType,
			double transactionAmount, String repaymentTime, double repaymentAmount, String transactionChannel
			,String merchant) {
		super();
		this.creditCardTransactionSerialNum = creditCardTransactionSerialNum;
		this.transactionType = transactionType;
		this.transactionAmount = transactionAmount;
		this.repaymentTime = repaymentTime;
		this.repaymentAmount = repaymentAmount;
		this.transactionChannel = transactionChannel;
		this.merchant = merchant;
	}
	public String getTransactionChannel() {
		return transactionChannel;
	}
	public void setTransactionChannel(String transactionChannel) {
		this.transactionChannel = transactionChannel;
	}
	public long getCreditCardTransactionSerialNum() {
		return creditCardTransactionSerialNum;
	}
	public void setCreditCardTransactionSerialNum(long creditCardTransactionSerialNum) {
		this.creditCardTransactionSerialNum = creditCardTransactionSerialNum;
	}
	public String getTransactionType() {
		return transactionType;
	}
	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}
	public double getTransactionAmount() {
		return transactionAmount;
	}
	public void setTransactionAmount(double transactionAmount) {
		this.transactionAmount = transactionAmount;
	}
	public String getRepaymentTime() {
		return repaymentTime;
	}
	public void setRepaymentTime(String repaymentTime) {
		this.repaymentTime = repaymentTime;
	}
	public double getRepaymentAmount() {
		return repaymentAmount;
	}
	public void setRepaymentAmount(double repaymentAmount) {
		this.repaymentAmount = repaymentAmount;
	}
	
}
