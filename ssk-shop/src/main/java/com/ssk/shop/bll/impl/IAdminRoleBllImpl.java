package com.ssk.shop.bll.impl;

import com.ssk.exception.ServiceException;
import com.ssk.shop.bll.IAdminInfoBll;
import com.ssk.shop.bll.IAdminRoleBll;
import com.ssk.shop.dao.IAdminRoleDao;
import com.ssk.shop.entity.AdminInfo;
import com.ssk.utils.CommonPage;
import com.ssk.utils.ObjectHelper;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import java.util.List;
import com.ssk.shop.entity.AdminRole;

import javax.annotation.Resource;

/**
 * 角色表(AdminRole)表服务实现类
 *
 * @author ssk
 * @since 2020-11-03 08:11:22
 */
@Service("adminRoleBll")
public class IAdminRoleBllImpl implements IAdminRoleBll {
    @Resource
    private IAdminRoleDao IAdminRoleDao;

    @Resource
    private IAdminInfoBll adminInfoBll;

    @Override
    public AdminRole getRole(String adminRole) {
        return IAdminRoleDao.getRole(adminRole);
    }

    @Override
    public String addRole(AdminRole newRole) throws ServiceException {
        if (IAdminRoleDao.selectByName(newRole.getRoleName()) != null) {
            throw new ServiceException("角色名重复");
        }
        String newId = IAdminRoleDao.addRole(newRole);
        if (ObjectHelper.isNotEmpty(newId)){
            return newId;
        }else {
            throw new ServiceException("添加失败");
        }
    }

    @Override
    public boolean updateRole(AdminRole role) throws ServiceException {
        AdminRole oldRole = IAdminRoleDao.selectByName(role.getRoleName());
        if (oldRole != null && !oldRole.getRoleId().equals(role.getRoleId()) && oldRole.getRoleName().equals(role.getRoleName())) {
            throw new ServiceException("角色名重复");
        }
        return IAdminRoleDao.updateRole(role);
    }

    @Override
    public boolean deleteRole(String uuid) throws ServiceException {
        String thisAdminName = SecurityContextHolder.getContext().getAuthentication().getName();
        AdminInfo thisAdmin = adminInfoBll.getAdminByAccount(thisAdminName);
        //判空 id
        if (thisAdmin == null) {
            throw new ServiceException("账号异常");
        }
        AdminRole role = getRole(uuid);
        if (role.getRolePermission().contains("SUPER")){
            throw new ServiceException("权限不足无法删除");
        }
        return IAdminRoleDao.deleteById(uuid);
    }

    @Override
    public List<AdminRole> RoleList(CommonPage<AdminRole> pageInfo, String roleName, String rolePermission, Integer enable) {
        return IAdminRoleDao.RoleList(pageInfo,roleName, rolePermission, enable);
    }

}