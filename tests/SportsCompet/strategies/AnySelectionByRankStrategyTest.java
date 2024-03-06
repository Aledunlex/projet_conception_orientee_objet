package SportsCompet.strategies;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.util.Set;

import org.junit.jupiter.api.Test;

import SportsCompet.Strategy;

public class AnySelectionByRankStrategyTest {

	/*
	 * Most relevant tests for SelectionByRankStrategy may be found in
	 * ComplexSelectionByRankStrategyTest.java
	 */

	@Test
	public void equalsTest() {
		Strategy base = new StrategyBase();
		SelectionByRankStrategy one = new SelectionByRankStrategy(base, Set.of(1, 2, 3), false);
		SelectionByRankStrategy oneEquals = new SelectionByRankStrategy(base, Set.of(3, 2, 1), false);
		SelectionByRankStrategy two = new SelectionByRankStrategy(base, Set.of(1, 2, 3), true);
		SelectionByRankStrategy three = new SelectionByRankStrategy(base, Set.of(1, 2, 5), false);
		assertEquals(one, oneEquals);
		assertNotEquals(one, two);
		assertNotEquals(one, three);
	}

}
