package com.demo.pojo.resquest;

import org.hibernate.validator.constraints.NotEmpty;

import io.swagger.annotations.ApiModelProperty;

public class UserRequest {

	
	
	@NotEmpty(message="用户名不能为空")
	@ApiModelProperty(value = "用户名",required=true)
	private String username;
	
	@NotEmpty(message="密码不能为空")
	@ApiModelProperty(value = "密码",required=true)
	private String password;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	
	
	
}
