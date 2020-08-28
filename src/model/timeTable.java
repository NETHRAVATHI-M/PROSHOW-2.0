package model;

import java.time.LocalDateTime;

public class timeTable {

	private String subject_code;
	private LocalDateTime date;
	public String getSubject_code() {
		return subject_code;
	}
	public void setSubject_code(String subject_code) {
		this.subject_code = subject_code;
	}
	public LocalDateTime getDate() {
		return date;
	}
	public void setDate(LocalDateTime date) {
		this.date = date;
	}
	public timeTable(String subject_code, LocalDateTime date) {
		super();
		this.subject_code = subject_code;
		this.date = date;
	}
	public timeTable() {
		
	}
}
