package cn.edu.shiep.action;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

public class SendMailAction implements ServletResponseAware,ServletRequestAware{
	
	private HttpServletResponse response;
	private HttpServletRequest request;
	
	@Action(value="ajaxSendMailAction")
	public void sendMail(){
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		System.out.println("AjaxSendMailAction:passed!"+username+" "+password);

		try {
			response.getOutputStream().write("goodwork".getBytes());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void setServletResponse(HttpServletResponse res) {
		this.response = res;
	}

	@Override
	public void setServletRequest(HttpServletRequest req) {
		this.request = req;
		
	}
	
}
