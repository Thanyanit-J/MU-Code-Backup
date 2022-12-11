#include<stdio.h>

void findMinMax(int array[], int nElems, int *max, int *min);

int main(){
	int size;
	printf("size: "); scanf("%d",&size);
	int arr[size];
	printf("Array : ");for(int i=0;i<size;i++)scanf("%d",&arr[i]);
	
	int max,min;
	findMinMax(arr,size,&max,&min);
	printf("Max = %d\nMin = %d",max,min);
	
	return 0;
}

void findMinMax(int array[], int nElems, int *max, int *min){
	*max=array[0];
	*min=array[0];
	for(int i=1;i<nElems;i++){
		if(array[i]>*max) *max=array[i];
		else if(array[i]<*min) *min=array[i]; 
	}
}
