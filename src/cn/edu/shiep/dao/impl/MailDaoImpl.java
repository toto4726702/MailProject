package cn.edu.shiep.dao.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.springframework.stereotype.Repository;

import cn.edu.shiep.dao.IMailDao;
import cn.edu.shiep.entity.Mail;
import cn.edu.shiep.utils.SqlMapUtil;

import com.ibatis.sqlmap.client.SqlMapClient;

@Repository
public class MailDaoImpl implements IMailDao {

	@Override
	public void saveMail(Mail mail) {
		
		SqlMapClient client = SqlMapUtil.getSqlMapClient();
		try {
			client.insert("saveMail", mail);
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Mail> receiveMail(String sendTo) {
		SqlMapClient client = SqlMapUtil.getSqlMapClient();
		try {
			List<Mail> mails = client.queryForList("getMailBySendTo", "%"+sendTo+"%");
			return mails;
		} catch (SQLException e) {
			e.printStackTrace();
			return new ArrayList<Mail>();
		}		
	}
	
	@Test
	public void testSaveMail(){
		Mail mail = new Mail();
		mail.setContent("123");
		this.saveMail(mail);
	}
	
	@Test
	public void testReceiveMail(){
		String user = "emily@localhost";
		System.out.println(this.receiveMail(user));
	}

}
