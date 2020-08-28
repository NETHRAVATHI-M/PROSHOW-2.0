package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import model.Student;
import utility.ConnectionManager;

public class StudentFunctionalityInterfaceImpl implements StudentFunctinalityInterface {

	Scanner sc = new Scanner(System.in);
	Student s = new Student();

	@Override
	public void studentDetails() {
		// TODO Auto-generated method stub
		System.out.println("*********HERE IS YOUR details*********");
		String sql = "select * from student where student_id= ?";
		try {
			Connection con = ConnectionManager.getConnection();
			System.out.println("enter student id");
			String student_id = sc.next();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, student_id);
			ResultSet res = ps.executeQuery();
			if(res.next()==false){
				System.out.println("plase provide correct id");
				
				
			}
			else {
		
				String student_id1 = res.getString("STUDENT_ID");

				String fname = res.getString("FNAME");
				String lname = res.getString("LNAME");
				String email = res.getString("EMAIL");
				String password = res.getString("PASSWORD");
				String deparment = res.getString("D_NUM");

				String phoneNumber = res.getString("PHONE_NUMBER");

				System.out.println("student_id =" + student_id1 + "\tfname=" + fname + "\tlastname=" + lname
						+ "\temail=" + email + "\tpassword= " + password + "\tdepartment=" + deparment
						+ "\tphoneNumber=" + phoneNumber);
			}

		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
		}

	}

	@SuppressWarnings("resource")
	@Override
	public void chooseSoftCores(Student s) {
		// TODO Auto-generated method stub

		Connection connection = null;
		connection = ConnectionManager.getConnection();

		PreparedStatement preparedStatement = null;
		try {
			// SELECT USERID AND PASSWORD FROM STUDENT
			String sql = "select STUDENT_ID,PASSWORD from student WHere STUDENT_ID=?";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, s.getStudent_id());

			ResultSet result = preparedStatement.executeQuery();

			if (result.next()) {
				String name = result.getString("STUDENT_ID");
				String password = result.getString("PASSWORD");
				// LOGIN PROCESS
				if (name.equals(s.getStudent_id()) && password.equals(s.getPassword())) {
					System.out.println("login successfull");
					// ONCE LOGIN ASKS FOR DEPARTMENT ID WHERE STUDENT BELONGS TO
					System.out.println("Please provide you department");
					sc.next();
					String department = sc.next();
					s.setDepartmentId(department);
					String sql1 = "select D_NUM from student where STUDENT_ID=?";
					preparedStatement = connection.prepareStatement(sql1);
					preparedStatement.setString(1, s.getStudent_id());
					ResultSet result1 = preparedStatement.executeQuery();
					while (result1.next()) {
						String depart = result1.getString("D_NUM");
						// VERIFIES WHETHER STUDENT BELONGS TO A PARTICULAR DEPARTMENT
						if (department.equals(depart)) {
							// IS YES STUDENT CAN ENTER THE SUBJECT BELONGS TO HIS/HER DEPARTMENT
							System.out.println("Enter subject id you want to choose ");
							String subId = sc.next();
							s.setSubject_id(subId);
							String sql2 = "select sub_id,DE_ID,SUB_NAME from subject where DE_id =?";
							preparedStatement = connection.prepareStatement(sql2);

							preparedStatement.setString(1, s.getDepartmentId());
							ResultSet result11 = preparedStatement.executeQuery();
							int count = 0;
							while (result11.next()) {

								String subjectid = result11.getString("SUB_ID");
								String depat = result11.getString("DE_ID");
								String sujectname = result11.getString("SUB_NAME");
								// IF ENTERED SUBID AND DEPARTMENT ARE ARE VALID THEN FOLLOW THE BELOW STEPS
								if (subjectid.equals(s.getSubject_id()) && depat.equals(s.getDepartmentId())) {
									count = 1;
									String sql21 = "select * from student_subject";
									preparedStatement = connection.prepareStatement(sql21);
									ResultSet result12 = preparedStatement.executeQuery();
									// IF THESUJECT STUDENT TABLE IS EMPTY IF STUDENT IS THE FIRST PERSON TO CHOOSE
									// THEN THIS CONDITION WORKS
									if (result12.next() == false) {
										String course = "insert into student_subject(STU_ID,Subject_ID)VALUES(?,?)";
										preparedStatement = connection.prepareStatement(course);

										preparedStatement.setString(1, s.getStudent_id());
										preparedStatement.setString(2, s.getSubject_id());
										int res = preparedStatement.executeUpdate();
										if (res == 1) {
											System.out.println("updated successfully");
										}
									}
//IF SOME RECORD ARE THERE WE SHOULD CHECK IF STUDENTS IS TRYING TO CHOOSE SAME SUBJECT AGAIN
									else {
										int count1 = 0;
										while (result12.next()) {
											String studentid1 = result12.getString("stu_ID");
											String subject = result11.getString("SUBject_NAME");
											if (studentid1.equals(s.getStudent_id()) && subject.equals(sujectname)) {
												System.out.println("you have alredy choosen this subject");
												count1 = 1;
												break;
											}

										}
										if (count1 == 0) {
											String course1 = "insert into student_subject(STU_ID,Subject_ID)VALUES(?,?)";
											preparedStatement = connection.prepareStatement(course1);

											preparedStatement.setString(1, s.getStudent_id());
											preparedStatement.setString(2, s.getSubject_id());
											int res = preparedStatement.executeUpdate();
											if (res == 1) {
												System.out.println("updated successfully");
											}

										}
									}
								}

							}
							if (count == 0) {
								System.out.println("please provide subject belongs to our department");
							}

						} else {
							System.out.println("provide correct department id");
						}
					}
				} else {
					System.out.println("please check your credentilas");
				}
			} else {
				System.out.println("Your details are not present in university record");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public void viewSoftcoreSubjects() {
		// TODO Auto-generated method stubstudent_id
		// TODO Auto-generated method stub
		System.out.println("*********HERE IS YOUR details*********");
		String sql = "select sub.SUB_ID,sub.SUB_NAME from SUBJECT sub INNER JOIN student_subject s  ON sub.sub_id = s.SUBJECT_ID where s.STU_ID = ?";
		try {
			Connection con = ConnectionManager.getConnection();
			System.out.println("enter student id");
			String student_id = sc.next();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, student_id);
			ResultSet res = ps.executeQuery();

			while (res.next()) {
				String subject_id = res.getString("SUB_ID");
				String subject_name = res.getString("SUB_NAME");

				System.out.println("subject_id=" + subject_id+"\tSubjectName="+ subject_name );
			}

		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
		}

	}

	@Override
	public void viewMarks() {
		// TODO Auto-generated method stub
		System.out.println("enter your student id to view result");
		String student_id = sc.nextLine();
		Connection con = ConnectionManager.getConnection();
		String sql ="select * from marks where STUDENTF_ID = ?";
		
		
		PreparedStatement ps = null;
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, student_id);
			
			ResultSet res = ps.executeQuery();
			while(res.next()) {
				String student_id1 = res.getString("STUDENTF_ID");
				String subject_id = res.getString("SUBJECT_ID1");
				long marks = res.getLong("MARKS");
				System.out.println("student_id1 ="+ student_id1+"\tsubject_id="+subject_id+"\tmarks="+ marks);
			
		} }catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
	}

}
