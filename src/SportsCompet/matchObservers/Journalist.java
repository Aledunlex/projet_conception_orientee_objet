package SportsCompet.matchObservers;

import SportsCompet.Competitor;
import SportsCompet.Match;
import SportsCompet.util.Displayer;

/**
 * A Journalist can be registered to a Competition, or directly to a Match. It
 * will display any match results at the end of it.
 */
public class Journalist extends MatchObserver {

	/**
	 * Creates a MatchObserver Journalist to register to a Match or Competition.
	 * 
	 * @param name of this Journalist
	 */
	public Journalist(String name) {
		super(name);
	}

	@Override
	/**
	 * A Journalist announces who won and who lost at the end of a match in a
	 * dramatic fashion.
	 */
	public void reactToMatch(Match match) {
		Competitor winner = match.getWinner();
		Competitor loser = match.getLoser();

		Displayer.useDisplay("*** [" + name + "] COMMENTS THE OUTCOME... ***");
		Displayer.useDisplay("*** \"Well, " + winner.getName().toUpperCase() + " showed everyone who's the boss ! "
				+ "...but " + loser.getName().toUpperCase() + " was super bad.\"\n");
	}

}
