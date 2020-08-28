package controller;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Scanner;

import dao.TeacherLoginConfrom;
import service.AdminService;
import service.Studentfunctionality;
import service.TeacherFunctionality;
import utility.ConnectionManager;

public class Main {
	public static void main(String args[]) throws ClassNotFoundException, IOException, SQLException {
		Scanner sc = new Scanner(System.in);
		TeacherFunctionality teacher = new TeacherFunctionality();
		
		System.out.println("Welcome to University Management System");
		boolean flag= true;
		while(flag) {
			System.out.println(" choose 1.  for Admin \n 2.  for Teacher\n "+ "3. for students\n  "+ "4. exit");
			int choice = sc.nextInt();
			switch(choice) {
			case 1:AdminService ad = new AdminService();
					ad.adminFunctionality();
					break;
			case 2: TeacherLoginConfrom tea =  new TeacherLoginConfrom();
						boolean b = tea.teacherLogindeails();
					if(b) {
						teacher.teacherFunctionality();
					}
					else {
						System.out.println("enter correct teacherId and password");
					}
					
					break;
					
			case 3:Studentfunctionality student  = new Studentfunctionality();
					student.StudentFunctionaly();
		
					break;
			case 4: flag=false;
					System.out.println("you exited successfully");
					break;
			default:System.out.println("*******ENTER CORRECT CHOICE******");
				
			
			}
			
		}
		
		if(ConnectionManager.getConnection()!=null) {
			System.out.println("Connection established");
		}
		else {
			System.out.println("connection not established");
		}
		
	}

	
}
