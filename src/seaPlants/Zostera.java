package seaPlants;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

/**
 * This class represents a type of sea plant Zostera.
 * 
 * @author Shai Hod, ID: 304800402
 */

public class Zostera extends Immobile {

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
	public Zostera(int x, int y, int size){
		super.name = "Zostera";
		
		this.x = x;
		this.y = y;
		this.size = size;		
	}
	
	/**
	 * This method draws the kind Zostera sea plant.
	 * 
	 * @param g
	 *            gets reference of graphics constructor for drawing that plant.
	 */
	public void draw(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.setStroke(new BasicStroke(3));
		g2.setColor(color);
		g.drawLine(x, y, x, y - size);
		g.drawLine(x - 2, y, x - 10, y - size * 9 / 10);
		g.drawLine(x + 2, y, x + 10, y - size * 9 / 10);
		g.drawLine(x - 4, y, x - 20, y - size * 4 / 5);
		g.drawLine(x + 4, y, x + 20, y - size * 4 / 5);
		g.drawLine(x - 6, y, x - 30, y - size * 7 / 10);
		g.drawLine(x + 6, y, x + 30, y - size * 7 / 10);
		g.drawLine(x - 8, y, x - 40, y - size * 4 / 7);
		g.drawLine(x + 8, y, x + 40, y - size * 4 / 7);
		g2.setStroke(new BasicStroke(1));
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
