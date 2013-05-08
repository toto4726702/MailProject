package cn.edu.shiep.dao;

import java.util.List;

import cn.edu.shiep.entity.Mail;

public interface IMailDao {

	public void saveMail(Mail mail);
	
	public List<Mail> receiveMail(String sendTo);
}
	
	public void updatList<Mail> getMonthlyMail(String username,String monthdateblic void updateReadstatus(String mailid);
	
	public void updateImportant(String mailid,String important);
	
	public void updateMark(String mailid,String mark);
}
