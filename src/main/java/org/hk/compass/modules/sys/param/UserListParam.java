package org.hk.compass.modules.sys.param;

import lombok.Data;

/**
 * @author zengry
 * @description
 * @since 2019/12/31
 */

@Data
public class UserListParam extends PageParam {
    private static final long serialVersionUID = -7076318195852846835L;
    /**
     * 用户名
     */
    private String username;
    /**
     * 用户标识
     */
    private Long userId;
}
