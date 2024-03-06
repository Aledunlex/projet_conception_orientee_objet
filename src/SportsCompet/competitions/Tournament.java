package SportsCompet.competitions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import SportsCompet.Competition;
import SportsCompet.Competitor;
import SportsCompet.Match;
import SportsCompet.util.MathsPlus;

/**
 * In a Tournament, losing Competitors are not allowed to play again.
 */
public class Tournament extends Competition {

	/**
	 * Number of played matches by this competitor, changes throughout a competition
	 */
	private Map<Competitor, Integer> nbOfPlayedMatches;

	/**
	 * Each tournament is played in several rounds. During a round, competitors face
	 * each other. A Competitor will only play once per round. A losing competitor
	 * will not be playing any other round. The Tournament ends when there is only
	 * one remaining competitor.
	 * 
	 * @param name,       unique to this competition
	 * @param competitors to compete against each other
	 * @exception IllegalArgumentException if the given list's size is not a power
	 *                                     of two, making this tournament
	 *                                     impossible.
	 */
	public Tournament(String name, List<Competitor> competitors) throws IllegalArgumentException {
		super(name, competitors);
		if (!MathsPlus.isPowerOfTwo(competitors.size()))
			throw new IllegalArgumentException("Can't play a tournament if competitors number is not a power of two.");
		nbOfPlayedMatches = new HashMap<>();
		for (Competitor competitor : competitors)
			setNbOfPlayedMatch(competitor, 0);
	}

	/**
	 * Returns the number of matches this competitor played as an int
	 * 
	 * @param compet for which the nb of played match is to be returned
	 * @return the number of matches this competitor played
	 */
	public int getNbOfPlayedMatches(Competitor compet) {
		if (!this.nbOfPlayedMatches.containsKey(compet)) {
			this.nbOfPlayedMatches.put(compet, 0);
		}
		return this.nbOfPlayedMatches.get(compet);
	}

	@Override
	public void reactToMatch(Match match) {
		super.reactToMatch(match);
		for (Competitor competitor : List.of(match.getWinner(), match.getLoser()))
			addNbOfPlayedMatch(competitor);
	}

	/**
	 * Adds one to this competitor's number of played matches
	 * 
	 * @param competitor for which the nb of played match is to be set
	 */
	public void addNbOfPlayedMatch(Competitor competitor) {
		int nb = this.nbOfPlayedMatches.containsKey(competitor) ? (nbOfPlayedMatches.get(competitor) + 1) : 1;
		setNbOfPlayedMatch(competitor, nb);
	}

	/**
	 * Sets this competitor's number of played matches to the given number
	 * 
	 * @param compet for which the nb of played matches is to be set
	 * @param nb     of matches to be set
	 */
	public void setNbOfPlayedMatch(Competitor compet, int nb) {
		this.nbOfPlayedMatches.put(compet, nb);
	}

	/**
	 * A Tournament is organized in rounds. Calls the playOneRound method once per
	 * round.
	 */
	@Override
	protected void play(List<Competitor> competitors) {
		List<Competitor> newList = new ArrayList<Competitor>();
		newList.addAll(competitors);
		for (int i = 0; i < MathsPlus.log2(this.competitors.size()); i++) {
			newList = playOneRound(newList, i);
		}
	}

	/**
	 * Plays one round and removes every loser from future rounds
	 * 
	 * @param canPlay     list of Competitors who were able to play at the beginning
	 *                    of the round
	 * @param roundNumber the number of the current round
	 * @return the list of Competitors who can play at the end of the round
	 */
	private List<Competitor> playOneRound(List<Competitor> canPlay, int roundNumber) {
		List<Competitor> newCanPlay = canPlay;
		boolean roundEnded = false;
		while (!roundEnded) {
			Competitor c1 = pickAnyCompetitor(newCanPlay, roundNumber);
			Competitor c2 = pickAnyOtherCompetitor(newCanPlay, roundNumber, c1);
			playMatch(c1, c2);
			newCanPlay = updateListOfCompetitors(newCanPlay);
			roundEnded = checkIfEveryonePlayed(newCanPlay, roundNumber);
		}
		return newCanPlay;
	}

	/**
	 * Returns an updated list of Competitors, minus the Competitor who lost the
	 * previous played match
	 * 
	 * @param canPlay provided list of currently competiting competitors
	 * @return updated list of competitors, removing the loser of this.match
	 */
	private List<Competitor> updateListOfCompetitors(List<Competitor> canPlay) {
		List<Competitor> newCanPlay = new ArrayList<Competitor>();
		newCanPlay.addAll(canPlay);
		newCanPlay.remove(match.getLoser());
		return newCanPlay;
	}

	/**
	 * Selects an available Competitor (who didn't lose yet) for the ongoing round
	 * 
	 * @param canPlay     provided list of currently competiting competitors
	 * @param roundNumber for which a match must be played
	 * @return
	 */
	private Competitor pickAnyCompetitor(List<Competitor> canPlay, int roundNumber) {
		Competitor chosen = null;
		while ((chosen == null) || nbOfPlayedMatches.get(chosen) > roundNumber) {
			int random = new Random().nextInt(canPlay.size());
			chosen = canPlay.get(random);
		}
		return chosen;
	}

	/**
	 * Used to determine any competitor who can play this round, except the one
	 * given as parameter
	 * 
	 * @param canPlay     list of Competitors who were able to play at the beginning
	 *                    of the round
	 * @param roundNumber the number of the current round
	 * @param competitor  who is going to play against the one about to be selected
	 *                    by this method
	 * @return the competitor to face the one given as parameter to this method
	 */
	private Competitor pickAnyOtherCompetitor(List<Competitor> canPlay, int roundNumber, Competitor competitor) {
		List<Competitor> duplicate = new ArrayList<Competitor>();
		duplicate.addAll(canPlay);
		duplicate.remove(competitor);
		return pickAnyCompetitor(duplicate, roundNumber);
	}

	/**
	 * Used to determine if this round is ended or not
	 * 
	 * @param newCanPlay  is the list of Competitors who can still play this round
	 * @param roundNumber is the current round number
	 * @return true if every competitor who can play this round have played
	 */
	private boolean checkIfEveryonePlayed(List<Competitor> newCanPlay, int roundNumber) {
		boolean result = true;
		for (Competitor competitor : newCanPlay) {
			if (nbOfPlayedMatches.get(competitor) <= roundNumber)
				result = false;
		}
		return result;
	}

}
