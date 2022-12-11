#include<stdio.h>
int main(){
	int num[5],sum=0;
	for(int i=0;i<5;i++){
		scanf("%d",&num[i]);
		if(i==0 || i==2 || i==4)
			sum+=num[i];
	}
	printf("SUM = %d", sum);
}
