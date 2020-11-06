package com.ssk.constants;

import java.util.regex.Pattern;

/**
 * @Author: ssk
 * @Date: 2020/11/4 22:31
 */
public interface RegularExpression {

    //分割字符串的分隔符
    String SEPARATOR = ",";
    //匹配32个字母和数字,用于检测UUID码
    String UUID = "[a-zA-Z0-9]{32}";
    //匹配大小写字母、数字、下划线
    String NOTCHINESE = "[a-zA-Z0-9_]{1,250}";
    //匹配账号
    String ACCOUNT = "[a-zA-Z0-9_]{4,16}";
    //匹配手机号
    String PHONE = "^1(3|4|5|7|8|9)[0-9]{9}$";
    //匹配电子邮箱
    String EMAIL = "^[0-9a-zA-Z]{0,14}@[0-9a-zA-Z]{1,14}.(com|cn|net|asp|com.cn|cn.com)$";
    //匹配大陆身份证，支持18位
    String IDCART = "(^[1-9]\\d{5}(18|19|20)\\d{2}((0[1-9])|(10|11|12))" +
            "(([0-2][1-9])|10|20|30|31)\\d{3}[0-9Xx]$)|(^[1-9]\\d{5}\\d{2}((0[1-9])|(10|11|12))" +
            "(([0-2][1-9])|10|20|30|31)\\d{3}$)";
    //匹配密码，限制密码最低6位最高16位
    String ISPASSWORD = "[a-zA-Z0-9]{6,16}";

    //其他字符
    String OTHERSTR = "(\\s|\'|\"| ){1,}";

    //判断是否为非中文字符
    static boolean isNotChinese(String str){
        return Pattern.matches(NOTCHINESE, str);
    }

    //判断是否为手机号码
    static boolean isPhoneNumber(String str){
        return Pattern.matches(PHONE, str);
    }

    //判断是否为电子邮箱
    static boolean isEmail(String str){
        return Pattern.matches(EMAIL, str);
    }

    //判断是否为UUID码
    static boolean isUUID(String str){
        return Pattern.matches(UUID, str);
    }

    //判断是否为身份证号码
    static boolean isIdcard(String str){
        return Pattern.matches(IDCART, str);
    }

    //匹配是否符合密码格式
    static boolean isPassword(String str){
        return Pattern.matches(ISPASSWORD, str);
    }
    
}
