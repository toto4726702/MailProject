package cn.edu.shiep.webservice;

import javax.jws.WebParam;

import cn.edu.shiep.entity.User;

public class UserService implements IUserService {

	@Override
	public User getUserByName(@WebParam(name = "name") String name) {
		
		User user = new User();
		user.setId(1);
		user.setName(name);
		user.setEmail("toto4726702@yahoo.com.cn");
		user.setAddress("Mars");
		
		return user;
	}

	@Override
	public void setUser(User user) {
		System.out.println(user.toString());
	}



}
