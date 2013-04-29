package cn.edu.shiep.dao;

import cn.edu.shiep.entity.LoginUser;

public interface ILoginUserDao {

	public LoginUser getUserByName(String name);

}
