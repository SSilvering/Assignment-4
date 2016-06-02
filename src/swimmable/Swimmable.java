package swimmable;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Vector;
import java.util.concurrent.CyclicBarrier;

import aquarium.Observer;
import aquarium.SeaCreature;

/**
 * This abstract class represents variety types of animals that can swim in the
 * aquarium.
 * 
 * @author Shai Hod, ID: 304800402
 * 
 * @see Fish
 * @see Jellyfish
 */

public abstract class Swimmable extends AnimalFactory implements SeaCreature, Cloneable {

	protected int horSpeed;
	protected int verSpeed;
	protected int size;
	protected int feedFreq;
	protected Color col;
	protected CyclicBarrier barrier;
	protected Vector<Observer> list;

	public Swimmable() {
		horSpeed = 0;
		verSpeed = 0;
	}

	public Swimmable(int hor, int ver, int size, Color col, int feedFreq) {
		horSpeed = hor;
		verSpeed = ver;
		this.size = size;
		this.col = col;
		this.feedFreq = feedFreq;
	}

	public int getHorSpeed() {
		return horSpeed;
	}

	public int getVerSpeed() {
		return verSpeed;
	}

	public void setHorSpeed(int hor) {
		horSpeed = hor;
	}

	public void setVerSpeed(int ver) {
		verSpeed = ver;
	}

	public String getAnimalName(){
		return super.getName();
	}
	
	public void setColor(Color col){
		this.col = col;
	}
	
	public void setSize(int size){
		this.size = size;
	}
	
	public int getSize(){
		return size;
	}
	
	public Color getColorRef(){
		return col;		
	}
	
	public int getFeedFreq(){
		return feedFreq;
	}	
	
	abstract public boolean checkHungry();
	
	abstract public void drawAnimal(Graphics g);

	abstract public void setSuspend();

	abstract public void setResume();

	abstract public void setBarrier(CyclicBarrier b);

	abstract public void eatInc();

	abstract public int getEatCount();

	abstract public String getColor();
	
	abstract public void setReset();
	
	@Override
	public Object clone() {
		Object clone = null;

		try {
			clone = super.clone();

		} catch (CloneNotSupportedException e) {
			return null;
		}

		return clone;
	}
}
