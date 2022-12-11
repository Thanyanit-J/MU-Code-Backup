////////////////////////////////////
/// MONSTER HUNTER mini game 2.0 ///
////////////////////////////////////
#include<stdio.h>
#include<string.h>
int isEnd=0;
void endGame(){
	isEnd=1;
}

int main(){
	struct monster
	{
		char name[80];
		int attack;
		int hp;
	};
	
	
	/**//////// MONSTER INFO ////////**/
		struct monster monsters[]={
			{"bone",20,50},
			{"snot",30,100},
			{"plague",50,70}
		};
	/**//////////////////////////////**/
	
	int playerHP=100;
	int playerATK;
	
	struct monster *current_monster;
	
	char name[6];
	
	while(isEnd==0){
		//////GET INPUT//////
		int loop=1;
		while(loop){ printf("Enter the Monster's name: ");
			scanf("%s",&name);
			
			for(int i=0;i<3;i++){
				if(strcmp(name,monsters[i].name)==0){
					current_monster = &monsters[i];
					loop=0;
					break;
				}
			}
		}
		
		do{	printf("Enter the attack power from 1 to 100: ");
			scanf(" %d",&playerATK);
		}while(playerATK<1 || playerATK>100);
		
		//////BATTLE CALCULATION//////
		(*current_monster).hp -= playerATK;
		if((*current_monster).hp<0)
			(*current_monster).hp=0;
		printf("Monster: %s\n", (*current_monster).name);
		printf("HP: %d\n", (*current_monster).hp);
		
		if((*current_monster).hp>0)
			playerHP -=(*current_monster).attack;
		if(playerHP<0)
			playerHP=0;
		printf("Your HP: %d\n\n",playerHP);
		
		//////END GAME//////
		if(playerHP==0){
			printf("You lose");
			endGame();
		}
		int count=0;
		for(int i=0;i<3;i++){
			if(monsters[i].hp==0)
				count++;
			if(count==3){
				printf("You won");
				endGame();
			}
		}
	}
	
	return 0;
}


