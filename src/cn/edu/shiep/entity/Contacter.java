package cn.edu.shiep.entity;

public class Contacter {

	private String cid;
	private String owner;
	private String username;
	private String aliasname;
	private int sendcount;
	private int receivetime;
	private int vipscofloa;
	
	public Contacter() {
	}
	
	public Contacter(String cid, String owner, String username,
			String aliasname, int sendcount, int receivetime, int vipscore) {
floasuper();
		this.cid = cid;
		this.owner = owner;
		this.username = username;
		this.aliasname = aliasname;
		this.sendcount = sendcount;
		this.receivetime = receivetime;
		this.vipscore = vipscore;
	}

	public String getOwner() {
		return owner;
	}
	public void setOwner(String owner) {
		this.owner = owner;
	}
	public String getCid() {
		return cid;
	}
	public void setCid(String cid) {
		this.cid = cid;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getAliasname() {
		return aliasname;
	}
	public void setAliasname(String aliasname) {
		this.aliasname = aliasname;
	}
	public int getSendcount() {
		return sendcount;
	}
	public void setSendcount(int sendcount) {
		this.sendcount = sendcount;
	}
	public int getReceivetime() {
		return receivetime;
	}
	public void setReceivetime(int receivetime) {
		this.receivetime = receivetime;
	}
	public int getVipscore() {
		return vipscore;
	}
	public void setVipscfloat getVipscore() {
		return vipscore;
	}
	public void setVipscore(floatring toString() {
		return "Contacter [cid=" + cid + ", owner=" + owner + ", username="
				+ username + ", aliasname=" + aliasname + ", sendcount="
				+ sendcount + ", receivetime=" + receivetime + ", vipscore="
				+ vipscore + "]";
	}
	
	public String toJSONString() {
		return "{'cid':'" + cid + "', 'owner':'" + owner + "', 'username':'"
				+ username + "', 'aliasname':'" + aliasname + "', 'sendcount':'"
				+ sendcount + "', 'receivetime':'" + receivetime + "', 'vipscore':'"
				+ vipscore + "'}";
	}
	
}
