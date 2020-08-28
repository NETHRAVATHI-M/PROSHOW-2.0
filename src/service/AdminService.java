package service;

import java.util.Scanner;

import dao.CreateLoginAdminInterfaceImplementation;
import model.Admin;

public class AdminService {

	Scanner sc = new Scanner(System.in);
	CreateLoginAdminInterfaceImplementation adminCreate = new CreateLoginAdminInterfaceImplementation();
	public void adminFunctionality() {
	boolean flag= true;
	while(flag) {
		System.out.println(" choose 1. create account\n 2. login\n "
				+ "3. update admin details\n  "
				+ "4.delete admin details\n 5. exit");
		int choice = sc.nextInt();
		switch(choice) {
		case 1://Admin admin = new Admin(); //CREATING ADMIN ACCOUNT IF NOT EXIST
				System.out.println("enter username");
				String username = sc.next();
				//admin.setUsername(username);
				System.out.println("enter password");
				String password =sc.next();
				//admin.setPassword(password);
				Admin admin1 = new Admin(username, password);
				
				adminCreate.createAdmin(admin1);
				break;
		case 2: System.out.println("enter username");//ADMIN TRYING TO LOGIN
				String username1 = sc.next();
				
				System.out.println("enter password");
				String password1 =sc.next();
				Admin admin2 = new Admin(username1,password1);
				boolean result =adminCreate.loginAdmin(admin2);
				if(result==true) {
	
					AdminFunctionality admin = new AdminFunctionality();
					admin.addAdminFunctionality();
					
				}
				
				break;
				
		case 3:adminCreate.updateAdmin();
				break;
		case 4: adminCreate.delete();
				break;
		case 5: flag= false;
				break;
		default:System.out.println("please enter right choice");
		}
		
		}
		
	}
}
