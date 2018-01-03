package com.demo.pojo.resquest;


import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


@ApiModel(value="Emp对象",description="员工对象Emp")
public class EmpRequest {
	
	
	private int id;
	
	@ApiModelProperty(value = "员工姓名",required=true)
	@NotEmpty(message = "用户名称不能为空")
//    @Size(min = 3, max = 20, message = "{error.username}")
    private String empname;
    
	@ApiModelProperty(value = "邮箱",required=true)
    @Email
    private String email;

    @ApiModelProperty(value = "年龄",required=true)
    @Min(value = 1,message = "年龄最小1")
    @Max(value = 120, message = "{error.age}")
    private int age;
    
    private String birthday;

    private Integer deptId;

    private Double bonus;
    
    
//    @ApiModelProperty(value = "密码",required=true)
//    @NotEmpty(message = "密码不能为空")
//    @Pattern(regexp = "^((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])).{6,20}$", message = "{error.password}")
//    private String password;
    
    public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}


	public Integer getDeptId() {
		return deptId;
	}

	public void setDeptId(Integer deptId) {
		this.deptId = deptId;
	}

	
	public Double getBonus() {
		return bonus;
	}

	public void setBonus(Double bonus) {
		this.bonus = bonus;
	}

	public String getEmpname() {
        return empname;
    }
 
    public void setEmpname(String empname) {
        this.empname = empname;
    }
 
    public String getEmail() {
        return email;
    }
 
    public void setEmail(String email) {
        this.email = email;
    }
 
    public int getAge() {
        return age;
    }
 
    public void setAge(int age) {
        this.age = age;
    }

    
 
}