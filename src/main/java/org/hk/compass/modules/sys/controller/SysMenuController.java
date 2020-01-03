package org.hk.compass.modules.sys.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.hk.compass.modules.sys.entity.SysMenu;
import org.hk.compass.modules.sys.service.ISysMenuService;
import org.hk.compass.util.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;

/**
 * @author zengry
 * @description 菜单、权限管理
 * @since 2019/12/30
 */

@RestController
@RequestMapping("/sys/menu")
public class SysMenuController extends AbstractController{

    @Autowired
    private ISysMenuService sysMenuService;

    @GetMapping("/nav")
    public R nav(){
        Long userId = getUserId();
        List<SysMenu> menuList = sysMenuService.listMenuByUserId(getUserId());
        Set<String> permissions = sysMenuService.getUserPermissionSet(userId);
        return R.success().put("menuList", menuList)
                          .put("permissions", permissions);
    }


    @GetMapping("/list")
    public R list(){
        List<SysMenu> menus = sysMenuService.list();
        return R.success().put("list", menus);
    }


    @GetMapping("/select")
    public R select(){
        List<SysMenu> menus = sysMenuService
            .list(new QueryWrapper<SysMenu>().
                ne("type", 2)
                .orderByAsc("order_num"));

        return R.success().put("menuList", menus);
    }



    @GetMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        SysMenu menu = sysMenuService.getById(id);
        return R.success().put("menu", menu);
    }


}
