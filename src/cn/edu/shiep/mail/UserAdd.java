package cn.edu.shiep.mail;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class UserAdd {

	// 数据库连接参数
	private static String driverName = "com.mysql.jdbc.Driver";
	private static String dbURL = "jdbc:mysql://localhost/mail?autoReconnect=true";
	private static String userName = "root";
	private static String userPass = "900618";

	// 连接
	private static Connection conn = null;

	static {

		try {
			Class.forName(driverName);
			conn = DriverManager.getConnection(dbURL, userName, userPass);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static void main(String[] args) {

		try {
			//SQL语句
			PreparedStatement prep = conn
					.prepareStatement("insert into users values ('hello', ? ,'SHA',0,NULL,0,NULL) ");

			//prep.setString(1, DigestUtil.digestString("123456", "SHA"));

			//执行
			prep.execute();

			//关闭连接
			prep.close();
			conn.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
