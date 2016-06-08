package aquarium;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
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
 * 
 * @author Shai Hod, ID: 304800402
 *
 */

public class JPanelDecorator extends JPanel{


	private static final long serialVersionUID = 1L;
	private JList<String> objList;
	private AquaPanel ap;
	private Iterator<SeaCreature> ITE;
	private JButton ChangeColor;
	private ArrayList<MarineAnimal> objArr= new ArrayList<MarineAnimal>();
	public Color col;	

	public JPanelDecorator(AquaPanel ap, HashSet<SeaCreature> creatures){
		this.ap=ap;

		JLabel label = new JLabel("Choose an animal you want to change its color, and click on the button ------>");
		DefaultListModel<String> model = new DefaultListModel<String>();
		JPanel component = new JPanel();
			
		
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
		
		ChangeColor = new JButton("Change Color");
		
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
		
		objList.setFont(new Font("DAVID", Font.PLAIN, 16));
		add(objList, BorderLayout.CENTER);

		component.add(label);
		component.add(ChangeColor);
		add(component, BorderLayout.SOUTH);

		setBorder(BorderFactory.createMatteBorder(4, 4, 4, 4, Color.BLACK));
		setVisible(true);
		ap.repaint();
	}	
	
	protected void dispose() {
		ap.remove(this);
		
		AquaPanel.ifPressed = false;
	}
}