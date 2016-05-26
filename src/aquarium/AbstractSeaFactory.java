package aquarium;

/**
 * This class represents an abstract factory for creating new creatures to the
 * aquarium.
 * 
 * @author Shai Hod, ID: 304800402
 */

public interface AbstractSeaFactory {

	public SeaCreature produceSeaCreature(String type);
}
