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

import cn.edu.shiep.entity.Contacter;
import cn.edu.shiep.entity.Mail;
import cn.edu.shiep.model.ContacterAnalyzeService;

public class ContacterAnalyzeAction implements ServletResponseAware{

	private HttpServletResponse response;
	
	@Resource(name="contacterAnalyzeService")
	private ContacterAnalyzeService contacterAnalyzeService;
	private String filter;
	
	@Action(value="ajaxGetHotContacterAction")
	public void getHotContacter() throws IOException{
		
		Map<String, Object> session = ServletActionContext.getContext().getSession();
		String user = (String) session.get("loginname");
		
		List<Contacter> contacters = contacterAnalyzeService.getHotContacter(user, filter);
		
		Iterator<Contacter> i = contacters.iterator();
		StringBuffer data = new StringBuffer();
		
		//×°ÔØÊý¾Ý
		data.append("{'contacters':[");
		while(i.hasNext()){
			Contacter contacter = i.next();
			data.append(contacter.toJSONString());
			if(i.hasNext()){
				data.append(",");
			}
		}
		data.append("]}");
		
		System.out.println("ContacterAnalyzeAction:"+data);
		
		response.getOutputStream().write(new String(data.toString().getBytes("UTF-8"), "GBK").getBytes());
	}	
	
	public String getFilter() {
		return filter;
	}

	public void setFilter(String filter) {
		this.filter = filter;
	}

	@Override
	public void setServletResponse(HttpServletResponse response) {
		this.response = response;		
	}
	
	public void setContacterAnalyzeService(
			ContacterAnalyzeService contacterAnalyzeService) {
		this.contacterAnalyzeService = contacterAnalyzeService;
	}
	
}
