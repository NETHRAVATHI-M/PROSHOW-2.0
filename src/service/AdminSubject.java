package service;

import java.util.Scanner;

import dao.AdminFunctionalityInterfaceImplementation;
import model.Department;
import model.SubjectTable;

public class AdminSubject {
	Scanner sc = new Scanner(System.in);
	AdminFunctionalityInterfaceImplementation adminimpl = new AdminFunctionalityInterfaceImplementation();
	
	SubjectTable s = new SubjectTable();
	public void subjectFunctionality(){
		boolean flag = true;
		while(flag) {
			System.out.println("1.add 2.update 3. delete 4.display");
			int choice = sc.nextInt();
			switch(choice) {
			
			case 1: 
				  adminimpl.addSubject();
				break;
			case 2:
				System.out.println("enter the subject id which you want to update");
				String sub_id = sc.next();
				
				adminimpl.updateSubject(sub_id);
				break;
			case 3:
				System.out.println("enter the subject id which you want to delete");
				String sub_id1 = sc.next();
			
				adminimpl.deleteSubject(sub_id1);
				break;
			case 4:
				
				System.out.println("enter which department subjects you need ");
				String dept_id =  sc.next();
				s.setDepartmentId(dept_id);
				adminimpl.displaySubject(s);

				break;
			case 5:flag= false;
				System.out.println("you exited successfully");
			default: System.out.println("please provide correct input");
			
			}
		}
	
	}
	
	
}
