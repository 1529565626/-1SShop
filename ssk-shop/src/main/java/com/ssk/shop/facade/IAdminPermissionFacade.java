package com.ssk.shop.facade;

import com.ssk.exception.ServiceException;
import com.ssk.shop.entity.AdminPermission;
import com.ssk.shop.vo.admin.permission.InsertAdminPermissionVO;
import com.ssk.utils.CommonPage;

import java.util.List;


/**
 * admin权限表(AdminPermission)Facade层接口
 *
 * @author ssk
 * @since 2020-11-03 08:09:00
 */
public interface IAdminPermissionFacade {

    /**
     * 根据权限名获取权限实体
     *
     * @param name
     * @return
     */
    AdminPermission selectByName(String name);

    /**
     * 根据权限名集合获取权限实体集
     *
     * @param names
     * @return
     */
    List<AdminPermission> getAdminPermissionsByNames(String names);

    /**
     * 新增权限
     *
     * @param permission
     * @return
     */
    String insertAdminPermission(InsertAdminPermissionVO permission)throws ServiceException;

    /**
     * 权限列表
     *
     * @param page
     * @param pageSize
     * @return
     */
    CommonPage<AdminPermission> selectAdminPermission(Integer page, Integer pageSize) throws ServiceException;



    List<AdminPermission> allPermissionList();

}