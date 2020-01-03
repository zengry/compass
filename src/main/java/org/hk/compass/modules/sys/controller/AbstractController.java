package org.hk.compass.modules.sys.controller;

import org.apache.shiro.SecurityUtils;
import org.hk.compass.modules.sys.entity.SysUser;

/**
 * @author zengry
 * @description 公共信息
 * @since 2019/12/27
 */
public abstract class AbstractController {
    protected SysUser getUser() {
        return (SysUser) SecurityUtils.getSubject().getPrincipal();
    }

    protected Long getUserId() {
        return getUser().getId();
    }
}
