package cn.edu.shiep.model;

import java.text.ParseException;import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;Iteratomport java.util.List;

import javax.annotation.Resource;

import org.junit.Test;rt org.springframework.stereotype.Service;

import cn.edu.shiep.dao.IContacterDao;
import cn.edu.shiep.entity.Contacter;
dao.IMailDao;
import cn.edu.shiep.entity.Contacter;
import cn.edu.shiep.entity.MailcterAnalyzeService")
public class ContacterAnalyzeService {

	@Resource(name="contacterDao = "contacterDaoImpl")
	private IContacterDao dao;

	public Li	@Resource(name = "mailDaoImpl")
	private IMailDao mailDao;

	public String getSocialGraph(){
		List<Contacter> contacters =  dao.getAll();
		Iterator<Contacter> i = contacters.iterator();
		StringBuffer data = new StringBuffer();
		
		//装载数据
		data.append("{'contacters':[");
		while(i.hasNext()){
			Contacter contacter = i.next();
			data.append(contacter.toJSONString());
			if(i.hasNext()){
				data.append(",");
			}
		}
		data.append("]}");
		System.out.println("SocialGraph:"+data.toString());
		return data.toString();
	}
	
	public String getContactVipData(String username){
		//dao = new ContacterDaoImpl();
		List<Contacter> contacters = dao.getAllContacter(username);
		int length = contacters.size();
		
		//System.out.println(percents);
		StringBuffer sb = new StringBuffer();
		sb.append("[");
		for(int k=0;k<length;k++){
			sb.append("{'label':'"+contacters.get(k).getUsername()+"',");
			sb.append("'value':"+contacters.get(k).getVipscore()+"}");
			if(k!=6){
				sb.append(",");
			}
		}
		sb.append("]");
		System.out.println(sb.toString());
		return sb.toString();
	}
	
	public String getContactReceiveData(String username){
		//dao = new ContacterDaoImpl();
		List<Contacter> contacters = dao.getAllContacter(username);
		int length = contacters.size();
		double total = 0;
		int n = 0;
		double[] percents = new double[length];
		
		Iterator<Contacter> i = contacters.iterator();
		while(i.hasNext()){
			Contacter contacter = i.next();
			int rtime = contacter.getReceivetime();
			total = total+rtime;
			percents[n] = rtime;
			n++;
		}
		for(int k=0;k<length;k++){
			percents[k] = (percents[k]/total)*100;
		}
		//System.out.println(percents);
		StringBuffer sb = new StringBuffer();
		sb.append("[");
		for(int k=0;k<length;k++){
			sb.append("{'label':'"+contacters.get(k).getUsername()+"',");
			sb.append("'value':"+percents[k]+"}");
			if(k!=6){
				sb.append(",");
			}
		}
		sb.append("]");
		System.out.println(sb.toString());
		return sb.toString();
	}
	
	public String getMonthlyDayData(String username) throws ParseException{
		String monthdate;
		//取得一个月前的日期= new Date();
		// 创建(日期)格式化
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		// 创建(日 HH:mm:ss历)格式化
		GregorianCalendar gc = new GregorianCalendar();
		// 格式化当前日期
		gc.setTime(date);
		
		System.out.printlngc.add(2, -1);
		monthdate = sdf.format(gc.getTime());
		// 调用方法取得邮件
		//mailDao = new MailDaoImpl();
		List<Mail> mails = mailDao.getMonthlyMail(username, monthdate);
		
		int[] periods = {0,0,0,0,0,0,0};
		String[] periodString = {"早晨","上午工作","午餐","下午工作","晚餐","夜深","午夜"};
		
		Iterator<Mail> i = mails.iterator();
		while(i.hasNext()){
			Mail mail = i.next();
			gc.setTime(sdf.parse(mail.getDate()));
			int hour = gc.get(GregorianCalendar.HOUR_OF_DAY);
			if(hour>=6 && hour<9){
				periods[0]++;
			}else if(hour>=9 && hour<12){
				periods[1]++;
			}else if(hour>=12 && hour<13){
				periods[2]++;
			}else if(hour>=13 && hour<18){
				periods[3]++;
			}else if(hour>=18 && hour<20){
				periods[4]++;
			}else if(hour>=20 && hour<24){
				periods[5]++;
			}else if(hour>=0 && hour<6){
				periods[6]++;
			}
		}
		StringBuffer sb = new StringBuffer();
		sb.append("[");
		for(int k=0;k<7;k++){
			sb.append("{'label':'"+periodString[k]+"',");
			sb.append("'value':"+periods[k]+"}");
			if(k!=6){
				sb.append(",");
			}
		}
		sb.append("]");
		System.out.println(sb.toString());
		return sb.toString();
	}
	
	public String getMonthlyData(String username) throws ParseException{
		String monthdate;
		//取得一个月前的日期= new Date();
		// 创建(日期)格式化
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		// 创建(日 HH:mm:ss历)格式化
		GregorianCalendar gc = new GregorianCalendar();
		// 格式化当前日期
		gc.setTime(date);
		
		System.out.printlngc.add(2, -1);
		monthdate = sdf.format(gc.getTime());
		// 调用方法取得邮件
		//mailDao = new MailDaoImpl();
		List<Mail> mails = mailDao.getMonthlyMail(username, monthdate);
		
		//将邮件按星期几归类并解析为JSON
		int[] weekdays = {0,0,0,0,0,0,0};
		String[] weekdaysString = {"周一","周二","周三","周四","周五","周六","周日"};
		Iterator<Mail> i = mails.iterator();
		while(i.hasNext()){
			Mail mail = i.next();
			gc.setTime(sdf.parse(mail.getDate()));
			weekdays[gc.get(GregorianCalendar.DAY_OF_WEEK)]++;
		}
		System.out.println(weekdays);  //顺序 周六，周日，周一...
		
		int count = 0;
		StringBuffer sb = new StringBuffer();
		sb.append("[");
		for(int k=2;count<7;k=(k+1)%7){
			sb.append("{'label':'"+weekdaysString[count]+"',");
			sb.append("'value':"+weekdays[k]+"}");
			if(count!=6){
				sb.append(",");
			}
			count++;
		}
		sb.append("]");
		//System.out.println(sb.toString());
		return sb.toString();
	}
	st<Contacter> getHotContacter(String owner, String filter) {
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
		  // 创建(日期)格蕄ublic void setMailDao(IMailDao mailDao) {
		this.mailDao = mailDao;
	}
	
	@Test
	public void testGetContactVipData(){
		this.getContactVipData("vince");
	}
	
	@Test
	public void testGetContactReceiveData(){
		this.getContactReceiveData("vince");
	}
	
	@Test
	public void testMonthlyDayData(){
		try {
			this.getMonthlyDayData("emily");
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testMonthlyData(){
		try {
			this.getMonthlyData("emily");
		} catch (ParseException e) {
			e.printStackTrace();
		}�(日期)格式化
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
