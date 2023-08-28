package kr.co.sdy;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DBUtil {
	static final String driverName = "com.mysql.cj.jdbc.Driver";
	static final String url = "jdbc:mysql://127.0.0.1:3306/login_db";
	static final String user = "root";
	static final String passwd = "0000";
	static Connection connect() {
		Connection conn = null;
		try {
			Class.forName(driverName);
			conn = DriverManager.getConnection(url, user, passwd);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}
	static Statement statement() {
		Statement stmt = null;
		try {
			stmt = connect().createStatement();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return stmt;
	}
	static void disconnect(Statement stmt) {
		try {
			stmt.close();
			DriverManager.getConnection(url, user, passwd).close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
