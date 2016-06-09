package aquarium;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import swimmable.Swimmable;

/**
 * This class represents a panel that allows to user to change color of specific
 * animal while program running.
 * 
 * @author Shai Hod, ID: 304800402
 * 
 * @see MarineAnimal
 * @see MarineAnimalDecorator
 */

public class JPanelDecorator extends JPanel{

	private static final long serialVersionUID = 1L;
	private JList<String> objList;
	private AquaPanel ap;
	private Iterator<SeaCreature> ITE;
	private JButton ChangeColor;
	private ArrayList<MarineAnimal> objArr = new ArrayList<MarineAnimal>();
	private DefaultListModel<String> model = new DefaultListModel<String>();
	private Color col;

	/**
	 * General Constructor.
	 * 
	 * @param ap
	 *            gets a reference to AquaPanel.
	 * @param creatures
	 *            gets a reference to hashset that holds a reference for every
	 *            creature in the aquarium.
	 */
	public JPanelDecorator(AquaPanel ap, HashSet<SeaCreature> creatures) {
		this.ap = ap;
		
		this.setLayout(new BorderLayout());

		String labelText = "<html><FONT SIZE=+1><FONT COLOR=RED>Choose an animal you want to change its color,</FONT><FONT SIZE=+1> and click on the button <FONT COLOR=BLUE>Change Color</FONT></html>";
		JLabel label = new JLabel(labelText);

		ChangeColor = new JButton("Change Color");
		
		fillList(creatures);
		
		add(label, BorderLayout.NORTH);
		add(objList, BorderLayout.CENTER);
		add(ChangeColor, BorderLayout.SOUTH);

		setBorder(BorderFactory.createMatteBorder(4, 4, 4, 4, Color.BLACK));
		setVisible(true);
		ap.repaint();
		
		ChangeColor.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				if (objList.getSelectedIndex() == -1) {
					
					JOptionPane.showMessageDialog(new JDialog(), "No animal selected");
					
				} else {
					
					int index = objList.getSelectedIndex();

					new JColorChooser();
					col = JColorChooser.showDialog(JPanelDecorator.this,
							"Choose Color", col);
					
					if (col != null) {
						
						MarineAnimalDecorator MAD = new MarineAnimalDecorator(
								objArr.get(index));
						MAD.PaintFish(col);
						
					}
					
					dispose();
				}
			}
		});
	}	
	
	/**
	 * This method fills the list of animals that are in the aquarium.
	 * 
	 * @param creatures
	 *            gets an hashset that holds a reference for every creature in
	 *            the aquarium.
	 */
	private void fillList(HashSet<SeaCreature> creatures) {
		ITE = creatures.iterator();
		while (ITE.hasNext()) {
			try {
				Swimmable cur = (Swimmable) ITE.next();
				
				col = cur.getColorRef();
				
				model.addElement(new String("Animmal Type: "
						+ cur.getAnimalName() + "; Color: " + cur.getColor()));
				
				objArr.add((MarineAnimal)cur);
				
			} catch (ClassCastException ex) {}
		}	
		
		objList = new JList<String>(model);
	}

	/**
	 * This method removes that panel from parent panel - AquaPanel.
	 */
	protected void dispose() {
		ap.remove(this);
		
		AquaPanel.ifPressed = false;
	}
}