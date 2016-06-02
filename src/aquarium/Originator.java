package aquarium;

import java.awt.Color;

/**
 * 
 * @author Shai Hod, ID: 304800402
 *
 */

public class Originator {

	private Color col;// for plants too
	private int size; // for plants too
	private int xPos; // for plants too
	private int yPos; // for plants too
	private int verSpeed;
	private int horSpeed;

	public void set(Color col, int size, int xPos, int yPos, int verSpeed, int horSpeed) {
		this.col = col;
		this.size = size;
		this.xPos = xPos;
		this.yPos = yPos;
		this.verSpeed = verSpeed;
		this.horSpeed = horSpeed;
	}

	public Memento saveToMemento() {
		return new Memento(col, size, xPos, yPos, verSpeed, horSpeed);
	}

	public void restoreFromMemento(Memento m) {
		this.col = m.getColor();
		this.size = m.getSize();
		this.xPos = m.getXpos();
		this.yPos = m.getYpos();
		this.verSpeed = m.getVerSpeed();
		this.horSpeed = m.getHorSpeed();
	}
}
