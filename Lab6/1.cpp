#include<stdio.h>
int main(){
	int n;
	printf("Enter a number of row: "); scanf("%d",&n);
	for(n;n>=1;n--){
		for(int i=n;i>=1;i--){
		printf("%d",i);
		}
		printf("\n");
	}
}
