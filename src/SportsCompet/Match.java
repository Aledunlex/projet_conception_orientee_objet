package SportsCompet;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;

/**
 * Abstract class that defines the basics of what a Match is going to be in most
 * cases. The determineWinnerAndLoser() method must be defined according to
 * requirements.
 */
public abstract class Match {

	/**
	 * The collection of all of this match's observers
	 */
	protected Collection<ObserverInterface> matchObservers;

	/**
	 * The winning competitor determined at the end of the play method
	 */
	protected Competitor winner;
	/**
	 * The losing competitor determined at the end of the play method
	 */
	protected Competitor loser;

	/**
	 * Initializes a Match with an empty HashSet of observers
	 */
	public Match() {
		this.matchObservers = new HashSet<>();
	}

	/**
	 * Plays this match to determine the winner between the two given competitors.
	 * 
	 * @param c1 first competitor
	 * @param c2 second competitor
	 */
	public void play(Competitor c1, Competitor c2) {
		List<Competitor> couple = Arrays.asList(c1, c2);

		determineWinnerAndLoser(couple);
		for (ObserverInterface observer : matchObservers) {
			observer.reactToMatch(this);
		}
	}

	/**
	 * Determines a winner and a loser from the given couple of Competitors. Method
	 * to determine these depends on the Match type.
	 * 
	 * @param couple must be a list of exactly 2 different Competitors.
	 */
	protected abstract void determineWinnerAndLoser(List<Competitor> couple);

	/**
	 * Returns the Competitor object that won the match
	 * 
	 * @return the winner of the match
	 */
	public Competitor getWinner() {
		return winner;
	}

	/**
	 * Sets this winner attribute to the given Competitor
	 * 
	 * @param winner that must be set as this winner attribute
	 */
	protected void setWinner(Competitor winner) {
		this.winner = winner;
	}

	/**
	 * Returns the Competitor object that lost the match
	 * 
	 * @return the loser of the match
	 */
	public Competitor getLoser() {
		return loser;
	}

	/**
	 * Sets this loser attribute to the given Competitor
	 * 
	 * @param loser that must be set as this loser attribute
	 */
	protected void setLoser(Competitor loser) {
		this.loser = loser;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof Match))
			return false;
		Match other = (Match) obj;
		return Objects.equals(loser, other.loser) && Objects.equals(winner, other.winner);
	}

	/**
	 * Adds an observer to this match's collection of observers
	 * 
	 * @param observer to be added
	 */
	public void register(ObserverInterface observer) {
		matchObservers.add(observer);
	}

	/**
	 * Removes an observer from this match's collection of observers
	 * 
	 * @param observer to be removed
	 */
	public void unRegister(ObserverInterface observer) {
		matchObservers.remove(observer);
	}

	/**
	 * Returns the collection of observers
	 * 
	 * @return the collection of observers
	 */
	public Collection<ObserverInterface> getObservers() {
		return this.matchObservers;
	}

}