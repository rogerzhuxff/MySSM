package com.demo.common;
public enum StatusCode {
     

    SUCCESS_CODE("成功", 200),
	NOTLOGIN("用户未登录",1001),
    UNAUTHORIZED("未授权",1002),
    INSERTERROR("插入失败",1003),
    REQUESTERROR("请求异常",1004),
    LOGINFAIL("登录失败",1005),
    DELETEERROR("删除失败",1006),
    UPDATEERROR("跟新失败",1007),
    OPTERROR("操作失败",1008),
    ;
    
     
     
    private String name ;
    private int index ;
     
    private StatusCode( String name , int index ){
        this.name = name ;
        this.index = index ;
    }
     
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getIndex() {
        return index;
    }
    public void setIndex(int index) {
        this.index = index;
    }
     
 
}
