package idwall.desafio.crawlers.mapper;

import org.openqa.selenium.WebElement;

import idwall.desafio.crawlers.service.SubredditContentReader;

/**
 * <p>Interface usada pelo {@link SubredditContentReader} para mapear os elementos web retornados pelo selenium.</p>
 * 
 * @author higor.azevedo
 *
 * @param <T>
 */
@FunctionalInterface
public interface CrawlerMapper<T> {

	T getResultSet(WebElement elements);
	
}
