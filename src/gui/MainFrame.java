package gui;

import java.awt.Component;
import java.awt.Container;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.JFrame;

class MainFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	public static final Font plainFont = new Font("Times New Roman", Font.PLAIN, 16);
	
	public MainFrame() {
		
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		
		setIconImage(toolkit.getImage("files/images/brLogo.png"));
		
		setTitle("Bookread");
		setBounds(toolkit.getScreenSize().width/2-600, toolkit.getScreenSize().height/2-350, 1200, 700);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		
	}
	
	public static void changeFont (Component component, Font font) {
	    component.setFont(font);
	    if (component instanceof Container) {
	        for (Component child: ((Container) component).getComponents()) {
	            changeFont (child, font);
	        }
	    }
	}
}
