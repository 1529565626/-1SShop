package com.ssk.shop.vo.admin;

import com.ssk.constants.RegularExpression;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

/**
 * @Author: ssk
 * @Date: 2020/11/4 22:29
 */
@Getter
@Setter
@ToString
@ApiModel(value = "管理员新增模型")
public class InsertAdminVO {


    /**
     * 账号
     */
    @NotEmpty(message = "账号不能未空")
    @Pattern(regexp = RegularExpression.ACCOUNT,message = "账号格式异常")
    @ApiModelProperty(value = "账号", required = true, example = "")
    private String account;
    /**
     * 密码
     */
    @NotEmpty(message = "密码不能未空")
    @Pattern(regexp = RegularExpression.ISPASSWORD,message = "密码格式异常")
    @ApiModelProperty(value = "密码", required = true, example = "******")
    private String password;
    /**
     * 名称
     */
    @NotEmpty(message = "名字不能未空")
    @ApiModelProperty(value = "名字", required = true, example = "姓名")
    private String name;
    /**
     * 电话
     */
    @NotEmpty(message = "电话不能未空")
    @Pattern(regexp = RegularExpression.PHONE,message = "电话格式异常")
    @ApiModelProperty(value = "电话", required = true, example = "15295656260")
    private String phone;

    /**
     * 角色id
     */
    @NotEmpty(message = "角色id不能未空")
    @ApiModelProperty(value = "角色id", required = true, example = "")
    private String adminRole;

}
