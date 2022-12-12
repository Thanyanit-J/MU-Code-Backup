//Thanyanit Jongjitragan
//ID 6188075
public class Person
{
	String firstname;
	String lastname;
	MyDate birthday = new MyDate();
	
	/////////////////////////////////////////////////////////////////////////
	//Constructors
	public Person(String aFirstname, String aLastname)
	{
		firstname = aFirstname;
		lastname = aLastname;
		

		birthday = new MyDate();
	}
	
	public Person(String aFirstname, String aLastname, int aYear, int aMonth, int aDay)
	{
		firstname = aFirstname;
		lastname = aLastname;
		
		birthday = new MyDate(aYear,aMonth,aDay);
	}
	
	/////////////////////////////////////////////////////////////////////////
	//Instance Methods
	public int getAge(MyDate aDate)
	{
		return MyDate.yearDiff(this.birthday,aDate);
	}
	
	public boolean isEligible(MyDate elecDate)
	{
		if(MyDate.yearDiff(this.birthday,elecDate)>=18)
			return true;
		else
			return false;
	}
	void printPersonInfo()
	{
		System.out.println("Person: " +firstname +"  " +lastname);
		System.out.println("Birthday: " +birthday.toString());
	}
	
}
