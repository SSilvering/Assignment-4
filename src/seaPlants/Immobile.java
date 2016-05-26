package seaPlants;

import java.awt.Graphics;

import aquarium.SeaCreature;

/**
 * This abstract class represents common interface for plants in the aquarium.
 * 
 * @author Shai Hod, ID: 304800402
 */

public abstract class Immobile extends PlantFactory implements SeaCreature {

	String name;
	
	public abstract void drawCreature(Graphics g);
	
	public String toString(){
		return name;
	}
}
