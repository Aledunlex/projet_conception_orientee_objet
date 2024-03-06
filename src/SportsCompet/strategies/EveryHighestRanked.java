package SportsCompet.strategies;

import java.util.Set;

import SportsCompet.Strategy;

/**
 * Selects every competitor who won in their own pool
 */
public class EveryHighestRanked extends SelectionByRankStrategy {

	/**
	 * Uses the SelectionByRankStrategy to select every competitor at rank 0, ergo
	 * every competitor who won in their own pool.
	 * 
	 * @param element any implementation of the Strategy interface
	 */
	public EveryHighestRanked(Strategy element) {
		super(element, Set.of(0), false);
	}

}
