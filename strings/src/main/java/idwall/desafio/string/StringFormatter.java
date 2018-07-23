package idwall.desafio.string;

import idwall.desafio.string.align.DefaultAlignment;
import idwall.desafio.string.align.TextAlignment;

/**
 * Created by Rodrigo Catão Araujo on 06/02/2018.
 */
public abstract class StringFormatter {

    protected Integer limit;

    protected TextAlignment alignment;

    public StringFormatter() {
        this.limit = 40;
        this.alignment = new DefaultAlignment();
    }

    /**
     * It receives a text and should return it formatted
     *
     * @param text
     * @return
     */
    public abstract String format(String text);
    
    /**
     * Set limit characters perline
     * @param limit
     */
    public void setLimit(Integer limit) {
		this.limit = limit;
	}
    
    public void setTextAlignment(TextAlignment alignment) {
		this.alignment = alignment;
    }
}
