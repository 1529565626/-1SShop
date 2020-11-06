package com.ssk.shop.vo.admin.permission;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotEmpty;

@ApiModel(value = "权限新增模型")
@Getter
@Setter
@ToString
public class InsertAdminPermissionVO {

    /**
     * 相对应权限页面
     */
    @ApiModelProperty(value = "相对应权限页面", required = true, example = "")
    private String pageItemId;

    public String getPageItemId() {
        return pageItemId;
    }

    public void setPageItemId(String pageItemId) {
        this.pageItemId = pageItemId;
    }

    /**
     * 权限说明
     */
    @ApiModelProperty(value = "权限说明", required = true, example = "")
    @NotEmpty(message = "权限说明不能为空")
    private String permissionRemark;
    /**
     * 权限名
     */
    @ApiModelProperty(value = "权限名,注意：是permissionName而不是permissionId", required = true, example = "ADMIN,ADMIN_NORMAL")
    @NotEmpty(message = "权限名不能为空")
    private String permissionName;

    /**
     * 权限分类
     */
    @NotEmpty(message = "权限分类不能为空")
    @ApiModelProperty(value = "权限分类" ,required = true,example = "")
    private String jurisdictionId;

}
