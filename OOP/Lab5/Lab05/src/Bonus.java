//Thanyanit Jongjitragan 6188075
import java.util.Scanner;
public class Bonus
{
	public static void main(String[] args)
	{
		Scanner input = new Scanner(System.in);
		System.out.println("Enter a word or phrase to check if it is a palindrome: ");	String in = input.nextLine();
		String s = in.toUpperCase();
		String s1 = s.replaceAll("\\p{P}","");
		s1 = s1.replaceAll("\\p{Z}","");
		s1 = s1.replaceAll("\\p{S}","");
		String s2 = "";
		for(int i=s1.length()-1; i>=0;i--)
		{
			s2 += s1.charAt(i);
		}
		//System.out.println("S1: " + s1);
		//System.out.println("S2: " + s2);
		if(s1.equals(s2))
		{
			if(s1.length() == s.length())
				System.out.println("The input word is a palindrome");
			else
				System.out.println("The input phrase is a palindrome");
		}
		else
		{
			if(s1.length() == s.length())
				System.out.println("The input word is not a palindrome");
			else
				System.out.println("The input phrase is not a palindrome");
		}
	}
}
