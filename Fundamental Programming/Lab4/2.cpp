#include<stdio.h>
int main(){
	int num1,num2,num3;
	scanf("%d %d %d",&num1,&num2,&num3);
	switch( ( ((num1>num2&&num1>num3)||(num1==num2&&num1>=num3))*1 ) + 
            ( ((num2>num1&&num2>num3)||(num2==num3&&num2> num1))*2 ) + 
		    ( ((num3>num1&&num3>num2)||(num3==num1&&num3> num2))*3 ) ){
		case 1:
			printf("Max number is %d",num1);
			break;
		case 2:
			printf("Max number is %d",num2);
			break;
		case 3:
			printf("Max number is %d",num3);
			break;
	}
}

