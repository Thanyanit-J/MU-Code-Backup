//Thanyanit Jongjitragan
//ID 6188075
import java.util.Scanner;
public class ElectionTester {
	public static void main(String[] args) {
		MyDate election = new MyDate(2019, 3, 24);
		
		Person a = new Person("Lalisa", "Manoban", 1997, 3, 27);
		printPersonElectionInfo(a, election);
		
		Person b = new Person("Nuda", "Inter", 2012, 1, 16);
		printPersonElectionInfo(b, election);
		
		// Create another Person object with your information
		// Print your information, age, and election eligibility.
		
		Person c = new Person("YourName", "YourLastname", 1998, 2, 1);
		printPersonElectionInfo(c, election);
		
		// Write a program to take 3 persons information from the user
		// (Hint: Use scanner and for loop to get input)
		/*for(int i=0;i<3;i++)
		{
			Scanner input = new Scanner(System.in);
			System.out.print("Enter firstname: "); String firstname = input.nextLine();
			System.out.print("Enter lastname: "); String lastname = input.nextLine();
			System.out.print("Enter year of birthday: "); int byear = input.nextInt();
			System.out.print("Enter month of birthday: "); int bmonth = input.nextInt();
			System.out.print("Enter day of birthday: "); int bday = input.nextInt();
			Person in = new Person(firstname, lastname, byear, bmonth,bday);
			printPersonElectionInfo(in, election);
		}/**/
		
		
		
		// Challenge Bonus
		// Instead of taking 3 persons' information, write a program to continuously take input from the user
		// until the user types 'q' to quite the program.
		
		for(int i=0;i>-1;i++)
		{
			Scanner input = new Scanner(System.in);
			System.out.print("Enter firstname or type 'q' to exit: ");
			String firstname = input.nextLine();
			if(firstname.equals("q")==false)
			{
				System.out.print("Enter lastname: "); String lastname = input.nextLine();
				System.out.print("Enter year of birthday: "); int byear = input.nextInt();
				System.out.print("Enter month of birthday: "); int bmonth = input.nextInt();
				System.out.print("Enter day of birthday: "); int bday = input.nextInt();
				Person in = new Person(firstname, lastname, byear, bmonth,bday);
				printPersonElectionInfo(in, election);
			}
			else
				i=-999;
		}
		
	}
	
	public static void printPersonElectionInfo(Person p, MyDate election) {
		p.printPersonInfo();
		System.out.println("Age: " + p.getAge(election));
		if(p.isEligible(election))
			System.out.println("This person is eligible to vote.");
		else
			System.out.println("This person is NOT eligible to vote");
		
		System.out.println("-----------------------------------");
	}
}
