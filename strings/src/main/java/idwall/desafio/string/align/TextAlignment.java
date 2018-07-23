package idwall.desafio.string.align;

/**
 * Interface usada para aplicar algum tipo de alinhamento ao texto.
 * 
 * @author higor.azevedo
 *
 */
public interface TextAlignment {

	/**
	 * Alinha o texto podendo ou não usar o número máximo de caracteres por linha
	 * @param text - texto
	 * @param lineSize - número de caracteres por linha
	 * @return
	 */
	String align(String text, int lineSize);
	
}
