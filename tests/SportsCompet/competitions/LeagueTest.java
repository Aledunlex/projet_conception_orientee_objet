package SportsCompet.competitions;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;

import SportsCompet.Competition;
import SportsCompet.CompetitionTest;
import SportsCompet.Competitor;

public class LeagueTest extends CompetitionTest {

	protected Competition createCompetition(List<Competitor> competitors) throws IllegalArgumentException {
		return new League("l1",competitors);
	}

	@Test
	public void everyMatchDoneAtTheEndOfALeagueWithScoresTest() {
		competition.play();
		int count = 0;
		int listSize = competitors.size();
		for (Competitor competitor : competition.getCompetitors()) {
			count += competition.getScore(competitor);
		}
		assertEquals(listSize * (listSize - 1), count);
	}

}