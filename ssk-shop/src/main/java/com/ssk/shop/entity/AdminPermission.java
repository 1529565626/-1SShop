package com.ssk.shop.entity;

import java.io.Serializable;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * admin权限表(AdminPermission)实体类
 *
 * @author ssk
 * @since 2020-11-03 08:09:00
 */

@Setter
@Getter
@ToString
public class AdminPermission implements Serializable {
    private static final long serialVersionUID = 539113320509350178L;
    /**
    * id
    */
    @TableId
    @ApiModelProperty(value = "id")
    private String adminPermissionId;

    /**
    * 权限说明
    */
    @TableField("permission_remark")
    @ApiModelProperty(value = "权限说明")
    private String permissionRemark;
    
    /**
    * 权限名
    */
    @TableField("permission_name")
    @ApiModelProperty(value = "权限名")
    private String permissionName;
    
    /**
    * 相对应权限页面
    */
    @TableField("page_item_id")
    @ApiModelProperty(value = "相对应权限页面")
    private String pageItemId;
    
    /**
    * 权限分类
    */
    @TableField("jurisdiction_id")
    @ApiModelProperty(value = "权限分类")
    private String jurisdictionId;
    

    
    public String getAdminPermissionId() {
        return adminPermissionId;
    }

    public void setAdminPermissionId(String adminPermissionId) {
        this.adminPermissionId = adminPermissionId;
    }
    
    public String getPermissionRemark() {
        return permissionRemark;
    }

    public void setPermissionRemark(String permissionRemark) {
        this.permissionRemark = permissionRemark;
    }
    
    public String getPermissionName() {
        return permissionName;
    }

    public void setPermissionName(String permissionName) {
        this.permissionName = permissionName;
    }
    
    public String getPageItemId() {
        return pageItemId;
    }

    public void setPageItemId(String pageItemId) {
        this.pageItemId = pageItemId;
    }
    
    public String getJurisdictionId() {
        return jurisdictionId;
    }

    public void setJurisdictionId(String jurisdictionId) {
        this.jurisdictionId = jurisdictionId;
    }

@Override
    public String toString() {
        return "AdminPermission["+
            "adminPermissionId='" + adminPermissionId + '\'' +
            ",permissionRemark='" + permissionRemark + '\'' +
            ",permissionName='" + permissionName + '\'' +
            ",pageItemId='" + pageItemId + '\'' +
            ",jurisdictionId='" + jurisdictionId + '\'' +
     "]";
    }
}