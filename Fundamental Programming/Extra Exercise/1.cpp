#include<stdio.h>

void FindFibonacciSeries(int n,int *arr){
	
	for(int i=0;i<n;i++){
		if(i==0) *(arr+i)=0;
		else if(i==1) *(arr+i)=1;
		else *(arr+i)=*(arr+i-2)+*(arr+i-1);
		printf("%d ",*(arr+i));
	}
}

int main(){
	int fibo[30];
	FindFibonacciSeries(30,fibo);
	
	return 0;
}
