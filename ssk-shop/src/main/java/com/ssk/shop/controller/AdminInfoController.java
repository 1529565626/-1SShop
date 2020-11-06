package com.ssk.shop.controller;

import com.ssk.shop.dto.AdminAccount;
import com.ssk.shop.dto.admin.AdminListDto;
import com.ssk.shop.vo.UpdatePassWordVO;
import com.ssk.shop.vo.UserLoginVO;
import com.ssk.shop.vo.admin.InsertAdminVO;
import com.ssk.shop.vo.admin.UpdateAdminVO;
import com.ssk.utils.CommonPage;
import com.ssk.exception.ServiceException;
import com.ssk.shop.facade.IAdminInfoFacade;
import com.ssk.utils.CommonResult;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.ApiOperation;
import org.springframework.security.access.prepost.PreAuthorize;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

/**
 * (AdminInfo表控制层
 *
 * @author ssk
 * @since 2020-11-03 08:08:38
 */
@RestController
@RequestMapping("admin-info")
@Api(tags = "管理员--管理API")
public class AdminInfoController {
    /**
     * 服务对象
     */
    @Resource
    private IAdminInfoFacade adminInfoFacade;

    @Value("${jwt.tokenHeader}")
    private String tokenHeader;
    @Value("${jwt.tokenHead}")
    private String tokenHead;

    @ApiOperation(value = "登录以后返回token")
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<Map<String, String>> login(@RequestBody UserLoginVO user) {
        try {
            String token = adminInfoFacade.login(user.getUsername(), user.getPassword());
            Map<String, String> tokenMap = new HashMap<>();
            tokenMap.put("token", token);
            tokenMap.put("tokenHead", tokenHead);
            return CommonResult.success(tokenMap);
        }catch (ServiceException e){
            return CommonResult.failed(e.getMessage());
        }
    }

    /**
     * 添加管理员
     *
     * @param newAdmin
     * @return
     */
    @PreAuthorize("hasAuthority('ADMIN_EDIT')")
    @ApiOperation(value = "添加管理员", notes = "添加新管理员", httpMethod = "POST")
    @PostMapping("")
//    @MethodLog(MethodLogLimit = true, module = "管理员管理", methods = "添加管理员")
    public CommonResult<Object> insertAdmin(@RequestBody @Valid InsertAdminVO newAdmin) {
        try {
            return CommonResult.success(adminInfoFacade.insertAdmin(newAdmin));
        }catch (ServiceException e){
            return CommonResult.failed(e.getMessage());
        }
    }

    /**
     * 修改管理员
     *
     * @param admin
     * @return
     */
    @PreAuthorize("hasAuthority('ADMIN_EDIT')")
    @ApiOperation(value = "修改管理员", notes = "修改管理员信息", httpMethod = "PUT")
    @PutMapping("")
//    @MethodLog(MethodLogLimit = true, module = "管理员管理", methods = "编辑管理员")
    public CommonResult<String> updateAdmin(@RequestBody @Valid UpdateAdminVO admin) {
        try {
            return CommonResult.success(adminInfoFacade.updateAdmin(admin));
        }catch (ServiceException e){
            return CommonResult.failed(e.getMessage());
        }
    }

    /**
     * 删除管理员
     *
     * @param uuid
     * @return
     */
    @PreAuthorize("hasAuthority('ADMIN_EDIT')")
    @ApiOperation(value = "删除管理员", notes = "删除管理员", httpMethod = "DELETE")
    @DeleteMapping("/{uuid}")
//    @MethodLog(MethodLogLimit = true, module = "管理员管理", methods = "删除管理员")
    public CommonResult<String> deleteAdmin(@PathVariable("uuid") String uuid) {
        try {
            return CommonResult.success(adminInfoFacade.deleteAdmin(uuid));
        }catch (ServiceException e){
            return CommonResult.failed(e.getMessage());
        }
    }


    /**
     * 修改密码
     *
     * @param json
     * @return
     */
    @PreAuthorize("hasAnyRole('ROLE_ADMIN') or hasAuthority('ADMIN_EDIT')")
    @ApiOperation(value = "修改密码", notes = "修改密码", httpMethod = "PUT")
    @PutMapping("/password")
//    @MethodLog(MethodLogLimit = true, module = "管理员管理", methods = "修改密码")
    public CommonResult<String> updatePassWord(@RequestBody @Valid UpdatePassWordVO json) {
        try {
            return CommonResult.success(adminInfoFacade.updatePassWord(json));
        }catch (ServiceException e){
            return CommonResult.failed(e.getMessage());
        }
    }

    /**
     * 根据id查看详情
     *
     * @param uuid
     * @return
     *
     *
     */
    @PreAuthorize("hasAuthority('ADMIN_SEE')")
    @ApiOperation(value = "根据id查看详情", notes = "根据id查看详情", httpMethod = "GET")
    @RequestMapping("/{uuid}")
    @ApiImplicitParam(name = "uuid", value = "管理员id", required = true, paramType = "path", dataType = "String")
    public CommonResult<Object> selectAdminById(@PathVariable("uuid") String uuid) {
        try {
            return CommonResult.success(adminInfoFacade.selectAdminById(uuid));
        }catch (ServiceException e){
            return CommonResult.failed(e.getMessage());
        }

    }

    /**
     * 查询管理员集合
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
    @PreAuthorize("hasAuthority('ADMIN_SEE')")
    @RequestMapping("/query_page")
    @ApiOperation(value = "查询管理员集合", notes = "查询管理员集合", httpMethod = "GET")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "roleId", value = "角色ID", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "account", value = "账号", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "name", value = "名字", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "sex", value = "性别（0：男，1：女）", paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "enable", value = "状态(1是启用，0是禁用)）", paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "phone", value = "电话", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "page", value = "展示的页数", required = true, paramType = "query", dataType = "int",example = "1"),
            @ApiImplicitParam(name = "pageSize", value = "每页展示几条", required = true, paramType = "query", dataType = "int",example = "10")
    })
    public CommonResult<CommonPage<AdminListDto>> selectAdmin(@RequestParam(required = false) String roleId,
                                                              @RequestParam(required = false) String account,
                                                              @RequestParam(required = false) String name,
                                                              @RequestParam(required = false) Integer sex,
                                                              @RequestParam(required = false) Integer enable,
                                                              @RequestParam(required = false) String phone,
                                                              Integer page, Integer pageSize) {
        try {
            return CommonResult.success(adminInfoFacade.selectAdmin(roleId, account, name, sex, enable, phone,  page, pageSize));
        } catch (ServiceException e) {
            e.printStackTrace();
            return CommonResult.failed(e.getMessage());
        }
    }

    /**
     * 获取管理员
     *
     * @return
     */
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @ApiOperation(value = "获取管理员信息", notes = "获取管理员信息", httpMethod = "GET")
    @GetMapping("/account_info")
    public CommonResult<AdminAccount> getAccount() {
        try {
            return CommonResult.success(adminInfoFacade.getAccount());
        }catch (ServiceException e){
            return CommonResult.failed(e.getMessage());
        }
    }

}