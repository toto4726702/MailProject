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
		// ��filter��ҵ����,������,��,����й���
		Date date = new Date();
		// ����(����)��ʽ��
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		// ����(����)��ʽ��
		GregorianCalendar gc = new GregorianCalendar();
		// ��ʽ����ǰ����
		gc.setTime(date);
		
		System.out.println(sdf.format(gc.getTime()));

		if (filter.equalsIgnoreCase("all")) {
			filter = "";
		} else if (filter.equalsIgnoreCase("week")) {
			// ���һ��ǰ������
			gc.add(3, -1);
			filter = sdf.format(gc.getTime());
		} else if (filter.equalsIgnoreCase("month")) {
			// ���һ����ǰ������
			gc.add(2, -1);
			filter = sdf.format(gc.getTime());
		} else if (filter.equalsIgnoreCase("year")) {
			// ���һ��ǰ������
			gc.add(1, -1);
			filter = sdf.format(gc.getTime());
		}
		System.out.println("ContacterAnalyzeService:�����ֶ�Ϊ " + filter);r(owner, filter);
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
		  // ����(����)��ʽ��
		  SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		  // ����(����)��ʽ��
		  GregorianCalendar gc = new GregorianCalendar();

		  // ��õ�ǰʱ��
		  System.out.println(sdf.format(date));

		  // ��ʽ����ǰ����
		  gc.setTime(date);
		  // �ڵ�ǰ�����ϼ�3����
		  gc.add(3, -1);
		  // ��������º������
		  System.out.println(sdf.format(gc.getTime()));
	}

}
