package com.zmj.wkt.entity.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author zhaomingjie
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MobileRegisterReq implements Serializable {

    @NotEmpty(message="用户名不能为空!")
    String username;

    @NotEmpty(message = "密码不能为空")
    String password;

    @NotEmpty(message = "手机号码不能为空")
    String mobile;

    @NotEmpty(message = "验证码不能为空")
    String code;

    @NotEmpty(message = "bizId不能为空")
    String bizId;

    String realName;

    String invitation_code;

    String address;
}
