package model;

public class Teacher extends Department{

		public Teacher() {
		super();
		// TODO Auto-generated constructor stub
	}
		public Teacher(String password) {
		super(password);
		// TODO Auto-generated constructor stub
	}
		// TODO Auto-generated constructor stub
	
	private String teacherId;
	private String Fname;
	private String Lname;
	
	
	
	
	
	public String getFname() {
		return Fname;
	}
	public void setFname(String fname) {
		Fname = fname;
	}
	public String getLname() {
		return Lname;
	}
	public void setLname(String lname) {
		Lname = lname;
	}


	private String subject_id;
	
	
	
	
	public String getTeacherId() {
		return teacherId;
	}
	public void setTeacherId(String teacherId) {
		this.teacherId = teacherId;
	}
	
	public String getSubject_id() {
		return subject_id;
	}
	public void setSubject_id(String subject_id) {
		this.subject_id = subject_id;
	}

	
		
}
