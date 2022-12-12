// Name: THANYANIT JONGJITRAGAN
// ID: 	 6188075
import java.util.ArrayList;
public class FullBinaryTreeTester {
	
	public static void inOrderTraverse(Node root)
	{
		if(root!=null)
		{
			inOrderTraverse(root.left);
			System.out.print(root.id+" ");
			inOrderTraverse(root.right);
		}
		
	}
	
	public static boolean isFullBinTree(Node root)
	{	
		if(root!=null)
		{
			if((root.left!=null && root.right!=null) || (root.left==null && root.right==null))
			{
				if(!isFullBinTree(root.left)) return false;
				if(!isFullBinTree(root.right)) return false;
			}
			else return false;	
		}
		return true;
	}
	
	public static void normalTester()
	{
		Node[] ts = new Node[7];
		int count = 0;
		ts[count++] = null;
		ts[count++] = new Node(16, null, null);
		
		ts[count++] = new Node(16, new Node(14, null, null), null);
		
		ts[count++] = new Node(1, new Node(3, new Node(6, null, null), new Node(7, null, null)), 
				new Node(4, new Node(8, null, null), new Node(10, null, null)));
		
		ts[count++] = new Node(1, new Node(3, null, null), 
				new Node(4, new Node(8, null, null), new Node(10, null, null)));
		
		ts[count++] = new Node(1, new Node(3, new Node(6, null, null), null), 
				new Node(4, new Node(8, null, null), new Node(10, null, null)));
		
		ts[count++] = new Node(1, new Node(3, new Node(6, null, null), new Node(7, null, null)), 
				null);
		
		for(int i = 0; i < ts.length; i++)
		{
			System.out.print("[T"+i+"] in-order: ");
			inOrderTraverse(ts[i]);
			System.out.println("\n[T"+i+"] is"+(isFullBinTree(ts[i])?" ":" NOT ")+"a full binary tree.\n");
		}
		
	}
	
	
	/**************BONUS STARTS***************/
	public static void printBinTree(Node root)
	{	
		if(root!=null && !(root.left==null && root.right==null)) {
			System.out.print("["+root.id+"]");
			System.out.print(" L: "+((root.left!=null)? root.left.id:" "));
			System.out.print(" R: "+((root.right!=null)?root.right.id:" "));
			System.out.println();
			printBinTree(root.left);
			printBinTree(root.right);
		}
		
	}
	
	static ArrayList<Integer> arr = new ArrayList<Integer>();
	public static Node getBinSearchTree(Node root)
	{	
		if(root!=null) {
			//sortNode(root);
			
				if(root.id<root.left.id)
					return new Node(root.left.id,root.left,root.right);
				else if(root.id>root.left.id)
					return new Node(root.right.id,root.left,root.right);
				else
					return new Node(root.id,root.left,root.right);
		}
		
		return null;
	}
	private static void sortNode(Node root)
	{
		if(root!=null) {
			arr.add(root.id);
			sortNode(root.left);
			sortNode(root.right);
		}
	}
	
	public static void bonusTester()
	{
		Node t = new Node(1, new Node(3, new Node(6, null, null), new Node(7, null, null)), 
				new Node(4, new Node(8, null, null), new Node(10, null, null)));
		System.out.println("Before Transforming: ");
		printBinTree(t);
		System.out.println("After Transforming: ");
		printBinTree(getBinSearchTree(t));
		
	}
	/**************BONUS ENDS***************/
	
	
	
	public static void main(String[] args)
	{
		normalTester();
		
		//Uncomment for bonus
		bonusTester();
	}
}
