package service;

import java.util.Scanner;

import dao.AdminFunctionalityInterfaceImplementation;
import model.Department;

public class AdminDepartmentFunctionality {
	Scanner sc = new Scanner(System.in);
	AdminFunctionalityInterfaceImplementation adminimpl = new AdminFunctionalityInterfaceImplementation();
	Department d = new Department();
	
	public void departmentFunctionality(){
		boolean flag = true;
		while(flag) {
			System.out.println("1.add 2.update 3. delete 4.display 5.exit ");
			int choice = sc.nextInt();
			switch(choice) {
			
			case 1: 
				  adminimpl.addDeparment();
				break;
			case 2:
				System.out.println("enter the department id which you want to update");
				String department_id = sc.next();
				d.setDepartmentId(department_id);
				adminimpl.updateDepartment(d);
				break;
			case 3:
				System.out.println("enter the department id which you want to delete");
				String department_id1 = sc.next();
				d.setDepartmentId(department_id1);
				adminimpl.deleteDepartment(d);
				break;
			case 4:
				System.out.println("************department details are************\n");
				adminimpl.displayDepartment();

				break;
			case 5:flag= false;
				System.out.println("you exited successfully");
			default: System.out.println("please provide correct input");
			
			}
		}
	
	}
}
