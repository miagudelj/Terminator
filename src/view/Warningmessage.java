/**
 * Dialog für fehlerhafte Eingaben in NeuerTerminView
 */
package view;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * @author Mia Gudelj
 * @version 1.0
 * @since 16.05.2020
 *
 */
public class Warningmessage extends JDialog{

	JDialog warningDialog;
	JButton ok;
	JLabel warningMessage1, warningMessage2;
	JPanel panel, messagePanel, buttonPanelDialog;
	
	/**
	 * Dialog für die Fehlermeldung
	 */
	public Warningmessage() {
		warningDialog = this;
		warningDialog.setTitle("Warning");
		//
		warningDialog.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		//
		init();
		centreWindow(warningDialog);
		//
		warningDialog.pack();
		warningDialog.setResizable(false);
		warningDialog.setVisible(true);

	}

	private void init() {

		ok = new JButton("OK");
		warningMessage1 = new JLabel("Bitte füllen Sie");
		warningMessage2 = new JLabel("alle Felder aus!");
		panel = new JPanel(new GridLayout(2, 1));
		messagePanel = new JPanel(new BorderLayout(30, 20));
		buttonPanelDialog = new JPanel(new GridLayout(1, 3, 0, 20));

		panel.add(warningMessage1);
		panel.add(warningMessage2);

		messagePanel.add(panel, BorderLayout.CENTER);
		messagePanel.add(new JLabel(), BorderLayout.NORTH);
		messagePanel.add(new JLabel(), BorderLayout.SOUTH);
		messagePanel.add(new JLabel(), BorderLayout.WEST);
		messagePanel.add(new JLabel(), BorderLayout.EAST);

		buttonPanelDialog.add(new JLabel());
		buttonPanelDialog.add(ok);
		buttonPanelDialog.add(new JLabel());

		buttonPanelDialog.setBackground(Color.black);
		panel.setBackground(Color.black);
		messagePanel.setBackground(Color.black);
		warningMessage1.setForeground(Color.red);
		warningMessage2.setForeground(Color.red);
		ok.setBackground(Color.red);

		warningDialog.getContentPane().add(messagePanel, BorderLayout.CENTER);
		warningDialog.getContentPane().add(buttonPanelDialog, BorderLayout.SOUTH);

		// Actionlistener
		ok.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				warningDialog.setVisible(false);
			}
		});
	}

	/**
	 * zentriert das Fenster
	 * 
	 * @param frame
	 */
	public void centreWindow(Window frame) {
		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
		int x = (int) ((dimension.getWidth() - frame.getWidth()) / 2.25);
		int y = (int) ((dimension.getHeight() - frame.getHeight()) / 2.25);
		frame.setLocation(x, y);
	}
}
