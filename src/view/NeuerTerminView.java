/**
 * 
 */
package view;

import model.*;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.*;

/**
 * Model Klasse für Aufgabe/Termin
 * 
 * @author Mia Gudelj
 * @since 09/04/2020
 *
 */
public class NeuerTerminView extends JDialog {

	// variabeln
	private JLabel title, calenderLabel;
	private JTextField bezeichnung, datum, inputValue;
	private JCheckBox testCheckbox;
	private JButton saveButton, cancelButton;
	private JComboBox<String> faecherComboBox;
	private DefaultComboBoxModel<String> fachModel;
	private TableModel tableModel;
	private StartseiteView parent;
	private JDialog dialog;

	/**
	 * Konstruktor
	 */
	public NeuerTerminView(DefaultComboBoxModel<String> fachModel, StartseiteView parent, TableModel tableModel) {

		this.dialog = this;
		//
		this.setTitle("Terminator - Neuer Termin");
		this.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		//
		this.fachModel = fachModel;
		this.tableModel = tableModel;
		this.parent = parent;
		//
		parent.centreWindow(this);
		this.init();
		//
		this.setSize(500, 500);
		this.setResizable(false);
		this.setVisible(false);
	}

	private void init() {

		title = new JLabel("Neuer Termin");
		// -- TITEL --
		// Schriftgrösse setzen
		title.setFont(title.getFont().deriveFont(24.0f));

		// Panel für den Titel
		JPanel titlePanel1 = new JPanel(new BorderLayout(50, 50));

		// Platzierung des Titels in der Mitte
		titlePanel1.add(title, BorderLayout.CENTER);
		titlePanel1.add(new JLabel(), BorderLayout.NORTH);
		titlePanel1.add(new JLabel(), BorderLayout.WEST);
		titlePanel1.add(new JLabel(), BorderLayout.EAST);
		titlePanel1.add(new JLabel(), BorderLayout.SOUTH);

		titlePanel1.setBackground(Color.white);

		// Panel des Titels in einen neuen Panel um diesen im Norden des Fensters zu
		// setzen
		JPanel labelPanel2 = new JPanel(new BorderLayout());

		labelPanel2.add(titlePanel1);

		// -- ANGABEN DES TERMINS --
		// bezeichnung
		bezeichnung = new JTextField("Bezeichnung");

		bezeichnung.setSize(20, 2);
		// checkbox
		testCheckbox = new JCheckBox("Test");
		testCheckbox.setBackground(Color.white);

		// combobox
		JLabel fachZufuegenLabel = new JLabel("Fach hinzufügen");
		fachModel.addElement(fachZufuegenLabel.getText());
		faecherComboBox = new JComboBox<String>(this.fachModel);
		faecherComboBox.setEditable(false);
		faecherComboBox.setSelectedIndex(0);
		// faecherComboBox.setSelectedIndex(fachModel.getSize() - 1);
		faecherComboBox.setPreferredSize(new Dimension(150, faecherComboBox.getPreferredSize().height));

		// textfield in combobox
		inputValue = new JTextField();

		inputValue.setVisible(false);
		faecherComboBox.add(inputValue);
		faecherComboBox.setComponentZOrder(inputValue, 0);

		// datum
		datum = new JTextField("dd.mm.yyyy");
		datum.setSize(20, 2);

		// Panel um alle Elemente anzuordnen
		JPanel bezeichnungPanel = new JPanel(new GridLayout(2, 2, 20, 10));
		JScrollPane scrollPane = new JScrollPane(faecherComboBox);

		bezeichnungPanel.add(bezeichnung);
		bezeichnungPanel.add(testCheckbox);
		bezeichnungPanel.add(scrollPane);
		bezeichnungPanel.add(datum);
		bezeichnungPanel.setBackground(Color.white);

		JPanel bezeichnungaPanelPanel = new JPanel(new BorderLayout(100, 100));

		bezeichnungaPanelPanel.add(bezeichnungPanel, BorderLayout.NORTH);
		bezeichnungaPanelPanel.setBackground(Color.white);

		// Panel für alles in die Mitte des Fensters zu setzen
		JPanel bodyPanel = new JPanel(new BorderLayout(50, 100));

		bodyPanel.add(bezeichnungaPanelPanel, BorderLayout.CENTER);
		bodyPanel.add(new JLabel(), BorderLayout.WEST);
		bodyPanel.add(new JLabel(), BorderLayout.EAST);

		bodyPanel.setBackground(Color.white);

		// -- KNÖPFE --
		saveButton = new JButton("Speichern");
		saveButton.setBackground(Color.green.darker());
		saveButton.setForeground(Color.white);

		cancelButton = new JButton("Abbrechen");
		cancelButton.setBackground(Color.white);

		// Panel für die Anordnung der Knöpfe
		JPanel buttonPanel = new JPanel(new GridLayout(1, 2, 5, 5));

		buttonPanel.add(cancelButton);
		buttonPanel.add(saveButton);

		buttonPanel.setBackground(Color.white);

		// Panel für die Platzierung im Osten
		JPanel buttonPanel2 = new JPanel(new BorderLayout(50, 0));

		buttonPanel2.add(buttonPanel, BorderLayout.CENTER);
		buttonPanel2.add(new JLabel(), BorderLayout.EAST);
		buttonPanel2.add(new JLabel(), BorderLayout.WEST);

		buttonPanel2.setBackground(Color.white);

		JPanel buttonPanel3 = new JPanel(new BorderLayout(0, 20));

		buttonPanel3.add(buttonPanel2, BorderLayout.EAST);
		buttonPanel3.add(new JLabel(), BorderLayout.SOUTH);

		buttonPanel3.setBackground(Color.white);

		// Elemente dem Fenster zufügen
		getContentPane().add(labelPanel2, BorderLayout.NORTH);
		getContentPane().add(bodyPanel, BorderLayout.CENTER);
		getContentPane().add(buttonPanel3, BorderLayout.SOUTH);

		// --- LISTENER ---
		// FOCUSLISTENER
		bezeichnung.addFocusListener(new TextFieldListener());
		datum.addFocusListener(new TextFieldListener());

		// ACTIONLISTENER
		cancelButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				parent.setEnabled(true);
				dialog.dispose();

			}
		});
		//
		saveButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				// falls eines der Textfelder leer und kein Fach ausgewählt ist
				if (!bezeichnung.getText().isBlank() && !bezeichnung.getText().equals("Bezeichnung")
						&& !datum.getText().isBlank() && !datum.getText().equals("dd.mm.yyyy")
						&& !faecherComboBox.getSelectedItem().toString().equals("Fach hinzufügen")) {

					// hole die Werte und speichere sie in die Tabelle
					// TODO ...if (ddmmyyyy.matches(
					if (datum.getText().matches(
							"^(([0,0][1-9])|([1,2][0-9])|([3,3][0,1]))[\\.](([0,0][1-9])|([1,1][0-2]))[\\.]([2-2][0-0][2-9][0-9])$")) {

						System.out.println("\n---- NEUE AUFGABE ----");
						System.out.println("Bezeichnung:\t" + bezeichnung.getText());
						System.out.println("Fach:\t\t" + faecherComboBox.getSelectedItem().toString());
						System.out.println("Test:\t\t" + testCheckbox.isSelected());
						System.out.println("Datum:\t\t" + datum.getText());

						AufgabenList aufgaben = (AufgabenList) tableModel;
						aufgaben.addAufgabe(new Aufgabe(faecherComboBox.getSelectedItem().toString(),
								bezeichnung.getText(), false, datum.getText(), testCheckbox.isSelected()));
						// Ausgabe aller Aufgaben
						System.out.println(aufgaben.toString());

						// Daten zurücksetzen
						bezeichnung.setText("Bezeichnung");
						testCheckbox.setSelected(false);
						datum.setText("dd.mm.yyyy");

						parent.setEnabled(true);
						dialog.dispose();
					} else {
						warning();

					}
				} else {
					new Warningmessage();
				}
			}
		});
		//
		faecherComboBox.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				if (e.getItem().equals(fachZufuegenLabel.getText()) && e.getStateChange() == ItemEvent.SELECTED) {
					// System.out.println(e.getItem());
					new FachInputDialog(fachModel, NeuerTerminView.this);
					/*
					 * TODO setze "fach hinzufügen" als letzen index
					 * fachModel.insertElementAt(fachZufuegenLabel.getText(), e.getStateChange()-1);
					 * faecherComboBox.add(fachZufuegenLabel, fachModel.getSize()-1);
					 */
				}
			}

		});
	}

	private void warning() {
		JDialog warning = new JDialog(this);
		JLabel warningmessage = new JLabel("Format nicht korrekt bei Datum");
		JLabel instructionMessage = new JLabel("Format: dd.MM.yyyy");
		JPanel messagePanel = new JPanel(new GridLayout(2, 1, 10, 10));
		JPanel panel = new JPanel(new BorderLayout(10, 10));
		//
		warning.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		warning.setTitle("Warning");
		//
		messagePanel.add(warningmessage);
		messagePanel.add(instructionMessage);
		
		warningmessage.setForeground(Color.red);
		instructionMessage.setForeground(Color.red);
		messagePanel.setBackground(Color.white);

		panel.add(messagePanel, BorderLayout.CENTER);
		panel.add(new JLabel(), BorderLayout.NORTH);
		panel.add(new JLabel(), BorderLayout.SOUTH);
		panel.add(new JLabel(), BorderLayout.WEST);
		panel.add(new JLabel(), BorderLayout.EAST);
		panel.setBackground(Color.white);
		
		warning.getContentPane().add(panel);
		//
		parent.centreWindow(warning);
		warning.setResizable(false);
		warning.setVisible(true);
		warning.pack();
	}

	/**
	 * Listener für Textfelder von "Neuer Termin" dieser löscht den Placeholder und
	 * stellt diesen bei leerem Textfeld wieder her
	 * 
	 * @author Mia
	 *
	 */
	class TextFieldListener implements FocusListener {

		@Override
		public void focusLost(FocusEvent e) {
			if (bezeichnung.getText().contentEquals("")) {
				bezeichnung.setText("Bezeichnung");
			}
			if (datum.getText().isBlank()) {
				datum.setText("dd.mm.yyyy");
			}
		}

		@Override
		public void focusGained(FocusEvent e) {
			if (bezeichnung.getText().equals("Bezeichnung")) {
				bezeichnung.setText("");
			}
			if (datum.getText().equals("dd.mm.yyyy")) {
				datum.setText("");
			}
		}
	}
}