#include <stdio.h>
int DATA[8] = {1, -10, 2, 6, 7, 5, 4, 3};
int found_idx = -1;
int i=0;

void search_for(int target);
void min_value();

int main() {
	int target = 6;
	i=7;
	search_for(target);
	if (found_idx != -1) {
		printf("Found the value %d at the index: %d\n", target, found_idx);
	} else {
		printf("Cannot find the value %d in the data\n", target);
	}
	min_value();
	return 0;
}

void search_for(int target) {
	//int found_idx = -1;
	for (int i=0 ; i<8 ; i++) {
		if (DATA[i] == target) {
			found_idx = i;
			break;
		}
	}
}

void min_value(){
	int min = DATA[0],a;
	for(int i=1;i<8;i++){
		if (min>DATA[i])
		{
			min=DATA[i];
		}
	}
	printf("The minimum value in the DATA array is %d.",min);
}
