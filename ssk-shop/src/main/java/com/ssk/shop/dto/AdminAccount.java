package com.ssk.shop.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

@ApiModel("管理员账号模型")
public class AdminAccount {
    /**
     * 主键
     */
    @ApiModelProperty(value = "主键")
    private String adminInfoId;

    /**
     * 账号
     */
    @ApiModelProperty(value = "账号")
    private String account;


    /**
     * 角色(grade_id)
     */
    @ApiModelProperty(value = "角色(grade_id)")
    private String adminRole;

    /**
     * 角色名
     */
    @ApiModelProperty(value = "角色名")
    private String roleName;

    /**
     * 名称
     */
    @ApiModelProperty(value = "名称")
    private String name;

    /**
     * 性别（0：男，1：女）
     */
    @ApiModelProperty(value = "性别（0：男，1：女）")
    private Integer sex;

    /**
     * 电话
     */
    @ApiModelProperty(value = "电话")
    private String phone;

    /**
     * 管理员头像
     */
    @ApiModelProperty(value = "管理员头像")
    private String adminImg;

    @ApiModelProperty(value = "权限路由")
    private List<String> permissionList;

    public List<String> getPermissionList() {
        return permissionList;
    }

    public void setPermissionList(List<String> permissionList) {
        this.permissionList = permissionList;
    }

    public String getAdminImg() {
        return adminImg;
    }

    public void setAdminImg(String adminImg) {
        this.adminImg = adminImg;
    }

    public String getAdminInfoId() {
        return adminInfoId;
    }

    public void setAdminInfoId(String adminInfoId) {
        this.adminInfoId = adminInfoId;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getAdminRole() {
        return adminRole;
    }

    public void setAdminRole(String adminRole) {
        this.adminRole = adminRole;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
}
