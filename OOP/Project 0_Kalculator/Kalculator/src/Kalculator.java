//Name: THANYANIT JONGJITRAGAN
//ID:	6188075
//Section:	2

import java.util.Scanner;
import java.util.ArrayList;

public class Kalculator {
	//******INSERT YOUR CODE HERE***********
	//Class attributes go here
	//**************************************
	ArrayList<Double> num;
	int count = 0;
	/**
	 * Constructor is the fist method to be call at instantiation of a Kalculator object.
	 * If you need to initialize your object, do so here. 
	 */
	Kalculator()
	{
		//******INSERT YOUR CODE HERE***********
		num = new ArrayList<Double>();
		count++;
		//**************************************
	}
	
	/**
	 * Add number to the list of numbers. 
	 * @param number
	 */
	public void addNumber(double number)
	{	//******INSERT YOUR CODE HERE***********
		//System.out.print(number+" ");
		num.add(number);
		//**************************************
	}
	
	/**
	 * Remove the least recently added number from the list. If the list is empty, do nothing.
	 */
	public void deleteFirstNumber()
	{
		//******INSERT YOUR CODE HERE***********
		if(0 != num.size()) num.remove(0);
		//**************************************
	}
	
	/**
	 * Remove the most recently added number from the list. If the list is empty, do nothing.
	 */
	public void deleteLastNumber()
	{
		//******INSERT YOUR CODE HERE***********
		if(0 != num.size()) num.remove(num.size()-1);
		//**************************************
	}
	
	/**
	 * Calculate the summation of all the numbers in the list, then returns the sum. 
	 * If the list is empty, return 0.
	 * @return
	 */
	public double getSum()
	{
		//******INSERT YOUR CODE HERE***********
		double sum=0;
		for(double n: num)
		{
			sum+=n;
		}
		return sum;/**/
		//**************************************
	}
	
	/**
	 * Calculate and return the average of all the numbers in the list.
	 * If the list is empty, return 0.
	 * @return
	 */
	public double getAvg()
	{
		//******INSERT YOUR CODE HERE***********
		if(num.size()==0)
			return 0;
		return getSum()/num.size();
		//**************************************
	}
	
	/**
	 * Calculate and return the sample standard deviation of all the numbers in the list.
	 * If the list has fewer than 2 numbers, return 0.
	 * @return
	 */
	public double getStd()
	{
		//******INSERT YOUR CODE HERE***********
		if(num.size()<2)
			return 0;
		
		double sum=0,avg=getAvg();
		
		for(int i=0; i<num.size();i++)
		{
			//find sum of x[i] - avg
			sum += Math.pow(num.get(i)-avg,2);
		}
		return Math.sqrt(sum/(num.size()-1));
		//**************************************
	}
	
	/**
	 * Find and return the maximum of all the numbers in the list.
	 * If the list is empty, return 0.
	 * @return
	 */
	public double getMax()
	{
		//******INSERT YOUR CODE HERE***********
		if(num.size()==0) return 0;
		double max=num.get(0);
		for(double i: num)
		{
			if(i > max) max=i;
		}
		return max;
		//**************************************
	}
	
	/**
	 * Find and return the minimum of all the numbers in the list.
	 * If the list is empty, return 0.
	 */
	public double getMin()
	{
		//******INSERT YOUR CODE HERE***********
		if(num.size()==0) return 0;
		double min=num.get(0);
		for(double i: num)
		{
			if(i < min) min=i;
		}
		return min;
		//**************************************
	}
	
	/**
	 * Find and return the maximum k numbers of all the numbers in the list as an array of k double number.
	 * The order of the returned k numbers does not matter. (We only care if you can get the max k elements)
	 * If the list has fewer than k numbers, return null.
	 */
	public double[] getMaxK(int k)
	{
		//******INSERT YOUR CODE HERE***********
		if(num.size()<k)		
			return null;
		
		double[] maxk = new double[k];
		double min = num.get(0);
		double currentNum;
		
		//Set the maxk. Assume that the first k number is/are the maximum numbers
		for(int i=0;i<k;i++)
		{
			currentNum=num.get(i);
			maxk[i]=(currentNum);
			
			//find the minimum value in maxk
			if(currentNum<min)
				min=currentNum;
		}
		
		//Checking each number that is not in maxk
		for(int i=k;i<num.size();i++)
		{
			currentNum=num.get(i);
			
			//Compare currentNum with each index in maxk
			for(int j=0;j<maxk.length;j++)
			{
				//if currentNum > one of the maxk
				if(currentNum>maxk[j])
				{
					//then replace the min number in maxk with currentNum
					for(int x=0;x<maxk.length;x++)
					{
						if(maxk[x]==min)
						{
							maxk[x]=currentNum;
							break;
						}
					}
					//Find new minimum number in the new maxk
					min=maxk[0];
					for(double a : maxk)
					{
						if(a < min) min=a;
					}
					break;
				}
			}
		}
		
		//By now we should get the max k numbers stored in maxk
		//So, let's return them
		return maxk;
		
		
		//**************************************
	}
	
	/**
	 * Find and return the minimum k numbers of all the numbers in the list as an array of k double number.
	 * The order of the returned k numbers does not matter. (We only care if you can get the min k elements)
	 * If the list has fewer than k numbers, return null.
	 */
	ArrayList<Double> mink;
	public double[] getMinK(int k)
	{
		//******INSERT YOUR CODE HERE***********
		if(num.size()<k)		
			return null;
		
		double[] mink = new double[k];
		double max = num.get(0);
		double currentNum;
		
		//Set the mink. Assume that the first k number is/are the minimum numbers
		for(int i=0;i<k;i++)
		{
			currentNum=num.get(i);
			mink[i]=(currentNum);
			
			//find the maximum value in mink
			if(currentNum>max)
				max=currentNum;
		}
		
		//Checking each number that is not in mink
		for(int i=k;i<num.size();i++)
		{
			currentNum=num.get(i);
			
			//Compare currentNum with each index in mink
			for(int j=0;j<mink.length;j++)
			{
				//if currentNum < one of the mink
				if(currentNum<mink[j])
				{
					//then replace the max number in mink with currentNum
					for(int x=0;x<mink.length;x++)
					{
						if(mink[x]==max)
						{
							mink[x]=currentNum;
							break;
						}
					}
					//Find new minimum number in the new mink
					max=mink[0];
					for(double a : mink)
					{
						if(a > max) max=a;
					}
					break;
				}
			}
		}
		
		//By now we should get the max k numbers stored in mink
		//So, let's return them
		return mink;
		//**************************************
	}
	
	/**
	 * Print (via System.out.println()) the numbers in the list in format of:
	 * DATA[<N>]:[<n1>, <n2>, <n3>, ...]
	 * Where N is the size of the list. <ni> is the ith number in the list.
	 * E.g., "DATA[4]:[1.0, 2.0, 3.0, 4.0]"
	 */
	public void printData()
	{
		//******INSERT YOUR CODE HERE***********
		System.out.print("DATA["+num.size()+"]:[");
		for(double n: num)
		{
			System.out.print(n);
			if(n!=num.get(num.size()-1))
				System.out.print(", ");
		}
		System.out.println("]");/**/
		//**************************************
	}
}
