package model;

public class Admin extends CommonDetails {

	public Admin(String username, String password) {
		super(password);
		this.username = username;
		// TODO Auto-generated constructor stub
	}

	private String username;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

}
