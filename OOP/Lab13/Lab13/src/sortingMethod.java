// THANYANIT JONGJITRAGAN 6188075
public class sortingMethod {
static int[] data = {7, 8, 5, 2, 4, 6, 3};
static boolean processViewing;
	static void selectionsort() {
		int idx=-1,tmp=-1;
		
		for(int x=0; x<data.length-1; x++) {
			int min=data[x];
			for(int y=x; y<data.length; y++)
			{
				if(data[y]<min)
				{
					min=data[y];
					idx=y;
				}
			}
			
			// Swapping
			tmp=data[idx];
			data[idx]=data[x];
			data[x]=tmp;
			
			// Process viewing
			if(processViewing==true)
			{System.out.println(); for(int y:data) System.out.print(y+"  ");}/**/
		}
	}
	static void bubblesort() {
		
		for(int x=0; x<data.length; x++)
		{
			for(int y=0; y<data.length-x-1; y++)
			{
				if(data[y] > data[y+1])
				{
					// Swapping
					int tmp=data[y]; 
					data[y]=data[y+1];
					data[y+1]=tmp;
				}
				
			}
			
			// Process viewing
			if(processViewing==true)
			{System.out.println(); for(int i=0; i<data.length; i++) System.out.print(data[i]+ ((i==data.length-2-x)? "| ":"  "));}/**/
		}
		
		
	}
	static void insertionsort() {
		
		for(int y=0; y<data.length-1; y++)
		{
			if(data[y] > data[y+1])
			{
				for(int search=y+1;search>0 ;search--)
				{
					if(data[search]>data[search-1])
						break;
					
					int tmp=data[search];
					data[search]=data[search-1];
					data[search-1]=tmp;
				}
			}
			// Process viewing
			if(processViewing) {
				System.out.println();
				for(int i=0;i<data.length;i++) System.out.print(data[i]+ ((i==y+1)?"| ":"  "));
			}
			/**/
		}
	}
	
	
	public static void main(String[] args) {
		System.out.print("Data: ");
		for(int x:data) System.out.print(x+" ");
		System.out.print("\n");
		
		processViewing=true;
		
		/*selectionsort();/**/
		/*bubblesort();/**/
		insertionsort();/**/
		
		System.out.print("\n\nSorted: ");
		for(int y:data) System.out.print(y+" ");
	}

}
