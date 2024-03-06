package SportsCompet;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;

import SportsCompet.competitions.League;
import SportsCompet.mocks.MockLeague;

public abstract class StrategyTestBase {

	protected Strategy strat;
	protected List<League> pools;
	protected static int count;
	protected static final char[] alphabet = "abcdefghijklmnopqrstuvwxyz".toCharArray();

	protected abstract Strategy createStrategy();

	@BeforeEach
	public void before() {
		this.strat = createStrategy();

		count = 0;

		List<Competitor> group1 = new ArrayList<Competitor>();
		List<Competitor> group2 = new ArrayList<Competitor>();
		List<Competitor> group3 = new ArrayList<Competitor>();
		List<Competitor> group4 = new ArrayList<Competitor>();

		for (int i = 10; i < 15; i++) {
			group1.add(new Competitor(String.valueOf(alphabet[count++])));
			group2.add(new Competitor(String.valueOf(alphabet[count++])));
			group3.add(new Competitor(String.valueOf(alphabet[count++])));
			group4.add(new Competitor(String.valueOf(alphabet[count++])));
		}

		League one = new MockLeague("mock1",group1);
		League two = new MockLeague("mock2",group2);
		League thr = new MockLeague("mock3",group3);
		League fou = new MockLeague("mock4",group4);
		this.pools = List.of(one, two, thr, fou);
	}

}
