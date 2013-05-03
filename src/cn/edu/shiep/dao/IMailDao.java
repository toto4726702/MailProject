package cn.edu.shiep.dao;

import java.util.List;

import cn.edu.shiep.entity.Mail;

public interface IMailDao {

	public void saveMail(Mail mail);
	
	public List<Mail> receiveMail(String sendTo);
}
