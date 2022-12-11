#include "stdio.h"
#include "math.h"

double euc_dist(double x1,double x2,double y1,double y2){
	double d = sqrt((x2-x1)*(x2-x1) + (y2-y1)*(y2-y1));
	return d;
}


int main(){
	double x1,x2,y1,y2;
	
	printf("Please enter a value for x1: "); scanf("%lf",&x1);
	printf("\nPlease enter a value for y1: "); scanf("%lf",&y1);
	printf("\nPlease enter a value for x2: "); scanf("%lf",&x2);
	printf("\nPlease enter a value for y2: "); scanf("%lf",&y2);
	
	printf("\nThe distance between the points is: %lf",euc_dist(x1,x2,y1,y2));
	
	printf("\n");
	return 0;
}
