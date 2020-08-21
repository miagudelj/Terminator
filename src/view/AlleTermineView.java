/**
 * 
 */
package view;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.RowFilter.Entry;
import javax.swing.table.*;

import model.*;

/**
 * GUI Klasse für die Anzeige aller Aufgaben und Termine
 * 
 * @author Mia Gudelj
 * @since 09/04/2020
 *
 */
public class AlleTermineView extends JDialog {

	// variabeln
	private final StartseiteView parent;
	private JLabel title;
	private JTable table;
	private TableModel tableModle;
	private JCheckBox erledigtCheckbox;
	private JButton zuruekButton;

	/**
	 * Konstruktor
	 */
	public AlleTermineView(TableModel tableModle, StartseiteView parent) {

		this.setTitle("Terminator - Alle Termine");
		this.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);

		this.tableModle = tableModle;
		this.parent = parent;
		//
		this.centreWindow(this);
		this.init();
		//
		this.setSize(500, 500);
		this.setResizable(false);
		this.setVisible(true);

	}

	/**
	 * 
	 */
	private void init() {

		// -- TITEL --
		title = new JLabel("Alle Termine");
		// Schriftgrösse setzen
		title.setFont(title.getFont().deriveFont(24.0f));

		// Panel für den Titel
		JPanel titlePanel = new JPanel(new BorderLayout());
		// Platzierung des Titels in der Mitte// Panel für den Titel
		titlePanel.add(title, BorderLayout.CENTER);
		titlePanel.add(new JLabel(), BorderLayout.NORTH);
		titlePanel.add(new JLabel(), BorderLayout.EAST);
		titlePanel.add(new JLabel(), BorderLayout.SOUTH);

		titlePanel.setBackground(Color.white);

		// -- KNOPF --
		zuruekButton = new JButton("<");
		zuruekButton.setFont(title.getFont().deriveFont(15.0f));
		zuruekButton.setBackground(Color.LIGHT_GRAY);
		// Panel für Knopf links anordnen
		JPanel buttonPanel = new JPanel(new BorderLayout(0, 10));
		buttonPanel.add(zuruekButton, BorderLayout.WEST);
		buttonPanel.add(new JLabel(), BorderLayout.NORTH);
		buttonPanel.add(new JLabel(), BorderLayout.CENTER);

		buttonPanel.setBackground(Color.white);

		JPanel headerPanel = new JPanel(new BorderLayout(10, 20));
		headerPanel.add(buttonPanel, BorderLayout.WEST);
		headerPanel.add(titlePanel, BorderLayout.CENTER);
		headerPanel.add(new JLabel(), BorderLayout.EAST);

		headerPanel.setBackground(Color.white);

		getContentPane().add(headerPanel, BorderLayout.NORTH);

		// -- TABELLE --
		table = new JTable(tableModle);
		table.setEnabled(false);
		table.setRowHeight(30);

		changeColor(table);

		// Panel um alle Elemente anzuordnen
		JPanel tablePanel = new JPanel(new BorderLayout());

		JScrollPane scrollPane = new JScrollPane(table);
		tablePanel.setBackground(Color.white);

		tablePanel.add(scrollPane);

		JPanel tablePanel2 = new JPanel(new BorderLayout(0, 15));
		tablePanel2.add(tablePanel, BorderLayout.CENTER);
		tablePanel2.add(new JLabel(), BorderLayout.NORTH);
		tablePanel2.setBackground(Color.white);

		// -- CHECKBOX --
		erledigtCheckbox = new JCheckBox("Erledigte anzeigen");
		erledigtCheckbox.setSelected(true);
		// panel für Checkbox
		JPanel checkboxPanel = new JPanel(new BorderLayout(50, 20));
		erledigtCheckbox.setBackground(Color.white);
		checkboxPanel.add(erledigtCheckbox, BorderLayout.EAST);
		checkboxPanel.add(new JLabel(), BorderLayout.SOUTH);
		checkboxPanel.add(new JLabel(), BorderLayout.WEST);

		checkboxPanel.setBackground(Color.white);
		JPanel checkboxPanel2 = new JPanel(new BorderLayout(50, 0));
		checkboxPanel2.add(checkboxPanel, BorderLayout.CENTER);
		checkboxPanel2.add(new JLabel(), BorderLayout.WEST);
		checkboxPanel2.add(new JLabel(), BorderLayout.EAST);

		checkboxPanel2.setBackground(Color.white);

		erledigtCheckbox.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				checkIfChecked();
				changeColor(table);
			}
		});

		// Panel für alles in die Mitte des Fensters zu setzen
		JPanel bodyPanel = new JPanel(new BorderLayout(50, 10));
		bodyPanel.add(tablePanel2, BorderLayout.CENTER);
		bodyPanel.add(checkboxPanel2, BorderLayout.SOUTH);
		bodyPanel.add(new JLabel(), BorderLayout.WEST);
		bodyPanel.add(new JLabel(), BorderLayout.EAST);

		bodyPanel.setBackground(Color.white);

		getContentPane().add(bodyPanel, BorderLayout.CENTER);

		// --LISTENER --
		zuruekButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				parent.setEnabled(true);
				AlleTermineView.super.dispose();
			}
		});

	}

	/**
	 * centre window
	 * 
	 * @param frame
	 */
	public void centreWindow(Window frame) {
		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
		int x = (int) ((dimension.getWidth() - frame.getWidth()) / 3);
		int y = (int) ((dimension.getHeight() - frame.getHeight()) / 4);
		frame.setLocation(x, y);
	}

	/**
	 * sets font color of row which tasks are tests red.
	 * 
	 * @author Adel Patkovic & Mia Gudelj
	 * @source https://stackoverflow.com/questions/26161702/color-jtable-row
	 * 
	 * @param table
	 * @param coloredRow
	 */
	private void changeColor(JTable table) {
		table.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
			@Override
			public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
					boolean hasFocus, int row, int column) {
				super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

				AufgabenList a = (AufgabenList) table.getModel();

				if (a.getAllAufgaben().get(row).isTest()) {
					setForeground(Color.red);
				} else {
					setForeground(Color.black);
				}
				return this;
			}
		});
		table.repaint();
	}

	/**
	 * hides all tasks which are set as done ("erledigt") if checkbox is not
	 * selected.
	 * 
	 * @author Adel Patkovic & Mia Gudelj
	 * @source https://stackoverflow.com/questions/1107911/how-can-i-filter-rows-in-a-jtable
	 */
	private void checkIfChecked() {
		RowFilter<DefaultTableModel, Object> filter = new RowFilter<DefaultTableModel, Object>() {
			@Override
			public boolean include(Entry entry) {
				// if the checkbox is set to true --> return true and show all
				// if the checkbox is set to false --> return false and show just those which
				// are false ("nicht erledigt")
				if (!erledigtCheckbox.isSelected() && (boolean) entry.getValue(2)) {
					return false;
				} else {
					return true;
				}
			}
		};

		TableRowSorter<DefaultTableModel> rowSorter = new TableRowSorter(tableModle);
		rowSorter.setRowFilter(filter);
		table.setRowSorter(rowSorter);

	}
}
