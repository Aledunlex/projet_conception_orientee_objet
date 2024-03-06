package SportsCompet;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import SportsCompet.matchObservers.MatchObserver;
import SportsCompet.matches.MatchRegular;

public abstract class MatchObserverTest {

	protected MatchObserver observer;
	protected MatchRegular match;
	protected Competitor pat;
	protected Competitor mich;
	
	@BeforeEach
	public void init() {
		match = new MatchRegular();
		pat = new Competitor("Patrick");
		mich = new Competitor("Jean-Michel");
		observer = createObserver();
		match.register(observer);
	}

	protected abstract MatchObserver createObserver();

	@Test
	public abstract void reactToCompetitionTest();

}
