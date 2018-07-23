package idwall.desafio.string.align;


import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.Before;
import org.junit.Test;

public class JustifyAlignmentTest {

	private static final int DEFAULT_LINE_SIZE = 40;
	
	private JustifyAlignment alignment;
	
	
	@Before
	public void setup() {
		alignment = new JustifyAlignment();
	}
	
	@Test
	public void givenASingleLineText_ThenJustifyIt() {
		String singleLineText = "and the earth. Now the earth was";
		String aligned = alignment.align(singleLineText, DEFAULT_LINE_SIZE);
		
		assertThat(aligned).isEqualTo("and   the   earth.  Now  the  earth  was");
		assertThat(aligned.length()).isEqualTo(DEFAULT_LINE_SIZE);
	}
	
	@Test
	public void givenASingleLineTextWithWhitespaceAtTheEnd_ThenJustifyIt() {
		String singleLineText = "and the earth. Now the earth was ";
		String aligned = alignment.align(singleLineText, DEFAULT_LINE_SIZE);
		
		assertThat(aligned).isEqualTo("and   the   earth.  Now  the  earth  was");
		assertThat(aligned.length()).isEqualTo(DEFAULT_LINE_SIZE);
	}
	
	@Test
	public void givenATextLengthEqualsToLineSize_ThenJustifyIt() {
		String singleLineText = "In the beginning God created the heavens";
		String aligned = alignment.align(singleLineText, DEFAULT_LINE_SIZE);
		
		assertThat(aligned).isEqualTo("In the beginning God created the heavens");
		assertThat(aligned.length()).isEqualTo(DEFAULT_LINE_SIZE);
	}
	
	@Test
	public void givenAEmptyText_ThenReturnIt() {
		String emptyString = " ";
		String nullString = null;
		
		assertThat(alignment.align(emptyString, DEFAULT_LINE_SIZE)).isEqualTo(emptyString);
		assertThat(alignment.align(nullString, DEFAULT_LINE_SIZE)).isEqualTo(null);
	}
	
	@Test
	public void givenALargeSingleLineText_And_LineSizeSmallerThanText_ThrowsIllegalArgumentException() {
		String singleLineText = "In the beginning God created the heavens.";
		
		assertThatThrownBy(() -> alignment.align(singleLineText, DEFAULT_LINE_SIZE)).isInstanceOf(IllegalArgumentException.class);
	}

}
