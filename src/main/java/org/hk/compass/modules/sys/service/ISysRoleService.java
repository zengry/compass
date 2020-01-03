package org.hk.compass.modules.sys.service;

import org.hk.compass.modules.sys.entity.SysRole;
import com.baomidou.mybatisplus.extension.service.IService;
import org.hk.compass.modules.sys.param.RoleDataParam;
import org.hk.compass.modules.sys.param.RoleListParam;
import org.hk.compass.util.R;

import java.util.List;

/**
 * <p>
 * 角色 服务类
 * </p>
 *
 * @author zengry
 * @since 2019-12-30
 */
public interface ISysRoleService extends IService<SysRole> {
    List<SysRole> getRolesByUserId(Long userId);
    R getRoles(RoleListParam param);
    R save(RoleDataParam param);
    R update(RoleDataParam param);
}
