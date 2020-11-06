package com.ssk.shop.dto.admin;

import com.ssk.shop.entity.AdminInfo;
import com.ssk.shop.entity.AdminRole;
import lombok.Getter;
import lombok.Setter;

/**
 * @Author: ssk
 * @Date: 2020/11/4 22:56
 */
@Getter
@Setter
public class AdminInfoDto extends AdminInfo {

    private AdminRole role;

}
