package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.GregorianCalendar;
import java.util.Iterator;

import javax.imageio.ImageIO;
import javax.swing.AbstractButton;
import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JSeparator;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;

import internal.Login;
import internal.Signup;
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
			
			private StayLoggedListener lStayLoggedListener;
			private StayLoggedListener sStayLoggedListener;
			
			public LogSignPanel(){
				
				//Grupos y JSpinner
				sGenderGroup.add(sGenderF);
				sGenderGroup.add(sGenderM);
				sGenderGroup.add(sGenderN);
				
				//Asignación de oyentes
				lStayLoggedListener = new StayLoggedListener();
				sStayLoggedListener = new StayLoggedListener();
				stayLoggedB.addActionListener(lStayLoggedListener);
				sStayLoggedB.addActionListener(sStayLoggedListener);
				
				signupB.addActionListener(new ActionListener() {
					
					public void actionPerformed(ActionEvent e) {
						
						Color errorColor = new Color(255, 163, 163);
						
						//Mail check
						boolean hasAt = false;
						boolean validMail = false;
						for(int i = 0; i<sMailT.getText().length(); i++) {
							if (sMailT.getText().charAt(i) == '@') hasAt = true;
							else if (hasAt && sMailT.getText().charAt(i) == '.') validMail = true;
						}
						if (!validMail) {
							sMailT.setBackground(errorColor);
							JOptionPane.showMessageDialog(signup.getRootPane().getRootPane(), "Please enter a valid e-mail.", "Error", JOptionPane.ERROR_MESSAGE);
							return;
						} else {
							sMailT.setBackground(Color.WHITE);
						}
						
						//User check
						boolean validUser = true;
						for (char a:sUserT.getText().toCharArray()) {
							if(a==' ') validUser = false;
						}
						if(sUserT.getText().length()!=0 && validUser) sUserT.setBackground(Color.WHITE);
						else {
							sUserT.setBackground(errorColor);
							JOptionPane.showMessageDialog(signup.getRootPane().getRootPane(), "Please enter a valid username.", "Error", JOptionPane.ERROR_MESSAGE);
							return;
						}
						
						//Password check
						boolean validPassword = true;
						for (char a:sPasswordT.getPassword()) {
							if(a==' ') validPassword = false;
						}
						if(sPasswordT.getPassword().length!=0 && validPassword) sPasswordT.setBackground(Color.WHITE);
						else {
							sPasswordT.setBackground(errorColor);
							JOptionPane.showMessageDialog(signup.getRootPane().getRootPane(), "Please enter a valid password.", "Error", JOptionPane.ERROR_MESSAGE);
							return;
						}
						
						//Name check
						if(sNameT.getText().length()!=0) sNameT.setBackground(Color.WHITE);
						else {
							sNameT.setBackground(errorColor);
							JOptionPane.showMessageDialog(signup.getRootPane().getRootPane(), "Please enter a name.", "Error", JOptionPane.ERROR_MESSAGE);
							return;
						}
						
						//Surname check
						if(sSurnameT.getText().length()!=0) sSurnameT.setBackground(Color.WHITE);
						else {
							sSurnameT.setBackground(errorColor);
							JOptionPane.showMessageDialog(signup.getRootPane().getRootPane(), "Please enter a surname.", "Error", JOptionPane.ERROR_MESSAGE);
							return;
						}
						
						//Birthdate check
						int month = (int) birthMonth.getValue();
						int day = (int) birthDay.getValue();
						int year = (int) birthYear.getValue();
						GregorianCalendar date;

						if((month == 1 && day <= 31) || (month == 2 && day <= 28) || (month == 2 && day <= 29 && year % 4 == 0) || (month == 3 && day <= 31) || (month == 4 && day <= 30) || (month == 5 && day <= 31) || (month == 6 && day <= 30) || (month == 7 && day <= 31) || (month == 8 && day <= 31) || (month == 9 && day <= 30) || (month == 10 && day <= 31) || (month == 11 && day <= 30) || (month == 12 && day <= 31)) date = new GregorianCalendar(year, month, day);
						else {
							JOptionPane.showMessageDialog(signup.getRootPane().getRootPane(), "Please enter a valid date.", "Error", JOptionPane.ERROR_MESSAGE);
							return;
						}

						//Gender check
						String gender = null;
						Iterator<AbstractButton> buttons = sGenderGroup.getElements().asIterator();
						while (buttons.hasNext()) {
							AbstractButton but = (AbstractButton) buttons.next();
							if (but.isSelected()) {
								gender = but.getText();
							}
						}
						if (gender == null) {
							JOptionPane.showMessageDialog(signup.getRootPane().getRootPane(), "Please select a gender.", "Error", JOptionPane.ERROR_MESSAGE);
							return;
						}
						
						Signup.createNewUser(sMailT.getText(), sUserT.getText(), sPasswordT.getPassword(), sNameT.getText(), sSurnameT.getText(), date, gender, sStayLoggedListener.isLogged());
					}
				});
				
				loginB.addActionListener(new ActionListener() {
					
					public void actionPerformed(ActionEvent e) {
						
						Color errorColor = new Color(255, 163, 163);
						
						//User check
						boolean validUser = true;
						for (char a:lUserT.getText().toCharArray()) {
							if(a==' ') validUser = false;
						}
						if(lUserT.getText().length()!=0 && validUser) lUserT.setBackground(Color.WHITE);
						else {
							lUserT.setBackground(errorColor);
							JOptionPane.showMessageDialog(signup.getRootPane().getRootPane(), "Please enter a valid username.", "Error", JOptionPane.ERROR_MESSAGE);
							return;
						}
						
						//Password check
						boolean validPassword = true;
						for (char a:lPasswordT.getPassword()) {
							if(a==' ') validPassword = false;
						}
						if(lPasswordT.getPassword().length!=0 && validPassword) lPasswordT.setBackground(Color.WHITE);
						else {
							lPasswordT.setBackground(errorColor);
							JOptionPane.showMessageDialog(signup.getRootPane().getRootPane(), "Please enter a valid password.", "Error", JOptionPane.ERROR_MESSAGE);
							return;
						}
						
						Login.enter(lUserT.getText(), lPasswordT.getPassword(), lStayLoggedListener.isLogged());
					}
				});
								
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
