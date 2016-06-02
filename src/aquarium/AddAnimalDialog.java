package aquarium;

import java.awt.Color;
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
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import swimmable.AnimalFactory;
import swimmable.Swimmable;

/**
 * This class represents the window that adds new animals to the aquarium.
 * 
 * @author Shai Hod, ID: 304800402
 */

public class AddAnimalDialog extends JDialog {

	private static final long serialVersionUID = 1L;
	private AquaPanel aquaPanel;
	private JTextField animalSizeField;
	private JTextField verSpeedField;
	private JTextField horSpeedField;
	private String Type;
	private Color col;
	private Integer size;
	private Integer verSpeed;
	private Integer horSpeed;
	private JTextField feedField;
	private int feedFreq;

	/**
	 * Constructor initializes the dialog for adding new animal to the aquarium.
	 * Also keeps a reference to AquaPanel.
	 * 
	 * @param aquaPanel
	 *            Reference to AquaPanel.
	 */
	public AddAnimalDialog(AquaPanel aquaPanel) {
		this.aquaPanel = aquaPanel;
		this.setModal(true);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setIconImage(Toolkit.getDefaultToolkit().getImage(
				getClass().getResource("/resources/Icon.png")));
		this.setResizable(false);
		this.setTitle("Add Animal");
		initialize(); // initializes GUI dialog
		this.setSize(new Dimension(512, 284));
		this.setLocationRelativeTo(aquaPanel);
		this.setVisible(true);
	}

	/**
	 * This method initialize GUI for add animal dialog. The constructor engage
	 * this method.
	 */
	private void initialize() {
		JLabel lblAnimalType = new JLabel("Animal Type:");
		lblAnimalType.setToolTipText("Choose the type of the animal.");

		JComboBox<String> animalComboBox = new JComboBox<String>();
		animalComboBox.setToolTipText("Choose the type of the animal.");
		animalComboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (animalComboBox.getSelectedItem() == "Fish")
					Type = "Fish";
				else if (animalComboBox.getSelectedItem() == "Jellyfish")
					Type = "Jellyfish";
			}
		});
		animalComboBox.setModel(new DefaultComboBoxModel<String>(new String[] {
				"Fish", "Jellyfish"}));
		animalComboBox.setSelectedIndex(0);
		animalComboBox.setMaximumRowCount(2);

		// /////////////////////////////////////////////////////////////////

		JLabel lblAnimalSize = new JLabel("Animal Size:");
		lblAnimalSize
				.setToolTipText("Choose animal size between 20px to 320px.");

		JSlider sizeSlider = new JSlider();
		sizeSlider.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				size = sizeSlider.getValue();
				animalSizeField.setText(size.toString());
			}
		});

		animalSizeField = new JTextField();
		animalSizeField
				.setToolTipText("Enter animal size between 20px to 320px.");
		animalSizeField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String typed = animalSizeField.getText();
				sizeSlider.setValue(20);
				if (!typed.matches("^[0-9]+$") || typed.length() > 3) {
					JOptionPane.showMessageDialog(new JDialog(),
							"Please enter a number between from 20 to 320",
							"Error", JOptionPane.INFORMATION_MESSAGE);
					animalSizeField.setText("20");
					return;
				}

				int num = Integer.parseInt(typed);
				if (num >= 20 && num <= 320)
					sizeSlider.setValue(Integer.parseInt(typed));
				else {
					JOptionPane.showMessageDialog(new JDialog(),
							"Please enter a number between from 20 to 320",
							"Error", JOptionPane.INFORMATION_MESSAGE);
					animalSizeField.setText("20");
					return;
				}
			}
		});
		animalSizeField.setHorizontalAlignment(SwingConstants.CENTER);
		animalSizeField.setColumns(3);
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

		JLabel lblVerticalSpeed = new JLabel("Vertical Speed:");
		lblVerticalSpeed
				.setToolTipText("Choose the vertical speed of animal between 1 [px/sec] to 10 [px/sec].");

		JSlider verSpeedSlider = new JSlider();
		verSpeedSlider.setToolTipText("");
		verSpeedSlider.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				verSpeed = verSpeedSlider.getValue();
				verSpeedField.setText(verSpeed.toString());
			}
		});

		verSpeedField = new JTextField();
		verSpeedField
				.setToolTipText("Enter the vertical speed of animal between 1 [px/sec] to 10 [px/sec].");
		verSpeedField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String typed = verSpeedField.getText();
				verSpeedSlider.setValue(1);
				if (!typed.matches("^[0-9]+$") || typed.length() > 2) {
					JOptionPane.showMessageDialog(new JDialog(),
							"Please enter a number between from 1 to 10",
							"Error", JOptionPane.INFORMATION_MESSAGE);
					verSpeedField.setText("1");
					return;
				}

				int num = Integer.parseInt(typed);
				if (num >= 1 && num <= 10)
					verSpeedSlider.setValue(Integer.parseInt(typed));
				else {
					JOptionPane.showMessageDialog(new JDialog(),
							"Please enter a number between from 1 to 10",
							"Error", JOptionPane.INFORMATION_MESSAGE);
					verSpeedField.setText("1");
					return;
				}
			}
		});
		verSpeedField.setHorizontalAlignment(SwingConstants.CENTER);
		verSpeedField.setColumns(3);
		verSpeedSlider.setMajorTickSpacing(1);
		verSpeedSlider.setValue(1);
		verSpeedSlider.setSnapToTicks(true);
		verSpeedSlider.setPaintLabels(true);
		verSpeedSlider.setPaintTicks(true);
		verSpeedSlider.setMinimum(1);
		verSpeedSlider.setMaximum(10);

		// /////////////////////////////////////////////////////////////////

		JLabel lblHorizontalSpeed = new JLabel("Horizontal Speed:");
		lblHorizontalSpeed
				.setToolTipText("Choose the horizontal speed of animal between 1 [px/sec] to 10 [px/sec].");

		JSlider horSpeedSlider = new JSlider();
		horSpeedSlider.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				horSpeed = horSpeedSlider.getValue();
				horSpeedField.setText(horSpeed.toString());
			}
		});

		horSpeedField = new JTextField();
		horSpeedField
				.setToolTipText("Enter the horizontal speed of animal between 1 [px/sec] to 10 [px/sec].");
		horSpeedField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String typed = horSpeedField.getText();
				horSpeedSlider.setValue(1);
				if (!typed.matches("^[0-9]+$") || typed.length() > 2) {
					JOptionPane.showMessageDialog(new JDialog(),
							"Please enter a number between from 1 to 10",
							"Error", JOptionPane.INFORMATION_MESSAGE);
					horSpeedField.setText("1");
					return;
				}

				int num = Integer.parseInt(typed);
				if (num >= 1 && num <= 10)
					horSpeedSlider.setValue(Integer.parseInt(typed));
				else {
					JOptionPane.showMessageDialog(new JDialog(),
							"Please enter a number between from 1 to 10",
							"Error", JOptionPane.INFORMATION_MESSAGE);
					horSpeedField.setText("1");
					return;
				}
			}
		});
		horSpeedField.setHorizontalAlignment(SwingConstants.CENTER);
		horSpeedField.setColumns(3);
		horSpeedSlider.setMajorTickSpacing(1);
		horSpeedSlider.setValue(1);
		horSpeedSlider.setSnapToTicks(true);
		horSpeedSlider.setPaintTicks(true);
		horSpeedSlider.setPaintLabels(true);
		horSpeedSlider.setMinimum(1);
		horSpeedSlider.setMaximum(10);

		// /////////////////////////////////////////////////////////////////

		JLabel lblAnimalColor = new JLabel("Animal Color:");
		lblAnimalColor.setToolTipText("Choose the color of the animal.");

		JComboBox<String> animalColorcomboBox = new JComboBox<String>();
		animalColorcomboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (animalColorcomboBox.getSelectedItem() == "Red")
					col = Color.RED;
				else if (animalColorcomboBox.getSelectedItem() == "Blue")
					col = Color.BLUE;
				else if (animalColorcomboBox.getSelectedItem() == "Cyan")
					col = Color.CYAN;
				else if (animalColorcomboBox.getSelectedItem() == "Green")
					col = Color.GREEN;
				else if (animalColorcomboBox.getSelectedItem() == "Magenta")
					col = Color.MAGENTA;
			}
		});
		animalColorcomboBox.setToolTipText("Choose the color of the animal.");
		animalColorcomboBox.setMaximumRowCount(5);
		animalColorcomboBox.setModel(new DefaultComboBoxModel<String>(
				new String[] { "Red", "Blue", "Cyan", "Green", "Magenta" }));
		animalColorcomboBox.setSelectedIndex(0);

		// /////////////////////////////////////////////////////////////////

		JButton btnAdd = new JButton("Add");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (e.getSource().equals(btnAdd)) {
					if (feedFreq == 0) {
						feedFreq = 5; // The default value of the frequency in
										// case the user forgot enter.
					}

					AbstractSeaFactory asf = new AnimalFactory(aquaPanel, col,
							horSpeed, verSpeed, size, feedFreq);

					if (Type == "Fish") {
						aquaPanel.addCreature((Swimmable) asf
								.produceSeaCreature("Fish"));
					} else if (Type == "Jellyfish") {
						aquaPanel.addCreature((Swimmable) asf
								.produceSeaCreature("Jellyfish"));
					}

					System.out.println("Create Animal");
					dispose();
				}
			}
		});

		// /////////////////////////////////////////////////////////////////

		GridBagConstraints gbc_addButton = new GridBagConstraints();
		gbc_addButton.insets = new Insets(0, 0, 0, 5);
		gbc_addButton.gridx = 2;
		gbc_addButton.gridy = 8;
		
		JLabel lblFeedingFrequency = new JLabel("Feeding Freq:");
		lblFeedingFrequency.setToolTipText("Enter the feeding frequency of the animal.");
		
		feedField = new JTextField();
		feedField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				feedFreq = Integer.parseUnsignedInt(feedField.getText());
			}
		});
		feedField.setToolTipText("Enter the feeding frequency of the animal.");
		feedField.setHorizontalAlignment(SwingConstants.CENTER);
		feedField.setColumns(3);

		// /////////////////////////////////////////////////////////////////

		// rejoin GroupLayout
		GroupLayout groupLayout = new GroupLayout(this.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(60)
					.addComponent(lblAnimalType)
					.addGap(28)
					.addComponent(animalComboBox, GroupLayout.PREFERRED_SIZE, 79, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(267, Short.MAX_VALUE))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(66)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblFeedingFrequency, 0, 0, Short.MAX_VALUE)
							.addGap(345))
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(lblAnimalColor)
									.addGap(40)
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addComponent(animalColorcomboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addGroup(groupLayout.createSequentialGroup()
											.addGap(10)
											.addComponent(feedField, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)))
									.addGap(77)
									.addComponent(btnAdd, GroupLayout.PREFERRED_SIZE, 64, GroupLayout.PREFERRED_SIZE))
								.addGroup(groupLayout.createSequentialGroup()
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addGroup(groupLayout.createSequentialGroup()
											.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
												.addGroup(groupLayout.createSequentialGroup()
													.addComponent(lblAnimalSize)
													.addGap(46))
												.addGroup(groupLayout.createSequentialGroup()
													.addComponent(lblHorizontalSpeed)
													.addGap(18)))
											.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
												.addComponent(verSpeedField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
												.addComponent(animalSizeField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
												.addComponent(horSpeedField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
										.addComponent(lblVerticalSpeed))
									.addGap(47)
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addComponent(horSpeedSlider, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addComponent(verSpeedSlider, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addComponent(sizeSlider, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))))
							.addContainerGap(50, Short.MAX_VALUE))))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(27)
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
								.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
									.addComponent(animalSizeField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
									.addComponent(lblAnimalSize))
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(sizeSlider, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)))
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addGap(20)
									.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
										.addComponent(lblVerticalSpeed)
										.addComponent(verSpeedField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
								.addGroup(groupLayout.createSequentialGroup()
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(verSpeedSlider, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addGap(9)
									.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
										.addComponent(lblHorizontalSpeed)
										.addComponent(horSpeedField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
								.addGroup(groupLayout.createSequentialGroup()
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(horSpeedSlider, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(3)
							.addComponent(lblAnimalType))
						.addComponent(animalComboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(11)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(animalColorcomboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblAnimalColor))
							.addGap(9)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblFeedingFrequency)
								.addComponent(feedField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
						.addGroup(groupLayout.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(btnAdd, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(15, Short.MAX_VALUE))
		);

		this.getContentPane().setLayout(groupLayout);
		// endrejoin GroupLayout
	}
}
