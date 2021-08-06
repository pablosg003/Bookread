package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JSeparator;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;

import listeners.StayLoggedListener;

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
			private JSeparator logSignSeparator = new JSeparator(JSeparator.HORIZONTAL);

			//Log in elements
			private JPanel login = new JPanel();
			
			private JLabel lUserL = new JLabel("Username:");
			private JTextField lUserT = new JTextField(15);
			
			private JLabel lPasswordL = new JLabel("Password:");
			private JPasswordField lPasswordT = new JPasswordField(15);

			private JButton loginB = new JButton("Log in");
			
			private JLabel stayLoggedL = new JLabel("Stay logged:");
			private JCheckBox stayLoggedB = new JCheckBox();
			
			//Sign up elements
			private JPanel signup = new JPanel();

			private JLabel sMailL = new JLabel("E-mail:");
			private JTextField sMailT = new JTextField(15);
			
			private JLabel sUserL = new JLabel("Username:");
			private JTextField sUserT = new JTextField(15);
			
			private JLabel sPasswordL = new JLabel("Password:");
			private JPasswordField sPasswordT = new JPasswordField(15);

			private JLabel sNameL = new JLabel("Name:");
			private JTextField sNameT = new JTextField(15);

			private JLabel sSurnameL = new JLabel("Surname:");
			private JTextField sSurnameT = new JTextField(15);

			private JLabel sBirthL = new JLabel("Birth date:");
			private JSpinner birthDay = new JSpinner(new SpinnerNumberModel(1, 1, 31, 1));
			private JSpinner birthMonth = new JSpinner(new SpinnerNumberModel(1, 1, 12, 1));
			private JSpinner birthYear = new JSpinner(new SpinnerNumberModel(2021, 1900, 2021, 1));

			private JLabel sGenderL = new JLabel("Gender:");
			private ButtonGroup sGenderGroup = new ButtonGroup();
			private JRadioButton sGenderM = new JRadioButton("Male");
			private JRadioButton sGenderF = new JRadioButton("Female");
			private JRadioButton sGenderN = new JRadioButton("Other");

			private JButton signupB = new JButton("Sign up");

			private JLabel sStayLoggedL = new JLabel("Stay logged:");
			private JCheckBox sStayLoggedB = new JCheckBox();
			
			public LogSignPanel(){
				
				//Grupos y JSpinner
				sGenderGroup.add(sGenderF);
				sGenderGroup.add(sGenderM);
				sGenderGroup.add(sGenderN);
				
				//Asignación de oyentes
				StayLoggedListener lStayLoggedListener = new StayLoggedListener();
				StayLoggedListener sStayLoggedListener = new StayLoggedListener();
				stayLoggedB.addActionListener(lStayLoggedListener);
				sStayLoggedB.addActionListener(sStayLoggedListener);
				
				//Log in panel
				login.setLayout(new GridBagLayout());
				GridBagConstraints c = new GridBagConstraints();
				
				c.fill = GridBagConstraints.HORIZONTAL;
				c.insets = new Insets(20, 10, 0, 10);
				c.gridx = 0;
				c.gridy = 0;
				c.gridwidth = 1;
				c.gridheight = 1;
				login.add(lUserL, c);

				c.gridwidth = 2;
				c.gridx = 1;
				c.gridy = 0;
				login.add(lUserT, c);

				c.gridwidth = 1;
				c.gridx = 0;
				c.gridy = 1;
				login.add(lPasswordL, c);
				
				c.gridwidth = 2;
				c.gridx = 1;
				c.gridy = 1;
				login.add(lPasswordT, c);
				
				c.gridx = 0;
				c.gridy = 2;
				c.gridwidth = 1;
				login.add(stayLoggedL, c);
				
				c.gridx = 1;
				c.gridy = 2;
				login.add(stayLoggedB, c);

				c.gridx = 2;
				c.gridy = 2;
				login.add(loginB, c);
				
				logSignBox.add(login);
				
				//Separator
				logSignBox.add(Box.createVerticalStrut(10));
				logSignSeparator.setForeground(Color.LIGHT_GRAY);
				logSignBox.add(logSignSeparator);
				logSignBox.add(Box.createVerticalStrut(10));
				
				//Sign up panel
				signup.setLayout(new GridBagLayout());
				
				c.gridx = 0;
				c.gridy = 0;
				signup.add(sMailL, c);
				
				c.gridwidth = 3;
				c.gridx = 1;
				c.gridy = 0;
				signup.add(sMailT, c);
				
				c.gridwidth = 1;
				c.gridx = 0;
				c.gridy = 1;
				signup.add(sUserL, c);
				
				c.gridwidth = 3;
				c.gridx = 1;
				c.gridy = 1;
				signup.add(sUserT, c);
				
				c.gridwidth = 1;
				c.gridx = 0;
				c.gridy = 2;
				signup.add(sPasswordL, c);
				
				c.gridwidth = 3;
				c.gridx = 1;
				c.gridy = 2;
				signup.add(sPasswordT, c);
				
				c.gridwidth = 1;
				c.gridx = 0;
				c.gridy = 3;
				signup.add(sNameL, c);
				
				c.gridwidth = 3;
				c.gridx = 1;
				c.gridy = 3;
				signup.add(sNameT, c);
				
				c.gridwidth = 1;
				c.gridx = 0;
				c.gridy = 4;
				signup.add(sSurnameL, c);
				
				c.gridwidth = 3;
				c.gridx = 1;
				c.gridy = 4;
				signup.add(sSurnameT, c);
				
				c.gridwidth = 1;
				c.gridx = 0;
				c.gridy = 5;
				signup.add(sBirthL, c);
				
				c.gridx = 1;
				c.gridy = 5;
				signup.add(birthDay, c);
				
				c.gridx = 2;
				c.gridy = 5;
				signup.add(birthMonth, c);
				
				c.gridx = 3;
				c.gridy = 5;
				signup.add(birthYear, c);
				
				c.gridx = 0;
				c.gridy = 6;
				signup.add(sGenderL, c);
				
				c.gridx = 1;
				c.gridy = 6;
				signup.add(sGenderM, c);
				
				c.gridx = 2;
				c.gridy = 6;
				signup.add(sGenderF, c);
				
				c.gridx = 3;
				c.gridy = 6;
				signup.add(sGenderN, c);
				
				c.gridx = 0;
				c.gridy = 7;
				signup.add(sStayLoggedL, c);

				c.gridx = 1;
				c.gridy = 7;
				signup.add(sStayLoggedB, c);
				
				c.gridwidth = 2;
				c.gridx = 2;
				c.gridy = 7;
				signup.add(signupB, c);
				
				logSignBox.add(signup);
								
				add(logSignBox);
				
				//Setting the fonts
				MainFrame.changeFont(logSignBox, plainFont);

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
