package org.hk.compass.modules.sys.service;

import org.hk.compass.modules.sys.entity.SysMenu;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Set;

/**
 * <p>
 * 菜单管理 服务类
 * </p>
 *
 * @author zengry
 * @since 2019-12-30
 */
public interface ISysMenuService extends IService<SysMenu> {
    /**
     * 获取用户菜单
     * @param userId
     * @return
     */
    List<SysMenu> listMenuByUserId(Long userId);

    /**
     * 获取用户权限
     * @param userId
     * @return
     */
    Set<String> getUserPermissionSet(Long userId);
}
