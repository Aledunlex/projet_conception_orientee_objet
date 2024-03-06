package SportsCompet.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

/**
 * Graciously given by the COO teachers. Used to work on Maps. Method
 * sortedMapToLinkedList was added by us.
 */
public class MapUtil {

	/**
	 * Sorts a map by value
	 * 
	 * @param <K> key of the given map
	 * @param <V> value to the key
	 * @param map given map to be sorted
	 * @return the sorted map
	 */
	public static <K, V extends Comparable<? super V>> Map<K, V> sortByDescendingValue(Map<K, V> map) {
		List<Entry<K, V>> sortedEntries = new ArrayList<Entry<K, V>>(map.entrySet());
		Collections.sort(sortedEntries, new Comparator<Entry<K, V>>() {
			@Override
			public int compare(Entry<K, V> e1, Entry<K, V> e2) {
				return e2.getValue().compareTo(e1.getValue());
			}
		});
		Map<K, V> result = new LinkedHashMap<>();
		for (Entry<K, V> entry : sortedEntries) {
			result.put(entry.getKey(), entry.getValue());
		}
		return result;
	}

	/**
	 * Returns a sorted linked list in which keys are in descending order of value
	 * from a sorted map
	 * 
	 * @param <K> key of the given map. Must be already sorted!
	 * @param <V> value to the key
	 * @param map given map to be sorted
	 * @return the corresponding LinkedList of sorted keys
	 */
	public static <K, V> LinkedList<K> sortedMapToLinkedList(Map<K, V> map) {
		Set<K> sortedEntries = map.keySet();
		LinkedList<K> sortedLinkedList = new LinkedList<K>();
		sortedLinkedList.addAll(sortedEntries);
		return sortedLinkedList;
	}

}