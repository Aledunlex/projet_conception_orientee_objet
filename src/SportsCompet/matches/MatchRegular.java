package SportsCompet.matches;

import java.util.List;
import java.util.Random;

import SportsCompet.Competitor;
import SportsCompet.Match;

/**
 * In a regular match, the winner is determined randomly.
 */
public class MatchRegular extends Match {

	/**
	 * Randomly determines a winner and a loser from the given Competitor list.
	 */
	protected void determineWinnerAndLoser(List<Competitor> couple) {
		int random = new Random().nextInt(2);
		Competitor winner = couple.get(random);
		Competitor loser = couple.get((random - 1) * (-1));
		setWinner(winner);
		setLoser(loser);
	}

}
