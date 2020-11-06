package com.ssk.enums;

/**
 * @Author: ssk
 * @Date: 2020/11/4 22:26
 */
public enum BooleanEnum {

    TRUE(1),FALSE(0);

    BooleanEnum(int code){
        this.code = code;
    }


    private int code;

    /**
     * 通过code寻找枚举
     * @param code
     * @return
     */
    public static BooleanEnum getByCode(int code) {
        for (BooleanEnum status : BooleanEnum.values()) {
            if (code == status.getCode()) {
                return status;
            }
        }
        return FALSE;
    }

    public int getCode() {
        return code;
    }

}
