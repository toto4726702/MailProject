package cn.edu.shiep.utils;

import java.io.IOException;
import java.io.Reader;

import com.ibatis.common.resources.Resources;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.client.SqlMapClientBuilder;

public class SqlMapUtil {

	private static SqlMapClient sqlMapClient; 
	
	static{
		
		try {
			Reader reader = Resources.getResourceAsReader("sqlMapConfig.xml");
			sqlMapClient = SqlMapClientBuilder.buildSqlMapClient(reader);
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public static SqlMapClient getSqlMapClient(){
		return sqlMapClient;
	}

}
