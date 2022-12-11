#include<stdio.h>
int main(){
	int i=125;
	while(i>=5){
		if(i%25!=0)	printf("%d ",i);
		i-=10;
	}
}
