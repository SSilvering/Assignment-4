package seaPlants;

import java.awt.Color;
import java.awt.Graphics;

import aquarium.SeaCreature;
import aquarium.Memento;

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
	
	public Memento saveToMemento() {
		return new Memento(null, this, Color.GREEN, this.getSize(),
				this.getXpos(), this.getYpos(), 0, 0);
	}
	
	abstract public int getXpos();
	
	abstract public int getYpos();
	
	abstract public int getSize();
	
	abstract public void setXpos(int x);
	
	abstract public void setYPos(int y);
}
