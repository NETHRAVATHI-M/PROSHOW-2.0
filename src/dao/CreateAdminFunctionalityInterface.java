package dao;

import javax.security.auth.Subject;

import model.Department;
import model.Student;
import model.Teacher;

public interface CreateAdminFunctionalityInterface {
	
	public void addDeparment();
	void updateDepartment(Department d);
	void deleteDepartment(Department d);
	public void displayDepartment();
	
	public void addSubject();
	public void updateSubject(String sublect_id);
	public void deleteSubject(String subject_id);

	void addTeacher(Teacher t);
	public void deleteTeacher(String teacher_id);
	public void updateTeacher(String teacher_id);
	public void displayTeacher();
	public void displayTeacherById(String teacher_id);
	
	void addStudent(Student s);
	public void deleteStudent(String student_id);
	public void updateStudent(String student_id);
	public void displayStudent();
	public void displayStudentById(String student_id);
	
	
	void displaySubject(model.SubjectTable s);

	
	
	

	
	
}
