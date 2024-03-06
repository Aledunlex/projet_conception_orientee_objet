package SportsCompet.competitions;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import SportsCompet.Competition;
import SportsCompet.Competitor;
import SportsCompet.ObserverInterface;
import SportsCompet.util.Displayer;
import SportsCompet.util.MathsPlus;

/**
 * In a GroupStage, competitors play in several Leagues. In this project, we
 * only used it in a Master.
 */
public class GroupStage extends Competition {

	/**
	 * List of every League competition used in the pools phase
	 */
	private List<League> pools;

	/*
	 * Groups of competitors that will face each others in pools
	 */
	private List<List<Competitor>> groups;

	/**
	 * In a GroupStage, competitors as first split into balanced groups, and then
	 * each competitors inside a group face each other in a League. A GroupStage
	 * can't be split into more groups than half the number of competitors.
	 * 
	 * @param name,       unique to this competition
	 * @param competitors to compete against each other
	 * @param groupNb     number of groups in which the competitors will be split
	 *                    for the pools
	 * @throws IllegalArgumentException if the given list of competitors or number
	 *                                  of groups are not valid
	 */
	public GroupStage(String name, List<Competitor> competitors, int groupNb) throws IllegalArgumentException {
		super(name, competitors);
		if (competitors.size() < (groupNb * 2))
			throw new IllegalArgumentException("There is not enough competitors to create this number of groups.");
		makeTeams(competitors, groupNb);
		signUpCompetitorsForPools();
	}

	@Override
	public void register(ObserverInterface observer) {
		super.register(observer);
		for (League league : pools)
			league.register(observer);
	}

	/**
	 * Determines the finalists for this Master by calling playPools(), and makes
	 * them compete against each other by calling playFinal().
	 */
	@Override
	protected void play(List<Competitor> competitors) {
		playPools();
	}

	/**
	 * Uses MathsPlus.balancedSplit to determine how to create balanced groups.
	 * After splitting all competitors in balanced groups, if there was a remainder
	 * to the division, the remaining competitors are added one at a time in
	 * different groups until there is no more remaining competitors.
	 * 
	 * @param competitors initial list of competitors, to be split into groups
	 * @param groupNb     number of groups to be made
	 */
	private void makeTeams(List<Competitor> competitors, int groupNb) {
		LinkedList<Integer> competModGroups = MathsPlus.balancedSplit(competitors.size(), groupNb);
		int competitorsPerGroup = competModGroups.getFirst();
		int remaining = competModGroups.getLast();
		List<List<Competitor>> groupsDone = new ArrayList<List<Competitor>>();

		for (int i = 0; i < groupNb; i++) {
			List<Competitor> oneGroup = new ArrayList<Competitor>();
			groupsDone.add(oneGroup);
			for (int j = 0; j < competitorsPerGroup; j++) {
				groupsDone.get(i).add(competitors.get(j + i * competitorsPerGroup));
			}
		}
		for (int i = 0; i < remaining; i++) {
			groupsDone.get(i).add(competitors.get(i + groupNb * competitorsPerGroup));
		}

		this.groups = groupsDone;
	}

	/**
	 * Calls the play() method for each league in this.pools
	 */
	private void playPools() {
		for (int i = 0; i < pools.size(); i++) {
			League league = pools.get(i);
			Displayer.useDisplay("START OF LEAGUE " + (i + 1) + " OUT OF " + pools.size() + "\n");
			league.play();
			if (!league.equals(pools.get(pools.size() - 1)))
				Displayer.useDisplay("\nNext League");
		}
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof GroupStage))
			return false;
		GroupStage other = (GroupStage) obj;
		return super.equals(obj) && other.groups.equals(this.groups);
	}

	/**
	 * Returns the groups of competitors competing in this GroupStage
	 * 
	 * @return the groups of competitors competing in this GroupStage
	 */
	public List<List<Competitor>> getGroups() {
		return groups;
	}

	/**
	 * Returns the pools used for this GroupStage
	 * 
	 * @return the pools used for this GroupStage
	 */
	public List<League> getPools() {
		return pools;
	}

	/**
	 * Creates a League for each of the already formed groups and 
	 * adds it to this pools.
	 */
	private void signUpCompetitorsForPools() {
		pools = new ArrayList<League>();
		int i = 0;
		for (List<Competitor> group : groups) {
			League league = new League("Pool League ".concat(String.valueOf(++i)), group);
			pools.add(league);
		}
	}

	@Override
	public Map<Competitor, Integer> ranking() {
		for (League league : pools) {
			ranking.putAll(league.ranking());
		}
		return super.ranking();
	}

}
