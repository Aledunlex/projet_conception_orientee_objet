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
 * of Competition. Adds an amount that is not power of two of Competitors to the
 * finalists list.
 */
public class MockStratNotPowerOfTwo implements Strategy {

	/**
	 * Used for testing purposes. Tries to add a competitor at an index the first
	 * League's competitor's list does not reach, systematically causing an
	 * OutOfBoundException.
	 */
	@Override
	public Set<Competitor> determineFinalists(List<League> pools) {
		List<Competitor> result = new ArrayList<Competitor>();

		for (int i = 0; i < 100; i++) {
			result.add(new Competitor(String.valueOf(i)));
		}

		int boundary = MathsPlus.log2(result.size());
		result = result.subList(0, boundary);

		return Set.copyOf(result);
	}

}
