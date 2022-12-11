#include "stdio.h"
#include "stdlib.h"
void check(int n,float f,double db){
	printf("\nThe integer is %d",n);
	printf("\nThe floating point number is %f",f);
	printf("\nThe double precision number is %lf",db);
}
int main(){
	int n;
	float f;
	double db;
	
	printf("Enter an integer: "); scanf("%d",&n);
	printf("Enter a floating point number: "); scanf("%f",&f);
	printf("Enter a double precision number: "); scanf("%lf",&db);
	
	check(n,f,db);
	
	printf("\n");
	return 0;
}
