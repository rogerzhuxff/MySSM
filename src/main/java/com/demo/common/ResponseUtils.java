package com.demo.common;
public class ResponseUtils<T>  {

    private String message;
    private int code;
    private T data;


	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public Object getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public ResponseUtils() {
		// TODO Auto-generated constructor stub
	}
	
	public ResponseUtils(String message, int code, T data) {
		super();
		this.message = message;
		this.code = code;
		this.data = data;
	}
    
    
}