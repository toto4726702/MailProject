package cn.edu.shiep.utils;

import javax.xml.ws.Endpoint;

public class DeployUtils {

	public static void deployService(String address, Object service) {
        System.out.println("Server start бнбн");
        Endpoint.publish(address, service);
    }
}
