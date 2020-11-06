package com.ssk.shop.facade.impl;

import com.ssk.constants.ResultConstants;
import com.ssk.enums.BooleanEnum;
import com.ssk.exception.ServiceException;
import com.ssk.shop.bll.IAdminPermissionBll;
import com.ssk.shop.config.constants.ProjectConstants;
import com.ssk.shop.dto.admin.AdminRoleDto;
import com.ssk.shop.entity.AdminPermission;
import com.ssk.shop.vo.admin.role.InsertRoleVO;
import com.ssk.shop.vo.admin.role.UpdateRoleVO;
import com.ssk.utils.CommonPage;
import com.ssk.utils.ObjectHelper;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.stereotype.Service;
import com.ssk.shop.facade.IAdminRoleFacade;
import com.ssk.shop.bll.IAdminRoleBll;
import javax.annotation.Resource;
import com.ssk.shop.entity.AdminRole;
import org.modelmapper.ModelMapper;
import java.util.List;

/**
 * 角色表(AdminRole)表门面实现类
 *
 * @author ssk
 * @since 2020-11-03 08:11:22
 */
@Service("adminRoleFacade")
public class AdminRoleFacadeImpl implements IAdminRoleFacade {
    @Resource
    private IAdminRoleBll roleBll;

    @Resource(name = "redisTemplate")
    private HashOperations<String, String, AdminRoleDto> roleTemp;


    @Resource
    private IAdminPermissionBll permissionBll;


    @Override
    public String addRole(InsertRoleVO role) throws ServiceException {
        AdminRole newRole = new ModelMapper().map(role, AdminRole.class);
        String uuid=roleBll.addRole(newRole);
        if (ObjectHelper.isEmpty(uuid)){
            throw new ServiceException("添加失败，请联系管理员");
        }
        return ResultConstants.ADD_SUCCESS ;
    }

    @Override
    public String updateRole(UpdateRoleVO roleVo) throws ServiceException {
        AdminRole role = new ModelMapper().map(roleVo, AdminRole.class);
        if (roleBll.updateRole(role)) {
            if (roleTemp.hasKey(ProjectConstants.ROLE_REDIS_KEY, role.getRoleId())) {
                roleTemp.delete(ProjectConstants.ROLE_REDIS_KEY, role.getRoleId());
            }
            return ResultConstants.UPDATE_SUCCESS ;
        }

        return ResultConstants.UPDATE_FAIL ;

    }

    @Override
    public String deleteRole(String uuid) throws ServiceException {
        if (roleBll.deleteRole(uuid)) {
            if (roleTemp.hasKey(ProjectConstants.ROLE_REDIS_KEY, uuid)) {
                roleTemp.delete(ProjectConstants.ROLE_REDIS_KEY, uuid);
            }
            return ResultConstants.DELETE_SUCCESS;
        }
        return ResultConstants.DELETE_FAIL;
    }

    @Override
    public CommonPage<AdminRole> RoleList(String roleName, String rolePermission, Integer enable, Integer page, Integer pageSize) throws ServiceException {
        CommonPage<AdminRole> resultPage = new CommonPage<>(page,pageSize);
        List<AdminRole> roles = roleBll.RoleList(resultPage,roleName,rolePermission,enable);
        resultPage.setRecords(roles);
        return resultPage;
    }

    @Override
    public AdminRoleDto getRole(String uuid) throws ServiceException {
        AdminRoleDto roleDto;
        if (roleTemp.hasKey(ProjectConstants.ROLE_REDIS_KEY, uuid)) {
            roleDto = roleTemp.get(ProjectConstants.ROLE_REDIS_KEY, uuid);
        } else {
            AdminRole role = roleBll.getRole(uuid);
            //空或者已删除
            if (ObjectHelper.isEmpty(role) || role.getDeleteFlag().equals(BooleanEnum.TRUE.getCode())) {
                return null;
            }
            List<AdminPermission> permissions = permissionBll.getAdminPermissionsByNames(role.getRolePermission());
            roleDto = new AdminRoleDto();
            roleDto.setRoleMsg(role);
            roleDto.setPermissions(permissions);
            roleTemp.put(ProjectConstants.ROLE_REDIS_KEY, uuid, roleDto);
        }
        return roleDto;
    }

}