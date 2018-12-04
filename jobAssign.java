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
		c=new int[50][50];//c[i][j]表示工人i执行作业j所需的时间
		 jobnum=inputNum[0];//jobnum表示工作数量与工人数量，两个数量是相等的。
		int k=1;
		//把文件中读到的一维数组值赋值给二维数组方阵
			for(int i=1;i<=jobnum;i++) {
				for(int j=1;j<=jobnum;j++)
					c[i][j]=inputNum[k++];
			}
		task=new int[50];//表示当前作业分配的情况，
		jobassign=new int[50];//表示工作有没有被分配，1表示已经被分配，0表示没有被分配
		bestSolution=new int[50];//表示最优解分配情况
		//初始化数组
		for(int i=0;i<=jobnum;i++) {
			jobassign[i]=0;
			task[i]=0;
			bestSolution[i]=0;
		}
		 mincost=89757;//设置一个足够大的数值作为初值
		 //开始时，向assign方法传俩值，第一个值为为第1个工人分配工作，第二个值为花费的值即当前作业分配后的花费
		 assign(1,0);
		 System.out.println("第"+h+"组数据最优花费为"+mincost);
		 System.out.println("分配方案如下：");
			for(int i=1;i<=jobnum;i++) {
				System.out.println("工人"+i+"执行任务"+bestSolution[i]);
				}
			}
				}
			//为工人k分配作业，cost：当前作业分配后的花费情况
			public static void assign(int k, int cost) {
				// TODO Auto-generated method stub
				//递归调用进来之后，k是当前状态最火分配的人，且当前花费比目前最小值小。mincost=cost,最优解的数组bestSolution里存task数组值，
				//bestSolution的下标跟task数组下标都表示第几个工人，这样，数组中存值代表第几个工人最适合做那件事。
				if(k>jobnum&&cost<mincost)//搜索至叶节点，判断是否更优。
				{
					mincost=cost;
					for(int i=1;i<=jobnum;i++)
						bestSolution[i]=task[i];
				}
				//如果上述条件不成立，那么，循环遍历还没有分配的工作尝试分配给工人，再去作比较。再做比较的过程就要用到递归，而递归调用完以后，把工作分配状态，工人做哪个工作清空，回溯到上一级，再去分配，尝试各种情况，
				//直到回溯到开始时得到最优解。
				else
				{
					for(int i=1;i<=jobnum;i++) {
						if(jobassign[i]==0&& (cost+c[k][i])!=0) //约束函数
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
			//使用RandomAccessFile方式实现将文本文件读取至一维数组
		public static int[] toArrayByRandomAccessFile(String name) {
			// 使用ArrayList来存储每行读取到的字符串
			ArrayList<String> arrayList = new ArrayList<>();
			try {
				File file = new File(name);
				RandomAccessFile fileR = new RandomAccessFile(file,"r");
				// 按行读取字符串
				String str = null;
				while ((str = fileR.readLine())!= null) {
					arrayList.add(str);
				}
				fileR.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			// 对ArrayList中存储的字符串进行处理 
			int length = arrayList.size();
			int[] array = new int[length];
			for (int i = 0; i < length; i++) {
				String s = arrayList.get(i);
				array[i] = Integer.parseInt(s);
			}
			// 返回数组
			return array;
		}


}
