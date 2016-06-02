package aquarium;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashSet;
import java.util.Iterator;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;
import javax.swing.LayoutStyle.ComponentPlacement;

import swimmable.*;
import seaPlants.*;
import aquarium.CareTaker;
/**
 * 
 * @author Shai Hod, ID: 304800402
 *
 */

public class SaveDialog extends JDialog{
	
	private static final long serialVersionUID = 1L;
	private JTextPane textType;
	private JTextPane textSize;
	private JTextPane textColor;
	private JTextPane textXPos;
	private JTextPane textYPos;
	private JTextPane textVerSpeed;
	private JTextPane textHorSpeed;
	private JComboBox<SeaCreature> comboBox = new JComboBox<SeaCreature>();
	private AquaPanel ap;
	private HashSet<SeaCreature> creatures;
	private Iterator<SeaCreature> ITE;

	public SaveDialog(AquaPanel ap, HashSet<SeaCreature> creatures){
		this.ap = ap;
		this.creatures = creatures;
		fillCB();
		
		this.setModal(true);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setIconImage(Toolkit.getDefaultToolkit().getImage(
				getClass().getResource("/resources/Icon.png")));
		this.setResizable(false);
		this.setTitle("Save Creature State");
		initialize(); // initializes GUI dialog
		this.setSize(new Dimension(330, 350));
		this.setLocationRelativeTo(ap);
		this.setVisible(true);
		

	}
	
	
	private void fillCB() {
		ITE = creatures.iterator();
		while (ITE.hasNext()) {
			try {
				comboBox.addItem((SeaCreature)ITE.next());
			} catch (ClassCastException ex) {
			}
		}		
	}


	private void initialize() {
		this.setTitle("Save Creature State");
		this.setBounds(100, 100, 512, 284);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Object obj = comboBox.getSelectedItem();
				if(obj instanceof Swimmable){
					
					textType.setText(((Swimmable)obj).getName());
					textSize.setText(Integer.toString(((Swimmable)obj).getSize()));
					textColor.setText(((Swimmable)obj).getColor());
					textXPos.setText(Integer.toString(((Swimmable)obj).get_X_front()));
					textYPos.setText(Integer.toString(((Swimmable)obj).get_Y_front()));
					textVerSpeed.setText(Integer.toString(((Swimmable)obj).getVerSpeed()));
					textHorSpeed.setText(Integer.toString(((Swimmable)obj).getHorSpeed()));
					
				}else if(obj instanceof Immobile){
					
					textType.setText(((Immobile)obj).toString());
					textSize.setText(Integer.toString(((Immobile)obj).getSize()));
					textColor.setText(AquaPanel.ColorName(Color.GREEN));
					textXPos.setText(Integer.toString(((Immobile)obj).getXpos()));
					textYPos.setText(Integer.toString(((Immobile)obj).getYpos()));
					textVerSpeed.setText("0");
					textHorSpeed.setText("0");
					
				}
			}
		});
		
		JButton btnSave = new JButton("Save State");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Object obj = comboBox.getSelectedItem();
				if(obj instanceof Swimmable){
					
					CareTaker.add(Integer.toString((CareTaker.getCount() + 1)), ((Swimmable)obj).saveToMemento());
					
				}else if(obj instanceof Immobile){

					CareTaker.add(Integer.toString((CareTaker.getCount() + 1)), ((Immobile)obj).saveToMemento());					
					
				}
			}
		});
		
		JLabel lblType = new JLabel("Type:");
		
		textType = new JTextPane();
		textType.setEditable(false);
		
		JLabel lblNewLabel = new JLabel("Size:");
		
		JLabel lblNewLabel_1 = new JLabel("Color:");
		
		JLabel lblNewLabel_2 = new JLabel("X Position:");
		
		JLabel lblNewLabel_3 = new JLabel("Y Position:");
		
		JLabel lblNewLabel_4 = new JLabel("Vertical Speed:");
		
		JLabel lblNewLabel_5 = new JLabel("Horizontal Speed:");			
		
		textSize = new JTextPane();
		textSize.setEditable(false);
		
		textColor = new JTextPane();
		textColor.setEditable(false);
		
		textXPos = new JTextPane();
		textXPos.setEditable(false);
		
		textYPos = new JTextPane();
		textYPos.setEditable(false);
		
		textVerSpeed = new JTextPane();
		textVerSpeed.setEditable(false);
		
		textHorSpeed = new JTextPane();
		textHorSpeed.setEditable(false);
		
		// rejoin GroupLayout
		GroupLayout groupLayout = new GroupLayout(this.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addComponent(comboBox, 0, 315, Short.MAX_VALUE)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblNewLabel_5)
						.addComponent(lblType)
						.addComponent(lblNewLabel)
						.addComponent(lblNewLabel_1)
						.addComponent(lblNewLabel_2)
						.addComponent(lblNewLabel_3)
						.addComponent(lblNewLabel_4))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
						.addComponent(textHorSpeed)
						.addComponent(textType, GroupLayout.DEFAULT_SIZE, 54, Short.MAX_VALUE)
						.addComponent(textSize)
						.addComponent(textColor)
						.addComponent(textXPos)
						.addComponent(textYPos)
						.addComponent(textVerSpeed))
					.addPreferredGap(ComponentPlacement.RELATED, 34, Short.MAX_VALUE)
					.addComponent(btnSave)
					.addGap(29))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addGap(12)
									.addComponent(lblType)
									.addGap(24)
									.addComponent(lblNewLabel)
									.addGap(18)
									.addComponent(lblNewLabel_1)
									.addGap(24)
									.addComponent(lblNewLabel_2)
									.addGap(24)
									.addComponent(lblNewLabel_3)
									.addGap(24)
									.addComponent(lblNewLabel_4))
								.addGroup(groupLayout.createSequentialGroup()
									.addGap(6)
									.addComponent(textType, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
									.addGap(18)
									.addComponent(textSize, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
									.addGap(12)
									.addComponent(textColor, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
									.addGap(18)
									.addComponent(textXPos, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
									.addGap(18)
									.addComponent(textYPos, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
									.addGap(18)
									.addComponent(textVerSpeed, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
							.addGap(18)
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
								.addComponent(lblNewLabel_5)
								.addComponent(textHorSpeed, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(103)
							.addComponent(btnSave, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(212, Short.MAX_VALUE))
		);
		groupLayout.linkSize(SwingConstants.HORIZONTAL, new Component[] {textType, textSize, textColor, textXPos, textYPos, textVerSpeed});
		// endrejoin GroupLayout
		
		this.getContentPane().setLayout(groupLayout);
	}
}
