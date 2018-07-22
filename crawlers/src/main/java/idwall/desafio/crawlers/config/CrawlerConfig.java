package idwall.desafio.crawlers.config;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import idwall.desafio.crawlers.service.SubredditContentReader;

@Configuration
public class CrawlerConfig {
	
	@Bean(destroyMethod="quit")
	public WebDriver config(@Value("${selenium.remote.url}") String rmeoteUrl) throws MalformedURLException {
		return new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), new ChromeOptions());
	}
	
	@Bean
	@ConditionalOnMissingBean
	public CommandLineRunner commandLineConfig(SubredditContentReader reader) {
		return new CommandLineConfig(reader);
	}
	
}
