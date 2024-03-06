package SportsCompet.competitions;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.Test;

import SportsCompet.Competition;
import SportsCompet.CompetitionTest;
import SportsCompet.Competitor;

public class GroupStageTest extends CompetitionTest {

	private GroupStage groupStage;
	private final int groups = 2;

	@Override
	protected Competition createCompetition(List<Competitor> competitors)
			throws IllegalArgumentException, IndexOutOfBoundsException {
		groupStage = new GroupStage("g",competitors, groups);
		return groupStage;
	}

	@Test
	public void teamsMakerCreatedBalancedGroupsTest() {
		Set<Integer> validDifferences = Set.of(-1, 0, 1);
		List<Integer> groupSizes = new ArrayList<Integer>();

		for (List<Competitor> group : groupStage.getGroups()) {
			groupSizes.add(group.size());
		}

		int max = Collections.max(groupSizes);
		int min = Collections.min(groupSizes);

		assertTrue(validDifferences.contains(max - min));
	}

	@Test
	public void cantCreateGroupStageWhenNotEnoughCompetitorsForANumberOfGroups() {
		competitors.clear();
		for (int i = 0; i < 2; i++)
			competitors.add(new Competitor(String.valueOf(i)));
		assertThrows(IllegalArgumentException.class, () -> {
			new GroupStage("g2",competitors, groups);
		});
	}

	@Test
	public void teamsMakerUsedEverySingleCompetitorExactlyOnceTest() {
		List<Competitor> competitorsInGroups = new ArrayList<Competitor>();
		for (List<Competitor> group : groupStage.getGroups()) {
			competitorsInGroups.addAll(group);
		}
		assertTrue(competitorsInGroups.containsAll(groupStage.getCompetitors()));
		assertEquals(groupStage.getCompetitors().size(), competitorsInGroups.size());
	}

	@Test
	public void signUpCompetitorsForPoolsCreateGoodAmountOfLeaguesTest() {
		assertEquals(2, groupStage.getGroups().size());

	}

	@Test
	@Override
	public void equalsTest() {
		super.equalsTest();
		GroupStage competEquals = (GroupStage) createCompetition(competitors);
		GroupStage competNotEquals = new GroupStage("g3",competitors, groups - 1);
		assertTrue(this.groupStage.equals(competEquals));
		assertFalse(this.groupStage.equals(competNotEquals));
	}

}
