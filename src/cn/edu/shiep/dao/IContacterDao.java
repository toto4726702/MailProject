package cn.edu.shiep.dao;

import java.util.List;

import cn.edu.shiep.entity.Contacter;

public interface IContacterDao {

	public void saveContacter(Contacter contacter);
	
	public List<ContaContacter haveContacter(String owner,String username);
	
	public void addSendcount(String cid);
	
	public void addReceivetime(String cid);
	
	public void updateVipScore(String cidblic List<Contacter> getHotContacter(String owner,String filter);
	
}
