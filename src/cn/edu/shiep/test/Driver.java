package cn.edu.shiep.test;


import java.security.NoSuchAlgorithmException;

import org.apache.commons.codec.digest.DigestUtils;
import org.junit.Test;

import cn.edu.shiep.algorithm.SocialMap;
import cn.edu.shiep.james.DigestUtil;

public class Driver {

	
	@Test
	public void testUserAdd(){
		//UserAdd.main(null);
	}
	
	@Test
	public void testDigest(){
		
		try {
			System.out.println(DigestUtil.digestString("123456", "SHA"));
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
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
