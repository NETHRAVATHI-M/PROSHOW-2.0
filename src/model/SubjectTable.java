package model;

public class SubjectTable extends Department{

	private String subject_id;
	private String subjectName;
	public String getSubject_id() {
		return subject_id;
	}
	public void setSubject_id(String subject_id) {
		this.subject_id = subject_id;
	}
	public String getSubjectName() {
		return subjectName;
	}
	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}
	
	public SubjectTable() {
		// TODO Auto-generated constructor stub
	}
}

