package idwall.desafio.crawlers.utils;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class WebElementUtils {
	
	public static WebElement createWebElement(Integer upVotes) {
		WebElement el = mock(WebElement.class);
		
		WebElement el2 = mock(WebElement.class);
		
		when(el.findElement(By.cssSelector("a.title"))).thenReturn(el2);
		when(el2.getText()).thenReturn("Título thread");
		when(el2.getAttribute("href")).thenReturn("href://teste.com");
		
		WebElement el3 = mock(WebElement.class);
		when(el.findElement(By.cssSelector("a.comments"))).thenReturn(el3);
		when(el3.getAttribute("href")).thenReturn("href://teste.com/comments");
		
		WebElement el4 = mock(WebElement.class);
		when(el.findElement(By.cssSelector(".score.unvoted"))).thenReturn(el4);
		when(el4.getAttribute("textContent")).thenReturn(upVotes+"");
		
		return el;
	}		
}
