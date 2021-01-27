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
		System.out.println("\t----------------欢迎使用XXX银行系统----------------");
		System.out.print("\t----------------身份：");
		String identity = sc.next();
		if("user".equals(identity)) {
			System.out.println("\t----------------1.银行卡----------------");
			System.out.println("\t----------------2.信用卡----------------");
			System.out.print("\t----------------请选择：");
			int choice = sc.nextInt();
			if(choice == 1) {
				System.out.print("\t----------------银 行 卡 号：");
				String bankCardNum = sc.next();
				System.out.print("\t----------------银行卡密码：");
				int bankCardPwd = sc.nextInt();
				BankCardVO bankCard = new BankCardVO(bankCardNum, bankCardPwd);
				if(new UserDAOImpl().checkLoginByBankCard(bankCard)) {
					System.out.println("登录成功");
					new UserView(bankCard.getBankCardNum()).bankCardMainView(bankCard);
				}else {
					System.out.println("登录失败");
				}
			}else if(choice == 2) {
				System.out.print("\t----------------信 用 卡 号：");
				String creditCardNum = sc.next();
				System.out.print("\t----------------信用卡密码：");
				int creditCardPwd = sc.nextInt();
				CreditCardVO creditCard = new CreditCardVO(creditCardNum, creditCardPwd);
				if(new UserDAOImpl().checkLoginByCreditCard(creditCard)) {
					System.out.println("登录成功");
					new UserView(creditCard).creditCardMainView();
				}else {
					System.out.println("登录失败");
				}
			}
			
		}else if("staff".equals(identity)) {
			System.out.print("\t----------------员工号：");
			int staffID = sc.nextInt();
			System.out.print("\t----------------密    码：");
			String staffPwd = sc.next();
			StaffVO loginStaff = new StaffVO(staffID, staffPwd);
			int rank = new StaffDAOImpl().checkLogin(loginStaff);
			if(rank != 0) {
				System.out.println("登录成功");
				new StaffView().staffView(rank);
			}else {
				System.out.println("登录失败");
			}
		}
		sc.close();
	}
}
