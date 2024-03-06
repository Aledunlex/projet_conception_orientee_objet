package SportsCompet;

import java.util.List;

import SportsCompet.competitions.League;
import SportsCompet.matchObservers.Bookmaker;
import SportsCompet.matchObservers.Journalist;
import SportsCompet.util.Displayer;
import SportsCompet.util.RandomCompetitorsGenerator;

/**
 * Starts a League with 8 competitors (by default) and displays every encounter,
 * then the final ranks. A Bookmaker and a Journalist are registered to it.
 */
public class LeagueMain {

	public static void main(String[] args) {
		int desired = 8;
		List<Competitor> competitors = new RandomCompetitorsGenerator(desired).generate();

		League league = new League("League", competitors);

		league.register(new Bookmaker("Patrick"));
		league.register(new Journalist("La Ch'tite chronique"));

		Displayer.useDisplay("Beginning League\n");

		league.play();

		Displayer.useDisplay("Ending of League\n");
	}

}
