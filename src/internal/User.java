package internal;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class User implements Serializable {

	public static final int WRONG_USER = 0;
	public static final int WRONG_PASSWORD = 1;
	public static final int LOGIN = 2;
	private static final long serialVersionUID = 1L;
	
	private String email, username, password, name, surname, gender;
	private GregorianCalendar birthDate, signupDate;
	private boolean stayLogged;
	private int id;
	private static int nextId = 0;
	
	public final static File userStorage = new File("files"+File.separator+"users");
	private File userDirectory;

	private User(String email, String username, char[] password, String name, String surname, GregorianCalendar birthDate, String gender, boolean stayLogged) {
		this.email = email;
		this.username = username;
		this.password = new String(password);
		this.name = name;
		this.surname = surname;
		this.gender = gender;
		this.birthDate = birthDate;
		this.stayLogged = stayLogged;
		signupDate = new GregorianCalendar();
		id = nextId;
		nextId++;
		userDirectory = new File(userStorage, username);
	}

	public static User signup(String email, String username, char[] password, String name, String surname, GregorianCalendar birthDate, String gender, boolean stayLogged) {
		if ((new File(userStorage, username)).exists()) {
			return null;
		} else {
			
			User newUser = new User(email, username, password, name, surname, birthDate, gender, stayLogged);
			newUser.userDirectory.mkdir();
			(new File(newUser.userDirectory, "books")).mkdir();
			
			User.store(newUser);
			User.login(username, password, stayLogged);
			return newUser;
		}
	}
	
	public static User login(String user, char[] password, boolean remember) {
		
		for (String folder: userStorage.list()) {
			if (folder.equals(user)) {
				try {
					ObjectInputStream storage = new ObjectInputStream(new FileInputStream(new File("files"+File.separator+"users"+File.separator+user+File.separator+user+".user")));
					User checkUser = (User) storage.readObject();
					
					if (checkUser.password.equals(new String(password))) {
						
						//New window, correct log in...
						if (remember) {
							for (String userName: userStorage.list()) {
								try {
									ObjectInputStream storage2 = new ObjectInputStream(new FileInputStream(new File("files"+File.separator+"users"+File.separator+userName+File.separator+userName+".user")));
									User checkUser2 = (User) storage2.readObject();
									checkUser2.stayLogged = false;
									User.store(checkUser2);
								} catch (IOException e) {
									e.printStackTrace();
								}
							}
							checkUser.stayLogged = true;
							User.store(checkUser);
						} else {
							checkUser.stayLogged = false;
							User.store(checkUser);
						}
						return checkUser;
					}
					
				} catch (IOException e) {
					e.printStackTrace();
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}
			}
		}
		return null;
	}
	
	public static void store(User user) {
		
		String storeFile = user.username + ".user";
		try {
			ObjectOutputStream storage = new ObjectOutputStream(new FileOutputStream(new File(user.userDirectory, storeFile)));
			storage.writeObject(user);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public boolean getStayLogged() {
		return stayLogged;
	}
	public String getUsername() {
		return username;
	}
	public String getPassword() {
		return password;
	}
	
	public String toString() {
		
		return email + ", " + username + ", " + password + ", " + name + ", " + surname + ", " + birthDate.get(Calendar.DAY_OF_MONTH) + ", " + birthDate.get(Calendar.MONTH) + ", " + birthDate.get(Calendar.YEAR) + ", " + gender + ", " + stayLogged + ", " + signupDate + ", " + id;
	}
}
