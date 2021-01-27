package com.rui.vo;
/**
 * 银行卡交易明细实体类
 * @author Rui
 * @date 2019年12月18日下午11:23:00
 * @version 1.0
 */
public class BankCardTransactionDetailsVO {
	private long bankCardTransactionSerialNum;
	private String transactionAccount;
	private String transactionType;
	private String tradingChannel;
	private double transactionAmount;
	private String counterpartyAccount;
	private String transactionDate;
	
	public BankCardTransactionDetailsVO() {
	}
	public BankCardTransactionDetailsVO(long bankCardTransactionSerialNum, String transactionAccount,
			String transactionType, String tradingChannel, double transactionAmount, String counterpartyAccount,
			String transactionDate) {
		super();
		this.bankCardTransactionSerialNum = bankCardTransactionSerialNum;
		this.transactionAccount = transactionAccount;
		this.transactionType = transactionType;
		this.tradingChannel = tradingChannel;
		this.transactionAmount = transactionAmount;
		this.counterpartyAccount = counterpartyAccount;
		this.transactionDate = transactionDate;
	}
	public long getBankCardTransactionSerialNum() {
		return bankCardTransactionSerialNum;
	}
	public void setBankCardTransactionSerialNum(long bankCardTransactionSerialNum) {
		this.bankCardTransactionSerialNum = bankCardTransactionSerialNum;
	}
	public String getTransactionAccount() {
		return transactionAccount;
	}
	public void setTransactionAccount(String transactionAccount) {
		this.transactionAccount = transactionAccount;
	}
	public String getTransactionType() {
		return transactionType;
	}
	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}
	public String getTradingChannel() {
		return tradingChannel;
	}
	public void setTradingChannel(String tradingChannel) {
		this.tradingChannel = tradingChannel;
	}
	public double getTransactionAmount() {
		return transactionAmount;
	}
	public void setTransactionAmount(double transactionAmount) {
		this.transactionAmount = transactionAmount;
	}
	public String getCounterpartyAccount() {
		return counterpartyAccount;
	}
	public void setCounterpartyAccount(String counterpartyAccount) {
		this.counterpartyAccount = counterpartyAccount;
	}
	public String getTransactionDate() {
		return transactionDate;
	}
	public void setTransactionDate(String transactionDate) {
		this.transactionDate = transactionDate;
	}
	
}
