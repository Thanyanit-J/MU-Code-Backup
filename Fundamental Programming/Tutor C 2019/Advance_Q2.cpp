#include <stdio.h>
int main(){
	// The input
	int withdraw_amount;
	// Assign the number of each notes available.
	int cash1000 = 10;
	int cash500 = 20;
	int cash100 = 30;
	
	//Get input
	printf("Withdraw: "); scanf("%d", &withdraw_amount);
	
	// If the machine has insufficient cash, END THE PROGRAM
	if(withdraw_amount > (cash1000*1000) + (cash500 * 500) + (cash100 * 100))
	{
		printf("We don’t have enough cash.");
		return 0;
	}
	
		// A temporary variable for getting the number of notes that will go out.
	int noteOut_tmp; 
	
	// GIVE OUT 1000 Notes
	noteOut_tmp = withdraw_amount/1000;
	// If the notes that will go out exceeds the number of the available notes,
	if(noteOut_tmp > cash1000)
	{
		// just give out the notes that the machine currently has.
		printf("1000: %d\n", cash1000);
		withdraw_amount -= cash1000 * 1000;
	}
	else
	{
		printf("1000: %d\n", noteOut_tmp);
		withdraw_amount -= noteOut_tmp * 1000;
	}
	
	// GIVE OUT 500 Notes
	noteOut_tmp = withdraw_amount/500;
	if(noteOut_tmp > cash500)
	{
		printf("500: %d\n", cash500);
		withdraw_amount -= cash500 * 500;
	}
	else
	{
		printf("500: %d\n", noteOut_tmp);
		withdraw_amount -= noteOut_tmp * 500;
	}
	
	// GIVE OUT 100 Notes
	noteOut_tmp = withdraw_amount/100;
	printf("100: %d\n", noteOut_tmp);
	withdraw_amount -= noteOut_tmp * 100;
	
	// END THE PROGRAM
	return 0;
} // End main()
