package SportsCompet.competitions;

import java.util.List;

import SportsCompet.Competition;
import SportsCompet.Competitor;

/**
 * A League lets competitors face every other competitor exactly twice.
 */
public class League extends Competition {

	/**
	 * Each League lets any Competitor face any other Competitor exactly twice. The
	 * League ends when every Competitor played exactly twice against each other.
	 * 
	 * @param name        unique to this competition
	 * @param competitors to compete against each other
	 */
	public League(String name, List<Competitor> competitors) throws IllegalArgumentException {
		super(name, competitors);
	}

	@Override
	protected void play(List<Competitor> competitors) {
		for (int round = 0; round < 2; round++) {
			for (int i = 0; i < this.competitors.size(); i++) {
				for (int j = i + 1; j < this.competitors.size(); j++) {
					playMatch(this.competitors.get(i), this.competitors.get(j));
				}
			}
		}
	}

}
