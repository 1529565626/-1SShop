package com.ssk.shop.dao.impl;

import com.ssk.shop.entity.AdminPermission;
import com.ssk.shop.dao.IAdminPermissionDao;
import com.ssk.shop.repository.AdminPermissionMapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.ssk.utils.CommonPage;
import com.ssk.utils.GuidGeneratorUtil;
import com.ssk.utils.ObjectHelper;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import java.util.List;

/**
 * admin权限表(AdminPermission)表服务实现类
 *
 * @author ssk
 * @since 2020-11-03 08:09:00
 */
@Service("adminPermissionDaoImpl")
public class AdminPermissionDaoImpl extends ServiceImpl<AdminPermissionMapper, AdminPermission> implements IAdminPermissionDao{

    @Override
    public AdminPermission selectByName(String name) {
        EntityWrapper<AdminPermission> wrapper = new EntityWrapper<>();
        wrapper.eq("permission_name", name);
        List<AdminPermission> permissions = baseMapper.selectList(wrapper);
        if (permissions.size() > 0) {
            return permissions.get(0);
        }
        return null;
    }

    @Override
    public String addPermission(AdminPermission newPermission) {
        String uuid = GuidGeneratorUtil.generate();
        newPermission.setAdminPermissionId(uuid);
        if (baseMapper.insert(newPermission) > 0) {
            return uuid;
        } else {
            return null;
        }
    }

    @Override
    public List<AdminPermission> RoleList(CommonPage<AdminPermission> pageUtil) {
        EntityWrapper<AdminPermission> wrapper = new EntityWrapper<>();
        return baseMapper.selectPage(pageUtil, wrapper);
    }
    

}