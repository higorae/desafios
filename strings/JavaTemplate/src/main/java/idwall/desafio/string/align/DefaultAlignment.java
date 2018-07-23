package idwall.desafio.string.align;

/**
 * Estrategia padrão, apenas remove o espaço em branco do texto.
 * 
 * @author higorazevedo
 *
 */
public class DefaultAlignment implements TextAlignment {

	@Override
	public String align(String text, int lineSize) {
		return text.trim();
	}
}
