/**
 * 
 */
package model;

/**
 * Model Klasse für Aufgabe/Termin
 * 
 * @author Mia Gudelj
 * @since 09/04/2020
 *
 */
public class Aufgabe {

	private String bezeichnung;
	private String datum;
	private boolean test;
	private boolean erledigt;
	private String fach;

	/**
	 * Konstruktor
	 */
	public Aufgabe(String fach, String bezeichnung, boolean erledigt, String datum, boolean test) {
		this.fach = fach;
		this.bezeichnung = bezeichnung;
		this.erledigt = erledigt;
		this.datum = datum;
		this.test = test;
	}

	/**
	 * @return the fach
	 */
	public String getFach() {
		return this.fach;
	}

	/**
	 * @param fach the fach to set
	 */
	public void setFach(String fach) {
		this.fach = fach;
	}

	/**
	 * @return the bezeichnung
	 */
	public String getBezeichnung() {
		return this.bezeichnung;
	}

	/**
	 * @param bezeichnung the bezeichnung to set
	 */
	public void setBezeichnung(String bezeichnung) {
		this.bezeichnung = bezeichnung;
	}

	/**
	 * @return the datum
	 */
	public String getDatum() {
		return this.datum;
	}

	/**
	 * @param datum the datum to set
	 */
	public void setDatum(String ddmmyyyy) {
		this.datum = ddmmyyyy;
	}

	/**
	 * @return the test
	 */
	public boolean isTest() {
		return this.test;
	}

	/**
	 * @param test the test to set
	 */
	public void setTest(boolean isTest) {
		this.test = isTest;
	}

	/**
	 * @return the erledigt
	 */
	public boolean isErledigt() {
		return this.erledigt;
	}

	/**
	 * @param erledigt the erledigt to set
	 */
	public void setErledigt(boolean erledigt) {
		this.erledigt = erledigt;
	}
// Ende
}
