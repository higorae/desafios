package idwall.desafio.crawlers.service;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import idwall.desafio.crawlers.domain.Post;
import idwall.desafio.crawlers.mapper.CrawlerMapper;
import idwall.desafio.crawlers.mapper.OldRedditCrawlerMapper;

/**
 * Classe responsável por ler o conteúdo de uma thread do reddit e busca pelo seu
 * conteúdo baseado em um determinado condicional.
 * 
 * @author higor.azevedo
 *
 */
@Component
public class SubredditContentReader {

	private Logger log = LoggerFactory.getLogger(SubredditContentReader.class);
	
	private WebDriver driver;
	
	private final String urlReddit = "http://old.reddit.com/r/:subredit:";
	
	public SubredditContentReader(WebDriver driver) {
		this.driver = driver;
	}
	
	public void read(String subreddit, Consumer<List<Post>> consumer) throws EmptySubredditResponseException{
		read(subreddit, consumer, thread -> thread.getUpVotes() < 5000);
	}
	
	public void read(String subreddit, Consumer<List<Post>> consumer, Predicate<Post> whereClause) throws EmptySubredditResponseException{
		log.info("Looking for threads about {}", subreddit);
		
		String urlSubreddit = getUrlSubreddit(subreddit);
		
		CrawlerMapper<Post> mapper = new OldRedditCrawlerMapper(urlSubreddit);
		
		driver.get(urlSubreddit + "/top");
		
		List<WebElement> elements = 	driver.findElements(By.cssSelector(OldRedditCrawlerMapper.POST_SELECTOR));
		
		List<Post> posts = new ArrayList<>();
		
		for (WebElement webElement : elements) {
			Post post = mapper.getResultSet(webElement);
			
			if(whereClause.test(post)) {
				break;
			}
			
			posts.add(post);
		}
		
		log.info("Found {} posts", posts.size());
		
		if(posts.isEmpty()) {
			throw new EmptySubredditResponseException(subreddit);
		}
		
		consumer.accept(posts);
	}

	private String getUrlSubreddit(String subreddit) {
		return urlReddit.replace(":subredit:", subreddit);
	}
	
}
