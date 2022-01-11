/*Emma Chiusano
 * CSE 017
 * PP3
 * Date Created: 30 November 2021
 * Last Date Modified: 1 December 2021*/
public class HashMapEntry<K, V> {
	private K key;
	private V value;

	public HashMapEntry(K k, V v) {
		key = k;
		value = v;
	}

	public K getKey() {
		return key;
	}

	public V getValue() {
		return value;
	}

	public void setKey(K k) {
		key = k;
	}

	public void setValue(V v) {
		value = v;
	}

	public String toString() {
		return "(" + key + ", " + value + ")";
	}
}

