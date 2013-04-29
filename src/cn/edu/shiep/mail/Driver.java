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
		System.out.println("最核心的人是:" + graph.getMaxIn());
		System.out.println("最活跃的人是:" + graph.getMaxOut());
		System.out.println("当前的团队密度是:" + graph.getDensity());
		graph.getGroup();
		graph.getGroupVIP();
	}

}
