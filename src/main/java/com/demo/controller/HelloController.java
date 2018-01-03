package com.demo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.demo.common.ResponseUtils;
import com.demo.common.StatusCode;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
  
@RestController
@RequestMapping("hello")
@Api(value = "hello",tags= {"hello接口"})
public class HelloController {  
    
	@RequestMapping(value = "world" ,method=RequestMethod.POST)
	@ApiOperation(value = "世界" )
    public ResponseUtils<String> HelloWorld(){  
    		
    		return new ResponseUtils<String>(
    										StatusCode.SUCCESS_CODE.getName(), 
    										StatusCode.SUCCESS_CODE.getIndex(), 
    										"hello world!");
    }  
      
}  