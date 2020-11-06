package com.ssk.shop.vo.admin;

import com.ssk.constants.RegularExpression;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.*;

/**
 * @Author: ssk
 * @Date: 2020/11/4 22:29
 */
@Getter
@Setter
@ToString
@ApiModel(value = "管理员修改模型")
public class UpdateAdminVO {
    /**
     * id
     */
    @ApiModelProperty(value = "uuid", required = true, example = "")
    @NotEmpty(message = "uuid不能为空")
    private String adminInfoId;

    /**
     * 名称
     */
    @ApiModelProperty(value = "姓名", required = true, example = "姓名")
    @NotEmpty(message = "姓名不能为空")
    private String name;

    /**
     * 启用状态
     */
    @ApiModelProperty(value = "启用状态 1启用 0禁用", required = true, example = "1")
    @NotNull(message = "启用状态不能为空")
    @Max(value = 1,message = "启用状态不能大于1")
    @Min(value = 0,message = "启用状态不能小于0")
    private Integer enableState;

    /**
     * 电话
     */
    @Pattern(regexp = RegularExpression.PHONE,message = "电话格式异常")
    @NotEmpty(message = "电话不能为空")
    @ApiModelProperty(value = "电话", required = true, example = "15265656260")
    private String phone;

    /**
     * 角色id
     */
    @NotEmpty(message = "角色id不能未空")
    @ApiModelProperty(value = "角色id", required = true, example = "")
    private String adminRole;


}
