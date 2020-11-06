package com.ssk.shop.bll.impl;

import com.ssk.exception.ServiceException;
import com.ssk.shop.bll.IAdminPermissionBll;
import com.ssk.shop.config.constants.ProjectConstants;
import com.ssk.shop.dao.IAdminPermissionDao;
import com.ssk.utils.CommonPage;
import com.ssk.utils.ObjectHelper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import com.ssk.shop.entity.AdminPermission;

import javax.annotation.Resource;

/**
 * admin权限表(AdminPermission)表服务实现类
 *
 * @author ssk
 * @since 2020-11-03 08:09:00
 */
@Service("adminPermissionBll")
public class IAdminPermissionBllImpl implements IAdminPermissionBll {
    private Logger LOG = LogManager.getLogger(IAdminPermissionBllImpl.class);
    @Resource
    private IAdminPermissionDao IAdminPermissionDao;

    @Resource(name = "redisTemplate")
    private HashOperations<String, String, AdminPermission> permissionTemp;

    @Override
    public AdminPermission selectByName(String name) {
        AdminPermission permission;
        if (permissionTemp.hasKey(ProjectConstants.ADMIN_PERMISSION_REDIS_KEY, name)) {
            permission = permissionTemp.get(ProjectConstants.ADMIN_PERMISSION_REDIS_KEY, name);
        } else {
            permission = IAdminPermissionDao.selectByName(name);
            if (permission != null) {
                permissionTemp.put(ProjectConstants.ADMIN_PERMISSION_REDIS_KEY, name, permission);
            }
        }
        return permission;
    }

    @Override
    public List<AdminPermission> getAdminPermissionsByNames(String names) {
        String[] permissionNames = names.split(",");
        List<AdminPermission> permissions = new ArrayList<>();
        for (String permissionName : permissionNames) {
            AdminPermission newPermission = selectByName(permissionName);
            if (ObjectHelper.isNotEmpty(newPermission)) {
                permissions.add(newPermission);
            }
        }
        return permissions;
    }

    @Override
    public String insertPermission(AdminPermission newPermission) throws ServiceException {

        if (selectByName(newPermission.getPermissionName()) != null) {
            throw new ServiceException("权限名重复");
        }

        String newid = IAdminPermissionDao.addPermission(newPermission);
        if (ObjectHelper.isNotEmpty(newid)){
            return newid;
        }else {
            throw new ServiceException("添加失败");
        }
    }

    @Override
    public List<AdminPermission> permissionList(CommonPage<AdminPermission> resultPage) {
        return IAdminPermissionDao.RoleList(resultPage);
    }


    @Override
    public List<AdminPermission> allPermissionList() {
        return IAdminPermissionDao.selectList(null);
    }

}