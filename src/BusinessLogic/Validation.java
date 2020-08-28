package BusinessLogic;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validation {

	Scanner s = new Scanner(System.in);

	public boolean emailValidation(String email) {
		// EMAIL CAN START WITH LETTERS,DIGIS FOLLOWED BY @ SYMBOL FOLLOWED BY ANYTHING
		Pattern p = Pattern.compile("^[A-Za-z 0-9+_.]+@(.+)$");
		Matcher m = p.matcher(email);
		if (m.matches()) {
			return true;
		} else {
			return false;
		}

	}

	// USER ID VALIDATION
	// IT SHOULD STARTS WITH UPPERCASE LETTERS FOLLOEWD BY - SYMBOLS AND 4 DIGITS
	// WHCIH MAY INCLUDE FROM 0-9
	public boolean idValidation1(String Id) {
		String regex = "[A-Z]{2}-[0-9]{4}";
		return Id.matches(regex);

	}

	// phone number validation for student and teacher

	public boolean phoneNumberValidation(String Phone) {
		String regex = "(\\+91(-)?|91(-)?|0(-)?)?(9)[0-9]{9}";
		// the number can start with 91- or +91- or 0 but which is exceptional
		// it should start with 9 then followed by 9 digits[0-9]
		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher(Phone);
		if (m.matches()) {
			return true;
		}
		// check if given string is empty
		else if (m.equals(null)) {
			return false;
		} else {
			return false;
		}

	}

	// user id validation for teacher and studnet

	// password Validation for student and teacher
	public boolean passwordValidation(String password) {
		boolean result = false;
		try {
			// Regex to check valid password.
			// it can starts with zero or more number of digits
			// followed by zero or more character followed by special characters followerd
			// by space or $
			// the length can be 8- 20 characters
			String regex = "^(?=.*[0-9])" + "(?=.*[a-z])(?=.*[A-Z])" + "(?=.*[@#$%^&+=])" + "(?=\\S+$).{8,20}$";

			// Compile the ReGex
			Pattern p = Pattern.compile(regex);
			Matcher m = p.matcher(password);
			if (m.matches()) {
				result = true;
			}

		} catch (Exception e) {
			System.out.println("Your given input doesnot match regex");
		}
		return result;
	}

	// UserName validation
	private String username;

	public boolean usernameValidation(String username) {
		// this should starrt with uppercase letter or lower case
		// the length should be off size 6
		Pattern p = Pattern.compile("^[A-Za-z]{6}$");
		Matcher m = p.matcher(username);
		if (m.matches()) {
			return true;
		} else {
			return false;
		}

	}

}
