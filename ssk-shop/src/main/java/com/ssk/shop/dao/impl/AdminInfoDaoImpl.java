package com.ssk.shop.dao.impl;

import com.ssk.utils.CommonPage;
import com.ssk.enums.BooleanEnum;
import com.ssk.shop.entity.AdminInfo;
import com.ssk.shop.dao.IAdminInfoDao;
import com.ssk.shop.repository.AdminInfoMapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.ssk.shop.vo.UpdatePassWordVO;
import com.ssk.utils.GuidGeneratorUtil;
import com.ssk.utils.ObjectHelper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.mapper.EntityWrapper;

import java.util.Date;
import java.util.List;

/**
 * (AdminInfo)表服务实现类
 *
 * @author ssk
 * @since 2020-11-03 08:08:41
 */
@Service("adminInfoDaoImpl")
public class AdminInfoDaoImpl extends ServiceImpl<AdminInfoMapper, AdminInfo> implements IAdminInfoDao{

    @Override
    public String  insertAdmin(AdminInfo admin) {
        String uuid = GuidGeneratorUtil.generate();
        admin.setAdminDepartmentId(uuid);
        admin.setPassword(new BCryptPasswordEncoder().encode(admin.getPassword()));
        admin.setEnableState(BooleanEnum.TRUE.getCode());
        admin.setDeleteFlag(BooleanEnum.FALSE.getCode());
        admin.setCreateTime(new Date());
        //执行sql
        if (baseMapper.insert(admin)>0){
            return uuid;
        }else {
            return null;
        }
    }


    @Override
    public boolean checkAccountRepeat(AdminInfo admin) {
        EntityWrapper<AdminInfo> wrapper = new EntityWrapper<>();
        if (ObjectHelper.isNotEmpty(admin.getAccount())){
            wrapper.eq("phone",admin.getPhone()).orNew().eq("account",admin.getAccount());
        }else {
            wrapper.eq("phone",admin.getPhone());
        }
        wrapper.andNew().eq("is_delete", BooleanEnum.FALSE.getCode()+"");
        if (ObjectHelper.isNotEmpty(admin.getAdminInfoId())){
            wrapper.andNew().ne("admin_info_id",admin.getAdminInfoId());
        }
        List<AdminInfo> admins = baseMapper.selectList(wrapper);
        return admins.size() != 0;
    }

    @Override
    public AdminInfo getAdminByUUID(String id) {
        return this.selectById(id);
    }

    @Override
    public boolean updatePassWord(UpdatePassWordVO json) {
        AdminInfo admin = new AdminInfo();
        admin.setAdminInfoId(json.getId());
        admin.setPassword(new BCryptPasswordEncoder().encode(json.getNewPwd()));
        return baseMapper.updateById(admin) > 0;
    }

    @Override
    public List<AdminInfo> selectAdmins(CommonPage<AdminInfo> pageUtil, String roleId, String account, String name, Integer sex, Integer enable, String phone) {
        EntityWrapper<AdminInfo> wrapper = new EntityWrapper<>();
        if (ObjectHelper.isNotEmpty(roleId)) {
            wrapper.eq("admin_role", roleId);
        }
        if (ObjectHelper.isNotEmpty(account)) {
            wrapper.like("account", account);
        }
        if (ObjectHelper.isNotEmpty(name)) {
            wrapper.like("name", name);
        }
        if (ObjectHelper.isNotEmpty(sex)) {
            wrapper.eq("sex", sex);
        }
        if (ObjectHelper.isNotEmpty(enable)) {
            wrapper.eq("or_no_enable", enable);
        }
        if (ObjectHelper.isNotEmpty(phone)) {
            wrapper.like("phone", phone);
        }
        wrapper.eq("is_delete", BooleanEnum.FALSE.getCode());
        return baseMapper.selectPage(pageUtil, wrapper);
    }

    @Override
    public boolean updateAdmin(AdminInfo admin) {
        if (ObjectHelper.isNotEmpty(admin.getPassword())){
            admin.setPassword(new BCryptPasswordEncoder().encode(admin.getPassword()));
        }
        return baseMapper.updateById(admin) > 0;
    }

    @Override
    public AdminInfo getAdminByAccount(String thisAdminAccount) {
        EntityWrapper<AdminInfo> wrapper = new EntityWrapper<>();
        wrapper.eq("account",thisAdminAccount);
        wrapper.eq("enable_state", BooleanEnum.TRUE.getCode());
        wrapper.ne("delete_flag", BooleanEnum.TRUE.getCode());
        List<AdminInfo> list = baseMapper.selectList(wrapper);
        if (list.size() == 1){
            return list.get(0);
        }
        return null;
    }
    

}