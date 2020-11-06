package com.ssk.shop.repository;

import com.ssk.shop.entity.AdminPermission;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * admin权限表(AdminPermission)表数据库访问层
 *
 * @author ssk
 * @since 2020-11-03 08:09:00
 */
 @Mapper
public interface AdminPermissionMapper extends BaseMapper<AdminPermission>{


}