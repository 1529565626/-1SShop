package com.ssk.shop.vo.admin.role;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@ApiModel(value = "角色新增模型")
@Getter
@Setter
@ToString
public class InsertRoleVO {
    /**
     * 角色名称
     */
    @NotEmpty(message = "角色名称不能未空")
    @ApiModelProperty(value = "角色名称", required = true, example = "管理员")
    private String roleName;
    /**
     * 角色权限(可看见的菜单)
     */
    @NotEmpty(message = "角色权限不能未空")
    @ApiModelProperty(value = "角色权限", required = true, example = "ADMIN")
    private String rolePermission;
    /**
     * 角色备注
     */
    @NotEmpty(message = "角色备注不能未空")
    @ApiModelProperty(value = "角色备注", required = true, example = "系统管理员")
    private String remark;

    /**
     * 角色状态
     */
    @NotNull(message = "角色状态不能未空")
    @ApiModelProperty(value = "角色状态 1 启用 0 禁用", required = true, example = "1")
    @Max(1)
    @Min(0)
    private Integer enable;
}
