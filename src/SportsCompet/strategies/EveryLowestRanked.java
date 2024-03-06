package SportsCompet.strategies;

import java.util.Set;

import SportsCompet.Strategy;

/**
 * Selects every competitor at the bottom of rankings in their own pool
 */
public class EveryLowestRanked extends SelectionByRankStrategy {

	/**
	 * Uses the SelectionByRankStrategy to select every competitor at rank 0, on the
	 * reversed list obtained from rankings, ergo every competitor with the worst
	 * score in their own pool.
	 * 
	 * @param element any implementation of the Strategy interface
	 */
	public EveryLowestRanked(Strategy element) {
		super(element, Set.of(0), true);
	}

}
