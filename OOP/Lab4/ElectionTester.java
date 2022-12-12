public class ElectionTester {
	public static void main(String[] args) {
		MyDate election = new MyDate(2019, 3, 24);
		
		Person a = new Person("Lalisa", "Manoban", 1997, 3, 27);
		printPersonElectionInfo(a, election);
		
		Person b = new Person("Nuda", "Inter", 2012, 1, 16);
		printPersonElectionInfo(b, election);
		
		// Create another Person object with your information
		// Print your information, age, and election eligibility.
		
		/*
		 * YOUR CODE GOES HERE
		 */
		
		// Write a program to take 3 persons information from the user
		// (Hint: Use scanner and for loop to get input)
		
		/*
		 * YOUR CODE GOES HERE
		 */
		
		
		
		// Challenge Bonus
		// Instead of taking 3 persons' information, write a program to continuously take input from the user
		// until the user types 'q' to quite the program.
		
		/*
		 * YOUR CODE GOES HERE
		 */
		
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
