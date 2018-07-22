package idwall.desafio.string;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.Before;
import org.junit.Test;

import idwall.desafio.string.align.JustifyAlignment;

public class IdwallFormatterTest {
	
	private final static String SINGLE_LINE_TEXT = "In the beginning God created the heavens and the earth. Now the earth was formless and empty, darkness was over the surface of the deep, and the Spirit of God was hovering over the waters.\n"; 
	
	private StringFormatter formmatter;
	
	@Before
	public void setup() {
		formmatter = new IdwallFormatter();
	}
	
	@Test
	public void givenAText_ThenFormatText() {
		String[] lines = formmatter.format(SINGLE_LINE_TEXT).split("\n");
		
		assertThat(lines[0]).isEqualTo("In the beginning God created the heavens");
		assertThat(lines[1]).isEqualTo("and the earth. Now the earth was");
		assertThat(lines[2]).isEqualTo("formless and empty, darkness was over");
		assertThat(lines[3]).isEqualTo("the surface of the deep, and the Spirit");
		assertThat(lines[4]).isEqualTo("of God was hovering over the waters.");
	}
	
	@Test
	public void givenATextAndJustifyAlignment_ThenFormatText() {
		formmatter.setTextAlignment(new JustifyAlignment());
		String[] lines = formmatter.format(SINGLE_LINE_TEXT).split("\n");
		
		assertThat(lines[0]).isEqualTo("In the beginning God created the heavens");
		assertThat(lines[1]).isEqualTo("and   the   earth.  Now  the  earth  was");
		assertThat(lines[2]).isEqualTo("formless  and  empty,  darkness was over");
		assertThat(lines[3]).isEqualTo("the  surface of the deep, and the Spirit");
		assertThat(lines[4]).isEqualTo("of  God  was  hovering  over the waters.");
	}
	
	@Test
	public void givenAMultilineText_ThenFormatText() {
		String text = "In the beginning God created the heavens and the earth. Now the earth was formless and empty, darkness was over the surface of the deep, and the Spirit of God was hovering over the waters.\n"+
					  "\n"+
					  "And God said, \"Let there be light,\" and there was light. God saw that the light was good, and he separated the light from the darkness. God called the light \"day,\" and the darkness he called \"night.\" And there was evening, and there was morning - the first day.";
		
		String[] lines = formmatter.format(text).split("\n");
		
		assertThat(lines[0]).isEqualTo("In the beginning God created the heavens");
		assertThat(lines[4]).isEqualTo("of God was hovering over the waters.");
		assertThat(lines[5]).isEmpty();
		assertThat(lines[6]).isEqualTo("And God said, \"Let there be light,\" and");
		assertThat(lines[12]).isEqualTo("there was morning - the first day.");
		
	}
	
	@Test
	public void givenALineSizeBiggerThanTextLine_ThrowsIllegalArgumentException() {
		formmatter.setLimit(3);
		
		assertThatThrownBy(() -> { formmatter.format(SINGLE_LINE_TEXT); }).hasMessageStartingWith("LineSize must be bigger than")
																	     .isInstanceOf(IllegalArgumentException.class);
	}
	
	@Test
	public void givenAEmptyText_ThrowsIllegalArgumentException() {
		assertThatThrownBy(() -> { formmatter.format(null); }).isInstanceOf(IllegalArgumentException.class);
		assertThatThrownBy(() -> { formmatter.format(""); }).isInstanceOf(IllegalArgumentException.class);
	}
	
}
