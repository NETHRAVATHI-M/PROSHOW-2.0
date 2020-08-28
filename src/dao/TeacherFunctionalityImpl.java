package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

import javax.naming.spi.DirStateFactory.Result;

import model.Teacher;
import utility.ConnectionManager;

public class TeacherFunctionalityImpl implements TeacherFunctionalityInterface {

	Scanner sc = new Scanner(System.in);

	@Override
	public void ViewDetails() {
		// TODO Auto-generated method stub
		System.out.println("ENTER YOUR ID TO SEE YOUR INFORMATION");
		String id = sc.next();

		String sql = "select * from teacher where teacher_id=?";
		try {
			Connection con = ConnectionManager.getConnection();

			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, id);
			ResultSet res = ps.executeQuery();
			if (res.next() == false) {
				System.out.println("please provise coreect id");
			} else {

				
					String teacher_id = res.getString("TEACHER_ID");

					String fname = res.getString("FNAME");
					String lname = res.getString("LNAME");
					String email = res.getString("EMAIL");
					String password = res.getString("PASSWORD");
					String deparment = res.getString("D_NUM");
					String sub_id = res.getString("S_ID");
					String phoneNumber = res.getString("PHONE_NUMBER");

					System.out.println("teacher_id =" + teacher_id + "\tfname=" + fname + "\tlastname=" + lname
							+ "\temail=" + email + "\tpassword= " + password + "\tdepartment=" + deparment
							+ "\tsub_id= " + sub_id + "\tphoneNumber=" + phoneNumber);
				}

			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
		}

	}

	@SuppressWarnings("resource")
	@Override
	public void updateMarks() {
		// TODO Auto-generated method stub
		System.out.println("please conform your credentilas before you update marks of your specified student");
		System.out.println("********************************************************");
		Teacher t = new Teacher();
		System.out.println("**********enter teacher id*********");
		String teacherId = sc.next();
	t.setTeacherId(teacherId);
	System.out.println("*********enter password****************");
	String password = sc.next();
	t.setPassword(password);
	String sql = "select * from teacher";
	Connection con = null;
	try {
		con = ConnectionManager.getConnection();

		PreparedStatement ps = con.prepareStatement(sql);
		
		ResultSet res = ps.executeQuery();
		int count =0;
		while(res.next()) {
			String teacher = res.getString("teacher_id");
			String password1= res.getString("password");
			if(teacher.equals(t.getTeacherId()) && password1.equals(t.getPassword())) {
				System.out.println("successfully vrified");
				count =1;
				System.out.println("*********enter your department ***********");
				String department = sc.next();
				System.out.println("enter subject");
				String subject = sc.next();
				String depart = res.getString("D_NUM");
				String sid = res.getString("S_ID");
				int count1=0;
				if(depart.equals(department) && sid.equals(subject)) {
					count1=1;
					System.out.println("enter student is you want to update");
					String Student_ID = sc.next();
					System.out.println("Enter subject id");
					String subject1 = sc.next();
					
					String sql1 ="SELECT * FROM  student_subject";
					ps = con.prepareStatement(sql1);
					ResultSet rs = ps.executeQuery();
					int count2 = 0;
					
					while(rs.next()) {
						String student_id = rs.getString("STU_ID");
						String sub_id = rs.getString("SUBJECT_ID");
						if(student_id.equals(Student_ID) && sub_id.equals(subject1) && sub_id.equals(subject)) {
							count2=1;
							System.out.println("enter the marks you want to update");
							int marks = sc.nextInt();
							String sql4 = "select * from marks";
							ps = con.prepareStatement(sql4);
							ResultSet req = ps.executeQuery();
							if(req.next()==false) {
								String sql3 = "insert into marks(STUDENTF_ID ,SUBJECT_ID1,MARKS)VALUES(?,?,?)";
								
								ps = con.prepareStatement(sql3);
								ps.setString(1,Student_ID);
								ps.setString(2,sub_id );
								ps.setLong(3,marks );
								int  r = ps.executeUpdate();
								if(r==1) {
									System.out.println("insertted succefully");
								}
								else {
									System.out.println("please check your details");
								}
							}
							else {
								String sql5 = "select * from marks";
								ps = con.prepareStatement(sql5);
								ResultSet rq = ps.executeQuery();
								while(rq.next()) {
									String student_id1 = rs.getString("STU_ID");
									String sub_id1 = rs.getString("SUBJECT_ID");
									long marks1 =  rs.getLong("MARKS");
									if(student_id1.equals(student_id1) && sub_id1.equals(subject1) && marks==marks1) {
										System.out.println("these details are alreay present !!!!!!!! no need to inseert");
										
									}
									else {
										String sql3 = "insert into marks(STUDENTF_ID ,SUBJECT_ID1,MARKS)VALUES(?,?,?)";
										System.out.println("enter the marks you want to update");
										int marks11 = sc.nextInt();
										ps = con.prepareStatement(sql3);
										ps.setString(1,Student_ID);
										ps.setString(2,sub_id );
										ps.setLong(3,marks11 );
										int r = ps.executeUpdate();
										if(r==1) {
											System.out.println("inserted successfully");
										}
										else {
											System.out.println("verify your details");
										}
									}
								}
							
							}
						}
						
						
					}
					if(count2==0) {
						System.out.println("enter correct student and suject id");
					}
				}
				if(count1==0) {
					System.out.println("provide your proper deparment and subject id");
				}
			}
			
		}
		if(count==0) {
			System.out.println("please provide your credentilas correctly");
			
		}

		

	} catch (Exception e) {
		// TODO: handle exception
		System.out.println(e);
	}

	}

}
