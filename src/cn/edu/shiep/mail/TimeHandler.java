package cn.edu.shiep.mail;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class TimeHandler implements InvocationHandler{

	private Object target;
	public TimeHandler(Object target) {
		super();
		this.target = target;
	}

	public Object invoke(Object proxy, Method method, Object[] args)
			throws Throwable {
		// TODO Auto-generated method stub
		long start = System.currentTimeMillis();
		System.out.println("start:"+start);
		method.invoke(target, args);
		long end = System.currentTimeMillis();
		System.out.println("use time = "+(end-start));
		return null;
	}

	

}
