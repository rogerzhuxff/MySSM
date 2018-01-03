package com.demo.service;

import java.util.List;

import com.demo.pojo.response.EmpResponse;
import com.demo.pojo.resquest.EmpRequest;

public interface IEmpService {

	public List<EmpResponse> getEmpList();
	
	
	public int addEmp(EmpRequest emp);
	
	public int deleteEmp(int id);
	
	public int updateEmp(EmpRequest emp);
}
