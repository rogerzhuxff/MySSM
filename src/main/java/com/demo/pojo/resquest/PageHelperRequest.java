package com.demo.pojo.resquest;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


@ApiModel("分页请求参数")
public class PageHelperRequest {
	
	
	@ApiModelProperty("起始页")
	private int pageNum;
	
	@ApiModelProperty("每页数量")
	private int pageSize;

	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	
	
	
	

}
