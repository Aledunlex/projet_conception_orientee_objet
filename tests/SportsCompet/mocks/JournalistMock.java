package SportsCompet.mocks;

import SportsCompet.Match;
import SportsCompet.matchObservers.Journalist;

/**
 * Only used for testing. Adds one to their counter of observed matches.
 */
public class JournalistMock extends Journalist {

	/**
	 * Counter of observed matches
	 */
	public int observedMatches;
	
	/**
	 * A journalist will add one to their counter after any observed match
	 * @param name of a mock journalist
	 */
	public JournalistMock(String name) {
		super(name);
		observedMatches = 0;
	}

	@Override
	public void reactToMatch(Match match) {
		super.reactToMatch(match);
		++observedMatches;
	}

}
