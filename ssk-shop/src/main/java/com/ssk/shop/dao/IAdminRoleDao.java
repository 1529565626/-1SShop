package com.ssk.shop.dao;

import com.ssk.shop.entity.AdminRole;
import com.baomidou.mybatisplus.service.IService;
import com.ssk.utils.CommonPage;

import java.util.List;

/**
 * 角色表(AdminRole)表数据库处理层
 *
 * @author ssk
 * @since 2020-11-03 08:11:22
 */
public interface IAdminRoleDao extends IService<AdminRole>{

    AdminRole selectByName(String roleName);

    String addRole(AdminRole role);

    boolean updateRole(AdminRole role);

    List<AdminRole> RoleList(CommonPage<AdminRole> pageInfo, String roleName, String rolePermission, Integer enable);

    AdminRole getRole(String uuid);

}