package cn.edu.shiep.mail;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

public class POP3Auth extends Authenticator{
	
	String user,password;
	
	public void setAccount(String user,String password){
		this.user = user;
		this.password = password;			
	}
	
	protected PasswordAuthentication getPasswordAuthentication(){
		return new PasswordAuthentication(user, password);
	}
}