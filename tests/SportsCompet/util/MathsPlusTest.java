package SportsCompet.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MathsPlusTest {

	private List<Integer> powersOfTwo;
	private List<Integer> notPowersOfTwo;

	@BeforeEach
	public void before() {
		this.powersOfTwo = Arrays.asList(2, 4, 8, 16, 32, 64, 128, 256, 512);
		this.notPowersOfTwo = Arrays.asList(3, 42, 68, 18, 357);
	}

	@Test
	public void isPowerOfTwoTest() {
		for (Integer i : powersOfTwo) {
			assertTrue(MathsPlus.isPowerOfTwo(i));
		}
		for (Integer i : notPowersOfTwo) {
			assertFalse(MathsPlus.isPowerOfTwo(i));
		}
	}

	@Test
	public void log2Test() {
		int i = 1;
		for (Integer j : powersOfTwo) {
			assertEquals(i, MathsPlus.log2(j));
			i++;
		}
	}

	@Test
	public void unbalancedSplitsTest() {
		LinkedList<Integer> list = MathsPlus.balancedSplit(10, 3);
		assertEquals(3, list.getFirst());
		assertEquals(1, list.getLast());
		list = MathsPlus.balancedSplit(14, 3);
		assertEquals(4, list.getFirst());
		assertEquals(2, list.getLast());

	}

	@Test
	public void balancedSplitsTest() {
		LinkedList<Integer> list = MathsPlus.balancedSplit(9, 3);
		assertEquals(3, list.getFirst());
		assertEquals(0, list.getLast());
		list = MathsPlus.balancedSplit(21, 7);
		assertEquals(3, list.getFirst());
		assertEquals(0, list.getLast());
	}

}
