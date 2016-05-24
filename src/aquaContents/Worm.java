package aquaContents;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import aquarium.AquaPanel;

/**
 * This class represents a worm, to feed the animals in the aquarium. This class
 * is designed with the Singleton design pattern, to ensure that an instance of
 * this class will be created only once.
 * 
 * @author Shai Hod, ID: 304800402
 */

public class Worm {

	static private volatile Worm instance = null;

	/**
	 * Private Constructor.
	 */
	private Worm() {
	}

	/**
	 * This method checks to see if there is an instance of the class. If there
	 * is not a show, it will create a new one and returns a reference, or
	 * returns a reference to it.
	 * 
	 * @return the class instantiation.
	 */
	public static Worm getInstance() {
		if (instance == null)
			// double-check
			synchronized (Worm.class) {
				if (instance == null)
					instance = new Worm();
			}
		
		return instance;
	}

	/**
	 * This method draws a single worm on the panel of AquaPanel.
	 * 
	 * @param ap
	 *            gets a reference to AquaPanel.
	 * @param g
	 *            gets a reference to Graphics constructor.
	 */
	public void drawWorm(AquaPanel ap, Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.setStroke(new BasicStroke(3));
		g2.setColor(Color.red);
		g2.drawArc(ap.getWidth() / 2, ap.getHeight() / 2 - 5, 10, 10, 30, 210);
		g2.drawArc(ap.getWidth() / 2, ap.getHeight() / 2 + 5, 10, 10, 180, 270);
		g2.setStroke(new BasicStroke(1));
	}
}
