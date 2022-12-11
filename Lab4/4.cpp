#include<stdio.h>
int main(){
	int num;
	scanf("%d",&num);
	(num==1)? printf("You have ordered: Coke") :
	(num==2)? printf("You have ordered: Est Cola") :
	(num==3)? printf("You have ordered: Oishi Green Tea") :
	(num==4)? printf("You have ordered: Sprite") :
	printf("Invalid drink number!");
}
