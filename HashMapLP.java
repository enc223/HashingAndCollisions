import java.util.ArrayList;
import java.util.LinkedList;
/*Emma Chiusano
 * CSE 017
 * PP3
 * Date Created: 30 November 2021
 * Last Date Modified: 1 December 2021*/
public class HashMapLP<K, V> {
	public static int iterations;
	static int collisions=0;
	private int size;
	private double loadFactor;
	private HashMapEntry<K, V>[] hashTable;

	// Constructors
	public HashMapLP() {
		this(100, 0.5);
	}
	public HashMapLP(int c) {
		this(c, 0.5);
	}
	public HashMapLP(int c, double lf) {
		hashTable = new HashMapEntry [trimToPowerOf2(c)];
		loadFactor = lf;
		size = 0;
		collisions=0;
	}

	// private methods
	private int trimToPowerOf2(int c) {
		int capacity = 1;
		while (capacity < c)
			capacity = capacity << 1;
		return capacity;
	}
	private int hash(int hashCode) {
		return hashCode & (hashTable.length - 1);
	}
	
	//MODIFY
	private void rehash() {
		ArrayList<HashMapEntry<K,V>> list = toList();
		hashTable = new HashMapEntry[hashTable.length << 1];
		size = 0;
		for(HashMapEntry<K,V> entry: list)
			put(entry.getKey(), entry.getValue());

	}
	// public interface
	public int size() {
		return size;
	}
	
	//MODIFY
	public void clear() {
		size = 0;
		for (int i = 0; i < hashTable.length; i++)
			if (hashTable[i] != null)
				hashTable[i]=null;
	}
	public boolean isEmpty() {
		return (size == 0);
	}
	public boolean containsKey(K key) {
		if (get(key) != null)
			return true;
		return false;
	}
	// returns the value of key if found, null otherwise
	
	//MODIFY
	public V get(K key) {
		iterations = 0;
		int HTIndex = hash(key.hashCode());
		V value=null;
		while (hashTable[HTIndex]!=null) {
			iterations++;
			if((hashTable[HTIndex]).getKey().equals(key)) {
				value=hashTable[HTIndex].getValue();
				return value;
			}
			HTIndex=(HTIndex+1)%hashTable.length;
			/*if(HTIndex!=hashTable.length-1) {
				HTIndex++;
			}else {
				HTIndex=0;
			}*/
		}
		return null;
	}
	// remove a key if found
	
	//MODIFY
	public void remove(K key) {
		int HTIndex = hash(key.hashCode());
		while (hashTable[HTIndex] != null) {
			HashMapEntry<K, V> entry = hashTable[HTIndex];
				if (entry.getKey().equals(key)) {
					hashTable[HTIndex]=null;
					size--;
					return;
				}
				HTIndex=(HTIndex+1)%hashTable.length;
			}
		}
	// adds a new key or modifies an existing key
	
	//MODIFY
	public V put(K key, V value) {
		// key in the hash map - find it and modify its value
		if(get(key) != null) {
			int HTIndex = hash(key.hashCode());
			while(hashTable[HTIndex]!=null) {
				if (hashTable[HTIndex].getKey().equals(key)) {
					V old=hashTable[HTIndex].getValue();
					hashTable[HTIndex].setValue(value);
					return old;
				}
				else {
					HTIndex=(HTIndex+1)%hashTable.length;
				}
			}
		}
			
		if (size>=hashTable.length*loadFactor) {
			rehash();
		}
		
		int HTIndex = hash(key.hashCode());
		if(hashTable[HTIndex]!=null) {
			collisions++;
		}
		
		while(hashTable[HTIndex]!=null) {
			HTIndex=(HTIndex+1)%hashTable.length;
		}
		hashTable[HTIndex]=new HashMapEntry(key, value);
	
		
		size++;
		return value;
	}
	//}/
	// returns the elements of the hash map as a list
	
	//MODIFY
	public ArrayList<HashMapEntry<K,V>> toList(){
		ArrayList<HashMapEntry<K,V>> list = new ArrayList<>();
		for(int i=0; i< hashTable.length; i++) {
			if(hashTable[i]!= null) {
					list.add(hashTable[i]);
			}
		}
		return list;
	}
	// returns the elements of the hash map as a string
	
	//MODIFY
	public String toString() {
		String out = "[";
		for(int i=0; i<hashTable.length; i++) {
			if(hashTable[i]!=null) {
					out += hashTable[i].toString();
				out += "\n";
			}
		}
		out += "]";
		return out;
	}

}
