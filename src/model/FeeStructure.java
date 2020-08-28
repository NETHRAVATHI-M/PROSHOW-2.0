package model;

public class FeeStructure {

	private String department_id;
	private Long total_amount;
	public String getDepartment_id() {
		return department_id;
	}
	public void setDepartment_id(String department_id) {
		this.department_id = department_id;
	}
	public Long getTotal_amount() {
		return total_amount;
	}
	public void setTotal_amount(Long total_amount) {
		this.total_amount = total_amount;
	}
	public FeeStructure(String department_id, Long total_amount) {
		super();
		this.department_id = department_id;
		this.total_amount = total_amount;
	}
	 
	public FeeStructure() {
		
	}
}
