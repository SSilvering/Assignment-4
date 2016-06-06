package aquarium;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;

import seaPlants.Immobile;
import swimmable.Swimmable;

public class RestoreDialog extends JDialog{

	private static final long serialVersionUID = 1L;
	private JTextPane textType;
	private JTextPane textSize;
	private JTextPane textColor;
	private JTextPane textXPos;
	private JTextPane textYPos;
	private JTextPane textVerSpeed;
	private JTextPane textHorSpeed;
	private JComboBox<Memento> comboBox = new JComboBox<Memento>();
	private Object obj;
	private ArrayList<Memento> arr;
	private AquaPanel ap;

	public RestoreDialog(AquaPanel ap){
		this.ap = ap;
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
	
	private void fillCB(){
		arr = Caretaker.toList();
		
		for (int i = 0; i < arr.size(); i++){
			    comboBox.addItem(arr.get(i));
		}
		
		comboBox.setSelectedIndex(-1);
	}
	
	private void dispValues(Object obj){
		if(obj instanceof Memento){
			
			if (((Memento) obj).getVerSpeed() != 0)
				textType.setText(((Memento) obj).getAnimal().getAnimalName());
			else
				textType.setText(((Memento) obj).getPlant().toString());
			
			textSize.setText(Integer.toString(((Memento)obj).getSize()));
			textColor.setText(AquaPanel.ColorName(((Memento)obj).getColor()));
			textXPos.setText(Integer.toString(((Memento)obj).getXpos()));
			textYPos.setText(Integer.toString(((Memento)obj).getYpos()));
			textVerSpeed.setText(Integer.toString(((Memento)obj).getVerSpeed()));
			textHorSpeed.setText(Integer.toString(((Memento)obj).getHorSpeed()));		
		}
	}

	private void initialize() {
		this.setTitle("Restore Creature State");
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				obj = comboBox.getSelectedItem();
				dispValues(obj);				
			}
		});
		
		JButton btnSave = new JButton(
				"<html><div style='text-align: center;'>Restore<br />State</html>");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(obj instanceof Memento){
					if(((Memento)obj).getVerSpeed() != 0){
						Swimmable animal = ((Memento)obj).getAnimal();
						animal.setHorSpeed(((Memento)obj).getHorSpeed());
						animal.setVerSpeed(((Memento)obj).getVerSpeed());
						animal.set_X_front(((Memento)obj).getXpos());
						animal.set_Y_front(((Memento)obj).getYpos());
						animal.setColor(((Memento)obj).getColor());
						
						ap.addCreature(animal); //TODO problem need to be solved
					}else{
						Immobile plant = ((Memento)obj).getPlant();
						plant.setXpos(((Memento)obj).getXpos());
						plant.setYPos(((Memento)obj).getYpos());
						plant.setSize(((Memento)obj).getSize());
						ap.addCreature(plant);
					}
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