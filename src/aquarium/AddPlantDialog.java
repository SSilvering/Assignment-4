package aquarium;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import seaPlants.Immobile;
import seaPlants.PlantFactory;

/**
 * This class represents the window that adds new plants to the aquarium.
 * 
 * @author Shai Hod, ID: 304800402
 */

public class AddPlantDialog extends JDialog {

	private static final long serialVersionUID = 1L;
	private AquaPanel aquaPanel;
	private JTextField plantSizeField;
	private JTextField xposField;
	private JTextField yposField;
	private JPanel addAnimal;
	private String Type;
	private Integer size;
	private Integer xPos;
	private Integer yPos;

	/**
	 * Constructor initializes the dialog for adding new plant to the aquarium.
	 * Also keeps a reference to AquaPanel.
	 * 
	 * @param aquaPanel
	 *            Reference to AquaPanel.
	 */
	public AddPlantDialog(AquaPanel aquaPanel) {
		this.aquaPanel = aquaPanel;
		this.setModal(true);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setIconImage(Toolkit.getDefaultToolkit().getImage(
				getClass().getResource("/resources/Icon.png")));
		this.setResizable(false);
		this.setTitle("Add Plant");
		initialize(); // initializes GUI dialog
		this.setSize(new Dimension(512, 284));
		this.setLocationRelativeTo(aquaPanel);
		this.setVisible(true);
	}

	/**
	 * This method initialize GUI for add plant dialog. The constructor engage
	 * this method.
	 */
	private void initialize() {
		JLabel lblPlantType = new JLabel("Plant Type:");
		lblPlantType.setToolTipText("Choose the type of the animal.");

		JComboBox<String> plantComboBox = new JComboBox<String>();
		plantComboBox.setToolTipText("Choose the type of the animal.");
		plantComboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (plantComboBox.getSelectedItem() == "Zostera")
					Type = "Zostera";
				else if (plantComboBox.getSelectedItem() == "Laminaria")
					Type = "Laminaria";
			}
		});
		plantComboBox.setModel(new DefaultComboBoxModel<String>(new String[] {"Zostera", "Laminaria"}));
		plantComboBox.setSelectedIndex(0);
		plantComboBox.setMaximumRowCount(2);

		// /////////////////////////////////////////////////////////////////

		JLabel lblPlantSize = new JLabel("Plant Size:");
		lblPlantSize
				.setToolTipText("Choose plant size between 20px to 320px.");

		JSlider sizeSlider = new JSlider();
		sizeSlider.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				size = sizeSlider.getValue();
				plantSizeField.setText(size.toString());
			}
		});

		plantSizeField = new JTextField();
		plantSizeField
				.setToolTipText("Enter plant size between 20px to 320px.");
		plantSizeField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String typed = plantSizeField.getText();
				sizeSlider.setValue(20);
				if (!typed.matches("^[0-9]+$") || typed.length() > 3) {
					JOptionPane.showMessageDialog(addAnimal,
							"Please enter a number between from 20 to 320",
							"Error", JOptionPane.INFORMATION_MESSAGE);
					plantSizeField.setText("20");
					return;
				}

				int num = Integer.parseInt(typed);
				if (num >= 20 && num <= 320)
					sizeSlider.setValue(Integer.parseInt(typed));
				else {
					JOptionPane.showMessageDialog(addAnimal,
							"Please enter a number between from 20 to 320",
							"Error", JOptionPane.INFORMATION_MESSAGE);
					plantSizeField.setText("20");
					return;
				}
			}
		});
		plantSizeField.setHorizontalAlignment(SwingConstants.CENTER);
		plantSizeField.setColumns(3);
		sizeSlider.setMinorTickSpacing(1);
		sizeSlider.setMajorTickSpacing(100);
		sizeSlider.setValue(20);
		sizeSlider.setToolTipText("");
		sizeSlider.setSnapToTicks(true);
		sizeSlider.setPaintTicks(true);
		sizeSlider.setPaintLabels(true);
		sizeSlider.setMinimum(20);
		sizeSlider.setMaximum(320);

		// /////////////////////////////////////////////////////////////////

		JLabel lblXpos = new JLabel("X-Position:");
		lblXpos
				.setToolTipText("Choose x-position of plant in the aquarium.");

		JSlider xPosSlider = new JSlider();
		xPosSlider.setMajorTickSpacing(149);
		xPosSlider.setMinorTickSpacing(1);
		xPosSlider.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				xPos = xPosSlider.getValue();
				xposField.setText(xPos.toString());
			}
		});

		xposField = new JTextField();
		xposField
				.setToolTipText("Enter x-position of plant in the aquarium.");
		xposField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String typed = xposField.getText();
				xPosSlider.setValue(1);
				if (!typed.matches("^[0-9]+$") || typed.length() > 3) {
					JOptionPane.showMessageDialog(addAnimal,
							"Please enter a number between from 40 to 745",
							"Error", JOptionPane.INFORMATION_MESSAGE);
					xposField.setText("1");
					return;
				}

				int num = Integer.parseInt(typed);
				if (num >= 40 && num <= 745)
					xPosSlider.setValue(Integer.parseInt(typed));
				else {
					JOptionPane.showMessageDialog(addAnimal,
							"Please enter a number between from 40 to 745",
							"Error", JOptionPane.INFORMATION_MESSAGE);
					xposField.setText("1");
					return;
				}
			}
		});
		xposField.setHorizontalAlignment(SwingConstants.CENTER);
		xposField.setColumns(3);
		xPosSlider.setValue(1);
		xPosSlider.setSnapToTicks(true);
		xPosSlider.setPaintLabels(true);
		xPosSlider.setPaintTicks(true);
		xPosSlider.setMinimum(40);
		xPosSlider.setMaximum(745);

		// /////////////////////////////////////////////////////////////////

		JLabel lblYpos = new JLabel("Y-Position:");
		lblYpos
				.setToolTipText("Choose y-position of plant in the aquarium.");

		JSlider yPosSlider = new JSlider();
		yPosSlider.setValue(1);
		yPosSlider.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				yPos = yPosSlider.getValue();
				yposField.setText(yPos.toString());
			}
		});

		yposField = new JTextField();
		yposField
				.setToolTipText("Enter y-position of plant in the aquarium.");
		yposField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String typed = yposField.getText();
				yPosSlider.setValue(1);
				if (!typed.matches("^[0-9]+$") || typed.length() > 3) {
					JOptionPane.showMessageDialog(addAnimal,
							"Please enter a number between from 20 to 600",
							"Error", JOptionPane.INFORMATION_MESSAGE);
					yposField.setText("1");
					return;
				}

				int num = Integer.parseInt(typed);
				if (num >= 20 && num <= 600)
					yPosSlider.setValue(Integer.parseInt(typed));
				else {
					JOptionPane.showMessageDialog(addAnimal,
							"Please enter a number between from 20 to 600",
							"Error", JOptionPane.INFORMATION_MESSAGE);
					yposField.setText("1");
					return;
				}
			}
		});
		yposField.setHorizontalAlignment(SwingConstants.CENTER);
		yposField.setColumns(3);
		yPosSlider.setMajorTickSpacing(149);
		yPosSlider.setMinorTickSpacing(1);
		yPosSlider.setSnapToTicks(true);
		yPosSlider.setPaintTicks(true);
		yPosSlider.setPaintLabels(true);
		yPosSlider.setMinimum(20);
		yPosSlider.setMaximum(600);

		// /////////////////////////////////////////////////////////////////

		JButton btnAdd = new JButton("OK");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (e.getSource().equals(btnAdd)) {
					createPlant(Type,size, xPos, yPos);
					System.out.println("Create Plant");
					dispose();
				}
			}
		});

		// /////////////////////////////////////////////////////////////////

		GridBagConstraints gbc_addButton = new GridBagConstraints();
		gbc_addButton.insets = new Insets(0, 0, 0, 5);
		gbc_addButton.gridx = 2;
		gbc_addButton.gridy = 8;

		// /////////////////////////////////////////////////////////////////

		// rejoin GroupLayout
		GroupLayout groupLayout = new GroupLayout(this.getContentPane());
		groupLayout
				.setHorizontalGroup(groupLayout
						.createParallelGroup(Alignment.LEADING)
						.addGroup(
								groupLayout
										.createSequentialGroup()
										.addGap(83)
										.addGroup(
												groupLayout
														.createParallelGroup(
																Alignment.TRAILING)
														.addGroup(
																groupLayout
																		.createSequentialGroup()
																		.addComponent(
																				lblXpos)
																		.addGap(18)
																		.addComponent(
																				xposField,
																				GroupLayout.PREFERRED_SIZE,
																				GroupLayout.DEFAULT_SIZE,
																				GroupLayout.PREFERRED_SIZE))
														.addGroup(
																groupLayout
																		.createSequentialGroup()
																		.addComponent(
																				lblYpos)
																		.addGap(18)
																		.addComponent(
																				yposField,
																				GroupLayout.PREFERRED_SIZE,
																				GroupLayout.DEFAULT_SIZE,
																				GroupLayout.PREFERRED_SIZE))
														.addGroup(
																groupLayout
																		.createParallelGroup(
																				Alignment.LEADING)
																		.addComponent(
																				lblPlantType)
																		.addGroup(
																				groupLayout
																						.createSequentialGroup()
																						.addComponent(
																								lblPlantSize)
																						.addGap(18)
																						.addComponent(
																								plantSizeField,
																								GroupLayout.PREFERRED_SIZE,
																								GroupLayout.DEFAULT_SIZE,
																								GroupLayout.PREFERRED_SIZE))))
										.addGroup(
												groupLayout
														.createParallelGroup(
																Alignment.LEADING)
														.addGroup(
																groupLayout
																		.createSequentialGroup()
																		.addGap(19)
																		.addGroup(
																				groupLayout
																						.createParallelGroup(
																								Alignment.LEADING)
																						.addComponent(
																								xPosSlider,
																								GroupLayout.PREFERRED_SIZE,
																								GroupLayout.DEFAULT_SIZE,
																								GroupLayout.PREFERRED_SIZE)
																						.addComponent(
																								sizeSlider,
																								GroupLayout.PREFERRED_SIZE,
																								GroupLayout.DEFAULT_SIZE,
																								GroupLayout.PREFERRED_SIZE)
																						.addComponent(
																								yPosSlider,
																								GroupLayout.PREFERRED_SIZE,
																								GroupLayout.DEFAULT_SIZE,
																								GroupLayout.PREFERRED_SIZE)))
														.addComponent(
																plantComboBox,
																GroupLayout.PREFERRED_SIZE,
																79,
																GroupLayout.PREFERRED_SIZE)
														.addComponent(btnAdd))
										.addContainerGap(95, Short.MAX_VALUE)));
		groupLayout
				.setVerticalGroup(groupLayout
						.createParallelGroup(Alignment.LEADING)
						.addGroup(
								groupLayout
										.createSequentialGroup()
										.addGap(36)
										.addGroup(
												groupLayout
														.createParallelGroup(
																Alignment.TRAILING)
														.addComponent(
																lblPlantType)
														.addComponent(
																plantComboBox,
																GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE,
																GroupLayout.PREFERRED_SIZE))
										.addPreferredGap(
												ComponentPlacement.RELATED)
										.addGroup(
												groupLayout
														.createParallelGroup(
																Alignment.LEADING)
														.addGroup(
																groupLayout
																		.createSequentialGroup()
																		.addGap(13)
																		.addGroup(
																				groupLayout
																						.createParallelGroup(
																								Alignment.BASELINE)
																						.addComponent(
																								lblPlantSize)
																						.addComponent(
																								plantSizeField,
																								GroupLayout.PREFERRED_SIZE,
																								GroupLayout.DEFAULT_SIZE,
																								GroupLayout.PREFERRED_SIZE)))
														.addComponent(
																sizeSlider,
																GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE,
																GroupLayout.PREFERRED_SIZE))
										.addPreferredGap(
												ComponentPlacement.RELATED)
										.addGroup(
												groupLayout
														.createParallelGroup(
																Alignment.TRAILING)
														.addComponent(
																xPosSlider,
																GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE,
																GroupLayout.PREFERRED_SIZE)
														.addGroup(
																groupLayout
																		.createSequentialGroup()
																		.addGroup(
																				groupLayout
																						.createParallelGroup(
																								Alignment.BASELINE)
																						.addComponent(
																								xposField,
																								GroupLayout.PREFERRED_SIZE,
																								GroupLayout.DEFAULT_SIZE,
																								GroupLayout.PREFERRED_SIZE)
																						.addComponent(
																								lblXpos))
																		.addGap(18)))
										.addPreferredGap(
												ComponentPlacement.RELATED)
										.addGroup(
												groupLayout
														.createParallelGroup(
																Alignment.LEADING)
														.addComponent(
																yPosSlider,
																GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE,
																GroupLayout.PREFERRED_SIZE)
														.addGroup(
																groupLayout
																		.createParallelGroup(
																				Alignment.BASELINE)
																		.addComponent(
																				yposField,
																				GroupLayout.PREFERRED_SIZE,
																				GroupLayout.DEFAULT_SIZE,
																				GroupLayout.PREFERRED_SIZE)
																		.addComponent(
																				lblYpos)))
										.addGap(3).addComponent(btnAdd)
										.addGap(23)));

		this.getContentPane().setLayout(groupLayout);
		// endrejoin GroupLayout		
	}
	
	/**
	 * This method creates a new instance for specific plant. First, it checks
	 * which plant to create, and accordingly creates the instance.
	 * 
	 * @param type
	 *            Name of the type of the new plant.
	 * @param size
	 *            Size for the new plant.
	 * @param xPos
	 *            X-Position in the aquarium to new plant.
	 * @param yPos
	 *            Y-Position in the aquarium to new plant.
	 */
	private void createPlant(String type, Integer size, Integer xPos,
			Integer yPos) {
		if (Type == "Zostera") {
			AbstractSeaFactory asf = new PlantFactory(xPos, yPos, size);
			aquaPanel.addCreature((Immobile) asf.produceSeaCreature("Zostera"));
		} else if (Type == "Laminaria") {
			AbstractSeaFactory asf = new PlantFactory(xPos, yPos, size);
			aquaPanel.addCreature((Immobile) asf.produceSeaCreature("Laminaria"));
		}
	}
}
