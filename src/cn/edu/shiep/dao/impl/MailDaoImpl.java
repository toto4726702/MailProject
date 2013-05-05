package cn.edu.shiep.dao.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ListHashMap;
import java.util.List;
import java.util.Mapmport org.junit.Test;
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
		Mail maOverride
	public void updateReadstatus(String mailid) {
		SqlMapClient client = SqlMapUtil.getSqlMapClient();
		try {
			client.update("updateReadstatus", mailid);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void updateImportant(String mailid, String important) {
		SqlMapClient client = SqlMapUtil.getSqlMapClient();
		Map<String, String> map = new HashMap<String, String>();
		map.put("important", important);
		map.put("mailid", mailid);
		try {
			client.update("updateImportant", map);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void updateMark(String mailid, String mark) {
		SqlMapClient client = SqlMapUtil.getSqlMapClient();
		Map<String, String> map = new HashMap<String, String>();
		map.put("mark", mark);
		map.put("mailid", mailid);
		try {
			client.update("updateMark", map);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testUpdateMark(){
		this.updateMark("15", "false");
	}
	
	@Test
	public void testUpdateReadstatus(){
		this.updateReadstatus("15");
	}
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
