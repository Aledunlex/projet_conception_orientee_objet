package SportsCompet.matchObservers;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import SportsCompet.MatchObserverTest;
import SportsCompet.Match;
import SportsCompet.matches.MatchRegular;
import SportsCompet.mocks.JournalistMock;

public class JournalistTest extends MatchObserverTest {

	private JournalistMock mock;
	
	@Override
	protected MatchObserver createObserver() {
		return new JournalistMock("Le Gorafi");
	}
	
	@BeforeEach
	public void init() {
		super.init();
		mock = (JournalistMock) createObserver();
		match.register(mock);
	}

	@Test
	public void reactToCompetitionTest() {
		assertEquals(0, mock.observedMatches);
		match.play(pat, mich);
		assertEquals(1, mock.observedMatches);
		match.unRegister(mock);
		match.play(pat, mich);
		assertEquals(1, mock.observedMatches);
		Match another = new MatchRegular();
		another.register(mock);
		another.play(pat, mich);
		assertEquals(2, mock.observedMatches);
	}

}
