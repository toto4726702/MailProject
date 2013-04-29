package cn.edu.shiep.action;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;

import cn.edu.shiep.model.LoginService;
import cn.edu.shiep.utils.ResultType;

public class LoginAction {
	
	String username;
	String password;
	String checkcode;
	String remember;
	
	@Resource
	LoginService loginService;

	@Action(value="loginAction",results={
			@Result(name=ResultType.FAILURE,location="/login.jsp",type="redirect"),
			@Result(name=ResultType.SUCCESS,location="/mainAction",type="redirect")})
	public String execute() throws UnsupportedEncodingException{
		
		Map<String, Object> session = ServletActionContext.getContext().getSession();
		
		Map<String, Object> result = null;
		
		//务必进行转码
		username = new String(username.getBytes("ISO-8859-1"), "UTF-8");
		System.out.println(username+" "+password+" "+checkcode+"remember");
		
		//打开业务类
		result = loginService.validateUser(username, password,new HashMap<String, Object>());
		
		//存入数据
		session.putAll(result);
		
		return (String) result.get("result");
		
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getCheckcode() {
		return checkcode;
	}

	public void setCheckcode(String checkcode) {
		this.checkcode = checkcode;
	}
	
	public String getRemember() {
		return remember;
	}

	public void setRemember(String remember) {
		this.remember = remember;
	}

	public void setLoginService(LoginService loginService) {
		this.loginService = loginService;
	}

}
