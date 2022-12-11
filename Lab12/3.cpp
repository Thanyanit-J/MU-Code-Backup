#include<stdio.h>

float findMean(int *arrPtr);
int findMin(int *arrPtr);

int main(){
	float mean;
	int minimum;
	int num[5];
	
	printf("Enter 5 integers: ");
	for(int i=0;i<5;i++) scanf("%d",&num[i]);
	
	mean = findMean(&num[0]);
	
	minimum = findMin(&num[0]);
	
	printf("Mean: %.3f and Min: %d",mean,minimum);
}

float findMean(int *arrPtr){
	float sum = (*arrPtr + *(arrPtr+1) + *(arrPtr+2) + *(arrPtr+3) + *(arrPtr+4));
	
	return sum/5;
}

int findMin(int *arrPtr){
	int min = *arrPtr;
	for(int i=0;i!=4;i++){
		if(min>*(arrPtr+i)) min = *(arrPtr+i);
	}
	return min;
}
