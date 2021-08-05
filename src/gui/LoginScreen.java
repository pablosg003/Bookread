package gui;

import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.Spring;

public class LoginScreen extends MainFrame{

	private static final long serialVersionUID = 1L;

	public LoginScreen() {
		
		super();
		setLayout(new BorderLayout());
		
		//Login menu (left)
		
		class LogSignPanel extends JPanel {
			
			private static final long serialVersionUID = 1L;
			
			//General elements
			private Box logSignBox = Box.createVerticalBox();
			private Box wholePanel = Box.createVerticalBox();
			private JSeparator logSignSeparator = new JSeparator(JSeparator.HORIZONTAL);
			private Spring cons = Spring.constant(0, 10, 100);

			//Log in elements
			private JPanel login = new JPanel();
			
			private JLabel lUserL = new JLabel("Username:");
			private JTextField lUserT = new JTextField(15);
			
			private JLabel lPasswordL = new JLabel("Password:");
			private JTextField lPasswordT = new JTextField(15);

			private JButton loginB = new JButton("Log in");
			
			//Sign up elements
			private JPanel signin = new JPanel();

			private JLabel sMailL = new JLabel("E-mail:");
			private JTextField sMailT = new JTextField(15);
			
			private JLabel sUserL = new JLabel("Username:");
			private JTextField sUserT = new JTextField(15);
			
			private JLabel sPasswordL = new JLabel("Password:");
			private JTextField sPasswordT = new JTextField(15);

			private JLabel sNameL = new JLabel("Name:");
			private JTextField sNameT = new JTextField(15);

			private JLabel sSurnameL = new JLabel("Surname:");
			private JTextField sSurnameT = new JTextField(15);

			private JLabel sBirthL = new JLabel("Birth date:");

			private JLabel sGenderL = new JLabel("Gender:");

			
			public LogSignPanel(){
				
				login.setLayout(new GridLayout(3, 2, 10, 10));
				
				login.add(lPasswordL);
				login.add(lPasswordT);
				
				login.add(lUserL);
				login.add(lUserT);
				login.add(new JPanel());
				login.add(loginB);
				logSignBox.add(login);
				
				logSignBox.add(Box.createVerticalStrut(10));
				logSignBox.add(logSignSeparator);
				logSignBox.add(Box.createVerticalStrut(10));

				signin.setLayout(new GridLayout(2, 6));
				
				signin.add(sMailL);
				
				wholePanel.add(logSignBox);
				
				//Setting the fonts
				MainFrame.changeFont(logSignBox, plainFont);

				add(wholePanel);
			}
		}
		
		add(new LogSignPanel(), BorderLayout.WEST);
		
		//Image (right)
		
		add(new JPanel() {
			
			private static final long serialVersionUID = 1L;

			public void paintComponent(Graphics g) {
				
				try {
					g.drawImage(ImageIO.read(new File("files/images/bookshelf.png")), 0, 0, null);
				} catch (IOException e) {
					e.printStackTrace();
					g.drawString("Image not found", 500, 200);
				}
			}
			
		}, BorderLayout.CENTER);
		
	}
	
}
