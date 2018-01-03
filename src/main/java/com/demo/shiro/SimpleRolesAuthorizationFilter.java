package com.demo.shiro;

import java.io.IOException;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.StringUtils;
import org.apache.shiro.web.filter.authz.AuthorizationFilter;
import org.apache.shiro.web.util.WebUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMethod;

import com.demo.common.ResponseUtils;
import com.demo.common.StatusCode;
import com.fasterxml.jackson.databind.ObjectMapper;

    
// AuthorizationFilter抽象类事项了javax.servlet.Filter接口，它是个过滤器。    
public class SimpleRolesAuthorizationFilter extends AuthorizationFilter {    
    
	private static final Logger log = LoggerFactory.getLogger(SimpleRolesAuthorizationFilter.class);
    @Override    
    protected boolean isAccessAllowed(ServletRequest req, ServletResponse resp, Object mappedValue) throws Exception {    
        Subject subject = getSubject(req, resp);    
        String[] rolesArray = (String[]) mappedValue;    
    
        if (rolesArray == null || rolesArray.length == 0) { //没有角色限制，有权限访问    
            return true;    
        }    
        for (int i = 0; i < rolesArray.length; i++) {    
            if (subject.hasRole(rolesArray[i])) { //若当前用户是rolesArray中的任何一个，则有权限访问    
                return true;    
            }    
        }    
    
        return false;    
    } 
    
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws IOException {

        Subject subject = getSubject(request, response);
        ObjectMapper mapper = new ObjectMapper();
        // If the subject isn't identified, redirect to login URL
        if (subject.getPrincipal() == null) {
//            saveRequestAndRedirectToLogin(request, response);
                
    		  
           ResponseUtils<String> responseUtils = new ResponseUtils<String>(StatusCode.NOTLOGIN.getName(), StatusCode.NOTLOGIN.getIndex(), null);
           response.setContentType("application/json;charset=UTF-8"); 
           response.getWriter().print(mapper.writeValueAsString(responseUtils));
        		
        		
        } else {
            // If subject is known but not authorized, redirect to the unauthorized URL if there is one
            // If no unauthorized URL is specified, just return an unauthorized HTTP status code
            String unauthorizedUrl = getUnauthorizedUrl();
            //SHIRO-142 - ensure that redirect _or_ error code occurs - both cannot happen due to response commit:
            if (StringUtils.hasText(unauthorizedUrl)) {
//                WebUtils.issueRedirect(request, response, unauthorizedUrl);
            	   ResponseUtils<String> responseUtils = new ResponseUtils<String>(StatusCode.UNAUTHORIZED.getName(), StatusCode.UNAUTHORIZED.getIndex(), null);
            	   response.setContentType("application/json;charset=UTF-8"); 
            	   response.getWriter().print(mapper.writeValueAsString(responseUtils));
            
            } else {
                WebUtils.toHttp(response).sendError(HttpServletResponse.SC_UNAUTHORIZED);
            }
        }
        return false;
    }
    
    
  protected boolean preHandle(ServletRequest servletRequest, ServletResponse servletResponse) throws Exception {
    	
	  	log.info("role 进入");
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        
	   	 response.addHeader("Access-Control-Allow-Origin"	,request.getHeader("Origin"));
	     response.addHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
	     response.addHeader("Access-Control-Allow-Headers", "origin, content-type, accept, x-requested-with, sid, mycustom, smuser,AUTHORIZTION_FLAG");
	     response.addHeader("Access-Control-Max-Age", "1800");//30 min
	     response.setHeader("Access-Control-Allow-Credentials", "true");
        
        if(request.getMethod().equals(RequestMethod.OPTIONS.name()))
        {
        		log.info("role 通过");
            response.setStatus(HttpStatus.OK.value());
            return true;
        }else {
        	log.info("role 不需要验证");
        }
        return super.preHandle(request, response);
    }
    
    

}  