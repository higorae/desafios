package idwall.desafio.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import idwall.desafio.string.align.TextAlignment;

public class Text {

	private List<LineText> lines;
	
	private int lineSize;
	
	public Text(int lineSize) {
		this.lines = new ArrayList<>();
		this.lineSize = lineSize;
	}
	
	public void addNewLine(LineText line) {
		lines.add(line);
	}
	
	public String print(TextAlignment alignment) {
		return lines.stream().map(it -> alignment.align(it.toString(), lineSize)).collect(Collectors.joining("\n"));
	}
	
}
