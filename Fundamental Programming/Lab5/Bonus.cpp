#include<stdio.h>
int main(){
	int num;
	printf("Please Enter numbers: ");
	while(scanf(" %d",&num)!=EOF){
		printf(" %d |",num);
		for(int i=0;i<num;i++)
		printf("*");
		printf("\n");
	}
}
