// THANYANIT JONGJITRAGAN 6188075
import java.io.*;
import java.util.ArrayList;

public class Sorting {
	static boolean viewProcess;
	static String[] read(String dataFile)
	{
		String[] data = null;
		BufferedReader br = null;
		try
		{
			br = new BufferedReader(new FileReader("C:\\Users\\TUNTUN\\Desktop\\Files\\Mahidol University\\OOP\\Lab13\\"+dataFile));
			String line;
			while ((line = br.readLine()) != null)
			{
				/*System.out.println(line);/**/
				data = line.split(" ");
			}
		}
		catch (IOException e) { e.printStackTrace(); }
		finally
		{
			try	{ if (br != null) br.close(); }
			catch (IOException e) { e.printStackTrace(); }
		}
		return data;
	}
	static void print(String[] str) {
		System.out.print("[");
		for(int i=0; i<str.length; i++)
		{
			System.out.print(str[i]+((i!=str.length-1)?", ":"]\n"));
		}
	}
	static void sort(String[] data) {
		int countPass=0;
		for(int y=0; y<data.length-1; y++)
		{
			if(data[y].compareTo(data[y+1])<0)
			{
				for(int search=y+1;search>0 ;search--)
				{
					if(data[search].compareTo(data[search-1])<0)
						break;
					
					String tmp=data[search];
					data[search]=data[search-1];
					data[search-1]=tmp;
				}
				// Process viewing
				countPass++;
				if(viewProcess) {
					System.out.print("Pass "+countPass+": ");
					print(data);
				}
				/**/
			}
		}
		if(!viewProcess) {
			System.out.print("Pass "+countPass+": ");
			print(data);
		}
	}

	static void bonus1(String[] data) {
		System.out.println("---------------BONUS Option 1---------------");
		
		if(data==null || data.length==0)
		{
			System.out.println("No word is founded.");
			return;
		}
		
		// Counting
		ArrayList<String> word = new ArrayList();
		ArrayList<Integer> wordCount = new ArrayList();
		for(int i=0; i<data.length; i++)
		{
			int count=0;
			boolean isFound=false;
			
			// Check if the word exist the list
			for(int j=0; j<word.size(); j++)
			{
				if(data[i].equals(word.get(j))) {
					isFound=true;
					break;
				}
			}
			
			// if not exist, then add that word and its count to the list
			if(isFound==false)
			{
				word.add(data[i]);
				for(int j=0; j<data.length; j++)
				{
					if(data[j].compareTo(data[i])==0)
						count++;
				}
				wordCount.add(count);
			}
				
		}
		
		// Find the position of the top 10 most frequent words
		
		// Create the tmp List for freely changing the counted max number to -1
		ArrayList<Integer> tmp = wordCount;
		int counter = 0; 
		final int MAXRANKING = 10;
		int posMostNumber=-1;
		int currentMostNumber=-1;
		int previousMostNumber=-1;
		int ranking = 0; // The ranking should not increased if the currentMostNumber is equal to the previous one
		
		System.out.println("Data: ");
		print(data);
		System.out.println("Founded words: ");
		System.out.println(word+"\nTotal different words: "+tmp.size()+"\n");
		// Scan through the tmp list
		for(int i=0; i<tmp.size(); i++)
		{
			// Finding the max
				/*System.out.println(tmp +" Size: "+tmp.size()+" Prev Max: "+previousMostNumber);/**/
			currentMostNumber = tmp.get(0);
			for(int j=0; j<tmp.size(); j++)
			{
				if(tmp.get(j)<0) continue;
				if(tmp.get(j)>currentMostNumber)
				{
					currentMostNumber = tmp.get(j);
					posMostNumber = j;
				}
				
			}
			if(posMostNumber==-1)
				posMostNumber = 0;
			
			// Print the max
			counter++;
			if(tmp.get(posMostNumber) < previousMostNumber || i==0)
				ranking = counter;
			if(ranking>MAXRANKING)
				break;
			System.out.println("#"+ranking+" ["+word.get(posMostNumber)+"] --- "+wordCount.get(posMostNumber)+" word"+((wordCount.get(posMostNumber)==1)?"":"s"));
			
			previousMostNumber = tmp.get(posMostNumber);
			tmp.set(posMostNumber,-1);
			posMostNumber = -1;	
		}
		
		// Print All
		/*for(int i=0; i<word.size(); i++)
		{
			System.out.println("["+word.get(i)+"] --- "+wordCount.get(i)+" word"+((wordCount.get(i)==1)?"":"s"));
		}/**/
	}
	
	public static void main(String[] args) {
		String[] data = read("unsorted.txt");
		System.out.print("Original: "); print(data);
		viewProcess=true;
		sort(data);
		
		// CHALLENGING BONUS
		System.out.println("\n\n");
		String[] data2 = read("bonus1.txt");
		bonus1(data2);
		System.out.println("DONE!!");
	}

}
