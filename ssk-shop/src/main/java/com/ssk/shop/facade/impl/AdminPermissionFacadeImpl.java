package com.ssk.shop.facade.impl;

import com.ssk.constants.ResultConstants;
import com.ssk.exception.ServiceException;
import com.ssk.shop.vo.admin.permission.InsertAdminPermissionVO;
import com.ssk.utils.CommonPage;
import com.ssk.utils.ObjectHelper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import com.ssk.shop.facade.IAdminPermissionFacade;
import com.ssk.shop.bll.IAdminPermissionBll;
import javax.annotation.Resource;
import com.ssk.shop.entity.AdminPermission;
import java.util.List;

/**
 * admin权限表(AdminPermission)表门面实现类
 *
 * @author ssk
 * @since 2020-11-03 08:09:00
 */
@Service("adminPermissionFacade")
public class AdminPermissionFacadeImpl implements IAdminPermissionFacade {
    private Logger LOG = LogManager.getLogger(AdminPermissionFacadeImpl.class);

    @Resource
    private IAdminPermissionBll adminPermissionBll;


    @Override
    public AdminPermission selectByName(String name) {
        return adminPermissionBll.selectByName(name);
    }

    @Override
    public List<AdminPermission> getAdminPermissionsByNames(String names) {
        return adminPermissionBll.getAdminPermissionsByNames(names);
    }

    @Override
    public String insertAdminPermission(InsertAdminPermissionVO permission) throws ServiceException {
        AdminPermission newPermission = new ModelMapper().map(permission, AdminPermission.class);
        String uuid=adminPermissionBll.insertPermission(newPermission);
        if (ObjectHelper.isEmpty(uuid)){
            throw new ServiceException("添加失败，请联系管理员");
        }
        return ResultConstants.ADD_SUCCESS;
    }

    @Override
    public CommonPage<AdminPermission> selectAdminPermission( Integer page, Integer pageSize) throws ServiceException {
        CommonPage<AdminPermission> resultPage = new CommonPage<>(page,pageSize);
        List<AdminPermission> permissions = adminPermissionBll.permissionList(resultPage);
        resultPage.setRecords(permissions);
        return resultPage;
    }


    @Override
    public List<AdminPermission> allPermissionList() {
        return adminPermissionBll.allPermissionList();
    }

}