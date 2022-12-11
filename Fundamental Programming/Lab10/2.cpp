#include "stdio.h"
#include "stdlib.h"

double find_abs(double a){
	if(a<0) return a*(-1);
	else return a;
}

int main(){
	double n,m;
	printf("Enter a number: "); scanf("%lf",&n);
	
	printf("The absolute value of %g is %g",n,find_abs(n));
	
	printf("\n");
	return 0;
}
