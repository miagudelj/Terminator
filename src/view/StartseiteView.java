package view;

/**
 * 
 */

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.*;

import javax.swing.*;
import javax.swing.table.*;

import model.*;

/**
 * @author Mia
 *
 */
public class StartseiteView extends JFrame {

	// variabeln
	private JLabel title, titleDate;
	private JTable table;
	private DefaultTableModel model;
	private JButton neueAufgabeButton, alleAufgabenButton;
	private JCheckBox erledigtCheckbox;
	private AlleTermineView alleTermineGUI;
	private NeuerTerminView neuerTerminGUI;

	/**
	 * Konstruktor
	 */
	public StartseiteView(DefaultTableModel model) {
		super("Terminator - Heutige Termine");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//
		this.model = model;
		//
		alleTermineGUI = new AlleTermineView(model, this);
		alleTermineGUI.setVisible(false);
		neuerTerminGUI = new NeuerTerminView(new FachList(), StartseiteView.this, model);
		neuerTerminGUI.setVisible(false);
		//
		this.init();
		//
		this.centreWindow(this);
		this.setSize(500, 500);
		this.setResizable(false);
		this.setVisible(true);
	}

	private void init() {
		Locale currentRegion = new Locale("de", "DE");
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM", currentRegion);

		title = new JLabel("Heutige Termine");
		titleDate = new JLabel(LocalDate.now().getDayOfMonth() + ". " + LocalDate.now().format(formatter).toString()
				+ " " + LocalDate.now().getYear());
		// -- TITEL --
		// Schriftgrösse setzen
		title.setFont(title.getFont().deriveFont(24.0f));
		titleDate.setFont(title.getFont().deriveFont(20.0f));
		// Panel für den Titel
		JPanel labelPanel1 = new JPanel(new BorderLayout(50, 10));
		labelPanel1.add(title, BorderLayout.CENTER);
		labelPanel1.add(new JLabel(), BorderLayout.NORTH);
		labelPanel1.add(new JLabel(), BorderLayout.WEST);
		labelPanel1.add(new JLabel(), BorderLayout.EAST);
		labelPanel1.add(new JLabel(), BorderLayout.SOUTH);
		labelPanel1.setBackground(Color.white);
		// Platzierung des Titels in der Mitte
		JPanel datePanel1 = new JPanel(new BorderLayout(50, 1));
		datePanel1.add(titleDate, BorderLayout.CENTER);
		datePanel1.add(labelPanel1, BorderLayout.NORTH);
		datePanel1.add(new JLabel(), BorderLayout.WEST);
		datePanel1.add(new JLabel(), BorderLayout.EAST);
		datePanel1.add(new JLabel(), BorderLayout.SOUTH);
		datePanel1.setBackground(Color.white);

		// Panel des Titels in einen neuen Panel um diesen im Norden des Fensters zu
		// setzen
		JPanel labelPanel2 = new JPanel(new BorderLayout());
		labelPanel2.add(datePanel1);
		labelPanel2.setBackground(Color.white);

		getContentPane().add(labelPanel2, BorderLayout.NORTH);

		// -- TABELLE --
		table = new JTable(model);
		table.setEnabled(false);
		table.setRowHeight(30);
		filterOutTasksOfToday();

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
		checkboxPanel.add(erledigtCheckbox, BorderLayout.CENTER);
		checkboxPanel.add(new JLabel(), BorderLayout.SOUTH);
		checkboxPanel.add(new JLabel(), BorderLayout.WEST);
		checkboxPanel.setBackground(Color.white);

		// Panel für alles in die Mitte des Fensters zu setzen
		JPanel bodyPanel = new JPanel(new BorderLayout(50, 10));
		bodyPanel.add(tablePanel2, BorderLayout.CENTER);
		bodyPanel.add(checkboxPanel, BorderLayout.SOUTH);
		bodyPanel.add(new JLabel(), BorderLayout.WEST);
		bodyPanel.add(new JLabel(), BorderLayout.EAST);

		bodyPanel.setBackground(Color.white);

		getContentPane().add(bodyPanel, BorderLayout.CENTER);

		// -- KNÖPFE --
		neueAufgabeButton = new JButton("Neuer Termin");
		neueAufgabeButton.setBackground(Color.white);
		alleAufgabenButton = new JButton("Alle Termine");
		alleAufgabenButton.setBackground(Color.white);

		// Panel für die Anordnung der Knöpfe
		JPanel buttonPanel = new JPanel(new GridLayout(1, 2, 5, 5));
		buttonPanel.add(neueAufgabeButton);
		buttonPanel.add(alleAufgabenButton);
		// Panel für die Platzierung im Osten
		JPanel buttonPanel2 = new JPanel(new BorderLayout(50, 0));
		buttonPanel2.add(buttonPanel, BorderLayout.CENTER);
		buttonPanel2.add(new JLabel(), BorderLayout.EAST);
		buttonPanel2.add(new JLabel(), BorderLayout.WEST);

		JPanel buttonPanel3 = new JPanel(new BorderLayout(0, 20));
		buttonPanel3.add(buttonPanel2, BorderLayout.EAST);
		buttonPanel3.add(new JLabel(), BorderLayout.SOUTH);

		buttonPanel.setBackground(Color.white);
		buttonPanel2.setBackground(Color.white);
		buttonPanel3.setBackground(Color.white);

		getContentPane().add(buttonPanel3, BorderLayout.SOUTH);

		// --LISTENER --

		erledigtCheckbox.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				checkIfChecked();
				changeColor(table);
			}
		});

		neueAufgabeButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				StartseiteView.super.setEnabled(false);
				neuerTerminGUI.setVisible(true);
			}
		});

		alleAufgabenButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				StartseiteView.super.setEnabled(false);
				alleTermineGUI.setVisible(true);
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
					if (shouldBeIncluded(entry.getValue(3).toString())) {
						return true;
					}else {
						return false;
					}
				}
			}
		};

		TableRowSorter<DefaultTableModel> rowSorter = new TableRowSorter(model);
		rowSorter.setRowFilter(filter);
		table.setRowSorter(rowSorter);

	}

	/**
	 * check if date equals date of today. 
	 * if so, then show those tasks with todays date
	 * if not, then hide them.
	 * 
	 * @param date
	 * @return
	 */
	private boolean shouldBeIncluded(String date) {
		String[] splittedDate = date.toString().split("\\.");

		if (splittedDate.length != 3) {
			return true;
		}
		int day = Integer.parseInt(splittedDate[0]);
		int month = Integer.parseInt(splittedDate[1]);
		int year = Integer.parseInt(splittedDate[2]);

		LocalDate today = LocalDate.now();
		/*
		 * Hol die rows in welchen das datum dem heutigen entspricht und blende die
		 * anderen rows aus
		 */
		if (day == today.getDayOfMonth() && month == today.getMonthValue() && year == today.getYear()) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * filters all tasks which are due today. Others will not be shown.
	 * 
	 * @author Adel Patkovic & Mia Gudelj
	 * @source https://stackoverflow.com/questions/1107911/how-can-i-filter-rows-in-a-jtable
	 */
	private void filterOutTasksOfToday() {
		RowFilter<DefaultTableModel, Object> filter = new RowFilter<DefaultTableModel, Object>() {
			@Override
			public boolean include(Entry entry) {
				return shouldBeIncluded(entry.getValue(3).toString());
			}
		};

		TableRowSorter<DefaultTableModel> rowSorter = new TableRowSorter(model);
		rowSorter.setRowFilter(filter);
		table.setRowSorter(rowSorter);

	}
}
