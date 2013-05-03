package cn.edu.shiep.utils;

public class MailProperty {

	private String smtpHost;
	private String smtpApop3;
	private String smtpAuth;
	private String transportProtocol;
	private String storeProtocol;
	
	public String getSmtpHost() {
		return smtpHost;
	}
	public void setSmtpHost(String smtpHost) {
		this.smtpHost = smtpHost;
	}
	public String getSmtpAuth() {
		return smtpAuth;
	}
	public void setSmtpAuth(String smtpAuth) {
		this.smtpAuth = smtpAuth;
	}
	public String getTransportProtocol() {
		return transportProtocol;
	}
	public void setTransportProtocol(String transportProtocol) {
		this.transportProtocol = transportProtocol;
	}
	public String getStoreProtocol() {
		return storeProtocol;
	}
	public void setStoreProtocol(String storeProtocol) {
		this.storeProtocol = storeProtocol;
	}
	
	
}
public String getPop3Host() {
		return pop3Host;
	}
	public void setPop3Host(String pop3Host) {
		this.pop3Host = pop3Host;
	}
	
	
}
