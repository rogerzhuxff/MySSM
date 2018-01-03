package com.demo.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.demo.dao.IEmpDao;
import com.demo.pojo.response.EmpResponse;
import com.demo.pojo.resquest.EmpRequest;
import com.demo.service.IEmpService;

@Service("empService")
public class EmpServiceImpl implements IEmpService {

	@Resource
	private IEmpDao empDao;
	
	@Override
	public List<EmpResponse> getEmpList() {
		// TODO Auto-generated method stub
		return empDao.getEmpList();
	}

	@Override
	public int addEmp(EmpRequest emp) {
		// TODO Auto-generated method stub
		return empDao.addEmp(emp);
	}

	@Override
	public int deleteEmp(int id) {
		// TODO Auto-generated method stub
		return empDao.deleteEmp(id);
	}

	@Override
	public int updateEmp(EmpRequest emp) {
		// TODO Auto-generated method stub
		return empDao.updateEmp(emp);
	}

}
