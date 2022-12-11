#include <stdio.h>
int main(){
	// The input
	int withdraw_amount;
	// Get input until the input is valid (input%100==0 && input>0)
	do{
		// Get input
		printf("Withdraw: "); scanf("%d", &withdraw_amount);
		
		if(withdraw_amount < 0)
		{
			printf("Invalid amount of cash.\nPlease try again.\n\n");
		}
		
		else if(withdraw_amount%100!=0)
		{
			printf("ATM only have 1000, 500, 100 notes.\nPlease try again.\n\n");
		}
		
		// If the input is valid, get out of the loop.
		else
		{
			break;
		}
	} while(1);
	
	printf("1000: %d\n", withdraw_amount/1000);
	withdraw_amount %= 1000; // alternatively: withdraw_amount -= (withdraw_amount/1000)*1000;
	printf("500 : %d\n", withdraw_amount/500);
	withdraw_amount %= 500;
	printf("100 : %d\n", withdraw_amount/100);
	withdraw_amount %= 100;
	
	// END THE PROGRAM
	return 0;
} // End main()
