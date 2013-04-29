package cn.edu.shiep.mail;

import org.junit.Test;

public class Driver {

	
	@Test
	public void testUserAdd(){
		UserAdd.main(null);
	}
	
	public static void main(String[] args) throws Exception {
	
		SocialMap graph = SocialMap.analyzeDoc("good.doc");;

		graph.printGraph();
		System.out.println("����ĵ�����:" + graph.getMaxIn());
		System.out.println("���Ծ������:" + graph.getMaxOut());
		System.out.println("��ǰ���Ŷ��ܶ���:" + graph.getDensity());
		graph.getGroup();
		graph.getGroupVIP();
	}

}
