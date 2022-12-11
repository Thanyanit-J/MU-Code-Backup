#include <stdio.h>
#include<string.h>


int main(){
	struct person{
		char Name[80];
		char Phone[11];
	};
	struct person member[2];
	char sortBy[5];
	int i;
	
	//////GET INFO//////
	for(int i=0; i<2;i++){
		printf("Enter name#%d: ",i+1);	scanf("%s",&member[i].Name);
		printf("Enter phone#%d: ",i+1);	scanf("%s",&member[i].Phone);
	}
	
	//////SELECT HOW TO SORT///////
	gets(sortBy);
	do{
		printf("Sort by: ");
		scanf("%s",&sortBy);
	}while(strcmp(sortBy,"name")!=0 && strcmp(sortBy,"phone")!=0);
	
	//////SORTING//////
	// By name
	if(strcmp(sortBy,"name")==0){
		if(strcmp(member[0].Name,member[1].Name)>0)
			for(i=1;i>=0;i--){
				printf("%s %s\n",member[i].Name,member[i].Phone);
			}
		else
			for(i=0;i<2;i++){
				printf("%s %s\n",member[i].Name,member[i].Phone);
			}
	}
	// By phone number
	else{
		if(strcmp(member[0].Phone,member[1].Phone)>0)
			for(i=1;i>=0;i--){
				printf("%s %s\n",member[i].Name,member[i].Phone);
			}
		else
			for(i=0;i<2;i++){
				printf("%s %s\n",member[i].Name,member[i].Phone);
			}
	}
	
	return 0;
}


