package idwall.desafio.crawlers.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Before;
import org.junit.Test;

import idwall.desafio.crawlers.domain.Post;
import idwall.desafio.crawlers.utils.WebElementUtils;

public class OldRedditCrawlerMapperTest {

	private OldRedditCrawlerMapper mapper;
	
	@Before
	public void setUp() throws Exception {
		mapper = new  OldRedditCrawlerMapper("teste");
	}

	@Test
	public void test() {
		Post post = mapper.getResultSet(WebElementUtils.createWebElement(500));

		assertThat(post.getTitle()).isNotBlank();
		assertThat(post.getUpVotes()).isEqualTo(500);
	}

}
