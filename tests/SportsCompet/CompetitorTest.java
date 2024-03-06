package SportsCompet;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CompetitorTest {

	private Competitor patrick;

	@BeforeEach
	public void before() {
		this.patrick = new Competitor("Patrick");
	}

	@Test
	public void getNameTest() {
		assertEquals("Patrick", patrick.getName());
	}

	@Test
	public void equalsTest() {
		assertTrue(this.patrick.equals(new Competitor("Patrick")));
		assertFalse(this.patrick.equals(new Competitor("Michel")));
	}

	@Test
	public void hashCodeTest() {
		assertTrue(patrick.hashCode() == new Competitor("Patrick").hashCode());
		assertFalse(patrick.hashCode() == new Competitor("Michel").hashCode());
	}

}