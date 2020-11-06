package com.ssk.shop.config.swagger;

public enum SwaggerVersionGroupEnum {
    V1(1,"農脉一期"),
    V2(2,"農脉二期");

    SwaggerVersionGroupEnum(int code,String version){
        this.code = code;
        this.version = version;
    }

    private int code;

    private String version;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }
}
