package com.ssk.shop.bll;

import com.ssk.utils.CommonPage;
import com.ssk.exception.ServiceException;
import com.ssk.shop.entity.AdminPermission;
import java.util.List;

/**
 * admin权限表(AdminPermission)Bll层接口
 *
 * @author ssk
 * @since 2020-11-03 08:09:00
 */
public interface IAdminPermissionBll {

    AdminPermission selectByName(String name);

    List<AdminPermission> getAdminPermissionsByNames(String names);

    String insertPermission(AdminPermission newPermission)throws ServiceException;

    List<AdminPermission> permissionList(CommonPage<AdminPermission> resultPage);

    List<AdminPermission> allPermissionList();

}