package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import javax.naming.spi.DirStateFactory.Result;

import model.Department;
import model.Student;
import model.SubjectTable;
import model.Teacher;
import utility.ConnectionManager;

public class AdminFunctionalityInterfaceImplementation implements CreateAdminFunctionalityInterface {
	Department depart = new Department();
	Scanner sc = new Scanner(System.in);
	Student st = new Student();

	@Override
	public void addDeparment() {
		// TODO Auto-generated method stub
		System.out.println("\n**********add department  now********\n");
		Department depart = new Department();
		int result = 0;
		Connection connection = null;
		connection = ConnectionManager.getConnection();
		int count = 0;
		PreparedStatement preparedStatement = null;
		try {
			System.out.println("******enter depatment id************");
			String depart_id = sc.next();
			depart.setDepartmentId(depart_id);
			System.out.println("**********enter department name********");
			String departName = sc.next();
			depart.setDepartmentName(departName);
			String sql1 = "select * from uni_department";
			preparedStatement = connection.prepareStatement(sql1);
			ResultSet res = preparedStatement.executeQuery();
			while (res.next()) {
				String departId = res.getString("depart_id");
				String departName11 = res.getString("DEPT_NAME");
				if (depart.getDepartmentId().equals(departId) || depart.getDepartmentName().equals(departName11)) {
					System.out.println("********department id or name already present***********");
					count = 1;
				}
			}
			if (count == 0) {
				String sql = "insert into uni_department(depart_ID,dept_NAME)values(?,?)";
				preparedStatement = connection.prepareStatement(sql);
				preparedStatement.setString(1, depart.getDepartmentId());
				preparedStatement.setString(2, depart.getDepartmentName());
				result = preparedStatement.executeUpdate();

				if (result == 1) {
					System.out.println("**********added auccessfully************");
				} else {
					System.out.println(
							"***********Admin details are already entered. please do login to perform functionaliy***********");
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public void updateDepartment(Department d) {
		// TODO Auto-generated method stub

		System.out.println("****please enter the department name which you want to update*********");
		String deparmet_name = sc.next();
		d.setDepartmentName(deparmet_name);

		int count = 0;

		try {
			Connection con = ConnectionManager.getConnection();
			PreparedStatement ps = null;
			String sql1 = "select * from uni_department";
			ps = con.prepareStatement(sql1);
			ResultSet res = ps.executeQuery();
			while (res.next()) {
				String departId = res.getString("depart_id");
				String departName11 = res.getString("DEPT_NAME");
				if (d.getDepartmentId().equals(departId) || d.getDepartmentName().equals(departName11)) {
					System.out.println(
							"*********DEPARTMENT NAME OR ID WITH THIS NAME ALREADY EXIST!!!.. TRY WITH NEW ONE*******");
					count = 1;
				}
			}

			if (count == 0) {

				String sql = "update uni_department set dept_NAME= ? where depart_ID= ?";
				ps = con.prepareStatement(sql);
				ps.setString(1, d.getDepartmentName());
				ps.setString(2, d.getDepartmentId());

				int res1 = ps.executeUpdate();

				if (res1 == 1) {
					System.out.println("**********department updated successfully***********");
				}

				else {
					System.out.println("******please check if department id is present**********");
				}
				con.close();

			}
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
		}

	}

	@Override
	public void deleteDepartment(Department d) {
		// TODO Auto-generated method stub
		String sql = "delete from UNI_DEPARTMENT where DEPART_ID =?";
		try {
			Connection con = ConnectionManager.getConnection();

			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, d.getDepartmentId());

			int res = ps.executeUpdate();

			if (res == 1) {
				System.out.println("************department deleted successfully!!!!!!!!*****");
			}

			else {
				System.out.println("***********please check if department id is present***********");
			}

		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
		}

	}

	@Override
	public void displayDepartment() {
		// TODO Auto-generated method stub
		String sql = "**********select * from UNI_DEPARTMENT***********";
		try {
			Connection con = ConnectionManager.getConnection();

			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet res = ps.executeQuery();

			while (res.next()) {
				String depart_id = res.getString("DEPART_ID");
				String depart_nmae = res.getString("dept_NAME");
				System.out.println("departmentId =" + depart_id + "\tDeparment Name= " + depart_nmae);
			}

		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
		}

	}

	@SuppressWarnings("resource")
	@Override
	public void addSubject() {
		// TODO Auto-generated method stub
		System.out.println("*************add subject  now************");

		int result = 0;
		Connection connection = null;
		connection = ConnectionManager.getConnection();

		PreparedStatement preparedStatement = null;
		try {
			System.out.println("*********enter subject id**********");
			String sub_id = sc.next();

			System.out.println("*********enter subject name**********");
			String subjectName = sc.next();
			String sql3 = "select * from subject";
			preparedStatement = connection.prepareStatement(sql3);
			ResultSet res = preparedStatement.executeQuery();
			int count12 = 0;
			if (res.next() == false) {

				System.out.println("*********enter department id*********");

				String dept_id = sc.next();
				String sql1 = "select * from uni_department";
				preparedStatement = connection.prepareStatement(sql1);
				ResultSet rs = preparedStatement.executeQuery();
				int count = 0;
				while (rs.next()) {
					String depart = rs.getString("DEPART_ID");
					if (depart.equals(dept_id)) {
						count = 1;
						String sql = "insert into subject(sub_ID,sub_NAME,DE_ID)values(?,?,?)";
						preparedStatement = connection.prepareStatement(sql);
						preparedStatement.setString(1, sub_id);
						preparedStatement.setString(2, subjectName);
						preparedStatement.setString(3, dept_id);
						result = preparedStatement.executeUpdate();

						if (result == 1) {
							System.out.println("********SUbject added auccessfully**********");
						} else {
							System.out.println("***********check your details**********");
						}
					}
				}
				if (count == 0) {
					System.out.println("********Department id Not found******");
				}
			} else {
				String sql4 = "select * from subject";
				preparedStatement = connection.prepareStatement(sql4);
				ResultSet res1 = preparedStatement.executeQuery();
				while (res1.next()) {
					String subid = res1.getString("SUB_ID");
					String sub_name = res1.getString("SUB_NAME");
					if (subid.equals(sub_id) && sub_name.equals(subjectName)) {
						System.out.println("**********subject id or name alreay existes***************");
						count12 = 1;

					}
					if (count12 == 0) {

						System.out.println("*********enter department id*********");

						String dept_id = sc.next();
						String sql1 = "select * from uni_department";
						preparedStatement = connection.prepareStatement(sql1);
						ResultSet rs = preparedStatement.executeQuery();
						int count = 0;
						while (rs.next()) {
							String depart = rs.getString("DEPART_ID");
							if (depart.equals(dept_id)) {
								count = 1;
								String sql = "insert into subject(sub_ID,sub_NAME,DE_ID)values(?,?,?)";
								preparedStatement = connection.prepareStatement(sql);
								preparedStatement.setString(1, sub_id);
								preparedStatement.setString(2, subjectName);
								preparedStatement.setString(3, dept_id);
								result = preparedStatement.executeUpdate();

								if (result == 1) {
									System.out.println("********SUbject added auccessfully**********");
								} else {
									System.out.println("***********check your details**********");
								}
							}
						}
						if (count == 0) {
							System.out.println("*************department id and subject is not matching************");
						}

					}
				}

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@SuppressWarnings("resource")
	@Override
	public void updateSubject(String subject_id) {
		// TODO Auto-generated method stub
		SubjectTable s = new SubjectTable();
		try {

			Connection con = ConnectionManager.getConnection();
			String sql = "select * from subject";
			PreparedStatement preparedStatement = con.prepareStatement(sql);
			ResultSet res = preparedStatement.executeQuery();
			int flag = 0;
			while (res.next()) {
				String Sub_id = res.getString("SUB_ID");
				if (Sub_id.equals(subject_id)) {
					flag = 1;
					System.out.println("*********entered subject is present *********");
					System.out.println("*********enter subject id**********\n");
					String sub_id = sc.next();
					s.setSubject_id(sub_id);
					System.out.println("*********enter subject name**********\n");
					String subjectName = sc.next();
					s.setSubjectName(subjectName);
					preparedStatement = con.prepareStatement(sql);
					ResultSet res1 = preparedStatement.executeQuery();
					int count12 = 0;
					if (res1.next() == false) {
						System.out.println("*******No records are there to Update**********");
					} else {
						preparedStatement = con.prepareStatement(sql);
						ResultSet res2 = preparedStatement.executeQuery();
						while (res2.next()) {
							String subid = res2.getString("SUB_ID");
							String sub_name = res2.getString("SUB_NAME");
							if (subid.equals(sub_id) || sub_name.equals(subjectName)) {
								System.out.println("**********subject id or name alreay existes***************");
								count12 = 1;

							}
						}
						if (count12 == 0) {

							System.out.println("*********enter department id*********");

							String dept_id = sc.next();
							String sql1 = "select * from UNI_DEPARTMENT";
							preparedStatement = con.prepareStatement(sql1);
							ResultSet rs = preparedStatement.executeQuery();
							int count = 0;
							while (rs.next()) {
								String depart = rs.getString("DEPART_ID");
								if (depart.equals(dept_id)) {
									count = 1;
									String sql2 = "update subject set SUB_NAME =? where sub_ID= ?";
									PreparedStatement preparedStatement1 = con.prepareStatement(sql2);

									preparedStatement1.setString(1, s.getSubjectName());
									preparedStatement1.setString(2, subject_id);

									int res11 = preparedStatement1.executeUpdate();

									if (res11 == 1) {
										System.out.println("**************subject updated successfully***********");
									}

									else {
										System.out.println("*********please check if subjectid is present*********");
									}
								}
								if (count == 0) {
									System.out.println("*******enter correct department id*****");
								}
							}
							if (count12 == 1) {
								System.out.println("******subjects with name alreay present cant update**********");
							}
						}

					}
				}
			}

			if (flag == 0) {
				System.out.println("please choose correct subject Id to update");
			}
			con.close();

		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
		}

	}

	@Override
	public void deleteSubject(String subject_id) {
		// TODO Auto-generated method stub
		String sql = "delete from Subject where sub_ID =?";
		try {
			Connection con = ConnectionManager.getConnection();

			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, subject_id);

			int res = ps.executeUpdate();

			if (res == 1) {
				System.out.println("**********subject deleted successfully*********");
			}

			else {
				System.out.println("*******please check if department id is present*************");
			}

		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
		}

	}

	@Override
	public void displaySubject(SubjectTable s2) {
		// TODO Auto-generated method stub
		String sql = "select SUB_ID,SUB_NAME from subject where DE_ID=?";
		try {
			Connection con = ConnectionManager.getConnection();

			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, s2.getDepartmentId());
			ResultSet res = ps.executeQuery();

			while (res.next()) {
				String sub_id = res.getString("sub_id");
				String sub_name = res.getString("sub_name");

				System.out.println("subjectId =" + sub_id + "\tSubjectName" + sub_name);
			}

		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
		}

	}

	@Override
	public void deleteTeacher(String teacher_id) {
		// TODO Auto-generated method stub
		String sql = "delete from teacher where teacher_id =?";
		try {
			Connection con = ConnectionManager.getConnection();

			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, teacher_id);

			int res = ps.executeUpdate();

			if (res == 1) {
				System.out.println("teacher deleted successfully");
			}

			else {
				System.out.println("please check if teacher id is present");
			}

		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
		}

	}

	@Override
	public void updateTeacher(String teacher_id) {
		System.out.println("********Enter teacher id**********");
		Teacher t = new Teacher();
		String teacher_id1 = sc.next();
		t.setTeacherId(teacher_id1);
		Connection connection = null;
		connection = ConnectionManager.getConnection();
		PreparedStatement preparedStatement = null;
		String sql2 = "select * FROM TEACHER";
		try {
			preparedStatement = connection.prepareStatement(sql2);
			ResultSet result = preparedStatement.executeQuery();
			if (result.next() == false) {
				System.out.println("*******Id you want to update is not present******** ");
			} else {
				preparedStatement = connection.prepareStatement(sql2);
				ResultSet result1 = preparedStatement.executeQuery();
				int count = 0;
				while (result1.next()) {
					String teacherId = result1.getString("Teacher_id");
					if (teacherId.equals(teacher_id1)) {

						System.out.println("Teacher id is present U can preoceed with Updation");
						count = 1;

					}
				}
				if (count == 1) {
					System.out.println("*******Enter first name********");
					String fname = sc.next();
					t.setFname(fname);
					System.out.println("**********Enter last name***********");
					String lname = sc.next();
					t.setLname(lname);
					System.out.println("***********enter emailId***********");
					String emailId = sc.next();
					t.setEmailId(emailId);
					System.out.println("**********enter password************8");
					String password = sc.next();
					t.setPassword(password);

					System.out.println("*****Enter phone number***********");
					String phone = sc.next();
					t.setPhoneNumber(phone);
					System.out.println("********enter department number**********");
					String dept_number = sc.next();
					t.setDepartmentId(dept_number);

					int result11 = 0;

					connection = ConnectionManager.getConnection();
					String te = "select DEPART_ID FROM UNI_DEPARTMENT";

					int count1 = 0;

					preparedStatement = connection.prepareStatement(te);
					ResultSet res = preparedStatement.executeQuery();
					int count11 = 0;
					while (res.next()) {
						String dept_id = res.getString("DEPART_ID");
						if (t.getDepartmentId().equals(dept_id)) {
							count11 = 1;
							System.out.println("enter subject number");
							String sub_id = sc.next();
							t.setSubject_id(sub_id);
							String sql11 = "select sub_id,DE_ID from subject";

							preparedStatement = connection.prepareStatement(sql11);
							ResultSet res12 = preparedStatement.executeQuery();
							while (res12.next()) {

								String subName = res12.getString("SUB_ID");
								String dept = res12.getString("de_id");
								if (t.getSubject_id().equals(subName) && t.getDepartmentId().equals(dept)) {
									count11 = 1;
									Connection con = ConnectionManager.getConnection();
									String sql = "update TEACHER set FNAME=?,LNAME=?,EMAIL=?,PASSWORD=?,D_NUM=?,S_ID=?,PHONE_NUMBER=? where teacher_id=?";
									PreparedStatement ps = con.prepareStatement(sql);

									ps.setString(1, t.getFname());
									ps.setString(2, t.getLname());
									ps.setString(3, t.getEmailId());
									ps.setString(4, t.getPassword());
									ps.setString(5, t.getDepartmentId());

									ps.setString(6, t.getSubject_id());
									ps.setString(7, t.getPhoneNumber());
									ps.setString(8, t.getTeacherId());

									int res1 = ps.executeUpdate();

									if (res1 == 1) {
										System.out.println("Teacher updated successfully");
									}

									else {
										System.out.println("please check if Teacherid is present");
									}

								}
							}
							if (count11 == 0) {
								System.out.println("invalid subjectcode");
							}

						}

					}

					if (count11 == 0) {
						System.out.println("provide correct depatment id");
					}

				}
				if (count == 0) {
					System.out.println("please provide correct teacher id");
				}

			}
			connection.close();
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
		}

	}

	@Override
	public void displayTeacher() {
		// TODO Auto-generated method stub
		String sql = "select * from teacher";
		try {
			Connection con = ConnectionManager.getConnection();

			PreparedStatement ps = con.prepareStatement(sql);

			ResultSet res = ps.executeQuery();

			while (res.next()) {
				String teacher_id = res.getString("TEACHER_ID");

				String fname = res.getString("FNAME");
				String lname = res.getString("LNAME");
				String email = res.getString("EMAIL");
				String password = res.getString("PASSWORD");
				String deparment = res.getString("D_NUM");
				String sub_id = res.getString("S_ID");
				String phoneNumber = res.getString("PHONE_NUMBER");

				System.out.println("teacher_id =" + teacher_id + "\tfname=" + fname + "\tlastname=" + lname + "\temail="
						+ email + "\tpassword= " + password + "\tdepartment=" + deparment + "\tsub_id= " + sub_id
						+ "\tphoneNumber=" + phoneNumber);
			}

		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
		}

	}

	@Override
	public void displayTeacherById(String teacher_id) {
		// TODO Auto-generated method stub
		String sql = "select * from teacher where teacher_id= ?";
		try {
			Connection con = ConnectionManager.getConnection();

			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, teacher_id);
			ResultSet res = ps.executeQuery();

			while (res.next()) {
				String teacher_id1 = res.getString("TEACHER_ID");

				String fname = res.getString("FNAME");
				String lname = res.getString("LNAME");
				String email = res.getString("EMAIL");
				String password = res.getString("PASSWORD");
				String deparment = res.getString("D_NUM");
				String sub_id = res.getString("S_ID");
				String phoneNumber = res.getString("PHONE_NUMBER");

				System.out.println("teacher_id =" + teacher_id1 + "\tfname=" + fname + "\tlastname=" + lname
						+ "\temail=" + email + "\tpassword= " + password + "\tdepartment=" + deparment + "\tsub_id= "
						+ sub_id + "\tphoneNumber=" + phoneNumber);
			}

		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
		}

	}

	@SuppressWarnings("resource")
	@Override
	public void addStudent(Student s) {
		// TODO Auto-generated method stub

		System.out.println("*********Enter student id***********");
		String student_id = sc.next();
		s.setStudent_id(student_id);
		int result = 0;
		Connection connection = null;
		connection = ConnectionManager.getConnection();

		PreparedStatement preparedStatement = null;
		try {
			String sql = "select * from student";
			preparedStatement = connection.prepareStatement(sql);
			ResultSet res = preparedStatement.executeQuery();
			if (res.next() == false) {
				s.setStudent_id(student_id);
				System.out.println("************Enter first name*********");
				String fname = sc.next();
				s.setFname(fname);
				System.out.println("*******Enter last name*******");
				String lname = sc.next();
				s.setLname(lname);
				System.out.println("******enter emailId************");
				String emailId = sc.next();
				s.setEmailId(emailId);
				System.out.println("**********enter password*********");
				String password = sc.next();
				s.setPassword(password);

				System.out.println("********enter department number*******");
				String dept_number = sc.next();
				s.setDepartmentId(dept_number);
				String deptsql = "select * from UNI_department";
				preparedStatement = connection.prepareStatement(deptsql);
				ResultSet resd = preparedStatement.executeQuery();
				int count = 0;
				while (resd.next()) {
					String Dept_no = resd.getString("DEPart_ID");
					if (Dept_no.equals(dept_number)) {
						count = 1;
						System.out.println("Enter phone number");
						String phone = sc.next();
						s.setPhoneNumber(phone);
						String sql1 = "insert into student(student_id,FNAME,LNAME,EMAIL,PASSWORD,D_NUM,PHONE_NUMBER)values(?,?,?,?,?,?,?)";
						preparedStatement = connection.prepareStatement(sql1);
						preparedStatement.setString(1, s.getStudent_id());
						preparedStatement.setString(2, s.getFname());
						preparedStatement.setString(3, s.getLname());
						preparedStatement.setString(4, s.getEmailId());
						preparedStatement.setString(5, s.getPassword());
						preparedStatement.setString(6, s.getDepartmentId());

						preparedStatement.setString(7, s.getPhoneNumber());

						result = preparedStatement.executeUpdate();

						if (result == 1) {
							System.out.println("added Successfully");
						} else {
							System.out.println("check your details");
						}
					}
				}
				if (count == 0) {
					System.out.println("Check your department ID");
				}
			} else {
				preparedStatement = connection.prepareStatement(sql);
				ResultSet res1 = preparedStatement.executeQuery();
				int count = 0;
				while (res1.next()) {
					String Studemt_id = res1.getString("STUDENT_ID");
					if (student_id.equals(Studemt_id)) {
						System.out.println("Student with this id already present");
						count = 1;
					}
				}
				if (count == 0) {

					System.out.println("************Enter first name*********");
					String fname = sc.next();
					s.setFname(fname);
					System.out.println("*******Enter last name*******");
					String lname = sc.next();
					s.setLname(lname);
					System.out.println("******enter emailId************");
					String emailId = sc.next();
					s.setEmailId(emailId);
					System.out.println("**********enter password*********");
					String password = sc.next();
					s.setPassword(password);

					System.out.println("********enter department number*******");
					String dept_number = sc.next();
					s.setDepartmentId(dept_number);
					String deptsql = "select * from UNI_department";
					preparedStatement = connection.prepareStatement(deptsql);
					ResultSet resd = preparedStatement.executeQuery();
					int count1 = 0;
					while (resd.next()) {
						String Dept_no = resd.getString("DEPART_ID");
						if (Dept_no.equals(dept_number)) {
							count1 = 1;
							System.out.println("Enter phone number");
							String phone = sc.next();
							s.setPhoneNumber(phone);
							String sql1 = "insert into student(student_id,FNAME,LNAME,EMAIL,PASSWORD,D_NUM,PHONE_NUMBER)values(?,?,?,?,?,?,?)";
							preparedStatement = connection.prepareStatement(sql1);
							preparedStatement.setString(1, s.getStudent_id());
							preparedStatement.setString(2, s.getFname());
							preparedStatement.setString(3, s.getLname());
							preparedStatement.setString(4, s.getEmailId());
							preparedStatement.setString(5, s.getPassword());
							preparedStatement.setString(6, s.getDepartmentId());

							preparedStatement.setString(7, s.getPhoneNumber());

							result = preparedStatement.executeUpdate();

							if (result == 1) {
								System.out.println("added Successfully");
							} else {
								System.out.println("check your details");
							}
						}
					}
					if (count1 == 0) {
						System.out.println("Check your department ID");
					}

				}

			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public void deleteStudent(String student_id) {
		// TODO Auto-generated method stub
		String sql = "delete from student where student_id =?";
		try {
			Connection con = ConnectionManager.getConnection();

			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, student_id);

			int res = ps.executeUpdate();

			if (res == 1) {
				System.out.println("student recored deleted successfully");
			}

			else {
				System.out.println("please check if student id is present");
			}

		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
		}

	}

	@Override
	public void updateStudent(String student_id) {
		// TODO Auto-generated method stub

		Student st = new Student();
		// TODO Auto-generated method stub

		st.setStudent_id(student_id);
		int result = 0;
		Connection connection = null;
		connection = ConnectionManager.getConnection();

		PreparedStatement preparedStatement = null;
		try {
			String sql = "select * from student";
			preparedStatement = connection.prepareStatement(sql);
			ResultSet res = preparedStatement.executeQuery();
			if (res.next() == false) {
				System.out.println("Your recoreds Are not there here to update");
			} else {
				int count = 0;
				preparedStatement = connection.prepareStatement(sql);
				ResultSet res1 = preparedStatement.executeQuery();
				while (res1.next()) {
					String id = res1.getString("Student_id");
					if (id.equals(student_id)) {
						count = 1;
					}
				}
				if (count == 1) {
					System.out.println("Enter first name");
					String fname = sc.next();
					st.setFname(fname);
					System.out.println("Enter last name");
					String lname = sc.next();
					st.setLname(lname);
					System.out.println("enter emailId");
					String emailId = sc.next();
					st.setEmailId(emailId);
					System.out.println("enter password");
					String password = sc.next();
					st.setPassword(password);

					System.out.println("Enter phone number");
					String phone = sc.next();
					st.setPhoneNumber(phone);
					System.out.println("enter department number");
					String dept_number = sc.next();
					st.setDepartmentId(dept_number);
					String deptsql = "select * from UNI_department";
					preparedStatement = connection.prepareStatement(deptsql);
					ResultSet resd = preparedStatement.executeQuery();
					int count1 = 0;
					while (resd.next()) {
						String Dept_no = resd.getString("DEPART_ID");
						if (Dept_no.equals(dept_number)) {
							count1 = 1;

							String sql1 = "update student set FNAME=?,LNAME=?,EMAIL=?,PASSWORD=?,D_NUM=?,PHONE_NUMBER=? where student_id=?";
							PreparedStatement ps = connection.prepareStatement(sql1);

							ps.setString(1, st.getFname());
							ps.setString(2, st.getLname());
							ps.setString(3, st.getEmailId());
							ps.setString(4, st.getPassword());
							ps.setString(5, st.getDepartmentId());

							ps.setString(6, st.getPhoneNumber());
							ps.setString(7, st.getStudent_id());

							int res11 = ps.executeUpdate();

							if (res11 == 1) {
								System.out.println("Student updated successfully");
							}

						}
					}
					if (count1 == 0) {
						System.out.println("enter proper dipartment id");
					}

				}
				if (count == 0) {
					System.out.println("Student id you want to update is not present");
				}
				connection.close();
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
		}

	}

	@Override
	public void displayStudent() {
		// TODO Auto-generated method stub
		String sql = "select * from student";
		try {
			Connection con = ConnectionManager.getConnection();

			PreparedStatement ps = con.prepareStatement(sql);

			ResultSet res = ps.executeQuery();

			while (res.next()) {
				String student_id = res.getString("STUDENT_ID");

				String fname = res.getString("FNAME");
				String lname = res.getString("LNAME");
				String email = res.getString("EMAIL");
				String password = res.getString("PASSWORD");
				String deparment = res.getString("D_NUM");
				String phoneNumber = res.getString("PHONE_NUMBER");

				System.out.println("STUDENT_id =" + student_id + "\tfname=" + fname + "\tlastname=" + lname + "\temail="
						+ email + "\tpassword= " + password + "\tdepartment=" + deparment + "\tphoneNumber="
						+ phoneNumber);
			}

		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
		}

	}

	@Override
	public void displayStudentById(String student_id) {
		String sql = "select * from student where student_id= ?";
		try {
			Connection con = ConnectionManager.getConnection();

			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, student_id);
			ResultSet res = ps.executeQuery();

			while (res.next()) {
				String student_id1 = res.getString("STUDENT_ID");

				String fname = res.getString("FNAME");
				String lname = res.getString("LNAME");
				String email = res.getString("EMAIL");
				String password = res.getString("PASSWORD");
				String deparment = res.getString("D_NUM");

				String phoneNumber = res.getString("PHONE_NUMBER");

				System.out.println("student_id=" + student_id1 + "\tfname=" + fname + "\tlastname=" + lname + "\temail="
						+ email + "\tpassword= " + password + "\tdepartment=" + deparment + "\tphoneNumber="
						+ phoneNumber);
			}

		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
		}

		// TODO Auto-generated method stub

	}

	@SuppressWarnings("resource")
	@Override
	public void addTeacher(Teacher t) {
		// TODO Auto-generated method stub

		System.out.println("********Enter teacher id**********");
		String teacher_id = sc.next();
		t.setTeacherId(teacher_id);
		Connection connection = null;
		connection = ConnectionManager.getConnection();
		PreparedStatement preparedStatement = null;
		String sql2 = "select* FROM TEACHER";
		try {
			preparedStatement = connection.prepareStatement(sql2);
			ResultSet result = preparedStatement.executeQuery();
			if (result.next() == false) {

				System.out.println("Enter first name");
				String fname = sc.next();
				t.setFname(fname);
				System.out.println("Enter last name");
				String lname = sc.next();
				t.setLname(lname);
				System.out.println("enter emailId");
				String emailId = sc.next();
				t.setEmailId(emailId);
				System.out.println("enter password");
				String password = sc.next();
				t.setPassword(password);

				System.out.println("Enter phone number");
				String phone = sc.next();
				t.setPhoneNumber(phone);
				System.out.println("enter department number");
				String dept_number = sc.next();
				t.setDepartmentId(dept_number);

				int result1 = 0;

				connection = ConnectionManager.getConnection();
				String te = "select DEPART_ID FROM UNI_DEPARTMENT";

				int count = 0;

				preparedStatement = connection.prepareStatement(te);
				ResultSet res = preparedStatement.executeQuery();
				int count1 = 0;
				while (res.next()) {
					String dept_id = res.getString("DEPART_ID");
					if (t.getDepartmentId().equals(dept_id)) {
						count1 = 1;
						System.out.println("enter subject number");
						String sub_id = sc.next();
						t.setSubject_id(sub_id);
						String sql11 = "select sub_id,DE_ID from subject";

						preparedStatement = connection.prepareStatement(sql11);
						ResultSet res12 = preparedStatement.executeQuery();
						while (res12.next()) {

							String subName = res12.getString("SUB_ID");
							String dept = res12.getString("de_id");
							if (t.getSubject_id().equals(subName) && t.getDepartmentId().equals(dept)) {
								count = 1;
								String sql = "insert into teacher(teacher_id,FNAME,LNAME,EMAIL,PASSWORD,D_NUM,S_ID,PHONE_NUMBER)values(?,?,?,?,?,?,?,?)";
								preparedStatement = connection.prepareStatement(sql);
								preparedStatement.setString(1, t.getTeacherId());
								preparedStatement.setString(2, t.getFname());
								preparedStatement.setString(3, t.getLname());
								preparedStatement.setString(4, t.getEmailId());
								preparedStatement.setString(5, t.getPassword());
								preparedStatement.setString(6, t.getDepartmentId());
								preparedStatement.setString(7, t.getSubject_id());
								preparedStatement.setString(8, t.getPhoneNumber());

								result1 = preparedStatement.executeUpdate();

								if (result1 == 1) {
									System.out.println("added auccessfully");
								} else {
									System.out.println("Check details");
								}
							}
						}
						if (count == 0) {
							System.out.println("invalid subjectcode");
						}

					}
					if (count1 == 0) {
						System.out.println("provide correct depatment id");
					}
				}

			} else {
				preparedStatement = connection.prepareStatement(sql2);
				ResultSet result1 = preparedStatement.executeQuery();
				int count = 0;
				while (result1.next()) {

					String teacherId = result1.getString("TEACHER_ID");
					if (teacherId.equals(t.getTeacherId())) {
						System.out.println("teacher id is already present");
						count = 1;
					}

				}
				if (count == 0) {

					System.out.println("Enter first name");
					String fname = sc.next();
					t.setFname(fname);
					System.out.println("Enter last name");
					String lname = sc.next();
					t.setLname(lname);
					System.out.println("enter emailId");
					String emailId = sc.next();
					t.setEmailId(emailId);
					System.out.println("enter password");
					String password = sc.next();
					t.setPassword(password);

					System.out.println("Enter phone number");
					String phone = sc.next();
					t.setPhoneNumber(phone);
					System.out.println("enter department number");
					String dept_number = sc.next();
					t.setDepartmentId(dept_number);

					int result11 = 0;

					connection = ConnectionManager.getConnection();
					String sql21 = "select DEPART_ID FROM UNI_DEPARTMENT";

					String sql11 = "select sub_id,DE_ID from subject";

					preparedStatement = connection.prepareStatement(sql21);
					ResultSet res = preparedStatement.executeQuery();
					int count11 = 0;
					while (res.next()) {
						String dept_id = res.getString("DEPART_ID");
						if (t.getDepartmentId().equals(dept_id)) {
							count11 = 1;
							System.out.println("enter subject number");
							String sub_id = sc.next();
							t.setSubject_id(sub_id);

							preparedStatement = connection.prepareStatement(sql11);
							ResultSet res12 = preparedStatement.executeQuery();
							while (res12.next()) {

								String subName = res12.getString("SUB_ID");
								String dept = res12.getString("de_id");
								if (t.getSubject_id().equals(subName) && t.getDepartmentId().equals(dept)) {
									count11 = 1;
									String sql = "insert into teacher(teacher_id,FNAME,LNAME,EMAIL,PASSWORD,D_NUM,S_ID,PHONE_NUMBER)values(?,?,?,?,?,?,?,?)";
									preparedStatement = connection.prepareStatement(sql);
									preparedStatement.setString(1, t.getTeacherId());
									preparedStatement.setString(2, t.getFname());
									preparedStatement.setString(3, t.getLname());
									preparedStatement.setString(4, t.getEmailId());
									preparedStatement.setString(5, t.getPassword());
									preparedStatement.setString(6, t.getDepartmentId());
									preparedStatement.setString(7, t.getSubject_id());
									preparedStatement.setString(8, t.getPhoneNumber());

									result11 = preparedStatement.executeUpdate();

									if (result11 == 1) {
										System.out.println("added auccessfully");
									} else {
										System.out.println("Check details");
									}
								}
							}
							if (count11 == 0) {
								System.out.println("invalid subjectcode");
							}

						}

					}
					if (count11 == 0) {
						System.out.println("provide correct depatment id");
					}

				}
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
