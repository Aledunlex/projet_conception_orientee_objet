package SportsCompet.strategies;

import SportsCompet.Strategy;

public class EveryHighestRankedTest extends ComplexSelectionByRankStrategyTest {

	protected int determineRelevantScore() {
		if (score == 0)
			score = 50;
		return score++;
	}

	protected Strategy createStrategy() {
		return new EveryHighestRanked(new StrategyBase());
	}

}
