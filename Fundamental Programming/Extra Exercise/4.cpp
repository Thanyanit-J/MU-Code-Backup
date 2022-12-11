#include<stdio.h>
#include<string.h>

int main(){
	char str1[80],str2[80];
	int count=0;
	
	printf("String 1: "); scanf("%s",str1);
	do{
		printf("String 2: "); scanf("%s",str2);
	}while(strlen(str1)!=strlen(str2));
	
	strupr(str1);
	strupr(str2);
	
	for(int i=0;i<strlen(str1);i++){
		if(str1[i]!=str2[i]) count++;
	}
	
	printf("Hamming distance = %d",count);
	return 0;
}
