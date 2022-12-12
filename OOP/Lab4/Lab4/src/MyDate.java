//Thanyanit Jongjitragan
//ID 6188075
public class MyDate {

	int year;
	int month;
	int day;
	int objectNumber;
	
	static int objectCounter = 0;
	static String[] strMonths = {"January","February","March","April","May","June","July","August","September","October","November","December"};
	
	/////////////////////////////////////////////////////////////////////////
	//Constructors
	
	public MyDate() {
		year = 1900;
		month = 1;
		day = 1;
		
		objectNumber = ++objectCounter;
	}
	
	public MyDate(int aYear, int aMonth, int aDay) {
		year = aYear;
		month = aMonth;
		day = aDay;
		
		objectNumber = ++objectCounter;
	}

	public int getObjectNumber() {
		
		return objectNumber;
	}
	
	public void setDate(int aYear, int aMonth, int aDay) {
		year = aYear;
		month = aMonth;
		day = aDay;
		
	}
	
	/////////////////////////////////////////////////////////////////////////
	//Instance Methods
	
	public void setYear(int aYear) {
		this.year = aYear;
	}

	public void setMonth(int aMonth) {
		this.month = aMonth;
	}

	public void setDay(int aDay) {
		this.day = aDay;
	}

	public int getYear() {
		return year;
	}

	public int getMonth() {
		return month;
	}

	public int getDay() {
		return day;
	}

	public String toString() {
		return day+" "+strMonths[month-1]+" "+year;
	}
	
	public MyDate nextDay() {
		if(month==12 && day==31)
		{
			year+=1;
			month=1;
			day=1;
		}
		else 
		{
			if(month==4 || month==6 || month==9 || month==11 ) 
			{
				if(day==30)
				{
					month+=1;
					day=1;
				}
				else
					day+=1;			
			}
			else if(month!=2)
			{
				if(day==31) 
				{
					month+=1;
					day=1;
				}
				else
					day+=1;
			}
			else 
			{
				if(isLeapYear(year)==true && day==29) 
				{
					month+=1;
					day=1;
				}
				else if(isLeapYear(year)==false && day==28)
				{
					month+=1;
					day=1;
				}
				else
					day+=1;
			}
		}
			
		return this;
	}

	public MyDate nextMonth() {
		if(month==12)
		{
			month=1;
			year+=1;
		}
		else if((month==3||month==5||month==8||month==10)&&day==31)
		{
			month=1;
			day=30;
		}
		else
		{
			month+=1;
		}
		return this;
	}

	public MyDate nextYear() {
		if(isLeapYear(year)==true && month==2 && day==29)
		{
			year+=1;
			day=28;
		}
		else
			year+=1;
		return this;
	}
	
	public MyDate previousDay() {
		if(month==1 && day==1)
		{
			year-=1;
			month=12;
			day=31;
		}
		else 
		{
			if(month==5 || month==7 || month==10 || month==12 ) 
			{
				if(day==1)
				{
					month-=1;
					day=30;
				}
				else
					day-=1;			
			}
			else if(month!=3)
			{
				if(day==1) 
				{
					month-=1;
					day=31;
				}
				else
					day-=1;
			}
			else 
			{
				if(isLeapYear(year)==true && day==1) 
				{
					month-=1;
					day=29;
				}
				else if(day==1)
				{
					month-=1;
					day=28;
				}
				else
					day-=1;
			}
		}
			
		return this;
	}

	public MyDate previousMonth() {
		if(month==1)
		{
			month=12;
			year-=1;
		}
		else if(month==3&&day>29&&isLeapYear(year)==true)
		{
			month-=1;
			day=29;
		}
		else if(month==3&&day>28&&isLeapYear(year)==false)
		{
			month-=1;
			day=28;
		}
		else
		{
			month-=1;
		}
		return this;
	}

	public MyDate previousYear() {
		if(isLeapYear(year)==true && month==2 && day==29)
		{
			year-=1;
			day=28;
		}
		else
			year-=1;
		return this;
	}

	//////////////////////////////////////////////////////////////
	//Static Method
	
	public static boolean isLeapYear(int year) {
		if(year%4!=0)	return false;
		else if(year%100!=0) return true;
		else if(year%400!=0) return false;
		else return true;
		
	}
	
	public static int yearDiff(MyDate start, MyDate end) {
		if(start.year > end.year)
		{
			return -1;
		}
		else
		{
			if(start.month > end.month)
			{
				return end.year - start.year - 1;
			}
			else if(start.month < end.month)
			{
				return end.year - start.year;
			}
			else
			{
				if(start.day > end.day)
				{
					return end.year - start.year - 1;
				}
				else
				{
					return end.year - start.year;
				}
			}
		}
	}
	
	

}
