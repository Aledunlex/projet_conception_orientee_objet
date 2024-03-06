package SportsCompet.strategies;

import java.util.List;
import java.util.Set;

import SportsCompet.Competitor;
import SportsCompet.Strategy;
import SportsCompet.competitions.League;

/**
 * Decorates an element implementing the Strategy interface. Can be a
 * StrategyDecorator used to decorate another StrategyDecorator. The purpose is
 * to be able to create strategies working as standalones or in combination of
 * any other strategy.
 */
public abstract class StrategyDecorator implements Strategy {

	/**
	 * This element must at least be a StrategyBase() for which the
	 * determineFinalists() methods returns an empty Set.
	 */
	private Strategy element;

	/**
	 * Decorates an element implementing the Strategy interface. Can be a
	 * StrategyDecorator used to decorate another StrategyDecorator.
	 * 
	 * @param element any implementation of the Strategy interface
	 */
	public StrategyDecorator(Strategy element) {
		this.element = element;
	}

	/**
	 * Returns the finalists according to this.element method
	 */
	public Set<Competitor> determineFinalists(List<League> groups) {
		return element.determineFinalists(groups);
	}

}
