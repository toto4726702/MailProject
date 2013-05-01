package cn.edu.shiep.action;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import cn.edu.shiep.model.SendMailService;

public class SendMailAction implements ServletResponseAware,ServletRequestAware{
	
	private HttpServletResponse response;
	private HttpServletRequest request;
	
	private String sendTo;
	private String copyTo;
	private String title;
	private String content;
	private String passwd;
	private String telePass;
	private String importantMail;
	private String encryptMethod;
	
	@Resource(name="sendMailService")
	private SendMailService sendMailService;
	
	@Action(value="ajaxSendMailAction")
	public void sendMail(){
		
		Map<String, Object> session = ServletActionContext.getContext().getSession();
		String user = (String) session.get("loginname");
		String pass = (String) session.get("password");
		
		System.out.println("AjaxSendMailAction:passed!"+user+" "+pass);
		
		Map<String, String> props = new HashMap<String, String>();
		props.put("sender", user.split("@")[0]);
		props.put("sendTo", sendTo);
		props.put("copyTo", copyTo);
		props.put("title", title);
		props.put("content", content);
		props.put("passwd", passwd);
		props.put("telePass", telePass);
		props.put("importantMail", importantMail);
		props.put("encryptMethod", encryptMethod);
		
		Boolean retData = sendMailService.sendJamesMail(user, pass, props);
		
		try {
			response.getOutputStream().write(retData.toString().getBytes());
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
	
	

	@Override
	public String toString() {
		return "SendMailAction [sendTo=" + sendTo + ", copyTo=" + copyTo
				+ ", title=" + title + ", content=" + content + ", passwd="
				+ passwd + ", telePass=" + telePass + ", importantMail="
				+ importantMail + ", encryptMethod=" + encryptMethod + "]";
	}

	public String getSendTo() {
		return sendTo;
	}

	public void setSendTo(String sendTo) {
		this.sendTo = sendTo;
	}

	public HttpServletResponse getResponse() {
		return response;
	}

	public void setResponse(HttpServletResponse response) {
		this.response = response;
	}

	public HttpServletRequest getRequest() {
		return request;
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}

	public String getCopyTo() {
		return copyTo;
	}

	public void setCopyTo(String copyTo) {
		this.copyTo = copyTo;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getPasswd() {
		return passwd;
	}

	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}

	public String getTelePass() {
		return telePass;
	}

	public void setTelePass(String telePass) {
		this.telePass = telePass;
	}

	public String getImportantMail() {
		return importantMail;
	}

	public void setImportantMail(String importantMail) {
		this.importantMail = importantMail;
	}

	public String getEncryptMethod() {
		return encryptMethod;
	}

	public void setEncryptMethod(String encryptMethod) {
		this.encryptMethod = encryptMethod;
	}

	public SendMailService getSendMailService() {
		return sendMailService;
	}

	public void setSendMailService(SendMailService sendMailService) {
		this.sendMailService = sendMailService;
	}
	
}
