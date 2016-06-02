package aquarium;

import java.util.Hashtable;

import swimmable.Swimmable;

/**
 * This class represents the cache containing the animals in the aquarium to
 * create a prototype to be used in the animal clone.
 * 
 * @author Shai Hod, ID: 304800402
 */

public class AnimalCache {

	private static Hashtable<String, Swimmable> animalMap = new Hashtable<String, Swimmable>();

	public static Swimmable getAnimal(String type) {
		Swimmable cachedAnimal = animalMap.get(type);
		return (Swimmable) cachedAnimal.clone();
	}

	public static void loadCache(Integer key, Swimmable animal) {
		animalMap.put(key.toString(), animal);
	}

	public static void setReset() {
		animalMap.clear();
	}
}
