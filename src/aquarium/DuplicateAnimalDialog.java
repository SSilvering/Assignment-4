package aquarium;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashSet;
import java.util.Iterator;

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
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import swimmable.Fish;
import swimmable.Jellyfish;
import swimmable.Swimmable;

/**
 * This class represents a pop-up dialog for duplicating a specific animal that
 * already in the aquarium.
 * 
 * @author Shai Hod, ID: 304800402
 * 
 * @see AnimalCache
 */

public class DuplicateAnimalDialog extends JDialog {

	private static final long serialVersionUID = 1L;
	private AquaPanel ap;
	private JPanel dupAnimal;
	private JTextField tfSize;
	private JTextField tfVerSpeed;
	private JTextField tfHorSpeed;
	private Integer size;
	private Integer horSpeed;
	private Integer verSpeed;
	private Color col;
	private Iterator<SeaCreature> ITE;
	private HashSet<SeaCreature> creatures;
	private boolean fishPress = false;
	private boolean jellyfishPress = false;
	private Swimmable animal;
	private JSlider sizeSlider;
	private JSlider verSpeedSlider;
	private JSlider horSpeedSlider;
	private JComboBox<Swimmable> comboBox;
	private JComboBox<String> colorComboBox;


	public DuplicateAnimalDialog(AquaPanel ap, HashSet<SeaCreature> creatures) {
		this.ap = ap;
		this.creatures = creatures;

		this.setModal(true);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setIconImage(Toolkit.getDefaultToolkit().getImage(
				getClass().getResource("/resources/Icon.png")));
		this.setResizable(false);
		this.setTitle("Duplicate Animal");
		initialize(); // initializes GUI dialog
		this.setSize(new Dimension(512, 284));
		this.setLocationRelativeTo(ap);
		this.setVisible(true);
	}

	private void initialize() {
		this.setResizable(false);
		this.setBounds(100, 100, 424, 296);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);

		comboBox = new JComboBox<Swimmable>();
		comboBox.setToolTipText("Choose a specific animal to duplicate.");
		comboBox.setMaximumRowCount(5);
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setValues();
			}
		});

		JLabel lblSize = new JLabel("Size:");

		JLabel lblVerSpeed = new JLabel("Vertical Speed:");
		lblVerSpeed
				.setToolTipText("Enter new vertical speed between 1px/sec to 10px/sec.");

		JLabel lblHorSpeed = new JLabel("Horizontal Speed:");

		sizeSlider = new JSlider();
		sizeSlider.setToolTipText("Choose a new size between 20px to 320px.");
		tfSize = new JTextField();
		tfSize.setToolTipText("Enter a new size between 20px to 320px.");
		tfSize.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String typed = tfSize.getText();
				sizeSlider.setValue(20);
				if (!typed.matches("^[0-9]+$") || typed.length() > 3) {
					JOptionPane.showMessageDialog(dupAnimal,
							"Please enter a number between from 20 to 320",
							"Error", JOptionPane.INFORMATION_MESSAGE);
					tfSize.setText("20");
					return;
				}

				int num = Integer.parseInt(typed);
				if (num >= 20 && num <= 320)
					sizeSlider.setValue(Integer.parseInt(typed));
				else {
					JOptionPane.showMessageDialog(dupAnimal,
							"Please enter a number between from 20 to 320",
							"Error", JOptionPane.INFORMATION_MESSAGE);
					tfSize.setText("20");
					return;
				}
			}
		});
		tfSize.setColumns(10);

		verSpeedSlider = new JSlider();
		verSpeedSlider
				.setToolTipText("Choose a new vertical speed between 1px/sec to 10px/sec.");
		tfVerSpeed = new JTextField();
		tfVerSpeed
				.setToolTipText("Enter a new vertical speed between 1px/sec to 10px/sec.");
		tfVerSpeed.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String typed = tfVerSpeed.getText();
				verSpeedSlider.setValue(1);
				if (!typed.matches("^[0-9]+$") || typed.length() > 2) {
					JOptionPane.showMessageDialog(dupAnimal,
							"Please enter a number between from 1 to 10",
							"Error", JOptionPane.INFORMATION_MESSAGE);
					tfVerSpeed.setText("1");
					return;
				}

				int num = Integer.parseInt(typed);
				if (num >= 1 && num <= 10)
					verSpeedSlider.setValue(Integer.parseInt(typed));
				else {
					JOptionPane.showMessageDialog(dupAnimal,
							"Please enter a number between from 1 to 10",
							"Error", JOptionPane.INFORMATION_MESSAGE);
					tfVerSpeed.setText("1");
					return;
				}
			}
		});
		tfVerSpeed.setColumns(10);

		horSpeedSlider = new JSlider();
		horSpeedSlider
				.setToolTipText("Choose a new horizontal speed between 1px/sec to 10px/sec.");
		tfHorSpeed = new JTextField();
		tfHorSpeed
				.setToolTipText("Enter a new horizontal speed between 1px/sec to 10px/sec.");
		tfHorSpeed.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String typed = tfHorSpeed.getText();
				horSpeedSlider.setValue(1);
				if (!typed.matches("^[0-9]+$") || typed.length() > 2) {
					JOptionPane.showMessageDialog(dupAnimal,
							"Please enter a number between from 1 to 10",
							"Error", JOptionPane.INFORMATION_MESSAGE);
					tfHorSpeed.setText("1");
					return;
				}

				int num = Integer.parseInt(typed);
				if (num >= 1 && num <= 10)
					horSpeedSlider.setValue(Integer.parseInt(typed));
				else {
					JOptionPane.showMessageDialog(dupAnimal,
							"Please enter a number between from 1 to 10",
							"Error", JOptionPane.INFORMATION_MESSAGE);
					tfHorSpeed.setText("1");
					return;
				}
			}
		});
		tfHorSpeed.setColumns(10);

		sizeSlider.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				size = sizeSlider.getValue();
				tfSize.setText(size.toString());
			}
		});
		sizeSlider.setPaintLabels(true);
		sizeSlider.setValue(20);
		sizeSlider.setMinorTickSpacing(1);
		sizeSlider.setPaintTicks(true);
		sizeSlider.setMinimum(20);
		sizeSlider.setMaximum(320);

		verSpeedSlider.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				verSpeed = verSpeedSlider.getValue();
				tfVerSpeed.setText(verSpeed.toString());
			}
		});
		verSpeedSlider.setValue(1);
		verSpeedSlider.setPaintTicks(true);
		verSpeedSlider.setPaintLabels(true);
		verSpeedSlider.setMinorTickSpacing(1);
		verSpeedSlider.setMinimum(1);
		verSpeedSlider.setMaximum(10);

		horSpeedSlider.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				horSpeed = horSpeedSlider.getValue();
				tfHorSpeed.setText(horSpeed.toString());
			}
		});
		horSpeedSlider.setValue(1);
		horSpeedSlider.setPaintTicks(true);
		horSpeedSlider.setPaintLabels(true);
		horSpeedSlider.setMinorTickSpacing(1);
		horSpeedSlider.setMinimum(1);
		horSpeedSlider.setMaximum(10);

		JPanel panel = new JPanel();
		panel.setToolTipText("Choose the type of the animal that you want to duplicate.");

		colorComboBox = new JComboBox<String>();
		colorComboBox.setToolTipText("Choose a new color.");
		colorComboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (colorComboBox.getSelectedItem() == "Red")
					col = Color.RED;
				else if (colorComboBox.getSelectedItem() == "Blue")
					col = Color.BLUE;
				else if (colorComboBox.getSelectedItem() == "Cyan")
					col = Color.CYAN;
				else if (colorComboBox.getSelectedItem() == "Green")
					col = Color.GREEN;
				else if (colorComboBox.getSelectedItem() == "Magenta")
					col = Color.MAGENTA;
			}
		});
		colorComboBox.setModel(new DefaultComboBoxModel<String>(new String[] {
				"Red", "Blue", "Cyan", "Green", "Magenta" }));

		JLabel lblColor = new JLabel("Color:");

		JButton btnUpdate = new JButton("Update & Duplicate");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (comboBox.getSelectedIndex() != -1) {
					Integer num = comboBox.getSelectedIndex();
					Swimmable cloneAnimal = (Swimmable) AnimalCache
							.getAnimal(num.toString());

					cloneAnimal.setColor(col);
					cloneAnimal.setHorSpeed(horSpeed);
					cloneAnimal.setVerSpeed(verSpeed);
					cloneAnimal.setSize(size);

					ap.addCreature(cloneAnimal);

					dispose();
				}
			}
		});

		// rejoin Grouplayout
		GroupLayout groupLayout = new GroupLayout(this.getContentPane());
		groupLayout
				.setHorizontalGroup(groupLayout
						.createParallelGroup(Alignment.TRAILING)
						.addGroup(
								groupLayout
										.createSequentialGroup()
										.addContainerGap()
										.addGroup(
												groupLayout
														.createParallelGroup(
																Alignment.TRAILING)
														.addComponent(
																panel,
																GroupLayout.DEFAULT_SIZE,
																398,
																Short.MAX_VALUE)
														.addComponent(comboBox,
																0, 398,
																Short.MAX_VALUE)
														.addGroup(
																groupLayout
																		.createSequentialGroup()
																		.addGroup(
																				groupLayout
																						.createParallelGroup(
																								Alignment.TRAILING)
																						.addComponent(
																								lblColor)
																						.addComponent(
																								lblHorSpeed)
																						.addComponent(
																								lblSize)
																						.addComponent(
																								lblVerSpeed))
																		.addPreferredGap(
																				ComponentPlacement.UNRELATED)
																		.addGroup(
																				groupLayout
																						.createParallelGroup(
																								Alignment.LEADING)
																						.addGroup(
																								groupLayout
																										.createSequentialGroup()
																										.addComponent(
																												tfVerSpeed,
																												GroupLayout.PREFERRED_SIZE,
																												45,
																												GroupLayout.PREFERRED_SIZE)
																										.addGap(18)
																										.addComponent(
																												verSpeedSlider,
																												GroupLayout.DEFAULT_SIZE,
																												240,
																												Short.MAX_VALUE))
																						.addGroup(
																								groupLayout
																										.createSequentialGroup()
																										.addComponent(
																												tfSize,
																												GroupLayout.PREFERRED_SIZE,
																												45,
																												GroupLayout.PREFERRED_SIZE)
																										.addGap(18)
																										.addComponent(
																												sizeSlider,
																												GroupLayout.DEFAULT_SIZE,
																												240,
																												Short.MAX_VALUE))
																						.addGroup(
																								groupLayout
																										.createSequentialGroup()
																										.addComponent(
																												tfHorSpeed,
																												GroupLayout.PREFERRED_SIZE,
																												45,
																												GroupLayout.PREFERRED_SIZE)
																										.addGap(18)
																										.addComponent(
																												horSpeedSlider,
																												GroupLayout.DEFAULT_SIZE,
																												240,
																												Short.MAX_VALUE))
																						.addGroup(
																								groupLayout
																										.createSequentialGroup()
																										.addComponent(
																												colorComboBox,
																												GroupLayout.PREFERRED_SIZE,
																												79,
																												GroupLayout.PREFERRED_SIZE)
																										.addGap(61)
																										.addComponent(
																												btnUpdate)))))
										.addContainerGap()));
		groupLayout
				.setVerticalGroup(groupLayout
						.createParallelGroup(Alignment.LEADING)
						.addGroup(
								groupLayout
										.createSequentialGroup()
										.addContainerGap()
										.addComponent(panel,
												GroupLayout.PREFERRED_SIZE,
												GroupLayout.DEFAULT_SIZE,
												GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(
												ComponentPlacement.RELATED)
										.addComponent(comboBox,
												GroupLayout.PREFERRED_SIZE,
												GroupLayout.DEFAULT_SIZE,
												GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(
												ComponentPlacement.RELATED)
										.addGroup(
												groupLayout
														.createParallelGroup(
																Alignment.TRAILING)
														.addGroup(
																groupLayout
																		.createParallelGroup(
																				Alignment.BASELINE)
																		.addComponent(
																				lblSize)
																		.addComponent(
																				tfSize,
																				GroupLayout.PREFERRED_SIZE,
																				GroupLayout.DEFAULT_SIZE,
																				GroupLayout.PREFERRED_SIZE))
														.addComponent(
																sizeSlider,
																GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE,
																GroupLayout.PREFERRED_SIZE))
										.addGap(7)
										.addGroup(
												groupLayout
														.createParallelGroup(
																Alignment.TRAILING)
														.addGroup(
																groupLayout
																		.createParallelGroup(
																				Alignment.BASELINE)
																		.addComponent(
																				lblVerSpeed)
																		.addComponent(
																				tfVerSpeed,
																				GroupLayout.PREFERRED_SIZE,
																				GroupLayout.DEFAULT_SIZE,
																				GroupLayout.PREFERRED_SIZE))
														.addComponent(
																verSpeedSlider,
																GroupLayout.PREFERRED_SIZE,
																31,
																GroupLayout.PREFERRED_SIZE))
										.addGap(7)
										.addGroup(
												groupLayout
														.createParallelGroup(
																Alignment.TRAILING)
														.addGroup(
																groupLayout
																		.createParallelGroup(
																				Alignment.BASELINE)
																		.addComponent(
																				lblHorSpeed)
																		.addComponent(
																				tfHorSpeed,
																				GroupLayout.PREFERRED_SIZE,
																				GroupLayout.DEFAULT_SIZE,
																				GroupLayout.PREFERRED_SIZE))
														.addComponent(
																horSpeedSlider,
																GroupLayout.PREFERRED_SIZE,
																31,
																GroupLayout.PREFERRED_SIZE))
										.addPreferredGap(
												ComponentPlacement.UNRELATED)
										.addGroup(
												groupLayout
														.createParallelGroup(
																Alignment.LEADING)
														.addComponent(lblColor)
														.addComponent(
																colorComboBox,
																GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE,
																GroupLayout.PREFERRED_SIZE)
														.addComponent(btnUpdate))
										.addContainerGap(50, Short.MAX_VALUE)));
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		// endrejoin Grouplayout

		JButton btnFish = new JButton("Fish");
		btnFish.setToolTipText("Choose the type of the animal that you want to duplicate.");
		btnFish.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AnimalCache.setReset();

				if (!fishPress) {
					comboBox.removeAllItems();
					ITE = creatures.iterator();
					int i = 0;
					while (ITE.hasNext()) {
						try {
							Fish cur = (Fish) ITE.next();
							comboBox.addItem(cur);
							AnimalCache.loadCache(i++, cur);
						} catch (ClassCastException ex) {
						}
					}

					fishPress = true;
					jellyfishPress = false;
				}
			}
		});
		panel.add(btnFish);

		JButton btnJellyfish = new JButton("Jellyfish");
		btnJellyfish
				.setToolTipText("Choose the type of the animal that you want to duplicate.");
		btnJellyfish.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AnimalCache.setReset();

				if (!jellyfishPress) {
					comboBox.removeAllItems();
					ITE = creatures.iterator();
					int i = 0;
					while (ITE.hasNext()) {
						try {
							Jellyfish cur = (Jellyfish) ITE.next();
							AnimalCache.loadCache(i++, cur);
							comboBox.addItem(cur);
						} catch (ClassCastException ex) {
						}
					}
					jellyfishPress = true;
					fishPress = false;
				}

			}
		});
		panel.add(btnJellyfish);
		this.getContentPane().setLayout(groupLayout);
	}

	/**
	 * This method defines the parameters of the animal that goes to be
	 * duplicated.
	 * 
	 * @param size
	 *            Size of the animal that going to be duplicated.
	 * @param verSpeed
	 *            Vertical speed of the animal that going to be duplicated.
	 * @param horSpeed
	 *            Horizontal speed of the animal that going to be duplicated.
	 * @param col
	 *            Animal's color that going to be duplicated.
	 */
	private void setValues() {
		if (comboBox.getSelectedIndex() != -1) {
			animal = (Swimmable) comboBox.getSelectedItem();

			this.size = animal.getSize();
			this.verSpeed = animal.getVerSpeed();
			this.horSpeed = animal.getHorSpeed();
			this.col = animal.getColorRef();

			sizeSlider.setValue(size);
			verSpeedSlider.setValue(verSpeed);
			horSpeedSlider.setValue(horSpeed);
			colorComboBox.setSelectedItem(AquaPanel.ColorName(col));
		}
	}
}
