package idwall.desafio.string.align;

public class DefaultAlignment implements TextAlignment {

	@Override
	public String align(String text, int lineSize) {
		return text.trim();
	}

}
