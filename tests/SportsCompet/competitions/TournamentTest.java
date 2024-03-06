package SportsCompet.competitions;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;

import org.junit.jupiter.api.Test;

import SportsCompet.Competition;
import SportsCompet.CompetitionTest;
import SportsCompet.Competitor;
import SportsCompet.util.MathsPlus;

public class TournamentTest extends CompetitionTest {

	protected Competition createCompetition(List<Competitor> competitors) throws IllegalArgumentException {
		return new Tournament("t1",competitors);
	}

	@Test
	public void getAndAddNbOfPlayedMatchesTest() {
		Tournament competit = (Tournament) competition;
		competit.setNbOfPlayedMatch(patrick, 0);
		assertEquals(0, competit.getNbOfPlayedMatches(patrick));
		competit.addNbOfPlayedMatch(patrick);
		assertEquals(1, competit.getNbOfPlayedMatches(patrick));
	}
	
	@Test
	public void cantStartTournamentIfCompetitorsAmountIsntAPowerOfTwoTest() {
		for (int i = 5; i < 50; i++) {
			competitors.add(new Competitor(String.valueOf(i)));
			if (!MathsPlus.isPowerOfTwo(competitors.size())) {
				assertThrows(IllegalArgumentException.class, () -> {
					createCompetition(competitors);
				});
			}
		}
	}

	@Test
	public void everyMatchDoneAtTheEndOfATournamentWithScoresTest() {
		competition.play();
		int count = 0;
		int listSize = competitors.size();
		for (Competitor competitor : competition.getCompetitors()) {
			count += competition.getScore(competitor);
		}
		assertEquals(listSize - 1, count);
	}

	@Test
	public void everyMatchDoneAtTheEndOfATournamentWithMatchesTest() {
		Tournament competit = (Tournament) competition;
		competit.play();
		Competitor winner = new Competitor("winner");
		for (Competitor competitor : competit.getCompetitors()) {
			if (competit.getScore(competitor) > competit.getScore(winner)) {
				winner = competitor;
			}
		}
		List<Competitor> losingCompetitor = competit.getCompetitors();
		losingCompetitor.remove(winner);
		for (Competitor competitor : losingCompetitor) {
			assertEquals(competit.getNbOfPlayedMatches(competitor), competit.getScore(competitor) + 1);
		}
		assertEquals(competit.getNbOfPlayedMatches(winner), competit.getScore(winner));
	}

}
