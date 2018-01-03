package com.demo.controller;  
  
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.demo.common.ResponseUtils;
import com.demo.common.StatusCode;
import com.demo.pojo.response.UserResponse;
import com.demo.pojo.resquest.UserRequest;

import io.swagger.annotations.Api;
  
  
@RestController  
@RequestMapping("/login")
@Api(value = "login",tags={"登录接口"})
public class LoginController {  

  
@RequestMapping(value="/dologin",method=RequestMethod.POST)  
public ResponseUtils<?> dologin(@Valid @RequestBody UserRequest user){ 
//	if(isRelogin(user)!=null) {
//		 UserResponse userInfo = (UserResponse) SecurityUtils.getSubject().getPrincipal();
		 
//		 return new ResponseUtils<>(StatusCode.SUCCESS_CODE.getName(),StatusCode.SUCCESS_CODE.getIndex(),userInfo);  
//	}
	
	
    return (ResponseUtils<?>) loginUser(user);  
}  
  
@RequestMapping(value="/logout",method=RequestMethod.POST)  
public  ResponseUtils<?> logout(HttpServletRequest request,HttpServletResponse response) throws IOException{  
    Subject subject = SecurityUtils.getSubject();  
    if (subject != null) {  
        try{  
            subject.logout(); 
            return new ResponseUtils<>(StatusCode.SUCCESS_CODE.getName(),StatusCode.SUCCESS_CODE.getIndex(),null);  
        }catch(Exception ex){  
        }  
    }  
    return new ResponseUtils<>(StatusCode.OPTERROR.getName(),StatusCode.OPTERROR.getIndex(),null);  
}  

@RequestMapping(value="/notlogin",method=RequestMethod.GET)  
public ResponseUtils<String> notlogin(){ 
	
    return new ResponseUtils<String>(StatusCode.NOTLOGIN.getName(), StatusCode.NOTLOGIN.getIndex(), null);
}  

@RequestMapping(value="/unauthorized",method=RequestMethod.GET)  
public ResponseUtils<String> unauthorized(){ 
	
    return new ResponseUtils<String>(StatusCode.UNAUTHORIZED.getName(), StatusCode.UNAUTHORIZED.getIndex(), null);
}  

  
private ResponseUtils<?> loginUser(UserRequest user) {  
//        if (isRelogin(user)!=null) return isRelogin(user); // 如果已经登陆，无需重新登录  
          
        return shiroLogin(user); // 调用shiro的登陆验证  
}  
private ResponseUtils<?> shiroLogin(UserRequest user) {  
        // 组装token，包括客户公司名称、简称、客户编号、用户名称；密码  
    UsernamePasswordToken token = new UsernamePasswordToken(user.getUsername(), user.getPassword().toCharArray(), null);   
   // token.setRememberMe(true);  
    UserResponse userInfo;
    // shiro登陆验证  
    try {  
        SecurityUtils.getSubject().login(token); 
         userInfo  = (UserResponse) SecurityUtils.getSubject().getPrincipal();
    } catch (UnknownAccountException ex) {  
        return new ResponseUtils<String>(StatusCode.LOGINFAIL.getName()+":用户不存在或者密码错误！", StatusCode.LOGINFAIL.getIndex(),null);  
    } catch (IncorrectCredentialsException ex) {  
    		return new ResponseUtils<String>(StatusCode.LOGINFAIL.getName()+":用户不存在或者密码错误！", StatusCode.LOGINFAIL.getIndex(),null);  
    } catch (AuthenticationException ex) {  
    		return new ResponseUtils<String>(StatusCode.LOGINFAIL.getName()+":"+ex.getMessage(), StatusCode.LOGINFAIL.getIndex(),null);  
    } catch (Exception ex) {  
        ex.printStackTrace();  
        return new ResponseUtils<String>(StatusCode.LOGINFAIL.getName()+":内部错误，请重试！", StatusCode.LOGINFAIL.getIndex(),null);  
    }  
    return new ResponseUtils<UserResponse>(StatusCode.SUCCESS_CODE.getName(), StatusCode.SUCCESS_CODE.getIndex(), userInfo  );  
}  
  
private ResponseUtils<?> isRelogin(UserRequest user) {  
    Subject us = SecurityUtils.getSubject(); 
    UserResponse  userInfo  = (UserResponse) SecurityUtils.getSubject().getPrincipal();
        if (us.isAuthenticated()) {  
        		return new ResponseUtils<UserResponse>(StatusCode.SUCCESS_CODE.getName(), StatusCode.SUCCESS_CODE.getIndex(), userInfo  );  
        }  
        return null; // 需要重新登陆  
}  
}