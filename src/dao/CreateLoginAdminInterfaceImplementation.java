package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import model.Admin;
import utility.ConnectionManager;

public class CreateLoginAdminInterfaceImplementation implements CreateLoginAdminInterface {

	Scanner sc = new Scanner(System.in);

	@Override
	public void createAdmin(Admin admin) {
		// TODO Auto-generated method stub
		int result = 0;
		Connection connection = null;
		connection = ConnectionManager.getConnection();
		String sql1 = "select * from admin";

		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = connection.prepareStatement(sql1);
			ResultSet rs = preparedStatement.executeQuery();
			if (rs.next() == false) {
				String sql = "insert into ADMIN(USERNAME,PASSWORD)VALUES(?,?)";
				preparedStatement = connection.prepareStatement(sql);
				preparedStatement.setString(1, admin.getUsername());
				preparedStatement.setString(2, admin.getPassword());
				result = preparedStatement.executeUpdate();

				if (result == 1) {
					System.out.println("added auccessfully");
				}

			} else {
				System.out.println("Admin details are already entered. please do login to perform functionaliy");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public boolean loginAdmin(Admin admin) {
		// TODO Auto-generated method stub

		try {
			Connection con = ConnectionManager.getConnection();
			String sql = "select * from admin";
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			if (rs.next() == false) {
				System.out.println("Admin is not registerd please do create your account");
			} else {

				String userName = rs.getString("USERNAME");
				String password = rs.getString("PASSWORD");
				if (userName.equals(admin.getUsername()) && password.equals(admin.getPassword())) {
					System.out.println("**********login successfull******\n");
					
					return true;
				} else {
					System.out.println("please check your credentials");
				}
				con.close();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return false;
	}

	public void updateAdmin() {
		System.out.println("please verify your self before updating admin credentilas");

		Connection con = ConnectionManager.getConnection();
		String sql = "select * from admin";
		try {

			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			if (rs.next() == false) {
				System.out.println("Admin is not registerd please do create your account");
			} else {

				System.out.println("enter username");
				String username = sc.next();

				System.out.println("enter password");
				String password = sc.next();
				String userName = rs.getString("USERNAME");
				String Password = rs.getString("PASSWORD");
				if (userName.equals(username) && password.equals(Password)) {
					System.out.println("verified  successfull!.....");
					System.out.println("now you can edit details");
					System.out.println("enter username");
					String username1 = sc.next();
					System.out.println("enter password");
					String password1 = sc.next();
					String sql1 = "update admin set USERNAME=?,PASSWORD =? ";
					ps = con.prepareStatement(sql1);
					ps.setString(1, username1);
					ps.setString(2, password1);
					int result = ps.executeUpdate();
					if (result == 1) {
						System.out.println("Admin details updated successfully");
						System.out.println("current username =" + username1 + "\nPassword= " + password1);

					}

				} else {
					System.out.println("please check your credentials");
				}

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void delete() {
		// TODO Auto-generated method stub
		System.out.println("please verify your self before deleting admin credentilas");

		Connection con = ConnectionManager.getConnection();
		String sql = "select * from admin";
		try {

			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			if (rs.next() == false) {
				System.out.println("Admin is not registerd please do create your account");
			} else {

				System.out.println("enter username");
				String username = sc.next();

				System.out.println("enter password");
				String password = sc.next();
				String userName = rs.getString("USERNAME");
				String Password = rs.getString("PASSWORD");
				if (userName.equals(username) && password.equals(Password)) {
					System.out.println("verified  successfull!.....");

					String sql1 = "delete from admin";
					ps = con.prepareStatement(sql1);
				 ps.executeQuery();

					System.out.println("admin deleted successfully");
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
