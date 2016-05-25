package seaPlants;

import aquarium.AbstractSeaFactory;
import aquarium.SeaCreature;

/**
 * This class represents a factory for creating a immobile creature.
 * 
 * @author Shai Hod, ID: 304800402
 */

public class PlantFactory implements AbstractSeaFactory {

	private int x;
	private int y;
	private int size;

	public PlantFactory() {

	}

	public PlantFactory(int x, int y, int size) {
		this.x = x;
		this.y = y;
		this.size = size;
	}

	@Override
	public SeaCreature produceSeaCreature(String type) {
		if (type.equals("Zostera"))
			return new Zostera(x, y, size);
		else if (type.equals("Laminaria"))
			return new Laminaria(x, y, size);
		
		return null;
	}
}
