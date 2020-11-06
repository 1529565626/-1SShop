package com.ssk.shop.dao;

import com.ssk.shop.entity.AdminPermission;
import com.baomidou.mybatisplus.service.IService;
import com.ssk.utils.CommonPage;

import java.util.List;

/**
 * admin权限表(AdminPermission)表数据库处理层
 *
 * @author ssk
 * @since 2020-11-03 08:09:00
 */
public interface IAdminPermissionDao extends IService<AdminPermission>{

    AdminPermission selectByName(String name);

    String addPermission(AdminPermission newPermission);

    List<AdminPermission> RoleList(CommonPage<AdminPermission> pageUtil);

}