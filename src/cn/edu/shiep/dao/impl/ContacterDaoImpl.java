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
		System.out.println(this.getHotContacter("vince", "2013-05-06"));
	}
	
	@Test
	public void testSaveMail(){
		this.saveContacter(new Contacter(null, "vince","bob", "Good", 1, 1, 1));
	}
	
}
