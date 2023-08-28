package kr.co.sdy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Menu {
	Regist r = new Regist();
	List l = new List();
	Search s = new Search();
	Delete d = new Delete();
	Edit e = new Edit();
	Login rI = new Login();
	Logout rO = new Logout();
	static String session;
	static {
		session = null;
	}
	void loginWarning() {
		System.out.println("로그인부터 하세요");
	}
	void logoutWarning() {
		System.out.println("로그아웃부터 하세요");
	}
	public static void main(String[] args) {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		Menu m = new Menu();
		while (true) {
			System.out.println("R:회원가입 L:회원목록 S:ID찾기 D:회원탈퇴 E:회원수정 I:로그인 O:로그아웃 X:종료");
			char choice = ' ';
			try {
				String inputChar = br.readLine();
				choice = inputChar.charAt(0);
			} catch (IOException e) {
				e.printStackTrace();
			}
			try {
				if (!(choice > 64 && choice < 123)) {
					throw new MyException("알파벳만 입력하시오");
				}
			} catch (MyException e) {
				System.out.println(e.getMessage());
			}
			if (choice == 'r' || choice == 'R') {
				if (session == null) {
					m.r.regist();	
				} else {
					m.logoutWarning();
				}
			} else if (choice == 'l' || choice == 'L') {
				if (session == null) {
					m.loginWarning();
				} else {
					m.l.list();	
				}
			} else if (choice == 's' || choice == 'S') {
				if (session == null) {
					m.loginWarning();
				} else {
					m.s.search();	
				}
			} else if (choice == 'd' || choice == 'D') {
				if (session == null) {
					m.loginWarning();
				} else {
					m.d.delete();					
				}
			} else if (choice == 'e' || choice == 'E') {
				if (session == null) {
					m.loginWarning();
				} else {
					m.e.edit();					
				}
			} else if (choice == 'i' || choice == 'I') {
				if (session == null) {
					m.rI.login();	
				} else {
					m.logoutWarning();
				}
			} else if (choice == 'o' || choice == 'O') {
				if (session == null) {
					m.loginWarning();
				} else {
					m.rO.logout();
				}
			} else if (choice == 'x' || choice == 'X') {
				break;
			} else {
				System.out.println("올바른 알파벳 입력");
			}
		}
	}
}
