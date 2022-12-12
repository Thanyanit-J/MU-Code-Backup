//THANYANIT JONGJITRAGAN 6188075
import java.util.ArrayList;
public class ProMember extends Member {
	double fee;
	final static double COLLABORATOR_FEE = 80.00;
	ArrayList <String> collaborators;
	
	public ProMember(String em, String pw, double F) {
		super(em,pw);
		fee=F;
		collaborators = new ArrayList <String>();
	}
	
	void printMemberInfo()
	{
		System.out.println("---- PRO MEMBER ----");
		System.out.println("Member fee: "+fee);
		super.printMemberInfo();
		if(collaborators.size()!=0) {System.out.println("---------------------");
		 System.out.println("List of collaborators");}
		for(int i=0;i<collaborators.size();i++)
		{
			System.out.print(collaborators.get(i) + ",");
		}
		System.out.println("");
	}
	
	boolean addCollaborator (String username)
	{
		if(username != "") {
			collaborators.add(username);
			System.out.println(username +" is added successfully.");
			return true;
		}
		return false;
	}
	
	boolean removeCollaborator (String username)
	{
		for(int i=0;i<collaborators.size();i++)
		{
			if(collaborators.get(i).equals(username))
			{
				System.out.println(username + " is removed successfully.");
				collaborators.remove(i);
				return true;
			}
		}
		System.out.println(username + " cannot be removed.");
		return false;
	}
	
	double getMonthlyBill ()
	{
		return fee+collaborators.size()*COLLABORATOR_FEE;
	}
}
