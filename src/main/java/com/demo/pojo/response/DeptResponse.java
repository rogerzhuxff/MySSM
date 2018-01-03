package com.demo.pojo.response;

import java.util.List;

public class DeptResponse {

	private int id;

	private String deptname;

	private List<EmpResponse> emps;
	
	public List<EmpResponse> getEmps() {
		return emps;
	}

	public void setEmps(List<EmpResponse> emps) {
		this.emps = emps;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDeptname() {
		return deptname;
	}

	public void setDeptname(String deptname) {
		this.deptname = deptname;
	}

	
	
}
