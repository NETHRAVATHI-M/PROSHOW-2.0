package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

import model.Teacher;
import utility.ConnectionManager;

public class TeacherLoginConfrom {
	Scanner sc = new Scanner(System.in);
	
	public boolean teacherLogindeails() {
		Teacher t = new Teacher();
		System.out.println("welcome");
		System.out.println("enter teacher id");
		String teacherId = sc.next();
	t.setTeacherId(teacherId);
	System.out.println("enter password");
	String password = sc.next();
	t.setPassword(password);
	String sql = "select * from teacher";
	Connection con = null;
	try {
		con = ConnectionManager.getConnection();

		PreparedStatement ps = con.prepareStatement(sql);
		
		ResultSet res = ps.executeQuery();
		while(res.next()) {
			String teacher = res.getString("teacher_id");
			String password1= res.getString("password");
			if(teacher.equals(t.getTeacherId()) && password1.equals(t.getPassword())) {
				System.out.println("login succcess");
				return true;
			}
			
		}
		

		

	} catch (Exception e) {
		// TODO: handle exception
		System.out.println(e);
	}
		return false;
		
	}
	

}
