package kr.co.sdy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

class Regist {
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	String id;
	String pw;
	String ad;
	String pn;
	int cnt;
	int checkNum;
	Statement stmt;
	ResultSet rs;
	static ArrayList<Info> al = new ArrayList<Info>();
	void registTitle() {
		System.out.println("회원정보등록");
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
	void checkDuplicationId() {
		stmt = DBUtil.statement();
		try {
			rs = stmt.executeQuery("select * from info where id = '"+id+"' ");
			while (rs.next()) {
				System.out.println("ID가 중복되었습니다. 다른ID로 등록해주세요");
				inputId();
				break;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (rs != null) try { rs.close(); } catch (Exception e) {}
			if (stmt != null) DBUtil.disconnect(stmt);
		}
	}
	void registInfoAl() {
		Info i = new Info();
		i.setId(id);
		i.setPw(pw);
		i.setAd(ad);
		i.setPn(pn);
		al.add(i);
	}
	void registInfoDb() {
		stmt = DBUtil.statement();
		cnt = 0;
		try {
			cnt = stmt.executeUpdate("insert into info(id, pw, pn, ad) values('"+id+"', '"+pw+"', '"+pn+"', '"+ad+"')");
			if (cnt == 0) {
				System.out.println("등록실패\n");
			} else {
				System.out.println("등록성공\n");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (stmt != null) DBUtil.disconnect(stmt);
		}
	}
	void regist() {
		registTitle();
		inputId();
		checkDuplicationId();
		inputPw();
		inputAd();
		inputPnTitle();
		inputPn();
		registInfoAl();
		registInfoDb();
	}
}
