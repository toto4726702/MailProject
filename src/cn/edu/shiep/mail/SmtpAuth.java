package cn.edu.shiep.mail;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

public class SmtpAuth extends Authenticator {

	private String user;
	private String pass;
	
	public SmtpAuth() {}
	
	public SmtpAuth(String user, String pass) {
		this.user = user;
		this.pass = pass;
	}

	public void setUserInfo(String user, String pass){
		this.user = user;
		this.pass = pass;
	}
	
	protected PasswordAuthentication getPasswordAuthentication() {
		return new PasswordAuthentication(user, pass);	
	}
	
	/*以下自动生成*/
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
	
	
	
	
}
