#include<stdio.h>
#include<stdlib.h>

int main(){
	float f,c[6];
	
	FILE *fp,*fp2;
	if((fp = fopen("f.txt","r"))==NULL){
		printf("Error opening file\n");
		exit(1);
	}
	for(int i=0;fscanf(fp,"%f",&f)!=EOF;i++){
		c[i]=(f-32)*5/9;
	}
	fclose(fp);
	//////////////////////// CREATE ///////////////////////////////
	//////////////////////// A FILE ///////////////////////////////
	if((fp2 = fopen("c.txt","w"))==NULL){
		printf("Error opening file\n");
		exit(1);
	}
	for(int j=0;j<6;j++){
		printf("%.2f\n",c[j]);
		fprintf(fp2,"%.2f\n",c[j]);
	}
	fclose(fp2);/**/
	
	return 0;
}
