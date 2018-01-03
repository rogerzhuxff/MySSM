package com.demo.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.demo.dao.IUserDao;
import com.demo.pojo.response.UserResponse;
import com.demo.pojo.resquest.UserRequest;
import com.demo.service.IUserService;

@Service("userService")
public class UserServiceImpl implements IUserService{

	@Resource
	private IUserDao userDao;
	
	@Override
	public UserResponse doUserLogin(UserRequest userRequest) {
		// TODO Auto-generated method stub
		return userDao.doUserLogin(userRequest);
	}

	

}
