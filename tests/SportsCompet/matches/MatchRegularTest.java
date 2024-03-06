package SportsCompet.matches;

import java.util.List;

import SportsCompet.Competitor;
import SportsCompet.Match;
import SportsCompet.MatchTest;
import SportsCompet.competitions.League;

public class MatchRegularTest extends MatchTest {

	protected Match createMatch() {
		Match match;
		match = new MatchRegular();
		Competitor patrick = new Competitor("Patrick");
		Competitor michel = new Competitor("Michel");
		Competitor jean = new Competitor("Jean");
		Competitor michmich = new Competitor("MichMich");
		List<Competitor> compts = List.of(patrick, michel, jean, michmich);
		match.register(new League("l",compts));
		return match;
	}

}
