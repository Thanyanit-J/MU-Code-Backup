#include<stdio.h>
#include<conio.h>

int isDead(int HP){
	if(HP<=0)	return 0;
	else return HP;
}

int main(){
	int boneHP=50, snotHP=100, plagueHP=70;
	int ATK;
	int *current_monsterHP;
	char name;
	
	start:
	do{ printf("Enter the 1st character of Monster's name: ");
		name = getche();
		printf("\n");
	}while(name!='b' && name!='s' && name!='p');
	
	do{	printf("Enter the attack power from 1 to 100: ");
		scanf(" %d",&ATK);
	}while(ATK<1 || ATK>100);
	
	switch(name){
		case 'b':{
			current_monsterHP=&boneHP;
			printf("Current HP: %d", *current_monsterHP);
			/*boneHP=*current_monsterHP-ATK;
			boneHP=isDead(boneHP);*/
			break;
		}
		case 's':{
			current_monsterHP=&snotHP;
			printf("Current HP: %d", *current_monsterHP);
			/*snotHP=*current_monsterHP-ATK;
			snotHP=isDead(snotHP);*/
			break;
		}case 'p':{
			current_monsterHP=&plagueHP;
			printf("Current HP: %d", *current_monsterHP);
			/*plagueHP=*current_monsterHP-ATK;
			plagueHP=isDead(plagueHP);*/
			break;
		}
	}
	
	printf("\nAttack power: %d",ATK);
	
	*current_monsterHP-=ATK;
	if(*current_monsterHP<0) *current_monsterHP=0;
	printf("\nRemaining HP: %d\n", *current_monsterHP);
	
	goto start;
}
