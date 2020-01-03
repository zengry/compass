package org.hk.compass.modules.sys.param;

import lombok.Data;

import java.io.Serializable;

/**
 * @author zengry
 * @description 登录表单参数
 * @since 2019/12/27
 */

@Data
public class LoginForm implements Serializable {
    private static final long serialVersionUID = -8594252112385932374L;
    private String username;
    private String password;
    private String captcha;
    private String uuid;
}
