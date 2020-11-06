package com.ssk.shop.facade;

import com.ssk.exception.ServiceException;
import com.ssk.shop.dto.AdminAccount;
import com.ssk.shop.dto.LoginMan;
import com.ssk.shop.dto.admin.AdminInfoDto;
import com.ssk.shop.dto.admin.AdminListDto;
import com.ssk.shop.entity.AdminInfo;
import com.ssk.shop.vo.UpdatePassWordVO;
import com.ssk.shop.vo.admin.InsertAdminVO;
import com.ssk.shop.vo.admin.UpdateAdminVO;
import com.ssk.utils.CommonPage;
import org.springframework.security.core.userdetails.UserDetailsService;


/**
 * (AdminInfo)Facade层接口
 *
 * @author ssk
 * @since 2020-11-03 08:08:38
 */
public interface IAdminInfoFacade extends UserDetailsService {

    /**
     * 添加管理员
     *
     * @param admin
     * @return
     */
    String insertAdmin(InsertAdminVO admin)throws ServiceException;

    /**
     * 修改密码
     *
     * @param json
     * @return
     */
    String  updatePassWord(UpdatePassWordVO json) throws ServiceException;

    /**
     * 根据id查看详情
     *
     * @param uuid
     * @return
     */
    AdminInfo selectAdminById(String uuid)throws ServiceException;

    /**
     * 查询管理员集合
     *
     * @param roleId
     * @param account
     * @param name
     * @param sex
     * @param enable
     * @param phone
     * @param page
     * @param pageSize
     * @return
     */
    CommonPage<AdminListDto> selectAdmin(String roleId, String account, String name, Integer sex,
                                         Integer enable, String phone, Integer page, Integer pageSize)throws ServiceException;

    /**
     * 修改管理员
     *
     * @param admin
     * @return
     */
    String updateAdmin(UpdateAdminVO admin) throws ServiceException;

    /**
     * 删除管理员
     *
     * @param uuid
     * @return
     */
    String  deleteAdmin(String uuid)throws ServiceException;

    LoginMan loadAdmin(String account) throws ServiceException;


    AdminInfoDto getAdminByAccount(String account);

    AdminAccount getAccount() throws ServiceException;

    String login(String username, String password);

}