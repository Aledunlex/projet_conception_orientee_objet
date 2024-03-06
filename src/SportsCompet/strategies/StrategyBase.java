package SportsCompet.strategies;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import SportsCompet.Competitor;
import SportsCompet.Strategy;
import SportsCompet.competitions.League;

/**
 * This Strategy will always returns an empty set of competitors. May be used as
 * a base for any decoration-based strategy, but never on its own.
 */
public class StrategyBase implements Strategy {

	/**
	 * Returns an empty set of competitors.
	 */
	public Set<Competitor> determineFinalists(List<League> groups) {
		return new HashSet<Competitor>();
	}

}
