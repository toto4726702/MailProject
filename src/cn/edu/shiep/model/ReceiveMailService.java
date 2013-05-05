package cn.edu.shiep.model;

import java.util.List;
import java.util.Properties;

import javax.annotation.Resource;
import javax.mail.FetchProfile;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Store;

import org.springframework.stereotype.Service;

import cn.edu.shiep.dao.IMailDao;
import cn.edu.shiep.entity.Mail;
import cn.edu.shiep.mail.POP3Auth;
import cn.edu.shiep.utils.MailProperty;

@Service("receiveMailService")
public class ReceiveMailService {

	@Resource(name="mailPropertyBean")
	private MailProperty mailProperty;
	@Resource(name="mailDaoImpl")
	private IMailDao mailDao;
	
	private Session mailSession;
	private Store mailStore;
	private Folder folder;
	
	//连接邮件服务器
	public void connect(String user,String password) throws Exception{
		
		POP3Auth auth = new POP3Auth();
		auth.setAccount(user, password);
		
		Properties prop = new Properties();
		
		prop.put("mail.pop3.host", mailProperty.getPop3Host());
		
		mailSession  =Session.getDefaultInstance(prop, auth);
		
		mailStore = mailSession.getStore("pop3");
		mailStore.connect(mailProperty.getPop3Host(), user, password);
		
		folder = mailStore.getDefaultFolder().getFolder("INBOX");
		folder.open(Folder.READ_ONLY);
	}
		
	
	
	//从james获得个人所有邮件
	public Message[] receiveAllMail(String user,String pass) throws Exception{
		this.connect(user,pass);
		
		Message[] msg = folder.getMessages();
		FetchProfile profile = new FetchProfile();
		profile.add(FetchProfile.Item.ENVELOPE);
		folder.fetch(msg, profile);
		
		return msg;
	}
	
	//从数据库表获得个人所有邮件
	public List<Mail> getDBMail(String sendTo){
		List<Mail> list =  mailDao.receiveMail(sendTo);
		return list;
	}
	
}
	public void updateReadstatus(String mailid){
		mailDao.updateReadstatus(mailid);
	}
	
	public void updateImportant(String mailid,String important){
		mailDao.updateImportant(mailid, important);
	}
	
	public void updateMark(String mailid,String mark){
		mailDao.updateMark(mailid, mark);
	}
	
	public void setMailProperty(MailProperty mailProperty) {
		this.mailProperty = mailProperty;
	}
	
	public void setMailDao(IMailDao mailDao) {
		this.mailDao = mailDao;
	}
}
