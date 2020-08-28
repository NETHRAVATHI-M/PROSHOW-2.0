package model;

public class Department extends CommonDetails {
	
	private String departmentId;
	private String departmentName;
	public String getDepartmentId() {
		return departmentId;
	}
	public void setDepartmentId(String departmentId) {
		this.departmentId = departmentId;
	}
	public String getDepartmentName() {
		return departmentName;
	}
	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}
//	public Department(String departmentId, String departmentName) {
//		super();
//		this.departmentId = departmentId;
//		this.departmentName = departmentName;
//	}
	
	
	
	public Department() {
		
	}
public Department(String departmentId, String departmentName) {
	super();
	this.departmentId = departmentId;
	this.departmentName = departmentName;
}
public Department(String departmentId) {
	super();
	this.departmentId = departmentId;
}
	

}
