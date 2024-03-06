package SportsCompet;

/**
 * An observer to observe a match. Specific behavior is to be determined in this
 * interface's implementation.
 */
public interface ObserverInterface {

	/**
	 * Method determining how this observer will react at the end of specific Match
	 * 
	 * @param match to be observed
	 */
	void reactToMatch(Match match);

}