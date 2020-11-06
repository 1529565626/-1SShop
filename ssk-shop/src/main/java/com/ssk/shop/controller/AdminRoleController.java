package com.ssk.shop.controller;

import com.ssk.exception.ServiceException;
import com.ssk.shop.facade.IAdminRoleFacade;
import com.ssk.shop.vo.admin.role.InsertRoleVO;
import com.ssk.shop.vo.admin.role.UpdateRoleVO;
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
 * 角色表(AdminRole表控制层
 *
 * @author ssk
 * @since 2020-11-03 08:11:21
 */
@RestController
@RequestMapping("admin-role")
@Api(tags = "管理员--角色管理API")
public class AdminRoleController {
    /**
     * 服务对象
     */
    @Resource
    private IAdminRoleFacade adminRoleFacade;

    /**
     * 添加角色
     *
     * @param role
     * @return
     */
    @PreAuthorize("hasAuthority('ROLE_EDIT')")
    @ApiOperation(value = "添加角色", notes = "添加角色", httpMethod = "POST")
    @PostMapping("")
//    @MethodLog(MethodLogLimit = true, module = "角色管理", methods = "添加角色")
    public CommonResult<Object> insertRole(@RequestBody @Valid InsertRoleVO role)throws ServiceException {
        return CommonResult.success(adminRoleFacade.addRole(role));
    }


    /**
     * 修改角色
     *
     * @param role
     * @return
     */
    @PreAuthorize("hasAuthority('ROLE_EDIT')")
    @ApiOperation(value = "修改角色", notes = "修改角色", httpMethod = "PUT")
    @PutMapping("")
//    @MethodLog(MethodLogLimit = true, module = "角色管理", methods = "修改角色")
    public CommonResult<String> updateRole(@RequestBody @Valid UpdateRoleVO role){
        try {
            return CommonResult.success(adminRoleFacade.updateRole(role));
        }catch (ServiceException e){
            return CommonResult.failed(e.getMessage());
        }
    }


    /**
     * 删除角色
     *
     * @param uuid
     * @return
     */
    @PreAuthorize("hasAuthority('ROLE_EDIT')")
    @ApiOperation(value = "删除角色ByRoleId", notes = "删除角色", httpMethod = "DELETE")
    @DeleteMapping("/{uuid}")
//    @MethodLog(MethodLogLimit = true, module = "角色管理", methods = "删除角色")
    public CommonResult<String> deleteRole(@PathVariable("uuid") String uuid){
        try {
            return CommonResult.success(adminRoleFacade.deleteRole(uuid));
        }catch (ServiceException e){
            return CommonResult.failed(e.getMessage());
        }
    }

    /**
     * 角色详情
     *
     * @param uuid
     * @return
     */
    @PreAuthorize("hasAuthority('ROLE_SEE')")
    @ApiOperation(value = "角色详情", notes = "查询角色详情ByRoleId", httpMethod = "GET")
    @RequestMapping("/{uuid}")
    @ApiImplicitParam(name = "uuid", value = "角色id", required = true, paramType = "path", dataType = "String")
    public CommonResult<Object> getRole(@PathVariable("uuid") String uuid)throws ServiceException {
        try {
            return CommonResult.success(adminRoleFacade.getRole(uuid));
        }catch (ServiceException e){
            return CommonResult.failed(e.getMessage());
        }
    }


    /**
     * 角色列表
     *
     * @param roleName
     * @param rolePermission
     * @param enable
     * @param page
     * @param pageSize
     * @return
     */
    @PreAuthorize("hasAuthority('ROLE_SEE')")
    @ApiOperation(value = "角色列表", notes = "角色列表获取", httpMethod = "GET")
    @RequestMapping("/query_page")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "roleName", value = "角色名", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "rolePermission", value = "权限名", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "enable", value = "状态(1是启用，0是禁用)", paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "page", value = "展示的页数", required = true, paramType = "query", dataType = "int", example = "1"),
            @ApiImplicitParam(name = "pageSize", value = "每页展示几条", required = true, paramType = "query", dataType = "int", example = "10")
    })
    public CommonResult<Object> RoleList(@RequestParam(required = false) String roleName,
                                            @RequestParam(required = false) String rolePermission,
                                            @RequestParam(required = false) Integer enable,
                                            Integer page, Integer pageSize) throws ServiceException {
        try {
            return CommonResult.success(adminRoleFacade.RoleList(roleName, rolePermission, enable, page, pageSize));
        }catch (ServiceException e){
            return CommonResult.failed(e.getMessage());
        }
    }

}