package internal;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class Signup {

	public static void createNewUser(String email, String user, char[] password, String name, String surname, GregorianCalendar birthdate, String gender, boolean stayLogged) {
		System.out.println(email);
		System.out.println(user);
		System.out.println(password);
		System.out.println(name);
		System.out.println(surname);
		System.out.println(birthdate.get(Calendar.DAY_OF_MONTH) + ", " + birthdate.get(Calendar.MONTH) + ", " + birthdate.get(Calendar.YEAR));
		System.out.println(gender);
		System.out.println(stayLogged);
	}
	
}
