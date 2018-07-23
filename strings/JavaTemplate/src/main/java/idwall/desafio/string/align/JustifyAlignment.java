package idwall.desafio.string.align;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Justifica o texto adicionado espaços em branco entre as palavras até que chegue ao limite de caracteres por linha.
 * 
 * @author higor.azevedo
 *
 */
public class JustifyAlignment implements TextAlignment {

	@Override
	public String align(String text, int lineSize) {

		if(isEmptyText(text)) {
			return text;
		}
		
		text = text.trim();
		
		if(text.length() > lineSize) {
			throw new IllegalArgumentException("Text size is bigger than the line size.");
		}

		List<Integer> indexWhitespaces = getWhitespacePositions(text);
		
		StringBuilder line = new StringBuilder(text);
		
		Integer currentWhitespacePosition = 0;
		
		while(line.length() < lineSize) {
			line.insert(indexWhitespaces.get(currentWhitespacePosition) + currentWhitespacePosition, " ");
			
			currentWhitespacePosition = updateCurrentWhitespacePosition(indexWhitespaces, currentWhitespacePosition);
		}
		
		return line.toString();
	}

	private boolean isEmptyText(String text) {
		return text == null || text.trim().isEmpty();
	}

	private Integer updateCurrentWhitespacePosition(List<Integer> indexWhitespaces, Integer currentWhitespacePosition) {
		if(currentWhitespacePosition == indexWhitespaces.size() - 1) {
			currentWhitespacePosition = 0;
		} else {
			currentWhitespacePosition++;
		}
		
		return currentWhitespacePosition;
	}

	private List<Integer> getWhitespacePositions(String text) {
		List<Integer> indexWhitespaces = new ArrayList<>();
		Matcher matcher = Pattern.compile("\\s+").matcher(text);
		while(matcher.find()) {
			indexWhitespaces.add(matcher.end());
		}
		return indexWhitespaces;
	}

}
