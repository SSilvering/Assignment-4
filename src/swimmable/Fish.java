package swimmable;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

import aquarium.AquaPanel;
import aquarium.HungerState;
import aquarium.Hungry;
import aquarium.Satiated;

/**
 * This class represents a single fish.
 * 
 * @author Shai Hod, ID: 304800402
 *
 * @see Swimmable
 */

public class Fish extends Swimmable {

	private AquaPanel aquaPanel;
	private int x_dir;
	private int y_dir;
	private int x_front;
	private int y_front;
	private int foodCount;
	private boolean isSuspend = false;
	private boolean isReset = false;
	private int numTurns = 0;
	private int temp_x_dir = 0;

	/**
	 * Constructor for initializing a new fish.
	 * 
	 * @param aquaPanel
	 *            gets reference to the AquaPanel.
	 * @param col
	 *            gets the color of the current fish.
	 * @param horSpeed
	 *            gets the horizontal speed of the current fish.
	 * @param verSpeed
	 *            gets the vertical speed of the current fish.
	 * @param size
	 *            gets the size of a fish.
	 * @param feedFreq
	 *            gets frequency of feeding for a specific fish.
	 */
	public Fish(AquaPanel aquaPanel, Color col, int horSpeed, int verSpeed,
			int size, int feedFreq) {
		super(horSpeed, verSpeed, size, col, feedFreq);
		super.name = "Fish";
		super.setName(name);

		// check if adding new fish when the board is suspended, in case and
		// that is true and the game was suspended it avoids from the new animal
		// to start moving
		isSuspend = AquaPanel.AQisSuspend;

		Random rand = new Random();

		if (rand.nextInt(1 + 1) == 0)
			this.x_dir = -1;
		else
			this.x_dir = 1;

		this.temp_x_dir = x_dir;

		this.y_dir = 1;
		this.x_front = rand.nextInt((aquaPanel.getWidth() - size) + 1) + size;
		this.y_front = 0; // adding a new fish from the top of the aquarium.

		this.foodCount = 0;

		this.aquaPanel = aquaPanel;
	}

	/**
	 * Copy Constructor.
	 * 
	 * @param obj
	 *            gets reference to object which going to be copied.
	 */
	public Fish(Fish obj) {
		super(obj.horSpeed, obj.verSpeed, obj.size, obj.col, obj.feedFreq);
		super.setName(obj.getName());

		// check if adding new fish when the board is suspended, in case and
		// that is true and the game was suspended it avoids from the new animal
		// to start moving
		isSuspend = AquaPanel.AQisSuspend;

		if (obj.x_dir == 1)
			this.x_dir = -1;
		else
			this.x_dir = 1;

		this.y_dir = obj.y_dir;
		this.x_front = obj.x_front;
		this.y_front = obj.y_front;

		this.foodCount = 0;

		this.aquaPanel = obj.aquaPanel;
	}

	@Override
	public Fish clone() {
		return new Fish(this);
	}

	/**
	 * This method paints a fish.
	 * 
	 * @param g
	 *            gets a reference to Graphics constructor.
	 */
	@Override
	public void drawAnimal(Graphics g) {
		g.setColor(col);
		if (x_dir == 1) { // fish swims to right side

			// Body of fish
			g.fillOval(x_front - size, y_front - size / 4, size, size / 2);

			// Tail of fish
			int[] x_t = { x_front - size - size / 4, x_front - size - size / 4,
					x_front - size };
			int[] y_t = { y_front - size / 4, y_front + size / 4, y_front };
			Polygon t = new Polygon(x_t, y_t, 3);
			g.fillPolygon(t);

			// Eye of fish
			Graphics2D g2 = (Graphics2D) g;
			g2.setColor(new Color(255 - col.getRed(), 255 - col.getGreen(),
					255 - col.getBlue()));
			g2.fillOval(x_front - size / 5, y_front - size / 10, size / 10,
					size / 10);

			// Mouth of fish
			if (size > 70)
				g2.setStroke(new BasicStroke(3));
			else if (size > 30)
				g2.setStroke(new BasicStroke(2));
			else
				g2.setStroke(new BasicStroke(1));
			g2.drawLine(x_front, y_front, x_front - size / 10, y_front + size
					/ 10);
			g2.setStroke(new BasicStroke(1));

		} else { // fish swims to left side

			// Body of fish
			g.fillOval(x_front, y_front - size / 4, size, size / 2);

			// Tail of fish
			int[] x_t = { x_front + size + size / 4, x_front + size + size / 4,
					x_front + size };
			int[] y_t = { y_front - size / 4, y_front + size / 4, y_front };
			Polygon t = new Polygon(x_t, y_t, 3);
			g.fillPolygon(t);

			// Eye of fish
			Graphics2D g2 = (Graphics2D) g;
			g2.setColor(new Color(255 - col.getRed(), 255 - col.getGreen(),
					255 - col.getBlue()));
			g2.fillOval(x_front + size / 10, y_front - size / 10, size / 10,
					size / 10);

			// Mouth of fish
			if (size > 70)
				g2.setStroke(new BasicStroke(3));
			else if (size > 30)
				g2.setStroke(new BasicStroke(2));
			else
				g2.setStroke(new BasicStroke(1));
			g2.drawLine(x_front, y_front, x_front + size / 10, y_front + size
					/ 10);
			g2.setStroke(new BasicStroke(1));
		}
	}

	/**
	 * This method suspends the thread of a specific fish.
	 */
	@Override
	public void setSuspend() {
		isSuspend = true;
		AquaPanel.AQisSuspend = true; // in case of adding new fish when the
										// board suspended
	}

	/**
	 * This method resumes all the threads of fish.
	 */
	@Override
	public void setResume() {
		isSuspend = false;
		AquaPanel.AQisSuspend = false; // in case of adding new fish when the
										// board suspended

		synchronized (this) { // wake-up the animals at the same time
			notify();
		}
	}

	/**
	 * This method marks true for resetting the board and stop the specific
	 * thread.
	 */
	public void setReset() {
		isReset = true;
	}

	/**
	 * This method suspends fish thread when food appears.
	 * 
	 * @param b
	 *            references of CyclicBarrier from AquaPanel.
	 * @see AquaPanel
	 */
	@Override
	public void setBarrier(CyclicBarrier b) {
		super.barrier = b;
	}

	/**
	 * This method increments the food counter if the specific fish ate the
	 * food.
	 */
	@Override
	public void eatInc() {
		foodCount++;
	}

	/**
	 * This method returns the food counter for a specific fish.
	 * 
	 * @return the food counter for a specific jellyfish.
	 */
	@Override
	public int getEatCount() {
		return foodCount;
	}

	/**
	 * This method returns the color of a specific fish.
	 */
	@Override
	public String getColor() {
		return AquaPanel.ColorName(col);
	}

	/**
	 * This method makes sure that the fish swam only within the board. When the
	 * fish comes to any border of the board, the method changes the direction
	 * of his swimming.
	 */
	private void fishMoveBound() {
		if (x_front >= aquaPanel.getWidth()) {
			x_dir = -1;
			x_front -= (size * 1.25);
		}
		if (x_front <= 0) {
			x_dir = 1;
			x_front += size * 1.25;
		}
		if (y_front > (aquaPanel.getHeight() - 119)) {
			y_dir = -1;
		}
		if (y_front < size / 4) {
			y_dir = 1;
		}
	}

	/**
	 * This method calculates the new speed and directs the animal to center of
	 * the panel, when there is a food.
	 */
	protected void toCenter() {
		int v_hor_new = 0, v_ver_new = 0;
		double k, v_old;

		v_old = Math.sqrt(Math.pow(horSpeed, 2) + Math.pow(verSpeed, 2));

		// calculating the new speed for the animal
		try {
			k = Math.abs((y_front - aquaPanel.getHeight() / 2)
					/ (x_front - aquaPanel.getWidth() / 2));
			v_hor_new = (int) (v_old / Math.sqrt(k * k + 1));
			if (v_hor_new > 10)
				v_hor_new = 10;
			v_ver_new = (int) (v_hor_new * k);
			if (v_ver_new > 10)
				v_ver_new = 10;
		} catch (ArithmeticException ex) { // in case of dividing by zero
			v_hor_new = 0;
			v_ver_new = (int) v_old;
		}

		// change direction of the animal
		if (v_hor_new != 0) {
			if (x_front > aquaPanel.getWidth() / 2)
				x_dir = -1;
			else
				x_dir = 1;
		}
		if (y_front > aquaPanel.getHeight() / 2)
			y_dir = -1;
		else
			y_dir = 1;

		// update position of the animal with the new speed
		x_front += (v_hor_new * x_dir);
		y_front += (v_ver_new * y_dir);
	}

	@Override
	public void run() {
		while (true) {
			try {
				synchronized (this) {
					sleep(40);
				}

				if (isSuspend == true) {
					synchronized (this) { // stop the animals at the same time
						wait();
					}
				}

				if (isReset == true) {
					try { // try to stop the older thread
						while (!currentThread().isInterrupted()) {
							synchronized (this) {
								wait();
							}
						}
					} catch (InterruptedException consumed) {
						interrupt();
					}
				}

				if (aquaPanel.thereIsFood() == true) {
					if (barrier != null) {
						barrier.await();
					}

					if (super.state.toString().equals("Hungry"))

						toCenter(); // change the swim direction of fish to the
									// panel center.

					// check if the fish nears to food in less from 7 pixels
					if (super.state.toString().equals("Hungry")
							&& (Math.abs(x_front - aquaPanel.getWidth() / 2) <= 7)
							&& (Math.abs(y_front - aquaPanel.getHeight() / 2) <= 7)) {
						synchronized (this) {
							aquaPanel.ateFood(this);
							numTurns = 0;

							Satiated satiated = new Satiated();
							satiated.action(this);

							notify();
						}
					}

				} else {
					fishMoveBound(); // boundaries for the movement of the fish
				}

				fishMoveBound(); // boundaries for the movement of the fish

			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (BrokenBarrierException e) {
				e.printStackTrace();
			} catch (NullPointerException e) {
				e.printStackTrace();
			}

			// update position of the animal
			x_front += (x_dir * horSpeed);
			y_front += (y_dir * verSpeed);

			if (checkHungry()) {
				synchronized (this) {
					HungerState oldState = super.state;

					Hungry hungry = new Hungry();
					hungry.action(this);

					super.stopCheck = true; // prevent counting turns while animal goes to the center. 

					if (super.stopCheck == true
							&& oldState.toString().equals("Satiated")) {
						aquaPanel.notify("Hungry");
						numTurns = 0;
					}
				}
			}

			aquaPanel.repaint();
		}
	}

	@Override
	public void drawCreature(Graphics g) {
		this.drawAnimal(g);
	}

	@Override
	public boolean checkHungry() {
		if (super.stopCheck == false) {
			if (temp_x_dir != x_dir) {
				if (numTurns < super.feedFreq)
					++numTurns;

				temp_x_dir = x_dir;
			}

			if (numTurns == super.feedFreq)
				return true;
		}

		return false;
	}

	@Override
	public int get_X_front() {
		return x_front;
	}

	@Override
	public int get_Y_front() {
		return y_front;
	}

	@Override
	public void set_X_front(int x) {
		this.x_front = x;
	}

	@Override
	public void set_Y_front(int y) {
		this.y_front = y;
	}

	@Override
	public void PaintFish(Color col) {
		this.col = col;		
	}
}
