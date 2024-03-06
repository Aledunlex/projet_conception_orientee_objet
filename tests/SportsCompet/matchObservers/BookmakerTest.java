package SportsCompet.matchObservers;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import SportsCompet.Competitor;
import SportsCompet.MatchObserverTest;

public class BookmakerTest extends MatchObserverTest {
	
	private Map<Competitor, Integer> odds;
	private Bookmaker bookmaker;

	@Override
	protected MatchObserver createObserver() {
		return new Bookmaker("Some Bookmaker");
	}
	
	@BeforeEach
	public void init() {
		super.init();
		bookmaker = (Bookmaker) createObserver();
		match.register(bookmaker);
	}

	@Test
	public void reactToCompetitionTest() {
		match.play(pat, mich);
		odds = bookmaker.getOdds();
		Competitor loser1 = match.getLoser();
		Competitor winner1 = match.getWinner();
		int oddsLoser1 = odds.get(loser1);
		int oddsWinner1 = odds.get(winner1);
		
		assertEquals(1, oddsLoser1);
		assertEquals(0, oddsWinner1);
		assertEquals(2, odds.size());
		
		match.play(pat, mich);
		odds = bookmaker.getOdds();
		Competitor loser2 = match.getLoser();
		Competitor winner2 = match.getWinner();
		int oddsLoser2 = odds.get(loser2);
		int oddsWinner2 = odds.get(winner2);
		
		boolean wonTwiceInRow = winner1.equals(winner2);
		int expectedWinnerOdds = 0;
		int expectedLoserOdds = wonTwiceInRow?2:1;
		
		assertEquals(expectedWinnerOdds, oddsWinner2);
		assertEquals(expectedLoserOdds, oddsLoser2);
		assertEquals(2, odds.size());
		
		match.play(new Competitor ("Jean"), new Competitor ("Harry"));
		match.play(new Competitor ("Salomone"), new Competitor ("Angele"));
		match.play(new Competitor ("Michmich"), new Competitor ("Anonyme"));
		match.play(new Competitor ("Philippette"), new Competitor ("Jeanne"));
		
		odds = bookmaker.getOdds();
		assertEquals(10, odds.size());
	}


}
