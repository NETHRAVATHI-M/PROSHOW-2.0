package model;

import java.time.LocalDate;

public class CommonDetails {
	private String emailId;
	private String password;
	private LocalDate dob;
	private String phoneNumber;
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public LocalDate getDob() {
		return dob;
	}
	public void setDob(LocalDate localDate) {
		this.dob = localDate;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public CommonDetails(String emailId, String password, LocalDate dob, String phoneNumber) {
		super();
		this.emailId = emailId;
		this.password = password;
		this.dob = dob;
		this.phoneNumber = phoneNumber;
	}
	public CommonDetails(String password) {
		this.password = password;
	}
	public CommonDetails() {
		super();
		// TODO Auto-generated constructor stub
	}
	

}
