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
 * 角色表(AdminRole)实体类
 *
 * @author ssk
 * @since 2020-11-03 08:11:21
 */

@Setter
@Getter
@ToString
public class AdminRole implements Serializable {
    private static final long serialVersionUID = 387509105193480410L;
    /**
    * 角色id
    */
    @TableId
    @ApiModelProperty(value = "角色id")
    private String roleId;
    
    /**
    * 角色名称
    */
    @TableField("role_name")
    @ApiModelProperty(value = "角色名称")
    private String roleName;
    
    /**
    * 角色权限(可看见的菜单)
    */
    @TableField("role_permission")
    @ApiModelProperty(value = "角色权限(可看见的菜单)")
    private String rolePermission;
    
    /**
    * 备注
    */
    @TableField("remark")
    @ApiModelProperty(value = "备注")
    private String remark;
    
    /**
    * 创建时间
    */
    @TableField("create_time")
    @ApiModelProperty(value = "创建时间")
    private Date createTime;
    
    /**
    * 启用状态 1 启用 0 禁用
    */
    @TableField("enable_state")
    @ApiModelProperty(value = "启用状态 1 启用 0 禁用")
    private Object enableState;
    
    /**
    * 是否删除 1已删除 0未删除
    */
    @TableField("delete_flag")
    @ApiModelProperty(value = "是否删除 1已删除 0未删除")
    private Object deleteFlag;
    
    /**
    * 组织id
    */
    @TableField("organization_id")
    @ApiModelProperty(value = "组织id")
    private String organizationId;
    
    /**
    * 角色类型
    */
    @TableField("role_type")
    @ApiModelProperty(value = "角色类型")
    private String roleType;

}