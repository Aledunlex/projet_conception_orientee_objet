package SportsCompet.competitions;

import java.util.List;
import java.util.Set;

import SportsCompet.Competition;
import SportsCompet.Competitor;
import SportsCompet.ObserverInterface;
import SportsCompet.Strategy;
import SportsCompet.util.Displayer;
import SportsCompet.util.MathsPlus;

/**
 * In a Master, competitors first play in several Leagues, organized
 * in a GroupStage, then finalists face each other in a Tournament.
 */
public class Master extends Competition {

	/**
	 * List of every League competition used in the pools phase
	 */
	private GroupStage groupStage;

	/*
	 * Used to determine which competitors will be selected for the final
	 */
	private Strategy strategy;

	/**
	 * In a Master, competitors are first split into groups. In each group,
	 * competitors face each other in Leagues. Then, finalists are selected
	 * according to a strategy, and face each other in a single Tournament. Calls
	 * checkStratValidity() to determine if all parameters fit the Strategy.
	 * 
	 * @param name        unique to this competition
	 * @param competitors to compete against each other
	 * @param strategy    used to determine which competitors will be selected for
	 *                    the final
	 * @param groupNb     number of groups in which the competitors will be split
	 *                    for the pools
	 * @throws IllegalArgumentException if the given list of competitors or the
	 *                                  strategy are not valid
	 */
	public Master(String name, List<Competitor> competitors, Strategy strategy, int groupNb)
			throws IllegalArgumentException {
		super(name, competitors);
		this.strategy = strategy;
		this.groupStage = new GroupStage("Pools Session", competitors, groupNb);
		if (!checkStratValidity())
			throw new IllegalArgumentException("Invalid Strategy for this amount of competitors/groups!");
	}

	@Override
	public void register(ObserverInterface observer) {
		super.register(observer);
		this.groupStage.register(observer);
	}

	/**
	 * Determines the finalists for this Master by calling play() on the
	 * GroupStage, ergo the pools, then makes them compete against each 
	 * other by calling playFinal().
	 */
	@Override
	protected void play(List<Competitor> competitors) {
		groupStage.play();
		List<Competitor> finalists = determineFinalists();

		Displayer.useDisplay("\nSelected competitors : " + finalists);
		Displayer.useDisplay("\nPools phase over. Beginning the final!\n");

		Competitor winner = playFinal(finalists);

		Displayer.useDisplay("\n\n----> " + winner.toString() + " WON THE MASTER ! <----\n");
		Displayer.useDisplay("\nAll competitors' ranking based on the final :");
	}

	/**
	 * Returns the list of selected finalists after selection by this strategy
	 * 
	 * @return the list of selected finalists after the pool selection according to
	 *         this Master's strategy
	 */
	private List<Competitor> determineFinalists() {
		Set<Competitor> finalists = strategy.determineFinalists(groupStage.getPools());
		return List.copyOf(finalists);
	}

	/**
	 * Plays the final Tournament with the given list of finalists.
	 * 
	 * @param finalists that will compete against each other
	 * @return the winner of the final
	 */
	private Competitor playFinal(List<Competitor> finalists) {
		Tournament finalCompet = new Tournament("Final Tournament", finalists);
		for (ObserverInterface observer : this.match.getObservers())
			finalCompet.register(observer);
		finalCompet.play();
		Competitor winner = finalCompet.getMatch().getWinner();
		return winner;
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Master))
			return false;
		Master other = (Master) obj;
		return super.equals(obj) && other.groupStage.equals(this.groupStage) && other.strategy.equals(this.strategy);
	}

	/**
	 * Returns the strategy to chose finalists for this Master
	 * 
	 * @return the strategy to chose finalists for this Master
	 */
	public Strategy getStrategy() {
		return strategy;
	}

	/**
	 * Returns the pools used for this Master
	 * 
	 * @return the pools used for this Master
	 */
	public GroupStage getGroupStage() {
		return groupStage;
	}

	/**
	 * Checks if this strategy allows for correct group making that will allow the
	 * master to play from start to finish.
	 * 
	 * @return true if the used strategy allows for correct group making by
	 *         GroupStage and returns a power of two sized list of competitors for
	 *         the final; or false if it doesn't.
	 */
	private boolean checkStratValidity() {
		if (groupStage.getGroups() == null)
			return false;
		return MathsPlus.isPowerOfTwo(this.strategy.determineFinalists(groupStage.getPools()).size());
	}

}
