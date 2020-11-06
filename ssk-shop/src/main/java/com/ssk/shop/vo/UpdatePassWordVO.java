package com.ssk.shop.vo;

import com.ssk.constants.RegularExpression;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

/**
 * @Author: ssk
 * @Date: 2020/11/4 22:29
 */
@Getter
@Setter
@ToString
public class UpdatePassWordVO {

    /**
     * uuid
     */
    @NotEmpty(message = "uuid不能为空")
    @ApiModelProperty(value = "uuid", required = true, example = "")
    private String id;

    /**
     * 原密码
     */
    @NotEmpty(message = "密码不能未空")
    @ApiModelProperty(value = "原密码", required = true, example = "")
    private String oldPwd;

    /**
     * 新密码
     */
    @NotEmpty(message = "新密码不能为空")
    @Pattern(regexp = RegularExpression.ISPASSWORD,message = "新密码格式异常")
    @ApiModelProperty(value = "新密码", required = true, example = "")
    private String newPwd;

}
