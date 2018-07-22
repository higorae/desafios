package idwall.desafio.crawlers.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;

import idwall.desafio.crawlers.App;
import idwall.desafio.crawlers.service.EmptySubredditResponseException;
import idwall.desafio.crawlers.service.SubredditContentReader;

/**
 * Classe de execução, via linha de comando, do crawler
 * 
 * @author higor.azevedo
 *
 */
public class CommandLineConfig implements CommandLineRunner {

	private Logger log =  LoggerFactory.getLogger(App.class);

	private SubredditContentReader reader;
	
	public CommandLineConfig(SubredditContentReader reader) {
		super();
		this.reader = reader; 
	}

	@Override
	public void run(String... args) throws Exception {
		log.info("Starting command line...");
		
		try {
			for (String subreddit : args[0].split(";")) {
				try {
					reader.read(subreddit, posts -> posts.forEach(System.out::println));
				} catch (EmptySubredditResponseException e) {
					log.info(e.getMessage());
				}
			}
		} catch(ArrayIndexOutOfBoundsException e) {
			log.info("Deve ser informado pelo menos um assunto.");
		}
		
	}
	
}
