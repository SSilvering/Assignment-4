package swimmable;

import java.awt.Color;

import aquarium.AbstractSeaFactory;
import aquarium.AquaPanel;
import aquarium.SeaCreature;

/**
 * This class represents a factory for creating a creature that can swim.
 * 
 * @author Shai Hod, ID: 304800402
 */

public class AnimalFactory extends Thread implements AbstractSeaFactory {

	private AquaPanel ap;
	private Color col;
	private int horSpeed;
	private int verSpeed;
	private int size;

	public AnimalFactory() {

	}

	public AnimalFactory(AquaPanel ap, Color col, int horSpeed, int verSpeed,
			int size) {
		this.ap = ap;
		this.col = col;
		this.horSpeed = horSpeed;
		this.verSpeed = verSpeed;
		this.size = size;
	}

	@Override
	public SeaCreature produceSeaCreature(String type) {
		if (type.equals("Fish"))
			return new Fish(ap, col, horSpeed, verSpeed, size);
		else if (type.equals("Jellyfish"))
			return new Jellyfish(ap, col, horSpeed, verSpeed, size);

		return null;
	}
}
