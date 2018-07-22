package idwall.desafio.crawlers.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public class PostTest {
	
	@Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][] {     
                 { "50.0k", 50000 }, { "50.2k", 50200 }, {"50000", 50000}, {"5000", 5000}, {"200", 200}, {"", 0}, {null, 0}  
           });
    }
	
	private Post post;
	private String value;
	private int expected;
	
	public PostTest(String value, int expected) {
		this.value = value;
		this.expected = expected;
	}
	
	@Before
	public void setUp() throws Exception {
		post = new Post();
	}

	@Test
	public void giveAnStringNumber_ThenReturnIntegerValue() {
		post.setUpVotes(value);
		
		assertThat(post.getUpVotes()).isEqualTo(expected);
	}
	
}
