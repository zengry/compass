package org.hk.compass.modules.sys.param;

import lombok.Data;

/**
 * @author zengry
 * @description
 * @since 2020/1/2
 */

@Data
public class RoleListParam extends PageParam {
    private static final long serialVersionUID = 169559271488185426L;
    private String roleName;
    private Long userId;
}
