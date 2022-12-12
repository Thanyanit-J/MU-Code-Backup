// THANYANIT JONGJITRAGAN 6188075
public class BinaryContextSearcher extends ContextSearcher {

	public BinaryContextSearcher(String filename) {
		super(filename);
	}

	@Override
	public String find(Word query, int window) {
		int upper = this.sortedWords.size();
		int lower = 0;
		int mid = (upper-lower)/2;
		int countloop=0; // JUST IN CASE OF INFINITE OUTPUT BUG
		Word currentWord = this.sortedWords.get(mid);
		int result = Word.compareTo(query,currentWord);
		while(result != 0) {
			//System.out.println(currentWord.toString() + " " + upper + " " + mid + " " + lower);
			if(result > 0)
			{
				lower = mid;
			}
			else
			{
				upper = mid;
			}
			mid = lower + (upper-lower)/2;
			currentWord = this.sortedWords.get(mid);
			result = Word.compareTo(query,currentWord);
			countloop++;
			if(lower == upper || lower == mid || mid == upper || countloop == 10000)
				return null;
		}/**/
		return super.getSnippet(currentWord, window);
	}

}
