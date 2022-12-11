#include<stdio.h>

int main(){
	int n;
	do{
		printf("Input odd number: ");
		scanf("%d",&n);
	}while(n%2==0 && n<=15);
	
	for(int i=1;i<=n;i++){
		for(int j=1;j<=n;j++){
			if(j==i && j==n-i+1) printf("3 ");
			else if(j==i || j==n-i+1) printf("2 ");
			else printf("1 ");
		}
		printf("\n");
	}
	
	return 0;
}
