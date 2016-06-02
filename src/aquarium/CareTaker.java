package aquarium;

import java.util.Hashtable;

/**
 * 
 * @author Shai Hod, ID: 304800402
 *
 */

public class CareTaker {

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
}
