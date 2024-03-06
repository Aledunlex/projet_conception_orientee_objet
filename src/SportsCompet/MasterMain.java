package SportsCompet;

import java.util.List;
import java.util.Set;

import SportsCompet.competitions.Master;
import SportsCompet.matchObservers.Bookmaker;
import SportsCompet.matchObservers.Journalist;
import SportsCompet.strategies.EveryHighestRanked;
import SportsCompet.strategies.EveryLowestRanked;
import SportsCompet.strategies.EverySecondBest;
import SportsCompet.strategies.EverySecondLowest;
import SportsCompet.strategies.SelectionByRankStrategy;
import SportsCompet.strategies.StrategyBase;
import SportsCompet.util.Displayer;
import SportsCompet.util.MathsPlus;
import SportsCompet.util.RandomCompetitorsGenerator;

/**
 * By default starts a Master with 16 competitors in 4 groups, and a Strategy
 * that picks the two better ranked competitors of each pool for the final
 * Tournament. A Bookmaker and a Journalist are registered to it.
 */
@SuppressWarnings("unused")
public class MasterMain {

	public static void main(String[] args) {
		/**
		 * Be careful when choosing `desired` and `nbGroups` values along with a
		 * `Strategy` : the resulting Tournament must be starting with a list of
		 * Competitors of length power of two. Otherwise, the Master will not be
		 * created. Base values known to work for reference : desired=16; nbGroups=4;
		 */
		int desired = 16;
		List<Competitor> competitors = new RandomCompetitorsGenerator(desired).generate();

		int nbGroups = 4;

		Strategy baseStrat = new StrategyBase();
		SelectionByRankStrategy strat;

		/* You can comment out 3 of the following lines */
		strat = new EveryHighestRanked(new EverySecondBest(baseStrat));
		// strat = new EveryHighestRanked(baseStrat);
		// strat = new EverySecondLowest(new EveryLowestRanked(baseStrat));
		// strat = new EveryLowestRanked(new SelectionByRankStrategy(baseStrat, Set.of(0), false));

		Competition master = new Master("Master", competitors, strat, nbGroups);

		master.register(new Bookmaker("Patrick"));
		master.register(new Journalist("La Ch'tite chronique"));

		Displayer.useDisplay("Beginning Master\n");

		master.play();

		Displayer.useDisplay("\nEnd of Master");
	}

}
