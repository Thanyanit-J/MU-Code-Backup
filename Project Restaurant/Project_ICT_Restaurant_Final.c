#include<stdio.h>
#include<conio.h>
#include<string.h>
#include<time.h>
#include<stdlib.h>
#include<windows.h>

#define PROGRESS_BAR_SIZE 40
#define nolimit -1
#define yes 1
#define no 0

///////////////////////
// LIST OF FUNCTIONS //
///////////////////////

//PAGES of the program
void go_menu();
void go_bookTable(); //press 1
void go_orderFoodAndDrink(); //press 2
void go_displayAndClearBill();//press 3
float extra_IDForDiscount(float netPrice);//10% discount for MUICT student
void exit();

//OTHER FUNCTIONS
int isValidNumber(char input[100], int min, int max); //check if input is valid
int isNumber(char input[100]); //check if input is number: TRUE return 1, FALSE return 0
int isInRange(int input, int min, int max); //check if number is between min and max. Type "nolimit" if there is no min or max.
int isValidChar(char input[10], char *ptr);  //check if input is either 'y' or 'n' : TRUE return 1,   FALSE return 0
void reset_tableData(int num_table); //reset Variable of a table to default
void print_n_chars(int n, int c);	//Loading 
void display_progress_bar(int p);	//Bar

/////////////////////
// Global Variable //
/////////////////////
int randomFood; // Food REC
int randomDrink; // Drink REC
int Takehome_choice; //customer decision
int Takehome=no;  // if Takehome = 1 ,No need to book a table
int isEnd=no; //if isEnd = 1, END THE PROGRAM
int isNoOccupiedTable = yes; //In case there's no occupied table, the program will not show the table list and forced user to go back to main menu.

int total_table = 10;
int table_seat[]   = {2,2,2,2, 4,4,4,4, 8,8};
int isOccupied[]   = {0,0,0,0, 0,0,0,0, 0,0};
int isOrderFood[]  = {0,0,0,0, 0,0,0,0, 0,0};
int isOrderDrink[] = {0,0,0,0, 0,0,0,0, 0,0};

int total_food = 4;
char *food_name[] = {"Kao Pad Kra Pao","Fried Rice","Kha Nar Mhoo Krob","Tom Yum Koong Nam Khon"};
int food_order[4][10];
int food_order_tmp[] = {0,0,0,0};
int Takehome_food[4][10];
int Takehome_isOrder[10];
float food_price[] = {45.0,45.0,50.0,60.0};


int total_drink = 3;
char *drink_name[] = {"Coca Cola","Orange Juice","Still Water"};
int drink_order[3][10];
int Takehome_drink[3][10];
int drink_order_tmp[] = {0,0,0};
float drink_price[] = {20.0,30.0,10.0};


////////////////
//*** MAIN ***//
////////////////

int main(){
	system("cls");
	//reset everything to default
	srand(time(NULL));
	randomFood = rand() % 4;
	randomDrink = rand() % 3;
	char welcome[27]={'W','E','L','C','O','M','E','!','T','O',' ','M','U','I','C','T',' ','R','E','S','T','U','A','R','A','N','T'};
	char shutdown[33]={'P','R','O','G','R','A','M',' ','I','S',' ','S','H','U','T','I','N','G','D','O','W','N','.','.','.','.','.','.','.','.','.','.','.'};
	for(int i=0;i<27;i++)
	{
		Sleep(100);
		printf("%c",welcome[i]);
	}
	Sleep(1000);
	for(int i=0;i<=100;i++)
	{
		display_progress_bar(i);
		Sleep(20);
	}
	int num_table;
	for(num_table = 1; num_table < total_table; num_table++){
		reset_tableData(num_table);
	}
	
	while(isEnd == 0){ //while program is NOT end, go_menu.
		go_menu();
	}
}
void exit_Over()
{	/// PROGRAM IS END ///
	char shutdown[33]={'P','R','O','G','R','A','M',' ','I','S',' ','S','H','U','T','I','N','G','D','O','W','N','.','.','.','.','.','.','.','.','.','.','.'};
	system("cls");
	printf("\n");
	printf("******************************************************\n");
    printf("****************     THANK YOU     *******************\n");
	printf("******************************************************\n");
    printf("\n\n");
	Sleep(3000);
	for(int i=0;i<33;i++)
	{
		if(i >= 22 )
		{
			Sleep(500);
		}
		else
		{
			Sleep(50);
		}
		printf("%c",shutdown[i]);
	}
	system("cls");
	exit(0);
}


///////////
// PAGES //
///////////

void go_menu(){
	char choice_tmp[5];
	int choice;
	int isFirstInput = yes;
	
	do{
		system("cls");
	    printf("=================================================================\n");
		Sleep(100);
	    printf(" ICT Restaurant System\n");
	    printf("=================================================================\n");
		Sleep(100);
	    printf("[1] Book a table\n");
		Sleep(100);
	    printf("[2] Order food & drink\n");
		Sleep(100);
	    printf("[3] Display and clear bill\n");
		Sleep(100);
		printf("[4] Take Home\n");
		Sleep(100);
	    printf("[0] Exit\n");
	    printf("-----------------------------------------------------------------\n");
	    if(isFirstInput == no) printf("Invalid Choice!\n");
	    printf("Enter your choice : ");
	    
	    gets(choice_tmp);
	    isFirstInput = no;
	}while(isValidNumber(choice_tmp,0,4)==no);
	
	choice = atoi(choice_tmp);

    
    switch(choice){
    	case 1: go_bookTable(); 						break;
    	case 2: go_orderFoodAndDrink();					break;
    	case 3: go_displayAndClearBill();				break;
		case 4: Takehome = yes; go_orderFoodAndDrink(); break;
    	case 0: exit_Over();  /*END PROGRAM*/			break;
	}
}
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////
void go_bookTable(){
	//This is devided into 2 parts: show a list of tables, book a table
	
	/*SHOW A LIST OF TABLES*/
	char total_people_tmp[10];
	int total_people;
	int isFirstInput = yes;
	char occupied_art[8]={'O','c','c','u','p','i','e','d'};
	char available_art[9]={'A','v','a','i','l','a','b','l','e'};
	char seatOver_art[15]={'N','o','t',' ','E','n','o','u','g','h',' ','S','e','a','t'};
	do{
		system("cls");
	    printf("[Book a table]");
	    printf("\n");
	    printf("\n");
	    if(isFirstInput == no) printf("Invalid Choice!\n");
		printf("Enter the number of people (input 0 to cancel) : ");
	    gets(total_people_tmp);
	    isFirstInput = no;
	    if(isValidNumber(total_people_tmp,0,nolimit)){
			total_people = atoi(total_people_tmp);
	    	if(total_people==0) 
				return;
	    	else
				break;
		}
	}while(1);
	printf("Checking for Table");
	 for(int i = 0;i<10;i++)
	 {
		Sleep(500);
		printf(". ");
	 }
	
    printf("\n");
    printf("\n");
    printf("=================================================================\n");
    printf(" List of tables\t\t\t\tStatus\n");
    printf("=================================================================\n");
    
    int i;
    for(i=0;i < total_table;i++)
    {
        printf(" Table%3d: %-2d seats",i+1,table_seat[i]);
        printf("\t\t\t");
                        
        if(isOccupied[i]){
            for(int i=0;i<8;i++)
			{
				Sleep(50);
				printf("%c",occupied_art[i]);
			}
        }
        else if (table_seat[i] < total_people){
			for(int i=0;i<15;i++)
			{
				Sleep(50);
				printf("%c",seatOver_art[i]);
			}
        }
        else{
            for(int i=0;i<9;i++)
			{
				Sleep(50);
				printf("%c",available_art[i]);
			}
        }
		printf("\n");
    }
    
    /*BOOK A TABLE*/
    char book_table_tmp[100];
    int book_table = 0;
    int done_booking = no;
    isFirstInput = yes;
    
    while(1){
		do{
	    	if(isFirstInput == no) printf("Invalid Choice!\n");
	    	printf("Enter a table number (input 0 to cancel) : ");
	    	gets(book_table_tmp);
	    	isFirstInput = no;
		}while(isValidNumber(book_table_tmp,0,total_table)==no);
		
		isFirstInput = yes;
		book_table = atoi(book_table_tmp);
		
		if(book_table!=0){
			if(isOccupied[book_table-1])
				printf("Please enter the available table.\n");
			else if(table_seat[book_table-1] < total_people)
				printf("Sorry. There is not enough seat. Please enter the available table.\n");
			else{
				isOccupied[book_table-1] = yes;
				isNoOccupiedTable = no;
				printf("Booking");
				for(int i=0;i<4;i++)
				{
					Sleep(900);
					printf(". ");
				}
				printf("Complete!");
				Sleep(500);
				go_menu(); //DONE BOOKING AND BACK TO MENU
			}
		}
		else{
			go_menu(); //BACK TO MENU
		}
	}
}
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////
void go_orderFoodAndDrink(){
	//This is divided into 4 parts: select table, order food, order drink, confirm order
	char num_table_tmp[100];
	int num_table;
	int isOrderFood_tmp, isOrderDrink_tmp;
	int isFirstInput = yes;
	char Takehome_choice_tmp[5];
	int Takehome_choice;
	for(int i=0;i<total_table;i++)
	{
		if(isOccupied[i] == 1)
		{
			break;
		}

	}
	//Select Table
	do{
		system("cls");
		printf("[Order food & drink]");
		printf("\n\n");
		if(Takehome == no)
		{
			for(int i=0;i<total_table;i++){
        		if(isOccupied[i]!=0){
        		isNoOccupiedTable = no;
				}
			}
			if(isNoOccupiedTable==yes){ //if no table is occupied yet, return to menu
				printf("There is no one sitting at the table\n");
        		printf("Press Enter to go back to main menu.");  
        		getch();
        		go_menu();
			}
		}
		printf("List of occupied table:\n");
		int i;
		for(i=0; i<total_table; i++){
		    if(isOccupied[i]){
		        printf("Table %d",i+1);
		        printf("\n");
		        isNoOccupiedTable=no;
		    }      
		}
		if(Takehome == yes)
		{
			printf("Takehome Order");
		}
		printf("\n\n");
		if(isFirstInput==no) printf("Invalid Table Number!\n");
		printf("Enter the table number [1-%d][11 for Takehome Order] (0 to exit): ",total_table);
		gets(num_table_tmp);
		isFirstInput=no;
		
		if(isValidNumber(num_table_tmp,0,total_table+1)){
			num_table = atoi(num_table_tmp);
			if(num_table==0) go_menu();
			if(isOccupied[num_table-1]){
				break;
			}
			if(Takehome == yes && num_table  ==11)
			{
				break;
			}
		}
	}while(1);
    
	//Order Food
	char food_choice_tmp[100];
	int food_choice;
    printf("=================================================================\n");
    printf("Order food & drink\n");
    printf("=================================================================\n");
    printf("\n\n");
    printf("Food Menu\t\t\tPrice (Baht)\n");
    printf("-----------------------------------------------------------------\n");
    printf("[1] Kao Pad Kra Pao\t\t 45.0\n");
    printf("[2] Fried Rice\t\t\t 45.0\n");
    printf("[3] Khao Nar Mhoo Krob\t\t 50.0\n");
    printf("[4] Tom Yum Koong Nam Khon\t 60.0\n");
    printf("-----------------------------------------------------------------\n");
	printf("=================================================================\n");
	printf("Recommended : %s\n",food_name[randomFood]);
	printf("=================================================================\n");
	food_order_tmp[0]=0;
    do{
    	printf("Enter the choice (input 0 to stop): ");
		gets(food_choice_tmp);
		if(isValidNumber(food_choice_tmp,0,total_food)){
			food_choice = atoi(food_choice_tmp);
			if(food_choice==0)
				break;
			food_order_tmp[food_choice-1] += 1;
			isOrderFood_tmp = 1;
		}
		else
			printf("Invalid Choice!\n");
	}while(1);
    
    //Order Drink
    char drink_choice_tmp[100];
	int drink_choice;
	
	printf("\n\n");
    printf("Drink Menu\t\t\tPrice (Baht)\n");
    printf("-----------------------------------------------------------------\n");
    printf("[1] Coca Cola\t\t\t 20.0\n");
    printf("[2] Orange Juice\t\t 30.0\n");
    printf("[3] Still Water\t\t\t 10.0\n");
    printf("-----------------------------------------------------------------\n");
	printf("=================================================================\n");
	printf("Recommended : %s\n",drink_name[randomDrink]);
	printf("=================================================================\n");
    do{
    	printf("Enter the choice (input 0 to stop): ");
		gets(drink_choice_tmp);
		if(isValidNumber(drink_choice_tmp,0,total_drink)){
			drink_choice = atoi(drink_choice_tmp);
			if(drink_choice==0)
				break;
			drink_order_tmp[drink_choice-1] += 1;
			isOrderDrink_tmp=1;
		}
		else
			printf("Invalid Choice!\n");
	}while(1);
	
	printf("\n\n");
    printf("You have order the following : \n");
    int i;
	for(i=0;i<total_food;i++){
		if(food_order_tmp[i] >0)
            printf("[F] %s x%d\n",food_name[i], food_order_tmp[i]);
	}
	for(i=0;i<total_drink;i++){
		if(drink_order_tmp[i] >0)
            printf("[D] %s x%d\n",drink_name[i], drink_order_tmp[i]);
	}
	if(isOrderFood_tmp==0 && isOrderDrink_tmp==0)
		printf("None.\n");
	
	//Confirm Order
	char confirmOrder_tmp[10];
	char confirmOrder;
	do{
		printf("Confirm? (y|n): ");
		gets(confirmOrder_tmp);
		if(isValidChar(confirmOrder_tmp,&confirmOrder)){
			
			for(i=0;i<total_food+1;i++){
				if(confirmOrder=='y'){
					if(Takehome == yes && num_table == 11)
					{
						Takehome_food[1][i] += food_order_tmp[i];
					}
					else
					{
						food_order[num_table-1][i]+=food_order_tmp[i];
					}
					if(isOrderFood[num_table-1]==0)
						isOrderFood[num_table-1]=isOrderFood_tmp;
				}
				food_order_tmp[i]=0;
			}
			for(i=0;i<total_drink+1;i++){
				if(confirmOrder=='y'){
					if(Takehome == yes && num_table == 11)
					{
						Takehome_drink[1][i] += drink_order_tmp[i];
					}
					else
					{
						drink_order[num_table-1][i]+=drink_order_tmp[i];
					}
					if(isOrderDrink[num_table-1]==0)
						isOrderDrink[num_table-1]=isOrderDrink_tmp;
				}
					
				drink_order_tmp[i]=0;
			}
			printf("\n\nSending Order to Chef");
			for(int i=0;i<5;i++)
			{
				Sleep(300);
				printf(". ");
			}
			printf("Complete!");
			Sleep(500);
			go_menu();
		}
	}while(1);
}
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////
void go_displayAndClearBill(){
	char num_table_tmp[100];
	int num_table;
	float netPrice=0;
	char confirm_tmp[100];
	char confirm;
	int isFirstInput = yes;
	
	do{
		system("cls");
        printf("[Display and clear a bill]\n\n");
        
        for(int i=0;i<total_table;i++){
        	if(isOccupied[i]!=0){
        		isNoOccupiedTable = no;
			}
		}
        
        if(isNoOccupiedTable==yes && Takehome == no){ //if no table is occupied yet, return to menu
			printf("There is no one sitting at the table\n");
        	printf("Press Enter to go back to main menu.");  
			getch();
        	go_menu();
		}
		printf("List of occupied table:\n");
		int i;
		for(i=0; i<total_table; i++){
		    if(isOccupied[i]){
		        printf("Table %d",i+1);
		        printf("\n");
		    }
		}
			if(Takehome == yes)
			{
				printf("\n");
				printf("Takehome Ordered\n");
				printf("\n");
			}
		  
		
        if(isFirstInput==no) printf("Invalid Table Number!\n");
		printf("Enter the table number [1-%d][11 For Takehome Ordered] (0 to exit): ",total_table);
		gets(num_table_tmp);
		isFirstInput=no;
		
        if(isValidNumber(num_table_tmp,0,total_table+1)){
			num_table = atoi(num_table_tmp);
			if(num_table==0) go_menu();
			else if(isOccupied[num_table-1]){
				break;
			}
			else if (num_table == 11 && Takehome == yes)
			{
				break;
			}
		}
		
	}while(1);
	
	//if there's no order.
	if(Takehome == yes)
	{
		
	}
	else if(isOrderFood[num_table-1]==0 && isOrderDrink[num_table-1]==0){//if no food or drink is ordered yet, return to menu
		printf("\nThere is no any ordered food or drink.\n");
        printf("Press Enter to go back to main menu.");  
        getch();
        go_menu();
	}
	
	printf("\nYou have order the following: \n");
	printf("Food menu \t\t\t Qty.\tPrice(Baht)\n");
    printf("-----------------------------------------------------------------\n");
    int i;
	for(i=0;i<total_food;i++){
    	if(food_order[num_table-1][i]!=0)
        {
        	switch(i+1){
        		case 2: printf("[%d] %s \t\t\t %d\t%.1f\n",i+1,food_name[i],food_order[num_table-1][i],food_price[i]); break;
        		case 4: printf("[%d] %s \t %d\t%.1f\n",i+1,food_name[i],food_order[num_table-1][i],food_price[i]); break;
        		default: printf("[%d] %s \t\t %d\t%.1f\n",i+1,food_name[i],food_order[num_table-1][i],food_price[i]);
			}
			
        	netPrice += food_order[num_table-1][i]*food_price[i];
        }
		if(Takehome_food[1][i] !=0 && num_table == 11)
		{
			switch(i+1)
			{
				case 2: printf("[%d] %s \t\t\t %d\t%.1f\n",i+1,food_name[i],Takehome_food[1][i],food_price[i]); break;
        		case 4: printf("[%d] %s \t %d\t%.1f\n",i+1,food_name[i],Takehome_food[1][i],food_price[i]); break;
        		default: printf("[%d] %s \t\t %d\t%.1f\n",i+1,food_name[i],Takehome_food[1][i],food_price[i]);
			}
			netPrice += Takehome_food[1][i]*food_price[i];
		}
	}
	if(Takehome == yes && num_table == 11)
	{
		printf(" ");
	}
	else if(isOrderFood[num_table-1]==no) printf("None\n");
	printf("-----------------------------------------------------------------\n");
	printf("\n");
	printf("Drink menu \t\t\t Qty.\tPrice(Baht)\n");
    printf("-----------------------------------------------------------------\n");
    for(i=0;i<total_drink;i++){
    	if(drink_order[num_table-1][i]!=0)
        {
        	switch(i+1){
        		case 1: printf("[%d] %s \t\t\t %d\t%.1f\n",i+1,drink_name[i],drink_order[num_table-1][i],drink_price[i]); break;
        		default: printf("[%d] %s \t\t %d\t%.1f\n",i+1,drink_name[i],drink_order[num_table-1][i],drink_price[i]);
			}
			
        	netPrice += drink_order[num_table-1][i]*drink_price[i];
        }
		if(Takehome_drink[1][i] !=0 && num_table ==11 )
		{
			switch(i+1){
        		case 1: printf("[%d] %s \t\t\t %d\t%.1f\n",i+1,drink_name[i],Takehome_drink[1][i],drink_price[i]); break;
        		default: printf("[%d] %s \t\t %d\t%.1f\n",i+1,drink_name[i],Takehome_drink[1][i],drink_price[i]);
			}
			netPrice += Takehome_drink[1][i]*drink_price[i];
		}
	}
	if(Takehome == yes && num_table == 11)
	{
		printf(" ");
	}
	else if(isOrderDrink[num_table-1]==no) printf("None\n");
	printf("-----------------------------------------------------------------\n");
	printf("\n");
	
	float discount = extra_IDForDiscount(netPrice);
	printf("Checking your bill");
	for(int i=0;i<10;i++)
	{
		Sleep(100);
		printf(". ");
	}
	printf("\n\n");
	if(discount){
		printf("** Total amount: %.2f - %.2f(Discount) = %.2f \n\n",netPrice,discount,netPrice-discount);
	}
	else
    	printf("** Total amount: %.2f\n\n",netPrice);
    do{
    	printf("Do you want to clear the bill (y/n): ");
    	gets(confirm_tmp);
		if(isValidChar(confirm_tmp,&confirm)){
			if(confirm=='y'){
				printf("Please Wait\n");
	            for(i=0;i<=38;i++)
	            {
	                Sleep(50);
	                printf(". ");
	                if(i == 38){
	                    system("cls");
	                }
	            }
	            printf("Complete!");
	            Sleep(1000);
	            reset_tableData(num_table);
				if(num_table == 11)
				{
					
					for(i=0;i<total_food;i++)
						{
							Takehome_food[1][i]=0;
						}
    				for(i=0;i<total_drink;i++)
						{
							Takehome_drink[1][i]=0;
						}
				}
				Takehome = no;
			}
			go_menu();
		}
	}while(1);
	
}
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////
float extra_IDForDiscount(float netPrice){
	char ID_tmp[100];
	int ID;
	int count_num=0;
    printf("Enter MUICT#16 Student ID for a discount!\n");
    printf("Press [0] If you are not MUICT#16 student)\n");
    printf(">>> ");
	gets(ID_tmp);
    if(isValidNumber(ID_tmp,0,nolimit)){
		int i;
		for(i=0;i<strlen(ID_tmp);i++){
			if(ID_tmp[i]!=' ') count_num++;
		}
		if(count_num==7){
			ID = atoi(ID_tmp);
			if(ID>=6188000 && ID<6189000){
				printf("\nThe discount is successfully applied.\n");
				return netPrice/10;
			}
		}
	}
	return 0;
}

/////////////////////
// OTHER FUNCTIONS //
/////////////////////

int isValidNumber(char input[100], int min, int max){ //check if input is NUMBER and IN RANGE
	if(isNumber(input)){		
	   	if(isInRange(atoi(input),min,max))
	   		return yes;
	}
	return no;
}

int isNumber(char input[100]){ //check if input is NUMBER
	int containSpaceOnly = yes; //if input is space only, return 0
	//Check every index.
	int i;
	for(i = 0; i < strlen(input); i++) 
	{
		if(input[i]!=' ')
			containSpaceOnly = no;
		//If the index is NOT 0-9 and ' ', then return 0
		if(input[i]!='0' && input[i]!='1' && input[i]!='2' && input[i]!='3' && input[i]!='4' && input[i]!='5' && input[i]!='6' && input[i]!='7' && input[i]!='8' && input[i]!='9' && input[i]!=' ')
			return no;
	}
	
	if(containSpaceOnly == yes)
		return no;
	
	return yes;
}

int isInRange(int input, int min, int max){ //check if input is between min and max
	if(min == nolimit && max == nolimit){
		return yes;
	}
	if(min == nolimit){
		if(input > max)
			return no;
		return yes;
	}
	if(max == nolimit){
		if(input < min)
			return no;
		return yes;
	}
	if(input < min || input > max){
		return no;
	}
	return yes;
	
}

int isValidChar(char input[10], char *ptr){ //check if input is either 'y' or 'n', also assign 'y' or 'n' via pointer
	int count_n=0;
	int count_y=0;
	
	int i;
	for(i=0;i<strlen(input);i++){ //check if input if either 'y' or 'n'
		
		//If the index is NOT 'y','Y','n','N' and ' ', then return 0
		if(input[i]=='y' || input[i]=='Y'){
			count_y++;
		}
		else if(input[i]=='n' || input[i]=='N'){
			count_n++;
		}
		else if(input[i]!=' ')
			return no;
	}
	
	if(count_y>1 || count_n>1 || count_y==count_n)
		return no;
	
	if(count_y==1) *ptr='y';
	else *ptr='n';
	return yes;
}

void reset_tableData(int num_table){ //reset Variable of a table to default
	isOccupied[num_table-1]=0;
    isOrderFood[num_table-1]=0;
    isOrderDrink[num_table-1]=0;
    int i;
	for(i=0;i<total_food;i++)
		{
    		food_order[num_table-1][i]=0;
		}
    for(i=0;i<total_drink;i++)
		{
    		drink_order[num_table-1][i]=0;
		}
}

void print_n_chars(int n, int c) {
    while (n-- > 0) putchar(c);
}

void display_progress_bar(int p) {
    putchar('\r');
    putchar('[');
    print_n_chars(PROGRESS_BAR_SIZE * p / 100, '|');
    print_n_chars(PROGRESS_BAR_SIZE - PROGRESS_BAR_SIZE * p / 100, ' ');
    putchar(']');
    printf("	Loading. ");
}
