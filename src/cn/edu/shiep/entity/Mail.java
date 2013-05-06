package cn.edu.shiep.entity;

import cn.edu.shiep.utils.EncryptUtil;

public class Mail {

	private int mailid;
	private String sender;
	private String sendTo;
	private String copyTo;
	private String title;
	private String content;
	private String attachment;
	private String date;
	private String sendstatus;
	private String readstatus;
	private String lockpass;
	private String telepass;
	private String important;
	private String encrypt;
	private String mark;
	private String tags;
	private String classified;
	
	public Mail() {
	}
	
	
	
	public Mail(String sender, String sendTo, String copyTo, String title,
			String content, String attachment, String date, String sendstatus,
			String readstatus, String lockpass, String telepass,
			String important, String encrypt, String mark, String tags,
			String classified) {
		super();
		this.sender = sender;
		this.sendTo = sendTo;
		this.copyTo = copyTo;
		this.title = title;
		this.content = content;
		this.attachment = attachment;
		this.date = date;
		this.sendstatus = sendstatus;
		this.readstatus = readstatus;
		this.lockpass = lockpass;
		this.telepass = telepass;
		this.important = important;
		this.encrypt = encrypt;
		this.mark = mark;
		this.tags = tags;
		this.classified = classified;
	}



	public int getMailid() {
		return mailid;
	}
	public void setMailid(int mailid) {
		this.mailid = mailid;
	}
	public String getSender() {
		return sender;
	}
	public void setSender(String sender) {
		this.sender = sender;
	}
	public String getSendTo() {
		return sendTo;
	}
	public void setSendTo(String sendTo) {
		this.sendTo = sendTo;
	}
	public String getCopyTo() {
		return copyTo;
	}
	public void setCopyTo(String copyTo) {
		this.copyTo = copyTo;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		String c = null;
		try {
			c = EncryptUtil.decryptDES("429387498234".getBytes(), content.getBytes());
			System.out.println("解密:"+c);
		} catch (Exception e) {
			System.out.println("解密开始");
		}
		this.content = content;
	}
	public String getAttachment() {
		return attachment;
	}
	public void setAttachment(String attachment) {
		this.attachment = attachment;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getSendstatus() {
		return sendstatus;
	}
	public void setSendstatus(String sendstatus) {
		this.sendstatus = sendstatus;
	}
	public String getReadstatus() {
		return readstatus;
	}
	public void setReadstatus(String readstatus) {
		this.readstatus = readstatus;
	}
	public String getLockpass() {
		return lockpass;
	}
	public void setLockpass(String lockpass) {
		this.lockpass = lockpass;
	}
	public String getTelepass() {
		return telepass;
	}
	public void setTelepass(String telepass) {
		this.telepass = telepass;
	}
	public String getImportant() {
		return important;
	}
	public void setImportant(String important) {
		this.important = important;
	}
	public String getEncrypt() {
		return encrypt;
	}
	public void setEncrypt(String encrypt) {
		this.encrypt = encrypt;
	}
	public String getMark() {
		return mark;
	}
	public void setMark(String mark) {
		this.mark = mark;
	}
	public String getTags() {
		return tags;
	}
	public void setTags(String tags) {
		this.tags = tags;
	}
	public String getClassified() {
		return classified;
	}
	public void setClassified(String classified) {
		this.classified = classified;
	}
	
	
	
	@Override
	public String toString() {
		return "[mailid=" + mailid + ", sender=" + sender + ", sendTo="
				+ sendTo + ", copyTo=" + copyTo + ", title=" + title
				+ ", content=" + content + ", attachment=" + attachment
				+ ", date=" + date + ", sendstatus=" + sendstatus
				+ ", readstatus=" + readstatus + ", lockpass=" + lockpass
				+ ", telepass=" + telepass + ", important=" + important
				+ ", encrypt=" + encrypt + ", mark=" + mark + ", tags=" + tags
				+ ", classified=" + classified + "]";
	}



	public String toJSONString(){
		return "{'mailid':'" + mailid + "', 'sender':'" + sender + "', 'sendTo':'"
				+ sendTo + "', 'copyTo':'" + copyTo + "', 'title':'" + title
				+ "', 'content':'" + content.trim() + "', 'attachment':'" + attachment
				+ "', 'date':'" + date + "', 'sendstatus':'" + sendstatus
				+ "', 'readstatus':'" + readstatus + "', 'lockpass':'" + lockpass
				+ "', 'telepass':'" + telepass + "', 'important':'" + important
				+ "', 'encrypt':'" + encrypt + "', 'mark':'" + mark + "', 'tags':'" + tags
				+ "', 'classified':'" + classified + "'}";
	}
	
}
