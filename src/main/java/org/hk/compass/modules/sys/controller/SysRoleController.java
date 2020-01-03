package org.hk.compass.modules.sys.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.hk.compass.constants.Constants;
import org.hk.compass.modules.sys.entity.SysRole;
import org.hk.compass.modules.sys.entity.SysRoleMenu;
import org.hk.compass.modules.sys.param.RoleDataParam;
import org.hk.compass.modules.sys.param.RoleListParam;
import org.hk.compass.modules.sys.service.ISysRoleMenuService;
import org.hk.compass.modules.sys.service.ISysRoleService;
import org.hk.compass.util.R;
import org.hk.compass.validator.ValidationUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author zengry
 * @description
 * @since 2020/1/2
 */
@RestController
@RequestMapping("/sys/role")
public class SysRoleController extends AbstractController {

    @Autowired
    private ISysRoleService sysRoleService;

    @Autowired
    private ISysRoleMenuService sysRoleMenuService;

    @GetMapping("/select")
    public R select(){

        List<SysRole> roles = new ArrayList<>();
        if(Constants.SUPER_ADMIN.equals(getUserId())){
            // 自己创建的
            roles = sysRoleService.list(new QueryWrapper<SysRole>().eq("create_user_id", getUserId()));
        }

        else {
            // 自己拥有的
            roles = sysRoleService.getRolesByUserId(getUserId());
        }

        return R.success().put("list", roles);
    }


    @GetMapping("/list")
    @RequiresPermissions("sys:role:list")
    public R list(HttpServletRequest request, HttpServletResponse response){
        String page = request.getParameter("page");
        String limit = request.getParameter("limit");
        String roleName = request.getParameter("roleName");

        RoleListParam listParam = new RoleListParam();
        if(!StringUtils.isBlank(page)){
            listParam.setPage(Integer.valueOf(page));
        }
        if(!StringUtils.isBlank(limit)){
            listParam.setLimit(Integer.valueOf(limit));
        }
        if(!StringUtils.isBlank(roleName)){
            listParam.setRoleName(roleName);
        }

//        // 只能查看自己创建的用户
//        if(!Constants.SUPER_ADMIN.equals(getUserId())){
//            listParam.setUserId(getUserId());
//        }

        return sysRoleService.getRoles(listParam);
    }



    @PostMapping("/save")
    @RequiresPermissions("sys:role:save")
    public R save(@RequestBody RoleDataParam param){
        ValidationUtils.validateEntityGroup(param);
        param.setCreateUserId(getUserId());
        return sysRoleService.save(param);
    }


    @GetMapping("/info/{roleId}")
    public R info(@PathVariable("roleId") Long roleId){
        SysRole role = sysRoleService.getById(roleId);
        List<SysRoleMenu> roleMenus = sysRoleMenuService
            .list(new QueryWrapper<SysRoleMenu>().eq("role_id", roleId));
        List<Long> menuIdList = roleMenus.stream()
            .map(SysRoleMenu::getMenuId).collect(Collectors.toList());
        role.setMenuIdList(menuIdList);
        return R.success().put("role", role);
    }


    @PostMapping("/update")
    @RequiresPermissions("sys:role:update")
    public R update(@RequestBody RoleDataParam param){
        ValidationUtils.validateEntityGroup(param);
        return sysRoleService.update(param);
    }


}
