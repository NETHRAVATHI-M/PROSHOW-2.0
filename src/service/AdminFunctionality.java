package service;

import java.util.Scanner;

import dao.AdminFunctionalityInterfaceImplementation;
import model.Department;
import model.Student;
import model.SubjectTable;
import model.Teacher;

public class AdminFunctionality {

	AdminDepartmentFunctionality admin = new AdminDepartmentFunctionality();
	AdminSubject adminsub = new AdminSubject();
	AdminTeacherService adminTeacher = new AdminTeacherService();
	AdminStudent adminstudent = new AdminStudent();

	public void addAdminFunctionality() {
		Scanner sc = new Scanner(System.in);
		boolean flag = true;
		while (flag) {
			System.out.println(
					"1 perform department operations\t 2. perform subject operations \t 3. teacher operations\t"
							+ " 4.student operations\n 5.exit ");

			int choice = sc.nextInt();
			switch (choice) {
			case 1:
				admin.departmentFunctionality();
				break;

			case 2:
				 adminsub.subjectFunctionality();
				break;
			case 3:
				System.out.println("Enter teachers with you");
				adminTeacher.teacherFunctionality();

				break;
			case 4:
				adminstudent.studentFunctionality();
				break;

			case 5:flag=false;
					System.out.println("exited successfully");
				break;
			default:System.out.println("please enter correct choice");

			}
		}
	}
}
