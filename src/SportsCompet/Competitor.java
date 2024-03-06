package SportsCompet;

import java.util.Objects;

/**
 * Competitors are added to a Competition's competitors list before the latter
 * can play. It's caracterized by a unique name.
 */
public class Competitor {

	/* Name of the competitor */
	private final String name;

	/**
	 * A Competitor to be added to a Competition.
	 * 
	 * @param name of the Competitor.
	 */
	public Competitor(String name) {
		this.name = name;
	}

	/**
	 * Returns the name of this competitor as a String
	 * 
	 * @return the name of this competitor
	 */
	public String getName() {
		return this.name;
	}

	@Override
	public String toString() {
		return getName().toUpperCase();
	}

	@Override
	public int hashCode() {
		return Objects.hash(name);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof Competitor))
			return false;
		Competitor other = (Competitor) obj;
		return Objects.equals(name, other.name);
	}

}