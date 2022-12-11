#include<stdio.h>
#include<conio.h>
#include<string.h>
#include<time.h>
#include<stdlib.h>
#include<windows.h>

int main(int argc, char const *argv[])
{
    char id[8];
    char cleartable;
    int discount=0;
    int choice,people,seat,orderseat,food,drink,bills;
    int khao=0,rice=0,mhoo=0,tomyum=0;
    int coca=0,juice=0,still=0,d=0,f=0;
    int tableseat[10]={2,2,2,2,4,4,4,4,8,8};
    int occupied[10]={0,0,0,0,0,0,0,0,0,0};
    float price[10][10];
    int bill[10][7];
    float tablebill[10];
    char confirm,a,enter;
    for(int i=0;i<10;i++)
    {
        for(int n=0;n<7;n++)
        {
            bill[i][n]=0;
        }
        for(int k=0;k<10;k++)
        {
            price[i][k]=0;
        }
    }
    menu:{
    system("cls");
    Sleep(100);
    printf("=================================================================\n");
    printf("ICT Restuarant System\n");
    printf("=================================================================\n");
    Sleep(100);
    printf("[1] Book a table\n");
    Sleep(100);
    printf("[2] Order food & drink\n");
    Sleep(100);
    printf("[3] Display and clear bill\n");
    Sleep(100);
    printf("[0] Exit\n");
    printf("-----------------------------------------------------------------\n");
    printf("Enter your choice : ");
    scanf("%d",&choice);
    }

    switch(choice)
    {
        case 0:
        system("cls");
        printf("*********************THANK YOU************************");
        printf("\n\n\n");
        return 0;


        case 1:
        system("cls");
        printf("[Book a table]");
        printf("\n");
        printf("\n");
        printf("Enter  the nummber  of people : ");
        scanf("%d",&people);
        printf("\n");
        printf("\n");
        printf("=================================================================\n");
        printf("List of table\t\t\t\tStatus\n");
        printf("=================================================================\n");
        
        for(int i=0;i<10;i++)
        {
            Sleep(100);
            printf("Table%-2d:%-2d seat",i+1,tableseat[i]);
            printf("\t\t\t\t");
            if(occupied[i])
            {
                printf("Occupied");
            }
            else if (tableseat[i]<people)
            {
                printf("Not enough seat");
            }
            else{
                printf("Available");
            }
            
            
            printf("\n");
            
        }
        seat = -1;
        while(seat != 0)
        {
            printf("Enter a table number (input 0 to cancel) : ");
            scanf("%d",&seat);
            if(seat == 0)
                goto menu;
            else if (seat <1 || seat > 10)
                printf("Invalid choice\n");
            else if(occupied[seat - 1])
                printf("Please Enter the available table.\n");
            else if(tableseat[seat-1]<people)
                printf("Sorry Not enough seat.Please Enter another table.\n");
            else
                {
                    occupied[seat - 1] = 1;
                    goto menu;
                }
        }
        
        
        
        
    
    case 2:
    order:{
        system("cls");
        printf(" [Order food & drink]");
        printf("\n\n");
        printf("List of occupied table:\n");
        for(int i=0;i<10;i++)
        {
            Sleep(100);
            if(occupied[i]){
                printf("Table %d",i+1);
                printf("\n");
            }
            
        }
        printf("\n\n");
        printf("Enter the table number [1-10] (0 to exit) :");
        scanf("%d",&orderseat);
        if(orderseat == 0)
        {
            goto menu;
        }
        if(occupied[orderseat-1] != 1)
        {
            printf("There is no one sitting at the table\n");
            printf("Press 'Enter' to go back to main menu.");
            enter = 0;
            getchar();
	        while(enter != '\n')
                {
	                enter = getchar();
	            }
                goto menu;
        
            
            

            
            
        
            
        }
        if(occupied[orderseat-1]== 1)
        {
            ordered:{
        printf("=================================================================\n");
        printf("Order food & drink\n");
        printf("=================================================================\n");
        printf("\n\n");
        printf("Food Menu\t\t\tPrice (Baht)\n");
        printf("-----------------------------------------------------------------\n");
        Sleep(100);
        printf("[1] Kao Pad Kra Pao\t\t 45.0\n");
        Sleep(100);
        printf("[2] Fried Rice\t\t\t 45.0\n");
        Sleep(100);
        printf("[3] Kha Nar Mhoo Krob\t\t 50.0\n");
        Sleep(100);
        printf("[4] Tom yum Koong Nam Khon\t 60.0\n");
        Sleep(100);
        printf("-----------------------------------------------------------------\n");
        printf("Enter the choice (input 0 to stop) :");
        while(scanf("%d",&food) != 0)
        {
            if(food == 1)
                khao=khao+1; 
            if(food == 2)
                rice=rice+1;
            if(food == 3)
                mhoo=mhoo+1;
            if(food == 4)
                tomyum=tomyum+1;  
            if(food >4)
            {
                printf("Invalid Menu Choice!\n");
            }
            if(food == 0)
            {
                break;
            }
            printf("Enter the choice (input 0 to stop) :");
        }
        printf("\n\n");
        printf("Drink Menu\t\t\tPrice (Baht)\n");
        printf("-----------------------------------------------------------------\n");
        Sleep(100);
        printf("[1] Coca Cola\t\t\t 20.0\n");
        Sleep(100);
        printf("[2] Orange Juice\t\t 30.0\n");
        Sleep(100);
        printf("[3] Still Water\t\t\t 10.0\n");
        Sleep(100);
        printf("-----------------------------------------------------------------\n");
        printf("Enter the choice (input 0 to stop) :");
        while(scanf("%d",&drink) != 0)
        {
            if(drink == 1)
                coca=coca+1; 
            if(drink == 2)
                juice=juice+1;
            if(drink == 3)
                still=still+1;
            if(drink > 3)
            {
                printf("Invalid Menu Choice!\n");
            }
            
            if(drink == 0)
            {
                break;
            }
            printf("Enter the choice (input 0 to stop) :");
        
        }
            
       
         
         
            }
        
        printf("\n\n");
        printf("You have order the following : \n");
        if(khao >0)
        {
            bill[orderseat-1][0] = khao;
            Sleep(100);
            printf("[F] Kao Pad Kra Pao x%d\n",khao); 
            khao = 0;
        }
        if(rice >0)
        {
            bill[orderseat-1][1] = rice;
            Sleep(100);
            printf("[F] Fried Rice x%d\n",rice);
            rice = 0;
        }
        if(mhoo >0)
        {
            bill[orderseat-1][2] = mhoo;
            Sleep(100);
            printf("[F] Kha Nar Mhoo Krob x%d\n",mhoo);
            mhoo = 0;
        }
        if(tomyum >0)
        {
            bill[orderseat-1][3] = tomyum;
            Sleep(100);
            printf("[F] Tom Yum Koong Nam Khon x%d\n",tomyum);
            tomyum = 0;
        }
        if(coca > 0)
        {
            bill[orderseat-1][4] = coca;
            Sleep(100);
            printf("[D] Coca Cola x%d\n",coca);
            coca = 0; 
        }
        if(juice > 0)
        {
            bill[orderseat-1][5] = juice;
            Sleep(100);
            printf("[D] Orange Juice x%d\n",juice);
            juice = 0;
        }
        if(still > 0)
        {
            bill[orderseat-1][6] = still;
            Sleep(100);
            printf("[D] Still Water x%d\n",still);
            still = 0;
        }  
        
        
        printf("Confirm? (y|n) : ");
        scanf(" %c",&confirm);
        if(confirm == 'y')
            goto menu;
        if(confirm == 'n')
        {
            for(int i=0;i<7;i++)
            {
                bill[orderseat][i]=0;
            }
            goto ordered;
        }
            
        }}

        case 3:
        system("cls");
        printf("[Display and clear a bill]\n\n");
        printf("list of occupied table : \n\n");
        for(int i=0;i<10;i++)
        {
            Sleep(100);
            if(occupied[i]){
                printf("Table %d",i+1);
                printf("\n");
            }
            
        }
        printf("Enter the table number [1-10](0 to exit) :");
        scanf("%d",&bills);
        if(occupied[bills-1] != 1)
        {
            printf("There is no one sitting at the table\n");
            printf("Press 'Enter' to go back to main menu.");
            enter = 0;
            getchar();
	        while(enter != '\n')
                {
	                enter = getchar();
	            }
                goto menu;
        }
        for(int i=0;i<4;i++)
        {
            if(bill[bills-1][i] > 0)
            {
                f++;
            }
        }  
        for(int i=4;i<7;i++)
        {
            if(bill[bills-1][i] > 0)
            {
                d++;
            }
        }   
        if(d > 0 || f > 0)
        {       
        printf("=============================================================================\n");
        printf("Enter ID to get discount coupon Press [n] IF you are not MUICT#16 student)\n");
        ID:{
        scanf("%s",&id);
        if(strstr(id,"6188"))
        {
            for(int i=0;i<=38;i++)
            {
                Sleep(50);
                printf(". ");
                if(i == 38)
                {
                    system("cls");
                }
            }
            discount=1;
            printf("You got 10%% discount!\n");
        }
        else if(strstr(id,"n"))
        {
            system("cls");
        }
        else
        {
            for(int i=0;i<=38;i++)
            {
                Sleep(50);
                printf(". ");
                if(i == 38)
                {
                    system("cls");
                }
            }
            printf("Invalid ID\n");
            goto ID;
        }
        }
        }
        printf("Food menu \t\t\t Qty.\tPrice(Baht)\n");
        printf("-----------------------------------------------------------------\n");
           if(f >0)
           {
            if(bill[bills-1][0]!=0)
                {
                    Sleep(100);
                    printf("[1] Kao Pad Kra Pao \t\t %d\t%.1f\n",bill[bills-1][0],bill[bills-1][0]*45.0);
                    price[bills-1][0]=price[bills-1][0]+(bill[bills-1][0]*45.0);
                }
            if(bill[bills-1][1]!=0)
                {
                    Sleep(100);
                    printf("[2] Fried Rice \t\t\t %d\t%.1f\n",bill[bills-1][1],bill[bills-1][1]*45.0);
                    price[bills-1][0]=price[bills-1][0]+(bill[bills-1][1]*45.0);
                }
            if(bill[bills-1][2]!=0)
                {
                    Sleep(100);
                    printf("[3] Kha Nar Mhoo Krob \t\t %d\t%.1f\n",bill[bills-1][2],bill[bills-1][2]*50.0);
                    price[bills-1][0]=price[bills-1][0]+(bill[bills-1][2]*50.0);
                }
            if(bill[bills-1][3]!=0)
                {
                    Sleep(100);
                    printf("[4] Tom Yum Koong Nam Khon \t %d\t%.1f\n",bill[bills-1][3],bill[bills-1][3]*60.0);
                    price[bills-1][0]=price[bills-1][0]+(bill[bills-1][3]*60.0);
                }
           }
            else
                {
                    printf("None\n");
                }
        printf("-----------------------------------------------------------------\n");
        printf("\n\n");
        printf("Drink Menu \t\t\t Qty.\tPrice(Baht)\n");
        printf("-----------------------------------------------------------------\n");
        if(d > 0)
        {
            if(bill[bills-1][4] !=0)
                {
                    Sleep(100);
                    printf("[1] Coca Cola \t\t\t %d\t%.1f\n",bill[bills-1][4],bill[bills-1][4]*20.0);
                    price[bills-1][0]=price[bills-1][0]+(bill[bills-1][4]*20.0);
                }
            if(bill[bills-1][5]!=0)
                {
                    Sleep(100);
                    printf("[2] Orange Juice \t\t %d\t%.1f\n",bill[bills-1][5],bill[bills-1][5]*30.0);
                    price[bills-1][0]=price[bills-1][0]+(bill[bills-1][5]*30.0);
                }
            if(bill[bills-1][6]!=0)
                {
                    Sleep(100);
                    printf("[3] Still Water \t\t %d\t%.1f\n",bill[bills-1][6],bill[bills-1][6]*10.0);
                    price[bills-1][0]=price[bills-1][0]+(bill[bills-1][6]*10.0);
                }
        }
        else
            {
                printf("None\n");
            }
        printf("-----------------------------------------------------------------\n");
        printf("\n\n");
        if(discount == 1)
        {
            Sleep(100);
            printf("** Total amount: %.2f - %.2f(Discount) = %.2f\n",price[bills-1][0],(price[bills-1][0]*10)/100,price[bills-1][0]-(price[bills-1][0]*10)/100);
        }
        else
        {
            Sleep(100);
            printf("** Total amount: %.2f\n",price[bills-1][0]);
        }

        printf("Do you want to clear bill (y/n) :");
        while(scanf(" %c",&cleartable)!='a')
        {
        if(cleartable == 'y')
        {
            printf("Please Wait\n");
            for(int i=0;i<=38;i++)
            {
                Sleep(50);
                printf(". ");
                if(i == 38)
                {
                    system("cls");
                }
            }
            printf("Complete!");
            Sleep(1000);
            occupied[bills-1]=0;
            price[bills-1][0]=0;
            for(int i=0;i<7;i++)
            {
                bill[bills-1][i]=0;
            }
            goto menu;
        }
        else if (cleartable == 'n')
        {
            goto menu;
        }
        else
        {
            printf("Invalid choice\n");
        }
        }



    }
    
    return 0;
    }


        
    
    
    


    

