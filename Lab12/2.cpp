#include<stdio.h>

void sort_nums(int *a, int *b, int *c);
void swap(int *a, int *b);

int main(){
	int n1,n2,n3;	
	
	start:
		printf("Enter 3 integers: ");
		scanf("%d %d %d",&n1,&n2,&n3);
		sort_nums(&n1,&n2,&n3);
		printf("\n\n");
		goto start;
}

void sort_nums(int *a, int *b, int *c){
	do{
		if(*a<*b) swap(a,b);
		if(*b<*c) swap(b,c);
	}while(*a<*b || *b<*c);
	printf("Sorted integers: %d %d %d",*a,*b,*c);
}

void swap(int *a, int *b){
	int tmp = *a;
	*a = *b;
	*b = tmp;
}
