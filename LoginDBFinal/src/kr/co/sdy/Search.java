package kr.co.sdy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

class Search {
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	String id;
	Statement stmt;
	ResultSet rs;
	int cnt;
	
	void searchId() {
		System.out.println("회원정보검색");
		System.out.print("찾는 ID 입력 : ");
		try {
			id = br.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	void findSameId() {
		cnt = 0;
		stmt = DBUtil.statement();
		try {
			rs = stmt.executeQuery("select * from info where id = '"+id+"'");
			while (rs.next()) {
				System.out.println("아이디	: " + rs.getString("id"));
				System.out.println("비밀번호	: " + rs.getString("pw"));
				System.out.println("주소	: " + rs.getString("ad"));
				System.out.println("전화번호	: " + rs.getString("pn"));
				System.out.println();
				cnt++;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (rs != null) try { rs.close();} catch (Exception e) {}
			if (stmt != null) DBUtil.disconnect(stmt);
		}
		if (cnt == 0) {
			System.out.println("해당아이디로 검색된 정보가 없습니다.");
		} else {
			System.out.println("출력완료");
		}
	}
	void search() {
		searchId();
		findSameId();
	}
}
