package net.wms.util;

import java.sql.Connection;
import java.sql.DriverManager;

public class DB {
	//���ݿ��ַ
	private String url = "jdbc:oracle:thin:@127.0.0.1:1521:orcl";
	//���ݿ��û���
	private String user = "root";
	//���ݿ�����
	private String pass = "123456789";
	//���ݿ�����
	public static Connection con;
	//���췽��
	public DB(){
		try {
			//��������
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//��ȡ����
			con = DriverManager.getConnection(url, user, pass);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	//��ȡ����
	public static Connection getConnection(){
		if(con == null){
			new DB();
		}
		return con;
	}
}
