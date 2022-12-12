// Name: Thanyanit Jongjitragan
// Student ID: 6188075
// Section: 2

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Movie {
	private int mid;
	private String title;
	private int year;
	private Set<String> tags;
	private Map<Integer, Rating> ratings;	//mapping userID -> rating
	private Double avgRating;
	//additional
	
	public Movie(int _mid, String _title, int _year){
		mid=_mid;
		title=_title;
		year=_year;
		tags = new HashSet<String>();
		ratings= new HashMap<Integer, Rating>();
		avgRating=0.0;
	}
	
	public int getID() {
		return mid;
	}
	public String getTitle(){
		return title;
	}
	public Set<String> getTags() {
		return tags;
	}
	
	public void addTag(String tag){
		this.tags.add(tag);
	}
	
	public int getYear(){
		return year;
	}
	
	public String toString()
	{
		return "[mid: "+mid+":"+title+" ("+year+") "+tags+"] -> avg rating: " + avgRating;
	}
	
	
	public double calMeanRating(){
		// Prevent the case of dividing by 0
		if(this.ratings.size()==0) return 0.0;
		
		double sum = 0;
		for(Integer key: this.ratings.keySet()) {
			sum += this.ratings.get(key).rating;
		}
		return sum/this.ratings.size();
	}
	
	public Double getMeanRating(){
		avgRating = this.calMeanRating();
		return this.avgRating;
	}
	
	public void addRating(User user, Movie movie, double rating, long timestamp) {
		// Add rating
		Rating r = new Rating(user, movie, rating, timestamp);
		ratings.put(user.uid, r);
		// then immediately UPDATE avgRating.
		avgRating = this.calMeanRating();
		
	}
	
	public Map<Integer, Rating> getRating(){
		return this.ratings;
	}
	
	// Edit rating in case of the same user rates the same movie again
	// Require input: user_id, rating, timestamp
	public void setRating(int uid, double rating, long timestamp) {
		this.ratings.get(uid).rating = rating;
		this.ratings.get(uid).timestamp = timestamp;
		// Always update the rating every time the rating is potentially edited
		avgRating = this.calMeanRating();
	}
	
}
