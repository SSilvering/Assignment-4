package seaPlants;

import java.awt.Color;
import java.awt.Graphics;

/**
 * This class represents a type of sea plant Laminaria.
 * 
 * @author Shai Hod, ID: 304800402 *
 */

public class Laminaria extends Immobile {

	private Color color = Color.GREEN;
	private int x;
	private int y;
	private int size;

	/**
	 * 
	 * @param x
	 * @param y
	 * @param size
	 */
	public Laminaria(int x, int y, int size){
		super.name = "Laminaria";
		
		this.x = x;
		this.y = y;
		this.size = size;
	}
	
	/**
	 * This method draws the kind Laminaria sea plant.
	 * 
	 * @param g
	 *            gets reference of graphics constructor for drawing that plant.
	 */
	public void draw(Graphics g) {
		g.setColor(color);
		g.fillArc(x - size / 20, y - size / 20, size / 10, size * 4 / 5, 0, 360);
		g.fillArc(x - size * 3 / 20, y - size * 13 / 15, size / 10,
				size * 2 / 3, 0, 360);
		g.fillArc(x + size / 20, y - size * 13 / 15, size / 10, size * 2 / 3,
				0, 360);
		g.drawLine(x, y, x, y - size / 5);
		g.drawLine(x, y, x - size / 10, y - size / 5);
		g.drawLine(x, y, x + size / 10, y - size / 5);
	}
	
	@Override
	public void drawCreature(Graphics g) {
		this.draw(g);
	}

	@Override
	public int getXpos() {
		return x;
	}

	@Override
	public int getYpos() {
		return y;
	}

	@Override
	public int getSize() {
		return size;
	}

	@Override
	public void setXpos(int x) {
		this.x = x;
		
	}

	@Override
	public void setYPos(int y) {
		this.y = y;		
	}
}
