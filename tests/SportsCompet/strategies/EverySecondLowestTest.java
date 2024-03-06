package SportsCompet.strategies;

import SportsCompet.Strategy;
import SportsCompet.competitions.League;
import SportsCompet.mocks.MockLeague;

public class EverySecondLowestTest extends ComplexSelectionByRankStrategyTest {

	protected int determineRelevantScore() {
		if (score == 0)
			score = -2;
		return score;
	}

	protected Strategy createStrategy() {
		return new EverySecondLowest(new StrategyBase());
	}

	@Override
	public void determineExpectedResult() {
		super.determineExpectedResult();
		expectedResult.clear();
		for (League league : pools) {
			MockLeague mleague = (MockLeague) league;
			expectedResult.add(mleague.getBeforeLast());
		}
	}

}
