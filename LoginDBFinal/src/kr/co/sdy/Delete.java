package kr.co.sdy;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

class Delete {
	Statement stmt;
	ResultSet rs;
	int cnt;
	
	void deleteId() {
		cnt = 0;
		stmt = DBUtil.statement();
		try {
			cnt = stmt.executeUpdate("delete from info where id = '"+Menu.session+"'");
			if (cnt > 0) {
				System.out.println("회원아이디-" + Menu.session + "탈퇴성공");
				Menu.session = null;
			} else {
				System.out.println("탈퇴실패...");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (stmt != null) DBUtil.disconnect(stmt);
		}
	}
	void delete() {
		deleteId();
	}
}
