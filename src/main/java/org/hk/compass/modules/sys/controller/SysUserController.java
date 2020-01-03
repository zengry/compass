package org.hk.compass.modules.sys.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.hk.compass.constants.Constants;
import org.hk.compass.modules.sys.entity.SysRole;
import org.hk.compass.modules.sys.entity.SysUser;
import org.hk.compass.modules.sys.entity.SysUserRole;
import org.hk.compass.modules.sys.param.PasswordForm;
import org.hk.compass.modules.sys.param.UserDataForm;
import org.hk.compass.modules.sys.param.UserListParam;
import org.hk.compass.modules.sys.service.ISysUserRoleService;
import org.hk.compass.modules.sys.service.ISysUserService;
import org.hk.compass.util.Assert;
import org.hk.compass.util.PasswordHelper;
import org.hk.compass.util.R;
import org.hk.compass.validator.ValidationUtils;
import org.hk.compass.validator.group.AddGroup;
import org.hk.compass.validator.group.UpdateGroup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author zengry
 * @description 用户管理
 * @since 2019/12/31
 */
@RestController
@RequestMapping("/sys/user")
public class SysUserController extends AbstractController {

    @Autowired
    private ISysUserService sysUserService;
    @Autowired
    private ISysUserRoleService sysUserRoleService;

    @GetMapping("/info")
    public R info(){
        return R.success().put("user", getUser());
    }


    @GetMapping("/info/{userId}")
    public R info(@PathVariable("userId") Long userId){
        SysUser user = sysUserService.getById(userId);
        List<SysUserRole> roles = sysUserRoleService
            .list(new QueryWrapper<SysUserRole>().eq("user_id", userId));
        List<Long> roleIds = roles.stream()
            .map(SysUserRole::getRoleId).collect(Collectors.toList());
        user.setRoleIdList(roleIds);
        return R.success().put("user", user);
    }

    @PostMapping("/password")
    public R password(@RequestBody PasswordForm form){
        Assert.isBlank(form.getNewPassword(), "新密码不为能空");

        String salt = getUser().getSalt();
        String newPwd = PasswordHelper.encyptPassword(form.getNewPassword(), salt);
        String pwd = PasswordHelper.encyptPassword(form.getPassword(), salt);
        return sysUserService.resetPassword(getUserId(), pwd, newPwd);
    }

    @GetMapping("/list")
    @RequiresPermissions("sys:user:list")
    public R list(HttpServletRequest request, HttpServletResponse response){
        String page = request.getParameter("page");
        String limit = request.getParameter("limit");
        String username = request.getParameter("username");

        UserListParam listParam = new UserListParam();
        if(!StringUtils.isBlank(page)){
            listParam.setPage(Integer.valueOf(page));
        }
        if(!StringUtils.isBlank(limit)){
            listParam.setLimit(Integer.valueOf(limit));
        }
        if(!StringUtils.isBlank(username)){
            listParam.setUsername(username);
        }

        // 只能查看自己创建的用户
//        if(!Constants.SUPER_ADMIN.equals(getUserId())){
//            listParam.setUserId(getUserId());
//        }
        return sysUserService.pageQueryUserList(listParam);
    }


    @PostMapping("/update")
    @RequiresPermissions("sys:user:update")
    public R update(@RequestBody UserDataForm dataForm){
        ValidationUtils.validateEntityGroup(dataForm, UpdateGroup.class);
        return sysUserService.update(dataForm);
    }

    @PostMapping("/save")
    @RequiresPermissions("sys:user:save")
    public R save(@RequestBody UserDataForm dataForm){
        ValidationUtils.validateEntityGroup(dataForm, AddGroup.class);
        dataForm.setCreateUserId(getUserId());
        return sysUserService.save(dataForm);
    }


    @PostMapping("/delete")
    @RequiresPermissions("sys:user:delete")
    public R delete(@RequestBody Long[] userIds){
        return sysUserService.delete(Arrays.asList(userIds));
    }


}


