package com.ssk.shop.facade;

import com.ssk.exception.ServiceException;
import com.ssk.shop.dto.admin.AdminRoleDto;
import com.ssk.shop.entity.AdminRole;
import com.ssk.shop.vo.admin.role.InsertRoleVO;
import com.ssk.shop.vo.admin.role.UpdateRoleVO;
import com.ssk.utils.CommonPage;


/**
 * 角色表(AdminRole)Facade层接口
 *
 * @author ssk
 * @since 2020-11-03 08:11:22
 */
public interface IAdminRoleFacade {

    /**
     * 新增角色
     *
     * @param role
     * @return
     */
    String addRole(InsertRoleVO role)throws ServiceException;

    /**
     * 修改角色
     *
     * @param role
     * @return
     */
    String updateRole(UpdateRoleVO role)throws ServiceException;


    /**
     * 删除角色
     *
     * @param uuid
     * @return
     */
    String deleteRole(String uuid)throws ServiceException;

    /**
     * 角色集合
     *
     * @param roleName
     * @param rolePermission
     * @param enable
     * @param page
     * @param pageSize
     * @return
     */
    CommonPage<AdminRole> RoleList(String roleName, String rolePermission, Integer enable, Integer page, Integer pageSize) throws ServiceException;

    /**
     * 角色详情
     *
     * @param uuid
     * @return
     */
    AdminRoleDto getRole(String uuid)throws ServiceException;

}