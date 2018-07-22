package idwall.desafio.crawlers.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.telegram.telegrambots.TelegramBotsApi;

import idwall.desafio.crawlers.service.RedditTelegramBot;
import idwall.desafio.crawlers.service.SubredditContentReader;

@Configuration
@ConditionalOnProperty(prefix="org.telegram.bot", name="key")
public class TelegramBotCommandLineConfig implements CommandLineRunner {

	private Logger log =  LoggerFactory.getLogger(TelegramBotCommandLineConfig.class);
	
	@Bean
	public RedditTelegramBot redditTelegramBot(@Value("${org.telegram.bot.key}") String key, 
											  SubredditContentReader reader) {
		return new RedditTelegramBot(key, reader);
	}

	@Override
	public void run(String... args) throws Exception {
		log.info("Starting telegram bot...");
		
		TelegramBotsApi botsApi = new TelegramBotsApi();

		botsApi.registerBot(redditTelegramBot(null, null));

	}

}
