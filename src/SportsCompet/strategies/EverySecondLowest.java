package SportsCompet.strategies;

import java.util.Set;

import SportsCompet.Strategy;

/**
 * Selects every second-to-last competitor in their own pool
 */
public class EverySecondLowest extends SelectionByRankStrategy {

	/**
	 * Uses the SelectionByRankStrategy to select every competitor at rank l, on the
	 * reversed list obtained from rankings, ergo second lowest ranked competitor of
	 * all pools.
	 * 
	 * @param element any implementation of the Strategy interface
	 */
	public EverySecondLowest(Strategy element) {
		super(element, Set.of(1), true);
	}

}
