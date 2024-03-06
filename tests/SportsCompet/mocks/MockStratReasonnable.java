package SportsCompet.mocks;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import SportsCompet.Competitor;
import SportsCompet.Strategy;
import SportsCompet.competitions.League;
import SportsCompet.util.MathsPlus;

/**
 * Mock strategy object used to test the Strategy interface and the Master type
 * of Competition. Adds every single competitor to the finalists list and
 * removes any necessary competitor so that the list's size is a power of two.
 */
public class MockStratReasonnable implements Strategy {

	/**
	 * Used for testing purposes. Adds every single competitor to the finalists list
	 * and removes any necessary competitor so that the list's size is a power of
	 * two.
	 */
	@Override
	public Set<Competitor> determineFinalists(List<League> pools) {
		List<Competitor> result = new ArrayList<Competitor>();

		for (League league : pools) {
			Set<Competitor> comp = league.ranking().keySet();
			result.addAll(comp);
		}

		return Set.copyOf(result.subList(0, MathsPlus.log2(result.size())));
	}

}
