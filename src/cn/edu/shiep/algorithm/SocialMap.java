package cn.edu.shiep.algorithm;


import java.io.FileReader;
import java.util.Stack;

/**
 * 挖掘人际联系网络中的信息
 * @author 陈志成
 *
 */
public class SocialMap {
	
	private String[] vertex;	//人员名称数组
	private int[][] costMatrix;	//耗费邻接矩阵
	private int[][] matrix;	//邻接矩阵
	private int pointNum;	//网络中的总人数
	private int[] record;	
	
	private static FileReader reader;	//文件读取器

	private SocialMap(int n, int[][] martrix,int[][] costMartrix, String[] names) { // 图的构造函数，输入点的个数

		pointNum = n; // 节点个数
		costMatrix = costMartrix;	//耗费邻接矩阵
		matrix = martrix; // 根据节点个数构造出的邻接矩阵
		vertex = names; // 节点数组

		record = new int[pointNum]; // 记录路线
	}
	
	/**
	 * 主要方法，将一份特定格式的文档转化为内存中的对象
	 * @param doc
	 * @return
	 * @throws Exception
	 */
	public static SocialMap analyzeDoc(String docPath) throws Exception{
		
			// 初始化一些准备变量
			int personNum;	//人数
			String[] rowData;	//邻接表数据
			String[] rowName;
			
			// 读取文件
			StringBuffer str = new StringBuffer();	
			char[] buffer = new char[1024];
			int readChar = 0;
			reader = new FileReader(docPath);
			while ((readChar = reader.read(buffer, 0, 1024)) > 0) {
				str.append(buffer, 0, readChar);
			}

			//分析获取的数据
			rowData = str.toString().split(";");
			rowName = rowData[0].split(",");
			personNum = rowName.length;	

			//提取剩余的行数做为邻接矩阵参数
			String[][] cellData = new String[personNum][personNum];
			int[][] intCellData = new int[personNum][personNum];
			int[][] intCostCellData = new int[personNum][personNum];
			int density = getAverage(personNum,rowData);

			//遍历数据内容，并将邻接表转化为数组
			for (int i = 1; i <= personNum; i++) {
				cellData[i - 1] = rowData[i].split(",");
				for (int j = 0; j < personNum; j++) { // 转换为整型
					int val  = Integer.valueOf(cellData[i - 1][j]);
					intCostCellData[i - 1][j] = val;
					if(val>density){
						intCellData[i - 1][j] = 1;
					}else{
						intCellData[i - 1][j] = 0;
					}
				}
			}
	
			return new SocialMap(personNum, intCellData,intCostCellData, rowName);
	}

	/**
	 * 取得每个人发送邮件的平均数
	 * @param personNum
	 * @param rows
	 * @return	邮件平均数
	 */
	private static int getAverage(int personNum,String[] rows){
		String[][] values = new String[personNum][personNum];
		int intSum = 0;
		
		for (int i = 1; i <= personNum; i++) {
			values[i - 1] = rows[i].split(",");
			for (int j = 0; j < personNum; j++) { // 转换为整型
				int val  = Integer.valueOf(values[i - 1][j]);	
				intSum+=val;
			}
		}
		
		intSum = intSum/(personNum*(personNum-1));
		return intSum;
	}
	
	/**
	 * 输出图的信息
	 */
	public void printGraph() { 
		for (int i = 0; i < pointNum; i++) {
			for (int j = 0; j < pointNum; j++) {
				System.out.print(matrix[i][j] + " ");
			}
			System.out.println();
		}
	}
	
	/**
	 * 获得图结点中入度最大的结点，并返回结点的名称，此人为最核心的成员
	 * @return	结点编号，-1为未找到
	 */
	public String getMaxIn() {
		int count = -1; // 记录行中最大的出度
		int name = -1; // 节点编号
		for (int j = 0; j < pointNum; j++) {
			int summon = 0;
			for (int i = 0; i < pointNum; i++) {
				summon += costMatrix[i][j];
			}
			if (summon > count) {
				count = summon;
				name = j;
			}
		}
		if(count==0){
			return "联系图可供分析数据不足...";
		}
		System.out.println(count);
		return vertex[name];
	}

	/**
	 * 获得图结点中出度最大的结点，并返回结点的名称，此人为最活跃的成员
	 * @return
	 */
	public String getMaxOut() { 
		int count = -1;
		int name = -1;
		for (int i = 0; i < pointNum; i++) {
			int summon = 0;
			for (int j = 0; j < pointNum; j++) {
				summon += costMatrix[i][j];
			}
			if (summon > count) {
				count = summon;
				name = i;
			}
		}
		if(count==0){
			return "联系图可供分析数据不足...";
		}
		System.out.println(count);
		return vertex[name];
	}

	/**
	 * 计算图中的联系密度，判断整体联系的紧密性
	 * @return
	 */
	public double getDensity() { 
		double summon = 0;
		for (int i = 0; i < pointNum; i++) {
			for (int j = 0; j < pointNum; j++) {
				summon += matrix[i][j]; // 将所有的边数相加
			}
		}
		summon = summon / ((pointNum - 1) * pointNum); // 所有边除以最大边数得到密度
		return (summon);
	}

	/**
	 *  返回连接两个子图的重要结点，此人为团队间联系桥接的核心人物
	 */
	public void getGroupVIP() { 

		int flag = -1;
		for (int i = 0; i < pointNum; i++) {
			if (flag < record[i]) {
				flag = record[i];
			}
		}

		for (int i = 0; i < pointNum; i++) {
			System.out.print(vertex[i] + "的团队交流活跃点数为:" + record[i]);
			if (record[i] == flag && record[i] != 0) {
				System.out.print("          团队桥接人物");
			} else if (record[i] == 1) {
				System.out.print("          较为孤立,需要注意");
			} else if (record[i] == 0) {
				System.out.print("          孤立人物，危险！");
			}
			System.out.println();
		}
		// cout<<"注:团队交流活跃点数取决于与其有交流之团队的数量之和。"<<endl;
	}

	//-------------------遍历法--------------------
	/**
	 * 启动有向图的深度优先遍历（遍历到一些孤立的结点）
	 * @param visited
	 */
	public void diTraverse(int visited[]) { 
		for (int i = 0; i < pointNum; i++) {
			if (visited[i] == 0) {
				depthTraverse(i, visited);
			}
		}
	}

	/**
	 * 有向图的深度优先遍历
	 * @param ver  所处节点编号
	 * @param visited  访问数组
	 */
	private void depthTraverse(int ver, int visited[]) { 
		//越界处理
		if (ver > pointNum && ver < 0) {
			return;
		}
		//记录到达的位置
		visited[ver] = 1;
		//寻找下一个可以到达的节点，注意到达的节点必须是还没有访问过的
		for (int i = 0; i < pointNum; i++) {
			if (matrix[ver][i] == 1 && visited[i] == 0) {
				depthTraverse(i, visited);
			}
		}
	}
	
	/**
	 * 读取图中的联系网络形成的环，这种环一般代表一群关系比较密切的团队，可以作为团队培养
	 */
	public void getGroup() { 
		int step; // 记录环的长度
		int visited[] = new int[pointNum];	//记录访问到的节点
		
		//初始化record
		for (int i = 0; i < pointNum; i++) {
			record[i] = 0;
		}
		
		//
		for (int i = 0; i < pointNum; i++) {
			Stack<Integer> loop = new Stack<Integer>(); // 初始化堆栈
			step = 0; // 初始化长度
			for (int j = 0; j < pointNum; j++) { // 初始化visited数组
				visited[j] = 0;
			}
			getCircle(i, visited, step, loop, i);
		}
	}

	/**
	 * 取系统中所有的环
	 * @param ver
	 * @param visited
	 * @param step
	 * @param loop
	 * @param start
	 */
	private void getCircle(int ver, int visited[], int step,
			Stack<Integer> loop, int start) { 
		if (ver > pointNum && ver < 0) {
			return;
		}
		// 前置部分****************************************
		loop.push(ver);
		step++;
		visited[ver] = 1;
		// **********************************************
		for (int i = 0; i < pointNum; i++) {
			//如果下一个节点是已经访问过，并且是出发点的话，环形成
			if (matrix[ver][i] == 1 && visited[i] == 1 && i == start) {
				int flag = 0; // 设置标志位，如果记录中有未被访问的点，则忽略
				for (int j = 0; j < step; j++) { // 从步骤为0开始审查
					if (record[loop.get(j)] == 0) {
						flag = 1;
						break;
					}
				}
				if (flag == 1) {
					System.out.println("小团体人数:" + step);
					// cout<<"小团体的人数是:"<<step<<endl;
					// cout<<"现在输出小团体:"<<endl;
					for (int j = 0; j < step; j++) {
						int temp = loop.get(j);
						++record[temp];
						System.out.println("第" + (j + 1) + "位是:" + vertex[temp]
								+ " ");
						// cout<<"第"<<i+1<<"位是:"<<Vertex[temp]<<" ";
					}
					System.out.println();
					// cout<<endl;
				}
			}
			
			//果下一个节点是还没有被访问过，继续遍历
			if (matrix[ver][i] == 1 && visited[i] == 0) {
				getCircle(i, visited, step, loop, start);
			}
		}
		// 后置部分****************************************
		step--;
		loop.pop();
		// **********************************************
	}

}
