#include <stdio.h>

#define usd 32.86
#define jpy 0.29

float ConvertToUSD(float n);
float ConvertToJPY(float n);

int main(){
	
	float thb = 5000;
	
	printf("THB: %.2f\n", thb);
	printf("\nConvert to USD: %.2f", ConvertToUSD(thb) );
	printf("\nConvert to JPY: %.2f", ConvertToJPY(thb) );
	printf("\n");
	return 0;
}

float ConvertToUSD(float n){
	return n/usd;
}

float ConvertToJPY(float n){
	return n/jpy;
}
