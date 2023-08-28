package kr.co.sdy;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

class List {
	Statement stmt;
	ResultSet rs;
	int cnt;
	void countTable() {
		cnt = 0;
		stmt = DBUtil.statement();
		try {
			rs = stmt.executeQuery("select * from info");
			while (rs.next()) {
				cnt++;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (rs != null) try { rs.close();} catch (Exception e) {}
			if (stmt != null) DBUtil.disconnect(stmt);
		}
	}
	void listTitle() {
		System.out.println("전체출력");
	}
	void listShow() {
		stmt = DBUtil.statement();
		try {
			rs = stmt.executeQuery("select * from info");
			while (rs.next()) {
				System.out.println("아이디	: " + rs.getString("id"));
				System.out.println("비밀번호	: " + rs.getString("pw"));
				System.out.println("주소	: " + rs.getString("ad"));
				System.out.println("전화번호	: " + rs.getString("pn"));
				System.out.println();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (rs != null) try { rs.close();} catch (Exception e) {}
			if (stmt != null) DBUtil.disconnect(stmt);
		}
	}
	void list() {
		listTitle();
		listShow();
	}
}
