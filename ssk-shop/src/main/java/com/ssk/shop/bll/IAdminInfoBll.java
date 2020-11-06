package com.ssk.shop.bll;

import com.ssk.utils.CommonPage;
import com.ssk.exception.ServiceException;
import com.ssk.shop.entity.AdminInfo;
import com.ssk.shop.vo.UpdatePassWordVO;

import java.util.List;

/**
 * (AdminInfo)Bll层接口
 *
 * @author ssk
 * @since 2020-11-03 08:08:40
 */
public interface IAdminInfoBll {

    String insertAdmin(AdminInfo admin)throws ServiceException;

    boolean updatePassWord(UpdatePassWordVO json)throws ServiceException;

    AdminInfo selectAdminById(String uuid);

    List<AdminInfo> selectAdmins(CommonPage<AdminInfo> pageUtil, String roleId, String account, String name, Integer sex, Integer enable, String phone);

    boolean updateAdmin(AdminInfo admin)throws ServiceException;

    boolean deleteAdmin(String uuid)throws ServiceException;

    AdminInfo getAdminByAccount(String adminAccount);

}