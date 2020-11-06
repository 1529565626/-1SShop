package com.ssk.shop.repository;

import com.ssk.shop.entity.AdminRole;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 角色表(AdminRole)表数据库访问层
 *
 * @author ssk
 * @since 2020-11-03 08:11:22
 */
 @Mapper
public interface AdminRoleMapper extends BaseMapper<AdminRole>{


}