#include<stdio.h>
#include<string.h>
#include<ctype.h>
int countVowel(char *a){
	int i,count=0;
	for(i=0;*(a+i) != '\0';i++){
		if(*(a+i)=='a'||*(a+i)=='e'||*(a+i)=='i'||*(a+i)=='o'||*(a+i)=='u'){
			count++;
		}
	}
	return count;
}
int checkAnagram(char a[],char b[]){
	char tmp,*pos;
	if(strlen(a)!=strlen(b)) return 0;
	for(int i=0;i<strlen(a);i++){
		while(a[i]!=b[i]){
			if(strchr(b,a[i])!=NULL){
				pos=strchr(b,a[i]);
				tmp=*pos;
				*pos=b[i];
				b[i]=tmp;
			}
			else
				return 0;
		}
	}
	return 1;
}
int main(){
	char a[100],b[100];
	
	fgets(a,100,stdin);
	fgets(b,100,stdin);

	char *pos;
	if ((pos=strchr(a,'\n')) != NULL)
	    *pos = '\0';
	if ((pos=strchr(b,'\n')) != NULL)
		*pos = '\0';
	
	//task1
	if(strlen(a)>strlen(b)) printf("The 1st String is longer than the 2nd string.\n");
	else printf("The 1st String is not longer than the 2nd string.\n");
	//task2
	strlwr(a);
	strlwr(b);
	
	if(countVowel(a)>countVowel(b))
		printf("The 1st String has more vowel than the 2nd string.\n");
	else 
		printf("The 1st String doesn't have more vowel than the 2nd string.\n");
	//task3
	if(checkAnagram(a,b)) printf("Anagram confirmed.\n");
	else printf("Not anagram.\n");
	
	return 0;
}
