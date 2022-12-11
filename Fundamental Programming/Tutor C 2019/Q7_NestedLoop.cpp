#include <stdio.h>
int main(){
	int row_max;
	
	// Get total row from user
	printf("Enter a number of row: "); scanf(" %d", &row_max);
	
	/* The goal is to print a shape like this
		    ...*..*
		    ..**..**
			.***..***
			****..****
		Note that I use '.' instead of the space.
	*/
	for(int row=0; row < row_max; row++)
	{
		/* Print this part
			...
			..
			.
			
		*/
		for(int i = row_max-1; i > row; i--)
		{
			printf(".");
		}
		
		/* Print the left * triangle
			...*      *
 			..**  --> **
			.***      ***
			****      ****
			Imagine if you print * triangle without the spaces in the front, it's actually same as a triangle on the right
		*/
		for(int i=0; i <= row; i++)
		{
			printf("*");
		}
		
		// Print 2 Spaces
		printf("..");
		
		// Print the * triangle again
		for(int i=0; i <= row; i++)
		{
			printf("*");
		}
		
		// End the line, print \n
		printf("\n");
		
	}// END FOR LOOP
	
	// END THE PROGRAM
	return 0;
}// END MAIN
