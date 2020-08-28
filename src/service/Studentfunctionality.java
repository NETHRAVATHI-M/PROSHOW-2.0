package service;

import java.util.Scanner;

import dao.StudentFunctionalityInterfaceImpl;
import model.Student;

public class Studentfunctionality {

	Scanner sc = new Scanner(System.in);
	StudentFunctionalityInterfaceImpl student = new StudentFunctionalityInterfaceImpl();
	Student s = new Student();
	public void StudentFunctionaly() {
		System.out.println("welcome student");
		boolean flag = true;
		while (flag) {

			System.out.println("enter 1. to choose softcores 2.to viewyour details  3. view softcores"
					+ "4 .view MARKS 5.exit");
			int choice = sc.nextInt();
			switch (choice) {
			case 1:
				System.out.println("Please login to your account");
				System.out.println("enter Your studen id");
				String student_id = sc.next();
				String student_password = sc.next();
				s.setStudent_id(student_id);
				System.out.println("Enter password");
				s.setPassword(student_password);
				student.chooseSoftCores(s);
				break;
			case 2:
				student.studentDetails();
				break;
			case 3:
				student.viewSoftcoreSubjects();
				break;
			case 4:student.viewMarks();
				
				break;
			case 5:
				flag = false;
				break;
			default:
				System.out.println("invalid choice");
			}
		}

	}

}
