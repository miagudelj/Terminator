package model;

import java.util.*;

import javax.swing.table.*;

public class AufgabenList extends DefaultTableModel {
	private Vector<Aufgabe> allAufgaben;

	/**
	 * Konstruktor
	 */
	public AufgabenList() {
		this.allAufgaben = new Vector<Aufgabe>();
	}
	
	/**
	 * Fügt ein neues Objekt der Sammlung zu.
	 * 
	 * Die GUI-Komponenten müssen über die Datenänderung infomriert werden, damit
	 * sie die neuen Werte einlesen können. Diese erfolgt über die
	 * fireIntervalAdded-Methode aus der Komfortklasse AbstractListModel von der
	 * DefaultListModel erbt.
	 */
	public void addAufgabe(Aufgabe aufgabe) {
		this.allAufgaben.addElement(aufgabe);
		// TODO prove which one works better
		//this.fireTableRowsInserted(0, getRowCount() - 1);
		this.fireTableDataChanged();
		
	}

	/**
	 * gibt alle Aufgaben zurück
	 * 
	 * @return allAufgaben
	 */
	public Vector<Aufgabe> getAllAufgaben() {
		return this.allAufgaben;
	}

	/**
	 * 
	 * @param allAufgaben the allAufgaben to set
	 */
	public void setAllAufgaben(Vector<Aufgabe> allAufgaben) {
		this.allAufgaben = allAufgaben;
	}

	
	/**
	 * @param column
	 * @return columnname
	 */
	@Override
	public String getColumnName(int column) {
		// TODO Auto-generated method stub
		return this.getTitles().elementAt(column);
	}

	/**
	 * Column names
	 * 
	 * @return titles
	 */
	private Vector<String> getTitles() {
		Vector<String> titles = new Vector<String>();
		titles.add("Fach");
		titles.add("Aufgabe");
		titles.add("Erledigt");
		titles.add("Datum");
		return titles;
	}

	/**
	 * @return size of columns in table
	 */
	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return this.getTitles().size();
	}

	/**
	 * @return size of rows in table
	 */
	@Override
	public int getRowCount() {
		return this.allAufgaben != null ? this.allAufgaben.size() : 0;
	}

	/**
	 *
	 */
	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Aufgabe aufg = this.allAufgaben.get(rowIndex);

		switch (columnIndex) {
		case 0:
			return aufg.getFach();
		case 1:
			return aufg.getBezeichnung();
		case 2:
			return aufg.isErledigt();
		case 3:
			return aufg.getDatum();
		case 4:
			return aufg.isTest();
		default:
			return null;
		}
	}

	/**
	 * 
	 */
	@Override
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {

		Aufgabe aufg = this.allAufgaben.get(rowIndex);

		switch (columnIndex) {
		case 0:
			aufg.setFach(aValue.toString());
			break;
		case 1:
			aufg.setBezeichnung(aValue.toString());
			break;
		case 2:
			aufg.setErledigt(new Boolean(aValue.toString()).booleanValue());
			break;
		case 3:
			aufg.setDatum((String) aValue);
			break;
		case 4:
			aufg.setTest(new Boolean(aValue.toString()).booleanValue());
			break;
		}
		this.fireTableDataChanged();
	}

	/**
	 * sets cells not editable
	 * @return false
	 */
	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return false;
	}

	/**
	 * 
	 */
	@Override
	public String toString() {
		String out = "\n--- Alle Aufgaben der Sammlung---";
		for (Aufgabe aufgabe : this.allAufgaben) {
			out += "\n";
			out += "Fach:\t\t" + aufgabe.getFach() + "\n";
			out += "Bezeichnung:\t" + aufgabe.getBezeichnung() + "\n";
			out += "Datum:\t\t" + aufgabe.getDatum() + "\n";
			out += "Test:\t\t" + aufgabe.isTest() + "\n";
		}
		return out;
	}
// Ende
}
