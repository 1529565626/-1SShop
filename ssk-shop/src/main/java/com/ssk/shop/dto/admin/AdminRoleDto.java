package com.ssk.shop.dto.admin;

import com.ssk.shop.entity.AdminPermission;
import com.ssk.shop.entity.AdminRole;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

/**
 * @Author: ssk
 * @Date: 2020/11/4 23:09
 */
@Getter
@Setter
@ToString
public class AdminRoleDto implements Serializable {
    /**
     * 角色基础信息
     */
    private AdminRole roleMsg;
    /**
     * 角色权限
     */
    private List<AdminPermission> permissions;

}
