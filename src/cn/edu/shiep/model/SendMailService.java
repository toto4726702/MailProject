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

import cn.edu.shiep.dao.IContacterDao;
import cn.edu.shiep.dao.IMailDao;
import cn.edu.shiep.entity.Contacter;
import cn.edu.shiep.entity.Mail;
import cn.edu.shiep.mail.SmtpAuth;
import cn.edu.shiep.utils.EncryptUtil;
import cn.edu.shiep.utils.MailProperty;

@Service("sendMailService")
public class SendMailService {
	
	@Resource(name="mailPropertyBean")
	private MailProperty mailProperty;
	@Resource(name="mailDaoImpl")
	private IMailDao mailDao;
	@Resource(name="contacterDaoImpl")
	private IContacterDao contacterDao;
	
	//发送邮件
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
			System.out.println("SendMailService:James发送出现问题");
			return false;
		}
		
	}
	
	public void sendDBMail(Map<String, String> props){
		//检查加密
		String encryptMethod = props.get("encryptMethod");
		String content = props.get("content");
		if(encryptMethod.equals("DES")){
			System.out.println("SendMailService:采用DES加密");
			try {
				content = new String(EncryptUtil.encryptDES("429387498234".getBytes(), content));
				System.out.println(content);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if(encryptMethod.equals("AES")){
			System.out.println("SendMailService:采用AES加密");
			content = new String(EncryptUtil.encryptAES(content, "123456"));
			System.out.println(content);
		}
		
		//发送邮件
		Date now = new Date();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Mail mail = new Mail(props.get("sender"),props.get("sendTo"), props.get("copyTo"), props.get("title"),
							 props.get("content"), "", format.format(now), "send", "unread", props.get("passwd"), 
							 props.get("telepass"), props.get("importantMail"), props.get("encryptMethod"), "false", null, null);
		
		mailDao.saveMail(mail);
		
		//联系人操作
		//首先查询是否存在(现在还只能支持单个人)
		Contacter contacter1 = contacterDao.haveContacter(props.get("sender"), props.get("sendTo").split("@")[0]);
		Contacter contacter2 = contacterDao.haveContacter(props.get("sendTo").split("@")[0],props.get("sender"));
		if(contacter1==null){
			Contacter newCon1 = new Contacter(null, props.get("sender"), props.get("sendTo").split("@")[0], "", 1, 0, 1.5f);
			Contacter newCon2 = new Contacter(null, props.get("sendTo").split("@")[0], props.get("sender"), "", 0, 1, 0.5f);
			contacterDao.saveContacter(newCon1);
			contacterDao.saveContacter(newCon2);
			System.out.println("SendMailService:添加联系人"+newCon1);
			System.out.println("SendMailService:添加被联系人"+newCon2);
		}else{
			contacterDao.addSendcount(contacter1.getCid());
			contacterDao.addReceivetime(contacter2.getCid());
			//更新双方的vip分数
			contacterDao.updateVipScore(contacter1.getCid());
			contacterDao.updateVipScore(contacter2.getCid());
			System.out.println("SendMailService:联系人增加寄件+1 "+contacter1.getUsername());
			System.out.println("SendMailService:联系人增加寄件+1 "+contacter2.getUsername());
		}
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

	public IContacterDao getContacterDao() {
		return contacterDao;
	}

	public void setContacterDao(IContacterDao contacterDao) {
		this.contacterDao = contacterDao;
	}
	
	
	
}
