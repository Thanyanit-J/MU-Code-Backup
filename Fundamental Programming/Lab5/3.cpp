#include<stdio.h>
int main(){
	for(float hour=0;hour<=4;hour+=0.5){
		printf("Distance for %.1f hours is %.2f\n",hour,hour*60);
	}
}
