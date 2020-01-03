package org.hk.compass.modules.sys.param;

import lombok.Data;

import java.io.Serializable;

/**
 * @author zengry
 * @description
 * @since 2019/12/31
 */

@Data
public class PasswordForm implements Serializable {
    private static final long serialVersionUID = -948314786847809638L;

    private String password;
    private String newPassword;
}
