package com.ssk.shop.dao;

import com.ssk.utils.CommonPage;
import com.ssk.shop.entity.AdminInfo;
import com.baomidou.mybatisplus.service.IService;
import com.ssk.shop.vo.UpdatePassWordVO;

import java.util.List;

/**
 * (AdminInfo)表数据库处理层
 *
 * @author ssk
 * @since 2020-11-03 08:08:41
 */
public interface IAdminInfoDao extends IService<AdminInfo>{

    String insertAdmin(AdminInfo admin);

    boolean checkAccountRepeat(AdminInfo admin);

    AdminInfo getAdminByUUID(String id);

    boolean updatePassWord(UpdatePassWordVO json);

    List<AdminInfo> selectAdmins(CommonPage<AdminInfo> pageUtil, String roleId, String account, String name, Integer sex, Integer enable, String phone);

    boolean updateAdmin(AdminInfo admin);

    AdminInfo getAdminByAccount(String thisAdminAccount);

}