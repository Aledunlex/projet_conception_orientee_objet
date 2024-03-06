package SportsCompet;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import SportsCompet.competitions.League;
import SportsCompet.competitions.Tournament;
import SportsCompet.matchObservers.Bookmaker;
import SportsCompet.matchObservers.Journalist;

public abstract class MatchTest {

	protected Competitor michel;
	protected Competitor patrick;
	protected List<Competitor> competitors;
	protected Match match;

	protected abstract Match createMatch();

	@BeforeEach
	public void init() {
		this.patrick = new Competitor("Patrick");
		this.michel = new Competitor("Michel");
		this.competitors = Arrays.asList(patrick, michel);
		this.match = createMatch();
	}

	@Test
	public void playingAMatchGivesExactlyOnePointToExactlyOneCompetitorTest() {
		League leag = new League("l", competitors);
		match.register(leag);
		match.play(patrick, michel);
		assertFalse(0 == (leag.getScore(michel) - leag.getScore(patrick)));
	}

	@Test
	public void playingAMatchGivesAWinnerDifferentFromTheLoserTest() {
		League leag = new League("l", competitors);
		match.register(leag);
		match.play(patrick, michel);
		assertEquals(1, leag.getScore(match.getWinner()));
		assertEquals(0, leag.getScore(match.getLoser()));
		assertFalse(match.getWinner().equals(match.getLoser()));
	}

	@Test
	public void playingAMatchAddsOneToPlayedMatchNumberForBothCompetitorTest() {
		Tournament tourn = new Tournament("t", competitors);
		match.register(tourn);
		match.play(patrick, michel);
		for (Competitor competitor : competitors)
			assertEquals(1, tourn.getNbOfPlayedMatches(competitor));
	}
	
	@Test
	public void otherObserversHandlingTest() {
		assertEquals(1, match.getObservers().size());
		Journalist journalist = new Journalist("jojo");
		Bookmaker bookmaker = new Bookmaker("bobo");
		match.register(journalist);
		match.register(bookmaker);		
		assertEquals(3, match.getObservers().size());
		assertTrue(match.getObservers().containsAll(Set.of(journalist, bookmaker)));
		match.unRegister(bookmaker);
		match.unRegister(journalist);
		assertEquals(1, match.getObservers().size());
		assertFalse(match.getObservers().contains(journalist) || match.getObservers().contains(bookmaker));
	}

	@Test
	public void equalsTest() {
		Match matchEquals = createMatch();
		Match notEquals = createMatch();
		this.match.play(patrick, michel);
		matchEquals.play(patrick, michel);
		notEquals.play(new Competitor("Jacques"), new Competitor("Poulou"));
		matchEquals.setWinner(this.match.winner);
		matchEquals.setLoser(this.match.loser);
		assertTrue(this.match.equals(matchEquals));
		assertFalse(this.match.equals(notEquals));
	}

}
