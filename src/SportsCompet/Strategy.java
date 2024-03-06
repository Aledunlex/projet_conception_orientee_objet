package SportsCompet;

import java.util.List;
import java.util.Set;

import SportsCompet.competitions.League;

/**
 * Interface to determine how finalists will be chosen at the end of a Master
 */
public interface Strategy {

	/**
	 * Determines who will be competing in the Competition's final
	 * 
	 * @param pools composing a Master's GroupStage
	 * @return the list of finalists
	 */
	public Set<Competitor> determineFinalists(List<League> pools);

}
