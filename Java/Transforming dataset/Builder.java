public class Builder {
	public static void main(String[] args) {
		long start = System.currentTimeMillis();
		BuildKeywords.build();
		BuildWebpages.build();
		BuildArticles.build();
		long stop = System.currentTimeMillis();
		System.out.println("Finished preparing dataset for Solr in " + (stop-start) + " milliseconds");
	}
}