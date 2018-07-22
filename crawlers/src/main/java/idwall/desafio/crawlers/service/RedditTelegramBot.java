package idwall.desafio.crawlers.service;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;

import idwall.desafio.crawlers.domain.Post;

/**
 * Responsável por manipular os comandos enviados pelo bot do telegram.
 * 
 * @author higor.azevedo
 *
 */
public class RedditTelegramBot extends TelegramLongPollingBot {

	private Logger log =  LoggerFactory.getLogger(RedditTelegramBot.class);
	
	private String key;
	
	private SubredditContentReader reader;

	public RedditTelegramBot(String key, SubredditContentReader reader) {
		this.key = key;
		this.reader = reader;
	}

	@Override
	public void onUpdateReceived(Update update) {
		if (isCommand(update)) {
			String text = update.getMessage().getText();

			String[] commandSplit = text.split("\\s+");
			
			if(isInvalidCommand(commandSplit)) {
				sendMessage(update, "<strong>Comando inválido!</strong>\nComando disponível:\n<strong>/NadaParaFazer</strong> [Lista de assuntos separados por ponto-e-vírgula]");
				return;
			}
			
			try {
				for (String subreddit : commandSplit[1].split(";")) {
					try {
						reader.read(subreddit, posts -> sendMessage(update, getText(posts)));
					} catch (EmptySubredditResponseException e) {
						log.info(e.getMessage());
						sendMessage(update, e.getMessage());
					}
				}
			} catch(ArrayIndexOutOfBoundsException e) {
				log.info("Deve ser informado pelo menos um assunto.");
				sendMessage(update, "Deve ser informado pelo menos um assunto.");
			}
		}

	}

	private boolean isInvalidCommand(String[] commandSplit) {
		return !commandSplit[0].toLowerCase().equals("/nadaparafazer");
	}
	
	private boolean isCommand(Update update) {
		return update.hasMessage() && update.getMessage().hasText() && update.getMessage().isCommand();
	}
	
	private void sendMessage(Update update, String text) {
		SendMessage message = new SendMessage() 
				 .setChatId(update.getMessage().getChatId())
				 .setText(text).enableHtml(true);
		
		try {
			execute(message);
		} catch (TelegramApiException e) {
			e.printStackTrace();
		}
	}

	private String getText(List<Post> result) {
		return result.stream().map(it -> it.toString()).collect(Collectors.joining("\n"));
	}

	@Override
	public String getBotToken() {
		return key;
	}
	
	@Override
	public String getBotUsername() {
		return null;
	}

}
