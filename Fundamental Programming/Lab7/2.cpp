#include<stdio.h>
int main(){
	int num[10],i;
	float avg=0;
	for(i=0;1;i++){
		scanf("%d",&num[i]);
		if(num[i]>=0) avg+=num[i];
		else break;
	}
	printf("AVG = (");
	for(int j=0;j<i;j++){
		printf("%d",num[j]);
		if(j!=i-1) printf("+");
	}
	printf(")/%d = %.1f",i,avg/(i));
}
