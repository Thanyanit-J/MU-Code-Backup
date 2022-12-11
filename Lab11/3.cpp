#include <stdio.h>

#define N 4

int gcd(int num1, int num2) {
    // Parameter to store the GCD
    int result;

    ////////////////////////////////////////////////////////////////////////
    //                        Start of your code                          //
    ////////////////////////////////////////////////////////////////////////
    if(num1<0) num1*=-1;
    if(num2<0) num2*=-1;
    
    if(num1>num2){
    	int a=num2;
    	num2=num1;
    	num1=a;
	}
	
	for(int i=num1; i>=1 ;i--){
		if(num1%i==0 && num2%i==0){
		    result = i;
			break;
		}
	}
    ////////////////////////////////////////////////////////////////////////
    //                         End of your code                           //
    ////////////////////////////////////////////////////////////////////////

    // Return the GCD
    return result;
}

int main() {
    int num[N];     // Input numbers
    int result;     // GCD of the input numbers

    printf("Enter %d numbers: ", N);
    for (int i=0 ; i<N ; i++) {
        scanf("%d", &num[i]);
    }

    ////////////////////////////////////////////////////////////////////////
    //                        Start of your code                          //
    ////////////////////////////////////////////////////////////////////////
    
	result = gcd(num[0],num[1]);
	result = gcd(result,num[2]);
	result = gcd(result,num[3]);

    ////////////////////////////////////////////////////////////////////////
    //                         End of your code                           //
    ////////////////////////////////////////////////////////////////////////

    printf("GCD of ");
    for (int i=0 ; i<N ; i++) {
        printf("%d ", num[i]);
    }
    printf("is %d\n", result);

    return 0;
}
