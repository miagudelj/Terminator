/**
 * 
 */
import java.util.Vector;

import model.*;
import view.StartseiteView;

/** 
 * @author Mia Gudelj
 * @since 09/04/2020
 */
public class TerminatorApp {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		FachList faecher = new FachList();
		AufgabenList aufgabenList = new AufgabenList();
		//
		new Datainitialization(aufgabenList, faecher);
		//
		StartseiteView gui = new StartseiteView(aufgabenList);
	}

}
