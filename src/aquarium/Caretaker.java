package aquarium;

import java.util.ArrayList;
import java.util.Hashtable;

/**
 * 
 * @author Shai Hod, ID: 304800402
 *
 */

public class Caretaker {

	private static Hashtable<String, Memento> Map = new Hashtable<String, Memento>();
	
	public static void add(String key, Memento state) {
		Map.put(key, state);
	}

	public static Memento get(int index) {
		return Map.get(index);
	}
	
	public static int getCount(){
		return Map.size();
	}

	public static void setClear() {
		Map.clear();
	}
	
	public static boolean containsKey(String key){
		if(Map.containsKey(key))
			return true;
		
		return false;
	}
	
	public static ArrayList<Memento> toList(){
		return new ArrayList<Memento>(Map.values());
	}	
}
