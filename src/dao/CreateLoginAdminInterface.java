package dao;

import model.Admin;

public interface CreateLoginAdminInterface {
	public void createAdmin(Admin admin);
	public boolean loginAdmin(Admin admin);
	public void delete();
	public void updateAdmin();
}
