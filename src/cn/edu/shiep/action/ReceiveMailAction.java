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
	
	@Resource(name="receiveMailService")
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

	public void setFilter(String filter) {
		this.filter = filter;
	}

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}

	@Override
	public void setServletResponse(HttpServletResponse response) {
		this.response = response;		
	}
	
	
	
}
