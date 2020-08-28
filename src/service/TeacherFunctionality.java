package service;

import java.util.Scanner;

import dao.TeacherFunctionalityImpl;

public class TeacherFunctionality {
	
	public void teacherFunctionality() {
		System.out.println("*****welcome to student management system***********");
		
		boolean flag = true;
		TeacherFunctionalityImpl teacher = new TeacherFunctionalityImpl();
		Scanner sc = new Scanner(System.in);
		while(flag) {
			System.out.println("1.view details  2. update Marks 3. exit ");
			int choice = sc.nextInt();
			switch(choice) {
			case 1:teacher.ViewDetails();
					break;
			case 2:teacher.updateMarks();
					break;
			case 3:flag = false;
					break;
			default: System.out.println("please enter a correct choice");
				
				
			}
			
		}
		sc.close();
	}

}
