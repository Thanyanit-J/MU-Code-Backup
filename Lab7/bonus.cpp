#include<stdio.h>
int main(){
	int num1[5],num2[5],i;
	for(i=0;i<5;i++) scanf("%d",&num1[i]); //Get num
	printf("\n");
	
	for(int j=0;j<5;j++) num2[j]=num1[j]; //Copy
	
	for(int k=4;k>=0;k--) printf("%d ",num2[k]); //Display in reverse order
}
