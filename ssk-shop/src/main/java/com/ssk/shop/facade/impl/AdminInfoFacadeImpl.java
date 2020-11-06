package com.ssk.shop.facade.impl;

import com.ssk.constants.ResultConstants;
import com.ssk.enums.BooleanEnum;
import com.ssk.exception.ServiceException;
import com.ssk.shop.bll.IAdminPermissionBll;
import com.ssk.shop.bll.IAdminRoleBll;
import com.ssk.shop.config.security.token.JwtTokenUtil;
import com.ssk.shop.dto.AdminAccount;
import com.ssk.shop.dto.LoginMan;
import com.ssk.shop.dto.admin.AdminInfoDto;
import com.ssk.shop.dto.admin.AdminListDto;
import com.ssk.shop.dto.admin.AdminRoleDto;
import com.ssk.shop.entity.AdminPermission;
import com.ssk.shop.entity.AdminRole;
import com.ssk.shop.facade.BaseAccountInfoFacade;
import com.ssk.shop.facade.IAdminRoleFacade;
import com.ssk.shop.vo.UpdatePassWordVO;
import com.ssk.shop.vo.admin.InsertAdminVO;
import com.ssk.shop.vo.admin.UpdateAdminVO;
import com.ssk.utils.CommonPage;
import com.ssk.utils.ObjectHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.ssk.shop.facade.IAdminInfoFacade;
import com.ssk.shop.bll.IAdminInfoBll;
import javax.annotation.Resource;
import com.ssk.shop.entity.AdminInfo;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;

/**
 * (AdminInfo)表门面实现类
 *
 * @author ssk
 * @since 2020-11-03 08:08:39
 */
@Service("adminInfoFacade")
public class AdminInfoFacadeImpl extends BaseAccountInfoFacade implements IAdminInfoFacade {
    private final static Logger LOG = LoggerFactory.getLogger(AdminInfoFacadeImpl.class);
    @Resource
    private IAdminInfoBll adminInfoBll;
    @Resource
    private IAdminRoleBll roleBll;
    @Resource
    private IAdminPermissionBll permissionBll;
    @Resource
    private IAdminRoleFacade roleFacade;

    /**
     * 添加管理员
     *
     * @param admin
     * @return
     */
    @Override
    public String insertAdmin(InsertAdminVO admin) throws ServiceException {
        AdminRoleDto role = roleFacade.getRole(admin.getAdminRole());
        if (role==null){
            throw new ServiceException("角色未找到");
        }
        AdminInfo newadmin = new ModelMapper().map(admin, AdminInfo.class);
        String uuid=adminInfoBll.insertAdmin(newadmin);
        if (ObjectHelper.isEmpty(uuid)){
            throw new ServiceException("添加失败，请联系管理员");
        }
        return ResultConstants.ADD_SUCCESS ;
    }

    /**
     * 修改密码
     *
     * @param json
     * @return
     */
    @Override
    public String  updatePassWord(UpdatePassWordVO json) throws ServiceException {
        if(!this.adminInfoBll.updatePassWord(json)){
            throw new ServiceException("修改失败，请联系管理员");
        }
        return ResultConstants.UPDATE_SUCCESS;
    }

    @Override
    public AdminInfo selectAdminById(String uuid) throws ServiceException {
        return adminInfoBll.selectAdminById(uuid);
    }

    @Override
    public CommonPage<AdminListDto> selectAdmin(String roleId, String account, String name, Integer sex, Integer enable, String phone, Integer page, Integer pageSize) throws ServiceException {
        CommonPage<AdminInfo> adminListDtoPage = new CommonPage<>(page,pageSize);
        List<AdminInfo> adminPage = adminInfoBll.selectAdmins(adminListDtoPage,roleId, account, name, sex, enable, phone);
        CommonPage<AdminListDto> resultPage = new CommonPage<>(page,pageSize);
        List<AdminListDto> adminListDtos = new ArrayList<>();
        if (null != adminPage && adminPage.size() != 0){
            for (AdminInfo adminInfo : adminPage) {
                AdminListDto dto = new ModelMapper().map(adminInfo, AdminListDto.class);
                AdminRoleDto roleDto = roleFacade.getRole(adminInfo.getAdminRole());
                if (ObjectHelper.isNotEmpty(roleDto)){
                    dto.setRoleName(roleDto.getRoleMsg().getRoleName());
                }else {
                    dto.setRoleName("-角色不存在，请重新分配-");
                }
                adminListDtos.add(dto);
            }
        }
        resultPage.setRecords(adminListDtos);
        resultPage.setTotal(adminListDtoPage.getTotal());
        return resultPage;
    }

    @Override
    public String updateAdmin(UpdateAdminVO admin) throws ServiceException {
        AdminRoleDto role = roleFacade.getRole(admin.getAdminRole());
        if (role==null){
            throw new ServiceException("角色未找到");
        }
        AdminInfo entity = new ModelMapper().map(admin, AdminInfo.class);
//        if (1 == admin.getResetPWD()){
//            admin.setPassword("123456");
//        }
        if(!this.adminInfoBll.updateAdmin(entity)){
            throw new ServiceException("修改失败，请联系管理员");
        }
        return ResultConstants.UPDATE_SUCCESS;
    }

    @Override
    public AdminInfoDto getAdminByAccount(String account) {
        AdminInfo admin = adminInfoBll.getAdminByAccount(account);
        AdminRole role = roleBll.getRole(admin.getAdminRole());
        AdminInfoDto dto = new ModelMapper().map(admin, AdminInfoDto.class);
        dto.setRole(role);
        return dto;
    }

    @Override
    public String  deleteAdmin(String uuid) throws ServiceException {
        if (uuid.equals(getThisAccountId())){
            throw new ServiceException("不能删除当前账户");
        }
        if(!this.adminInfoBll.deleteAdmin(uuid)){
            throw new ServiceException("删除失败，请联系管理员");
        }
        return ResultConstants.DELETE_SUCCESS;
    }

    @Override
    public LoginMan loadAdmin(String account) throws ServiceException {
        LoginMan user;
        AdminInfoDto admin = getAdminByAccount(account);
        if (admin != null) {
            if (admin.getDeleteFlag() == BooleanEnum.TRUE.getCode() || admin.getEnableState() == BooleanEnum.FALSE.getCode()) {
                return null;
            }
            List<AdminPermission> permissionList = new ArrayList<>();
            if (admin.getRole() != null) {
                String permission = admin.getRole().getRolePermission();
                permissionList = permissionBll.getAdminPermissionsByNames(permission);
            }
            List<String> permissions = new ArrayList<>();
            for (AdminPermission adminPermission : permissionList) {
                permissions.add(adminPermission.getPermissionName());
            }
            user = new LoginMan(admin.getAccount(),admin.getPassword(),admin.getRole().getRoleType(),permissions,admin.getAdminInfoId(),admin);
            user.setEnabled(true);
            System.out.println("启用"+user.getAuthority()+user.getPassword());
            return user;
        }
        System.out.println("不启用");
        throw new ServiceException("账号不存在");
    }

    @Override
    public AdminAccount getAccount() throws ServiceException {
        AdminAccount adminAccount = new ModelMapper().map(adminInfoBll.selectAdminById(getThisAccountId()),AdminAccount.class);
        AdminRoleDto role = roleFacade.getRole(adminAccount.getAdminRole());
        adminAccount.setRoleName(role.getRoleMsg().getRoleName());
        List<String> permissions = new ArrayList<>();
        for (AdminPermission permission : role.getPermissions()) {
            if (ObjectHelper.isNotEmpty(permission.getPageItemId())){
                permissions.add(permission.getPageItemId());
            }
        }
        adminAccount.setPermissionList(permissions);
        return adminAccount;
    }

    @Resource
    private PasswordEncoder passwordEncoder;

    @Resource
    private JwtTokenUtil jwtTokenUtil;

    @Override
    public String login(String username, String password) {
        String token = null;
        //密码需要客户端加密后传递
        try {
            LoginMan userDetails = this.loadUserByUsername(username);
            if(!passwordEncoder.matches(password,userDetails.getPassword())){
                throw new BadCredentialsException("密码不正确");
            }
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
            token = jwtTokenUtil.generateToken(userDetails);
//            updateLoginTimeByUsername(username);
//            insertLoginLog(username);
        } catch (AuthenticationException e) {
//            LOGGER.warn("登录异常:{}", e.getMessage());
            throw new ServiceException("用户名或密码错误");
        }
        return token;
    }



    @Override
    public LoginMan loadUserByUsername(String userName) throws UsernameNotFoundException {
        return loadAdmin(userName);
    }


}