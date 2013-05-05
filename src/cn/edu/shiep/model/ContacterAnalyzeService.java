package cn.edu.shiep.model;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.edu.shiep.dao.IContacterDao;
import cn.edu.shiep.entity.Contacter;

@Service("contacterAnalyzeService")
public class ContacterAnalyzeService {

	@Resource(name="contacterDaoImpl")
	private IContacterDao dao;
	
	public List<Contacter> getHotContacter(String owner,String filter){
		//对filter做业务处理,按照周,月,年进行过滤
		if(filter.equalsIgnoreCase("all")){
			filter = "";
		}else if(filter.equalsIgnoreCase("week")){
			//filter = 
		}else if(filter.equalsIgnoreCase("month")){
			
		}else if(filter.equalsIgnoreCase("year")){
			
		}
		
		return dao.getHotContacter(owner, filter);
	}

	public IContacterDao getDao() {
		return dao;
	}

	public void setDao(IContacterDao dao) {
		this.dao = dao;
	}
	
}
