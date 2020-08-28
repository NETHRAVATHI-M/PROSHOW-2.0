package service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import dao.AdminFunctionalityInterfaceImplementation;
import model.Teacher;

public class AdminTeacherService {
	Scanner sc = new Scanner(System.in);
	Teacher t = new Teacher();

	AdminFunctionalityInterfaceImplementation adminimpl = new AdminFunctionalityInterfaceImplementation();

	public void teacherFunctionality() {
		boolean flag = true;
		while (flag) {
			System.out.println("1.add 2.update 3. delete 4.display 5.display_id 6.exit");
			int choice = sc.nextInt();
			switch (choice) {

			case 1:
				
				adminimpl.addTeacher(t);
				break;
			case 2:
				System.out.println("enter the teacher_id1 which you want to update");
				String teacher_id1 = sc.next();

				adminimpl.updateTeacher(teacher_id1);
				break;
			case 3:
				System.out.println("enter the teacher_id which you want to delete");
				String teacher_id11 = sc.next();

				adminimpl.deleteTeacher(teacher_id11);
				break;
			case 4:
				System.out.println("HERE ARE TEACHRES DETAILS");
				adminimpl.displayTeacher();

				break;
			case 5:System.out.println("enter teacher id of teacher you want to see");
					String t_id = sc.next();
					adminimpl.displayTeacherById(t_id);
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
