package kr.co.sdy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

class Login {
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	String id;
	String pw;
	Statement stmt;
	ResultSet rs;
	int cnt;
	
	void loginInput() {
		System.out.print("아이디입력 : ");
		try {
			id = br.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.print("비밀번호입력 : ");
		try {
			pw = br.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	void loginCheck() {
		cnt = 0;
		stmt = DBUtil.statement();
		try {
			rs = stmt.executeQuery("select * from info where id = '"+id+"' and pw = '"+pw+"'");
			while (rs.next()) {
				cnt++;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (rs != null) try { rs.close();} catch (Exception e) {}
			if (stmt != null) DBUtil.disconnect(stmt);
		}
		if (cnt > 0) {
			System.out.println("로그인 완료");
			Menu.session = id;
		} else {
			System.out.println("로그인 실패");
		}
	}
	void login() {
		loginInput();
		loginCheck();
	}
}
