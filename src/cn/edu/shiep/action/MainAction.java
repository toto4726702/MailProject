package cn.edu.shiep.action;

import java.io.UnsupportedEncodingException;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;

import cn.edu.shiep.utils.ResultType;

public class MainAction {

	@Action(value="mainAction",results={
			@Result(name=ResultType.FAILURE,location="/login.jsp",type="redirect"),
			@Result(name=ResultType.SUCCESS,location="main.jsp")})
	public String execute() throws UnsupportedEncodingException{

		System.out.println("MainAction:passed!");
	
		return ResultType.SUCCESS;
	}
}
