package cn.edu.shiep.model;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.Properties;

import javax.annotation.Resource;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.stereotype.Service;

import cn.edu.shiep.dao.IMailDao;
import cn.edu.shiep.entity.Mail;
import cn.edu.shiep.mail.SmtpAuth;
import cn.edu.shiep.utils.MailProperty;

@Service("sendMailService")
public class SendMailService {
	
	@Resource(name="mailPropertyBean")
	private MailProperty mailProperty;
	@Resource(name="mailDaoImpl")
	private IMailDao mailDao;
	
	//·¢ËÍÓÊ¼þ
	public boolean sendJamesMail(String user, String password,Map<String,String> map){
		
		Properties props = new Properties();
		props.put("mail.smtp.host", mailProperty.getSmtpHost());
		props.put("mail.smtp.auth",mailProperty.getSmtpAuth());
		
		try{
			SmtpAuth auth = new SmtpAuth();
			auth.setUserInfo(user, password);
			
			Session mailSession = Session.getDefaultInstance(props,auth);
			
			mailSession.setDebug(true);
			
			Message message = new MimeMessage(mailSession);
			
			message.setFrom(new InternetAddress(map.get("sender")));
			
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(map.get("sendTo")));
			
			message.setSubject(map.get("title"));
			message.setText(map.get("content"));
			
			message.setSentDate(new Date());
			
			
			message.setHeader("X-Priority", "1");
			
			message.saveChanges();
			
			Transport transport = mailSession.getTransport("smtp");
			
			
			transport.connect(mailProperty.getSmtpHost(), user,password);
			transport.sendMessage(message, message.getAllRecipients());
			transport.close();
		
			return true;
		}catch(Exception x){
			x.printStackTrace();
			return false;
		}
		
	}
	
	public void sendDBMail(Map<String, String> props){
		Date now = new Date();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Mail mail = new Mail(props.get("sender"),props.get("sendTo"), props.get("copyTo"), props.get("title"),
							 props.get("content"), "", format.format(now), "send", "unread", props.get("passwd"), 
							 props.get("telepass"), props.get("importantMail"), props.get("encryptMethod"), null, null, null);
		
		mailDao.saveMail(mail);
		
		
	}

	public MailProperty getMailProperty() {
		return mailProperty;
	}

	public void setMailProperty(MailProperty mailProperty) {
		this.mailProperty = mailProperty;
	}

	public IMailDao getMailDao() {
		return mailDao;
	}

	public void setMailDao(IMailDao mailDao) {
		this.mailDao = mailDao;
	}
	
	
	
}
