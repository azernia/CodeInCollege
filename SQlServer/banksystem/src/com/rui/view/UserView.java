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
		System.out.println("\t-----------------\tXXX�������˵�\t-----------------");
		System.out.println("\t-----------------\t���п�\t\t-----------------");
		System.out.println("\t-----------------\t1.���\t\t-----------------");
		System.out.println("\t-----------------\t2.ȡ��\t\t-----------------");
		System.out.println("\t-----------------\t3.��ѯ�ʲ�\t\t-----------------");
		System.out.println("\t-----------------\t4.����ҵ��\t\t-----------------");
		System.out.println("\t-----------------\t5.����\t\t-----------------");
		System.out.println("\t-----------------\t6.ת��\t\t-----------------");
		System.out.println("\t-----------------\t7.�鿴���׼�¼\t\t-----------------");
		System.out.println("\t-----------------\t8.�˳�ϵͳ\t\t-----------------");
		System.out.print("\t-----------------\t��ѡ��");
		int choice = sc.nextInt();
		switch(choice) {
			case 1:
				bankSavingsView();
				break;
			case 2:
				withDrawView();
				break;
			case 3:
				System.out.println("��ǰ�˻�ʣ���ʲ�Ϊ��" + new BankCardDAOImpl().searchAssets(bankCard));
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
				System.out.println("\t-----------------��л����֧��-----------------");
				System.exit(0);
				break;
			default :
				System.out.println("��Ч����");
		}
		sc.close();
	}
	/**
	 * ���
	 */
	public void bankSavingsView() {
		System.out.println("���˵�>>���");
		System.out.print("���������Ĵ�����");
		
	}
	/**
	 * ȡ��
	 */
	public void withDrawView() {
		
	}
	/**
	 * ����ҵ��
	 */
	public void transactBusinessView() {
		System.out.println("���˵�>>ҵ��");
		System.out.println("\t-----------------\t1.�鿴ҵ������");
		System.out.println("\t-----------------\t2.�鿴�ÿ������ҵ��");
		System.out.print("��ѡ��");
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
			System.out.println("ҵ������:" + bvo.getBusinessName());
			System.out.println("ҵ������:" + bvo.getDescription());
		}
	}
	/**
	 * ����
	 */
	public void loanView() {
		System.out.println("���˵�>>����");
		System.out.println("\t-----------------\t1.�鿴����Ĵ��� ");
		System.out.println("\t-----------------\t2.�������");
		System.out.print("��ѡ��");
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
		System.out.println("���˵�>>����>>������Ϣ");
		List<LoanVO> list = new LoanDAOImpl().searchLoanMsg(bankCardNum);
		System.out.println("������\t\t�������п���   ������\t\t��������\t\t\t��������");
		for(LoanVO lvo : list) {
			System.out.print(lvo.getLoanID() + "\t" + replaceStr(lvo.getBankCardNum()).toString() + "\t"
		+ lvo.getLoanAmount() + "\t" + lvo.getLoanDate() + "\t" + lvo.getRepaymentDate());
		}
	}
	
	private void applyLoanView() {
		
	}
	
	/**
	 * ת��
	 */
	public void transferView() {
		
	}
	
	public void showBankCardTransactionDetails(BankCardVO bankCard) {
		System.out.println("���˵�>>������ϸ");
		List<BankCardTransactionDetailsVO> list = new BankCardDAOImpl().searchTransactionDetails(bankCard);
		System.out.println("\t������ˮ��\t\t�����˻�\t��������\t��������\t���׽��\t�Է��˻�\t����ʱ��");
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
		System.out.println("\t-----------------\tXXX�������˵�\t-----------------");
		System.out.println("\t-----------------\t���ÿ�\t-----------------");
		System.out.println("\t-----------------\t1.ȡ��\t-----------------");
		System.out.println("\t-----------------\t2.����\t-----------------");
		System.out.println("\t-----------------\t3.�鿴���\t-----------------");
		System.out.println("\t-----------------\t4.�鿴���Ѽ�¼\t-----------------");
		System.out.println("\t-----------------\t5.�˳�ϵͳ\t-----------------");
		System.out.print("\t-----------------\t��ѡ��");
		int choice = sc.nextInt();
		switch(choice) {
			case 1:
				cashOutView();
				break;
			case 2:
				repaymentView();
				break;
			case 3:
				System.out.println("��ǰ�������Ϊ��" + new CreditCardDAOImpl().searchBalance(creditCardNum)
						+ "Ӧ���" + new CreditCardDAOImpl().shouldRepaymentAmount(creditCardNum));
				break;
			case 4:
				showCreditCardTransactionDetails();
				break;
			case 5:
				System.out.println("\t-----------------��л����֧��-----------------");
				System.exit(0);
				break;
			default :
				System.out.println("��Ч����");
		}
		sc.close();
	}
	/**
	 * ȡ��
	 */
	public void cashOutView() {
		
	}
	
	public void repaymentView() {
		
	}
	
	public void showCreditCardTransactionDetails() {
		System.out.println("���˵�>>������ϸ");
		List<CreditCardTransactionDetailsVO> list = new CreditCardDAOImpl().searchTransactionDetails(creditCardNum);
		System.out.println("������ˮ��\t\t��������\t�����̻�\t���׶��\t����ʱ��\t\t\t������");
		for(CreditCardTransactionDetailsVO ccd : list) {
			System.out.println(ccd.getCreditCardTransactionSerialNum() + "\t" + ccd.getTransactionType() + "\t" + ccd.getMerchant() + "\t"
			+ ccd.getTransactionAmount() + "\t" + ccd.getRepaymentTime() + "\t" + ccd.getRepaymentAmount());
		}
	}
}
