package SportsCompet.strategies;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

import SportsCompet.Competitor;
import SportsCompet.Strategy;
import SportsCompet.competitions.League;
import SportsCompet.util.MapUtil;

/**
 * This Strategy allows to chose competitors according to any single or multiple
 * given ranks. If one of the given indexes is larger than any pool's
 * competitors' list's size, it will not be used to determine the finalists
 * list. Indexes are stored in a Set, meaning it doesn't allow for duplicate
 * values. The ranks start at index 0 (for the winner), and go up to a pool's
 * list of competitors' size minus one. Inheriting from StrategyDecorator, you
 * can individually add the result of one of these strategies to any other
 * existing Strategy.
 */
public class SelectionByRankStrategy extends StrategyDecorator {

	/**
	 * Boolean to determine if this Strategy will sort the finalists from last to
	 * first through their rank index before selection. If isReversed is set to true
	 * and the index is set to 0, determineFinalists will return every lowest ranked
	 * Competitor of each pool.
	 */
	private final boolean isReversed;

	/**
	 * Indexes that will be used to select the finalists through their ranks in each
	 * pool. Every single index must be smaller or equal to each pool's size. No
	 * duplicate values.
	 */
	private Set<Integer> indexes;

	/**
	 * Creates a Strategy that will select finalists according to a given set of
	 * indexes, corresponding to their ranks. Index 0 corresponds to the winner, (if
	 * isReversed is false), and indexes equals to or bigger than a pool's size will
	 * not be used while determining finalists.
	 * 
	 * @param indexes    the set of every rank index used to select the finalists
	 * @param isReversed must be set to false to use the indexes from start of the
	 *                   rank, or false otherwise.
	 * @param element    any implementation of the Strategy interface
	 */
	public SelectionByRankStrategy(Strategy element, Set<Integer> indexes, boolean isReversed) {
		super(element);
		this.indexes = indexes;
		this.isReversed = isReversed;
	}

	/**
	 * Adds a single index to a pre-existing SelectionByRankStrategy object.
	 * 
	 * @param index to be added to the set of indexes
	 */
	public void addAnIndex(int index) {
		this.indexes.add(index);
	}

	@Override
	/**
	 * If any of this.indexes is larger or equal to any of the pools' size, the
	 * relevant index will not be used at all.
	 */
	public Set<Competitor> determineFinalists(List<League> pools) {
		var newSet = pools.stream()
					.map(League::ranking)
					.map(MapUtil::sortedMapToLinkedList)
					.map(mapByIndexInList())
					.flatMap(map -> map.entrySet().stream())
					.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue))
					.entrySet().stream()
							  	.filter(competitor -> getIndexes().contains(competitor.getValue()))
							  	.map(entry -> entry.getKey())
							  	.collect(Collectors.toSet());
		
		newSet.addAll(super.determineFinalists(pools));
		return newSet;
	}

	private Function<List<Competitor>, Map<Competitor, Integer>> mapByIndexInList() {
		return list -> list.stream().collect(Collectors.toMap(
				Function.identity(), 
				competitor -> {
					int indexOf = list.indexOf(competitor);
					int upperBound = list.size()-1;
					return this.isReversed?(upperBound-indexOf):indexOf;
				}));
	}


	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof SelectionByRankStrategy))
			return false;
		SelectionByRankStrategy other = (SelectionByRankStrategy) obj;
		return other.indexes.equals(this.indexes) && (other.isReversed == this.isReversed);
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(indexes) + Objects.hash(isReversed);
	}

	/**
	 * Returns a boolean evaluating if this strategy starts from the end of rankings
	 * or not.
	 * 
	 * @return if this strategy starts from ending of rank or not.
	 */
	public boolean isReversed() {
		return isReversed;
	}

	/**
	 * Returns the indexes at which the competitors will be selected
	 * 
	 * @return the indexes at which the competitors will be selected
	 */
	public Set<Integer> getIndexes() {
		return indexes;
	}

	/**
	 * Allows to set a different set of indexes for this strategy.
	 * 
	 * @param indexes to be set
	 */
	public void setIndexes(Set<Integer> indexes) {
		this.indexes = indexes;
	}

}
