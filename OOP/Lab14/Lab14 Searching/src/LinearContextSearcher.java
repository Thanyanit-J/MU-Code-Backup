// THANYANIT JONGJITRAGAN 6188075
public class LinearContextSearcher extends ContextSearcher {

	public LinearContextSearcher(String filename) {
		super(filename);
	}

	@Override
	public String find(Word query, int window) {
		for(Word currentWord: this.sortedWords) {
			// If found, return that word and word +- window 
			if(currentWord.equals(query))
			{
				return super.getSnippet(currentWord, window);
			}
		}
		return null;
	}

}
