#include<stdio.h>
int main(){
	char op;
	float num1,num2;
	
	printf("Select an operator either(+,-,*,/) ");
	scanf(" %c",&op);
	printf("Enter two numbers: ");
	scanf(" %f %f",&num1,&num2);
	switch(op){
		case '+': printf("%.1f %c %.1f = %.1f",num1,op,num2,num1+num2); break;
		case '-': printf("%.1f %c %.1f = %.1f",num1,op,num2,num1-num2); break;
		case '*': printf("%.1f %c %.1f = %.1f",num1,op,num2,num1*num2); break;
		case '/': printf("%.1f %c %.1f = %.1f",num1,op,num2,num1/num2); break;
	}
}
