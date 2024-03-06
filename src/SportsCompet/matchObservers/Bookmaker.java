package SportsCompet.matchObservers;

import java.util.HashMap;
import java.util.Map;

import SportsCompet.Competitor;
import SportsCompet.Match;
import SportsCompet.util.Displayer;
import SportsCompet.util.MapUtil;

/**
 * A Bookmaker can be registered to a Competition, or directly to a Match. It
 * will update and display the odds for every competitor at the end of a match.
 */
public class Bookmaker extends MatchObserver {

	/**
	 * The register of this Bookmaker's odds, updated after every match
	 */
	private Map<Competitor, Integer> odds;

	/**
	 * Creates a MatchObserver Bookmaker to register to a Match or Competition.
	 * 
	 * @param name of this Bookmaker
	 */
	public Bookmaker(String name) {
		super(name);
		odds = new HashMap<>();
	}

	@Override
	/**
	 * A Bookmaker adds one to the value of a Competitor in their odds Hashmap if
	 * they lose a match, or removes one if they won.
	 */
	public void reactToMatch(Match match) {
		Competitor winner = match.getWinner();
		Competitor loser = match.getLoser();
		updateOdds(winner, loser);
		if (odds.size() > 0)
			displayOdds();
	}

	/**
	 * Updates the odds for the given winner and loser.
	 * 
	 * @param winner who will lose one for their odds
	 * @param loser  who will win one for their odds
	 */
	private void updateOdds(Competitor winner, Competitor loser) {
		if (!odds.containsKey(winner))
			odds.put(winner, 0);
		if (odds.get(winner) > 0)
			odds.put(winner, odds.get(winner) - 1);

		if (!odds.containsKey(loser))
			odds.put(loser, 1);
		else
			odds.put(loser, odds.get(loser) + 1);
	}

	/**
	 * Returns the odds
	 * 
	 * @return the odds
	 */
	public Map<Competitor, Integer> getOdds() {
		return odds;
	}

	/**
	 * Returns the sorted odds, only used for display
	 * 
	 * @return the sorted odds
	 */
	private Map<Competitor, Integer> getSortedOdds() {
		return MapUtil.sortByDescendingValue(odds);
	}

	/**
	 * Formats and displays the odds, from lowest ranked (so highest odds) to best
	 * ranked (so lowest odds).
	 */
	public void displayOdds() {
		Displayer.useDisplay("*** [" + name + "] UPDATES THE ODDS... ***");
		Displayer.displaySortedMap(getSortedOdds(), true);
		Displayer.useDisplay("");
	}

}
