package listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JCheckBox;

public class StayLoggedListener implements ActionListener {

	private boolean stayLogged = false;
	
	public void actionPerformed(ActionEvent e) {
		
		if(((JCheckBox)(e.getSource())).isSelected()) {
			stayLogged = true;
		} else {
			stayLogged = false;
		}
	}
	
	public boolean isLogged() {
		return stayLogged;
	}
}
