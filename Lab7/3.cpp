#include<stdio.h>
int main(){
	int num[5],swap;
	st: for(int i=0;i<5;i++) 
	{
	 	scanf("%d",&num[i]);
		if(num[i]<0) 
		{
		 	printf("Negative\n");
	 		goto st;
	    }
	}
		
		{
			printf("\n");
			//printf("%d %d %d %d %d",num[4],num[2],num[1],num[3],num[0]);
			
			swap=num[0];
			num[0]=num[4];
			num[4]=swap;
			
			swap=num[1];
			num[1]=num[2];
			num[2]=swap;
			
			for(int i=0;i<5;i++) printf("%d ",num[i]);/**/
		}
	
}
