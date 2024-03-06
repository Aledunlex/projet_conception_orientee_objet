package SportsCompet.strategies;

import java.util.List;

import SportsCompet.Competitor;
import SportsCompet.Strategy;
import SportsCompet.competitions.League;
import SportsCompet.mocks.MockLeague;

public class EverySecondBestTest extends ComplexSelectionByRankStrategyTest {

	protected int determineRelevantScore() {
		if (score == 0)
			score = 50;
		return score++;
	}

	protected Strategy createStrategy() {
		return new EverySecondBest(new StrategyBase());
	}

	@Override
	public void determineExpectedResult() {
		super.determineExpectedResult();

		int bestScore1 = pools.get(0).getScore(expectedGp1);
		expectedGp1 = new Competitor("secondBest1");
		int bestScore2 = pools.get(1).getScore(expectedGp2);
		expectedGp2 = new Competitor("secondBest2");
		int bestScore3 = pools.get(2).getScore(expectedGp3);
		expectedGp3 = new Competitor("secondBest3");
		int bestScore4 = pools.get(3).getScore(expectedGp4);
		expectedGp4 = new Competitor("secondBest4");

		expectedResult = List.of(expectedGp1, expectedGp2, expectedGp3, expectedGp4);
		List<Integer> expectedScores = List.of(bestScore1 - 1, bestScore2 - 1, bestScore3 - 1, bestScore4 - 1);

		int i = 0;
		for (League pool : pools) {
			MockLeague mockPool = (MockLeague) pool;
			Competitor comp = expectedResult.get(i);
			mockPool.addCompetitor(comp, expectedScores.get(i));
			i++;
		}
	}

}
