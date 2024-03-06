package SportsCompet.strategies;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import SportsCompet.Competitor;
import SportsCompet.Strategy;
import SportsCompet.StrategyTestBase;
import SportsCompet.competitions.League;

public class StrategyDecorationTest extends StrategyTestBase {

	private Strategy strat1;
	private Strategy strat2;

	@BeforeEach
	@Override
	public void before() {
		super.before();
		for (League pool : pools) {
			pool.play();
		}
		Strategy base = new StrategyBase();
		strat1 = new EveryHighestRanked(base);
		strat2 = new EverySecondLowest(base);
	}

	@Test
	public void imbriquedStrategyObtainedSetIsSumOfTwoDifferentStrategieTest() {
		Set<Competitor> imbriquedObtained = strat.determineFinalists(pools);
		Set<Competitor> strat1Obtained = strat1.determineFinalists(pools);
		Set<Competitor> strat2Obtained = strat2.determineFinalists(pools);
		assertEquals(strat1Obtained.size() + strat2Obtained.size(), imbriquedObtained.size());
		assertTrue(imbriquedObtained.containsAll(strat1Obtained) && imbriquedObtained.containsAll(strat2Obtained));
	}

	@Test
	public void imbriquedStrategyObtainedSetIfTwoSameStrategiesTest() {
		Strategy otherStrat = new EverySecondLowest(new EverySecondLowest(new StrategyBase()));
		Set<Competitor> otherObtained = otherStrat.determineFinalists(pools);
		Set<Competitor> strat2Obtained = strat2.determineFinalists(pools);
		assertEquals(strat2Obtained.size(), otherObtained.size());
		assertTrue(otherObtained.containsAll(strat2Obtained));
	}

	@Override
	protected Strategy createStrategy() {
		return new EveryHighestRanked(new EverySecondLowest(new StrategyBase()));
	}

}
