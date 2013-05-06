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
	
	//�����ʼ�
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
			System.out.println("SendMailService:James���ͳ�������");
			return false;
		}
		
	}
	
	public void sendDBMail(Map<String, String> props){
		//������
		String encryptMethod = props.get("encryptMethod");
		String content = props.get("content");
		if(encryptMethod.equals("DES")){
			System.out.println("SendMailService:����DES����");
			try {
				content = new String(EncryptUtil.encryptDES("429387498234".getBytes(), content));
				System.out.println(content);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if(encryptMethod.equals("AES")){
			System.out.println("SendMailService:����AES����");
			content = new String(EncryptUtil.encryptAES(content, "123456"));
			System.out.println(content);
		}
		
		//�����ʼ�
		Date now = new Date();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Mail mail = new Mail(props.get("sender"),props.get("sendTo"), props.get("copyTo"), props.get("title"),
							 props.get("content"), "", format.format(now), "send", "unread", props.get("passwd"), 
							 props.get("telepass"), props.get("importantMail"), props.get("encryptMethod"), "false", null, null);
		
		mailDao.saveMail(mail);
		
		//��ϵ�˲���
		//���Ȳ�ѯ�Ƿ����(���ڻ�ֻ��֧�ֵ�����)
		Contacter contacter1 = contacterDao.haveContacter(props.get("sender"), props.get("sendTo").split("@")[0]);
		Contacter contacter2 = contacterDao.haveContacter(props.get("sendTo").split("@")[0],props.get("sender"));
		if(contacter1==null){
			Contacter newCon1 = new Contacter(null, props.get("sender"), props.get("sendTo").split("@")[0], "", 1, 0, 1.5f);
			Contacter newCon2 = new Contacter(null, props.get("sendTo").split("@")[0], props.get("sender"), "", 0, 1, 0.5f);
			contacterDao.saveContacter(newCon1);
			contacterDao.saveContacter(newCon2);
			System.out.println("SendMailService:�����ϵ��"+newCon1);
			System.out.println("SendMailService:��ӱ���ϵ��"+newCon2);
		}else{
			contacterDao.addSendcount(contacter1.getCid());
			contacterDao.addReceivetime(contacter2.getCid());
			//����˫����vip����
			contacterDao.updateVipScore(contacter1.getCid());
			contacterDao.updateVipScore(contacter2.getCid());
			System.out.println("SendMailService:��ϵ�����Ӽļ�+1 "+contacter1.getUsername());
			System.out.println("SendMailService:��ϵ�����Ӽļ�+1 "+contacter2.getUsername());
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
