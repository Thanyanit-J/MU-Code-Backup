//THANYANIT JONGJITRAGAN 6188075
import java.util.ArrayList;
public class Member {
	String email;
	String password;
	ArrayList <Repository> repoList;
	
	public Member(String em, String pw) {
		this.email = em;
		this.password = pw;
		repoList = new ArrayList <Repository>();
	}
	
	boolean addRepository (Repository repo) {
	if(repo!=null)
	{
		repoList.add(repo);
		return true;
	}
	return false;
	}
	
	boolean removeRepository (Repository repo) 
	{
		for(int i=0;i<repoList.size();i++)
		{
			if(repo==repoList.get(i))
			{
				System.out.print(repoList.get(i).toString());
				System.out.println("is successfully removed.");
				repoList.remove(i);
				return true;
			}
			
		}
		
		return false;
	}
	
	void printMemberInfo()
	{
		System.out.println("Email: "+ this.email +" (pwd: "+this.password+")");
		System.out.println("List of repositories");
		for(int i=0;i<repoList.size();i++)
		{
			System.out.println(repoList.get(i).toString());
		}
	}
}
