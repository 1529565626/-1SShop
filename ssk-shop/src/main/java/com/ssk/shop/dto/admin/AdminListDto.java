package com.ssk.shop.dto.admin;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

/**
 * @Author: ssk
 * @Date: 2020/11/4 22:55
 */
@Getter
@Setter
@ToString
public class AdminListDto {
    /**
     * 主键
     */
    @ApiModelProperty("用户id")
    private String adminInfoId;
    /**
     * 账号
     */
    @ApiModelProperty("账号")
    private String account;
    /**
     * 密码
     */
    @ApiModelProperty("密码")
    private String password;
    /**
     * 角色(grade_id)
     */
    @ApiModelProperty("角色id")
    private String adminRole;

    /**
     * 角色名
     */
    @ApiModelProperty("角色名")
    private String roleName;

    /**
     * 所属部门
     */
    @ApiModelProperty("所属部门")
    private String adminDepartmentName;

    /**
     * 名称
     */
    @ApiModelProperty("名称")
    private String name;
    /**
     * 性别（0：男，1：女）
     */
    @ApiModelProperty(value = "性别（0：男，1：女）",example = "0")
    private Integer sex;
    /**
     * 电话
     */
    @ApiModelProperty("电话")
    private String phone;
    /**
     * 是否启用
     */
    @ApiModelProperty(value = "是否启用",example = "0")
    private Integer enableState;
    /**
     * 创建时间
     */
    @ApiModelProperty("创建时间")
    private Date createTime;

}
