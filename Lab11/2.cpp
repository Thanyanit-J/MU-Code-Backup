#include <stdio.h>
int DATA[8] = {1, 0, 2, 6, 7, 5, 4, 3};
int found_idx = -1;
int i=0;
void search_for(int target);
int main() {
	int target = 6;
	i=7;
	search_for(target);
	if (found_idx != -1) {
		printf("Found the value %d at the index: %d\n", target, found_idx);
	} else {
		printf("Cannot find the value %d in the data\n", target);
	}
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
