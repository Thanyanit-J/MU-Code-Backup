// Name: Thanyanit Jongjitragan
// Student ID: 6188075
// Section: 2

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class SimpleMovieSearchEngine implements BaseMovieSearchEngine {
	public Map<Integer, Movie> movies;
	
	@Override
	public Map<Integer, Movie> loadMovies(String movieFilename) {
		if(movieFilename == null) return null;
		
		HashMap<Integer, Movie> movie = new HashMap<Integer, Movie>();
		BufferedReader br = null;
		try	
		{
			br = new BufferedReader(new FileReader(movieFilename));
			
			int id = 0;
			String title = null;
			int year = 0;
			String[] tags = null;
			String line; // temporarily store strings from the file 1 line at a time
			String[][] data = new String[2][3]; // temporarily store strings from line.split()
					/* data[0][0] = <mid> =
					 * data[0][1] = <title>(<year>)
					 * data[0][2] = <tag|tag|...|tag>
					 * 
					 * data[1][0] = <title>
					 * data[1][1] = <year>
					 */
			String regex = " \\([0-9]{4}\\)"; // Split year from bracelets
			
			// Read and ignore first line
			line = br.readLine();
			while ((line = br.readLine()) != null)
			{
				// Split id, title+year, tags
				if(line.contains("\""))
				{
					data[0] = line.split(",\"|\",");
				}
				else
				{
					data[0] = line.split(",");
				}
				// Split title and year
				Pattern _year = Pattern.compile(regex);
				Matcher M = _year.matcher(data[0][1]);
				if(M.find())
				{
					data[1][1] = M.group(0).replaceAll("\\(|\\)|\\s", "");
					data[1][0] = data[0][1].replace(M.group(0), "");
				}
				// Return data
				try
				{
					id  = Integer.parseInt(data[0][0]);
					title = data[1][0];
					year = Integer.parseInt(data[1][1]);
					tags = data[0][2].split("\\|");
					Movie m = new Movie (id, title, year);
					for(String tag: tags)
					{
						m.addTag(tag);
					}	
					movie.put(id, m);
					/**/
				}
				catch (Exception e) { e.printStackTrace();/**/ }
			}/**/
		}
		catch (IOException e) { e.printStackTrace(); }
		finally
		{
			try { 
				if (br != null)	{ br.close(); }
			}
			catch (IOException e) { e.printStackTrace(); }
		}
		return movie;
	}

	@Override
	public void loadRating(String ratingFilename) {
		if(ratingFilename == null) return;
		
		BufferedReader br = null;
		try	
		{
			br = new BufferedReader(new FileReader(ratingFilename));
			
			int uid;
			int mid;
			double rating;
			long timestamp;
			
			String line;
			String data[];
			/* data[0] = uid
			 * data[1] = mid
			 * data[2] = rating
			 * data[3] = timestamp
			 */
			
			// Read and ignore first line
			line = br.readLine();
			// Read and process the rest of the lines
			while ((line = br.readLine()) != null)
			{
				data = line.split(",");
				try
				{
					uid = Integer.parseInt(data[0]);
					mid = Integer.parseInt(data[1]);
					rating = Double.parseDouble(data[2]);
					timestamp = Long.parseLong(data[3]);
					
					// Check if the movie has already been rated by this user or not by using uid
					if(this.movies.get(mid).getRating().containsKey(uid))
					{	// If found this user, compare timestamp: edit rating only when the timestamp is newer
						if(timestamp > this.movies.get(mid).getRating().get(uid).timestamp) 
							this.movies.get(mid).setRating(uid, rating, timestamp);
					}
					else
					{	// If not found user, create a new one
						User user = new User(uid);
						this.movies.get(mid).addRating(user, this.movies.get(mid), rating, timestamp);
					}
				}
				catch (Exception e) { e.printStackTrace(); /**/}
			}
		}
		catch (IOException e) { e.printStackTrace(); }
		finally
		{
			try	{ if (br != null) br.close(); }
			catch (IOException e) { e.printStackTrace(); }
		}
		 
	}

	@Override
	public void loadData(String movieFilename, String ratingFilename) {
		this.movies = loadMovies(movieFilename);
		this.loadRating(ratingFilename);
	}

	@Override
	public Map<Integer, Movie> getAllMovies() {
		return this.movies;
	}

	@Override
	public List<Movie> searchByTitle(String title, boolean exactMatch) {
		ArrayList<Movie> list = new ArrayList<>();
		// exactMatch==true --> both titles are exactly same (ignore case)
		if(exactMatch)
		{	// compare title and list all matched title
			for(Integer key: this.movies.keySet())
			{
				if(title.equalsIgnoreCase(this.movies.get(key).getTitle()))
					list.add(this.movies.get(key));
			}
		}
		// exactMatch == false --> Find the movies that contain the same char/string in the title
		else
		{	// Using the title as RegEx. List all matcher
			// For case insensitive: make everything to LowerCase just to be simple
			Pattern p = Pattern.compile(title.toLowerCase());
			for(Integer key: this.movies.keySet())
			{
				Movie movie = this.movies.get(key);
				Matcher m = p.matcher(movie.getTitle().toLowerCase());
				if(m.find())
					list.add(movie);
			}
		}
		return list;
	}

	@Override
	public List<Movie> searchByTag(String tag) {
		ArrayList<Movie> list = new ArrayList<>();
		
		// Compare tag and list all matched tag
		for(Integer key: this.movies.keySet())
		{
			if(this.movies.get(key).getTags().contains(tag))
				list.add(this.movies.get(key));
		}
		
		return list;
	}

	@Override
	public List<Movie>searchByYear(int year) {
		ArrayList<Movie> list = new ArrayList<>();
		
		// Compare year and list all matched year
		for(Integer key: this.movies.keySet())
		{
			if(this.movies.get(key).getYear() == year)
				list.add(this.movies.get(key));
		}
		
		return list;
	}

	@Override
	public List<Movie> advanceSearch(String title, String tag, int year) {
		ArrayList<Movie> list = new ArrayList<>();
		
		if(title != null)
		{
			for(Movie m:searchByTitle(title, true))
			{
				if(((tag!=null)?m.getTags().contains(tag):true) && ((year >= 0)? m.getYear()==year:true))
					list.add(m);
			}
			for(Movie m:searchByTitle(title, false))
			{
				if(((tag!=null)?m.getTags().contains(tag):true) && ((year >= 0)? m.getYear()==year:true))
					list.add(m);
			}
		}
		else
		{
			if(tag != null)
			{
				for(Movie m:searchByTag(tag))
					if((year >= 0)? m.getYear()==year:true)
							list.add(m);
			}
			else
				for(Movie m:searchByYear(year))
					list.add(m);
		}

		return list;
	}

	@Override
	public List<Movie> sortByTitle(List<Movie> unsortedMovies, boolean asc) {
		ArrayList<Movie> list = new ArrayList<>();
		list.addAll(unsortedMovies);
		// Insertion sort
			// list[y] compare to list[y+1]
			//	   Asc '> 0'
			//	   Des '< 0'
		if(asc)
			for(int y=0; y<list.size()-1; y++)
			{
				// Find the movie
				if(list.get(y).getTitle().compareTo(list.get(y+1).getTitle()) > 0)
				{
					// Insert that movie in thr right spot
					for(int search=y+1;search>0 ;search--)
					{
						if(list.get(search).getTitle().compareTo(list.get(search-1).getTitle()) > 0)
							break;
						
						Movie m = list.get(search);
						list.set(search, list.get(search-1));
						list.set(search-1, m);
					}
				}
			}
		else
			for(int y=0; y<list.size()-1; y++)
			{
				// Find the movie
				if(list.get(y).getTitle().compareTo(list.get(y+1).getTitle()) < 0)
				{
					// Insert that movie in thr right spot
					for(int search=y+1;search>0 ;search--)
					{
						if(list.get(search).getTitle().compareTo(list.get(search-1).getTitle()) < 0)
							break;
						
						Movie m = list.get(search);
						list.set(search, list.get(search-1));
						list.set(search-1, m);
					}
				}
			}
		return list;
	}

	@Override
	public List<Movie> sortByRating(List<Movie> unsortedMovies, boolean asc) {
		ArrayList<Movie> list = new ArrayList<>();
		list.addAll(unsortedMovies);
		// Insertion sort
			// list[y] > or < list[y+1]
			//	   Asc '>'
			//	   Des '<'
		if(asc)
			for(int y=0; y<list.size()-1; y++)
			{
				// Find the movie
				if(list.get(y).getMeanRating() > list.get(y+1).getMeanRating())
				{
					// Insert that movie in thr right spot
					for(int search=y+1;search>0 ;search--)
					{
						if(list.get(search).getMeanRating() > list.get(search-1).getMeanRating())
							break;
						
						Movie m = list.get(search);
						list.set(search, list.get(search-1));
						list.set(search-1, m);
					}
				}
			}
		else
			for(int y=0; y<list.size()-1; y++)
			{
				// Find the movie
				if(list.get(y).getMeanRating() < list.get(y+1).getMeanRating())
				{
					// Insert that movie in thr right spot
					for(int search=y+1;search>0 ;search--)
					{
						if(list.get(search).getMeanRating() < list.get(search-1).getMeanRating())
							break;
						
						Movie m = list.get(search);
						list.set(search, list.get(search-1));
						list.set(search-1, m);
					}
				}
			}
		return list;
	}

}
