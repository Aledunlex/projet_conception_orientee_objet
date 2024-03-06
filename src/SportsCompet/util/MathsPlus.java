package SportsCompet.util;

import java.util.LinkedList;

/**
 * Utility class used to define additional mathematical functions outside of
 * classes which use them.
 */
public class MathsPlus {

	private static MathsPlus uniqueInstance;

	private MathsPlus() {
	}

	/**
	 * Makes sure only one instance of MathsPlus is created
	 * 
	 * @return this MathsPlus
	 */
	public static MathsPlus getInstance() {
		if (uniqueInstance == null) {
			uniqueInstance = new MathsPlus();
		}
		return uniqueInstance;
	}

	/**
	 * Used to check if an integer is a power of two.
	 * 
	 * @param numb the integer to be checked
	 * @return true if given integer is a power of two
	 */
	public static boolean isPowerOfTwo(int numb) {
		float n = (float) numb;
		while (n > 2) {
			n /= 2;
		}
		return n == 2;
	}

	/**
	 * Used to calculate the log2 of a given integer.
	 * 
	 * @param numb the integer for which log2 is calculated
	 * @return the log2 of given integer
	 */
	public static int log2(int numb) {
		int rep = 0;
		float n = (float) numb;
		while (n > 1) {
			n /= 2;
			rep++;
		}
		return rep;
	}

	/**
	 * Used to determine how many times an integer can be divided by another integer
	 * to return another integer. If it can be, it will return a LinkedList in which
	 * the first element is the number of times it can be divided, and the second
	 * will be 0. If it can't be, the second integer in the LinkedList will be the
	 * remainder of the division.
	 * 
	 * @param numb1 to be divided numb2 times
	 * @param numb2 by which to divide num1
	 * @return a LinkedList in which the first element is the number of times numb1
	 *         can be divided by numb2, and the second element is the rest of the
	 *         division
	 */
	public static LinkedList<Integer> balancedSplit(int numb1, int numb2) {
		LinkedList<Integer> result = new LinkedList<Integer>();
		int div = numb1 / numb2;
		int rest = numb1 % numb2;
		result.addFirst(div);
		result.addLast(rest);
		return result;
	}

}
