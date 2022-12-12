//THANYANIT JONGJITRAGAN 6188075
import java.util.ArrayList;
public class FreeMember extends Member {
	final static int FREE_PRIVATE_REPOS = 3;
	int numPrivateRepo;
	
	public FreeMember(String em, String pw) {
		super(em,pw);
		numPrivateRepo = 0;
	}
	
	void printMemberInfo()
	{
		System.out.println("---- FREE MEMBER ----");
		super.printMemberInfo();
	}
	
	boolean addRepository(Repository repo)
	{
		if(repo.isPrivate())
		{
			if(numPrivateRepo >= FREE_PRIVATE_REPOS)
			{
				System.out.println(repo.toString()+" cannot be added because the number of private repository is reaching the limit");
				return false;
			}
			this.numPrivateRepo++;
		}
		super.addRepository(repo);
		return true;
	}
	
	boolean removeRepository(Repository repo)
	{
		if(repo.isPrivate()==false) return false;
		if(super.removeRepository(repo)==true)
		{
			this.numPrivateRepo-=1; return true;
		}
		else return false;
	}
	
	int getNumPrivateRepo()
	{
		int count=0;
		for(int i=0;i<repoList.size();i++)
		{
			if(repoList.get(i).isPrivate())
				count++;
		}
		return count;
	}
}
