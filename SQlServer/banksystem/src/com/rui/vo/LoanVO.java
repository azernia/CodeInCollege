package com.rui.vo;
/**
 * 贷款实体类
 * @author Rui
 * @date 2019年12月18日下午11:20:51
 * @version 1.0
 */
public class LoanVO {
	private String loanID;
	private String bankCardNum;
	private double loanAmount;
	private String loanDate;
	private String repaymentDate;
	
	public LoanVO() {
	}
	public LoanVO(String loanID, String bankCardNum, double loanAmount, String loanDate, String repaymentDate) {
		super();
		this.loanID = loanID;
		this.bankCardNum = bankCardNum;
		this.loanAmount = loanAmount;
		this.loanDate = loanDate;
		this.repaymentDate = repaymentDate;
	}
	public String getLoanID() {
		return loanID;
	}
	public void setLoanID(String loanID) {
		this.loanID = loanID;
	}
	public String getBankCardNum() {
		return bankCardNum;
	}
	public void setBankCardNum(String bankCardNum) {
		this.bankCardNum = bankCardNum;
	}
	public double getLoanAmount() {
		return loanAmount;
	}
	public void setLoanAmount(double loanAmount) {
		this.loanAmount = loanAmount;
	}
	public String getLoanDate() {
		return loanDate;
	}
	public void setLoanDate(String loanDate) {
		this.loanDate = loanDate;
	}
	public String getRepaymentDate() {
		return repaymentDate;
	}
	public void setRepaymentDate(String repaymentDate) {
		this.repaymentDate = repaymentDate;
	}
	
	
}
