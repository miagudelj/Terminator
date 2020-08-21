/**
 * Dialog für die Erfassung eines neuen Faches welche dieses dann in die Dropbox von NeuerTerminView speichert.
 */
package view;

import java.awt.*;
import java.awt.event.*;
import java.util.*;

import javax.swing.*;

import model.*;

/**
 * @author Mia Gudelj
 * @version 1.0
 * @since 16.05.2020
 *
 */
public class FachInputDialog extends JDialog {

	private JTextField fachName;
	private JLabel warning;
	private JButton okBtn, cancelBtn;
	private FachList model;
	private NeuerTerminView parent;
	private JDialog dialog;

	FachInputDialog(ComboBoxModel<String> model, NeuerTerminView parent) {
		dialog = this;
		this.parent = parent;
		this.model = (FachList) model;
		//
		dialog.setVisible(true);
		dialog.setTitle("Neues Fach erfassen");
		dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		//
		this.init();
		this.centreWindow(this);
		//
		dialog.pack();
		dialog.setResizable(false);
	}

	private void init() {
		dialog.setBackground(Color.white);

		warning = new JLabel(" ");
		warning.setForeground(Color.red);
		//
		JPanel inputPanel = new JPanel(new BorderLayout(5, 15));
		fachName = new JTextField();

		inputPanel.add(new JLabel("Fach: "), BorderLayout.WEST);
		inputPanel.add(fachName, BorderLayout.CENTER);
		inputPanel.add(new JLabel(), BorderLayout.NORTH);
		inputPanel.add(new JLabel(), BorderLayout.SOUTH);
		inputPanel.setBackground(Color.white);

		JPanel btnPanel = new JPanel(new GridLayout(1, 2, 5, 5));
		cancelBtn = new JButton("Abbrechen");
		okBtn = new JButton("OK");

		btnPanel.add(cancelBtn);
		btnPanel.add(okBtn);
		btnPanel.setBackground(Color.white);

		JPanel panel = new JPanel(new BorderLayout(10, 10));
		panel.add(warning, BorderLayout.NORTH);
		panel.add(inputPanel, BorderLayout.CENTER);
		panel.add(new JLabel(), BorderLayout.WEST);
		panel.add(new JLabel(), BorderLayout.EAST);
		panel.add(btnPanel, BorderLayout.SOUTH);
		panel.setBackground(Color.white);

		// Komponente dem Fenster zufügen
		dialog.getContentPane().add(panel, BorderLayout.CENTER);

		// ---- LISTENERS ----
		// ---ActionListener
		okBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO prüfen ob fach bereits existiert in collection
				boolean fachExists = false;
				
				for (int i = 0; i < model.getSize(); i++) {
					if (fachName.getText().equalsIgnoreCase(model.getElementAt(i).toString())) {
						fachExists = true;
					}
				}

				if (fachExists == true) {
					warning.setText("Fach existiert bereits");
					fachName.setText("");
				} else if (fachName.getText().isBlank()) {
					warning.setText("ungültige Eingabe");
					fachName.setText("");
				} else {
					model.addElement(fachName.getText());
					model.setSelectedItem(fachName.getText());
					dialog.dispose();
				}
			}
		});

		cancelBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dialog.dispose();
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
