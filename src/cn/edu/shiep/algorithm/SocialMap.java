package cn.edu.shiep.algorithm;


import java.io.FileReader;
import java.util.Stack;

/**
 * �ھ��˼���ϵ�����е���Ϣ
 * @author ��־��
 *
 */
public class SocialMap {
	
	private String[] vertex;	//��Ա��������
	private int[][] costMatrix;	//�ķ��ڽӾ���
	private int[][] matrix;	//�ڽӾ���
	private int pointNum;	//�����е�������
	private int[] record;	
	
	private static FileReader reader;	//�ļ���ȡ��

	private SocialMap(int n, int[][] martrix,int[][] costMartrix, String[] names) { // ͼ�Ĺ��캯���������ĸ���

		pointNum = n; // �ڵ����
		costMatrix = costMartrix;	//�ķ��ڽӾ���
		matrix = martrix; // ���ݽڵ������������ڽӾ���
		vertex = names; // �ڵ�����

		record = new int[pointNum]; // ��¼·��
	}
	
	/**
	 * ��Ҫ��������һ���ض���ʽ���ĵ�ת��Ϊ�ڴ��еĶ���
	 * @param doc
	 * @return
	 * @throws Exception
	 */
	public static SocialMap analyzeDoc(String docPath) throws Exception{
		
			// ��ʼ��һЩ׼������
			int personNum;	//����
			String[] rowData;	//�ڽӱ�����
			String[] rowName;
			
			// ��ȡ�ļ�
			StringBuffer str = new StringBuffer();	
			char[] buffer = new char[1024];
			int readChar = 0;
			reader = new FileReader(docPath);
			while ((readChar = reader.read(buffer, 0, 1024)) > 0) {
				str.append(buffer, 0, readChar);
			}

			//������ȡ������
			rowData = str.toString().split(";");
			rowName = rowData[0].split(",");
			personNum = rowName.length;	

			//��ȡʣ���������Ϊ�ڽӾ������
			String[][] cellData = new String[personNum][personNum];
			int[][] intCellData = new int[personNum][personNum];
			int[][] intCostCellData = new int[personNum][personNum];
			int density = getAverage(personNum,rowData);

			//�����������ݣ������ڽӱ�ת��Ϊ����
			for (int i = 1; i <= personNum; i++) {
				cellData[i - 1] = rowData[i].split(",");
				for (int j = 0; j < personNum; j++) { // ת��Ϊ����
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
	 * ȡ��ÿ���˷����ʼ���ƽ����
	 * @param personNum
	 * @param rows
	 * @return	�ʼ�ƽ����
	 */
	private static int getAverage(int personNum,String[] rows){
		String[][] values = new String[personNum][personNum];
		int intSum = 0;
		
		for (int i = 1; i <= personNum; i++) {
			values[i - 1] = rows[i].split(",");
			for (int j = 0; j < personNum; j++) { // ת��Ϊ����
				int val  = Integer.valueOf(values[i - 1][j]);	
				intSum+=val;
			}
		}
		
		intSum = intSum/(personNum*(personNum-1));
		return intSum;
	}
	
	/**
	 * ���ͼ����Ϣ
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
	 * ���ͼ�����������Ľ�㣬�����ؽ������ƣ�����Ϊ����ĵĳ�Ա
	 * @return	����ţ�-1Ϊδ�ҵ�
	 */
	public String getMaxIn() {
		int count = -1; // ��¼�������ĳ���
		int name = -1; // �ڵ���
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
			return "��ϵͼ�ɹ��������ݲ���...";
		}
		System.out.println(count);
		return vertex[name];
	}

	/**
	 * ���ͼ����г������Ľ�㣬�����ؽ������ƣ�����Ϊ���Ծ�ĳ�Ա
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
			return "��ϵͼ�ɹ��������ݲ���...";
		}
		System.out.println(count);
		return vertex[name];
	}

	/**
	 * ����ͼ�е���ϵ�ܶȣ��ж�������ϵ�Ľ�����
	 * @return
	 */
	public double getDensity() { 
		double summon = 0;
		for (int i = 0; i < pointNum; i++) {
			for (int j = 0; j < pointNum; j++) {
				summon += matrix[i][j]; // �����еı������
			}
		}
		summon = summon / ((pointNum - 1) * pointNum); // ���б߳����������õ��ܶ�
		return (summon);
	}

	/**
	 *  ��������������ͼ����Ҫ��㣬����Ϊ�ŶӼ���ϵ�Žӵĺ�������
	 */
	public void getGroupVIP() { 

		int flag = -1;
		for (int i = 0; i < pointNum; i++) {
			if (flag < record[i]) {
				flag = record[i];
			}
		}

		for (int i = 0; i < pointNum; i++) {
			System.out.print(vertex[i] + "���Ŷӽ�����Ծ����Ϊ:" + record[i]);
			if (record[i] == flag && record[i] != 0) {
				System.out.print("          �Ŷ��Ž�����");
			} else if (record[i] == 1) {
				System.out.print("          ��Ϊ����,��Ҫע��");
			} else if (record[i] == 0) {
				System.out.print("          �������Σ�գ�");
			}
			System.out.println();
		}
		// cout<<"ע:�Ŷӽ�����Ծ����ȡ���������н���֮�Ŷӵ�����֮�͡�"<<endl;
	}

	//-------------------������--------------------
	/**
	 * ��������ͼ��������ȱ�����������һЩ�����Ľ�㣩
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
	 * ����ͼ��������ȱ���
	 * @param ver  �����ڵ���
	 * @param visited  ��������
	 */
	private void depthTraverse(int ver, int visited[]) { 
		//Խ�紦��
		if (ver > pointNum && ver < 0) {
			return;
		}
		//��¼�����λ��
		visited[ver] = 1;
		//Ѱ����һ�����Ե���Ľڵ㣬ע�⵽��Ľڵ�����ǻ�û�з��ʹ���
		for (int i = 0; i < pointNum; i++) {
			if (matrix[ver][i] == 1 && visited[i] == 0) {
				depthTraverse(i, visited);
			}
		}
	}
	
	/**
	 * ��ȡͼ�е���ϵ�����γɵĻ������ֻ�һ�����һȺ��ϵ�Ƚ����е��Ŷӣ�������Ϊ�Ŷ�����
	 */
	public void getGroup() { 
		int step; // ��¼���ĳ���
		int visited[] = new int[pointNum];	//��¼���ʵ��Ľڵ�
		
		//��ʼ��record
		for (int i = 0; i < pointNum; i++) {
			record[i] = 0;
		}
		
		//
		for (int i = 0; i < pointNum; i++) {
			Stack<Integer> loop = new Stack<Integer>(); // ��ʼ����ջ
			step = 0; // ��ʼ������
			for (int j = 0; j < pointNum; j++) { // ��ʼ��visited����
				visited[j] = 0;
			}
			getCircle(i, visited, step, loop, i);
		}
	}

	/**
	 * ȡϵͳ�����еĻ�
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
		// ǰ�ò���****************************************
		loop.push(ver);
		step++;
		visited[ver] = 1;
		// **********************************************
		for (int i = 0; i < pointNum; i++) {
			//�����һ���ڵ����Ѿ����ʹ��������ǳ�����Ļ������γ�
			if (matrix[ver][i] == 1 && visited[i] == 1 && i == start) {
				int flag = 0; // ���ñ�־λ�������¼����δ�����ʵĵ㣬�����
				for (int j = 0; j < step; j++) { // �Ӳ���Ϊ0��ʼ���
					if (record[loop.get(j)] == 0) {
						flag = 1;
						break;
					}
				}
				if (flag == 1) {
					System.out.println("С��������:" + step);
					// cout<<"С�����������:"<<step<<endl;
					// cout<<"�������С����:"<<endl;
					for (int j = 0; j < step; j++) {
						int temp = loop.get(j);
						++record[temp];
						System.out.println("��" + (j + 1) + "λ��:" + vertex[temp]
								+ " ");
						// cout<<"��"<<i+1<<"λ��:"<<Vertex[temp]<<" ";
					}
					System.out.println();
					// cout<<endl;
				}
			}
			
			//����һ���ڵ��ǻ�û�б����ʹ�����������
			if (matrix[ver][i] == 1 && visited[i] == 0) {
				getCircle(i, visited, step, loop, start);
			}
		}
		// ���ò���****************************************
		step--;
		loop.pop();
		// **********************************************
	}

}
