package idwall.desafio.crawlers.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Consumer;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import idwall.desafio.crawlers.domain.Post;
import idwall.desafio.crawlers.mapper.OldRedditCrawlerMapper;
import idwall.desafio.crawlers.utils.WebElementUtils;

public class SubredditContentReaderTest {
	
	private SubredditContentReader resolver;
	
	private WebDriver driver;
	
	@Before
	public void setup() {
		driver = mock(WebDriver.class);
		resolver = new SubredditContentReader(driver);
	}
	
	@Test
	public void givenAnSubredditWithoutFilter_ThenListAllThreads() throws EmptySubredditResponseException {
		List<WebElement> elements = getMockedElements();
		
		mockFindElement(elements);
		
		final List<Post> threads = new ArrayList<>();
		
		resolver.read("cats", defaultConsumer(threads), thread -> thread.getUpVotes() < 2000 );
		
		assertThat(threads).hasSize(3);
	}
	
	@Test
	public void givenAnSubredditWithUpvoteFilter_ThenListAllThreadsWithMoreOrEquals5kUpVotes() throws EmptySubredditResponseException {
		List<WebElement> elements = getMockedElements();
		
		mockFindElement(elements);
		
		final List<Post> threads = new ArrayList<>();
		
		resolver.read("cats", defaultConsumer(threads));
		
		assertThat(threads).hasSize(2);
	}

	@Test
	public void givenAnSubredditWithUpvoteFilter_ThenReturnEmptyElementList() throws EmptySubredditResponseException {
		
		mockFindElement(Collections.emptyList());
		
		assertThatExceptionOfType(EmptySubredditResponseException.class).isThrownBy(() -> {
			resolver.read("cats", null);
		});
	}

	private void mockFindElement(List<WebElement> elements) {
		when(driver.findElements(By.cssSelector(OldRedditCrawlerMapper.POST_SELECTOR))).thenReturn(elements);
	}
	
	/**
	 * Mock a list of elements
	 * 
	 * @return
	 */
	private List<WebElement> getMockedElements() {
		List<WebElement> elements = new ArrayList<>();
		elements.add(WebElementUtils.createWebElement(5000));
		elements.add(WebElementUtils.createWebElement(15000));
		elements.add(WebElementUtils.createWebElement(2000));
		return elements;
	}
	
	private Consumer<List<Post>> defaultConsumer(List<Post> threads) {
		return posts -> threads.addAll(posts);
	}

}
