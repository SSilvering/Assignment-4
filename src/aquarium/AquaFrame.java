package aquarium;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

/**
 * This class represents the main framework of the aquarium.
 * 
 * @author Shai Hod, ID: 304800402
 *
 */

public class AquaFrame extends JFrame implements ActionListener {
	
	private static final long serialVersionUID = 1L;
	private AquaPanel panel;
	private String[] names = { "Exit", "Image", "Blue", "None", "Save Object State", "Restore Object State", "Help" };
	private JMenu m1, m2, m3, m4;
	private JMenuItem[] mi;
	private JMenuBar mb;

	public static void main(String[] args) {
		AquaFrame aqua = new AquaFrame();
		aqua.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		aqua.setVisible(true);
	}

	public AquaFrame() {
		super("Shai's Aquarium");
		super.setIconImage(Toolkit.getDefaultToolkit().getImage(
				getClass().getResource("/resources/Icon.png")));
		super.setLocationByPlatform(true);
		panel = new AquaPanel(this);
		add(panel);
		panel.setVisible(true);

		mb = new JMenuBar();
		m1 = new JMenu("File");
		m2 = new JMenu("Background");
		m3 = new JMenu("Memento");
		m4 = new JMenu("Help");
		mi = new JMenuItem[names.length];

		/**
		 * This method determines the names of buttons sub-menus of the main
		 * menu bar and adds a listener for each button.
		 */
		for (int i = 0; i < names.length; i++) {
			mi[i] = new JMenuItem(names[i]);
			mi[i].addActionListener(this);
		}

		/**
		 * Sets the buttons sub-menus of the main menu bar, and adds the bar to
		 * the main framework.
		 */
		// adds to File button
		m1.add(mi[0]);

		// adds to Background button
		m2.add(mi[1]);
		m2.addSeparator();
		m2.add(mi[2]);
		m2.addSeparator();
		m2.add(mi[3]);
		
		// adds to Memento button
		m3.add(mi[4]);
		m3.addSeparator();
		m3.add(mi[5]);

		// adds to Help button
		m4.add(mi[6]);

		// add the general buttons to main bar
		mb.add(m1);
		mb.add(m2);
		mb.add(m3);
		mb.add(m4);

		// adds the new bar to main framework
		setJMenuBar(mb);
	}

	/**
	 * Sets actions to buttons of the menu bar.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == mi[0])
			destroy();
		else if (e.getSource() == mi[1])
			panel.setBackgr(2);
		else if (e.getSource() == mi[2])
			panel.setBackgr(1);
		else if (e.getSource() == mi[3])
			panel.setBackgr(0);
		else if(e.getSource() == mi[4])
			panel.saveDialog();
		else if(e.getSource() == mi[5])
			destroy(); // TODO change it
		else if (e.getSource() == mi[6])
			printHelp();
	}

	/**
	 * The method sets the action of exit from the program.
	 */
	public void destroy() {
		System.exit(0);
	}

	/**
	 * The method sets the message that will print when Help button is pressed.
	 */
	public void printHelp() {
		JOptionPane.showMessageDialog(this, "Home Work 4\nDesign Patterns (DP)");
	}
}