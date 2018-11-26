#include<stdio.h>
#include<iostream>
#include <fstream>
#include <cassert>
#include<algorithm>
#include<cstring> 
#include<cmath>
#include <vector>
using namespace std;
//infile表示一个文件流对象 
ifstream infile; 
//minCost保存最小的花费 
int minCost=0;
//n表示作业数和人数 
int n,j; 
//expand按顺序存放扩展结点 
int expand[100000];
//minCostP存放深度为P时的最小花费 
int minCostP; 
//minCostRow表示最小下界节点表示的行 
int minCostRow; 
int lowBound; 
int c[100][100];
int minCostRank(int k,int column){
	int min=100000;
	for(int i=0;i<n;++i){
		bool isExpand = false;
		for(j=0;j<n;++j){
			if(expand[j]==i){
			  isExpand=true;
			  break;
			}
		}
		if((i!=k)&&(!isExpand)&&(c[i][column]<min)){    //找出这一列的最小值 
			min=c[i][column];
		}
		
	}
	return min;
}
void assignWork(int p){
    while(true){
    	//遍历到叶子节点 
    	if(p<n){
		bool isExpand = false; //用来标记当前的行是否被扩展 
		minCostP=1000000;
		for(int i=0;i<n;++i){
		   for(j=0;j<n;++j){
		   	if(expand[j]==i){
		   		isExpand = true; //表示当前所在行中某一节点已经被扩展，置isExpand标记位为真，表示当前所在行的节点不能被扩展 
			   break;
			   }
		   }
		if(!isExpand){    //表示当前所在行的节点未被扩展； 
			lowBound=c[i][p]; 
			for(int m=p+1;m<n;++m){ 
				lowBound+=minCostRank(i,m);//用来存储深度为p下的花费下界，取剩余未被扩展节点花费最小的相加 
			} 
			if(lowBound<minCostP) {
			 minCostP =  lowBound;
			 minCostRow = i; //记录扩展节点的行号 
			 expand[p]=i;    //将第P个作业分配给工人i 
		}
		} 
		
		isExpand = false;  //恢复当前节点为未被扩展行 
		 expand[p]=-2; //恢复当前节点为未被扩展节点 
		 } 	
    	expand[p]= minCostRow; //存放花费最小的扩展节点 
    	++p; 
	}
	else{    //到达叶子节点
	 for(int i=0;i<n;++i){   //根据扩展节点的顺序，计算最小花费 
	 	minCost += c[expand[i]][i];
	 } 
	 break;
	} 
}
}
void ReadTxt(string file)
	{
		//连接文件与文件流对象 
		infile.open(file.data());
		//若失败，则输出错误消息，并终止程序运行 
		assert(infile.is_open());
		int i,j;
		//读取文件 
		while(!infile.eof())
		{
		
		//n表示有n个人和n个作业 
		   infile>>n;
		   for(i=0;i<n;++i)
		    for(j=0;j<n;++j){
		     infile>>c[i][j];
			}
	    }
	    assignWork(0);
	}
	void Print(){
			for(int i=0;i<n;++i){
			cout<<"第"<<i+1<<"个作业"<<"由工人"<<expand[i]+1<<"完成"<<endl;
		}
		cout<<"最少消耗的总时间为："<<minCost<<endl; 	
	}  
int main(){
		//测试用例所在文件名  
		string testName;
		cout<<"提示文件名：input_assign05_01.dat/input_assign05_02.dat"<<endl;
			cout<<"/input_assign05_03.dat/input_assign05_04.dat/input_assign05_05.dat"<<endl;
		while(true)
		{
		  memset(expand,-2,sizeof(expand));
		  minCost=0;
		  cout<<"请输入文件名：";
		  cin>>testName; 
	      ReadTxt(testName);
          Print();
	      infile.close();
	    }
	return 0;
}
