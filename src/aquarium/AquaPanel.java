package aquarium;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FileDialog;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.concurrent.CyclicBarrier;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import swimmable.Swimmable;
import aquaContents.Worm;

/**
 * This class represents the panels and other elements in the main framework.
 * 
 * @author Shai Hod, ID: 304800352
 *
 */

public class AquaPanel extends JPanel implements aquarium.Observer{

	private static final long serialVersionUID = 1L;
	private AquaFrame aquaFrame;
	private BufferedImage img = null;
	private HashSet<SeaCreature> creatures = new HashSet<SeaCreature>();
	private Iterator<SeaCreature> ITE;
	private JScrollPane scrollPane;
	private boolean isFood = false;
	private boolean infoClicked = false;
	private int animalsCount = 0;
	private int plantsCount = 0;
	private JPanelDecorator JPD;
	public static boolean ifPressed = false;
	public static boolean AQisSuspend = false;

	/**
	 * Constructor that keeps a reference of the main framework (AquaFrame) to
	 * get an easy access. Also builds all the panels for the main framework.
	 * 
	 * @param aquaFrame
	 *            reference of the main framework.
	 */
	public AquaPanel(AquaFrame aquaFrame) {
		this.aquaFrame = aquaFrame;
		this.aquaFrame.setBounds(0, 0, 800, 600);
		this.aquaFrame.setLocationRelativeTo(aquaFrame);
		this.aquaFrame.setResizable(false);
		this.setBackground(Color.WHITE);
		Panel(); // initialize panel buttons
	}

	/**
	 * This method creates the lower panel of buttons and adds it to the main
	 * framework.
	 */
	private void Panel() {
		BorderLayout layout = new BorderLayout();
		this.setLayout(layout);
		JPanel Panel = new JPanel();
		Panel.setSize(800, 600);
		Panel.setLayout(new FlowLayout());

		// "Add Animal" button.
		JButton addAnimalBtn = new JButton("<html><div style='text-align: center;'>Add<br />Animal</html>");
		addAnimalBtn.setPreferredSize(new Dimension(74, 35));
		addAnimalBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Add Animal");
				addAnimalDialog();
			}
		});
		Panel.add(addAnimalBtn);

		// "Add Plant" button.
		JButton addPlantBtn = new JButton("<html><div style='text-align: center;'>Add<br />Plant</html>");
		addPlantBtn.setPreferredSize(new Dimension(74, 35));
		addPlantBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Add Plant");
				addPlantDialog();
			}
		});
		Panel.add(addPlantBtn);
		
		// "Duplicate Animal" button.
		JButton dupAnimalBtn = new JButton("<html><div style='text-align: center;'>Duplicate<br />Animal</html>");
		dupAnimalBtn.setPreferredSize(new Dimension(74, 35));
		dupAnimalBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Duplicate Animal");
				duplicateAnimal();
			}
		});
		Panel.add(dupAnimalBtn);
		
		// "Decorator" button
		JButton decoratorBtn = new JButton("<html><div style='text-align: center;'>Decorator<br /></html>");
		decoratorBtn.setPreferredSize(new Dimension(78, 35));
		decoratorBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Decorate Animal");
				decorateAnimal();
			}
		});
		Panel.add(decoratorBtn);
		
		// "Sleep" button.
		JButton sleepBtn = new JButton("Sleep");
		sleepBtn.setPreferredSize(new Dimension(74, 35));
		sleepBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Sleep");
				animalSleep();
			}
		});
		Panel.add(sleepBtn);

		// "Wake up" button.
		JButton wakeUpBtn = new JButton("<html><div style='text-align: center;'>Wake<br />Up</html>");
		wakeUpBtn.setPreferredSize(new Dimension(74, 35));
		wakeUpBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Wake UP");
				animalWakeup();
			}
		});
		Panel.add(wakeUpBtn);

		// "Reset" button.
		JButton resetBtn = new JButton("Reset");
		resetBtn.setPreferredSize(new Dimension(74, 35));
		resetBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Reset");
				resetBoard();
			}
		});
		Panel.add(resetBtn);

		// "Food" button.
		JButton foodBtn = new JButton("Food");
		foodBtn.setPreferredSize(new Dimension(74, 35));
		foodBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Food");
				food();
			}
		});
		Panel.add(foodBtn);

		// "Info" button.
		JButton infoBtn = new JButton("Info");
		infoBtn.setPreferredSize(new Dimension(74, 35));
		infoBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Info");
				info();
			}
		});
		Panel.add(infoBtn);

		// "Exit" button.
		JButton exitBtn = new JButton("Exit");
		exitBtn.setPreferredSize(new Dimension(74, 35));
		exitBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Exit");
				System.exit(0);
			}
		});
		Panel.add(exitBtn);

		this.add(Panel, BorderLayout.SOUTH);
	}
	
	/**
	 *
	 */
	public void decorateAnimal() {
		ifPressed = !ifPressed;
		
		if (animalsCount != 0) {
			
			if (ifPressed) {
				JPD = new JPanelDecorator(this, creatures);
				add(JPD);
			} else {
				JPD.dispose();
			}
			
		} else 
		{
			JOptionPane.showMessageDialog(this, "No animals in the aquarium.\nYou need to add one animal at least.");
			
			ifPressed = !ifPressed;
		}

		revalidate();
		
		repaint();			
	}

	public int getAnimalCount(){
		return animalsCount;
	}
	
	public int getPlantCount(){
		return plantsCount;
	}
	
	/**
	 * This method opens the saving state dialog for saving state of animal or plant.
	 */
	public void saveDialog(){
		animalSleep(); // hold animals for saving specific moment.
		
		new SaveDialog(this, creatures);
		
		animalWakeup(); // release animals from holding.
	}
	
	/**
	 * This method opens the restore dialog for restoring state of animal or plant.
	 */
	public void restoreDialog() {
		new RestoreDialog(this);
	}

	/**
	 * This method opening the duplicate animal dialog for duplicating an animal
	 * that already in the aquarium. It checks for the number of animals in the
	 * aquarium, if less than 5, duplicate animal dialog will open, otherwise
	 * appropriate message will appear.
	 */
	private void duplicateAnimal() {
		if (animalsCount < 5) {
			new DuplicateAnimalDialog(this, creatures);
		} else {
			JOptionPane.showMessageDialog(aquaFrame,
					"Cannot duplicate animals at this moment. \nCannot be more than 5 animals in the aquarium.", "Error",
					JOptionPane.INFORMATION_MESSAGE);
		}
	}

	/**
	 * This method shows a table with all the information about the animals in
	 * the aquarium.
	 */
	private void info() {
		infoClicked = !infoClicked;

		if (infoClicked) {
			int totalFood = 0;
			DefaultTableModel model = new DefaultTableModel();
			model.setColumnIdentifiers(new String[] { "Animal", "Color",
					"Size", "Hor. Speed", "Ver. Speed", "Eat Count", "Feeding Freq." });
			JTable table = new JTable(model);

			ITE = creatures.iterator();
			while (ITE.hasNext()) {
				try{
				Swimmable cur = (Swimmable) ITE.next();

				model.addRow(new Object[] { cur.getAnimalName(),
						cur.getColor(), cur.getSize(), cur.getHorSpeed(),
						cur.getVerSpeed(), cur.getEatCount(), cur.getFeedFreq() });

				totalFood += cur.getEatCount();
				} catch (ClassCastException ex){}
			}
			model.addRow(new Object[] { "Total:", "", "", "", "", totalFood });

			table.setPreferredScrollableViewportSize(table.getPreferredSize());
			scrollPane = new JScrollPane(table);
			add(scrollPane, BorderLayout.NORTH);
			aquaFrame.setVisible(true);
		} else {
			this.remove(scrollPane);
			validate();
			repaint();
			scrollPane = null;
			aquaFrame.setVisible(true);
		}
	}

	/**
	 * This method triggers small a waiting to a specific thread for all the
	 * other different threads will see there is food in the aquarium at the
	 * same moment.
	 */
	private void food() {
		try {
			isFood = true;
			CyclicBarrier b = new CyclicBarrier(animalsCount);
			ITE = creatures.iterator();
			while (ITE.hasNext()) {
				try {
					((Swimmable) ITE.next()).setBarrier(b);
				} catch (ClassCastException ex) {
				}
			}
		} catch (IllegalArgumentException ex) {
			JOptionPane.showMessageDialog(aquaFrame,
					"Please add one animal at least for giving a food.",
					"Error", JOptionPane.INFORMATION_MESSAGE);
			isFood = false;
		}
	}

	/**
	 * This boolean method returns if there is a food in the aquarium.
	 * 
	 * @return true, if there is a food on the panel. otherwise return false.
	 */
	public boolean thereIsFood() {
		return isFood;
	}

	/**
	 * This is CALLBACK method that increments the food counter of a specific
	 * animal.
	 * 
	 * @param anim
	 *            get the instance of a specific animal.
	 */
	public void ateFood(Swimmable anim) {
		synchronized (this) {
			anim.eatInc(); // increments the food counter of a specific animal.
			isFood = false;
			
			// after callback, allows checking of counting turns again.
			ITE = creatures.iterator();
			while (ITE.hasNext()) {
				try {
					((Swimmable) ITE.next()).setStopCheck(false);
				} catch (ClassCastException ex) {
				}
			}			
		}
	}

	/**
	 * This method update the boolean reset indicator to true in a specific
	 * animal for stopping all the animals threads.
	 */
	private void resetBoard() {
		ITE = creatures.iterator();
		while (ITE.hasNext()) {
			try {
				((Swimmable) ITE.next()).setReset();
			} catch (ClassCastException ex) {
			}
		}

		creatures.clear(); // clear the main frame from animal.
		isFood = false; // if there is a food on the board it clears it.
		animalsCount = 0;
		plantsCount = 0;		

		if (img != null)
			img = null;
		this.repaint();
		this.setBackground(Color.WHITE);
	}

	/**
	 * This method causes to all the threads of the aquarium to wake up.
	 */
	private void animalWakeup() {
		ITE = creatures.iterator();
		while (ITE.hasNext()) {
			try{
			((Swimmable) ITE.next()).setResume();
			}catch (ClassCastException ex){
			}
		}
	}

	/**
	 * This method causes to all the threads of the aquarium to sleep.
	 */
	private void animalSleep() {
		ITE = creatures.iterator();
		while (ITE.hasNext()) {
			try{
			((Swimmable) ITE.next()).setSuspend();
			}catch (ClassCastException ex){
			}
		}
	}

	/**
	 * This method opening the adds animal dialog for adding new animal to the
	 * aquarium. It checks for the number of animals in the aquarium, if less
	 * than 5, add animal dialog will open, otherwise appropriate message will
	 * appear.
	 */
	private void addAnimalDialog() {
		if (animalsCount > 4)
			JOptionPane.showMessageDialog(aquaFrame,
					"Cannot create more than 5 animals.", "Error",
					JOptionPane.INFORMATION_MESSAGE);
		else if (isFood == false)
			new AddAnimalDialog(this);
		else {
			JOptionPane.showMessageDialog(aquaFrame,
					"Wait until animals will eat their food.", "Error",
					JOptionPane.INFORMATION_MESSAGE);
		}
	}
	
	/**
	 * This method opening the adds plant dialog for adding new plant to the
	 * aquarium. It checks for the number of plants in the aquarium, if less
	 * than 5, add plant dialog will open, otherwise appropriate message will
	 * appear.
	 */
	private void addPlantDialog() {	
		if (plantsCount > 4)
			JOptionPane.showMessageDialog(aquaFrame,
					"Cannot create more than 5 plants.", "Error",
					JOptionPane.INFORMATION_MESSAGE);
		else 
			new AddPlantDialog(this);		
	}

	/**
	 * This method adds a new creature to the aquarium.
	 * 
	 * @param sc
	 *            gets a SeaCreature instance for adding to the panel.
	 */
	protected void addCreature(SeaCreature sc) {
		if (sc.toString().equals("Zostera")
				|| sc.toString().equals("Laminaria")) {
			
			if(creatures.contains(sc) == false){
				creatures.add(sc);
				plantsCount++;
			}
			
			this.repaint();
		} else {
			try {
				if (creatures.contains(sc) == false) {
					creatures.add(sc);
					((Thread) sc).start();
					animalsCount++;
				}
			} catch (ClassCastException ex) {
				JOptionPane.showMessageDialog(aquaFrame,
						"Cannot continue right now.", "Error",
						JOptionPane.INFORMATION_MESSAGE);
			}
		}
	}
	
	public boolean ifContains(Object obj){
		if(creatures.contains(obj))
			return true;
		
		return false;
	}

	/**
	 * This method sets a background to main framework.
	 * 
	 * @param i
	 *            the number of choice depending on the selection from the
	 *            sub-menu.
	 */
	public void setBackgr(int i) {
		if (i == 0) {
			if (img != null)
				img = null;
			this.repaint();
			this.setBackground(Color.WHITE);
		} else if (i == 1) {
			if (img != null)
				img = null;
			this.repaint();
			this.setBackground(Color.cyan);
		} else if (i == 2) {
			try {
				FileDialog fd = new FileDialog(aquaFrame, "Choose a photo:",
						FileDialog.LOAD);
				fd.setVisible(true);

				img = ImageIO.read(new File(fd.getDirectory() + fd.getFile()));

				repaint();

			} catch (IOException e) {
				System.out.println("Cannot load image");
			}
		}
	}

	/**
	 * This static method gets the RGB color codes, relevant to this program,
	 * and returns the color name.
	 * 
	 * @param obj
	 *            Object of Color.
	 * @return the color name.
	 */
	public static String ColorName(Object obj) {
		if (obj instanceof Color) {
			if (((Color) obj).equals(Color.RED))
				return "Red";
			else if (((Color) obj).equals(Color.BLUE))
				return "Blue";
			else if (((Color) obj).equals(Color.CYAN))
				return "Cyan";
			else if (((Color) obj).equals(Color.GREEN))
				return "Green";
			else if (((Color) obj).equals(Color.MAGENTA))
				return "Magenta";
			else {
				return "(" + ((Color) obj).getRed() + ","
						+ ((Color) obj).getGreen() + ","
						+ ((Color) obj).getBlue() + ")";
			}
		}

		return null;
	}

	/**
	 * This method paints the elements in the main framework.
	 */
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		if (img != null) {
			g.drawImage(img, 0, 0, getWidth(), getHeight(), this);
		}

		ITE = creatures.iterator();
		while (ITE.hasNext()) {
			ITE.next().drawCreature(g);
		}

		if (isFood == true) {
			Worm.getInstance().drawWorm(this, g);
		}
	}

	@Override
	public void notify(String msg) {
		if(msg.equals("Hungry"))
		{
		JOptionPane.showMessageDialog(aquaFrame,
				"Please feed us!", "Info",
				JOptionPane.INFORMATION_MESSAGE);
		}
	}
}
