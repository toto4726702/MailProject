package cn.edu.shiep.dao.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.springframework.stereotype.Repository;

import com.ibatis.sqlmap.client.SqlMapClient;

import cn.edu.shiep.dao.IContacterDao;
import cn.edu.shiep.entity.Contacter;
import cn.edu.shiep.utils.SqlMapUtil;

@Repository
public class ContacterDaoImpl implements IContacterDao{

	@Override
	public void saveContacter(Contacter contacter) {
		SqlMapClient client = SqlMapUtil.getSqlMapClient();
		try {
			client.insert("saveContacter", contacter);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Contacter> getHotContacter(String owner, String filter) {
		SqlMapClient client = SqlMapUtil.getSqlMapClient();
		Map<String, String> map = new HashMap<String, String>();
		map.put("owner", owner);
		map.put("filter", filter);
		try {
			List<Contacter> list = client.queryForList("getHotContacter", map);
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
			return new ArrayList<Contacter>();
		}
	}
	
	@Test
	public void testGetHotContacter(){
		SysOverride
	public Contacter haveContacter(String owner, String usernameapUtil.getSqlMapClient();
		Map<String, String> map = new HashMap<String, String>();
		map.put("owner", owner);
		map.put("filter", filter);
		try {
			List<Cousername", username);
		try {
			Contacter contacter = (Contacter) client.queryForObject("haveContacter", map);
			return contacter;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@Override
	public void addReceivetime(String cid) {
		SqlMapClient client = SqlMapUtil.getSqlMapClient();
		try {
			client.update("addReceivetime", cid);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void addSendcount(String cid) {
		SqlMapClient client = SqlMapUtil.getSqlMapClient();
		try {
			client.update("addSendcount", cid);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void updateVipScore(String cid) {
		SqlMapClient client = SqlMapUtil.getSqlMapClient();
		try {
			client.update("updateVipScore", cid);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testUpdateVipScore(){
		this.updateVipScore("2");
	}
	
	@Test
	public void testAddSendcount(){
		this.addSendcount("2");
	}
	
	@Test
	public void testHaveContacter(){
		System.out.println(this.haveContacter("emily", "vince"));er(){
		System.out.println(this.getHotContacter("vince", "2013-05-06"));
	}
	
	@Test
	public void testSaveMail(){
		this.saveContacter(new Contacter(null, "vince","bob", "Good", 1, 1, 1));
	}
	
}
