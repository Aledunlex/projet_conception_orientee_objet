package SportsCompet.mocks;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import SportsCompet.Competitor;
import SportsCompet.Strategy;
import SportsCompet.competitions.League;

/**
 * Mock strategy object used to test the Strategy interface and the Master type
 * of Competition. Adds every single competitor to the finalists list.
 */
public class MockStratUnreasonnable implements Strategy {

	public int toBePicked;

	/**
	 * Used for testing purposes. Tries to add a competitor at an index the first
	 * League's competitor's list does not reach, systematically causing an
	 * OutOfBoundException.
	 */
	@Override
	public Set<Competitor> determineFinalists(List<League> pools) {
		List<Competitor> result = new ArrayList<Competitor>();

		toBePicked = pools.get(0).getCompetitors().size() * 2;

		for (League league : pools) {
			Competitor comp = (Competitor) league.ranking().keySet().toArray()[toBePicked];
			result.add(comp);
		}

		return Set.copyOf(result);
	}

}
