#include<stdio.h>
/*int main(){
	for(int i=7;i>=1;i--)
	{
		for(int j=97;j<97+i;j++)
			printf("%c ",j);
		for(int k=1;k<=(i-7)*(-2)-1;k++)
			printf("  ");
		for(int l=96+i;l>=97;l--)
			if(l!=103) printf("%c ",l);
		
		printf("\n");
	}
}/**/
int main(){
	for(int i=0;i<7;i++)
	{
		for(int j=97;j<=103;j++)
		{
			if(j<104-i) printf("%c ",j);
			else printf("  ");
		}
		for(int k=102;k>=97;k--)
		{
			if(k<104-i) printf("%c ",k);
			else printf("  ");
		}
		printf("\n");
	}
}/**/
