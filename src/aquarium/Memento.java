package aquarium;

import java.awt.Color;

import seaPlants.Immobile;
import swimmable.Swimmable;

/**
 * 
 * @author Shai Hod, ID: 304800402
 *
 */

public class Memento {
	
	private Color col;// for plants too
	private int size; // for plants too
	private int xPos; // for plants too
	private int yPos; // for plants too
	private int verSpeed;
	private int horSpeed;
	private Swimmable animal;
	private Immobile plant;

	public Memento(Swimmable animal, Immobile plant, Color col, int size, int xPos, int yPos, int verSpeed, int horSpeed) {
		this.animal = animal;
		this.plant = plant;
		this.col = col;
		this.size = size;
		this.xPos = xPos;
		this.yPos = yPos;
		this.verSpeed = verSpeed;
		this.horSpeed = horSpeed;
	}
	
	public Swimmable getAnimal(){
		return animal;
	}
	
	public Immobile getPlant(){
		return plant;
	}

	public Color getColor() {
		return col;
	}
	
	public int getSize() {
		return size;
	}
	
	public int getXpos() {
		return xPos;
	}
	
	public int getYpos() {
		return yPos;
	}
	
	public int getVerSpeed() {
		return verSpeed;
	}
	
	public int getHorSpeed() {
		return horSpeed;
	}	
}
