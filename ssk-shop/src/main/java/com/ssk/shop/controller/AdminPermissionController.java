package com.ssk.shop.controller;

import com.ssk.exception.ServiceException;
import com.ssk.shop.facade.IAdminPermissionFacade;
import com.ssk.shop.vo.admin.permission.InsertAdminPermissionVO;
import com.ssk.utils.CommonResult;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.ApiOperation;
import org.springframework.security.access.prepost.PreAuthorize;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * admin权限表(AdminPermission表控制层
 *
 * @author ssk
 * @since 2020-11-03 08:09:00
 */
@RestController
@RequestMapping("admin-permission")
@Api(tags = "管理员--权限管理API")
public class AdminPermissionController {
    /**
     * 服务对象
     */
    @Resource
    private IAdminPermissionFacade adminPermissionFacade;

    /**
     * 添加admin权限表(AdminPermission)
     *
     * @param newAdminPermission
     * @return
     */
    @PreAuthorize("hasAnyRole('ROLE_SUPER')")
    @ApiOperation(value = "添加admin权限表(AdminPermission", notes = "添加admin权限表(AdminPermission", httpMethod = "POST")
    @PostMapping("")
//    @MethodLog(MethodLogLimit = true, module = "权限管理", methods = "添加权限")
    public CommonResult<Object> insertAdminPermission(@RequestBody @Valid InsertAdminPermissionVO newAdminPermission) {
        try {
            return CommonResult.success(adminPermissionFacade.insertAdminPermission(newAdminPermission));
        }catch (ServiceException e){
            return CommonResult.failed(e.getMessage());
        }
    }
    
    /**
     * 查询admin权限表(AdminPermission)集合
     *
     * @param page
     * @param pageSize
     * @return
     */
    @PreAuthorize("hasAnyRole('ROLE_')")
    @RequestMapping("/query_page")
    @ApiOperation(value = "查询admin权限表(AdminPermission)集合", notes = "查询admin权限表(AdminPermission)集合", httpMethod = "GET")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "展示的页数", required = true, paramType = "query", dataType = "int",example = "1"),
            @ApiImplicitParam(name = "pageSize", value = "每页展示几条", required = true, paramType = "query", dataType = "int",example = "10")
    })
    public CommonResult<Object> selectAdminPermission(Integer page, Integer pageSize) {
        try {
            return CommonResult.success(adminPermissionFacade.selectAdminPermission(page,pageSize));
        } catch (ServiceException e) {
            e.printStackTrace();
            return CommonResult.failed(e.getMessage());
        }
    }

    /**
     * 获取所有权限
     *
     * @return
     */
    @PreAuthorize("hasAuthority('ADMIN_SEE')")
    @ApiOperation(value = "获取所有权限", notes = "获取所有权限", httpMethod = "GET")
    @RequestMapping("/all")
    public CommonResult<Object> PermissionList() {
        return CommonResult.success(adminPermissionFacade.allPermissionList());
    }

}