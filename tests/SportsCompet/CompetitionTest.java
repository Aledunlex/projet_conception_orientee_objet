package SportsCompet;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import SportsCompet.util.Displayer;

public abstract class CompetitionTest {

	protected Competitor michel;
	protected Competitor patrick;
	protected Competitor jean;
	protected Competitor michmich;
	protected List<Competitor> competitors;
	protected Competition competition;

	protected abstract Competition createCompetition(List<Competitor> competitors) throws IllegalArgumentException;

	@BeforeEach
	public void before() {
		this.patrick = new Competitor("Patrick");
		this.michel = new Competitor("Michel");
		this.jean = new Competitor("Jean");
		this.michmich = new Competitor("MichMich");
		this.competitors = new ArrayList<Competitor>();
		competitors.add(jean);
		competitors.add(michel);
		competitors.add(michmich);
		competitors.add(patrick);
		this.competition = createCompetition(competitors);
	}

	@Test
	public void getAndAddScoreTest() {
		competition.setCompetitorScore(patrick, 0);
		assertEquals(0, competition.getScore(patrick));
		competition.addOnePoint(patrick);
		assertEquals(1, competition.getScore(patrick));
	}
	
	@Test
	public void afterPlayAtLeastOneCompetitorHasMoreThanZeroPointsTest() {
		competition.play();
		boolean positive = false;
		Displayer.useDisplay(competition.ranking);
		for (Competitor competitor : competition.getCompetitors()) {
			if (competition.getScore(competitor) > 0)
				positive = true;
		}
		assertTrue(positive);
	}

	@Test
	public void cantStartCompetitionWithoutAtLeastTwoCompetitorsTest() {
		List<Competitor> emptyList = new ArrayList<Competitor>();
		assertThrows(IllegalArgumentException.class, () -> {
			createCompetition(emptyList);
		});

		List<Competitor> oneCompetitor = new ArrayList<Competitor>();
		oneCompetitor.add(michel);
		assertThrows(IllegalArgumentException.class, () -> {
			createCompetition(oneCompetitor);
		});
	}

	@Test
	public void rankingReturnsAllCompetitorsAsKeyOfTheirCorrespondingScoreTest() {
		competition.play();
		Map<Competitor, Integer> rankings = competition.ranking();
		assertEquals(competitors.size(), rankings.size());
		List<Competitor> competitorsList = new ArrayList<Competitor>(rankings.keySet());
		for (Competitor competitor : competitorsList) {
			assertEquals(competition.getScore(competitor), rankings.get(competitor));
		}
	}

	@Test
	public void equalsTest() {
		Competition competEquals = createCompetition(competitors);
		Competition competNotEquals = createCompetition(Arrays.asList(new Competitor("Keenu"), new Competitor("Reeves"),
				new Competitor("Nukee"), new Competitor("Vesree")));
		assertTrue(this.competition.equals(competEquals));
		assertFalse(this.competition.equals(competNotEquals));
	}

}
