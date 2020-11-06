package com.ssk.shop.config.swagger;

public enum  SwaggerUserGroupEnum {
    USER(0,"用户端"),
    ADMIN(1,"管理后端");

    SwaggerUserGroupEnum(int code,String role){
        this.code = code;
        this.role = role;
    }

    private int code;

    private String role;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
