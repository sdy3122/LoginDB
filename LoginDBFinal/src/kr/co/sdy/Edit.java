package kr.co.sdy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.sql.Statement;

class Edit {
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	Statement stmt;
	int cnt;
	String id;
	String pw;
	String ad;
	String pn;
	int checkNum;
	void editTitle() {
		System.out.println("수정메뉴");
	}
	void inputId() {
		System.out.println("아이디 입력 : ");
		try {
			id = br.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	void inputPw() {
		System.out.print("패스워드 입력 : ");
		try {
			pw = br.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	void inputAd() {
		System.out.print("주소입력 : ");
		try {
			ad = br.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	void inputPnTitle() {
		System.out.print("전화번호 입력 : ");
	}
	void inputPn() {
		String number = null;
		cnt = 0;
		checkNum = 0;
		try {
			number = br.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		for (int i = 0; i < number.length(); i++) {
			if (!(number.charAt(i) > 47 && (number.charAt(i) < 58))) {
				checkNum++;
				break;
			} else {
				cnt++;				
			}
		}
		if (checkNum > 0) {
			System.out.println("숫자만 입력하세요");			
			inputPn();
		} else {
			if (cnt > 11) {
				System.out.println("너무 깁니다 11자내로 입력해주세요");
				inputPn();
			} else {
				pn = number;
			}
		}
	}
	void editDb() {
		cnt = 0;
		stmt = DBUtil.statement();
		try {
			cnt = stmt.executeUpdate("update info set id = '"+id+"',  pw = '"+pw+"', pn = '"+pn+"', ad = '"+ad+"' where id = '"+Menu.session+"'");
			if (cnt > 0) {
				System.out.println("회원정보 수정 완료");
				Menu.session = id;
			} else {
				System.out.println("수정 실패");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (stmt != null) DBUtil.disconnect(stmt);
		}
	}
	void edit() {
		editTitle();
		inputId();
		inputPw();
		inputAd();
		inputPnTitle();
		inputPn();
		editDb();
	}
}
