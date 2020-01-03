package org.hk.compass.modules.sys.mapper;

import org.hk.compass.modules.sys.entity.SysMenu;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 菜单管理 Mapper 接口
 * </p>
 *
 * @author zengry
 * @since 2019-12-30
 */
public interface SysMenuMapper extends BaseMapper<SysMenu> {
    /**
     * 查询同类菜单
     * @param pid
     * @return
     */
    List<SysMenu> getMenusByParentId(Long pid);

    /**
     * 查询用户所有菜单
     * @param userId
     * @return
     */
    List<Long> listUserMenuId(Long userId);

    /**
     * 获取所有权限
     * @return
     */
    List<String> getAllPerms();

    /**
     * 获取用户所有权限
     * @param userId
     * @return
     */
    List<String> getPermsByUserId(Long userId);
}
