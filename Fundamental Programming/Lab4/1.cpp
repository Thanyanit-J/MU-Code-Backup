#include<stdio.h>
int main(){
	int num;
	scanf("%d",&num);
	switch(num)
	{
		case 1:
			printf("You have ordered: Coke");
			break;
		case 2:
			printf("You have ordered: Est Cola");
			break;
		case 3:
			printf("You have ordered: Oishi Green Tea");
			break;
		case 4:
			printf("You have ordered: Sprite");
			break;
		default:
			printf("Invalid drink number!");
	}
}
