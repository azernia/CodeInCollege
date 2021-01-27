package com.rui.view;

import java.util.Scanner;

import com.rui.dao.impl.StaffDAOImpl;
import com.rui.dao.impl.UserDAOImpl;
import com.rui.vo.BankCardVO;
import com.rui.vo.CreditCardVO;
import com.rui.vo.StaffVO;

public class Login {
	public void loginView() {
		Scanner sc = new Scanner(System.in);
		System.out.println("\t----------------��ӭʹ��XXX����ϵͳ----------------");
		System.out.print("\t----------------��ݣ�");
		String identity = sc.next();
		if("user".equals(identity)) {
			System.out.println("\t----------------1.���п�----------------");
			System.out.println("\t----------------2.���ÿ�----------------");
			System.out.print("\t----------------��ѡ��");
			int choice = sc.nextInt();
			if(choice == 1) {
				System.out.print("\t----------------�� �� �� �ţ�");
				String bankCardNum = sc.next();
				System.out.print("\t----------------���п����룺");
				int bankCardPwd = sc.nextInt();
				BankCardVO bankCard = new BankCardVO(bankCardNum, bankCardPwd);
				if(new UserDAOImpl().checkLoginByBankCard(bankCard)) {
					System.out.println("��¼�ɹ�");
					new UserView(bankCard.getBankCardNum()).bankCardMainView(bankCard);
				}else {
					System.out.println("��¼ʧ��");
				}
			}else if(choice == 2) {
				System.out.print("\t----------------�� �� �� �ţ�");
				String creditCardNum = sc.next();
				System.out.print("\t----------------���ÿ����룺");
				int creditCardPwd = sc.nextInt();
				CreditCardVO creditCard = new CreditCardVO(creditCardNum, creditCardPwd);
				if(new UserDAOImpl().checkLoginByCreditCard(creditCard)) {
					System.out.println("��¼�ɹ�");
					new UserView(creditCard).creditCardMainView();
				}else {
					System.out.println("��¼ʧ��");
				}
			}
			
		}else if("staff".equals(identity)) {
			System.out.print("\t----------------Ա���ţ�");
			int staffID = sc.nextInt();
			System.out.print("\t----------------��    �룺");
			String staffPwd = sc.next();
			StaffVO loginStaff = new StaffVO(staffID, staffPwd);
			int rank = new StaffDAOImpl().checkLogin(loginStaff);
			if(rank != 0) {
				System.out.println("��¼�ɹ�");
				new StaffView().staffView(rank);
			}else {
				System.out.println("��¼ʧ��");
			}
		}
		sc.close();
	}
}
