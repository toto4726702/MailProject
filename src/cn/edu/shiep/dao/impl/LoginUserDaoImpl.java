package cn.edu.shiep.dao.impl;

import java.sql.SQLException;

import org.junit.Test;
import org.springframework.stereotype.Repository;


import cn.edu.shiep.dao.ILoginUserDao;
import cn.edu.shiep.entity.LoginUser;
import cn.edu.shiep.utils.SqlMapUtil;

import com.ibatis.sqlmap.client.SqlMapClient;

@Repository
public class LoginUserDaoImpl implements ILoginUserDao {

	@Override
	public LoginUser getUserByName(String name) {
		SqlMapClient client = SqlMapUtil.getSqlMapClient();
		LoginUser user = null;
		try {
			user = (LoginUser) client.queryForObject("getUserByName", name);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user;
	}
	
	@Test
	public void testGetUserByName(){
		LoginUser user = this.getUserByName("admin");
		System.out.println(user.getPwdHash());
	}


}
