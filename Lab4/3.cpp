# include <stdio.h>
int main()
{ 
  	double number;
    
	start: printf("Enter a number:");
   	scanf("%lf",&number);
	if(number < 0.0){
	 	printf("number is negative\n");
	 	goto start;
	 }
	else
		printf("number is positive");
	return 0;
}
