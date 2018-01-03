package com.demo.dao;

import com.demo.pojo.response.UserResponse;
import com.demo.pojo.resquest.UserRequest;

public interface IUserDao {
	
	public UserResponse doUserLogin(UserRequest userRequest);
}