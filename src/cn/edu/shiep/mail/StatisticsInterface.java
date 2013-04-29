package cn.edu.shiep.mail;

import java.io.IOException;
import java.util.List;

public interface StatisticsInterface {

	public void statisticsResult() throws IOException;
	public List<String> getUsers();
	public List<List<Integer>> getData();
}
