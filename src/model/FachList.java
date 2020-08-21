/**
 * 
 */
package model;

import java.util.*;
import javax.swing.*;

/**
 * Model Klasse für die Sammlung der Fächer
 * 
 * @author Mia Gudelj
 * @since 09/04/2020
 *
 */
public class FachList extends DefaultComboBoxModel<String> {

	private Vector<String> faecher;

	/**
	 * Konstruktor
	 */
	public FachList() {
		this.faecher = new Vector<String>();
		this.faecher.add("Mathe");
		this.faecher.add("Deutsch");
		this.faecher.add("Franz");
		this.sortFaecher();
	}

	/**
	 * sortiert die Fächer aufsteigend nach Alphabet
	 */
	public void sortFaecher() {
		/*
		 * List<String> datenList = new Vector<String>(); for (int i = 0; i <
		 * faecher.size(); i++) { datenList.add(faecher.get(i)); }
		 * Collections.sort(datenList);
		 */

		Collections.sort(faecher, new Comparator<String>() {
			public int compare(String s1, String s2) {
				return s1.compareTo(s2);
			}
		});
	}

	@Override
	public String getElementAt(int index) {
		return this.faecher.get(index);

	}

	@Override
	public int getSize() {
		return this.faecher.size();
	}

	@Override
	public void addElement(String fach) {
		this.faecher.add(fach);
		//Collections.sort(faecher);
		fireIntervalAdded(this, getSize(), getSize());
	}
	
	@Override
	public String toString() {
		String string = "--- Alle Fächer ---";
		for (Iterator iterator = faecher.iterator(); iterator.hasNext();) {
			string += "\n" + (String) iterator.next();
			
		}
		return string;
		
	}
// Ende
}
