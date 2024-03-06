package SportsCompet;

import java.util.List;

import SportsCompet.competitions.Tournament;
import SportsCompet.matchObservers.Bookmaker;
import SportsCompet.matchObservers.Journalist;
import SportsCompet.util.Displayer;
import SportsCompet.util.RandomCompetitorsGenerator;

/**
 * Starts a Tournament with 8 competitors (by default) and displays every
 * encounter, then the final ranks. A Bookmaker and a Journalist are registered
 * to it.
 */
public class TournamentMain {

	public static void main(String[] args) {
		/**
		 * Be careful when choosing `desired` value : a Tournament can't be created if
		 * desired value is not a power of two.
		 */
		int desired = 8;
		List<Competitor> competitors = new RandomCompetitorsGenerator(desired).generate();

		Tournament tourn = new Tournament("Tournament", competitors);

		tourn.register(new Bookmaker("Patrick"));
		tourn.register(new Journalist("La Ch'tite chronique"));

		Displayer.useDisplay("Beginning Tournament\n");

		tourn.play();

		Displayer.useDisplay("Ending of Tournament\n");
	}

}
