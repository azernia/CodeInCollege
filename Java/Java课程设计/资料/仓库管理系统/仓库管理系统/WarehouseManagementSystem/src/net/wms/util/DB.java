package net.wms.util;

import java.sql.Connection;
import java.sql.DriverManager;

public class DB {
	//数据库地址
	private String url = "jdbc:oracle:thin:@127.0.0.1:1521:orcl";
	//数据库用户名
	private String user = "root";
	//数据库密码
	private String pass = "123456789";
	//数据库连接
	public static Connection con;
	//构造方法
	public DB(){
		try {
			//加载驱动
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//获取连接
			con = DriverManager.getConnection(url, user, pass);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	//获取连接
	public static Connection getConnection(){
		if(con == null){
			new DB();
		}
		return con;
	}
}
