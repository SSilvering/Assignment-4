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
	
	abstract public int getXpos();
	
	abstract public int getYpos();
	
	abstract public int getSize();
	
	abstract public void setXpos(int x);
	
	abstract public void setYPos(int y);
}
