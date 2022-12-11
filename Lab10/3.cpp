#include "stdio.h"
#include "stdlib.h"
#include "time.h"
int guess = 7;

int HILO(int ans){
	int num;
	printf("Enter your guess: "); scanf("%d",&num);
	
	if(num==ans){
		printf("Hooray, you have won!");
		return 0;
	}
	else if(guess == 0){
		printf("Sorry, you lose.");
		printf("\nThe correct number is %d",ans);
		return 0;
	}
	else{
		printf("Wrong Number:( Your guess was too ");
 		if(num>=ans) printf("high.");
 		else 		 printf("low.");
		
		guess--;
		
		if(guess>1)	printf("\nYou have %d guesses left. Try Again\n",guess);
		else		printf("\nYou have %d guess left. Try Again\n",guess);
		printf("\n");
		return 1;
	}
}

int main(){
	srand(time(NULL));
	
	int rand_num = rand()%100 +1;
	
	while(HILO(rand_num)){}

	printf("\n");
	return 0;
}
