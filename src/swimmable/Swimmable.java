package swimmable;

import java.awt.Color;
import java.awt.Graphics;
import java.util.UUID;
import java.util.Vector;
import java.util.concurrent.CyclicBarrier;

import aquarium.HungerState;
import aquarium.Observer;
import aquarium.Satiated;
import aquarium.SeaCreature;
import aquarium.Memento;

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

	private final String uid = UUID.randomUUID().toString(); // generate unique id to specific instance.
	protected HungerState state;
	protected int horSpeed;
	protected int verSpeed;
	protected int size;
	protected int feedFreq;
	protected String name;
	protected Color col;
	protected boolean stopCheck = false;
	protected CyclicBarrier barrier;
	protected Vector<Observer> list; // List of observers. In practice we have
										// only one observer it AquaPanel.

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
		
		Satiated satiated = new Satiated();
		satiated.action(this);
	}
	
	/**
	 * This method change animal's hunger state.
	 * 
	 * @param state
	 *            A specific state for assignment.
	 */
	public void setHungerState(HungerState state){
		this.state = state;
	}
	
	/**
	 * This method returns the unique id of specific instance.
	 * 
	 * @return the unique id of the instance.
	 */
	public String getUID(){
		return uid;
	}

	public void setStopCheck(boolean bool){
		this.stopCheck = bool;
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
		return name;
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
	
	public Memento saveToMemento() {
		return new Memento(this, null, col, size, this.get_X_front(),
				this.get_Y_front(), verSpeed, horSpeed);
	}
	
	abstract public int get_X_front();
	
	abstract public int get_Y_front();
	
	abstract public void set_X_front(int x);
	
	abstract public void set_Y_front(int y); 
	
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
