package SportsCompet.competitions;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import SportsCompet.Competition;
import SportsCompet.CompetitionTest;
import SportsCompet.Competitor;
import SportsCompet.Strategy;
import SportsCompet.mocks.MockStratNotPowerOfTwo;
import SportsCompet.mocks.MockStratReasonnable;
import SportsCompet.mocks.MockStratUnreasonnable;
import SportsCompet.strategies.EveryLowestRanked;
import SportsCompet.strategies.StrategyBase;
import SportsCompet.util.MathsPlus;

public class MasterTest extends CompetitionTest {

	private Strategy mockStrat;
	private Master master;
	private final int groups = 2;

	@BeforeEach
	@Override
	public void before() {
		mockStrat = new MockStratReasonnable();
		super.before();
	}

	@Override
	protected Competition createCompetition(List<Competitor> competitors)
			throws IllegalArgumentException, IndexOutOfBoundsException {
		master = new Master("m1",competitors, mockStrat, groups);
		return master;
	}

	@Test
	public void cantStartMasterIfItsStrategyDoesntFitCompetitorsAmountTest() {
		for (int i = 0; i < 10; i++) {
			competitors.add(new Competitor(String.valueOf(i)));
		}
		assertThrows(IndexOutOfBoundsException.class, () -> {
			new Master("m2",competitors, new MockStratUnreasonnable(), groups);
		});
	}

	@Test
	public void cantStartMasterIfItsStrategyReturnsAPowerOfTwoSizeListTest() {
		assertFalse(MathsPlus.isPowerOfTwo(new MockStratNotPowerOfTwo().determineFinalists(null).size()));
		assertThrows(IllegalArgumentException.class, () -> {
			new Master("m3",competitors, new MockStratNotPowerOfTwo(), groups);
		});
	}

	@Test
	public void canStartMasterIfItsStrategyFitsCompetitorsAmountTest() {
		for (int i = 0; i < 10; i++) {
			competitors.add(new Competitor(String.valueOf(i)));
		}
		new Master("m4",competitors, new MockStratReasonnable(), groups);
	}

	@Test
	@Override
	public void equalsTest() {
		super.equalsTest();
		Master competEquals = (Master) createCompetition(competitors);
		Master competNotEqualsOne = new Master("m5",competitors, mockStrat, groups - 1);
		Master competNotEqualsTwo = new Master("m6",competitors, new EveryLowestRanked(new StrategyBase()), groups);
		assertTrue(this.master.equals(competEquals));
		assertFalse(this.master.equals(competNotEqualsOne));
		assertFalse(this.master.equals(competNotEqualsTwo));
	}

}
