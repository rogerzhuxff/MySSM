package com.demo.controller;

import java.util.HashSet;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.demo.common.PageBean;
import com.demo.common.ResponseUtils;
import com.demo.common.StatusCode;
import com.demo.pojo.response.EmpResponse;
import com.demo.pojo.resquest.EmpRequest;
import com.demo.pojo.resquest.PageHelperRequest;
import com.demo.service.IEmpService;
import com.github.pagehelper.PageHelper;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;


@RestController
@RequestMapping("emp")
@Api(value = "emp",tags={"员工接口"})
public class EmpController {

	@Resource
	private IEmpService empService;
	
	@RequestMapping(value="getEmpList",method=RequestMethod.POST)
	@ApiOperation(value="获取员工数据")
	@RequiresRoles("user")
	public ResponseUtils<PageBean<EmpResponse>> getEmpList(@RequestBody PageHelperRequest pageHelperRequest){
		
		PageHelper.startPage(pageHelperRequest.getPageNum(), pageHelperRequest.getPageSize());
		
		PageBean<EmpResponse> list = new PageBean<EmpResponse>(empService.getEmpList());
		return new ResponseUtils<PageBean<EmpResponse>>(
										   StatusCode.SUCCESS_CODE.getName(), 
										   StatusCode.SUCCESS_CODE.getIndex(), 
										   list);
		
	}
	
	
	
	@RequestMapping(value="addEmp",method=RequestMethod.POST)
	@ApiOperation(value="添加新员工")
	public ResponseUtils<Integer> addEmp(@Valid @RequestBody EmpRequest empRequest){
		
		ResponseUtils<Integer> response = new ResponseUtils<Integer>();
		int result = empService.addEmp(empRequest);
		if(result>0) {
			response.setCode(StatusCode.SUCCESS_CODE.getIndex());
			response.setMessage(StatusCode.SUCCESS_CODE.getName());
		}else {
			response.setCode(StatusCode.INSERTERROR.getIndex());
			response.setMessage(StatusCode.INSERTERROR.getName());
		}
		
		return response;
	}
	
	@RequestMapping(value="deleteEmp/{id}",method=RequestMethod.POST)
	@ApiOperation(value="删除员工")
	public ResponseUtils<Integer> deleteEmp(@Valid @PathVariable int id){
		
		ResponseUtils<Integer> response = new ResponseUtils<Integer>();
		
		int result = empService.deleteEmp(id);
		
		if(result>0) {
			response.setCode(StatusCode.SUCCESS_CODE.getIndex());
			response.setMessage(StatusCode.SUCCESS_CODE.getName());
		}else {
			response.setCode(StatusCode.DELETEERROR.getIndex());
			response.setMessage(StatusCode.DELETEERROR.getName());
		}
		
		return response;
	}
	
	@RequestMapping(value="updateEmp",method=RequestMethod.POST)
	@ApiOperation(value="更新员工信息")
	public ResponseUtils<Integer> updateEmp(@Valid @RequestBody EmpRequest emp){
		
		
		ResponseUtils<Integer> response = new ResponseUtils<Integer>();
		
		int result = empService.updateEmp(emp);
		
		if(result>0) {
			response.setCode(StatusCode.SUCCESS_CODE.getIndex());
			response.setMessage(StatusCode.SUCCESS_CODE.getName());
		}else {
			response.setCode(StatusCode.UPDATEERROR.getIndex());
			response.setMessage(StatusCode.UPDATEERROR.getName());
		}
		
		return response;
		
	}
	
	
	
	
}
