package org.hk.compass.modules.sys.service.impl;

import org.apache.shiro.util.CollectionUtils;
import org.apache.shiro.util.StringUtils;
import org.hk.compass.constants.Constants;
import org.hk.compass.constants.enums.MenuType;
import org.hk.compass.modules.sys.entity.SysMenu;
import org.hk.compass.modules.sys.mapper.SysMenuMapper;
import org.hk.compass.modules.sys.service.ISysMenuService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * <p>
 * 菜单管理 服务实现类
 * </p>
 *
 * @author zengry
 * @since 2019-12-30
 */
@Service
public class SysMenuServiceImpl extends ServiceImpl<SysMenuMapper, SysMenu> implements ISysMenuService {

    @Override
    public List<SysMenu> listMenuByUserId(Long userId) {
        if(Constants.SUPER_ADMIN.equals(userId)){
            return getAllMenus(null);
        }

        List<Long> allMenuIdList = this.baseMapper.listUserMenuId(userId);
        return getAllMenus(allMenuIdList);
    }


    @Override
    public Set<String> getUserPermissionSet(Long userId) {
        List<String> perms = (Constants.SUPER_ADMIN.equals(userId))
            ? this.baseMapper.getAllPerms()
            : this.baseMapper.getPermsByUserId(userId);
        Set<String> permissions = new HashSet<>();
        for(String perm : perms){
            if(StringUtils.hasText(perm)){
                permissions.addAll(Arrays.asList(perm.trim().split(",")));
            }
        }

        return permissions;
    }


    /**
     * 获取用户所有菜单
     * @param menuIdList
     * @return
     */
    private List<SysMenu> getAllMenus(List<Long> menuIdList) {
        List<SysMenu> menus = getAllParentMenus(0L, menuIdList);
        getSubmenuTree(menus, menuIdList);
        return menus;
    }

    /**
     * 获取父级菜单
     * @param parentId
     * @param menuIdList
     * @return
     */
    private List<SysMenu> getAllParentMenus(Long parentId, List<Long> menuIdList) {
        List<SysMenu> menus =  this.baseMapper.getMenusByParentId(parentId);
        if(CollectionUtils.isEmpty(menuIdList)){
            return menus;
        }

        return menus.stream()
            .filter(sysMenu -> menuIdList.contains(sysMenu.getMenuId()))
            .collect(Collectors.toList());
    }

    /**
     * 递归 - 获取所有子菜单
     * @param menus
     * @param menuIdList
     * @return
     */
    private List<SysMenu> getSubmenuTree(List<SysMenu> menus, List<Long> menuIdList) {
        List<SysMenu> subMenus = new ArrayList<>();
        menus.forEach(sysMenu -> {
            if(MenuType.CATALOG.getValue() == sysMenu.getType()){
                sysMenu.setList(getSubmenuTree(getAllParentMenus(sysMenu.getMenuId(), menuIdList), menuIdList));
            }
            subMenus.add(sysMenu);
        });
        return subMenus;
    }

}
