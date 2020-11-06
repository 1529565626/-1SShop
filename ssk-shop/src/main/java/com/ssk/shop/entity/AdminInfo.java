package com.ssk.shop.entity;

import java.util.Date;
import java.io.Serializable;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * (AdminInfo)实体类
 *
 * @author ssk
 * @since 2020-11-03 08:08:36
 */
@Setter
@Getter
@ToString
public class AdminInfo implements Serializable {
    private static final long serialVersionUID = -43093063185844059L;
    /**
    * 主键
    */
    @TableId
    @ApiModelProperty(value = "主键")
    private String adminInfoId;

    /**
    * 账号
    */
    @TableField("account")
    @ApiModelProperty(value = "账号")
    private String account;
    
    /**
    * 密码
    */
    @TableField("password")
    @ApiModelProperty(value = "密码")
    private String password;
    
    /**
    * 用户部门
    */
    @TableField("admin_department_id")
    @ApiModelProperty(value = "用户部门")
    private String adminDepartmentId;
    
    /**
    * 角色(grade_id)
    */
    @TableField("admin_role")
    @ApiModelProperty(value = "角色(grade_id)")
    private String adminRole;
    
    /**
    * 名称
    */
    @TableField("name")
    @ApiModelProperty(value = "名称")
    private String name;
    
    /**
    * 性别（0：男，1：女）
    */
    @TableField("sex")
    @ApiModelProperty(value = "性别（0：男，1：女）")
    private Integer sex;
    
    /**
    * 电话
    */
    @TableField("phone")
    @ApiModelProperty(value = "电话")
    private String phone;
    
    /**
    * 是否启用 1 启用 0 禁用
    */
    @TableField("enable_state")
    @ApiModelProperty(value = "是否启用 1 启用 0 禁用")
    private Integer enableState;
    
    /**
    * 创建时间
    */
    @TableField("create_time")
    @ApiModelProperty(value = "创建时间")
    private Date createTime;
    
    /**
    * 是否删除 0 未删除 1 删除
    */
    @TableField("delete_flag")
    @ApiModelProperty(value = "是否删除 0 未删除 1 删除")
    private Integer deleteFlag;
    
    /**
    * 组织id
    */
    @TableField("organization_id")
    @ApiModelProperty(value = "组织id")
    private String organizationId;

}