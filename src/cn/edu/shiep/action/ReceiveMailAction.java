package cn.edu.shiep.action;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.interceptor.ServletResponseAware;

import cn.edu.shiep.entity.Mail;
import cn.edu.shiep.model.ReceiveMailService;

public class ReceiveMailAction implements ServletResponseAware{

	private HttpServletResponse response;
	
	private String filter;
	private String order;
	
	@Resource(name="receiveMprivate String mailid;
	private String mark;
	private String importanteceiveMailService")
	private ReceiveMailService receiveMailService;
	tion(value="ajaxReceiveMailAction")
	public void receiveMail(){
		
	} throws IOException{
		
		Map<String, Object> session = ServletActionContext.getContext().getSession();
		String user = (String) session.get("loginname");
		//String pass = (String) session.get("password");
		
		List<Mail> mails = receiveMailService.getDBMail(user);
		
		Iterator<Mail> i = mails.iterator();
		StringBuffer data = new StringBuffer();
		
		//×°ÔØÊý¾Ý
		data.append("{'mails':[");
		while(i.hasNext()){
			Mail mail = i.next();
			data.append(mail.toJSONString());
			if(i.hasNext()){
				data.append(",");
			}
		}
		data.append("]}");
		
		System.out.println("ReceiveMailAction:"+data);
		
		response.getOutputStream().write(new String(data.toString().getBytes("UTF-8"), "GBK").getBytes());
	}


	public String getFilter() {
		return filter;
	}

	
	@Action(value="ajaxUpdateReadstatusAction")
	public void updateReadstatus() throws IOException{
		
		receiveMailService.updateReadstatus(mailid);
		System.out.println("ReceiveMailAction:Readstatus Changed");
	}

	@Action(value="ajaxUpdateImportantAction")
	public void updateImportant() throws IOException{
		
		receiveMailService.updateImportant(mailid,important);
		System.out.println("ReceiveMailAction:Important Changed");

	}
	
	@Action(value="ajaxUpdateMarkAction")
	public void updateMark() throws IOException{
		
		receiveMailService.updateMark(mailid,mark);
		System.out.println("ReceiveMailAction:Mark Changed");
;
	}

	public void setFilter(	
	@Action(value="ajaxSmartPriorAction")
	public void smartPrior(){
		ilter(String filter) {
		this.filter = filter;
	}

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}

	@Override
	public void setServletResponse(HttpServletResponse responsepublic String getMailid() {
		return mailid;
	}

	public void setMailid(String mailid) {
		this.mailid = mailid;
	}

	public String getMark() {
		return mark;
	}

	public void setMark(String mark) {
		this.mark = mark;
	}

	public String getImportant() {
		return important;
	}

	public void setImportant(String important) {
		this.important = importante response) {
		this.response = response;		
	}
	
	
	
}
