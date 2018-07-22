package idwall.desafio.crawlers.mapper;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import idwall.desafio.crawlers.domain.Post;

/**
 * Classe responsável por mapear o post de um subreddit da versão antiga do layout do reddit.com.
 * 
 * @author higor.azevedo
 *
 */
public class OldRedditCrawlerMapper implements CrawlerMapper<Post> {

	public static final String POST_SELECTOR = "#siteTable > div.link.thing";
	
	private String linkSubreddit;
	
	public OldRedditCrawlerMapper(String linkSubreddit) {
		this.linkSubreddit = linkSubreddit;
	}
	
	public Post getResultSet(WebElement element) {
		Post post = new Post();
		
		post.setTitle(element.findElement(By.cssSelector("a.title")).getText());
		post.setSubreddit(linkSubreddit);
		post.setLinkThread(element.findElement(By.cssSelector("a.title")).getAttribute("href"));
		post.setLinkComments(element.findElement(By.cssSelector("a.comments")).getAttribute("href"));
		post.setUpVotes(element.findElement(By.cssSelector(".score.unvoted")).getAttribute("textContent"));
		
		return post;
	}
	
}
