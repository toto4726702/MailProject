package cn.edu.shiep.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.dispatcher.ng.filter.StrutsPrepareAndExecuteFilter;

public class MyStruts2Filter extends StrutsPrepareAndExecuteFilter{

	 public void doFilter(ServletRequest req, ServletResponse res,FilterChain chain) throws IOException,ServletException {  
	        HttpServletRequest request = (HttpServletRequest) req;  
	        //�����˵�url�������������  
	        if (request.getRequestURI().contains("/ws")) {  
	            //System.out.println��"Ӧ���Զ���Ĺ�����"��;  
	            chain.doFilter(req, res);  
	        }else{
	            //System.out.println��"Ӧ��Ĭ�ϵĹ�����"��;  
	            super.doFilter(request,res,chain); 
	        }
	    }  

}
