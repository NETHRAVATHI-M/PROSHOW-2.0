package service;

import java.util.Scanner;

import dao.AdminFunctionalityInterfaceImplementation;
import model.Student;

public class AdminStudent {
	Scanner sc = new Scanner(System.in);
	Student s = new Student();
	AdminFunctionalityInterfaceImplementation adminimpl = new AdminFunctionalityInterfaceImplementation();

	public void studentFunctionality() {
		boolean flag = true;
		while (flag) {
			System.out.println("1.add 2.update 3. delete 4.display 5.display_id 6.exit");
			int choice = sc.nextInt();
			switch (choice) {

			case 1:
				
				adminimpl.addStudent(s);
				break;
			case 2:
				System.out.println("enter the sstudent_id1 which you want to update");
				String student_id1 = sc.next();

				adminimpl.updateStudent(student_id1);
				break;
			case 3:
				System.out.println("enter the student id which you want to delete");
				String student11 = sc.next();

				adminimpl.deleteStudent(student11);
				break;
			case 4:
				System.out.println("HERE ARE students DETAILS");
				adminimpl.displayStudent();

				break;
			case 5:System.out.println("enter student id  you want to see");
					String student_id11 = sc.next();
					adminimpl.displayStudentById(student_id11);
					break;
			case 6:flag=false;
					System.out.println("you exited successfully");
					break;
			default:
				System.out.println("please provide correct input");

			}
		}

	}

}
