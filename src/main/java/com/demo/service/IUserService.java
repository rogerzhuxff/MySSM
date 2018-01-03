package com.demo.service;

import com.demo.pojo.response.UserResponse;
import com.demo.pojo.resquest.UserRequest;

public interface IUserService {

	public UserResponse doUserLogin(UserRequest userRequest);
}
