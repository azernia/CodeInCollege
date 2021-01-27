package com.rui.view;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

import com.rui.dao.impl.BankCardDAOImpl;
import com.rui.dao.impl.BusinessDAOImpl;
import com.rui.dao.impl.CreditCardDAOImpl;
import com.rui.dao.impl.LoanDAOImpl;
import com.rui.vo.BankCardTransactionDetailsVO;
import com.rui.vo.BankCardVO;
import com.rui.vo.BusinessVO;
import com.rui.vo.CreditCardTransactionDetailsVO;
import com.rui.vo.CreditCardVO;
import com.rui.vo.LoanVO;

public class UserView {
	private Scanner sc = new Scanner(System.in);
	private String bankCardNum;
	private String creditCardNum;
	public UserView() {}
	public UserView(String bankCardNum) {
		this.bankCardNum = bankCardNum;
	}
	public UserView(CreditCardVO creditCard) {
		this.creditCardNum = creditCard.getCreditCardNum();
	}
	public void bankCardMainView(BankCardVO bankCard) {
		System.out.println("\t-----------------\tXXX银行主菜单\t-----------------");
		System.out.println("\t-----------------\t银行卡\t\t-----------------");
		System.out.println("\t-----------------\t1.存款\t\t-----------------");
		System.out.println("\t-----------------\t2.取款\t\t-----------------");
		System.out.println("\t-----------------\t3.查询资产\t\t-----------------");
		System.out.println("\t-----------------\t4.办理业务\t\t-----------------");
		System.out.println("\t-----------------\t5.贷款\t\t-----------------");
		System.out.println("\t-----------------\t6.转账\t\t-----------------");
		System.out.println("\t-----------------\t7.查看交易记录\t\t-----------------");
		System.out.println("\t-----------------\t8.退出系统\t\t-----------------");
		System.out.print("\t-----------------\t请选择：");
		int choice = sc.nextInt();
		switch(choice) {
			case 1:
				bankSavingsView();
				break;
			case 2:
				withDrawView();
				break;
			case 3:
				System.out.println("当前账户剩余资产为：" + new BankCardDAOImpl().searchAssets(bankCard));
				break;
			case 4:
				transactBusinessView();
				break;
			case 5:
				loanView();
				break;
			case 6:
				transferView();
				break;
			case 7:
				showBankCardTransactionDetails(bankCard);
				break;
			case 8:
				System.out.println("\t-----------------感谢您的支持-----------------");
				System.exit(0);
				break;
			default :
				System.out.println("无效操作");
		}
		sc.close();
	}
	/**
	 * 存款
	 */
	public void bankSavingsView() {
		System.out.println("主菜单>>存款");
		System.out.print("请输入您的存款数额：");
		
	}
	/**
	 * 取款
	 */
	public void withDrawView() {
		
	}
	/**
	 * 办理业务
	 */
	public void transactBusinessView() {
		System.out.println("主菜单>>业务");
		System.out.println("\t-----------------\t1.查看业务种类");
		System.out.println("\t-----------------\t2.查看该卡办理的业务");
		System.out.print("请选择：");
		int choice = sc.nextInt();
		switch (choice) {
			case 1:
				showBusinessMsg();
				break;
			case 2:
				showBusinessMsgByBankCard();
				break;
		}
	}
	
	private void showBusinessMsgByBankCard() {
		Map<String, String> map = new BusinessDAOImpl().searchBusinessByBankCard(bankCardNum);
		for(String key : map.keySet()) {
			System.out.println(key + ":" + map.get(key).toString());
		}
	}
	
	private void showBusinessMsg() {
		List<BusinessVO> list = new BusinessDAOImpl().searchAllBusiness();
		for(BusinessVO bvo : list) {
			System.out.println("业务名称:" + bvo.getBusinessName());
			System.out.println("业务描述:" + bvo.getDescription());
		}
	}
	/**
	 * 贷款
	 */
	public void loanView() {
		System.out.println("主菜单>>贷款");
		System.out.println("\t-----------------\t1.查看申请的贷款 ");
		System.out.println("\t-----------------\t2.办理贷款");
		System.out.print("请选择：");
		int choice = sc.nextInt();
		switch(choice) {
		case 1:
			showLoanMsg();
			break;
		case 2:
			applyLoanView();
			break;
		}
	}
	
	private void showLoanMsg() {
		System.out.println("主菜单>>贷款>>贷款信息");
		List<LoanVO> list = new LoanDAOImpl().searchLoanMsg(bankCardNum);
		System.out.println("贷款编号\t\t贷款银行卡号   贷款额度\t\t贷款日期\t\t\t还款日期");
		for(LoanVO lvo : list) {
			System.out.print(lvo.getLoanID() + "\t" + replaceStr(lvo.getBankCardNum()).toString() + "\t"
		+ lvo.getLoanAmount() + "\t" + lvo.getLoanDate() + "\t" + lvo.getRepaymentDate());
		}
	}
	
	private void applyLoanView() {
		
	}
	
	/**
	 * 转账
	 */
	public void transferView() {
		
	}
	
	public void showBankCardTransactionDetails(BankCardVO bankCard) {
		System.out.println("主菜单>>交易明细");
		List<BankCardTransactionDetailsVO> list = new BankCardDAOImpl().searchTransactionDetails(bankCard);
		System.out.println("\t交易流水号\t\t交易账户\t交易类型\t交易渠道\t交易金额\t对方账户\t交易时间");
		for(BankCardTransactionDetailsVO btc : list) {
			StringBuffer strBuf = replaceStr(btc.getCounterpartyAccount());
			StringBuffer strBuf2 = replaceStr(btc.getTransactionAccount());
			System.out.println("\t" + btc.getBankCardTransactionSerialNum() + "\t" + strBuf2.toString()
			+ "\t" + btc.getTransactionType() + "\t" + btc.getTradingChannel() + "\t" + btc.getTransactionAmount()
			+ "\t" + strBuf.toString() + "\t" + btc.getTransactionDate());
		}
	}
	
	private StringBuffer replaceStr(String str) {
		String replaceStr = "**";
		StringBuffer strBuf = new StringBuffer(str);
		if(strBuf.length() == 18) {
			strBuf.replace(0, 13, replaceStr);
		}
		return strBuf;
	}
	
	public void creditCardMainView() {
		System.out.println("\t-----------------\tXXX银行主菜单\t-----------------");
		System.out.println("\t-----------------\t信用卡\t-----------------");
		System.out.println("\t-----------------\t1.取现\t-----------------");
		System.out.println("\t-----------------\t2.还款\t-----------------");
		System.out.println("\t-----------------\t3.查看余额\t-----------------");
		System.out.println("\t-----------------\t4.查看消费记录\t-----------------");
		System.out.println("\t-----------------\t5.退出系统\t-----------------");
		System.out.print("\t-----------------\t请选择：");
		int choice = sc.nextInt();
		switch(choice) {
			case 1:
				cashOutView();
				break;
			case 2:
				repaymentView();
				break;
			case 3:
				System.out.println("当前可用余额为：" + new CreditCardDAOImpl().searchBalance(creditCardNum)
						+ "应还款：" + new CreditCardDAOImpl().shouldRepaymentAmount(creditCardNum));
				break;
			case 4:
				showCreditCardTransactionDetails();
				break;
			case 5:
				System.out.println("\t-----------------感谢您的支持-----------------");
				System.exit(0);
				break;
			default :
				System.out.println("无效操作");
		}
		sc.close();
	}
	/**
	 * 取现
	 */
	public void cashOutView() {
		
	}
	
	public void repaymentView() {
		
	}
	
	public void showCreditCardTransactionDetails() {
		System.out.println("主菜单>>交易明细");
		List<CreditCardTransactionDetailsVO> list = new CreditCardDAOImpl().searchTransactionDetails(creditCardNum);
		System.out.println("交易流水号\t\t交易类型\t交易商户\t交易额度\t还款时间\t\t\t还款金额");
		for(CreditCardTransactionDetailsVO ccd : list) {
			System.out.println(ccd.getCreditCardTransactionSerialNum() + "\t" + ccd.getTransactionType() + "\t" + ccd.getMerchant() + "\t"
			+ ccd.getTransactionAmount() + "\t" + ccd.getRepaymentTime() + "\t" + ccd.getRepaymentAmount());
		}
	}
}
