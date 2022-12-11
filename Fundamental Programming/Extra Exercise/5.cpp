#include<stdio.h>

int dotProduct(int a[],int b[],int size){
	int dot=0;
	for(int i=0;i<size;i++){
		dot+=a[i]*b[i];
	}
	return dot;
}

int main(){
	int size;
	printf("size: "); scanf("%d",&size);
	int a[size],b[size],dot;
	
	printf("First vector : ");for(int i=0;i<size;i++)scanf("%d",&a[i]);
	printf("Second vector: ");for(int i=0;i<size;i++)scanf("%d",&b[i]);
	
	dot=dotProduct(a,b,size);
	
	printf("Dot product: %d",dot);
		
	return 0;
}
