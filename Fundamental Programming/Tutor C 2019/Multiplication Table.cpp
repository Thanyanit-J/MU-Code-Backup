#include <stdio.h>
int main(){
	for (int i = 1; i <= 12; i++)
	{
		printf("%2d: ",i);
		for (int j = 1; j <= 12; j++)
		{
			printf("%3d ", i*j);
		}
		printf("\\n\n");
	}
}
