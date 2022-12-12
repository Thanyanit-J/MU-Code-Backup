// Thanyanit Jongjitragan 6188075 
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.io.*;

import org.apache.commons.io.FileUtils;

public class SimpleMovieDatabase {
	public Map<Integer, Movie> movies = null;
	
	public void importMovies(String movieFilename)
	{
		movies = new HashMap<Integer, Movie>();
		BufferedReader br = null;
		try	
		{
			br = new BufferedReader(new FileReader("C:\\Users\\TUNTUN\\Desktop\\Files\\Mahidol University\\OOP\\Lab10\\Lab10_6188075\\src\\"+movieFilename));
			
			int id = 0;
			String title = null;
			String tags[] = null;
			
			String line;
			String data[];
			while ((line = br.readLine()) != null)
			{
				/*for(int i=0; i<line.length(); i++)
				{
					System.out.println(line);
				}/**/
				//System.out.println(line);
				data = line.split(",");
				try
				{
					id  = Integer.parseInt(data[0]);
					title = data[1];
					tags = data[2].split("\\|");
					Movie m = new Movie (id, title);
					m.tags.addAll(Arrays.asList(tags));
					
					movies.put(id, m);
				}
				catch (Exception e) {}
				
			}/**/


			
		}
		catch (IOException e) { e.printStackTrace(); }
		finally
		{
			try	{ if (br != null) br.close(); }
			catch (IOException e) { e.printStackTrace(); }
		}
	}
	
	
	//-------------------BONUS----------------------
	public List<Movie> searchMovies(String query) 
	{
		//YOUR BONUS CODE GOES HERE
		return null;
	}
	
	public List<Movie> getMoviesByTag(String tag)
	{
		//YOUR BONUS CODE GOES HERE
		return null;
	}
	
	
	public static void main(String[] args)
	{
		SimpleMovieDatabase mdb = new SimpleMovieDatabase();
		mdb.importMovies("lab10_movies.txt");
		System.out.println("Done importing "+mdb.movies.size()+" movies");
		int[] mids = new int[]{139747, 141432, 139415, 139620, 141305};
		for(int mid: mids)
		{
			System.out.println("Retrieving movie ID "+mid+": "+mdb.movies.get(mid));
		}
		
		//Uncomment for bonus
		/*System.out.println("\n////////////////////////// BONUS ///////////////////////////////");
		String[] queries = new String[]{"america", "thai", "thailand"};
		for(String query: queries)
		{
			System.out.println("Results for movies that match: "+query);
			for(Movie m: mdb.searchMovies(query))
			{
				System.out.println("\t"+m);
			}
			System.out.println();
		}
		
		String[] tags = new String[]{"Musical", "Action", "Thriller"};
		for(String tag: tags)
		{
			System.out.println("Results for movies in category: "+tag);
			for(Movie m: mdb.getMoviesByTag(tag))
			{
				System.out.println("\t"+m);
			}
			System.out.println();
		}
		/**/
		
	}

}
