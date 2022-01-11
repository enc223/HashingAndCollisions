import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
/*Emma Chiusano
 * CSE 017
 * PP3
 * Date Created: 30 November 2021
 * Last Date Modified: 1 December 2021*/
public class Test {
	public static void main(String[]Args) {
		HashMapSC<String, String> hmSC;
		HashMapLP<String, String> hmLP;
		
		ArrayList<String> dictionary=new ArrayList<>(50000);
		int index=0; 
		int scIt=0;
		int lpIt=0;
		
		System.out.println("Testing the method put(key, value)--Total Number of Collisions");
		System.out.printf("%-20s\t%-20s\t%-20s\n", "Size", "HashMapSC", "HashMapLP");
		
		for(int i=10000; i<100000; i+=10000) {
			hmSC=new HashMapSC<>(i);
			hmLP=new HashMapLP<>(i);
			
			readTextFromFile("dictionary.txt", hmSC, hmLP);
			System.out.printf("%-20d\t%-20d\t%-20d\n", i,HashMapSC.collisions, HashMapLP.collisions);
		}
		readTextFromFile("dictionary.txt", dictionary);
		hmSC=new HashMapSC<>(50000);
		hmLP=new HashMapLP<>(50000);
		
		readTextFromFile("dictionary.txt", hmSC, hmLP);
		System.out.println("Testing the method get(key, )--Total Number of Iterations");
		System.out.printf("%-20s\t%-20s\t%-20s\n", "Word", "HashMapSC", "HashMapLP");
		for(int i=0; i<1000; i++) {
			int ranNum=(int)(Math.random()*dictionary.size());
			hmSC.get(dictionary.get(ranNum));
			hmLP.get(dictionary.get(ranNum));
			scIt+=HashMapSC.iterations;
			lpIt+=HashMapLP.iterations;
			if(i%20==0) {
				System.out.printf("%-20s\t%-20d\t%-20d\n", dictionary.get(ranNum), HashMapSC.iterations, HashMapLP.iterations);
			}
		}
		System.out.printf("%-20s\t%-20d\t%-20d\n", "Average:",scIt/1000, lpIt/1000);
	}
	private static void readTextFromFile(String filename, ArrayList<String> dictionary) {
		File file = new File("dictionary.txt");
		try {
			Scanner readFile=new Scanner(file);
			while(readFile.hasNextLine()) {
				String line=readFile.nextLine();
				String[] tokens=line.split("\\|");
				String dictWord=tokens[0];
				String dictDef=tokens[1];
				
				dictionary.add(dictWord);
			}
			readFile.close();
		}
		catch(FileNotFoundException e) {
			System.out.println("File not found.");
			System.exit(0);
		}	
	}
	private static void readTextFromFile(String filename, HashMapSC<String, String> hmSC, HashMapLP<String, String> hmLP) {
		File file = new File("dictionary.txt");
		try {
			Scanner readFile=new Scanner(file);
			while(readFile.hasNextLine()) {
				String line=readFile.nextLine();
				String[] tokens=line.split("\\|");
				String dictWord=tokens[0];
				String dictDef=tokens[1];
				
				hmSC.put(dictWord, dictDef);
				hmLP.put(dictWord, dictDef);
			}
			readFile.close();
		}
		catch(FileNotFoundException e) {
			System.out.println("File not found.");
			System.exit(0);
		}	
	}
}


/*
 * Discussion:
 * HashMapSC Put Performance: The number of collisions of the HashMapSC method decreases as the size 
 * increases. This makes sense to me because as size gets larger, there are more open spots available in 
 * the HashMap, which will decrease the amount of collisions that occur.
 * 
 * HashMapSC Get Performance: Since you are only getting one word at a time, it makes sense that the get method
 * would not take many iterations to find the word from the dictionary. 
 * 
 * HashMapLP Put Performance: The number of collisions of the HashMapLP method decreases as the size 
 * increases. This makes sense to me because as size gets larger, there are more open spots available in 
 * the HashMap, which will decrease the amount of collisions that occur.
 * 
 * 
 * HashMapLP Get Performance: Since you are only getting one word at a time, it makes sense that the get method
 * would not take many iterations to find the word from the dictionary. 
 * */
 