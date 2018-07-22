package idwall.desafio.crawlers.domain;

public class Post {

	private String subreddit;
	
	private String title;
	
	private String linkComments;
	
	private String linkThread;
	
	private Integer upVotes;

	public String getSubreddit() {
		return subreddit;
	}

	public void setSubreddit(String subreddit) {
		this.subreddit = subreddit;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getLinkComments() {
		return linkComments;
	}

	public void setLinkComments(String linkComments) {
		this.linkComments = linkComments;
	}

	public Integer getUpVotes() {
		return upVotes;
	}

	public void setUpVotes(Integer upVotes) {
		this.upVotes = upVotes;
	}
	
	public void setUpVotes(String total) {
		
		if(total == null || total.trim().isEmpty()) {
			setUpVotes(0);
			return;
		}
		
		if(total.trim().toLowerCase().endsWith("k")) {
			setUpVotes(Integer.valueOf(total.toLowerCase().replace("k", "00").replace(".", "")) );
			return;
		}
		
		setUpVotes(Integer.valueOf(total.trim()));
	}
	
	public String getLinkThread() {
		return linkThread;
	}

	public void setLinkThread(String linkThread) {
		this.linkThread = linkThread;
	}

	@Override
	public String toString() {
		return "Subreddit: " + subreddit + "\n"+ 
			   "Title: " + title + "\n"+ 
			   "Thread Link: " + linkThread + "\n"+
			   "Comments Link: " + linkComments + "\n"+ 
			   "Up votes: " + upVotes+ "\n";
	}

}
