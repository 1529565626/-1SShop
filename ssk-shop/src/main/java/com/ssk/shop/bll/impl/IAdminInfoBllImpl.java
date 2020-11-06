package com.ssk.shop.bll.impl;

import com.ssk.utils.CommonPage;
import com.ssk.enums.BooleanEnum;
import com.ssk.exception.ServiceException;
import com.ssk.shop.bll.IAdminInfoBll;
import com.ssk.shop.dao.IAdminInfoDao;
import com.ssk.shop.vo.UpdatePassWordVO;
import com.ssk.utils.ObjectHelper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;
import com.ssk.shop.entity.AdminInfo;

import javax.annotation.Resource;

/**
 * (AdminInfo)表服务实现类
 *
 * @author ssk
 * @since 2020-11-03 08:08:40
 */
@Service("adminInfoBll")
public class IAdminInfoBllImpl implements IAdminInfoBll {
    @Resource
    private IAdminInfoDao IAdminInfoDao;


    @Override
    public String insertAdmin(AdminInfo admin) throws ServiceException {
        if (IAdminInfoDao.checkAccountRepeat(admin)){
            throw new ServiceException("账号或手机已存在");
        }
        return IAdminInfoDao.insertAdmin(admin);
    }

    @Override
    public boolean updatePassWord(UpdatePassWordVO json) throws ServiceException {
        //查出数据库是否有这条数据
        AdminInfo info = IAdminInfoDao.getAdminByUUID(json.getId());
        if (ObjectHelper.isEmpty(info)|| info.getDeleteFlag().equals(BooleanEnum.TRUE.getCode())) {
            throw new ServiceException("账户不存在或已删除");
        }
        if (!new BCryptPasswordEncoder().matches(json.getOldPwd(), info.getPassword())) {
            throw new ServiceException("旧密码不正确");
        }
        //判断是否成功
        return IAdminInfoDao.updatePassWord(json);
    }

    @Override
    public AdminInfo selectAdminById(String uuid) {
        AdminInfo admin = IAdminInfoDao.getAdminByUUID(uuid);
        if (admin == null || admin.getDeleteFlag() == 1) {
            return null;
        }
        return admin;
    }




    @Override
    public List<AdminInfo> selectAdmins(CommonPage<AdminInfo> pageUtil, String roleId, String account, String name, Integer sex, Integer enable, String phone) {
        List<AdminInfo> admins = IAdminInfoDao.selectAdmins( pageUtil,roleId,  account,  name,  sex,  enable,  phone);
        return admins;
    }

    @Override
    public boolean  updateAdmin(AdminInfo admin) throws ServiceException {
        AdminInfo isExitAdmin = selectAdminById(admin.getAdminInfoId());
        if (null == isExitAdmin){
            throw new ServiceException("请传输有效id");
        }
        if (IAdminInfoDao.checkAccountRepeat(admin)){
            throw new ServiceException("手机号已存在");
        }
        //判断是否成功
        return IAdminInfoDao.updateAdmin(admin);
    }

    @Override
    public boolean deleteAdmin(String uuid) throws ServiceException {
        AdminInfo admin = IAdminInfoDao.selectById(uuid);
        if (admin == null) {
            throw new ServiceException("获取数据失败");
        }
        admin.setDeleteFlag(BooleanEnum.TRUE.getCode());
        return IAdminInfoDao.updateById(admin);
    }

    @Override
    public AdminInfo getAdminByAccount(String adminAccount) {
        return IAdminInfoDao.getAdminByAccount(adminAccount);
    }

}