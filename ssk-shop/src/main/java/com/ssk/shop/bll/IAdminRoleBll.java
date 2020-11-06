package com.ssk.shop.bll;

import com.ssk.exception.ServiceException;
import com.ssk.shop.entity.AdminRole;
import com.ssk.utils.CommonPage;

import java.util.List;

/**
 * 角色表(AdminRole)Bll层接口
 *
 * @author ssk
 * @since 2020-11-03 08:11:22
 */
public interface IAdminRoleBll {

    AdminRole getRole(String adminRole);

    String addRole(AdminRole newRole) throws ServiceException;

    boolean updateRole(AdminRole role) throws ServiceException;

    boolean deleteRole(String uuid) throws ServiceException;

    List<AdminRole> RoleList(CommonPage<AdminRole> pageInfo, String roleName, String rolePermission, Integer enable);

}