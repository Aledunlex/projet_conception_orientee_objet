package SportsCompet.strategies;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import SportsCompet.Competitor;
import SportsCompet.Strategy;
import SportsCompet.StrategyTestBase;
import SportsCompet.competitions.League;
import SportsCompet.mocks.MockLeague;

public abstract class ComplexSelectionByRankStrategyTest extends StrategyTestBase {

	protected static int score;
	protected Competitor expectedGp1;
	protected Competitor expectedGp2;
	protected Competitor expectedGp3;
	protected Competitor expectedGp4;
	protected List<Competitor> expectedResult;

	protected abstract int determineRelevantScore();

	protected abstract Strategy createStrategy();

	@Override
	@BeforeEach
	public void before() {
		super.before();
		determineExpectedResult();
	}

	protected void determineExpectedResult() {
		score = 0;
		expectedGp1 = new Competitor(String.valueOf(alphabet[++count]));
		expectedGp2 = new Competitor(String.valueOf(alphabet[++count]));
		expectedGp3 = new Competitor(String.valueOf(alphabet[++count]));
		expectedGp4 = new Competitor(String.valueOf(alphabet[++count]));
		expectedResult = new ArrayList<Competitor>();
		expectedResult.add(0, expectedGp1);
		expectedResult.add(1, expectedGp2);
		expectedResult.add(2, expectedGp3);
		expectedResult.add(3, expectedGp4);

		int i = 0;
		for (League pool : pools) {
			MockLeague mockPool = (MockLeague) pool;
			mockPool.play();
			Competitor comp = expectedResult.get(i);
			mockPool.addCompetitor(comp, determineRelevantScore());
			i++;
		}

	}

	@Test
	public void StrategyReturnsExpectedListOfCompetitorsTest() {
		Set<Competitor> resSet = strat.determineFinalists(pools);
		List<Competitor> res = List.copyOf(resSet);
		assertEquals(pools.size(), res.size());
		assertEquals(expectedResult.size(), res.size());

		if (!res.containsAll(expectedResult)) {
			List<Integer> obtainedScores = new ArrayList<Integer>();
			List<Integer> expectedScores = new ArrayList<Integer>();
			for (int i = 0; i < expectedResult.size(); i++) {
				expectedScores.add(pools.get(i).getScore(expectedResult.get(i)));
				obtainedScores.add(pools.get(i).getScore(res.get(i)));
			}
			assertEquals(expectedScores.size(), obtainedScores.size());
			assertTrue(expectedScores.containsAll(obtainedScores));
		}
	}

}
