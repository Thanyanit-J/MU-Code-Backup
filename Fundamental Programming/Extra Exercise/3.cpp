#include<stdio.h>
const double pi = 3.14159265358979323846264338327950288419716939937510582097494459230781640628620899862803482534211706798214808651328230664709384460955058223172535940812848111745028410270193852110555964462294895493;

void designShield(double *r,double *cir,double *area){
	*cir=2*pi**r;
	*area=pi**r**r;
}

int main(){
	double rad,cir,area;
	printf("Radius: ");scanf("%lf",&rad);
	designShield(&rad,&cir,&area);
	printf("Circumference = %lf",cir);
	printf("\nThe area = %lf",area);
	
	return 0;
}
