package org.hk.compass.modules.sys.service;

import org.hk.compass.modules.sys.entity.SysUserRole;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Set;

/**
 * <p>
 * 用户与角色对应关系 服务类
 * </p>
 *
 * @author zengry
 * @since 2019-12-30
 */
public interface ISysUserRoleService extends IService<SysUserRole> {
    Set<String> getUserPerms(Long userId);
}
