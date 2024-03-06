package SportsCompet.mocks;

import java.util.Iterator;
import java.util.List;

import SportsCompet.Competitor;
import SportsCompet.competitions.League;
import SportsCompet.util.MapUtil;

/**
 * Adds a few methods required to test specific cases for Strategies
 */
public class MockLeague extends League {

	public MockLeague(String name, List<Competitor> competitors) throws IllegalArgumentException {
		super(name, competitors);
	}

	/**
	 * Allows to add a single competitor with a predetermined score to an existing
	 * MockLeague
	 * 
	 * @param competitor
	 * @param desiredScore
	 */
	public void addCompetitor(Competitor competitor, int desiredScore) {
		for (int i = 0; i < desiredScore; i++)
			addOnePoint(competitor);
		this.competitors.add(competitor);
	}

	/**
	 * Returns the second lowest ranked competitor of this MockLeague
	 * 
	 * @return the second lowest ranked competitor
	 */
	public Competitor getBeforeLast() {
		Iterator<Competitor> iterator = MapUtil.sortedMapToLinkedList(ranking()).descendingIterator();
		iterator.next();
		return iterator.next();
	}
	
	/**
	 * Returns the lowest ranked competitor of this MockLeague
	 * 
	 * @return the lowest ranked competitor
	 */
	public Competitor getLast() {
		Iterator<Competitor> iterator = MapUtil.sortedMapToLinkedList(ranking()).descendingIterator();
		return iterator.next();
	}

}
