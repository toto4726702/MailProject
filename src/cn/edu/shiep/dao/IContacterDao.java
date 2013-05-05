package cn.edu.shiep.dao;

import java.util.List;

import cn.edu.shiep.entity.Contacter;

public interface IContacterDao {

	public void saveContacter(Contacter contacter);
	
	public List<Contacter> getHotContacter(String owner,String filter);
	
}
