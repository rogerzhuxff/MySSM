package com.demo.dao;

import java.util.List;

import com.demo.pojo.response.EmpResponse;
import com.demo.pojo.resquest.EmpRequest;

public interface IEmpDao {

	public List<EmpResponse> getEmpList();

	public int addEmp(EmpRequest emp);
	
	public int deleteEmp(int id);
	
	public int updateEmp(EmpRequest emp);
}
