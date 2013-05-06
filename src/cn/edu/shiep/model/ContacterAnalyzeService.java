package cn.edu.shiep.model;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;rt org.springframework.stereotype.Service;

import cn.edu.shiep.dao.IContacterDao;
import cn.edu.shiep.entity.Contacter;

@Service("contacterAnalyzeService")
public class ContacterAnalyzeService {

	@Resource(name="contacterDao = "contacterDaoImpl")
	private IContacterDao dao;

	public List<Contacter> getHotContacter(String owner, String filter) {
		// 对filter做业务处理,按照周,月,年进行过滤
		Date date = new Date();
		// 创建(日期)格式化
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		// 创建(日历)格式化
		GregorianCalendar gc = new GregorianCalendar();
		// 格式化当前日期
		gc.setTime(date);
		
		System.out.println(sdf.format(gc.getTime()));

		if (filter.equalsIgnoreCase("all")) {
			filter = "";
		} else if (filter.equalsIgnoreCase("week")) {
			// 获得一周前的日期
			gc.add(3, -1);
			filter = sdf.format(gc.getTime());
		} else if (filter.equalsIgnoreCase("month")) {
			// 获得一个月前的日期
			gc.add(2, -1);
			filter = sdf.format(gc.getTime());
		} else if (filter.equalsIgnoreCase("year")) {
			// 获得一年前的日期
			gc.add(1, -1);
			filter = sdf.format(gc.getTime());
		}
		System.out.println("ContacterAnalyzeService:过滤字段为 " + filter);r(owner, filter);
	}

	public IContacterDao getDao() {
		return dao;
	}

	public void setDao(IContacterDao dao) {
		this.dao = dao;
	}
	
}
	@Test
	public void testDate(){
		Date date = new Date();
		  // 创建(日期)格式化
		  SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		  // 创建(日历)格式化
		  GregorianCalendar gc = new GregorianCalendar();

		  // 获得当前时间
		  System.out.println(sdf.format(date));

		  // 格式化当前日期
		  gc.setTime(date);
		  // 在当前日期上减3个月
		  gc.add(3, -1);
		  // 获得三个月后的日期
		  System.out.println(sdf.format(gc.getTime()));
	}

}
