package SportsCompet.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import SportsCompet.Competitor;

/**
 * Generates a specific amount of Competitors. Used in main classes.
 */
public class RandomCompetitorsGenerator {

	/**
	 * Desired amount of competitors.
	 */
	private int amount;

	/**
	 * Will be used as a counter incremented after each creation of a competitor.
	 * Will be used as an index in the alphabet array.
	 */
	private int count;

	/**
	 * Letters of the alphabet put in an array, to name competitors at creation.
	 */
	private final char[] alphabet = "abcdefghijklmnopqrstuvwxyz".toCharArray();

	/**
	 * Builds a generator for the specified amount of desired competitors.
	 * 
	 * @param amount of desired competitors
	 */
	public RandomCompetitorsGenerator(int amount) {
		this.amount = amount;
		this.count = 0;
	}

	/**
	 * A list of competitors. Names are more easily read if amount is inferior to
	 * 27. Any duplicate competitor will be removed.
	 * 
	 * @return A list of competitors, ideally of the desired size
	 */
	public List<Competitor> generate() {
		List<Competitor> result = new ArrayList<Competitor>();
		if (amount < alphabet.length) {
			for (int i = 0; i < amount; i++) {
				result.add(new Competitor(String.valueOf(alphabet[count++])));
			}
		} else {
			int made = 0;
			for (int i = 0; i < amount; i++) {
				if (count == alphabet.length)
					count = 0;
				result.add(new Competitor(String.valueOf(alphabet[count++]).concat(String.valueOf(made++))));
			}
			result = List.copyOf(Set.copyOf(result));
		}
		return result;
	}

}
