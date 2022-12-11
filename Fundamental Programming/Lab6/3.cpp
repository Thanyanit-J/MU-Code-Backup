#include<stdio.h>
/*int main(){
	int n;
	printf("Input n: "); scanf("%d",&n);
	for(int i=n;i>=1;i-=2){
		for(int j=1;j<=(n-i)/2;j++)
				printf(" ");
		for(int k=1;k<=i;k++){	
			printf("*");
		}
		printf("\n");	
	}
	
}/**/

int main(){
	int n;
	printf("Input n: "); scanf("%d",&n);
	for(int i=n;i>=(n+1)/2;i--){
		for(int k=1;k<=i;k++){	
			if(k<=n-i)
			printf(" ");
			else
			printf("*");
		}
		printf("\n");	
	}
	
}/**/
