package jobAssign;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;

public class jobAssign {
	static int mincost;
	static int jobnum;
	static int[] jobassign;
	static int[] task;
	static int[] bestSolution;
	static int[][] c;
	public static void main(String args[]) {
		for(int h=1;h<=5;h++) {
		int[] inputNum= toArrayByRandomAccessFile("D:\\input_assign04_"+h+".txt"); 
		c=new int[50][50];//c[i][j]��ʾ����iִ����ҵj�����ʱ��
		 jobnum=inputNum[0];//jobnum��ʾ���������빤��������������������ȵġ�
		int k=1;
		//���ļ��ж�����һά����ֵ��ֵ����ά���鷽��
			for(int i=1;i<=jobnum;i++) {
				for(int j=1;j<=jobnum;j++)
					c[i][j]=inputNum[k++];
			}
		task=new int[50];//��ʾ��ǰ��ҵ����������
		jobassign=new int[50];//��ʾ������û�б����䣬1��ʾ�Ѿ������䣬0��ʾû�б�����
		bestSolution=new int[50];//��ʾ���Ž�������
		//��ʼ������
		for(int i=0;i<=jobnum;i++) {
			jobassign[i]=0;
			task[i]=0;
			bestSolution[i]=0;
		}
		 mincost=89757;//����һ���㹻�����ֵ��Ϊ��ֵ
		 //��ʼʱ����assign��������ֵ����һ��ֵΪΪ��1�����˷��乤�����ڶ���ֵΪ���ѵ�ֵ����ǰ��ҵ�����Ļ���
		 assign(1,0);
		 System.out.println("��"+h+"���������Ż���Ϊ"+mincost);
		 System.out.println("���䷽�����£�");
			for(int i=1;i<=jobnum;i++) {
				System.out.println("����"+i+"ִ������"+bestSolution[i]);
				}
			}
				}
			//Ϊ����k������ҵ��cost����ǰ��ҵ�����Ļ������
			public static void assign(int k, int cost) {
				// TODO Auto-generated method stub
				//�ݹ���ý���֮��k�ǵ�ǰ״̬��������ˣ��ҵ�ǰ���ѱ�Ŀǰ��СֵС��mincost=cost,���Ž������bestSolution���task����ֵ��
				//bestSolution���±��task�����±궼��ʾ�ڼ������ˣ������������д�ֵ����ڼ����������ʺ����Ǽ��¡�
				if(k>jobnum&&cost<mincost)//������Ҷ�ڵ㣬�ж��Ƿ���š�
				{
					mincost=cost;
					for(int i=1;i<=jobnum;i++)
						bestSolution[i]=task[i];
				}
				//���������������������ô��ѭ��������û�з���Ĺ������Է�������ˣ���ȥ���Ƚϡ������ȽϵĹ��̾�Ҫ�õ��ݹ飬���ݹ�������Ժ󣬰ѹ�������״̬���������ĸ�������գ����ݵ���һ������ȥ���䣬���Ը��������
				//ֱ�����ݵ���ʼʱ�õ����Ž⡣
				else
				{
					for(int i=1;i<=jobnum;i++) {
						if(jobassign[i]==0&& (cost+c[k][i])!=0) //Լ������
						{
							jobassign[i]=1;
							task[k]=i;
							assign(k+1,cost+c[k][i]);
							jobassign[i]=0;
							task[k]=0;
						}
					}
				}
				
			}
			//ʹ��RandomAccessFile��ʽʵ�ֽ��ı��ļ���ȡ��һά����
		public static int[] toArrayByRandomAccessFile(String name) {
			// ʹ��ArrayList���洢ÿ�ж�ȡ�����ַ���
			ArrayList<String> arrayList = new ArrayList<>();
			try {
				File file = new File(name);
				RandomAccessFile fileR = new RandomAccessFile(file,"r");
				// ���ж�ȡ�ַ���
				String str = null;
				while ((str = fileR.readLine())!= null) {
					arrayList.add(str);
				}
				fileR.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			// ��ArrayList�д洢���ַ������д��� 
			int length = arrayList.size();
			int[] array = new int[length];
			for (int i = 0; i < length; i++) {
				String s = arrayList.get(i);
				array[i] = Integer.parseInt(s);
			}
			// ��������
			return array;
		}


}
