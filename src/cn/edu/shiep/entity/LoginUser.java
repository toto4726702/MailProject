package cn.edu.shiep.entity;

public class LoginUser {

	public String username;
	public String pwdHash;
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPwdHash() {
		return pwdHash;
	}

	public void setPwdHash(String pwdHash) {
		this.pwdHash = pwdHash;
	}

	@Override
	public String toString() {
		return "LoginUser [username=" + username + ", pwdHash=" + pwdHash + "]";
	}


}
