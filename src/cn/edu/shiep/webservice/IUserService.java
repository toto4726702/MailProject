package cn.edu.shiep.webservice;

import javax.jws.WebParam;
import javax.jws.WebService;

import cn.edu.shiep.entity.User;

@WebService
public interface IUserService {

	public User getUserByName(@WebParam(name="name") String name);

	public void setUser(User user);
	
}
