package idwall.desafio.crawlers.service;

public class EmptySubredditResponseException extends Exception{

	private static final long serialVersionUID = 1L;

	public EmptySubredditResponseException(String subredit) {
		super(String.format("Não foi encontrado nada empolgante sobre %s" , subredit));
	}
	
}
