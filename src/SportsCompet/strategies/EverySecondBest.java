package SportsCompet.strategies;

import java.util.Set;

import SportsCompet.Strategy;

/**
 * Selects every second-best competitor in their own pool
 */
public class EverySecondBest extends SelectionByRankStrategy {

	/**
	 * Uses the SelectionByRankStrategy to select every competitor at rank 1, ergo
	 * second best competitors of all pools.
	 * 
	 * @param element any implementation of the Strategy interface
	 */
	public EverySecondBest(Strategy element) {
		super(element, Set.of(1), false);
	}

}
