package model;

import java.time.LocalDateTime;

public class Attendance {

	private String student_id;
	private LocalDateTime date;
	private String mark;
	
	
	public Attendance(String student_id, LocalDateTime date, String mark) {
		super();
		this.student_id = student_id;
		this.date = date;
		this.mark = mark;
	}


	public String getStudent_id() {
		return student_id;
	}


	public void setStudent_id(String student_id) {
		this.student_id = student_id;
	}


	public LocalDateTime getDate() {
		return date;
	}


	public void setDate(LocalDateTime date) {
		this.date = date;
	}


	public String getMark() {
		return mark;
	}


	public void setMark(String mark) {
		this.mark = mark;
	}


	public Attendance() {
		
	}
}
