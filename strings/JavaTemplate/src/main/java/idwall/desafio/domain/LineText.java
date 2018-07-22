package idwall.desafio.domain;

public class LineText {

	private StringBuilder line;
	
	public LineText() {
		this.line = new StringBuilder();
	}

	public void addWord(String word) {
		line.append(word.trim()).append(" ");
	}
	
	public int getLineLength() {
		return line.toString().length();
	}
		
	@Override
	public String toString() {
		return line.toString();//.trim();
	}
}
