package com.ssk.shop.dao.impl;

import com.ssk.enums.BooleanEnum;
import com.ssk.shop.entity.AdminRole;
import com.ssk.shop.dao.IAdminRoleDao;
import com.ssk.shop.repository.AdminRoleMapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.ssk.utils.CommonPage;
import com.ssk.utils.GuidGeneratorUtil;
import com.ssk.utils.ObjectHelper;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.mapper.EntityWrapper;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 角色表(AdminRole)表服务实现类
 *
 * @author ssk
 * @since 2020-11-03 08:11:22
 */
@Service("adminRoleDaoImpl")
public class AdminRoleDaoImpl extends ServiceImpl<AdminRoleMapper, AdminRole> implements IAdminRoleDao{

    @Override
    public AdminRole selectByName(String roleName) {
        EntityWrapper<AdminRole> wrapper = new EntityWrapper<>();
        wrapper.eq("role_name", roleName);
        wrapper.eq("is_delete", BooleanEnum.FALSE.getCode());
        List<AdminRole> permissions = baseMapper.selectList(wrapper);
        if (permissions.size() > 0) {
            return permissions.get(0);
        }
        return null;
    }

    @Override
    public String addRole(AdminRole role) {
        String uuid = GuidGeneratorUtil.generate();
        role.setRoleId(uuid);
        role.setDeleteFlag(BooleanEnum.FALSE.getCode());
        role.setCreateTime(new Date());
        if (baseMapper.insert(role) > 0) {
            return uuid;
        } else {
            return null;
        }
    }

    @Override
    public boolean updateRole(AdminRole role) {
        return baseMapper.updateById(role) > 0;
    }

    @Override
    public boolean deleteById(Serializable id) {
        AdminRole nrole = new AdminRole();
        nrole.setRoleId(id.toString());
        nrole.setDeleteFlag(BooleanEnum.TRUE.getCode());
        return baseMapper.updateById(nrole) > 0;
    }

    @Override
    public List<AdminRole> RoleList(CommonPage<AdminRole> pageInfo, String roleName, String rolePermission, Integer enable) {
        EntityWrapper<AdminRole> wrapper = new EntityWrapper<>();
        if (!ObjectHelper.isEmpty(roleName)) {
            wrapper.like("role_name", roleName);
        }
        if (!ObjectHelper.isEmpty(rolePermission)) {
            wrapper.like("role_permission", rolePermission);
        }
        if (enable != null) {
            wrapper.eq("enable", enable);
        }
        wrapper.eq("is_delete", BooleanEnum.FALSE.getCode());
        wrapper.orderBy("create_time",false);
        return baseMapper.selectPage(pageInfo, wrapper);
    }

    @Override
    public AdminRole getRole(String uuid) {
        return baseMapper.selectById(uuid);
    }
    

}