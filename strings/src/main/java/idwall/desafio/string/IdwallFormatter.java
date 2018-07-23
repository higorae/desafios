package idwall.desafio.string;

import idwall.desafio.domain.LineText;
import idwall.desafio.domain.Text;

/**
 * Created by Rodrigo Catão Araujo on 06/02/2018.
 */
public class IdwallFormatter extends StringFormatter {

    /**
     * Should format as described in the challenge
     *
     * @param text
     * @return
     */
    @Override
    public String format(String text) {
    		if(text == null || text.trim().isEmpty()) {
    			throw new IllegalArgumentException("Empyt text cannot be formated");
    		}
    		
    		Text myText = new Text(limit);
    		String[] paragraphs = text.split("\n");
    		
    		for (int i = 0; i < paragraphs.length; i++) {
    			String[] words = paragraphs[i].split(" ");
    			LineText line = new LineText();
    			
    			for (String it : words) {
    				
    				if(it.length() > limit) {
    					throw new IllegalArgumentException(String.format("LineSize must be bigger than %s size", it.length()));
    				}
    				
        			int total = it.length() + line.getLineLength();
        			
        			if(total > limit) {
        				line = new LineText();
        			}
        			
        			//add new line
        			if(line.getLineLength() == 0) {
        				myText.addNewLine(line);
        			}
        			
        			line.addWord(it);
    			}
    			
    		}
    		
    		return myText.print(alignment);
    }
    
}
