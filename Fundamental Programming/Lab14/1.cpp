#include<stdio.h>
#include<stdlib.h>

struct Student{
	int id;
	char name[80];
	struct Birthdate{
		int day,month,year;
	}bd;
}stud;
	
int calculateAge(struct Student);

int main(){

	FILE *fp;
	if((fp = fopen("student.txt","r"))==NULL){
		printf("Error opening file\n");
		exit(1);
	}
	
	while (fscanf(fp, "%d %s %d %d %d", &stud.id, stud.name, &stud.bd.day, &stud.bd.month, &stud.bd.year) != EOF){
		printf("Read %d %s %d %d %d\n", stud.id, stud.name, stud.bd.day, stud.bd.month, stud.bd.year);		
	}
	fclose(fp);
	//////////////////////// CREATE ///////////////////////////////
	//////////////////////// A FILE ///////////////////////////////
	if((fp = fopen("student_age.txt","w"))==NULL){
		printf("Error opening file\n");
		exit(1);
	}
	int age=calculateAge(stud);
	fprintf(fp,"Student ID: %d\n",stud.id);
	fprintf(fp,"Name: %s\n",stud.name);
	fprintf(fp,"Birthdate: %d/%d/%d\n",stud.bd.day, stud.bd.month, stud.bd.year);
	fprintf(fp,"Age: %d",age);
	printf("Saved to: student_age.txt");
	fclose(fp);
	
	return 0;
}

int calculateAge(struct Student){
	return 2018-stud.bd.year;
}
