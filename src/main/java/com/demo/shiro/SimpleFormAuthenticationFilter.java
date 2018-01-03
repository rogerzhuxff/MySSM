/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package com.demo.shiro;


import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMethod;

import com.demo.common.ResponseUtils;
import com.demo.common.StatusCode;
import com.fasterxml.jackson.databind.ObjectMapper;


public class SimpleFormAuthenticationFilter extends FormAuthenticationFilter {

	private static final Logger log = LoggerFactory.getLogger(SimpleFormAuthenticationFilter.class);
  
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        if (isLoginRequest(request, response)) {
            if (isLoginSubmission(request, response)) {
                if (log.isTraceEnabled()) {
                    log.trace("Login submission detected.  Attempting to execute login.");
                }
                return executeLogin(request, response);
            } else {
                if (log.isTraceEnabled()) {
                    log.trace("Login page view.");
                }
                //allow them to see the login page ;)
                return true;
            }
        } else {
            if (log.isTraceEnabled()) {
                log.trace("Attempting to access a path which requires authentication.  Forwarding to the " +
                        "Authentication url [" + getLoginUrl() + "]");
            }
            
            ObjectMapper mapper = new ObjectMapper();
            ResponseUtils<String> responseUtils = new ResponseUtils<String>(StatusCode.NOTLOGIN.getName(), StatusCode.NOTLOGIN.getIndex(), null);
            response.setContentType("application/json;charset=UTF-8"); 
            response.getWriter().print(mapper.writeValueAsString(responseUtils));
            
           
            return false;
        }
    }

    protected boolean preHandle(ServletRequest servletRequest, ServletResponse servletResponse) throws Exception {
    	
    		log.info("access 进入");
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        response.addHeader("Access-Control-Allow-Origin"	,request.getHeader("Origin"));
        response.addHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
        response.addHeader("Access-Control-Allow-Headers", "origin, content-type, accept, x-requested-with, sid, mycustom, smuser,AUTHORIZTION_FLAG");
        response.addHeader("Access-Control-Max-Age", "1800");//30 min
        response.setHeader("Access-Control-Allow-Credentials", "true");
        
        
        System.out.println("AUTHORIZTION_FLAG 2: "+request.getHeader("AUTHORIZTION_FLAG"));
        
        if(request.getMethod().equals(RequestMethod.OPTIONS.name()))
        {
        		log.info("access 通过");
            response.setStatus(HttpStatus.OK.value());
            return true;
        }else {
        		 log.info("access 不需要验证");
             
        }
        return super.preHandle(request, response);
    }

}
