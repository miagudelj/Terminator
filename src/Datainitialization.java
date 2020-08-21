
/**
 * 
 */

import model.*;

/**
 * @author Mia
 *
 */

public class Datainitialization {

	private FachList faecher;
	private AufgabenList aufgabenList;

	public Datainitialization(AufgabenList aufgabenList, FachList faecher) {
		this.aufgabenList = aufgabenList;
		this.faecher = faecher;
		
		faecher.addElement("Englisch");
		faecher.addElement("Deutsch");
		faecher.addElement("Mathe");
		faecher.addElement("Franz");

		aufgabenList.addAufgabe(new Aufgabe("Französisch", "Gramatik Test", false, "24.05.2020", true));
		aufgabenList.addAufgabe(new Aufgabe("Englisch", "Gramatik Test", false, "19.05.2020", true));
		aufgabenList.addAufgabe(new Aufgabe("Mathe", "Test Wahrscheinlichkeiten", true, "20.05.2020", true));
		aufgabenList.addAufgabe(new Aufgabe("Englisch", "Voci Test", true, "17.05.2020", true));
		aufgabenList.addAufgabe(new Aufgabe("Deutsch", "Buch lesen", true, "17.05.2020", false));
		aufgabenList.addAufgabe(new Aufgabe("Französisch", "Pronomen", true, "17.05.2020", false));
		aufgabenList.addAufgabe(new Aufgabe("Französisch", "Kapitel 1 lesen", false, "24.05.2020", false));
		aufgabenList.addAufgabe(new Aufgabe("Mathe", "Aufgabe 3", false, "18.05.2020", false));
		aufgabenList.addAufgabe(new Aufgabe("Mathe", "Test", false, "24.05.2020", true));
		
	}

	public static void main(String[] args) {
		FachList fl = new FachList();
		AufgabenList al = new AufgabenList();
		Datainitialization di = new Datainitialization(al, fl);
		System.out.println(al.toString());
	}
}
