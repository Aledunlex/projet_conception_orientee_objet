package SportsCompet.util;

import java.util.Map;

/**
 * Utility class used to determine how any text will be displayed. In this
 * project, it will call the System.out.println() method.
 */
public class Displayer {
	/**
	 * Unique instance of this Displayer.
	 */
	private static Displayer uniqueInstance;

	/**
	 * If disabled, the Displayer won't output any given text. Mostly used for
	 * testing. Set to true by default.
	 */
	private static boolean enabled = true;

	/**
	 * A Displayer's constructor is set to private, to respect the Singleton
	 * pattern.
	 */
	private Displayer() {
	}

	/**
	 * Allows manually enable or disabling the displayer.
	 * 
	 * @param en the boolean to enable (if true) or disable (if false) the display.
	 */
	public static void setEnabled(boolean en) {
		enabled = en;
	}

	/**
	 * Returns if this display is enabled or not.
	 * 
	 * @return if this display is enabled or not.
	 */
	public static boolean getEnabled() {
		return enabled;
	}

	/**
	 * Makes sure only one instance of Displayer is created
	 * 
	 * @return this Displayer
	 */
	public static Displayer getInstance() {
		if (uniqueInstance == null) {
			uniqueInstance = new Displayer();
		}
		return uniqueInstance;
	}

	/**
	 * Displays given String on standard output. To be called by useDisplay if you
	 * so chose.
	 * 
	 * @param text to be displayed
	 */
	private static void displayTextSTDOUT(String text) {
		if (getEnabled())
			System.out.println(text);
	}

	/**
	 * Displays given String wherever this method's body prefers to. For this
	 * project, we will use the standard output, ergo the displayTextSTDOUT()
	 * method.
	 * 
	 * @param text to be displayed
	 */
	public static void useDisplay(String text) {
		displayTextSTDOUT(text);
	}

	/**
	 * Displays given Object wherever this method's body prefers to. For this
	 * project, we will use the standard output, ergo the displayTextSTDOUT()
	 * method.
	 * 
	 * @param object on which toString() will be called and displayed
	 */
	public static void useDisplay(Object object) {
		useDisplay(object.toString());
	}

	/**
	 * Formats and displays a given sorted Map, by last entry or by first entry.
	 * 
	 * @param <K>      key of the given map
	 * @param <V>      value to the key
	 * @param map      given map to be displayed by rank, reversed or not
	 * @param reversed determines if display start by last or first entry
	 */
	public static <K, V extends Comparable<? super V>> void displaySortedMap(Map<K, V> map, boolean reversed) {
		String separator = " : ";

		int i;
		i = reversed ? map.size() + 1 : 0;
		int maxRankStringSize = String.valueOf(map.size()).length();

		String longerName = "";
		for (Map.Entry<K, V> rankEntry : map.entrySet()) {
			String currName = rankEntry.getKey().toString();
			if (longerName.isEmpty())
				longerName = currName;
			else if (currName.length() > longerName.length())
				longerName = currName;
		}
		int maxNameStringSize = longerName.length();

		for (Map.Entry<K, V> rankEntry : map.entrySet()) {
			i = reversed ? i - 1 : i + 1;
			int rankStringSize = String.valueOf(i).length();
			int nameStringSize = rankEntry.getKey().toString().length();
			String spaceFormatRank = " " + " ".repeat(maxRankStringSize - rankStringSize);
			String spaceFormatName = " ".repeat(maxNameStringSize - nameStringSize);
			Displayer.useDisplay("#" + i + spaceFormatRank + rankEntry.getKey().toString() + spaceFormatName + separator
					+ rankEntry.getValue());
		}
	}

}
