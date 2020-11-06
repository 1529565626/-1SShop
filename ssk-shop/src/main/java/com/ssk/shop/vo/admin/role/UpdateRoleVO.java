package com.ssk.shop.vo.admin.role;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotEmpty;

@ApiModel(value = "角色更新模型")
public class UpdateRoleVO extends InsertRoleVO {

    /**
     * 角色id
     */
    @NotEmpty(message = "角色id不能未空")
    @ApiModelProperty(value = "角色id", required = true, example = "")
    private String roleId;

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }
}
