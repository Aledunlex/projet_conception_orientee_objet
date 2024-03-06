package SportsCompet.matchObservers;

import SportsCompet.ObserverInterface;

/**
 * A MatchObserver can be registered to a Competition, or directly to a Match.
 * It will react accordingly to its reactToMatch() implementation, called at the
 * end of every Match.
 */
public abstract class MatchObserver implements ObserverInterface {

	/**
	 * A unique name for an observer, for display clarity
	 */
	protected String name;

	/**
	 * Creates an observer with a unique name used for display.
	 * 
	 * @param name of this observer
	 */
	public MatchObserver(String name) {
		this.name = name;
	}

}
