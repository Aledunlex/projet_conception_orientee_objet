package SportsCompet;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import SportsCompet.matchObservers.MatchObserver;
import SportsCompet.matches.MatchRegular;
import SportsCompet.util.Displayer;
import SportsCompet.util.MapUtil;

/**
 * Abstract class that defines the basics of what a Competition is going to be
 * in most cases. The play() methods must be defined according to requirements.
 * A Competition should not be manually registered to a match, it is registered
 * to its own match at creation, to observe every played match's outcomes
 */
public abstract class Competition extends MatchObserver {

	/**
	 * This competition's immutable list of competitors
	 */
	protected final List<Competitor> competitors;

	/**
	 * Map associating each competitor to their score in this competition
	 */
	protected Map<Competitor, Integer> ranking;

	/**
	 * This competition's unique match object used to let competitors face each
	 * other
	 */
	protected Match match;

	/**
	 * Organizes a match for each determined couple of competitors. At creation, a
	 * Competition immediately subscribes to its own match.
	 * 
	 * @param name        unique to this competition
	 * @param competitors to compete against each other
	 * @throws IllegalArgumentException if the given list of Competitors is invalid
	 */
	public Competition(String name, List<Competitor> competitors) throws IllegalArgumentException {
		super(name);
		if (competitors.size() < 2)
			throw new IllegalArgumentException("Not enough competitors!");
		this.competitors = competitors;
		this.match = new MatchRegular();
		this.match.register(this);
		this.ranking = new HashMap<Competitor, Integer>();
		for (Competitor competitor : competitors)
			setCompetitorScore(competitor, 0);
	}

	/**
	 * A Competition adds a points to the winner of any of their match
	 */
	public void reactToMatch(Match match) {
		addOnePoint(match.getWinner());
	}

	/**
	 * Registers another observer to this competition's match
	 * 
	 * @param observer to be added
	 */
	public void register(ObserverInterface observer) {
		this.match.register(observer);
	}

	/**
	 * Removes an observer from this competition's match
	 * 
	 * @param observer to be removed
	 */
	public void unRegister(ObserverInterface observer) {
		this.match.unRegister(observer);
	}

	/**
	 * Returns the score of this competitor as an int
	 * 
	 * @param compet for which the score is to be set
	 * @return the score of this competitor
	 */
	public int getScore(Competitor compet) {
		if (!this.ranking.containsKey(compet)) {
			this.ranking.put(compet, 0);
		}
		return ranking.get(compet);
	}

	/**
	 * Adds one point to this competitor's score
	 * 
	 * @param compet competitor for which the score is to be set
	 */
	public void addOnePoint(Competitor compet) {
		int score = this.ranking.containsKey(compet) ? (ranking.get(compet) + 1) : 1;
		setCompetitorScore(compet, score);
	}

	/**
	 * Sets this competitor's score to the given number
	 * 
	 * @param compet competitor for which the score is to be set
	 * @param score  to be set
	 */
	public void setCompetitorScore(Competitor compet, int score) {
		this.ranking.put(compet, score);
	}

	/**
	 * Invokes play(competitors) and displays the hashmap of the ranking at the end.
	 */
	public void play() {
		this.play(competitors);
		this.displayRanking();
	}

	/**
	 * Must invoke playMatch(c1,c2) in a way specific to this Competition for each
	 * determined couple of competitors in the given list.
	 * 
	 * @param competitors given list of competitors.
	 */
	protected abstract void play(List<Competitor> competitors);

	/**
	 * Organizes this competition's match to be played with two competitors Also
	 * displays an announcement for any match before it is played (so this display
	 * is not a properly a "reaction")
	 * 
	 * @param c1 the first competitor
	 * @param c2 the second competitor
	 */
	protected void playMatch(Competitor c1, Competitor c2) {
		String announce = "=> NEXT MATCH: " + c1.getName() + " VS " + c2.getName();
		int rqdStrSize = announce.length();
		String topStr = "_".repeat(rqdStrSize);

		Displayer.useDisplay(topStr + "\n" + announce + "\n");
		match.play(c1, c2);
	}

	/**
	 * Returns the sorted hashmap of each competitor coupled to their score
	 * 
	 * @return a sorted hashmap of each competitor coupled to their score
	 */
	public Map<Competitor, Integer> ranking() {
		return MapUtil.sortByDescendingValue(ranking);
	}

	/**
	 * Formats and displays the rankings.
	 */
	private void displayRanking() {
		Displayer.useDisplay("\n#### " + name.toUpperCase() + " RANKING ####\n");
		Displayer.displaySortedMap(ranking(), false);
	}

	/**
	 * Returns the list of this competition's competitors
	 * 
	 * @return the list of this competition's competitors
	 */
	public final List<Competitor> getCompetitors() {
		return this.competitors;
	}

	/**
	 * Returns this competition's match
	 * 
	 * @return this competition's match
	 */
	public Match getMatch() {
		return this.match;
	}

	@Override
	public boolean equals(Object o) {
		if (o instanceof Competition) {
			Competition theOther = ((Competition) o);
			return this.name.equals(theOther.name) && this.competitors.equals(theOther.competitors);
		} else {
			return false;
		}
	}

}
